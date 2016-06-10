package com.thomas15v.packetlib.codegenerator.methodgenerator;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

public interface MethodGenerator {

    void generate(JDefinedClass dc, JCodeModel cm, String fieldName);

}
