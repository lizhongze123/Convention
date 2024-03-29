package com.gz0101.hzwy.camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz0101.hzwy.baselibrary.util.UnitUtil;
import com.gz0101.hzwy.camera.listener.CaptureListener;
import com.gz0101.hzwy.camera.listener.ClickListener;
import com.gz0101.hzwy.camera.listener.JCameraListener;
import com.gz0101.hzwy.camera.listener.ReturnListener;
import com.gz0101.hzwy.camera.listener.TypeListener;


/**
 * =====================================
 * 作    者: 陈嘉桐 445263848@qq.com
 * 版    本：1.0.4
 * 创建日期：2017/4/26
 * 描    述：集成各个控件的布局
 * =====================================
 */

public class CaptureLayout extends FrameLayout {
    private JCameraListener cameraListener;
    private CaptureListener captureLisenter;    //拍照按钮监听
    private TypeListener typeLisenter;          //拍照或录制后接结果按钮监听
    private ReturnListener returnListener;      //退出按钮监听
    private ClickListener leftClickListener;    //左边按钮监听
    private ClickListener rightClickListener;   //右边按钮监听

    public void setJCameraLisenter(JCameraListener cameraListener) {
        this.cameraListener = cameraListener;
    }

    public void setTypeLisenter(TypeListener typeLisenter) {
        this.typeLisenter = typeLisenter;
    }

    public void setCaptureLisenter(CaptureListener captureLisenter) {
        this.captureLisenter = captureLisenter;
    }

    public void setReturnLisenter(ReturnListener returnListener) {
        this.returnListener = returnListener;
    }

    private CaptureButton btn_capture;      //拍照按钮
    private ImageView btn_capture_cancel;          //拍照取消按钮
    private ImageView btn_confirm;         //确认按钮
    private ImageView btn_review;         //确认按钮
    private ImageView btn_cancel;          //取消按钮
    private ImageView btn_return;        //返回按钮
    //    private ImageView iv_custom_left;            //左边自定义按钮
//    private ImageView iv_custom_right;            //右边自定义按钮
    private TextView txt_tip;               //提示文本

    private int layout_width;
    private int layout_height;
    private int button_size;
    private int iconLeft = 0;
    private int iconRight = 0;

    private boolean isFirst = true;

    public CaptureLayout(Context context) {
        this(context, null);
    }

    public CaptureLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CaptureLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layout_width = outMetrics.widthPixels;
        } else {
            layout_width = outMetrics.widthPixels / 2;
        }
