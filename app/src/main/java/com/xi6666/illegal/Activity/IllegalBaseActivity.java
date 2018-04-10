package com.xi6666.illegal.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.xi6666.app.SuperAct;
import com.xi6666.illegal.other.PreferenceUtils;
import com.xi6666.illegal.other.StringTools;
import com.xi6666.illegal.bean.ProvincBean;
import com.xi6666.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr_yang on 2016/6/13.
 */
public class IllegalBaseActivity extends SuperAct {
    protected OptionsPickerView optionsPickerView;
    private ArrayList<String> provice;
    private ArrayList<String> proviceCode;
    private ArrayList<ArrayList<String>> citys;
    private ArrayList<ArrayList<String>> citycode;
    private ArrayList<String> carlist;
    private String cityName = "";
    protected boolean writeStartCar;
    protected boolean writeCarStore;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     * 地区选择
     * add: 返回选中的城市对象 (Huang Yingde)
     * @param choceProvinc
     * @param provincBeans
     */
    protected void getProvinc(final TextView choceProvinc, final ArrayList<ProvincBean.DataBean> provincBeans, final TextView carno, final EditText engino, final EditText classa,boolean iscan) {
        provice = new ArrayList<>();
        proviceCode = new ArrayList<>();
        citys = new ArrayList<>();
        citycode=new ArrayList<>();
        ArrayList<String> newList;
        ArrayList<String> codeList;
        for (int i = 0; i < provincBeans.size(); i++) {
            final String province = provincBeans.get(i).getProvince();
            final String province_code = provincBeans.get(i).getProvince_code();
            provice.add(province);
            proviceCode.add(province_code);
            newList = new ArrayList<String>();
            codeList=new ArrayList<>();
            for (int j = 0; j < provincBeans.get(i).getCitys().size(); j++) {
                final String citys=provincBeans.get(i).getCitys().get(j).getCity_name();
                newList.add(citys);
                codeList.add(provincBeans.get(i).getCitys().get(j).getCity_code());
            }
            citys.add(newList);
            citycode.add(codeList);
            LogUtil.d("changdu ", provincBeans.size() + "");
            optionsPickerView = new OptionsPickerView(IllegalBaseActivity.this);

            optionsPickerView.setPicker(provice, citys, true);
            optionsPickerView.setCyclic(false);
            //监听确定选择按钮
            optionsPickerView.setSelectOptions(0, 0);
            optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    if (citys.size() <= 0) {
                        Toast.makeText(getBaseContext(),"数据异常",Toast.LENGTH_SHORT).show();
                    }
                    choceProvinc.setText(provice.get(options1) + "," + citys.get(options1).get(option2));
                    LogUtil.d("---", "xuan" + citys.get(options1).get(option2));
                    cityName = citys.get(options1).get(option2);
                    setHint(engino,classa,provincBeans,cityName);
                    if (iscan) {
                        carno.setText(getProvinceSimpleName(provice.get(options1)));
                    }
                    //城市编码利用缓存保存起来
                    PreferenceUtils.setString(IllegalBaseActivity.this, StringTools.CITY_CODE, citycode.get(options1).get(option2));
                    PreferenceUtils.setString(IllegalBaseActivity.this, StringTools.PROVINCE_CODE, proviceCode.get(options1));
                    LogUtil.d("---", citycode.get(options1).get(option2));
                }
            });
        }

    }

    /**
     * 车牌选择框,本地数据
     *
     * @param mInviteTime
     */
    protected void getCarPai(final TextView mInviteTime) {
        optionsPickerView = new OptionsPickerView(this);
        carlist = new ArrayList<>();
        carlist.add("京");
        carlist.add("津");
        carlist.add("沪");
        carlist.add("渝");
        carlist.add("冀");
        carlist.add("豫");
        carlist.add("云");
        carlist.add("黑");
        carlist.add("湘");
        carlist.add("皖");
        carlist.add("鲁");
        carlist.add("新");
        carlist.add("苏");
        carlist.add("浙");
        carlist.add("赣");
        carlist.add("鄂");
        carlist.add("桂");
        carlist.add("甘");
        carlist.add("蒙");
        carlist.add("陕");
        carlist.add("吉");
        carlist.add("闽");
        carlist.add("贵");
        carlist.add("粤");
        carlist.add("青");
        carlist.add("藏");
        carlist.add("川");
        carlist.add("宁");
        carlist.add("贵");
        carlist.add("琼");
        optionsPickerView.setPicker(carlist);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setTitle("选择车牌");
        //监听确定选择按钮
        optionsPickerView.setSelectOptions(0, 0);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                mInviteTime.setText(carlist.get(options1));
            }
        });
    }

    /**
     * @param
     *   provinceName 要查询的省份直辖市
     * @return
     *   对应省份或直辖市的简称,没有则返回""
     */
    protected String getProvinceSimpleName(String provinceName) {
        Map<String,String> provinceMap = new HashMap<>();
        provinceMap.put("北京","京");
        provinceMap.put("天津","津");
        provinceMap.put("河北","冀");
        provinceMap.put("山西","晋");
        provinceMap.put("内蒙古","蒙");
        provinceMap.put("辽宁","辽");
        provinceMap.put("吉林","吉");
        provinceMap.put("黑龙江","黑");
        provinceMap.put("上海","沪");
        provinceMap.put("浙江","浙");
        provinceMap.put("安徽","皖");
        provinceMap.put("江苏","苏");
        provinceMap.put("福建","闽");
        provinceMap.put("江西","赣");
        provinceMap.put("山东","鲁");
        provinceMap.put("河南","豫");
        provinceMap.put("湖北","鄂");
        provinceMap.put("湖南","湘");
        provinceMap.put("广东","粤");
        provinceMap.put("广西","桂");
        provinceMap.put("海南","琼");
        provinceMap.put("重庆","渝");
        provinceMap.put("四川","川");
        provinceMap.put("贵州","贵");
        provinceMap.put("云南","云");
        provinceMap.put("西藏","藏");
        provinceMap.put("陕西","陕");
        provinceMap.put("甘肃","甘");
        provinceMap.put("青海","青");
        provinceMap.put("宁夏","宁");
        provinceMap.put("新疆","新");
        provinceMap.put("香港","港");
        provinceMap.put("澳门","澳");
        provinceMap.put("台湾","台");

        // 遍历map集合
        for (Map.Entry<String,String> entry : provinceMap.entrySet()) {
            if (entry.getKey().equals(provinceName)) {
                return entry.getValue();
            }
        }
        return "";
    }

    private void setHint(EditText engino, EditText classa, List<ProvincBean.DataBean> provincBeans, String cityName) {
        for (ProvincBean.DataBean resultBean : provincBeans) {
            ArrayList<ProvincBean.DataBean.CitysBean> citys = (ArrayList<ProvincBean.DataBean.CitysBean>) resultBean.getCitys();
            for (ProvincBean.DataBean.CitysBean city_ : citys) {
                if (city_.getCity_name().equals(cityName)) {
                    setCarNoHint(city_,engino,classa);
                }
            }
        }
    }

    private void setCarNoHint(ProvincBean.DataBean.CitysBean citysBean, EditText etEngino, EditText etClassa) {
        String engine = citysBean.getEngine();
        String engineno = citysBean.getEngineno();
        String classa = citysBean.getClassa();
        String classno = citysBean.getClassno();
        if ("0".equals(engine)) {
            etEngino.setHint("选填");
            writeStartCar = true;
        } else {
            if ("0".equals(engineno)) {
                etEngino.setHint("请输入完整的发动机号");
            } else {
                etEngino.setHint("请输入发动机号后" + engineno + "位");
            }
        }

        if ("0".equals(classa)) {
            etClassa.setHint("选填");
            writeCarStore = true;
        } else {
            if ("0".equals(classno)) {
                etClassa.setHint("请输入完整的车架号");
            } else {
                etClassa.setHint("请输入车架号后" + classno + "位");
            }
        }
        etEngino.setHint("请输入完整的发动机号");
        etClassa.setHint("请输入完整的车架号");
        writeStartCar = false;
        writeCarStore = false;
    }

}
