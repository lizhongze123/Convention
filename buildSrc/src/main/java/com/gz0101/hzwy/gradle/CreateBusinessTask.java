package com.gz0101.hzwy.gradle;
import java.io.File;
import java.util.List;

public class CreateBusinessTask extends CreateModuleTask {
    @Override
    public String getModulePath() {
        return  getProject().getProjectDir().getAbsolutePath()
                + File.separator + "business";
    }


    @Override
    public void create() {
        String root = getModulePath();
        List<String> list = getParentPath();
        StringBuilder buffer = new StringBuilder(root);
        if (list != null && list.size() > 0) {
            for (String path : list) {
                buffer.append(File.separator);
                buffer.append(path);
            }
        }
        root = buffer.toString() + File.separator + getModuleName();
        createDirectory(new File(root));
        createFile(makeFile(root, "build.gradle"), FileContent.BUILD_GRADLE);
        createFile(makeFile(root, ".gitignore"), FileContent.GIT_IGNORE);
        createFile(makeFile(root, "proguard-rules.pro"), FileContent.PROGUARD_RULES_PRO);
        createDirectory(makeFile(root, "src", "main"));
        root += File.separator + "src" + File.separator + "main";
        createDirectory(makeFile(root, "java"));
        createDirectory(makeFile(root, "res", "values"));
        createDirectory(makeFile(root, "module"));
        createFile(makeFile(root, "module", "AndroidManifest.xml"),
                FileContent.MODULE_FEST.replace("$PACKAGE", getPackageName()));
        createFile(makeFile(root, "AndroidManifest.xml"),
                FileContent.ANDROID_MANIFEST_XML.replace("$PACKAGE", getPackageName()));
        createFile(makeFile(root, "res", "values", "strings.xml"),
                FileContent.STRINGS_XML.replace("$NAME", getModuleName()));
        createDirectory(makeFile(root, "java",
                getPackageName().replace(".", File.separator)));
        createDirectory(makeFile(root, "java", "debug"));
        createFile(makeFile(root, "java", "debug", "MainApplication.java"),
                FileContent.APPLICATION);

        StringBuilder builder = new StringBuilder();
        List<String> parentPath = getParentPath();
        if (parentPath != null && parentPath.size() > 0) {
            for (String path : parentPath) {
                builder.append(path);
                builder.append('/');
            }
        }
        builder.append(getModuleName());
        String config = String.format("include_business(':%s', '%s')", getModuleName(), builder.toString());
        configGradleSetting(config);
    }
}
