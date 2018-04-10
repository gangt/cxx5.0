package com.xi6666.illegal.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.eventbus.IllegaQuerySuccessEvent;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.bean.FindResultBean2;
import com.xi6666.illegal.bean.ProvincBean;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.AutoCaseTransformationMethod;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.PreferenceUtils;
import com.xi6666.illegal.other.StringTools;
import com.xi6666.illegal.other.WeiZhangSelectProvinceDialog;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.xi6666.R.id.car_no;


/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class IllegalFindActivity extends IllegalBaseActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView       mycity;//省市
    private EditText       carid;//车牌号
    private EditText       startcar;//发动机号
    private EditText       carstore;//车架号码
    private RelativeLayout save;
    private LinearLayout   picker;
    private ProvincBean    provincBean;
    private ArrayList<ProvincBean.DataBean> resultBeans = new ArrayList<>();
    private LinearLayout car;//车牌的点击弹窗
    private TextView     carno;//车牌简称
    private ImageView    carNoProblem;
    private String       lastcarno;//车牌号
    private String       strCityCode;
    private String       strProvinceCode;
    private String       mProvince;
    private String       mCity;
    private int is_save = 0;
    private CheckBox     mRemberCar;
    private LinearLayout mLLIsSave;
    private String       query_city;
    private String       userId;
    private String       user_token;
    private Dialog       mLoading;
    private boolean      isCan = true;
    private String[]     provinceSimpleName;
    private  AutoCaseTransformationMethod mAutoCaseTransformationMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break);
        initToolBar();
        mLoading = ShowDialogUitls.showDio(this);
        if (mLoading.isShowing())
            mLoading.dismiss();
        initView();
        init();
        initHint();
    }

    private void init() {
        String whoStart = getIntent().getStringExtra("whoStart");
        if ("IllegalFindResultAct".equals(whoStart)) {
            isCan = false;
            String car_no = getIntent().getStringExtra("car_no");
            LogUtil.i("car_no",car_no);
            if (!TextUtils.isEmpty(car_no)) {
                String jc = car_no.substring(0, 1);
                if (!TextUtils.isEmpty(jc)){
                    carno.setText(jc);
                    car.setEnabled(false);
                }
                String num = car_no.substring(1, car_no.length());
                if (!TextUtils.isEmpty(num)) {
                    carid.setText(num);
                    carid.setEnabled(false);
                }

            }
        }
    }

    private void initHint() {
        Retrofit build = new Retrofit.Builder().baseUrl(NetAddress.baseUrl).build();
        NetAddress getData = build.create(NetAddress.class);
        getData.getProvince().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    LogUtil.i("province_city", "省份获取的数据是==" + string);
                    provincBean = GsonUtils.toEntityFromJson(string, ProvincBean.class);
                    if (provincBean == null || provincBean.getData() == null || !provincBean.isSuccess()) {
                        Toast.makeText(getBaseContext(), "数据异常", Toast.LENGTH_SHORT).show();
                    } else {
                        List<ProvincBean.DataBean> data = provincBean.getData();
                        resultBeans.addAll(data);
                        provinceSimpleName = new String[resultBeans.size()];
                        for (ProvincBean.DataBean resultBean : resultBeans) {
                            List<ProvincBean.DataBean.CitysBean> citys = resultBean.getCitys();
                            for (ProvincBean.DataBean.CitysBean city : citys) {
                                if (city.getCity_name().equals(mCity)) {
                                    setCarNoHint(city);
                                }
                            }
                        }
                        for (int i = 0; i < resultBeans.size(); i++) {
                            provinceSimpleName[i] = getProvinceSimpleName(resultBeans.get(i).getProvince());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void initView() {
        save = (RelativeLayout) findViewById(R.id.save_click);
        mycity = (TextView) findViewById(R.id.city);
        picker = (LinearLayout) findViewById(R.id.city_picker);
        car = (LinearLayout) findViewById(R.id.chocecar);
        carid = (EditText) findViewById(R.id.car_id);
        carno = (TextView) findViewById(car_no);
        carstore = (EditText) findViewById(R.id.carstore);
        startcar = (EditText) findViewById(R.id.startcar);
        carNoProblem = (ImageView) findViewById(R.id.car_no_problem);
        mRemberCar = (CheckBox) findViewById(R.id.is_save);
        mRemberCar.setOnCheckedChangeListener(this);
        mLLIsSave = (LinearLayout) findViewById(R.id.ll_is_save);
        mLLIsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRemberCar.toggle();
            }
        });

        // 测试
        userId = UserData.getUserId();
        user_token = UserData.getUserToken();
        if (TextUtils.isEmpty(userId)) {
            mLLIsSave.setVisibility(View.GONE);
            is_save = 0;
        } else {
            mLLIsSave.setVisibility(View.VISIBLE);
        }

        mAutoCaseTransformationMethod = new AutoCaseTransformationMethod();
        carid.setTransformationMethod(mAutoCaseTransformationMethod);
        carstore.setTransformationMethod(mAutoCaseTransformationMethod);
        startcar.setTransformationMethod(mAutoCaseTransformationMethod);

        /**
         *违章查询保存点击
         */
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("请选择查询地点".equals(mycity.getText().toString())) {
                    Toast.makeText(IllegalFindActivity.this, "查询地点不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(carno.getText().toString()) || TextUtils.isEmpty(carid.getText().toString())) {
                    Toast.makeText(IllegalFindActivity.this, "车牌号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!writeStartCar) {
                    if (TextUtils.isEmpty(startcar.getText().toString())) {
                        Toast.makeText(IllegalFindActivity.this, "发动机号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else if (!writeCarStore) {
                    if (TextUtils.isEmpty(carstore.getText().toString())) {
                        Toast.makeText(IllegalFindActivity.this, "车架号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (mRemberCar.isChecked()) {
                    is_save = 1;
                } else {
                    is_save = 0;
                }
                mLoading.show();
                strCityCode = PreferenceUtils.getString(IllegalFindActivity.this, StringTools.CITY_CODE);
                strProvinceCode = PreferenceUtils.getString(IllegalFindActivity.this, StringTools.PROVINCE_CODE);
                Retrofit.Builder builder = new Retrofit.Builder();
                Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
                retrofit.create(NetAddress.class).getBreak(carno.getText().toString() + carid.getText().toString(),
                        strCityCode, startcar.getText().toString(), carstore.getText().toString(),
                        is_save + "", userId, user_token, strProvinceCode,"0").enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                            String string = response.body().string();
                            LogUtil.i("FindResult", string);
                            FindResultBean2 mFindResultBean = GsonUtils.toEntityFromJson(string, FindResultBean2.class);
                            if (mFindResultBean != null) {
                                if (mFindResultBean.isSuccess()) {
                                    Intent intent = new Intent(IllegalFindActivity.this, IllegalFindResultAct.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("data", mFindResultBean);
                                    intent.putExtra("code",mFindResultBean.getCode());
                                    intent.putExtra("car_no", carno.getText().toString() + carid.getText().toString());
                                    intent.putExtras(bundle);
                                    intent.putExtra("whoStart", "IllegalFindActivity");
                                    intent.putExtra("info", mFindResultBean.getInfo());
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(IllegalFindActivity.this, mFindResultBean.getInfo(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(IllegalFindActivity.this, IllegalFindResultAct.class);
                                    intent.putExtra("whoStart", "IllegalFindActivity");
                                    intent.putExtra("car_no", carno.getText().toString() + carid.getText().toString());
                                    intent.putExtra("engineno",startcar.getText().toString() + carid.getText().toString());
                                    intent.putExtra("classno", carstore.getText().toString() + carid.getText().toString());
                                    intent.putExtra("info", mFindResultBean.getInfo());
                                    intent.putExtra("code", mFindResultBean.getCode());
                                    intent.putExtra("province_code", strProvinceCode);
                                    startActivity(intent);
                                }
                                if (is_save == 1) {
                                    EventBus.getDefault().post(new IllegaQuerySuccessEvent("success"));
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (mLoading.isShowing()) {
                            mLoading.dismiss();
                        }
                        Utils.make(IllegalFindActivity.this, "网络异常，请稍后重试！");
                    }
                });
            }
        });

        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultBeans.size() > 0)
                    initData();
                else
                    Utils.make(IllegalFindActivity.this, "网络异常，请稍后重试！");
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 省份简称换成网格式的选择
                WeiZhangSelectProvinceDialog dialog = new WeiZhangSelectProvinceDialog(IllegalFindActivity.this, provinceSimpleName);
                dialog.setOnItemProvinceClickListener(new WeiZhangSelectProvinceDialog.OnItemProvinceClickListener() {
                    @Override
                    public void onItemProvince(String province) {
                        carno.setText(province);
                    }
                });
                dialog.show();
                lastcarno = carno.getText().toString() + carid.getText().toString();
            }
        });

        carNoProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicDialog();
            }


        });

    }


    /**
     * 省份数据获取
     */
    private void initData() {
        getProvinc(mycity, resultBeans, carno, startcar, carstore,isCan);
        optionsPickerView.show();

    }

    private void setCarNoHint(ProvincBean.DataBean.CitysBean citysBean) {
        String engine = citysBean.getEngine();
        String engineno = citysBean.getEngineno();
        String classa = citysBean.getClassa();
        String classno = citysBean.getClassno();
        if ("0".equals(engine)) {
            startcar.setHint("选填");
            writeStartCar = true;
        } else {
            if ("0".equals(engineno)) {
                startcar.setHint("请输入完整的发动机号");
            } else {
                startcar.setHint("请输入" + engineno + "位" + "发动机号");
            }
        }

        if ("0".equals(classa)) {
            carstore.setHint("选填");
            writeCarStore = true;
        } else {
            if ("0".equals(classno)) {
                carstore.setHint("请输入完整的车架号");
            } else {
                carstore.setHint("请输入" + classno + "位" + "车架号");
            }
        }

        startcar.setHint("请输入完整的发动机号");
        carstore.setHint("请输入完整的车架号");
        writeStartCar = false;
        writeCarStore = false;
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.break_tool);
        setSupportActionBar(tb);
        findViewById(R.id.problem_toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://app.xiaoxi6.com/illegal/problems";
                Intent intent = new Intent(IllegalFindActivity.this, HtmlAct.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        findViewById(R.id.break_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showPicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Translucent_Dialog);
        final AlertDialog show = builder.show();
        View view = View.inflate(IllegalFindActivity.this, R.layout.dialog_show_pic, null);
        show.setContentView(view);
        view.findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show.isShowing()) {
                    show.dismiss();
                }
            }
        });
        WindowManager.LayoutParams params = show.getWindow().getAttributes();
        params.dimAmount = 0.0f;
        show.getWindow().setAttributes(params);
        show.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            is_save = 1;
        } else {
            is_save = 0;
        }
    }
}
