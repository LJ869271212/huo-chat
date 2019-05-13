package cn.wildfire.chat.app.main;


import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingTip;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Bind;
import cn.wildfire.chat.app.bean.JuHeNewsData;
import cn.wildfirechat.chat.R;

/**
 * 发现页（新闻资讯界面）
 */
public class ChildNewsFragment extends BaseFragment<JuHeNewsPresenter, JuHeNewsListModel> implements JuHeNewsContract.View, OnRefreshListener, OnLoadmoreListener {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
//    @Bind(R.id.loadingtip)
//    LoadingTip loadingtip;
    @Bind(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    private JuHeNewListAdapter adapter;
    private String newsType;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_child_news;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            newsType = getArguments().getString(AppConstant.NEWS_TYPE);
        }
        newsType = "top";
        smartrefreshlayout.setOnLoadmoreListener(this);
        smartrefreshlayout.setOnRefreshListener(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new JuHeNewListAdapter(getContext(),R.layout.item_juhe_news_photo);
        recyclerview.setAdapter(adapter);

        mPresenter.getNewsListDataRequest(newsType,AppConstant.JUHE_NEWS_KEY);
    }

    @Override
    public void returnNewsListData(List<JuHeNewsData.ResultData.JuheNewsBean> newsSummaries) {
        if (newsSummaries != null) {
            if (adapter.getPageBean().isRefresh()) {
                smartrefreshlayout.finishRefresh();
                adapter.replaceAll(newsSummaries);
            } else {
                if (newsSummaries.size() > 0) {
                    smartrefreshlayout.finishLoadmore();
                    adapter.addAll(newsSummaries);
                } else {
                    smartrefreshlayout.finishLoadmore();
                    ToastUitl.showShort(getContext(),getString(R.string.str_end));
                }
            }
        }
    }

    @Override
    public void showLoading(String title) {
        if( adapter.getPageBean().isRefresh()) {
            if(adapter.getSize()<=0) {
//                loadingtip.setLoadingTip(LoadingTip.LoadStatus.loading);
            }
        }
    }

    @Override
    public void stopLoading() {
        if(adapter.getSize()==0){
//            loadingtip.setLoadingTip(LoadingTip.LoadStatus.empty);
        }else{
//            loadingtip.setLoadingTip(LoadingTip.LoadStatus.finish);
        }
    }

    @Override
    public void showErrorTip(String msg) {
        if( adapter.getPageBean().isRefresh()) {
//            loadingtip.setLoadingTip(LoadingTip.LoadStatus.error);
//            loadingtip.setTips(msg);
            adapter.clear();
            smartrefreshlayout.finishRefresh(false);
        }else{
            smartrefreshlayout.finishLoadmore(false);
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        adapter.getPageBean().setRefresh(false);
        //没有分页
        smartrefreshlayout.finishLoadmore();
        ToastUitl.showShort(getContext(),getString(R.string.str_end));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        adapter.getPageBean().setRefresh(true);
        //发起请求
        mPresenter.getNewsListDataRequest(newsType,AppConstant.JUHE_NEWS_KEY);
    }

}
