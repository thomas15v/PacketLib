package com.thomas15v.packetlib.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class GeneratorPlugin implements Plugin<Project> {

    public void apply(Project project) {
        System.out.println("### Let's generate some code ... ###");
        project.afterEvaluate(p -> {
            Task task = project.getTasks().create("generatePackets", GeneratePacketsTask.class);
            project.getTasks().getByName("compileJava").dependsOn(task);
        });
    }

}
