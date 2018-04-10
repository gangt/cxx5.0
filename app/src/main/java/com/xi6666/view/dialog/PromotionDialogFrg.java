package com.xi6666.view.dialog;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xi6666.R;
import com.xi6666.app.BaseApplication;
import com.xi6666.app.SuperFrgm;
import com.xi6666.app.di.componets.AppComponets;
import com.xi6666.app.di.componets.DaggerApiComponent;
import com.xi6666.app.di.componets.DaggerAppComponets;
import com.xi6666.app.di.modules.ApiModule;
import com.xi6666.app.di.modules.AppModule;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.common.UserData;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.eventbus.CouponSuccessEvent;
import com.xi6666.network.ApiRest;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PromotionDialogFrg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PromotionDialogFrg extends SuperFrgm {


    @BindView(R.id.iv_item_cancle)
    ImageView mIvItemCancle;
    @BindView(R.id.lv_item_popu)
    ListView mLvItemPopu;
    @BindView(R.id.tv_item_receive)
    TextView mTvItemReceive;
    private MyAdapter mMyAdapter;
    private List<AddOilPopuBean.DataBean.CouponListBean> mCouponListBeen;
    @Inject
    ApiRest mApiRest;

    public static PromotionDialogFrg newInstance(List<AddOilPopuBean.DataBean.CouponListBean> param1) {
        PromotionDialogFrg fragment = new PromotionDialogFrg();
        Bundle args = new Bundle();
        args.putSerializable("pram", (Serializable) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCouponListBeen = (List<AddOilPopuBean.DataBean.CouponListBean>) getArguments().getSerializable("pram");
        }
        AppComponets build = DaggerAppComponets.builder().
                apiModule(new ApiModule(BaseApplication.getApplication())).
                appModule(new AppModule(BaseApplication.getApplication())).build();
        DaggerApiComponent.builder().appComponets(build).build().Inject(this);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_addoil_promotion;
    }

    @Override
    protected void init() {
        mMyAdapter = new MyAdapter();
        mLvItemPopu.setAdapter(mMyAdapter);
    }

    @OnClick({R.id.tv_item_receive, R.id.iv_item_cancle})
    public void viewOnclick(View view) {
        switch (view.getId()) {
            case R.id.tv_item_receive:
                mApiRest.receiveAddOilCard(UserData.getUserId(), UserData.getUserToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResponseBody>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    String string = responseBody.string();
                                    JSONObject jsonObject = new JSONObject(string);

                                    if (jsonObject.getBoolean("success")) {
                                        //view.receiveSuccess(jsonObject.getString("data"));
                                        EventBus.getDefault().post(new CouponSuccessEvent(jsonObject.getString("data")));
                                    } else {
                                        // view.showToast(jsonObject.getString("info"));
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case R.id.iv_item_cancle:
                mActivity.finish();
                break;
        }
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCouponListBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Context context = parent.getContext();
            View inflate = LayoutInflater.from(context).inflate(R.layout.addoil_promotion_item, null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_addoil_popu);
            TextView name = (TextView) inflate.findViewById(R.id.txt_addoil_popu_name);
            TextView type = (TextView) inflate.findViewById(R.id.txt_addoil_popu_name);

            name.setText(mCouponListBeen.get(position).getCoupon_name());
            textView.setText(mCouponListBeen.get(position).getCoupon_money());
            type.setText(mCouponListBeen.get(position).getCoupon_info());
            return inflate;
        }
    }
}
