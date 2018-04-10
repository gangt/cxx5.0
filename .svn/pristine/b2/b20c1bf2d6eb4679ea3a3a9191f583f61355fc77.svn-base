package com.xi6666.evaluate.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.evaluate.bean.ServerEvaluateBean;
import com.xi6666.evaluate.other.EvaluateBar;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.order.activity.OrderSeeLagerImgActivity;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.SpaceItemDecoration;
import com.xi6666.order.other.Utils;
import com.xi6666.store.StoreDetailsAct;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 作者： qsdsn on 2016/11/9
 * 描述：服务评价的fragment
 */

public class ServersEvaluateFragment extends Fragment implements XRecyclerView.LoadingListener {

    private XRecyclerView mXRecyclerView;
    private LinearLayout mLlNoOrder;
    private LinearLayout mLlReloadData;
    private Button mBtnReloadData;

    private MyAdapter mAdapter;

    private List<ServerEvaluateBean.DataBean> mDatas = new ArrayList<>();
    private ServerEvaluateBean mServerEvaluateBean;

    private ArrayList<String> mImagesList = new ArrayList<>();

    private int page = 1;
    private String user_id;
    private String user_token;
    private Dialog mLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goods_evaluate, container, false);
        mXRecyclerView = (XRecyclerView) rootView.findViewById(R.id.xRecyclerView_evaluate);
        mLlNoOrder = (LinearLayout) rootView.findViewById(R.id.ll_no_evaluate);
        mLlReloadData = (LinearLayout) rootView.findViewById(R.id.ll_reload_data);
        mBtnReloadData = (Button) rootView.findViewById(R.id.btn_reload_data);
        init();
        initData();
        initEvent();
        return rootView;
    }

    private void init() {
        mLoading = ShowDialogUitls.showDio(getActivity());
        mLoading.dismiss();
        user_id = UserData.getUserId();

        mXRecyclerView.setVisibility(View.VISIBLE);
        mLlNoOrder.setVisibility(View.GONE);
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MyAdapter(getActivity(), mDatas, R.layout.item_server_evaluate);
        mXRecyclerView.addItemDecoration(new SpaceItemDecoration(getActivity(), 11, mAdapter));
        mXRecyclerView.setAdapter(mAdapter);

        mLlReloadData.setVisibility(View.GONE);
        mXRecyclerView.setVisibility(View.GONE);
        mLlNoOrder.setVisibility(View.GONE);
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).getServerCommentList(page + "", user_id, user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getActivity().runOnUiThread(() -> {
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                    });
                    String result = response.body().string();
                    LogUtil.i("ServersEvaluateFragment", result);
                    mServerEvaluateBean = GsonUtils.toEntityFromJson(result, ServerEvaluateBean.class);
                    if (mServerEvaluateBean.isSuccess()) {
                        String count = mServerEvaluateBean.getCount();
                        if (!"0".equals(count)) {
                            List<ServerEvaluateBean.DataBean> data = mServerEvaluateBean.getData();
                            if (page == 1) {
                                if (data.size() > 0) {
                                    mDatas.clear();
                                    mLlReloadData.setVisibility(View.GONE);
                                    mXRecyclerView.setVisibility(View.VISIBLE);
                                    mLlNoOrder.setVisibility(View.GONE);
                                } else {
                                    mLlReloadData.setVisibility(View.GONE);
                                    mXRecyclerView.setVisibility(View.GONE);
                                    mLlNoOrder.setVisibility(View.VISIBLE);
                                }
                            }
                            if (data.size() > 0) {
                                mDatas.addAll(data);
                                mAdapter.notifyDataSetChanged();
                                if (data.size() < 15) {
                                    mXRecyclerView.loadMoreComplete();
                                    mXRecyclerView.setNoMore(true);
                                }
                            }
                        } else {
                            mLlReloadData.setVisibility(View.GONE);
                            mXRecyclerView.setVisibility(View.GONE);
                            mLlNoOrder.setVisibility(View.VISIBLE);
                            Utils.make(ServersEvaluateFragment.this.getActivity(), mServerEvaluateBean.getInfo());
                        }
                    } else {
                        mLlReloadData.setVisibility(View.GONE);
                        mXRecyclerView.setVisibility(View.GONE);
                        mLlNoOrder.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO 由于后台有数据时返回的data是数组，无数据时是{}，特意作此处理
                    mLlReloadData.setVisibility(View.GONE);
                    mXRecyclerView.setVisibility(View.GONE);
                    mLlNoOrder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                mLlReloadData.setVisibility(View.VISIBLE);
                mXRecyclerView.setVisibility(View.GONE);
                mLlNoOrder.setVisibility(View.GONE);
            }
        });
    }

    private void initEvent() {
        mXRecyclerView.setLoadingListener(this);
        mBtnReloadData.setOnClickListener(v -> {
            initData();
        });
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData();
        mXRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        initData();
        mXRecyclerView.loadMoreComplete();
    }

    class MyAdapter extends CustomAdapter<ServerEvaluateBean.DataBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected MyAdapter(Context context, List<ServerEvaluateBean.DataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(CustomViewHolder holder, ServerEvaluateBean.DataBean item) {
            final int position = holder.getAdapterPosition();
            EvaluateBar bar = holder.findViewById(R.id.item_evaluateBar_server);
            bar.setStarMark(Integer.parseInt(item.getComment_level()));
            holder.setText(R.id.item_tv_time_server, item.getAdd_datetime());
            holder.setText(R.id.item_tv_content_evaluate_server, item.getComment_content());
            holder.setText(R.id.item_tv_store_name_evaluate, item.getShop_name());
            holder.setText(R.id.item_tv_server_type_evaluate, "服务名称：" + item.getService_name());

            ImageView img = holder.findViewById(R.id.item_iv_server_img_evaluate);
            Glide.with(ServersEvaluateFragment.this.getActivity()).load(item.getShop_banner())
                    .placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(img);

            RecyclerView recyclerView = holder.findViewById(R.id.nine_img_recyclerView_server);
            recyclerView.setVisibility(View.GONE);
            GridLayoutManager manage = new GridLayoutManager(ServersEvaluateFragment.this.getActivity(), 3);
            manage.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manage);
            ImageAdapter adapter = new ImageAdapter(ServersEvaluateFragment.this.getActivity(), mImagesList, R.layout.item_nine_img);
            recyclerView.addItemDecoration(new SpaceItemDecoration(ServersEvaluateFragment.this.getActivity(), 10, adapter));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClickListener(int posotion) {
                    // 跳转到查看大图页面
                    Intent intent = new Intent(getActivity(), OrderSeeLagerImgActivity.class);
                    intent.putStringArrayListExtra("picS", mImagesList);
                    intent.putExtra("position", posotion);
                    startActivity(intent);
                }
            });

            LinearLayout layout = holder.findViewById(R.id.item_ll_store_details);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到门店详情页
                    StoreDetailsAct.openActivity(getActivity(), item.getShop_user_id(), item.getService_cate_id());
                }
            });
        }
    }

    class ImageAdapter extends CustomAdapter<String> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected ImageAdapter(Context context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(CustomViewHolder holder, String item) {

        }
    }
}
