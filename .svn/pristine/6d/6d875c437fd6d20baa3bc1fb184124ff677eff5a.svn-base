package com.xi6666.store.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xi6666.R;
import com.xi6666.multi_image_selector.MultiImageSelector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/17 下午6:09.
 * 个人公众号 ardays
 */
public class SelectImageView extends LinearLayout implements SelectImageViewImpl {

    // 图片数组
    public static final int REQUEST_IMAGE_DATA = 3929;

    private int mMaxImageNumber = 5; //设置最大拍照数量
    private boolean mShowCamera = true; //设置是否显示拍照功能
    ArrayList<String> mImageUrls; //相片数组



    @BindView(R.id.view_select_image_rv)
    RecyclerView mImageRv;  //显示图片的集合
    @BindView(R.id.view_select_image_add_iv)
    ImageView mAddImageIv;  //添加图片


    Context mContext;    //上下文
    MultiImageSelector mImageSelector; //图片选择器
    SelectImageView mSelectImageView;   //本身
    SelectImageAdapter mImageAdapter; //图片适配器

    public SelectImageView(Context context) {
        this(context, null);
    }

    public SelectImageView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public SelectImageView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_select_image, this);
        mSelectImageView = this;
        ButterKnife.bind(this, view);
        initSelectImage();
        initAddImage();
        initImage();
    }


    /**
     * 初始化选择图片
     */
    private void initSelectImage() {
        mImageSelector = MultiImageSelector.create();
        //设置是否显示拍照功能
        mImageSelector.showCamera(mShowCamera);
        //设置最大数量
        mImageSelector.count(mMaxImageNumber);
        //设置能多图做
        mImageSelector.multi();
        mImageUrls = new ArrayList<>();
        //设置显示图片的选中的数组
        mImageSelector.origin(mImageUrls);
    }

    /**
     * 初始化添加图片
     */
    private void initAddImage() {
        mAddImageIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnSelectImageListener != null) mOnSelectImageListener.addImageClick(mSelectImageView);
            }
        });
    }


    /**
     * 初始化Image对象
     */
    private void initImage() {
        //设置横向滚动的RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mImageRv.setLayoutManager(llm);

        //写入适配器
        mImageAdapter = new SelectImageAdapter(mContext, mImageUrls);
        mImageRv.setAdapter(mImageAdapter);

        //监听图片移除
        mImageAdapter.setOnSelectImageAdapterListener(new SelectImageAdapter.OnSelectImageAdapterListener() {
            @Override
            public void removeImage() {
                notifyAddImage();
            }
        });
    }


    public ArrayList<String> getImageList(){
        return mImageUrls;
    }





    @Override
    public void openSystemPhoto(Activity activity) {
        mImageSelector.start(activity, REQUEST_IMAGE_DATA);
    }
    @Override
    public void openSystemPhoto(Fragment fragment) {
        mImageSelector.start(fragment, REQUEST_IMAGE_DATA);
    }

    @Override
    public void onActivityResult(Intent data) {
        //清空数组
        mImageUrls.clear();
        //获取数组
        mImageUrls.addAll(data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT));

        notifyAddImage();

        Log.e("TAG","file--->" + mImageUrls.toString());
        //强制刷新
        mImageAdapter.notifyDataSetChanged();
    }

    /**
     * 立即刷新添加按钮
     */
    private void notifyAddImage() {
        int i = mImageUrls.size() == 5 ? GONE : VISIBLE;
        mAddImageIv.setVisibility(i);
    }


    public OnSelectImageListener mOnSelectImageListener;
    public void setOnSelectImageListener(OnSelectImageListener listener){
        this.mOnSelectImageListener = listener;
    }


    public interface OnSelectImageListener{
        /**
         * 添加图片
         */
        void addImageClick(SelectImageView view);
    }

}