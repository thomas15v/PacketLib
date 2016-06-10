package com.thomas15v.packetlib.codegenerator;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.sun.codemodel.*;
import com.thomas15v.packetlib.codegenerator.asm.AsmClass;
import com.thomas15v.packetlib.codegenerator.asm.AsmJarFile;
import com.thomas15v.packetlib.codegenerator.asm.AsmType;
import org.apache.commons.io.FileUtils;
import org.gradle.logging.ProgressLogger;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {

    public static final String PACKAGE_NAME = "com.thomas15v.packetlib.api.packets";
    public static final String PACKET_CLASS_NAME = "net/minecraft/network/Packet";

    private File forgeFile;
    private File spongeFile;
    private final JCodeModel cm = new JCodeModel();
    private Map<String, String> packetMapping = new HashMap<>();
    private SpongeBinder spongeBinder;
    private AsmJarFile jarFile;
    private JType packetClass = cm.directClass(PACKET_CLASS_NAME.replace("/","."));

    public CodeGenerator(File forgeFile, File spongeFile){
        this.forgeFile = forgeFile;
        this.spongeFile = spongeFile;
    }

    public void generate(File file) {
        try {
            jarFile = new AsmJarFile(forgeFile, spongeFile);
            spongeBinder = new SpongeBinder(jarFile, cm);
            spongeBinder.index();

            if (file.exists()) {
                FileUtils.deleteDirectory(file);
            }
            file.mkdirs();

            List<AsmClass> classList = jarFile.getClassesFor(PACKET_CLASS_NAME);
            classList.forEach(this::generateClassFor);

            try {
                JDefinedClass dc = cm._class(PACKAGE_NAME + ".PacketRegistery");
                JClass hashBiMapClass = cm.directClass(HashBiMap.class.getName());
                dc.field(JMod.STATIC | JMod.PRIVATE, cm.directClass(BiMap.class.getName()).narrow(Class.class, Class.class), "Packets", hashBiMapClass.staticInvoke("create"));

                JMethod getClassForNMSPacketMethod = dc.method(JMod.PUBLIC | JMod.STATIC, Class.class , "getClassForNMSPacket");
                getClassForNMSPacketMethod.body()._return(JExpr.invoke(dc.staticRef("Packets"), "get").arg(getClassForNMSPacketMethod.param(Class.class, "clazz")));

                JMethod getClassForLibPacket = dc.method(JMod.PUBLIC | JMod.STATIC, Class.class , "getClassForLibPacket");
                getClassForLibPacket.body()._return(JExpr.invoke(dc.staticRef("Packets"), "inverse").invoke("get").arg(getClassForLibPacket.param(Class.class, "clazz")));

                packetMapping.forEach((key, value) -> dc.init().invoke(dc.staticRef("Packets"), "put").arg(JExpr.direct(key + ".class")).arg(JExpr.direct(value + ".class")));

            } catch (JClassAlreadyExistsException e) {
                e.printStackTrace();
            }

            cm.build(file, new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    //no pls no spam
                }
            }));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateClassFor(AsmClass packet){
        try {
            JDefinedClass dc = cm._class(PACKAGE_NAME + "." + packet.getName()
                    .replace("net.minecraft.network.", "")
                    .replace("net.minecraftforge.fml.common.network.internal", "forge")
            );
            generateClassFor(packet, dc, "com.thomas15v.packetlib.api.data.PacketContainer");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void generateClassFor(AsmClass packet, JDefinedClass dc, String extend){
        try {
            dc._extends(cm.directClass(extend.replace("$", ".")));

            packetMapping.put(packet.getName(), dc.fullName().replace("$", "."));


            JFieldVar packetContainerField = dc.field(JMod.PRIVATE, packetClass, "packet");
            JMethod constructor = dc.constructor(JMod.PUBLIC);
            constructor.body().invoke("super").arg(constructor.param(packetContainerField.type(), "packet"));

            packet.getFields().stream().forEach(packetField ->
                    spongeBinder.getGeneratorFor(new AsmType(jarFile, packetField, cm)).generate(dc, cm, packetField.name)
            );

            for (AsmClass innerClass : packet.getInnerClasses()) {
                if (innerClass.getNode().superName.equals(packet.getNode().name)){
                    generateClassFor(innerClass, dc._class(JMod.PUBLIC |JMod.STATIC, innerClass.getClassName()), dc.name() );
                }
                else if (packet.isAssignableFrom(extend)) {
                    generateClassFor(innerClass, dc._class(JMod.PUBLIC |JMod.STATIC, innerClass.getClassName()), extend );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
