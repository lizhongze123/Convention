package com.gz0101.hzwy.gradle;

final class FileContent {
    private FileContent() {
        throw new IllegalStateException();
    }

    static final String BUILD_GRADLE_LIBRARY = "apply plugin: 'com.android.library'\n" +
            "\n" +
            "android {\n" +
            "    compileSdkVersion rootProject.ext.android.compileSdkVersion\n" +
            "\n" +
            "\n" +
            "\n" +
            "    defaultConfig {\n" +
            "        minSdkVersion rootProject.ext.android.minSdkVersion\n" +
            "        targetSdkVersion rootProject.ext.android.targetSdkVersion\n" +
            "        versionCode rootProject.ext.android.versionCode\n" +
            "        versionName rootProject.ext.android.versionName\n" +
            "\n" +
            "        testInstrumentationRunner \"android.support.test.runner.AndroidJUnitRunner\"\n" +
            "\n" +
            "        javaCompileOptions {\n" +
            "            annotationProcessorOptions {\n" +
            "                includeCompileClasspath true\n" +
            "                arguments = [moduleName: \"base\"]\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    buildTypes {\n" +
            "        debug {\n" +
            "            resValue(\"string\", \"version_code\", \"${VERSION_CODE}\")\n" +
            "            resValue(\"string\", \"version_name\", \"${VERSION_NAME}\")\n" +
            "            resValue(\"string\", \"isModule\", \"${isModule.toBoolean()}\")\n" +
            "        }\n" +
            "        release {\n" +
            "            resValue(\"string\", \"version_code\", \"${VERSION_CODE}\")\n" +
            "            resValue(\"string\", \"version_name\", \"${VERSION_NAME}\")\n" +
            "            resValue(\"string\", \"isModule\", \"${isModule.toBoolean()}\")\n" +
            "            minifyEnabled false\n" +
            "            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}\n" +
            "dependencies {\n" +
            "    implementation fileTree(dir: 'libs', include: ['*.jar'])\n" +
            "}";


    static final String BUILD_GRADLE =
            "if (isModule.toBoolean()) {\n" +
            "    apply plugin: 'com.android.application'\n" +
            "} else {\n" +
            "    apply plugin: 'com.android.library'\n" +
            "}\n" +
            "\n" +
            "android {\n" +
            "    compileSdkVersion rootProject.ext.android.compileSdkVersion\n" +
            "\n" +
            "    defaultConfig {\n" +
            "        if (isModule.toBoolean()) {\n" +
            "            applicationId \"com.gz0101.hzwy.login\"\n" +
            "        }\n" +
            "        minSdkVersion rootProject.ext.android.minSdkVersion\n" +
            "        targetSdkVersion rootProject.ext.android.targetSdkVersion\n" +
            "        versionCode rootProject.ext.android.versionCode\n" +
            "        versionName rootProject.ext.android.versionName\n" +
            "\n" +
            "        testInstrumentationRunner \"android.support.test.runner.AndroidJUnitRunner\"\n" +
            "\n" +
            "        javaCompileOptions {\n" +
            "            annotationProcessorOptions {\n" +
            "                includeCompileClasspath true\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    buildTypes {\n" +
            "        debug {\n" +
            "            resValue(\"string\", \"isModule\", \"${isModule.toBoolean()}\")\n" +
            "        }\n" +
            "        release {\n" +
            "            resValue(\"string\", \"isModule\", \"${isModule.toBoolean()}\")\n" +
            "            minifyEnabled false\n" +
            "            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'\n" +
            "        }\n" +
            "    }\n" +
            "    sourceSets {\n" +
            "        main {\n" +
            "            if (isModule.toBoolean()) {\n" +
            "                manifest.srcFile 'src/main/module/AndroidManifest.xml'\n" +
            "            } else {\n" +
            "                manifest.srcFile 'src/main/AndroidManifest.xml'\n" +
            "                java {\n" +
            "                    exclude 'debug/**'\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}\n" +
            "\n" +
            "repositories {\n" +
            "    flatDir {\n" +
            "        dirs 'libs'\n" +
            "    }\n" +
            "    maven {\n" +
            "        url 'https://maven.google.com/'\n" +
            "        name 'Google'\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "dependencies {\n" +
            "    implementation fileTree(include: ['*.jar'], dir: 'libs')\n" +
            "    implementation 'com.android.support.constraint:constraint-layout:1.1.3'\n" +
            "    testImplementation 'junit:junit:4.12'\n" +
            "    androidTestImplementation 'com.android.support.test:runner:1.0.2'\n" +
            "    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'\n" +
            "    implementation rootProject.ext.dependencies.baselibrary\n" +
            "}";

    static final String PROGUARD_RULES_PRO = "# Add project specific ProGuard rules here.\n"
            + "# You can control the set of applied configuration files using the\n"
            + "# proguardFiles setting in build.gradle.\n"
            + "#\n"
            + "# For more details, see\n"
            + "#   http://developer.android.com/guide/developing/tools/proguard.html\n"
            + "\n"
            + "# If your project uses WebView with JS, uncomment the following\n"
            + "# and specify the fully qualified class name to the JavaScript interface\n"
            + "# class:\n"
            + "#-keepclassmembers class fqcn.of.javascript.interface.for.webview {\n"
            + "#   public *;\n"
            + "#}\n"
            + "\n"
            + "# Uncomment this to preserve the line number information for\n"
            + "# debugging stack traces.\n"
            + "#-keepattributes SourceFile,LineNumberTable\n"
            + "\n"
            + "# If you keep the line number information, uncomment this to\n"
            + "# hide the original source file name.\n"
            + "#-renamesourcefileattribute SourceFile\n";

    static final String ANDROID_MANIFEST_XML = "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n"
            + "          package=\"$PACKAGE\"/>\n";

    static final String STRINGS_XML = "<resources>\n"
            + "    <string name=\"module_name\">$NAME</string>\n"
            + "</resources>\n";
    static final String GIT_IGNORE = "/build";
    static final String MODULE_FEST = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
            "    package=\"$PACKAGE\">\n" +
            "\n" +
            "    <application\n" +
            "        android:name=\"debug.MainApplication\"\n" +
            "        android:allowBackup=\"true\"/>\n" +
            "    \n" +
            "</manifest>";
    static final String APPLICATION = "package debug;\n" +
            "\n" +
            "\n" +
            "import com.gz0101.hzwy.baselibrary.base.BaseApplication;\n" +
            "\n" +
            "public class MainApplication extends BaseApplication {\n" +
            "\n" +
            "    @Override\n" +
            "    public void onCreate() {\n" +
            "        super.onCreate();\n" +
            "    }\n" +
            "\n" +
            "}";
}