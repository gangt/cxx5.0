package com.xi6666.store;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.xi6666.R;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.common.ShareUtils;
import com.xi6666.order.other.Utils;
import com.xi6666.store.adapter.TechnicianTeamAdapter;
import com.xi6666.store.mvp.StoreDetailsContract;
import com.xi6666.store.mvp.StoreDetailsModel;
import com.xi6666.store.mvp.StoreDetailsPresenter;
import com.xi6666.store.mvp.bean.TechnicianTeamBean;
import com.xi6666.store.custom.MonitorScrollView;
import com.xi6666.store.custom.StoreDetailsEvaluateView;
import com.xi6666.store.custom.StoreDetailsHeadView;
import com.xi6666.store.custom.StoreDetailsServiceView;
import com.xi6666.technician.view.TechnicianDetailsAct;
import com.xi6666.owner.view.EvaluateStoreAct;

import com.xi6666.owner.view.OwnerEvaluationAct;

/**
 * 创建者 sunsun
 * 时间 16/11/4 下午5:46.
 * 个人公众号 ardays
 */

public class StoreDetailsAct extends BaseToolbarView<StoreDetailsPresenter, StoreDetailsModel>
        implements StoreDetailsContract.View {

    MonitorScrollView mScrollView;  //具有监听效果的ScrollView;
    StoreDetailsHeadView mHeadView; //banner图以及店面信息的View
    StoreDetailsServiceView mServiceView;//服务项目View
    RecyclerView mTechnicianTeamRv;    //团队(列表)
    View mTechnicianTeamLL; //团队
    TechnicianTeamAdapter mTechnicianAdpter;    //团队(适配器)
    StoreDetailsEvaluateView mStoreDetailsEvaluateView; //评价适配器
    View mCommentLl; //点评
    View mCellLl;    //联系门店

    private String mStoreId;    //门店ID
    private String mStoreUserId;    //门店ID
    private String mServiceCateId;    //门店ID
    private String mTel;    //门电话

    private ShareUtils mShareUtils; //分享

    @Override
    public String title() {
        return "门店详情";
    }

    @Override
    public int mainResId() {
        return 0;
    }

    @Override
    public int backgroundResId() {
        return R.layout.activity_store_details;
    }

    @Override
    public void setUp(View view) {
        initView(view);
        initToolbar();
        initHeadView();
        initServiceView();
        initTechnicianTeamView();
        initEvaluateView();
        initBottomTab();
        mStoreId = getIntent().getStringExtra("store_id");
        mServiceCateId = getIntent().getStringExtra("service_cate_id");
        showLoading();
        mPersenter.requestStoreDetails(mStoreId, mServiceCateId);
        mScrollView.smoothScrollTo(0, 20);
    }

    //初始化View
    private void initView(View view) {
        mHeadView = (StoreDetailsHeadView) view.findViewById(R.id.store_details_head);
        mServiceView = (StoreDetailsServiceView) view.findViewById(R.id.store_details_service);
        mTechnicianTeamRv = (RecyclerView) view.findViewById(R.id.store_details_technician_team);
        mTechnicianTeamLL = view.findViewById(R.id.store_details_technician_ll);
        mScrollView = (MonitorScrollView) view.findViewById(R.id.store_details_scroll_msv);
        mCommentLl = view.findViewById(R.id.store_details_comment_ll);
        mCellLl = view.findViewById(R.id.store_details_cell_ll);
        mStoreDetailsEvaluateView = (StoreDetailsEvaluateView) view.findViewById(R.id.store_details_evaluate);
    }

    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        //设置标题栏颜色透明度
        setToolbarColorTransparent(0);


        // 根据滚动的高度做出对应的变化
        mScrollView.setOnScrollListener(new MonitorScrollView.OnScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                // 滑动的高度 / banner图的高度  * 100 = 透明度 ，（乘以1.0的意思就是java默认两个整数相除得整数)；
                float i = (float) (1.0 * t / mHeadView.getBannerHeight() * 100);
                setToolbarColorTransparent((int) i);
            }
        });
    }


    /**
     * 重写标题栏透明度改变方法，并且做出透明度大于50 和 小于50的逻辑
     */
    @Override
    public void setToolbarColorTransparent(int i) {
        super.setToolbarColorTransparent(i);


        //是否透明度大于50
        boolean bol = i < 50;

        //标题名字颜色
        int titleColor = bol ? R.color.white : R.color.text_black;
        //左边按钮
        int leftIcon = bol ? R.mipmap.bg_store_details_back : R.mipmap.ic_left_gray;
        //右边按钮
        int rightIcon = bol ? R.mipmap.bg_store_details_share : R.mipmap.bg_store_details_share_black;


        setToolbarTitleColor(getResources().getColor(titleColor));
        setToolbarLeftIcon(leftIcon);
        setToolbarRightIcon(rightIcon);
    }

    /**
     * 初始化头部逻辑
     */
    private void initHeadView() {
//        List<String> urls = new ArrayList<>();
//        urls.add("http://v3.qzone.cc/manage/201611/06/12/01/581eab2654d76316.jpg");
//        urls.add("http://v3.qzone.cc/manage/201611/06/12/01/581eab285daf4208.jpg");
//        urls.add("http://v3.qzone.cc/manage/201611/06/12/01/581eab2a42871737.jpg");
//        urls.add("http://v3.qzone.cc/manage/201611/06/12/01/581eab2c4b7d2573.jpg");
//        urls.add("http://v3.qzone.cc/manage/201611/06/12/01/581eab2ee6bce383.jpg");
//        urls.add("http://v3.qzone.cc/manage/201611/06/09/42/581e8a873b299392.jpg");
//        mHeadView.setBannerUrls(urls);
    }

    /**
     * 初始化服务项目
     */
    private void initServiceView() {
//        mServiceView.setServiceData(StoreDetailsServiceBean.test());
    }

    /**
     * 初始化技师团队
     */
    private void initTechnicianTeamView() {
        mTechnicianAdpter = new TechnicianTeamAdapter(this);
        LinearLayoutManager m = new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTechnicianTeamRv.setLayoutManager(m);
        mTechnicianTeamRv.setAdapter(mTechnicianAdpter);

//        //写入假数据
//        mTechnicianAdpter.addData(TechnicianTeamBean.test());

        //监听技师点击
        mTechnicianAdpter.setOnTechnicianTeamListener(new TechnicianTeamAdapter.OnTechnicianTeamListener() {
            @Override
            public void onItemClick(TechnicianTeamBean data) {
                TechnicianDetailsAct.openActivity(getActivity(), data.technician_id);
            }
        });
    }


    /**
     * 初始化评价
     */
    private void initEvaluateView() {

        //监听车主评价的点击事件

        mStoreDetailsEvaluateView.setOnStoreDetailsEvaluateListener(new StoreDetailsEvaluateView.OnStoreDetailsEvaluateListener() {
            @Override
            public void onDetailsClick(View view) {
                OwnerEvaluationAct.openActivity(getActivity(), mStoreId, mStoreUserId);
            }

            @Override
            public void onMoreClick(View view) {
                OwnerEvaluationAct.openActivity(getActivity(), mStoreId, mStoreUserId);
            }

            @Override
            public void onLikesClick(boolean likes, int position, String discuss_id) {
                mPersenter.requestLikesClick(likes, discuss_id, position, mStoreUserId);
            }
        });

    }

    /**
     * 初始化底部tab
     */
    private void initBottomTab() {
        Log.e("TAG", "initBottomTab");
        //点评点击事件
        mCommentLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点评点击事件
                EvaluateStoreAct.openActivity(getActivity(), mStoreUserId);
            }
        });

        //联系门店点击事件
        mCellLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //联系门店方法
                Utils.callTel(mTel, getActivity());
            }
        });
    }

    @Override
    public void onToolbarRightClick(View view) {
        super.onToolbarRightClick(view);

        if (mShareUtils == null) {
            make("分享失败");
            return;
        }

        mShareUtils.showDialog();
    }

    //                      @网络参数
    @Override
    public void returnStoreDetails(StoreDetailsBean bean) {
        hiddenLoading();
        hiddenErrorView();
        if (bean.success) {
            StoreDetailsBean.DataBean data = bean.data;
            mStoreUserId = data.storeinfo.user_id;
            //头部数据
            mHeadView.setData(data.storeinfo);
            //本店服务项目数据
            mServiceView.setStoreId(mStoreId);
            mServiceView.setServiceData(data.servicelist);
            if (data.technicianinfo != null && data.technicianinfo.size() == 0) {
                mTechnicianTeamLL.setVisibility(View.GONE);
            } else {
                //写入技师列表
                mTechnicianAdpter.addData(data.technicianinfo);
            }
            //写入评价
            mStoreDetailsEvaluateView.setCommentLv(data.discussinfo);
            //写入评论数量
            mStoreDetailsEvaluateView.setCommentData(data.storeinfo);
            //电话
            mTel = data.storeinfo.shop_tel;

            //分享
            StoreDetailsBean.DataBean.WxShareBean wxShareBean = data.wx_share;
            mShareUtils = new ShareUtils(this, wxShareBean.wx_share_link, wxShareBean.wx_share_title, wxShareBean.wx_share_desc, wxShareBean.wx_share_img_url);
        } else {
            make(bean.info);
        }
    }

    @Override
    public void returnLikeResult(boolean bol, int position, BaseBean bean) {
        make(bean.info);
        if (bean.success) {
            mStoreDetailsEvaluateView.setLikes(bol, position);
        }
    }

    @Override
    public void returnError() {
        showErrorView();
        errorClick(v -> {
            mPersenter.requestStoreDetails(mStoreId, mServiceCateId);
        });
    }


    /**
     * BY:记得注册Activity
     */
    public static final void openActivity(Context activity, String store_id, String service_cate_id) {
        Intent intent = new Intent(activity, StoreDetailsAct.class);
        intent.putExtra("store_id", store_id);
        intent.putExtra("service_cate_id", service_cate_id);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EvaluateStoreAct.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                mPersenter.requestStoreDetails(mStoreId, mServiceCateId);
            }
        }
    }
}
