package com.gz0101.hzwy.common.presenter;

import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.baselibrary.request.respone.ResponseCode;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.common.R;
import com.gz0101.hzwy.common.bean.AddressListBean;
import com.gz0101.hzwy.common.contract.AddressListContract;
import com.gz0101.hzwy.common.model.AddressListModel;

import java.lang.ref.SoftReference;
import java.util.List;

public class AddressListPresenterImpl implements AddressListContract.AddressListPresenter {

    private SoftReference<AddressListContract.AddressListView> mView;

    private int curPage = 1;

    @Override
    public void attachView(AddressListContract.AddressListView view) {
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
    public void getData() {
        if (mView != null & mView.get() != null)
            mView.get().showLoading();
        AddressListModel.getInstance().getData(new ICallBack<BaseRespone<List<AddressListBean>>>() {
            @Override
            public void onSuccess(BaseRespone<List<AddressListBean>> listBaseRespone) {
                if (mView != null && mView.get() != null)
                    mView.get().dismissLoading();
                if (listBaseRespone.getCode() == ResponseCode.SUCCESS) {
                    if (listBaseRespone.getData() != null) {
                        if (mView != null && mView.get() != null)
                            mView.get().showData(listBaseRespone.getData());
                    } else {
                        if (mView != null && mView.get() != null)
                            mView.get().showErrorPage();
                    }
                } else {
                    if (mView != null && mView.get() != null)
                        mView.get().showErrorPage();
                }
            }

            @Override
            public void onFailure(String str) {
                ToastHelp.showShort(R.string.base_net_error);
                if (mView != null && mView.get() != null) {
                    mView.get().dismissLoading();
                    mView.get().showErrorPage();
                }
            }
        });
    }
}

