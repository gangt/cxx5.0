package com.xi6666.evaluate.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.evaluate.bean.CollectionBean;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.SpaceItemDecoration;
import com.xi6666.order.other.Utils;
import com.xi6666.productdetails.view.ProductDetailsAct;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;
import com.xi6666.view.superrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyCollectionActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.iv_mc_back)
    ImageView      mIvMcBack;
    @BindView(R.id.tv_mc_edit)
    TextView       mTvMcEdit;
    @BindView(R.id.tv_mc_done)
    TextView       mTvMcDone;
    @BindView(R.id.mc_tv_no_goods)
    TextView       mTvMcNoGoods;
    @BindView(R.id.mc_tb)
    Toolbar        mMcTb;
    @BindView(R.id.mc_xRecyclerView)
    XRecyclerView  mMcXRecyclerView;
    @BindView(R.id.mc_bottom_cb)
    CheckBox       mMcBottomCb;
    @BindView(R.id.btn_mc_bottom_delete)
    Button         mBtnMcBottomDelete;
    @BindView(R.id.rl_mc_bottom)
    RelativeLayout mRlMcBottom;
    @BindView(R.id.ll_reload_data)
    LinearLayout   mLlReloadData;
    @BindView(R.id.btn_reload_data)
    Button         mBtnReloadData;

    private LinearLayoutManager mLayoutManager;

    private List<CollectionBean.DataBean> mDatas = new ArrayList<>();
    private CollectionAdapter             mAdapter;
    private CollectionBean                mCollectionBean;

    private HashMap<Integer, Boolean> mItemIsSelects = new HashMap<>();
    private boolean isEdit;
    private Dialog  mLoading;
    private String  user_id;
    private String  user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);
        setSupportActionBar(mMcTb);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ToolBarBaseActivity.setMiuiStatusBarDarkMode(this,true);
        init();
        initData();
    }

    private void init() {
        mLoading = ShowDialogUitls.showDio(this);
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();

        mLayoutManager = new LinearLayoutManager(this);
        controlDeleteBtn();
        mAdapter = new CollectionAdapter(this, mDatas, R.layout.item_my_collection);
        mMcXRecyclerView.setPullRefreshEnabled(true);
        mMcXRecyclerView.setLoadingMoreEnabled(false);
        mMcXRecyclerView.setLayoutManager(mLayoutManager);
        mMcXRecyclerView.addItemDecoration(new SpaceItemDecoration(this, 11, mAdapter));
        mMcXRecyclerView.setAdapter(mAdapter);

        mRlMcBottom.setOnSystemUiVisibilityChangeListener(l ->
            controlDeleteBtn()
        );

        mMcXRecyclerView.setLoadingListener(this);

        mLlReloadData.setVisibility(View.GONE);
        mMcXRecyclerView.setVisibility(View.GONE);
        mTvMcNoGoods.setVisibility(View.GONE);
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).myCollectionList(user_id,user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    runOnUiThread(() -> {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                        try {
                            int code = response.code();
                            LogUtil.i("MyCollectionActivity", "code =" + code);
                            String result = response.body().string();
                            LogUtil.i("MyCollectionActivity", result);
                            mCollectionBean = GsonUtils.toEntityFromJson(result, CollectionBean.class);
                            if (mCollectionBean.isSuccess()) {
                                mDatas.addAll(mCollectionBean.getData());
                                if (mDatas.size() <= 0) {
                                    mTvMcNoGoods.setVisibility(View.VISIBLE);
                                    mMcXRecyclerView.setVisibility(View.GONE);
                                    mLlReloadData.setVisibility(View.GONE);
                                } else {
                                    mTvMcNoGoods.setVisibility(View.GONE);
                                    mMcXRecyclerView.setVisibility(View.VISIBLE);
                                    mLlReloadData.setVisibility(View.GONE);
                                    for (int i = 0; i <= mDatas.size(); i++) {
                                        mItemIsSelects.put(i, false);
                                    }
                                    mAdapter.notifyDataSetChanged();
                                }
                            } else {
                                mLlReloadData.setVisibility(View.VISIBLE);
                                Utils.make(MyCollectionActivity.this, mCollectionBean.getInfo());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Utils.make(MyCollectionActivity.this,"服务器异常");
                            mLlReloadData.setVisibility(View.VISIBLE);
                            mMcXRecyclerView.setVisibility(View.GONE);
                            mTvMcNoGoods.setVisibility(View.GONE);
                        }
                    });
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                mLlReloadData.setVisibility(View.VISIBLE);
                mMcXRecyclerView.setVisibility(View.GONE);
                mTvMcNoGoods.setVisibility(View.GONE);
            }
        });
    }

    @OnClick({R.id.iv_mc_back, R.id.tv_mc_edit, R.id.tv_mc_done, R.id.btn_mc_bottom_delete, R.id.mc_bottom_cb,R.id.btn_reload_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_mc_back:
                finish();
                break;
            case R.id.tv_mc_edit:
                if (!isEdit) {
                    isEdit = true;
                    mRlMcBottom.setVisibility(View.VISIBLE);
                    mTvMcEdit.setVisibility(View.GONE);
                    mTvMcDone.setVisibility(View.VISIBLE);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_mc_done:
                if (isEdit) {
                    isEdit = false;
                    mRlMcBottom.setVisibility(View.GONE);
                    mTvMcEdit.setVisibility(View.VISIBLE);
                    mTvMcDone.setVisibility(View.GONE);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.btn_mc_bottom_delete:
                showDeleteDialog();
                break;
            case R.id.btn_reload_data:
                initData();
                break;
            case R.id.mc_bottom_cb:

                if (mMcBottomCb.isChecked()) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        mItemIsSelects.put(i, true);
                    }
                    controlDeleteBtn();
                    mAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < mDatas.size(); i++) {
                        mItemIsSelects.put(i, false);
                    }
                    controlDeleteBtn();
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void showDeleteDialog() {
        final AlertDialog show = new AlertDialog.Builder(MyCollectionActivity.this).create();
        show.show();
        show.setCancelable(true);
        Window window = show.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        lp.alpha = 0.9f;// 设置透明度
        // 设置不弹出软键盘
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        window.setWindowAnimations(R.style.delete_dialog_anima);// 设置动画效果
        window.setAttributes(lp);
        window.setContentView(R.layout.dialog_delete_goods);
        window.findViewById(R.id.tv_delete_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> ids = new ArrayList<>();
                List<Integer> indexs = new ArrayList<>();
                for (int i = 0; i < mDatas.size(); i++) {
                    if (mItemIsSelects.get(i)) {
                        ids.add(mDatas.get(i).getGoods_id());
                        indexs.add(i);
                    }
                }
                JSONArray array = new JSONArray();
                for (int i = 0; i < ids.size(); i++) {
                    array.put(ids.get(i));
                }
                deleteCollectionGoods(array.toString(),0,indexs);
                show.dismiss();
            }
        });
        window.findViewById(R.id.tv_cancel_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
    }

    private void controlDeleteBtn() {
        int count = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            if (mItemIsSelects.get(i)) {
                count++;
            }
        }
        if (count == 0) {
            mBtnMcBottomDelete.setTextColor(getResources().getColor(R.color.alpha_red_text));
            mBtnMcBottomDelete.setEnabled(false);
        } else {
            mBtnMcBottomDelete.setTextColor(getResources().getColor(R.color.red_text));
            mBtnMcBottomDelete.setEnabled(true);
        }
    }

    @Override
    public void onRefresh() {
        mDatas.clear();
        mAdapter.notifyDataSetChanged();
        initData();
        mMcXRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {

    }

    class CollectionAdapter extends CustomAdapter<CollectionBean.DataBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected CollectionAdapter(Context context, List<CollectionBean.DataBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(CustomViewHolder holder, CollectionBean.DataBean item) {
            final int position = holder.getAdapterPosition() - 1;
            TextView tvMarketPrice = holder.findViewById(R.id.item_tv_mc_men_price);
            TextView tvXiPrice = holder.findViewById(R.id.item_tv_mc_xi_price);
            TextView tvGoodsName = holder.findViewById(R.id.item_tv_mc_goods_desc);
            ImageView ivGoodsImg = holder.findViewById(R.id.item_iv_mc_goods_img);
            final CheckBox checkBox = holder.findViewById(R.id.item_mc_cb);
            if (isEdit) {
                checkBox.setVisibility(View.VISIBLE);
            } else {
                checkBox.setVisibility(View.GONE);
            }
            //设置文字斜杠
            TextPaint paint = tvMarketPrice.getPaint();
            paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            paint.setAntiAlias(true);

            tvMarketPrice.setText("¥ " + item.getMarket_price());
            tvXiPrice.setText("¥ " + item.getGoods_price());
            tvGoodsName.setText(item.getGoods_name());

            Glide.with(MyCollectionActivity.this)
                    .load(item.getGoods_thumb_img()).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(ivGoodsImg);
            final String goodsId = item.getGoods_id();
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mItemIsSelects.put(position, isChecked);
                }
            });
            checkBox.setChecked(mItemIsSelects.get(position));
            checkBox.setClickable(false);

            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isEdit) {
                        // 改变条目checkBox的状态
                        checkBox.toggle();
                        // 记录改变后的状态值
                        mItemIsSelects.put(position, checkBox.isChecked());
                    } else {
                        // 跳转到商品详情页
                        Intent intent = new Intent(MyCollectionActivity.this, ProductDetailsAct.class);
                        intent.putExtra("goodId",item.getGoods_id());
                        startActivity(intent);
                    }

                    int count = 0;
                    for (int i = 0; i < mDatas.size(); i++) {
                        if (mItemIsSelects.get(i)) {
                            count++;
                        }
                    }
                    if (count == mDatas.size()) {
                        mMcBottomCb.setChecked(true);
                        mBtnMcBottomDelete.setTextColor(getResources().getColor(R.color.red_text));
                        mBtnMcBottomDelete.setEnabled(true);
                    } else if (count == 0) {
                        mMcBottomCb.setChecked(false);
                        mBtnMcBottomDelete.setTextColor(getResources().getColor(R.color.alpha_red_text));
                        mBtnMcBottomDelete.setEnabled(false);
                    } else {
                        mMcBottomCb.setChecked(false);
                        mBtnMcBottomDelete.setTextColor(getResources().getColor(R.color.red_text));
                        mBtnMcBottomDelete.setEnabled(true);
                    }
                }
            });

            final ImageView delete = holder.findViewById(R.id.mc_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONArray array = new JSONArray();
                    array.put(goodsId);
                    deleteCollectionGoods(array.toString(),position,new ArrayList<Integer>());

                }
            });
        }
    }

    /**
     * 删除收藏的商品
     */
    private void deleteCollectionGoods(final String goodsIds, final int position, final List<Integer> indexs) {
        System.out.println("要删除的商品:" + goodsIds);
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).deleteCollections(goodsIds, user_id,user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("结果码:" + response.code());
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                        }
                    });
                    String result = response.body().string();
                    System.out.println("MyCollectionActivity_delete:" + result);
                    JSONObject json = new JSONObject(result);
                    Utils.make(MyCollectionActivity.this, json.optString("info"));
                    JSONArray array = new JSONArray(goodsIds);
                    if (json.optBoolean("success")) {
                        // 删除成功
                        if (array.length() == 1) {
                            // 单条删除
                            mDatas.remove(position);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            // 批量删除
                            for (int i = 0; i < indexs.size(); i++) {
                                mDatas.remove(indexs.get(i));
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        onRefresh();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.make(MyCollectionActivity.this, "网络异常");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Utils.make(MyCollectionActivity.this, "服务器异常");
            }
        });
    }
}
