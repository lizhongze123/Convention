package com.gz0101.hzwy.baselibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
    public static final String SEPARATOR = File.separator;
    public static final String SD_PATH = Environment
            .getExternalStorageDirectory() + SEPARATOR + "sdcard/";
    // 根路径(SDCARD)
    public static final String ROOT_PATH = SD_PATH + "hywy/";
    // 临时文件夹
    public static final String TEMP_PATH = ROOT_PATH + "Temp/";
    // 下载路径
    public static final String DOWNLOAD_PATH = ROOT_PATH + "download/";
    public static final String CACHE_PATH = ROOT_PATH + "cache/";
    public static final String PICS_DOWN_PATH = ROOT_PATH + "images/";
    public static final String VIDEO_PATH = ROOT_PATH + "videos/";
    public static List<String> usbPaths = new ArrayList<String>();

    public static String LOCAL_Path = null;
    public static String archiLocalDir = null;
    public static String archiSDCardDir = null;

    /**
     * 判断路径是否为根路径(本系统自定义�?��根路径architectureLoaclDir
     *
     * @param path 路径�?
     * @return 返回真如果是根路�?
     */
    public static boolean isRootPath(String path) {
        path = new File(path).getParent() + SEPARATOR;
        return (path.equals(archiLocalDir) || path.equals(archiSDCardDir)) ? true
                : false;
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();

    }


    public static String getTempPath() {
        return TEMP_PATH;
    }

    /**
     * 根据URL的到文件名
     *
     * @param url
     * @return
     */
    public static String getFileFromUrl(String url) {
        if (url != null) {
            String[] strs = url.split("/");
            if (strs.length > 0) {
                return strs[strs.length - 1];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     *
     */
    public static void init() {
        if (LOCAL_Path != null) {
        }
    }


    /**
     * 判断是否存在SDCard存储�?
     *
     * @return
     */
    public static boolean isExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_REMOVED)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将xml字符串写入xml文件
     *
     * @param context
     * @param str
     * @return
     */
    public static boolean writeToXml(Context context, String path, String str) {
        try {
            // OutputStream out=context.openFileOutput(path,
            // Context.MODE_PRIVATE);
            OutputStream out = new FileOutputStream(path, false);
            OutputStreamWriter outw = new OutputStreamWriter(out);
            try {
                outw.write(str);
                outw.close();
                out.close();
                return true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                return false;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

    public static boolean isFlashAvailable() {
        return new File(LOCAL_Path).exists();
    }

    /**
     * 删除文件
     *
     * @param f
     * @return
     */
    public static boolean deleteFile(File f) {
        if (f.isFile())
            f.delete();
        return true;
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    public static void deleteDir(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                // file.delete();
                return;
            }
            for (File f : childFile) {
                deleteDir(f);
            }
            // file.delete();
        }
    }

    public static boolean isXML(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith("xml");
    }

    public static boolean isPng(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith("png");
    }

    /**
     * 得到文件的扩展名.xxx
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot);
            }
        }
        return filename;
    }

    public static boolean saveBitmap(Bitmap bitmap, String filepath, String filename) {
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            if (!file.isDirectory()) {
                file.delete();
                file.mkdirs();
            }
        }
        try {
            File imageFile = new File(file, filename);
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveBitmapPng(Bitmap bitmap, String filepath, String filename) {
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            if (!file.isDirectory()) {
                file.delete();
                file.mkdirs();
            }
        }
        try {
            File imageFile = new File(file, filename);
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取sd卡剩余空间 返回KB
     *
     * @return
     */
    public static long getSDCardAvailSpace() {
        String state = Environment.getExternalStorageState();
        long availCount = 0;
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long blockSize = sf.getBlockSize();
            availCount = sf.getAvailableBlocks();
            availCount = availCount * blockSize / 1024;

        }
        return availCount;
    }

    public static String encode(String url) {
        try {
            Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]").matcher(url);
            while (matcher.find()) {
                String tmp = matcher.group();
                url = url.replaceAll(tmp, java.net.URLEncoder.encode(tmp, "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static byte[] readBytes(InputStream in) throws IOException {
        BufferedInputStream bufin = new BufferedInputStream(in);
        int buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);

        // System.out.println("Available bytes:" + in.available());

        byte[] temp = new byte[buffSize];
        int size = 0;
        while ((size = bufin.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        bufin.close();

        byte[] content = out.toByteArray();
        return content;
    }
}
