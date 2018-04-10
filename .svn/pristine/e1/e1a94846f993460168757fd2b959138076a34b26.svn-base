package com.xi6666.evaluate.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.carWash.view.DetermineOrderAct;
import com.xi6666.common.UserData;
import com.xi6666.evaluate.bean.StoreServiceBean;
import com.xi6666.eventbus.LoginEvent;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.ToolBarBaseActivity;
import com.xi6666.login.view.LoginAct;
import com.xi6666.order.activity.OrderSeeLagerImgActivity;
import com.xi6666.order.other.CustomAdapter;
import com.xi6666.order.other.CustomViewHolder;
import com.xi6666.order.other.HorizontalListView;
import com.xi6666.order.other.SpaceItemDecoration;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoreServiceItemActivity extends ToolBarBaseActivity {

    @BindView(R.id.iv_store_img_ssi)
    ImageView          mIvStoreImgSsi;
    @BindView(R.id.tv_store_name_ssi)
    TextView           mTvStoreNameSsi;
    @BindView(R.id.service_horizontalListView)
    HorizontalListView mServiceHorizontalListView;
    @BindView(R.id.ll_service_details)
    LinearLayout       mLlServiceDetails;
    @BindView(R.id.ll_reload_data)
    LinearLayout       mLlReloadData;
    @BindView(R.id.btn_reload_data)
    Button             mBtnReloadData;
    @BindView(R.id.ll_store_service_item)
    LinearLayout       mLlStoreServiceItem;
    @BindView(R.id.tv_store_service_item)
    TextView           mTvStoreServiceItem;
    @BindView(R.id.service_item_recyclerView)
    RecyclerView       mServiceItemRecyclerView;

    private ArrayList<String> mImagesList = new ArrayList<>();
    private ServiceApapter mAdapter;
    private List<StoreServiceBean.DataBean.ServiceListBean> mDatas = new ArrayList<>();
    private StoreServiceBean mStoreServiceBean;
    private String service_id;
    private String shop_id;
    private String service_name;
    private Dialog mLoading;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_service_item);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        initData();
    }

    private void init() {
        service_id = getIntent().getStringExtra("service_id");
        shop_id = getIntent().getStringExtra("shop_id");
        service_name = getIntent().getStringExtra("service_name");
        mTvStoreServiceItem.setText("本店" + service_name);
        LogUtil.i("StoreServiceItemActivity_intent",shop_id + "#######" + service_id);
        user_id = UserData.getUserId();
        mTxtTiltle.setText(service_name);
        mLoading = ShowDialogUitls.showDio(this);
        mLlReloadData.setVisibility(View.GONE);
        mLlStoreServiceItem.setVisibility(View.GONE);
        mBtnReloadData.setOnClickListener(v -> {
            initData();
        });
    }

    private void initData(){
        mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        retrofit.create(NetAddress.class).shopService(service_id,shop_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                runOnUiThread(() -> {
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                });
                try {
                    String result = response.body().string();
                    LogUtil.i("StoreServiceItemActivity",result);
                    mLlReloadData.setVisibility(View.GONE);
                    mLlStoreServiceItem.setVisibility(View.VISIBLE);
                    mStoreServiceBean = GsonUtils.toEntityFromJson(result,StoreServiceBean.class);
                    if (mStoreServiceBean.isSuccess()) {
                        StoreServiceBean.DataBean.ShopInfoBean shopInfoBean = mStoreServiceBean.getData().getShop_info();
                        mTvStoreNameSsi.setText(shopInfoBean.getShop_name());
                        Glide.with(StoreServiceItemActivity.this)
                                .load(shopInfoBean.getShop_banner()).placeholder(R.drawable.no_data_empty).centerCrop()
                                .into(mIvStoreImgSsi);
                        if (mStoreServiceBean.getData().getPhoto_list().size() > 0) {
                            mImagesList.addAll(mStoreServiceBean.getData().getPhoto_list());
                            mServiceHorizontalListView.setAdapter(new ImageAdapter(mImagesList));
                        } else {
                            mLlServiceDetails.setVisibility(View.GONE);
                        }
                        mDatas.addAll(mStoreServiceBean.getData().getService_list());
                        mAdapter = new ServiceApapter(StoreServiceItemActivity.this,mDatas,R.layout.item_store_service_item);
                        mServiceItemRecyclerView.setLayoutManager(new LinearLayoutManager(StoreServiceItemActivity.this));
                        mServiceItemRecyclerView.addItemDecoration(new SpaceItemDecoration(StoreServiceItemActivity.this,11,mAdapter));
                        mServiceItemRecyclerView.setAdapter(mAdapter);
                    } else {
                        Utils.make(StoreServiceItemActivity.this,mStoreServiceBean.getInfo());
                        mLlStoreServiceItem.setVisibility(View.GONE);
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
                mLlStoreServiceItem.setVisibility(View.GONE);
            }
        });

        mServiceHorizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StoreServiceItemActivity.this, OrderSeeLagerImgActivity.class);
                intent.putStringArrayListExtra("picS", mImagesList);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setToolbarIconDo() {
        finish();
    }

    @Override
    public String setToolbarTitle() {
        return "洗车服务";
    }

    class ServiceApapter extends CustomAdapter<StoreServiceBean.DataBean.ServiceListBean> {

        /**
         * 构造器
         *
         * @param context
         * @param mDatas
         * @param itemLayoutId
         */
        protected ServiceApapter(Context context, List<StoreServiceBean.DataBean.ServiceListBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(CustomViewHolder holder, StoreServiceBean.DataBean.ServiceListBean item) {
            final int position = holder.getAdapterPosition();
            TextView tvName = holder.findViewById(R.id.item_tv_service_name);
            TextView tvMoney = holder.findViewById(R.id.item_tv_service_money);
            tvName.setText(item.getService_name());
            tvMoney.setText("¥ " + item.getService_price());
            holder.findViewById(R.id.item_btn_to_pay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  跳转到门店的确认订单界面
                    if (user_id == null || TextUtils.isEmpty(user_id)) {
                        startActivity(new Intent(StoreServiceItemActivity.this, LoginAct.class));
                    } else {
                        Intent intent = new Intent(StoreServiceItemActivity.this, DetermineOrderAct.class);
                        intent.putExtra("service_id",item.getService_id());
                        startActivity(intent);
                    }
                }
            });
        }
    }

    class ImageAdapter extends BaseAdapter {
        private List<String> mDatas;

        public ImageAdapter(List<String> datas) {
            this.mDatas = datas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(StoreServiceItemActivity.this,R.layout.item_images,null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.item_more_img);
                ViewGroup.LayoutParams layoutParams = holder.img.getLayoutParams();
                DisplayMetrics dpMetrics = new DisplayMetrics();
                getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
                int screenWidth = dpMetrics.widthPixels;
                layoutParams.width = (int) (screenWidth / 4.5);
                holder.img.setLayoutParams(layoutParams);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.img.setPadding(Utils.dp2Px(StoreServiceItemActivity.this,18),0,0,0);
            Glide.with(StoreServiceItemActivity.this)
                    .load(mDatas.get(position)).placeholder(R.drawable.no_data_empty).centerCrop()
                    .into(holder.img);
            return convertView;
        }
    }

    class ViewHolder {
        ImageView img;
    }

    //登录状态改变
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginStateChange(LoginEvent mlLoginEvent) {
        if (!TextUtils.isEmpty(UserData.getUserId())) {
            user_id = UserData.getUserId();
        }
    }
}
