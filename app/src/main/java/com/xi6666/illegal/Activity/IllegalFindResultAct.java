package com.xi6666.illegal.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.xi6666.R;
import com.xi6666.carWash.view.CarWashAct;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.IllegaQuerySuccessEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.Adapter.FindResult_Adapter2;
import com.xi6666.illegal.alert.AlertView;
import com.xi6666.illegal.alert.OnItemClickListener;
import com.xi6666.illegal.bean.FindResultBean2;
import com.xi6666.illegal.bean.ProvincBean;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.network.ApiRest;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.xi6666.order.activity.GoodsOrderAffirmActivity.MY_PERMISSIONS_REQUEST_CALL_PHONE;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class IllegalFindResultAct extends IllegalBaseActivity implements OnItemClickListener {
    private RecyclerView        recyclerView;
    private TextView            mTvWeiZhang;
    private TextView            mTvFaKuan;
    private TextView            mTvKouFen;
    private TextView            mTvChePai;
    private TextView            mTvChePaiNum;
    private TextView            mTvIllegalInfo;
    private TextView            mTvQueryCityOrAdd;
    private Button              mBtnFindStore;
    private Button              mBtnUseCard;
    private LinearLayout        mLLNoWeiZhangInfo;
    private LinearLayout        mLLBottomBtn;
    private RelativeLayout      mRLIlleagleInfo;
    private FindResult_Adapter2 findResult_adapter2;
    private int                 service_cate_id;

    private AlertView           mFanKuiAlertView;
    private AlertView           mReasonAlertView;
    private AlertView           mBuyCardAlertView;
    private AlertView           mQueryAlertView;
    private String[]            reasons;

    private boolean             isCanClick;
    private String              query_id;
    private String              userId;
    private String              user_token;
    private String              car_no;
    private String              log_id_str;
    private List<FindResultBean2.DataBean.ListBean> logLists = new ArrayList<>();
    private Dialog              mLoading;
    private String              city_code;
    private String              engineno;
    private String              classno;
    private String              info;
    private String              province_code;

    private OptionsPickerView optionsPickerView;
    private List<ProvincBean.DataBean>   provinceBeans = new ArrayList<>();
    private ArrayList<String>                 provinces     = new ArrayList<>();
    private ArrayList<ArrayList<String>>      citys         = new ArrayList<>();
    private ArrayList<ArrayList<String>> citycode      = new ArrayList<>();
    private ArrayList<String> provincesCode      = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findresult);
        initToolBar();
        initView();
        initData();
        getProvinceList();
    }

    private void initView() {
        mLoading = ShowDialogUitls.showDio(this);
        if (mLoading.isShowing()) mLoading.dismiss();

        optionsPickerView = new OptionsPickerView(this);
        recyclerView = (RecyclerView) findViewById(R.id.findresult_recy);
        LinearLayoutManager linearLayoutManger = new LinearLayoutManager(IllegalFindResultAct.this);
        recyclerView.setLayoutManager(linearLayoutManger);
        mTvWeiZhang = (TextView) findViewById(R.id.tv_result_weizhang);
        mTvFaKuan = (TextView) findViewById(R.id.tv_result_fakuan);
        mTvKouFen = (TextView) findViewById(R.id.tv_result_koufen);
        mTvChePai = (TextView) findViewById(R.id.tv_result_chepai);
        mTvIllegalInfo = (TextView) findViewById(R.id.tv_illegal_info);
        mTvQueryCityOrAdd = (TextView) findViewById(R.id.tv_query_city_or_add_city);
        mTvChePaiNum = (TextView) findViewById(R.id.tv_result_chepai_num);
        mLLNoWeiZhangInfo = (LinearLayout) findViewById(R.id.ll_no_weizhang_info);
        mLLBottomBtn = (LinearLayout) findViewById(R.id.ll_bottom_btn);
        mRLIlleagleInfo = (RelativeLayout) findViewById(R.id.ll_illegal_info);
        mBtnFindStore = (Button) findViewById(R.id.btn_find_store);
        mBtnUseCard = (Button) findViewById(R.id.btn_use_illegal_card);
        mLLBottomBtn.setVisibility(View.GONE);

        reasons = new String[]{"我有违章，但车小喜暂未查询到该记录","我的违章已经销了，但车小喜仍然显示","罚款和扣分不准","违章地址不正确","拨打客服电话：400-9999-353"};

//        mFanKuiAlertView = new AlertView("反馈成功", "感谢您的反馈！我们会全力为您解决问题，尽快为您呈现正确的违章结果!", "关闭", null, null, this, AlertView.Style.Alert, null);

        mFanKuiAlertView = new AlertView("反馈成功", "感谢您的反馈！我们会全力为您解决问题，尽快为您呈现正确的违章结果!", null, new String[]{"关闭"}, null, this,
                AlertView.Style.Alert, null);

        mReasonAlertView = new AlertView(null,null,"取消",null,reasons,this, AlertView.Style.ActionSheet, this);

        mBtnFindStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 去找门店代办违章
                CarWashAct.openActivity(IllegalFindResultAct.this,4,false,"违章处理");
            }
        });

        mBtnUseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用违章卡
                HashMap<Integer, Boolean> itemSelectMap = findResult_adapter2.getItemSelectMap();
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < logLists.size(); i++) {
                    if (itemSelectMap.get(i)) {
                        // 被选中
                        buffer.append(logLists.get(i).getLog_id());
                        buffer.append(",");
                    }
                }
                String s = buffer.toString();
                if (!TextUtils.isEmpty(s)) {
                    log_id_str = s.substring(0,s.length() - 1);
                }
                Retrofit.Builder builder = new Retrofit.Builder();
                Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
                retrofit.create(NetAddress.class).canUseIllegalCard(userId,user_token,car_no,log_id_str,city_code).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (mLoading.isShowing()) mLoading.dismiss();
                        try {
                            String result = response.body().string();
                            LogUtil.i("canUseIllegalCard",result);
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.optInt("code");
                            String info = jsonObject.optString("info");
                            if (jsonObject.optBoolean("success") && code == 11) {
                                // 违章卡可以使用
                                Intent intent = new Intent(IllegalFindResultAct.this,ConfirmUseActivity.class);
                                intent.putExtra("log_id_str",log_id_str);
                                startActivity(intent);
                            } else {
                                if (code == 1 || code == 2 || code == 3 || code == 4 || code == 10) {
                                    // 去购卡
                                    mBuyCardAlertView = new AlertView("",info,"去购卡",null,null,IllegalFindResultAct.this, AlertView.Style.Alert, IllegalFindResultAct.this);
                                    mBuyCardAlertView.setCancelable(true);
                                    mBuyCardAlertView.show();
                                } else if (code == 14) {
                                    mQueryAlertView = new AlertView("",info,"修改查询",null,null,IllegalFindResultAct.this, AlertView.Style.Alert, IllegalFindResultAct.this);
                                    mQueryAlertView.setCancelable(true);
                                    mQueryAlertView.show();
                                } else {
                                    new AlertView("",info,"确定",null,null,IllegalFindResultAct.this, AlertView.Style.Alert,null).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (mLoading.isShowing()) mLoading.dismiss();
                    }
                });
            }
        });

        mTvQueryCityOrAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectQueryCity();
                optionsPickerView.show();
            }
        });

        mTvIllegalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示dialog
                mReasonAlertView.show();
            }
        });
    }

    private void selectQueryCity() {
        // 修改查询城市
        ArrayList<String> newList;
        ArrayList<String> codeList;
        ArrayList<String> pCodeList;
        for (int i = 0; i < provinceBeans.size(); i++) {
            String province = provinceBeans.get(i).getProvince();
            provinces.add(province);
            provincesCode.add(provinceBeans.get(i).getProvince_code());
            newList = new ArrayList<>();
            codeList=new ArrayList<>();
            pCodeList=new ArrayList<>();
            for (int j = 0; j < provinceBeans.get(i).getCitys().size(); j++) {
                codeList.add(provinceBeans.get(i).getCitys().get(j).getCity_code());
                newList.add(provinceBeans.get(i).getCitys().get(j).getCity_name());
                pCodeList.add(provinceBeans.get(i).getProvince_code());
            }
            citys.add(newList);
            citycode.add(codeList);
            LogUtil.d("changdu ", provinceBeans.size() + "");
            optionsPickerView = new OptionsPickerView(IllegalFindResultAct.this);

            optionsPickerView.setPicker(provinces, citys, true);
            optionsPickerView.setCyclic(false);
            //监听确定选择按钮
            optionsPickerView.setSelectOptions(0, 0);
            optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    if (citys.size() <= 0) {
                        Toast.makeText(getBaseContext(),"数据异常",Toast.LENGTH_SHORT).show();
                    }
                    //城市编码利用缓存保存起来
                    city_code = citycode.get(options1).get(option2);
                    province_code = provincesCode.get(options1);
                    mTvQueryCityOrAdd.setText(citys.get(options1).get(option2));
                    queryIllegalDetail();
                }
            });
        }
    }

    private void initData() {
        Intent intent = getIntent();
        String whoStart = intent.getStringExtra("whoStart");
        engineno = intent.getStringExtra("engineno");
        classno = intent.getStringExtra("classno");
        province_code = intent.getStringExtra("province_code");
        query_id = intent.getStringExtra("query_id");
        car_no = intent.getStringExtra("car_no");
        info = intent.getStringExtra("info");
        if (!"IllegalFindActivity".equals(whoStart)) {
            city_code = intent.getStringExtra("city_code");
            userId = UserData.getUserId();
            user_token = UserData.getUserToken();
            queryIllegalDetail();
        } else {
            FindResultBean2 bean = (FindResultBean2) intent.getSerializableExtra("data");
            int code = intent.getIntExtra("code",0);
            if (code == 14) {
                mQueryAlertView = new AlertView("",info,"修改查询",null,null,IllegalFindResultAct.this, AlertView.Style.Alert, IllegalFindResultAct.this);
                mQueryAlertView.setCancelable(true);
                mQueryAlertView.show();
            } else {
                Utils.make(IllegalFindResultAct.this,info);
            }
            if (null != bean) {
                FindResultBean2.DataBean dataBean = bean.getData();
                mTvChePai.setText(dataBean.getCarno());
                mTvChePaiNum.setText(dataBean.getCarno());
                car_no = dataBean.getCarno();
                city_code = dataBean.getCity_code();
                mTvWeiZhang.setText(dataBean.getCount() + "");
                mTvFaKuan.setText(dataBean.getMoney());
                mTvKouFen.setText(dataBean.getFen() + "");
                controlQueryCity(dataBean.getCity_name());
                service_cate_id = bean.getData().getService_cate_id();
                int size = dataBean.getList().size();
                if (size <= 0) {
                    recyclerView.setVisibility(View.GONE);
                    mLLNoWeiZhangInfo.setVisibility(View.VISIBLE);
                    mLLBottomBtn.setVisibility(View.GONE);
                } else {
                    mLLBottomBtn.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    mLLNoWeiZhangInfo.setVisibility(View.GONE);
                    logLists.clear();
                    logLists.addAll(dataBean.getList());
                    findResult_adapter2 = new FindResult_Adapter2(IllegalFindResultAct.this, dataBean.getList());
                    recyclerView.setAdapter(findResult_adapter2);
                }
            } else {
                String car_no = intent.getStringExtra("car_no");
                mTvChePai.setText(car_no);
                mTvChePaiNum.setText(car_no);
                mTvWeiZhang.setText(0 + "");
                mTvFaKuan.setText("0");
                mTvKouFen.setText(0 + "");
                recyclerView.setVisibility(View.GONE);
                mLLNoWeiZhangInfo.setVisibility(View.VISIBLE);
                mLLBottomBtn.setVisibility(View.GONE);
            }
        }
    }

    private void queryIllegalDetail() {
        if (!mLoading.isShowing()) mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        if (TextUtils.isEmpty(query_id)) {
            query_id = "0";
        }
        LogUtil.i("test",car_no + "#" + city_code + "#" + engineno + "#" + classno + "#" + province_code + "#" + query_id);
        retrofit.create(NetAddress.class).getBreak(car_no, city_code, engineno, classno, "1", userId, user_token, province_code,query_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                try {
                    String string = response.body().string();
                    LogUtil.i("FindResult", string);
                    FindResultBean2 mFindResultBean = GsonUtils.toEntityFromJson(string, FindResultBean2.class);
                    if (mFindResultBean != null) {
                        if (mFindResultBean.getData() != null) {
                            EventBus.getDefault().post(new IllegaQuerySuccessEvent("success"));
                            if (!mFindResultBean.isSuccess()) {
                                if (mFindResultBean.getCode() == 14) {
                                    mQueryAlertView = new AlertView("",mFindResultBean.getInfo(),"修改查询",null,null,IllegalFindResultAct.this, AlertView.Style.Alert, IllegalFindResultAct.this);
                                    mQueryAlertView.setCancelable(true);
                                    mQueryAlertView.show();
                                } else {
                                    Toast.makeText(IllegalFindResultAct.this, mFindResultBean.getInfo(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            mTvChePai.setText(mFindResultBean.getData().getCarno());
                            mTvChePaiNum.setText(mFindResultBean.getData().getCarno());
                            mTvWeiZhang.setText("" + mFindResultBean.getData().getCount());
                            mTvFaKuan.setText(mFindResultBean.getData().getMoney());
                            mTvKouFen.setText(mFindResultBean.getData().getFen() + "");
                            String city_name = mFindResultBean.getData().getCity_name();

                            controlQueryCity(city_name);

                            service_cate_id = mFindResultBean.getData().getService_cate_id();
                            int size = mFindResultBean.getData().getList().size();
                            if (size <= 0) {
                                mLLBottomBtn.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.GONE);
                                mLLNoWeiZhangInfo.setVisibility(View.VISIBLE);
                            } else {
                                mLLBottomBtn.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                mLLNoWeiZhangInfo.setVisibility(View.GONE);
                                logLists.clear();
                                logLists.addAll(mFindResultBean.getData().getList());
                                findResult_adapter2 = new FindResult_Adapter2(IllegalFindResultAct.this, mFindResultBean.getData().getList());
                                recyclerView.setAdapter(findResult_adapter2);
                            }
                        } else {
                            if (mFindResultBean.getCode() == 14) {
                                mQueryAlertView = new AlertView("",mFindResultBean.getInfo(),"修改查询",null,null,IllegalFindResultAct.this, AlertView.Style.Alert, IllegalFindResultAct.this);
                                mQueryAlertView.setCancelable(true);
                                mQueryAlertView.show();
                            } else {
                                Toast.makeText(IllegalFindResultAct.this, mFindResultBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                            mTvChePai.setText(car_no);
                            mTvChePaiNum.setText(car_no);
                            recyclerView.setVisibility(View.GONE);
                            mLLNoWeiZhangInfo.setVisibility(View.VISIBLE);
                            mLLBottomBtn.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                Toast.makeText(IllegalFindResultAct.this, "获取违章数据异常", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void controlQueryCity(String city_name) {
        mTvQueryCityOrAdd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);  // 下划线
        mTvQueryCityOrAdd.getPaint().setAntiAlias(true);  // 抗锯齿
        if (TextUtils.isEmpty(city_name)) {
            mTvQueryCityOrAdd.setText("点击添加");
            isCanClick = true;
        } else {
            mTvQueryCityOrAdd.setText(city_name);
            isCanClick = false;
        }
    }

    private void initToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.ac_tb);
        setSupportActionBar(tb);


        findViewById(R.id.result_back).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(Object o, int position) {
        if (o == mReasonAlertView && position != AlertView.CANCELPOSITION ) {
            if (position != 4) {
                // 向后台发送反馈意见
                submitFeedBack(reasons[position]);
            } else {
                // 拨打客服电话
                Utils.callTel("4009999353", this);
            }

        } else if (o == mBuyCardAlertView && position == AlertView.CANCELPOSITION) {
            // 去购卡
            HtmlAct.unsealActivity(this, ApiRest.ILLEGA + "?get_device_type=android" + "&user_id=" + UserData.getUserId()
                    + "&user_token=" + UserData.getUserToken());
        } else if (o == mQueryAlertView && position == AlertView.CANCELPOSITION) {
            // 添加查询
            Intent intent = new Intent(this,IllegalFindActivity.class);
            intent.putExtra("car_no",car_no);
            intent.putExtra("whoStart","IllegalFindResultAct");
            startActivity(intent);
        }
    }

    private void submitFeedBack(String reason) {
        if (!mLoading.isShowing()) mLoading.show();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
        if (TextUtils.isEmpty(query_id)) {
            query_id = "0";
        }
        retrofit.create(NetAddress.class).commitFeedBack(userId,user_token,reason,query_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                try {
                    String result = response.body().string();
                    LogUtil.i("commitFeedBack",result);
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.optBoolean("success")) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mFanKuiAlertView.show();
                            }
                        },1000);
                    } else {
                        Utils.make(getBaseContext(),jsonObject.optString("info"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                Toast.makeText(getBaseContext(), "反馈失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProvinceList() {
        if (!mLoading.isShowing()) mLoading.show();
        Retrofit build = new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build();
        NetAddress getData = build.create(NetAddress.class);
        getData.getProvince().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                try {
                    String string = response.body().string();
                    LogUtil.i("province_city", "省份获取的数据是==" + string);
                    ProvincBean provincBean = GsonUtils.toEntityFromJson(string, ProvincBean.class);
                    if (provincBean == null || provincBean.getData() == null || !provincBean.isSuccess()) {
                        Toast.makeText(getBaseContext(), "数据异常", Toast.LENGTH_SHORT).show();
                    } else {
                        List<ProvincBean.DataBean> data = provincBean.getData();
                        provinceBeans.clear();
                        provinceBeans.addAll(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mLoading.isShowing())
                    mLoading.dismiss();
                Toast.makeText(getBaseContext(), "获取违章查询城市数据失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    // 拨号：激活系统的拨号组件
                    Intent intent = new Intent(); // 意图对象：动作 + 数据
                    intent.setAction(Intent.ACTION_CALL); // 设置动作
                    Uri uri = Uri.parse("tel:" + "4009999353"); // 设置数据
                    intent.setData(uri);
                    startActivity(intent); // 激活Activity组件
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
