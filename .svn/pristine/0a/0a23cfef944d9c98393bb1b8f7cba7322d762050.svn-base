package com.xi6666.order.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.order.bean.SelectCommentBean;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SelectEvaluateGoodsActivity extends ToolBarBaseActivity {

    @BindView(R.id.list_view)
    ListView     mListView;
    @BindView(R.id.ll_reload_data)
    LinearLayout mLlReloadData;
    @BindView(R.id.btn_reload_data)
    Button mBtnReloadData;

    private String order_sn;
    private boolean isOneGoods;
    private List<SelectCommentBean.DataBean> mGoodsList = new ArrayList<>();
    private SelectCommentBean mSelectCommentBean;
    private Dialog mLoading;
    private String user_id;
    private String user_token;
    private GoodsAdapter mAdapter;
    private boolean isConment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_evaluate_goods);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        order_sn = bundle.getString("order_sn");
        isOneGoods = bundle.getBoolean("isOneGoods",false);
        mLoading = ShowDialogUitls.showDio(this);
        user_id = UserData.getUserId();
        user_token = UserData.getUserToken();
        mAdapter = new GoodsAdapter();
        mListView.setAdapter(mAdapter);

        mLlReloadData.setVisibility(View.GONE);
        mListView.setVisibility(View.GONE);

        mBtnReloadData.setOnClickListener(v -> {
            initData();
        });
    }

    private void initData() {
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).selectCommentGoods(order_sn,user_id,user_token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                try {
                    String result = response.body().string();
                    LogUtil.i("SelectEvaluateGoodsActivity",result);
                    mSelectCommentBean = GsonUtils.toEntityFromJson(result,SelectCommentBean.class);
                    if (mSelectCommentBean.isSuccess()) {
                        mLlReloadData.setVisibility(View.GONE);
                        mListView.setVisibility(View.VISIBLE);
                        mGoodsList.clear();
                        mGoodsList.addAll(mSelectCommentBean.getData());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Utils.make(SelectEvaluateGoodsActivity.this,mSelectCommentBean.getInfo());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
                mLlReloadData.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "选择评价商品";
    }

    class GoodsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mGoodsList != null)
                return mGoodsList.size();
            return 10;
        }

        @Override
        public Object getItem(int position) {
            if (mGoodsList != null)
                return mGoodsList.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(SelectEvaluateGoodsActivity.this,R.layout.item_select_evaluate_goods,null);
                holder = new ViewHolder();
                holder.mImageView = (ImageView) convertView.findViewById(R.id.item_iv_goods_img);
                holder.mTvGoodsName = (TextView) convertView.findViewById(R.id.item_tv_goods_name);
                holder.mTvGoodsColor = (TextView) convertView.findViewById(R.id.item_tv_goods_color);
                holder.mTvGoodsNum = (TextView) convertView.findViewById(R.id.item_tv_goods_num);
                holder.mTvGoodsPrice = (TextView) convertView.findViewById(R.id.item_tv_goods_price);
                holder.mBtnEvaluate = (Button) convertView.findViewById(R.id.item_btn_evaluate);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SelectCommentBean.DataBean dataBean = mGoodsList.get(position);
            holder.mTvGoodsName.setText(dataBean.getGoods_name());
            holder.mTvGoodsNum.setText("x" + dataBean.getBuy_number());
            holder.mTvGoodsPrice.setText("¥ " + dataBean.getGoods_shop_price());
            final String goods_soure_img = dataBean.getGoods_soure_img();
            final String goods_id = dataBean.getGoods_id();
            int is_comment = dataBean.getIs_comment();
            if (is_comment == 1) {
                isConment = true;
                holder.mBtnEvaluate.setText("查看评价");
            } else {
                isConment = false;
                holder.mBtnEvaluate.setText("立即评价");
            }
            // TODO SKU
            Glide.with(SelectEvaluateGoodsActivity.this).load(goods_soure_img).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(holder.mImageView);
            holder.mBtnEvaluate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SelectEvaluateGoodsActivity.this,GoodsEvaluateActivity.class);
                    intent.putExtra("goods_id",goods_id);
                    intent.putExtra("goodsImg",goods_soure_img);
                    intent.putExtra("order_sn",order_sn);
                    intent.putExtra("is_comment",isConment);
                    intent.putExtra("isOneGoods",isOneGoods);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView mImageView;
            TextView  mTvGoodsName;
            TextView  mTvGoodsColor;
            TextView  mTvGoodsNum;
            TextView  mTvGoodsPrice;
            Button    mBtnEvaluate;
        }
    }

    @Override
    protected void onResume() {
        initData();
    }
}
