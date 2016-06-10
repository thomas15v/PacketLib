package com.thomas15v.packetlib.codegenerator;

import com.sun.codemodel.JCodeModel;
import com.thomas15v.packetlib.codegenerator.asm.ASMHelper;
import com.thomas15v.packetlib.codegenerator.asm.AsmClass;
import com.thomas15v.packetlib.codegenerator.asm.AsmJarFile;
import com.thomas15v.packetlib.codegenerator.asm.AsmType;
import com.thomas15v.packetlib.codegenerator.methodgenerator.*;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpongeBinder {

    private Map<String, MethodGenerator> classMap = new HashMap<>();
    private AsmJarFile asmJarFile;
    private JCodeModel mc;

    public SpongeBinder(AsmJarFile asmJarFile, JCodeModel mc){
        this.asmJarFile = asmJarFile;
        this.mc = mc;
    }

    private void addStaticGenerators(){
        AsmType vector3i = new AsmType("com.flowpowered.math.vector.Vector3i", mc);

        addGenerator("net.minecraft.util.BlockPos", new BlockPosGenerator(vector3i));
        addGenerator("net.minecraft.util.IChatComponent", new TextGenerator(new AsmType("org.spongepowered.api.text.Text", mc)));
        addGenerator("net.minecraft.util.Vec3", new Vec3Generator(new AsmType("com.flowpowered.math.vector.Vector3d", mc)));
        addGenerator("net.minecraft.world.ChunkCoordIntPair", new ChunkCoordIntPairGenerator(vector3i));
    }

    public void index(){
        classMap.clear();

        addStaticGenerators();

        List<AsmClass> spongeClasses = asmJarFile.getClasses().stream()
                .filter(asmClass -> asmClass.getName().startsWith("org.spongepowered")).collect(Collectors.toList());

        spongeClasses.forEach(asmClass -> {
            List<Type> mixinTargets = getMixin(asmClass.getNode());
            if (mixinTargets != null) {
                List<String> interfaces = getInterfaces(asmClass);
                if (interfaces != null){
                    interfaces.forEach(inter -> mixinTargets.forEach(mixin ->
                            addGenerator(mixin.getClassName(), new DefaultGenerator(new AsmType(inter, mc)))
                    ));
                }
            }
        });
    }

    public MethodGenerator getGeneratorFor(AsmType type) {
        if (classMap.containsKey(type.getName())){
            return classMap.get(type.getName());
        }
        for (String s : type.getInterfaces()) {
            if (classMap.containsKey(s)){
                return classMap.get(s);
            }
        }
        return new DefaultGenerator(type);
    }

    public void addGenerator(String clazz, MethodGenerator generator){
        classMap.put(clazz.replace("$", "."), generator);
    }

    private List<Type> getMixin(ClassNode classNode){
        try {
            AnnotationNode mixinAnnotation = ASMHelper.getAnnotation(classNode.invisibleAnnotations, "Lorg/spongepowered/asm/mixin/Mixin;");
            if (mixinAnnotation == null) {
                return null;
            }
            return ASMHelper.getAnnotationValue(mixinAnnotation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getInterfaces(AsmClass classNode){
        List<String> returnData = new ArrayList<>();

        AnnotationNode mixinAnnotation = ASMHelper.getAnnotation(classNode.getNode().invisibleAnnotations, "Lorg/spongepowered/asm/mixin/Implements;");
        if (mixinAnnotation != null){
            List<AnnotationNode> annotationNodes = ASMHelper.getAnnotationValue(mixinAnnotation);
            if (annotationNodes != null) {
                for (AnnotationNode annotationNode : annotationNodes) {
                    Type itype = ASMHelper.getAnnotationValue(annotationNode, "iface");
                    returnData.add(itype.getClassName());
                }
            }
        }
        classNode.getInterfaces().stream()
                .filter(s -> s.contains("org/spongepowered/api"))
                .map(s -> s.replace("/", "."))
                .forEach(returnData::add);
        return returnData;
    }

}