//        button_size = (int) (layout_width / 4.5f);
        button_size = (int) UnitUtil.dp2px(67);
        layout_height = button_size + (button_size / 5) * 2 + 100;

        initView();
        initEvent();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(layout_width, layout_height);
    }

    public void initEvent() {
        //默认Typebutton为隐藏
        //   iv_custom_right.setVisibility(GONE);
        btn_cancel.setVisibility(GONE);
        btn_confirm.setVisibility(GONE);
        btn_review.setVisibility(GONE);
    }

    public void startTypeBtnAnimator() {
        //拍照录制结果后的动画
        if (this.iconLeft != 0) {
        }
        //     iv_custom_left.setVisibility(GONE);
        else
            btn_return.setVisibility(GONE);
        if (this.iconRight != 0) {
        }
        // iv_custom_right.setVisibility(GONE);
        btn_capture.setVisibility(GONE);
        btn_capture_cancel.setVisibility(GONE);
        btn_cancel.setVisibility(VISIBLE);
        btn_confirm.setVisibility(VISIBLE);
        btn_review.setVisibility(VISIBLE);
        btn_cancel.setClickable(false);
        btn_confirm.setClickable(false);
        btn_review.setClickable(false);
        ObjectAnimator animator_cancel = ObjectAnimator.ofFloat(btn_cancel, "translationX", layout_width / 4, 0);
        ObjectAnimator animator_confirm = ObjectAnimator.ofFloat(btn_confirm, "translationX", -layout_width / 4, 0);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator_cancel, animator_confirm);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btn_cancel.setClickable(true);
                btn_confirm.setClickable(true);
                btn_review.setClickable(true);
            }
        });
        set.setDuration(200);
        set.start();
    }


    private void initView() {
        setWillNotDraw(false);
        //拍照按钮
        btn_capture = new CaptureButton(getContext(), button_size);
        LayoutParams btn_capture_param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        btn_capture_param.gravity = Gravity.CENTER;
        btn_capture.setLayoutParams(btn_capture_param);
        btn_capture.setCaptureLisenter(new CaptureListener() {
            @Override
            public void takePictures() {
                if (captureLisenter != null) {
                    captureLisenter.takePictures();
                }
            }

            @Override
            public void recordShort(long time) {
                if (captureLisenter != null) {
                    captureLisenter.recordShort(time);
                }
                startAlphaAnimation();
            }

            @Override
            public void recordStart() {
                if (captureLisenter != null) {
                    captureLisenter.recordStart();
                }
                startAlphaAnimation();
            }

            @Override
            public void recordEnd(long time) {
                if (captureLisenter != null) {
                    captureLisenter.recordEnd(time);
                }
                startAlphaAnimation();
                startTypeBtnAnimator();
            }

            @Override
            public void recordZoom(float zoom) {
                if (captureLisenter != null) {
                    captureLisenter.recordZoom(zoom);
                }
            }

            @Override
            public void recordError() {
                if (captureLisenter != null) {
                    captureLisenter.recordError();
                }
            }

            @Override
            public void previewPicture() {

            }
        });
        btn_capture_cancel = new ImageView(getContext());
        btn_capture_cancel.setImageResource(R.mipmap.photo_camera_back);
        final LayoutParams btn_capture_cancel_param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        btn_capture_cancel_param.gravity = Gravity.CENTER_VERTICAL;
        btn_capture_cancel.setPadding(20, 20, 20, 20);
        btn_capture_cancel_param.setMargins((layout_width / 4) - button_size / 2 - 40, 0, 0, 0);
        btn_capture_cancel.setLayoutParams(btn_capture_cancel_param);
        btn_capture_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });

        //取消按钮
        btn_cancel = new ImageView(getContext());
        btn_cancel.setImageResource(R.mipmap.photo_select_photo);
        final LayoutParams btn_cancel_param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        btn_cancel_param.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
//        btn_cancel_param.setMargins((layout_width / 4) - button_size / 2, 0, 0, 0);
        btn_cancel_param.setMargins(button_size / 2, 0, 0, 0);
        btn_cancel.setLayoutParams(btn_cancel_param);
        btn_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typeLisenter != null) {
                    typeLisenter.cancel();
                }
                startAlphaAnimation();
//                resetCaptureLayout();
            }
        });

        //确认按钮
        btn_confirm = new ImageView(getContext());
        btn_confirm.setImageResource(R.mipmap.photo_select_confirm);
        LayoutParams btn_confirm_param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        btn_confirm_param.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        btn_confirm_param.setMargins(0, 0, button_size / 2, 0);
        btn_confirm.setLayoutParams(btn_confirm_param);
        btn_confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (typeLisenter != null) {
//                    typeLisenter.confirm();
//                }
//                startAlphaAnimation();
////                resetCaptureLayout();

                if (rightClickListener != null)
                    rightClickListener.onClick();

                if (typeLisenter != null) {
                    typeLisenter.cancel();
                }
                startAlphaAnimation();

            }
        });

        //查看按钮
        btn_review = new ImageView(getContext());
        btn_review.setImageResource(R.mipmap.photo_select_review);
        LayoutParams btn_review_param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        btn_review_param.gravity = Gravity.CENTER;
//        btn_review_param.setMargins(0, 0, (layout_width / 2) - button_size / 2, 0);
        btn_review_param.setMargins(0, 0, 0, 0);
//        btn_review_param.setMargins(0, 0, 0, 0);
        btn_review.setLayoutParams(btn_review_param);
        btn_review.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                captureLisenter.previewPicture();
            }
        });

        //返回按钮
        btn_return = new ImageView(getContext());
        btn_return.setImageResource(R.mipmap.photo_select_photo);
        LayoutParams btn_return_param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        btn_return_param.gravity = Gravity.CENTER_VERTICAL;
        btn_return_param.setMargins(layout_width / 6, 0, 0, 0);
        btn_return.setLayoutParams(btn_return_param);
        btn_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftClickListener != null) {
                    leftClickListener.onClick();
                }
            }
        });

