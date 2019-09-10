package com.gz0101.hzwy.camera.listener;

/**
 * create by CJT2325
 * 445263848@qq.com.
 */

public interface CaptureListener {
    void takePictures();

    void recordShort(long time);

    void recordStart();

    void recordEnd(long time);

    void recordZoom(float zoom);

    void recordError();

    void previewPicture();


}
