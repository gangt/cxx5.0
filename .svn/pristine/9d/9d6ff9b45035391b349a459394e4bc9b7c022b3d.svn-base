package com.xi6666.illegal.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.cardbag.view.CardBagAct;
import com.xi6666.common.UserData;
import com.xi6666.html5show.view.HtmlAct;
import com.xi6666.illegal.alert.AlertView;
import com.xi6666.illegal.alert.OnItemClickListener;
import com.xi6666.illegal.bean.ProvincBean;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.illegal.other.AutoCaseTransformationMethod;
import com.xi6666.illegal.other.GsonUtils;
import com.xi6666.illegal.other.WeiZhangSelectProvinceDialog;
import com.xi6666.order.other.Utils;
import com.xi6666.utils.LogUtil;
import com.xi6666.utils.ShowDialogUitls;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class IllegalBindCardActivity extends IllegalBaseActivity implements OnItemClickListener {
    private EditText       carid;//车牌号
    private EditText       startcar;//发动机号
    private EditText       carstore;//车架号码
    private RelativeLayout save;
    private LinearLayout car;//车牌的点击弹窗
    private TextView     carno;
    private ImageView    carNoProblem;
    private String       userId;
    private String       card_id;
    private String       user_token;
    private Dialog       mLoading;
    private String[]     provinceSimpleName;
    private ProvincBean    provincBean;
    private ArrayList<ProvincBean.DataBean> resultBeans = new ArrayList<>();
    private  AutoCaseTransformationMethod mAutoCaseTransformationMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        initToolBar();
        mLoading = ShowDialogUitls.showDio(this);
        if (mLoading.isShowing())
            mLoading.dismiss();
        initView();
        initHint();
    }

    private void initView() {
        card_id = getIntent().getStringExtra("card_id");

        save = (RelativeLayout) findViewById(R.id.btn_ok_click);
        car = (LinearLayout) findViewById(R.id.ll_chocecar);
        carid = (EditText) findViewById(R.id.et_car_id);
        carno = (TextView) findViewById(R.id.tv_car_no);
        carstore = (EditText) findViewById(R.id.et_carstore);
        startcar = (EditText) findViewById(R.id.et_startcar);
        carNoProblem = (ImageView) findViewById(R.id.iv_car_no_problem);

        userId = UserData.getUserId();
        user_token = UserData.getUserToken();

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
                if (TextUtils.isEmpty(carno.getText().toString()) || TextUtils.isEmpty(carid.getText().toString())) {
                    Toast.makeText(IllegalBindCardActivity.this, "车牌号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!writeStartCar) {
                    if (TextUtils.isEmpty(startcar.getText().toString())) {
                        Toast.makeText(IllegalBindCardActivity.this, "发动机号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else if (!writeCarStore) {
                    if (TextUtils.isEmpty(carstore.getText().toString())) {
                        Toast.makeText(IllegalBindCardActivity.this, "车架号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                mLoading.show();
                Retrofit.Builder builder = new Retrofit.Builder();
                Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
                retrofit.create(NetAddress.class).bindCard(carid.getText().toString(),
                        card_id, startcar.getText().toString(), carstore.getText().toString(),
                        userId, user_token, carno.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (mLoading.isShowing()) {
                                mLoading.dismiss();
                            }
                            String string = response.body().string();
                            LogUtil.i("bindCard", string);
                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.optBoolean("success")) {
                                new AlertView("绑定成功","您的违章卡已启用!","确定",null,null,IllegalBindCardActivity.this,AlertView.Style.Alert,IllegalBindCardActivity.this).show();
                            } else {
                                Utils.make(IllegalBindCardActivity.this, jsonObject.optString("info"));
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
                        Utils.make(IllegalBindCardActivity.this, "网络异常，请稍后重试！");
                    }
                });
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 省份简称换成网格式的选择
                WeiZhangSelectProvinceDialog dialog = new WeiZhangSelectProvinceDialog(IllegalBindCardActivity.this, provinceSimpleName);
                dialog.setOnItemProvinceClickListener(new WeiZhangSelectProvinceDialog.OnItemProvinceClickListener() {
                    @Override
                    public void onItemProvince(String province) {
                        carno.setText(province);
                    }
                });
                dialog.show();
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
     * 初始化toolbar
     */
    private void initToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.bind_card_tool);
        setSupportActionBar(tb);
        findViewById(R.id.tv_problem_toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://app.xiaoxi6.com/illegal/problems";
                Intent intent = new Intent(IllegalBindCardActivity.this, HtmlAct.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        findViewById(R.id.iv_break_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showPicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Translucent_Dialog);
        final AlertDialog show = builder.show();
        View view = View.inflate(IllegalBindCardActivity.this, R.layout.dialog_show_pic, null);
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

    @Override
    public void onItemClick(Object o, int position) {
        // 进入卡券包违章卡页面
        Intent intent = new Intent(this, CardBagAct.class);
        intent.putExtra("type", "3");
        startActivity(intent);
        finish();
    }

    public static void openActivity(Activity activity, String card_id) {
        Intent intent = new Intent(activity,IllegalBindCardActivity.class);
        intent.putExtra("card_id",card_id);
        activity.startActivity(intent);
    }
}