//        //左边自定义按钮
//        iv_custom_left = new ImageView(getContext());
//        LayoutParams iv_custom_param_left = new LayoutParams((int) (button_size / 2.5f), (int) (button_size / 2.5f));
//        iv_custom_param_left.gravity = Gravity.CENTER_VERTICAL;
//        iv_custom_param_left.setMargins(layout_width / 6, 0, 0, 0);
//        iv_custom_left.setLayoutParams(iv_custom_param_left);
//        iv_custom_left.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (leftClickListener != null) {
//                    leftClickListener.onClick();
//                }
//            }
//        });

//        //右边自定义按钮
//        iv_custom_right = new ImageView(getContext());
//        LayoutParams iv_custom_param_right = new LayoutParams((int) (button_size / 2.5f), (int) (button_size / 2.5f));
//        iv_custom_param_right.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
//        iv_custom_param_right.setMargins(0, 0, layout_width / 6, 0);
//        iv_custom_right.setLayoutParams(iv_custom_param_right);
//        iv_custom_right.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (rightClickListener != null) {
//                    rightClickListener.onClick();
//                }
//            }
//        });

        txt_tip = new TextView(getContext());
        LayoutParams txt_param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        txt_param.gravity = Gravity.CENTER_HORIZONTAL;
        txt_param.setMargins(0, 0, 0, 0);
        txt_tip.setText("轻触拍照");
        txt_tip.setTextColor(0xFFFFFFFF);
        txt_tip.setGravity(Gravity.CENTER);
        txt_tip.setLayoutParams(txt_param);

        this.addView(btn_capture);
        this.addView(btn_capture_cancel);
        this.addView(btn_cancel);
        this.addView(btn_confirm);
        this.addView(btn_review);
        //     this.addView(btn_return);
//        this.addView(iv_custom_left);
//        this.addView(iv_custom_right);
        this.addView(txt_tip);

    }

    /**************************************************
     * 对外提供的API                      *
     **************************************************/
    public void resetCaptureLayout() {
        btn_capture.resetState();
        btn_cancel.setVisibility(GONE);
        btn_confirm.setVisibility(GONE);
        btn_review.setVisibility(GONE);
        btn_capture.setVisibility(VISIBLE);
        btn_capture_cancel.setVisibility(VISIBLE);
        if (this.iconLeft != 0) {
        }
        //  iv_custom_left.setVisibility(VISIBLE);
        else
            btn_return.setVisibility(VISIBLE);
        if (this.iconRight != 0) {
        }
        // iv_custom_right.setVisibility(VISIBLE);
    }


    public void startAlphaAnimation() {
        if (isFirst) {
            ObjectAnimator animator_txt_tip = ObjectAnimator.ofFloat(txt_tip, "alpha", 1f, 0f);
            animator_txt_tip.setDuration(500);
            animator_txt_tip.start();
            isFirst = false;
        }
    }

    public void setTextWithAnimation(String tip) {
        txt_tip.setText(tip);
        ObjectAnimator animator_txt_tip = ObjectAnimator.ofFloat(txt_tip, "alpha", 0f, 1f, 1f, 0f);
        animator_txt_tip.setDuration(2500);
        animator_txt_tip.start();
    }

    public void setDuration(int duration) {
        btn_capture.setDuration(duration);
    }

    public void setButtonFeatures(int state) {
        btn_capture.setButtonFeatures(state);
    }

    public void setTip(String tip) {
        txt_tip.setText(tip);
    }

    public void showTip() {
        txt_tip.setVisibility(VISIBLE);
    }

    public void setIconSrc(int iconLeft, int iconRight) {
        this.iconLeft = iconLeft;
        this.iconRight = iconRight;
        if (this.iconLeft != 0) {
//            iv_custom_left.setImageResource(iconLeft);
//            iv_custom_left.setVisibility(VISIBLE);
            btn_return.setVisibility(GONE);
        } else {
//            iv_custom_left.setVisibility(GONE);
            btn_return.setVisibility(VISIBLE);
        }
        if (this.iconRight != 0) {
//            iv_custom_right.setImageResource(iconRight);
//            iv_custom_right.setVisibility(VISIBLE);
        } else {
//            iv_custom_right.setVisibility(GONE);
        }
    }

    public void setLeftClickListener(ClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    public void setRightClickListener(ClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }
}
