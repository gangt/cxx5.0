package com.xi6666.store.custom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xi6666.R;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.order.activity.OrderSeeLagerImgActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/8 下午5:07.
 * 个人公众号 ardays
 */
public class StoreEvaluateView extends LinearLayout implements StoreEvaluateViewImpl {
    /**
     * 上下文
     */
    Context mContext;

    public StoreEvaluateView(Context context) {
        this(context, null);
    }

    public StoreEvaluateView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public StoreEvaluateView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    @BindView(R.id.view_store_evaluate_image_rv)
    RecyclerView mImagesRv; //车主评论(晒图)
    @BindView(R.id.view_store_evaluate_level_eb)
    EvaluateBar mLevelEb;      //评分
    @BindView(R.id.view_store_evaluate_comment_tv)
    TextView mCommentTv;    //评论内容
    @BindView(R.id.view_store_evaluate_tel_tv)
    TextView mTelTv;        //电话
    @BindView(R.id.view_store_evaluate_time_tv)
    TextView mTimeTv;       //添加时间
    @BindView(R.id.view_store_evaluate_fabulous_ck)
    CheckBox mFabulousCk;   //点赞
    @BindView(R.id.view_store_evaluate_type_iv)
    ImageView mStoreTypeIv; //品牌

    ArrayList<String> mStoreEvaluateImageUrls;//车主评论(晒图)图片url地址
    StoreEvaluateAdapter mImagesAdapter; //车主评论(晒图)的适配器


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_store_evaluate, this);
        ButterKnife.bind(this, view);

        initStoreEvaluateImg();
    }


    /**
     * 点赞回调
     */
    public void onLikesClick(CompoundButton.OnCheckedChangeListener listener) {
        mFabulousCk.setOnCheckedChangeListener(listener);
    }

    /**
     * 初始化车主评价图片
     */
    private void initStoreEvaluateImg() {
        mStoreEvaluateImageUrls = new ArrayList<>();
        mImagesRv.setLayoutManager(new GridLayoutManager(mContext, 3));
        mImagesAdapter = new StoreEvaluateAdapter();
        mImagesRv.setAdapter(mImagesAdapter);
    }


    @Override
    public void setStoreEvaluateImgs(List<String> imageUrls) {
        mStoreEvaluateImageUrls.clear();
        mStoreEvaluateImageUrls.addAll(imageUrls);
        //强制刷新
        mImagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCommentData(StoreDetailsBean.DataBean.DiscussinfoBean data) {
        //写入评分
        mLevelEb.setStarMark(Float.parseFloat(data.comment_level));
        //写入评论
        mCommentTv.setText(data.comment_content);
        //电话
        mTelTv.setText(data.user_mobile);
        //添加时间
        mTimeTv.setText(data.add_datetime);
        //赞
        mFabulousCk.setChecked(data.zandot.equals("1"));
        //分类
//        switch (data.service_cate_id){
//            case 1: //洗车
//                mStoreTypeIv.setImageResource(R.mipmap.ic_store_evaluate_carwash_label_iv);
//                break;
//            case 2:
//                mStoreTypeIv.setImageResource(R.mipmap.ic_store_evaluate_maintain_label_iv);
//                break;
//            case 3:
//                mStoreTypeIv.setImageResource(R.mipmap.ic_store_evaluate_cosmetology_label_iv);
//                break;
//            case 4:
//                mStoreTypeIv.setImageResource(R.mipmap.ic_store_evaluate_illegal_label_iv);
//                break;
//        }
        Glide.with(mContext)
                .load(data.label_url)
                .placeholder(R.mipmap.ic_store_evaluate_loading_label)
                .error(R.mipmap.ic_store_evaluate_error_label)
                .centerCrop()
                .into(mStoreTypeIv);
        //监听赞的点击
        mFabulousCk.setOnClickListener(v -> {
            mFabulousCk.setChecked(!mFabulousCk.isChecked());

            if (mOnStoreEvaluateViewListner != null)
                mOnStoreEvaluateViewListner.onLikesClick(mFabulousCk.isChecked(), data.discuss_id);
        });
        //
        setStoreEvaluateImgs(data.pl_pics);
    }

    private OnStoreEvaluateViewListner mOnStoreEvaluateViewListner;

    public void setOnStoreEvaluateViewListner(OnStoreEvaluateViewListner listner) {
        this.mOnStoreEvaluateViewListner = listner;
    }

    public interface OnStoreEvaluateViewListner {
        /**
         * 赞的点击事件
         */
        void onLikesClick(boolean bol, String discuss_id);
    }


    //车主评价晒图的适配器
    class StoreEvaluateAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LinearLayout.inflate(mContext, R.layout.item_store_evaluate_img, null);
            return new StoreEvaluateViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof StoreEvaluateViewHolder) {
                onStoreDetailsData((StoreEvaluateViewHolder) holder, position);
            }
        }

        /**
         * 添加图片适配器
         */
        private void onStoreDetailsData(StoreEvaluateViewHolder holder, int position) {
            //获取Url
            String imageUrl = mStoreEvaluateImageUrls.get(position);
            //写入图片
            Glide.with(mContext)
                    .load(imageUrl)
                    .placeholder(R.drawable.bg_image_default)
                    .error(R.drawable.bg_image_default)
                    .into(holder.mStoreEvaluateIv);

            holder.mStoreEvaluateIv.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, OrderSeeLagerImgActivity.class);
                intent.putExtra("picS", mStoreEvaluateImageUrls);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return mStoreEvaluateImageUrls.size();
        }

        class StoreEvaluateViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.item_store_evaluate_iv)
            ImageView mStoreEvaluateIv;

            public StoreEvaluateViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

        }
    }
}