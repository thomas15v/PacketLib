package com.thomas15v.packetlib.codegenerator.methodgenerator;

import com.sun.codemodel.*;
import com.thomas15v.packetlib.codegenerator.asm.AsmType;

public class StaticHelperGenerator extends DefaultGenerator {

    private final String staticClass;
    private final String wrongType;
    private final String getMethodName;
    private final String setMethodName;

    public StaticHelperGenerator(AsmType type, String staticClass, String wrongType, String getMethodName, String setMethodName) {
        super(type);
        this.staticClass = staticClass;
        this.wrongType = wrongType;
        this.getMethodName = getMethodName;
        this.setMethodName = setMethodName;
    }

    @Override
    protected void generateSetBody(JDefinedClass dc, JCodeModel cm, JBlock block, JMethod setMethod, String fieldName) {
        JClass helperClass = cm.directClass(staticClass);
        block.invoke(setName)
                .arg(fieldName).arg(helperClass.staticInvoke(this.setMethodName).arg(setMethod.param(jType, fieldName)));
    }

    @Override
    protected void generateGetBody(JDefinedClass dc, JCodeModel cm, JBlock block, JMethod getMethod, String fieldName) {
        JClass helperClass = cm.directClass(staticClass);
        JExpression getField = JExpr.invoke(getName).arg(fieldName);
        block._return(helperClass.staticInvoke(getMethodName).arg(JExpr.cast(cm.directClass(wrongType), getField)));
    }

}
