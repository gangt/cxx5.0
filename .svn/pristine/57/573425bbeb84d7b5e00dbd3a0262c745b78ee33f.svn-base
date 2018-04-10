package com.xi6666.address;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.LocalSearchInfo;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.xi6666.MainActivity;
import com.xi6666.R;
import com.xi6666.address.fragment.mvp.bean.CityGeographyBean;
import com.xi6666.carWash.base.BaseToolbarView;
import com.xi6666.network.ApiRest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 创建者 sunsun
 * 时间 16/11/14 下午2:45.
 * 个人公众号 ardays
 */

public class SunTestAct extends BaseToolbarView {


    Button mBtn;

    EditText mEt;
    TextView mTv;
    ImageView mXuanzhuan;

    @Override
    public String title() {
        return "孙孙测试";
    }

    @Override
    public int mainResId() {
        return R.layout.activity_sun_test;
    }

    @Override
    public void uiCreate() {
        SDKInitializer.initialize(getApplicationContext());
        super.uiCreate();
    }

    @Override
    public void setUp(View view) {
        mEt = (EditText) view.findViewById(R.id.test_et);
        mBtn = (Button) view.findViewById(R.id.test_btn);
        mTv = (TextView) view.findViewById(R.id.test_tv);
        mXuanzhuan = (ImageView) view.findViewById(R.id.xuanzhuan);

        Animation orperatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
        LinearInterpolator lin = new LinearInterpolator();
        orperatingAnim.setInterpolator(lin);
        mXuanzhuan.setAnimation(orperatingAnim);


        mBtn.setOnClickListener(v -> {
            List<String> location = CityGeographyBean.getLocation(mEt.getText().toString());
            make(location.toString());
        });

    }

    //第二步，创建地理编码检索监听者；
    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
                Log.e("TAG", "数据");
            }
            //获取地理编码结果
            make(result.getLocation().latitude + "<---data");
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
                Log.e("TAG", "数据");
            }
            //获取反向地理编码结果
            make(result.getLocation().latitude + "<---data");
        }
    };


    private void click() {
    }
}
