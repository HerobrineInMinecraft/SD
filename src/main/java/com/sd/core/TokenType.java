package com.sd.core;

/**
 * SD语言令牌类型枚举
 * 命名规范：全大写+下划线分隔，语义清晰，避免缩写
 * 分类原则：按语法功能分组，同一类Token放在一起
 */
public enum TokenType {
    // ================================================
    // 1. 访问与修饰符关键字
    // ================================================
    // 变量可变性修饰
    LET,                // 不可变绑定
    MUT,                // 可变修饰
    CONST,              // 编译期常量
    READONLY,           // 运行时只读
    STATIC,             // 静态成员
    GLOBAL,             // 全局作用域
    FINAL,              // 禁止修改/重写/继承

    // 访问权限控制
    PUB,                // 公开访问
    PRIVATE,            // 私有访问
    PROTECTED,          // 受保护访问
    INTERNAL,           // 模块内访问
    LOCAL,              // 局部作用域

    // 继承与多态修饰
    OPEN,               // 允许继承
    SEALED,             // 密封禁止继承
    ABSTRACT,           // 抽象定义
    VIRTUAL,            // 虚方法
    OVERRIDE,           // 方法重写
    EXTENDS,            // 类继承
    IMPLEMENTS,         // 接口实现
    THIS,               // 当前实例
    SUPER,              // 父类引用

    // 线程安全与并发修饰
    VOLATILE,           // 线程可见性
    SYNCHRONIZED,       // 同步锁定
    SYNC,               // 线程同步约束
    SEND,               // 跨线程安全约束
    UNSAFE,             // 不安全代码域

    // 序列化相关修饰
    TRANSIENT,          // 序列化忽略
    SERIAL,             // 可序列化标识

    // 编译期行为修饰
    CONSTEXPR,          // 编译期求值
    CONSTEVAL,          // 强制编译执行
    CONSTINIT,          // 静态常量初始化
    NOEXCEPT,           // 无异常声明
    INLINE,             // 普通内联
    ALWAYS_INLINE,      // 强制内联
    NOINLINE,           // 禁止内联

    // 初始化与执行修饰
    LATEINIT,           // 延后初始化
    IMPLICIT,           // 隐式转换
    EXPLICIT,           // 显式转换

    // Python专属作用域
    NONLOCAL,           // 外层作用域引用

    // 语法模式修饰
    STRICT,             // 严格语法模式
    RELAXED,            // 宽松语法模式

    // 内存所有权修饰
    OWN,                // 独占所有权
    MANAGED,            // 托管内存
    UNMANAGED,          // 非托管内存

    // ================================================
// 2. 类型与定义关键字（无重复 | Java+Rust+Python+C++/C#/Go 融合）
// ================================================
// 函数与闭包
    FN,                 // 函数声明（Rust/SD 风格）
    FUNC,               // 函数别名/显式函数定义
    LAMBDA,             // Lambda 表达式
    CLOSURE,            // 闭包

    // 复合类型结构
    CLASS,              // 类（Java/C#）
    STRUCT,             // 结构体（Rust/C++）
    ENUM,               // 枚举
    UNION,              // 联合体
    INTERFACE,          // 接口（Java）
    TRAIT,              // 特征（Rust）
    IMPL,               // 实现（Rust）
    RECORD,             // 记录类型
    DELEGATE,           // 委托（C#）
    EVENT,              // 事件（C#）

    // 类型别名 & 类型系统
    TYPE,               // 类型别名
    TYPEDEF,            // C 风格类型定义
    WHERE,              // 泛型约束
    BOUND,              // 类型边界
    IN,                 // 协变
    OUT,                // 逆变
    INOUT,              // 不变
    DYN,                // 动态分发
    OPAQUE,             // 不透明类型
    CONCEPT,            // 概念（C++20）
    REQUIRES,           // 约束
    TEMPLATE,           // 模板（C++）
    TYPENAME,           // 模板类型参数

