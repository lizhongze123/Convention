package com.gz0101.hzwy.gradle;
import org.gradle.api.Plugin;
import org.gradle.api.Project;


public class GzPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getTasks().create("newBusinessModule", CreateBusinessTask.class);
        project.getTasks().create("newDependenceModule", CreateDependenceTask.class);
    }
}
