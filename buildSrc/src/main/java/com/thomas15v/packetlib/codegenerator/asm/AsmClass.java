package com.thomas15v.packetlib.codegenerator.asm;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AsmClass {

    private ClassNode classNode;
    private List<AsmClass> innerClasses;

    public AsmClass(ClassNode classNode, AsmJarFile jarFile){
        if (classNode == null)
            return;
        this.classNode = classNode;
        this.innerClasses = classNode.innerClasses.size() != 0 && !classNode.name.contains("$") ? getInnerClasseNodes().stream()
                .map(innerClassNode -> {
                    try {
                        return jarFile.getClass(innerClassNode.name);
                    } catch (Exception e) {
                        //System.out.println("WARNING: innerclass " + innerClassNode.name + " not found for " + classNode.name);
                    }
                    return null;
                })
                .filter(value -> value != null)
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    public ClassNode getNode() {
        return classNode;
    }

    public List<AsmClass> getInnerClasses(){
        return innerClasses;
    }

    public String getClassName(){
        String name = ASMHelper.getSimpleName(classNode.name);
        return name.substring(name.lastIndexOf('$') + 1);
    }

    public String getName(){
        return classNode.name.replace("$", ".").replace("/", ".");
    }

    public boolean isAssignableFrom(AsmClass asmClass){
        return isAssignableFrom(asmClass.classNode.name);
    }

    public boolean isAssignableFrom(String asmClass){
        asmClass = asmClass.replace(".", "/");
        return getNode().interfaces.contains(asmClass)
                || getNode().superName.equals(asmClass);
    }

    public List<FieldNode> getFields(){
        return classNode.fields;
    }

    private List<InnerClassNode> getInnerClasseNodes(){
        return classNode.innerClasses;
    }

    public String getDefaultValue(){
        return "null";
    }

    public List<String> getInterfaces(){
        return classNode.interfaces;
    }
}