    // 模块 & 包 & 导入导出
    NAMESPACE,          // 命名空间（C#/C++）
    MODULE,             // 模块
    PACKAGE,            // 包（Java）
    IMPORT,             // 导入
    EXPORT,             // 导出
    FROM,               // 从...导入
    USING,              // 使用（C#/C++）

    // 类型操作 & 运算符 & 转换
    NEW,                // 创建实例
    DELETE,             // 销毁实例
    OPERATOR,           // 运算符重载
    SIZEOF,             // 大小
    ALIGNOF,            // 对齐
    ALIGNAS,            // 指定对齐
    AS,                 // 类型转换
    IS,                 // 类型判断
    INSTANCEOF,         // 实例判断
    TYPEOF,             // 获取类型
    // ================================================
    // 3. 所有权与内存管理关键字
    // ================================================
    BORROW,             // 借用
    REF,                // 引用
    MUT_REF,            // 可变引用
    MOVE,               // 移动语义
    DROP,               // 析构函数
    BOX,                // 堆分配智能指针
    RAWPTR,             // 原始指针
    NULLPTR,            // 空指针
    UNDEFINED,          // 未定义值
    VOID,               // 空类型
    UNIT,               // 单元类型

    OPTION,             // 可选类型
    SOME,               // 有值
    NONE,               // 无值
    RESULT,             // 结果类型
    OK,                 // 成功
    ERR,                // 错误

    WEAK,               // 弱引用
    STRONG,             // 强引用
    ARC,                // 原子引用计数
    RC,                 // 引用计数
    CELL,               // 内部可变性
    REFCELL,            // 运行时检查内部可变性
    FINALIZE,           // 终结器
    GC,                 // 垃圾回收
    // ================================================
    // 4. 流程控制关键字
    // ================================================
    IF,                 // if 条件
    ELSE,               // else 分支
    SWITCH,             // 多分支选择
    CASE,               // 分支匹配
    MATCH,              // 模式匹配
    DEFAULT,            // 默认分支

    WHILE,              // while 循环
    FOR,                // for 循环
    LOOP,               // 无限循环
    DO,                 // do-while 循环
    UNTIL,              // until 循环
    BREAK,              // 跳出循环
    CONTINUE,           // 跳过当前循环

    RETURN,             // 函数返回
    YIELD,              // 生成器返回
    GOTO,               // 跳转
    PASS,               // 空语句
    WITH,               // 资源管理
    DEFER,              // 延迟执行

    TRY,                // 异常尝试
    CATCH,              // 异常捕获
    FINALLY,            // 最终执行
    THROW,              // 抛出异常
    EXCEPT,             // 异常捕获
    PANIC,              // 程序崩溃
    ABORT,              // 强制终止
    EXIT,               // 退出程序
    UNREACHABLE,         // 标记不可达
    // ================================================
    // 5. 异步与并发关键字
    // ================================================
    ASYNC,              // 异步函数
    AWAIT,              // 等待异步任务
    FUTURE,             // 异步结果
    PROMISE,            // 承诺对象
    TASK,               // 任务
    COROUTINE,          // 协程
    GENERATOR,          // 生成器
    RESUME,             // 恢复协程

    THREAD,             // 线程
    PROCESS,            // 进程
    SPAWN,              // 启动任务/线程
    JOIN,               // 等待线程结束
    DETACH,             // 分离线程

    CHANNEL,            // 消息通道
    SENDER,             // 发送端
    RECEIVER,           // 接收端

