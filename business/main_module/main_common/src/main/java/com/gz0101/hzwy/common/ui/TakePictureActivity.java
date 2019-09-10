package com.gz0101.hzwy.common.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.util.FileUtil;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.camera.JCameraView;
import com.gz0101.hzwy.camera.listener.JCameraListener;
import com.gz0101.hzwy.common.R;
import com.gz0101.hzwy.common.contract.TakePictureContract;
import com.gz0101.hzwy.common.presenter.TakePictureImpl;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import static com.gz0101.hzwy.baselibrary.util.FileUtil.PICS_DOWN_PATH;


@Route(path = RouterConstance.ACTIVITY_URL_TAKE_PICTURE)
public class TakePictureActivity extends BaseActivity<TakePictureImpl> implements TakePictureContract.TakeCameraView {


    private JCameraView jCameraView;

    private final int GET_PERMISSION_REQUEST = 100;
    public static String PIC_RESULT_CODE = "path";
    private int TAKE_PIC_RESULT = 104;
    private boolean granted = false;
    ArrayList<String> resultDatas = new ArrayList<>();


    @Override
    protected void layout() {
        StatusBarUtil.setTransparencyBar(this);
        setContentView(R.layout.activity_take_camrea);
    }

    @Override
    protected void onCreate() {
        jCameraView = findViewById(R.id.jcameraview);
        //JCameraView监听
        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                String path = "";
                String fileName = "cameraImg" + String.valueOf(System.currentTimeMillis()) + ".jpg";
                //获取图片发送家庭相册
                if (FileUtil.saveBitmap(bitmap, PICS_DOWN_PATH, fileName)) {
                    path = FileUtil.PICS_DOWN_PATH + fileName;
                }

                Intent intent = new Intent();
                if (resultDatas == null)
                    resultDatas = new ArrayList<>();
                if (resultDatas.size() > 0)
                    resultDatas.clear();
                resultDatas.add(path);
                intent.putStringArrayListExtra(PIC_RESULT_CODE, resultDatas);
                setResult(TAKE_PIC_RESULT, intent);
                finish();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {

            }

            @Override
            public void intentCrop(Bitmap bitmap) {

            }

            @Override
            public void choosePicture() {
                openGallery(PictureConfig.TYPE_IMAGE);
            }
        });

    }

    @Override
    protected TakePictureImpl createPresenter() {
        return null;
    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (granted) {
            jCameraView.onResume();
        }
    }

    private void openGallery(int type) {
        PictureSelector.create(TakePictureActivity.this)
                .openGallery(type)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(9)// 最大图片选择数量
                .maxVideoSelectNum(1)
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(false)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressWH(1280, 720)
                .compressGrade(Luban.CUSTOM_GEAR)
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .isGif(true)// 是否显示gif图片
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                //具有权限
                granted = true;
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(TakePictureActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
                granted = false;
            }
        } else {
            granted = true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    granted = true;
                    jCameraView.onResume();
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (resultDatas == null)
                    resultDatas = new ArrayList<>();
                if (resultDatas.size() > 0)
                    resultDatas.clear();
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                for (LocalMedia localMedia : selectList) {
                    resultDatas.add(localMedia.getPath());
                }
                data.putStringArrayListExtra("path", resultDatas);
                setResult(TAKE_PIC_RESULT, data);
                finish();
            }
        }
    }
}
