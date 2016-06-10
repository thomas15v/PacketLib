package com.thomas15v.packetlib.codegenerator.asm;

import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class AsmJarFile {

    private final List<JarFile> jars;
    private Map<String, AsmClass> asmClasses = new HashMap<>();

    public AsmJarFile(File... files){
        jars = Arrays.stream(files).map(file -> {
            try {
                return new JarFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        jars.forEach(jarFile -> jarFile.stream()
                .filter(clazz -> clazz.getName().endsWith(".class") && !clazz.getName().contains("\\$") )
                .map(clazz -> {
                    try {
                        return getClass(clazz.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .forEach(asmClass -> asmClasses.put(asmClass.getNode().name.replace("$", ".").replace("/", "."), asmClass)));
    }

    public Collection<AsmClass> getClasses(){
        return asmClasses.values();
    }

    public AsmClass getClass(String name) throws IOException, ClassNotFoundException {
        String keyName = name.replace("$", ".").replace("/",".");
        if (asmClasses.containsKey(keyName)){
            return asmClasses.get(keyName);
        }

        final String className = name.replace(".class","").replace(".", "/") + ".class";

        Optional<ZipEntry> zipEntry = returnOne(jar -> jar.getEntry(className));
        ClassReader classReader;
        if (zipEntry.isPresent()) {
            classReader = new ClassReader(returnOne(jar -> {
                try {
                    return jar.getInputStream(zipEntry.get());
                } catch (IOException ignored) {}
                return null;
            }).get());
        } else {
            Class clazz = ClassUtils.getClass(keyName);
            if (clazz.isPrimitive()){
                return new PrimitiveAsmClass(clazz);
            }
            classReader = new ClassReader(clazz.getName());
        }

        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);

        return new AsmClass(classNode, this);
    }

    /**
     *
     * @param clazz quallified name for the class extending the classes you want
     * @return
     */
    public List<AsmClass> getClassesFor(String clazz){
        return getClasses().stream().filter(classNode -> classNode.isAssignableFrom(clazz)).collect(Collectors.toList());
    }

    private  <I> Optional<I> returnOne(Function<JarFile, I> function){
        return jars.stream().map(function).filter(value -> value != null).findFirst();
    }

}
