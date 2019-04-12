package cn.wildfire.chat.app.main;


import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.List;

import cn.wildfire.chat.app.bean.JuHeNewsData;
import cn.wildfirechat.chat.R;

public class JuHeNewsPresenter extends JuHeNewsContract.Presenter{


    @Override
    public void getNewsListDataRequest(String type, String appkey) {
        mRxManage.add(mModel.getNewsListData(type, appkey).subscribe(new RxSubscriber<List<JuHeNewsData.ResultData.JuheNewsBean>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }

            @Override
            protected void _onNext(List<JuHeNewsData.ResultData.JuheNewsBean> newsSummaryBeans) {
                mView.returnNewsListData(newsSummaryBeans);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
