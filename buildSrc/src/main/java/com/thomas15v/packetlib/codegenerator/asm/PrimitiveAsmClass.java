package com.thomas15v.packetlib.codegenerator.asm;

import com.google.common.base.Defaults;

/**
 * Created by thomas15v on 09/06/16.
 */
public class PrimitiveAsmClass extends AsmClass {

    private String def;

    public PrimitiveAsmClass(Class param) {
        super(null, null);
        def = param.isArray() ? "null" : Defaults.defaultValue(param).toString();
    }

    @Override
    public String getDefaultValue() {
        return def;
    }
}
