package com.thomas15v.packetlib.codegenerator.methodgenerator;

import com.sun.codemodel.*;
import com.thomas15v.packetlib.codegenerator.CodeGenerator;
import com.thomas15v.packetlib.codegenerator.asm.AsmType;

public class DefaultGenerator implements MethodGenerator {

    protected AsmType typeClass;
    protected JType jType;

    protected static String getName = "get";
    protected static String setName = "set";

    public DefaultGenerator(AsmType type) {
        this.typeClass = type;
    }

    @Override
    public void generate(JDefinedClass dc, JCodeModel cm, String fieldName) {
       if (typeClass.isEnum()) {
            try {
                JDefinedClass enumClass = cm._class(CodeGenerator.PACKAGE_NAME + ".enums." + typeClass.getName().replace("net.minecraft.", "").replace("$","."), ClassType.ENUM);
                enumClass.field(JMod.PRIVATE, typeClass.getJtype(), "type");
                JMethod constructor = enumClass.constructor(JMod.PRIVATE);
                constructor.body().assign(JExpr._this().ref("type"), constructor.param(typeClass.getJtype(), "type"));
                enumClass.method(JMod.PUBLIC, typeClass.getJtype(), "getRealType").body()._return(JExpr._this().ref("type"));

                for (Object o : typeClass.getEnumConstants()) {
                    JEnumConstant enumConstant = enumClass.enumConstant(o.toString().toUpperCase());
                    enumConstant.arg(JExpr.direct(typeClass.getName().replace("$",".") + "." + o.toString().toUpperCase()));
                }
                jType = enumClass;
            } catch (JClassAlreadyExistsException e) {
                return;
            }
        } else {
            jType = typeClass.getJtype();
        }

        if (jType.fullName().contains("net.minecraft")){
            //System.out.println("WARNING: class " + dc.fullName() + " has " + jType.fullName() + " as method type, this is invalid and so ignored!" );
            return;
        }


        String setMethodName = "set" + fieldName;
        JMethod setMethod = dc.method(JMod.PUBLIC, cm.VOID, setMethodName);
        setMethod._throws(NoSuchFieldException.class);
        JBlock setMethodBlock = setMethod.body();
        generateSetBody(dc, cm, setMethodBlock, setMethod, fieldName);

        String getMethodName = "get" + fieldName;
        JMethod getMethod = dc.method(JMod.PUBLIC, jType, getMethodName);
        getMethod._throws(NoSuchFieldException.class);
        JBlock getMethodBlock = getMethod.body();
        generateGetBody(dc, cm, getMethodBlock, getMethod, fieldName);
    }

    protected void generateSetBody(JDefinedClass dc, JCodeModel cm, JBlock block, JMethod setMethod, String fieldName){
        block.invoke(setName).arg(fieldName).arg(setMethod.param(jType, fieldName));
    }

    protected void generateGetBody(JDefinedClass dc, JCodeModel cm, JBlock block, JMethod getMethod, String fieldName){
        block._return(JExpr.cast(getMethod.type(), JExpr.invoke(getName).arg(fieldName)));
    }
}
