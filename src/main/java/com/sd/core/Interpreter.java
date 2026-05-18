package com.sd.core;

import com.sd.core.ast.*;
import java.util.HashMap;
import java.util.Map;

public class Interpreter implements AstVisitor<Object> {
    private final Map<String, Object> env = new HashMap<>();
    private static final Map<String, Object> globalEnv = new HashMap<>();
    private static final Map<String, Object> staticEnv = new HashMap<>();

    // ====================== OOP 核心结构 ======================
    public static class SDObject {
        public Map<String, Object> fields = new HashMap<>();
        public SDObject parent;
    }

    private SDObject currentInstance; // this
    private SDObject currentParent;   // super

    // ==========================================================

    public void run(AstNode node) {
        try { visit(node); }
        catch (Exception e) { System.err.println("运行错误：" + e.getMessage()); }
    }

    private Object visit(AstNode n) {
        if (n instanceof LiteralExpr e) return visitLiteral(e);
        if (n instanceof VarExpr e) return visitVar(e);
        if (n instanceof BinaryExpr e) return visitBinary(e);
        if (n instanceof LetStmt s) return visitLet(s);
        if (n instanceof ExprStmt s) return visitExpr(s);
        if (n instanceof BlockStmt s) return visitBlock(s);
        if (n instanceof IfStmt s) return visitIf(s);
        if (n instanceof SwitchStmt s) return visitSwitch(s);
        if (n instanceof MatchStmt s) return visitMatch(s);
        if (n instanceof CaseStmt s) return visitCase(s);
        throw new RuntimeException("未知节点");
    }

    @Override public Object visitLiteral(LiteralExpr expr) { return expr.value; }

    @Override
    public Object visitVar(VarExpr expr) {
        String k = expr.token.text;

        // ====================== THIS / SUPER 真实可用 ======================
        if (k.equals("this")) {
            if (currentInstance == null) {
                currentInstance = new SDObject();
            }
            return currentInstance.fields;
        }
        if (k.equals("super")) {
            if (currentParent == null && currentInstance != null) {
                currentParent = currentInstance.parent;
                if (currentParent == null) currentParent = new SDObject();
            }
            return currentParent != null ? currentParent.fields : Map.of();
        }

        if (globalEnv.containsKey(k)) return globalEnv.get(k);
        if (staticEnv.containsKey(k)) return staticEnv.get(k);
        if (env.containsKey(k)) return env.get(k);
        throw new RuntimeException("变量未定义：" + k);
    }

    @Override
    public Object visitBinary(BinaryExpr e) {
        double l = toDouble(visit(e.left));
        double r = toDouble(visit(e.right));
        return switch (e.token.type) {
            case PLUS -> l + r; case MINUS -> l - r; case STAR -> l * r;
            case SLASH -> l / r; case PERCENT -> l % r; case POWER -> Math.pow(l, r);
            case EQ -> l == r; case NE -> l != r; case LT -> l < r;
            case GT -> l > r; case LE -> l <= r; case GE -> l >= r;
            default -> 0.0;
        };
    }

    @Override
    public Object visitLet(LetStmt stmt) {
        String name = stmt.name.token.text;
        Object val = visit(stmt.init);

        VarModifiers m = stmt.mods;

        String vis = "";
        if (m.isPub) vis = "public ";
        else if (m.isPrivate) vis = "private ";
        else if (m.isProtected) vis = "protected ";
        else if (m.isInternal) vis = "internal ";

        if (m.isLocal) vis = "local ";

        // ====================== 线程并发修饰符 ======================
        String threadMods = "";
        if (m.isVolatile) threadMods += "volatile ";
        if (m.isSynchronized) threadMods += "synchronized ";
        if (m.isSync) threadMods += "sync ";
        if (m.isSend) threadMods += "send ";
        if (m.isUnsafe) threadMods += "unsafe ";

        String inherit = "";
        if (m.isOpen)      inherit += "open ";
        if (m.isSealed)    inherit += "sealed ";
        if (m.isAbstract)  inherit += "abstract ";
        if (m.isVirtual)   inherit += "virtual ";
        if (m.isOverride)  inherit += "override ";

        String frozen = "";
        if (m.isFinal) frozen = "final ";
        if (m.isMut) frozen = "mut ";
        if (m.isConst) frozen = "const ";

        // 拼接所有前缀
        String prefix = vis + threadMods + inherit + frozen;

        // ====================== 线程安全校验 ======================
        if (m.isSend && m.isUnsafe) {
            throw new RuntimeException("错误：send 线程安全类型不能标记为 unsafe");
        }
        if (m.isSynchronized && m.isSync) {
            throw new RuntimeException("错误：synchronized 与 sync 不能同时使用");
        }

        // ====================== OOP 校验 ======================
        if (m.isSealed && m.isOpen) {
            throw new RuntimeException("错误：不能同时 sealed 和 open");
        }
        if (m.isAbstract && m.isSealed) {
            throw new RuntimeException("错误：抽象不能被密封");
        }
        if (m.isOverride && !m.isVirtual) {
            throw new RuntimeException("错误：重写必须标记 virtual 方法");
        }
        if (m.isAbstract && val != null) {
            throw new RuntimeException("错误：抽象不能包含值");
        }

        // 局部变量
        if (m.isLocal) {
            env.put(name, val);
            System.out.println("[本地" + prefix + "] " + name + " = " + val);
            return null;
        }

        // 常量不可修改
        boolean isConstant = m.isConst || m.isFinal || m.isReadonly;
        if (isConstant) {
            if (globalEnv.containsKey(name) || staticEnv.containsKey(name) || env.containsKey(name)) {
                throw new RuntimeException("错误：常量/只读变量不可修改 → " + name);
            }
        }

        // 存储
        if (m.isGlobal) {
            globalEnv.put(name, val);
            System.out.println("[全局" + prefix + "] " + name + " = " + val);
        } else if (m.isStatic) {
            staticEnv.put(name, val);
            System.out.println("[静态" + prefix + "] " + name + " = " + val);
        } else {
            env.put(name, val);
            System.out.println("[本地" + prefix + "] " + name + " = " + val);
        }

        return null;
    }

    @Override public Object visitExpr(ExprStmt stmt) { visit(stmt.expr); return null; }
    @Override public Object visitBlock(BlockStmt stmt) { for (var s : stmt.stmts) visit(s); return null; }
    @Override public Object visitIf(IfStmt stmt) { if (isTrue(visit(stmt.cond))) visit(stmt.thenBranch); else if (stmt.elseBranch != null) visit(stmt.elseBranch); return null; }
    @Override public Object visitSwitch(SwitchStmt stmt) {
        Object v = visit(stmt.expr);
        for (var c : stmt.cases) if (v.equals(visit(c.value))) { visit(c.body); return null; }
        if (stmt.defaultCase != null) visit(stmt.defaultCase);
        return null;
    }
    @Override public Object visitMatch(MatchStmt stmt) {
        Object v = visit(stmt.expr);
        for (var c : stmt.branches) if (v.equals(visit(c.value))) { visit(c.body); return null; }
        return null;
    }
    @Override public Object visitCase(CaseStmt stmt) { return null; }
    private boolean isTrue(Object o) { return o instanceof Boolean b ? b : ((Number)o).doubleValue() != 0; }
    private double toDouble(Object o) { return ((Number)o).doubleValue(); }
}