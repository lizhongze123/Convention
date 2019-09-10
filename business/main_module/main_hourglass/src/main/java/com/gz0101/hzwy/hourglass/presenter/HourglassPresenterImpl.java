package com.gz0101.hzwy.hourglass.presenter;

import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.PageRespone;
import com.gz0101.hzwy.baselibrary.request.respone.ResponseCode;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.contract.HourglassContract;
import com.gz0101.hzwy.hourglass.model.HourglassModel;


import java.lang.ref.SoftReference;
import java.util.List;

public class HourglassPresenterImpl implements HourglassContract.HourglassPresenter {

    private SoftReference<HourglassContract.HourglassView> mView;

    private int curPage = 1;

    @Override
    public void attachView(HourglassContract.HourglassView view) {
        mView = new SoftReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public void loadData(final boolean isMore, final boolean isRefresh) {
        if (mView != null && mView.get() != null && !isRefresh && !isMore) {
            mView.get().showLoading();
        }
        if (isMore) {
            curPage++;
        }
        if (isRefresh)
            curPage = 1;

        HourglassModel.getInstance().getData(curPage, new ICallBack<PageRespone<List<ShopListBean>>>() {
            @Override
            public void onSuccess(PageRespone<List<ShopListBean>> shopListBeanPageRespone) {
                if (mView != null && mView.get() != null && !isRefresh && !isMore) {
                    mView.get().dismissLoading();
                }
                if (shopListBeanPageRespone.getCode() == ResponseCode.SUCCESS) {
                    if (shopListBeanPageRespone.getData() != null) {
                        if (mView != null && mView.get() != null) {
                            mView.get().showData(shopListBeanPageRespone.getData(), isMore);
                        }
                    } else {
                        if (mView != null && mView.get() != null && !isRefresh && !isMore) {
                            mView.get().showErrorPage(true);
                        }
                    }
                } else {
                    if (mView != null && mView.get() != null && !isRefresh && !isMore) {
                        mView.get().showErrorPage(true);
                    }
                }
            }

            @Override
            public void onFailure(String str) {
                netError(isRefresh | isMore);
            }
        });
    }

    private void netError(boolean isRefresh) {
        if (mView != null && mView.get() != null && !isRefresh) {
            mView.get().dismissLoading();
            mView.get().showErrorPage(false);
            ToastHelp.showShort(R.string.base_net_error);
        }
        if (mView != null && mView.get() != null && isRefresh) {
            mView.get().stopRefresh();
        }
    }

}

