package cn.wildfire.chat.app.main;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.List;

import cn.wildfire.chat.app.bean.JuHeNewsData;
import rx.Observable;

public interface JuHeNewsContract {
    interface Model extends BaseModel {
        //请求获取资讯
        Observable<List<JuHeNewsData.ResultData.JuheNewsBean>> getNewsListData(String type, String appkey);
    }

    interface View extends BaseView {
        //返回获取的资讯
        void returnNewsListData(List<JuHeNewsData.ResultData.JuheNewsBean> newsSummaries);
    }
    abstract class Presenter extends BasePresenter<View, Model> {
        //发起获取资讯请求
        public abstract void getNewsListDataRequest(String type, String appkey);
    }
}
