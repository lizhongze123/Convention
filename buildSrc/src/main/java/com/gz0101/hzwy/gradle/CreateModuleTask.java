package com.gz0101.hzwy.gradle;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by changzhiwei on 2019/7/6
 */
public abstract class CreateModuleTask extends DefaultTask {
    public abstract String getModulePath();

    private String moduleName;

    private String packageName;

    private List<String> parentPath;

    @Input
    public String getModuleName() {
        return moduleName;
    }

    @Option(option = "name", description = "ModuleName: login")
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Input
    public String getPackageName() {
        return packageName;
    }

    @Option(option = "package", description = "PackageName:  com.gz0101.hzwy")
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getParentPath() {
        return parentPath;
    }
    @Option(option = "path", description = "ParentPath: (business)/main/")
    public void setParentPath(List<String> parentPath) {
        this.parentPath = parentPath;
    }
    @TaskAction
    public abstract void create();

    protected void configGradleSetting(String config) {
        String rootPath = getProject().getProjectDir().getAbsolutePath();
        appendFile(makeFile(rootPath, "settings.gradle"), "\n" + config);
    }

    static File makeFile(String... paths) {
        StringBuilder sb = new StringBuilder();
        for (String p : paths) {
            sb.append(p).append(File.separator);
        }
        return new File(sb.toString());
    }

    static void createDirectory(File file) {
        if (file.exists()) {
            logCreateFileFailed(file.getPath(), " is exists");
        } else {
            boolean newDir = file.mkdirs();
            if (newDir) {
                logCreateFileSuccess(file.getPath());
            } else {
                logCreateFileFailed(file.getPath(), " unknown");
            }
        }
    }

    static void createFile(File file, String content) {
        if (file.exists()) {
            logCreateFileFailed(file.getPath(), " is exists");
            return;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file, false);
            byte[] buf = content.getBytes();
            int len = buf.length;
            outputStream.write(buf, 0, len);
            outputStream.close();
            logCreateFileSuccess(file.getPath());
        } catch (Exception e) {
            logCreateFileFailed(file.getPath(), e.getMessage());
        }
    }

    static void appendFile(File file, String content) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            byte[] buf = content.getBytes();
            int len = buf.length;
            outputStream.write(buf, 0, len);
            outputStream.close();
            logUpdateFileSuccess(file.getPath());
        } catch (Exception e) {
            logUpdateFileFailed(file.getPath(), e.getMessage());
        }
    }

    private static void logCreateFileSuccess(String filePath) {
        logNormal("success: create file" + filePath);
    }

    private static void logCreateFileFailed(String filePath, String msg) {
        logError("failed: create file" + filePath + " reason: " + msg);
    }

    private static void logUpdateFileSuccess(String filePath) {
        logNormal("success: update file " + filePath);
    }

    private static void logUpdateFileFailed(String filePath, String msg) {
        logError("failed: update file" + filePath + " reason: " + msg);
    }

    static void logNormal(String msg) {
        System.out.println(msg);
    }

    static void logError(String msg) {
        System.err.println(msg);
    }
}