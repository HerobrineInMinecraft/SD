package com.sd;

import com.sd.std.core.basic.enumeration.EnumerationStd;
import com.sd.std.core.basic.primitive.PrimitiveTypes;
import com.sd.std.core.basic.struct.StructStd;
import com.sd.std.core.basic.types.BasicTypes;
import com.sd.std.core.basic.types.StdLibTypes;
import com.sd.std.core.basic.wrapper.WrapperStd;
import com.sd.vm.SDVm;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
        StdLibTypes.register();
        BasicTypes.register();
        PrimitiveTypes.register();
        WrapperStd.register();
        EnumerationStd.register();
        StructStd.register();

        String code =   """
                        import sd.core.basic.types;
                        import sd.core.basic.primitive;
                        import sd.core.basic.wrapper;
                        import sd.core.basic.enumeration;
                        import sd.core.basic.struct;
                
                        let a:i32 = 100;
                        let b:f64 = 3.14;
                        let c:bool = true;
                        let d:str = "Hello SD";
                        let e:i32 = 100;
                        let f:i64 = 314;
                        let g:i64 = e + f;
                        """;

        SDVm vm = new SDVm();
        vm.runSource(code);
    }
}