    LOCK,               // 锁
    MUTEX,              // 互斥锁
    RWLOCK,             // 读写锁
    BARRIER,            // 屏障
    ONCE,               // 一次性执行
    SHARED,             // 共享
    EXCLUSIVE,           // 独占
    // ================================================
    // 6. 元编程关键字
    // ================================================
    MACRO,              // 宏
    MACRO_RULES,        // 声明宏
    PROC_MACRO,         // 过程宏
    DERIVE,             // 自动派生
    ATTRIBUTE,          // 属性
    ANNOTATION,         // 注解
    COMPTIME,           // 编译期执行
    SPECIALIZATION,     // 模板特化
    SFINAE,             // 替换失败不是错误
    SATISFY,            // 满足约束
    TRAIT_OBJECT,       // 特征对象
    META,               // 元数据
    REFLECT,            // 反射
    RUNTIME,            // 运行时
    CASTOF,             // 类型强转
    TYPE_META,          // 类型元信息
    EVAL,               // 动态求值
    UNQUOTE,            // 解引用插值
    INCLUDE,            // 引入文件
    PREPROC,            // 预处理指令
    EXPAND,             // 宏展开
    CONST_EVAL,         // 常量求值
    TYPE_INFO,          // 运行时类型信息
    TYPE_ID,            // 类型唯一标识
    METACLASS,          // 元类(Python)
    INTRINSIC,          // 内置编译函数
    GENERIC,            // 泛型标识
    WHERE_CLAUSE,       // 多条件约束子句
    // ================================================
    // 7. 算术运算符
    // ================================================
    PLUS,               // +
    MINUS,              // -
    STAR,               // *
    SLASH,              // /
    PERCENT,            // %
    POWER,              // **
    INCREMENT,          // ++
    DECREMENT,          // --
    FLOOR_DIV,          // // 整除 (Python)
    MOD_ASSIGN,         // %= 取模赋值
    PLUS_PLUS,          // ++ 自增（冗余兼容）
    MINUS_MINUS,        // -- 自减（冗余兼容）
    ABS,                // |x| 绝对值
    NEGATE,             // - 负号
    // ================================================
    // 8. 比较运算符
    // ================================================
    EQ,                 // ==
    NE,                 // !=
    LT,                 // <
    GT,                 // >
    LE,                 // <=
    GE,                 // >=
    IDENTICAL,          // === 严格相等
    NOT_IDENTICAL,      // !== 严格不等
    COMPARE,            // <=> 三路比较 (Rust/C++)
    NOT_IN,             // not in 不包含
    IS_NONE,            // is None 空判断
    IS_NOT_NONE,         // is not None 非空判断
    // ================================================
    // 9. 逻辑运算符
    // ================================================
    AND,                // && 逻辑与
    OR,                 // || 逻辑或
    NOT,                // !  逻辑非
    XOR,                // ^  逻辑异或
    LOGICAL_AND,        // and (Python风格)
    LOGICAL_OR,         // or  (Python风格)
    LOGICAL_NOT,        // not (Python风格)
    LOGICAL_XOR,        // ^  逻辑异或补充
    // ================================================
    // 10. 位运算符
    // ================================================
    BIT_AND,            // &  按位与
    BIT_OR,             // |  按位或
    BIT_NOT,            // ~  按位非
    BIT_XOR,            // ^  按位异或
    SHL,                // << 左移
    SHR,                // >> 算术右移
    USHR,               // >>> 逻辑右移
    ROT_L,              // 循环左移
    ROT_R,              // 循环右移
    BIT_CLEAR,          // 位清除
    BIT_SET,            // 位设置
    BIT_TEST,            // 位测试
    // ================================================
    // 11. 赋值运算符
    // ================================================
    ASSIGN,             // =   基础赋值
    PLUS_ASSIGN,        // +=  加赋值
    MINUS_ASSIGN,       // -=  减赋值
    STAR_ASSIGN,        // *=  乘赋值
    SLASH_ASSIGN,       // /=  除赋值
    PERCENT_ASSIGN,     // %=  取模赋值
    BIT_AND_ASSIGN,     // &=  按位与赋值
    BIT_OR_ASSIGN,      // |=  按位或赋值
    BIT_XOR_ASSIGN,     // ^=  按位异或赋值
    SHL_ASSIGN,         // <<= 左移赋值
    SHR_ASSIGN,         // >>= 右移赋值
    USHR_ASSIGN,        // >>>= 无符号右移赋值
    POWER_ASSIGN,       // **= 幂赋值
    FLOOR_DIV_ASSIGN,   // //= 整除赋值
    NULL_ASSIGN,        // ??= 空合并赋值
    AND_ASSIGN,         // &&= 逻辑与赋值
    OR_ASSIGN,          // ||= 逻辑或赋值
    // ================================================
    // 12. 字面量
    // ================================================
    IDENTIFIER,         // 标识符
    INTEGER,            // 整数字面量
    FLOAT,              // 浮点数字面量
    STRING,             // 字符串
    RAW_STRING,         // 原始字符串
    CHAR,               // 字符
    BOOLEAN,            // 布尔
    TENSOR,             // 张量
    VECTOR,             // 向量
    MATRIX,             // 矩阵
    HEX_INTEGER,        // 十六进制整数
    BIN_INTEGER,        // 二进制整数
    OCT_INTEGER,        // 八进制整数
    DOUBLE,             // 双精度浮点数
    COMPLEX,            // 复数字面量
    NULL_LIT,           // null 空值
    TRUE_LIT,           // true 字面量
    FALSE_LIT,          // false 字面量
    NONE_LIT,           // None 空值 (Python风格)
    REGEX,              // 正则表达式字面量
    DATE,               // 日期字面量
    DATETIME,           // 日期时间字面量
    // ================================================
    // 13. 分隔符与标点符号
    // ================================================
    SEMI,               // ; 分号
    COMMA,              // , 逗号
    DOT,                // . 点
    COLON,              // : 冒号
    DOUBLE_COLON,       // :: 双冒号
    QUESTION,           // ? 问号
    QUESTION_DOT,       // ?. 空值访问
    QUESTION_QUESTION,  // ?? 空合并
    ELLIPSIS,           // ... 可变参数
    ARROW,              // -> 箭头
    FAT_ARROW,          // => 胖箭头
    AT,                 // @ 注解
    HASH,               // # 预处理/注释
    DOLLAR,             // $ 变量插值
    RANGE,              // .. 范围
    RANGE_INCLUSIVE,    // ..= 闭区间范围
    BACKTICK,           // ` 反引号
    QUOTE,              // " 双引号
    SINGLE_QUOTE,       // ' 单引号
    BACKSLASH,          // \ 转义符
    SLASH_BACKSLASH,    // /\ 特殊符号
    TILDE,              // ~ 波浪号
    CARET,              // ^ 脱字符
    AMPERSAND,          // & 和号
    PIPE,               // | 竖线
    ASSIGN_MARK,        // = 等号（符号兼容）
    EXCLAMATION,        // ! 感叹号
    ANGLE_LEFT,         // < 左尖括号
    ANGLE_RIGHT,         // > 右尖括号
    // ================================================
    // 14. 括号类
    // ================================================
    LPAREN,             // ( 左小括号
    RPAREN,             // ) 右小括号
    LBRACE,             // { 左大括号
    RBRACE,             // } 右大括号
    LBRACKET,           // [ 左中括号
    RBRACKET,           // ] 右中括号
    LANGLE,             // < 左尖括号
    RANGLE,             // > 右尖括号
    LQUOTE,             // “ 左双引号
    RQUOTE,             // ” 右双引号
    LCHEV,              // « 左书名号
    RCHEV,               // » 右书名号
    // ================================================
    // 15. 特殊Token
    // ================================================
    LINE_COMMENT,       // 单行注释
    BLOCK_COMMENT,      // 块注释
    DOC_COMMENT,        // 文档注释
    WHITESPACE,         // 空白
    NEWLINE,            // 换行
    EOF,                // 文件结束
    UNKNOWN,            // 未知字符
    ERROR,              // 词法错误
    INDENT,             // 缩进 (Python风格)
    DEDENT,             // 反缩进
    SHEBANG,            // #! 脚本声明
    ESCAPE,             // 转义序列
    CONTINUE_LINE,      // 行继续符
    MACRO_CALL,         // 宏调用
    ANNOTATION_MARK     // 注解标记
}