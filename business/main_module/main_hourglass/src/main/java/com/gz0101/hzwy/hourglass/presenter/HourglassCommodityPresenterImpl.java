package com.gz0101.hzwy.hourglass.presenter;

import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.PageRespone;
import com.gz0101.hzwy.baselibrary.request.respone.ResponseCode;
import com.gz0101.hzwy.baselibrary.request.respone.bean.GoodsListBean;
import com.gz0101.hzwy.hourglass.contract.HourglassCommodityContract;
import com.gz0101.hzwy.hourglass.model.HourglassCommodityModel;

import java.lang.ref.SoftReference;
import java.util.List;

public class HourglassCommodityPresenterImpl implements HourglassCommodityContract.HourglassCommodityPresenter {

    private SoftReference<HourglassCommodityContract.HourglassCommodityView> mView;

    private int curPage = 1;

    @Override
    public void attachView(HourglassCommodityContract.HourglassCommodityView view) {
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
    public void loadData(int shopId) {
        if (mView != null && mView.get() != null)
            mView.get().showLoading();

        HourglassCommodityModel.getInstance().getData(shopId, new ICallBack<PageRespone<List<GoodsListBean>>>() {
            @Override
            public void onSuccess(PageRespone<List<GoodsListBean>> listPageRespone) {
                if (mView != null && mView.get() != null)
                    mView.get().dismissLoading();
                if (listPageRespone.getCode() == ResponseCode.SUCCESS && listPageRespone.getData() != null) {
                    if (mView != null && mView.get() != null)
                        mView.get().showData(listPageRespone.getData());
                }
            }

            @Override
            public void onFailure(String str) {
                if (mView != null && mView.get() != null)
                    mView.get().dismissLoading();
            }
        });
    }


}

