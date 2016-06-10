package com.thomas15v.packetlib.gradle;

import com.thomas15v.packetlib.codegenerator.CodeGenerator;
import net.minecraftforge.gradle.user.TaskRecompileMc;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.artifacts.ResolvedArtifact;
import org.gradle.api.tasks.TaskAction;
import org.gradle.logging.ProgressLogger;
import org.gradle.logging.ProgressLoggerFactory;

import java.io.File;

import static net.minecraftforge.gradle.user.UserConstants.TASK_RECOMPILE;

public class GeneratePacketsTask extends DefaultTask {

    @TaskAction
    public void doStuff(){
        try {
            File spongeJar = null;
            for (ResolvedArtifact resolvedArtifact : getProject().getConfigurations().getByName("compile").getResolvedConfiguration().getResolvedArtifacts()) {
                if (resolvedArtifact.getName().equals("spongeforge")){
                    spongeJar = resolvedArtifact.getFile();
                    break;
                }
            }
            File forgeJar = ((TaskRecompileMc)getProject().getTasks().getByName(TASK_RECOMPILE)).getOutJar();

            if (spongeJar == null || !spongeJar.exists()){
                throw new GradleException("The is no spongeforge dependency found");
            } else if (forgeJar == null || !forgeJar.exists()){
                throw new GradleException("The is no forge dependency found. (Please run setupdecompworkspace task)");
            }

            new CodeGenerator(forgeJar, spongeJar).generate(new File(getProject().getProjectDir(), "generated"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
