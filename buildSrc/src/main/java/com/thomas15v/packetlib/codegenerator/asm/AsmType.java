package com.thomas15v.packetlib.codegenerator.asm;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JType;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;
import org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AsmType {

    private JType jType;
    private AsmClass asmClass;
    private String classname;
    private boolean Enum = false;
    private List<JClass> interfaces = new ArrayList<>();

    public AsmType(String classname, JCodeModel cm){
        this.classname = classname.replace("$", ".");
        this.jType = cm.ref(this.classname);
    }

    public AsmType(AsmJarFile jarFile, FieldNode name, JCodeModel cm){
        Type type = Type.getType(name.desc);
        this.classname = type.getClassName().replace("$", ".").replace("/",".");
        boolean isArray = classname.contains("[]");
        if (isArray) {
            classname = classname.replaceAll("\\[\\]", "");
        }

        try {
            asmClass = jarFile.getClass(classname);
            Enum = asmClass.getNode().superName.contains("Enum");
        } catch (Exception ignored) {}

        JClass clazz = cm.ref(classname);
        if (isArray){
            clazz = clazz.array();
        }
        if (name.signature != null){
            interfaces = getGenericClass(name.signature, cm);
            clazz = clazz.narrow(interfaces);
        }
        jType = clazz;
    }

    private List<JClass> getGenericClass(String signature, JCodeModel cm){
        final List<JClass> classes = new ArrayList<>();
        if (signature != null) {
            SignatureReader reader = new SignatureReader(signature);

            reader.accept(new SignatureVisitor(Opcodes.ASM5) {
                @Override
                public void visitClassType(String name) {
                    classes.add(cm.directClass(name.replace("/", ".").replace("$", ".")));
                }
            });
            classes.remove(0);
        }
        return classes;
    }

    public List<String> getInterfaces() {
        return interfaces.stream().map(jClass -> jClass.name().replace("$", ".")).collect(Collectors.toList());
    }

    public JType getJtype(){
        return jType;
    }

    public String getName(){
        return classname;
    }

    public boolean isEnum() {
        return Enum;
    }

    public List<String> getEnumConstants(){
        String className =  "L" + asmClass.getNode().name + ";";
        return asmClass.getFields().stream()
                .filter(fieldNode -> fieldNode.desc.equals(className))
                .filter(fieldNode -> !fieldNode.name.contains("$"))
                .map(fieldNode -> fieldNode.name).collect(Collectors.toList());
    }

    public String getDefaultValue(){
        return asmClass != null ? asmClass.getDefaultValue() : "null";
    }
}
