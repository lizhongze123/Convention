package com.gz0101.hzwy.main.ui.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.base.BaseFragment;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.main.R;
import com.gz0101.hzwy.main.contract.MainContract;
import com.gz0101.hzwy.main.presenter.MainPresenterImpl;
import com.gz0101.hzwy.main.ui.fragment.HomeMainFragment;
import com.gz0101.hzwy.main.ui.fragment.OrderMainFragment;
import com.gz0101.hzwy.main.ui.fragment.SettingMainFragment;

import java.util.HashMap;

@Route(path = RouterConstance.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView {
    public static final String HOME_TAB = "home_tab";
    public static final String ORDER_TAB = "order_tab";
    public static final String SETTING_TAB = "setting_tab";

    RadioGroup radioGroup;

    String currentTab;

    private HashMap<String, BaseFragment> fragmentMap = new HashMap<>();

    @Override
    protected void layout() {
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_93BEFA));
    }

    @Override
    protected void onCreate() {
        initFragment();
        initView();
    }


    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void destroy() {

    }


    private void initView() {
        radioGroup = findViewById(R.id.rg_navigation);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_home_tab) {
                    switchFragment(HOME_TAB);
                } else if (checkedId == R.id.rb_order_tab) {
                    switchFragment(ORDER_TAB);
                } else if (checkedId == R.id.rb_setting_tab) {
                    switchFragment(SETTING_TAB);
                }
            }
        });
        switchFragment(HOME_TAB);
    }

    private void initFragment() {
        fragmentMap.put(HOME_TAB, new HomeMainFragment());
        fragmentMap.put(ORDER_TAB, new OrderMainFragment());
        fragmentMap.put(SETTING_TAB, new SettingMainFragment());
    }


    private void switchFragment(String tag) {
        if (tag.equals(currentTab))
            return;

        FragmentManager ftManager = getSupportFragmentManager();
        FragmentTransaction ftTransaction = ftManager.beginTransaction();

        BaseFragment currentFragment;
        BaseFragment targetFragment = fragmentMap.get(tag);
        if (TextUtils.isEmpty(currentTab)) {
            currentFragment = fragmentMap.get(ORDER_TAB);
            currentTab = HOME_TAB;
        } else {
            currentFragment = fragmentMap.get(currentTab);
            currentTab = tag;
        }
        if (!targetFragment.isAdded()) {
            ftTransaction.hide(currentFragment)
                    .add(R.id.fl_main_content, targetFragment, targetFragment.getTAG())
                    .show(targetFragment)
                    .commitAllowingStateLoss();
        } else {
            ftTransaction.hide(currentFragment)
                    .show(targetFragment)
                    .commitAllowingStateLoss();
        }
    }
}
