package com.xi6666.address.fragment.mvp.bean;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xi6666.R;
import com.xi6666.utils.AssetsUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/24 下午6:24.
 * 个人公众号 ardays
 */

public class AddressInitTask extends AsyncTask<String, Void, ArrayList<CxxAdddressPicker.Province>> {
    private Activity activity;
    private ProgressDialog dialog;
    private String address_name;
    private boolean hideCounty=false;

    /**
     * 初始化为不显示区县的模式
     * @param activity
     * @param hideCounty   is hide County
     */
    public AddressInitTask(Activity activity,boolean hideCounty) {
        this.activity = activity;
        this.hideCounty=hideCounty;
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    public AddressInitTask(Activity activity) {
        this.activity = activity;
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }
    @Override
    protected ArrayList<CxxAdddressPicker.Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    address_name = params[0];
                    break;
                default:
                    break;
            }
        }
        ArrayList<CxxAdddressPicker.Province> data = new ArrayList<>();
        try {
            String json = AssetsUtils.readText(activity, "city.json");
            Log.e("TAG","json数据是:" + json);
            Gson gson = new Gson();
//            data.addAll(JSON.parseArray(json, CxxAdddressPicker.Province.class));
            Type type = new TypeToken<ArrayList<CxxAdddressPicker.Province>>(){}.getType();
            data = gson.fromJson(json,type);
            Log.e("TAG","data--->" + data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    @Override
    protected void onPostExecute(ArrayList<CxxAdddressPicker.Province> result) {
        dialog.dismiss();
        if (result.size() > 0) {
            CxxAdddressPicker picker = new CxxAdddressPicker(activity, result);
            picker.setHideCounty(hideCounty);
            picker.setItemString(address_name);
            picker.setOnAddressPickListener(new CxxAdddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(String provice,String city,String area, String provice_id, String city_id,String area_id) {
                    TextView tv = (TextView)activity.findViewById(R.id.add_goods_address_tv);
                    if (area_id==null){
                        tv.setText(provice);
                    } else {
                        List<String> datas = new ArrayList<>();
                        datas.add(provice_id);
                        datas.add(city_id);
                        datas.add(area_id);
                        tv.setTag(datas);
                        tv.setText(provice + city + area);
                    }
                }
            });
            picker.show();
        } else {
            Toast.makeText(activity, "数据初始化失败", Toast.LENGTH_SHORT).show();
        }
    }

}
