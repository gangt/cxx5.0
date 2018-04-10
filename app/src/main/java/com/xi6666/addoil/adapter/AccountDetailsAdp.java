package com.xi6666.addoil.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xi6666.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Mr_yang on 2016/8/26.
 */
public class AccountDetailsAdp extends BaseAdapter {
    private Context mContext;
    private List<String> moneyS;
    private List<String> timeS;
    private int mMoney;
    private int mDiscount;


    public AccountDetailsAdp(int mMoney, int mDiscount, Context mContext) {
        this.mMoney = mMoney;
        this.mDiscount = mDiscount;
        this.mContext = mContext;
        initData();
    }

    private void initData() {
        moneyS = new ArrayList<>();
        timeS = new ArrayList<>();
        if (mDiscount == 1) {
            moneyS.add(mMoney + "元" + "(" + 1 + "/" + 1 + ")");
            timeS.add("购买后2小时");
        } else {
            getData(mDiscount);
        }
       /* if (mDiscount == 2) {
            getData(3);
        }
        if (mDiscount == 3) {
            getData(6);
        }
        if (mDiscount == 4) {
            getData(12);
        }*/
    }

    private void getData(int i) {
        int y = 1;
        int yearCount = 1;
        for (int x = 0; x < i; x++) {
            moneyS.add(mMoney + "(" + (x + 1) + "/" + i + ")");
            if (x == 0) {
                timeS.add("购买后2小时内");
            } else {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                Log.d("===", "year==" + year + "\nmonth==" + month + "\nday==" + day);
                //判断天数是否超过28
                if (day <= 28) {
                    if ((month + x + 1) > 12) {
                        if (y > 12) {
                            yearCount++;
                            y = 1;
                        }
                        //超过一年
                        if (y < 10) {
                            //判断天数是否超过10
                            if (day < 10) {
                                timeS.add((year + yearCount) + "-" + ("0" + y) + "-" + "0" + day);
                            } else {
                                timeS.add((year + yearCount) + "-" + ("0" + y) + "-" + day);
                            }
                            y++;
                        } else {
                            //判断天数是否超过10
                            if (day < 10) {
                                timeS.add((year + yearCount) + "-" + (y) + "-" + "0" + day);
                            } else {
                                timeS.add((year + yearCount) + "-" + (y) + "-" + day);
                            }
                            y++;
                        }
                    } else {
                        //不超过一年
                        if ((month + x + 1) < 10) {
                            //月份在10个月以下
                            if (day < 10) {
                                timeS.add(year + "-" + ("0" + (month + x + 1)) + "-" + "0" + day);
                            } else {
                                timeS.add(year + "-" + ("0" + (month + x + 1)) + "-" + day);
                            }
                        } else {
                            if (day < 10) {
                                timeS.add(year + "-" + (month + x + 1) + "-" + "0" + day);
                            } else {
                                timeS.add(year + "-" + (month + x + 1) + "-" + day);
                            }
                        }
                    }
                } else {
                    if ((month + x + 1) > 12) {
                        if (y > 12) {
                            yearCount++;
                            y = 1;
                        }
                        //判断月份是否小于10
                        if (y < 10) {
                            timeS.add((year + yearCount) + "-" + "0" + (y) + "-" + 28);
                        } else {
                            timeS.add((year + yearCount) + "-" + (y) + "-" + 28);
                        }
                        y++;
                    } else {
                        if ((month + x + 1) < 10) {
                            timeS.add(year + "-" + "0" + (month + x + 1) + "-" + 28);
                        } else {
                            timeS.add(year + "-" + (month + x + 1) + "-" + 28);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getCount() {
        return moneyS.size();
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
        View view = View.inflate(mContext, R.layout.rechargelist_item, null);
        TextView listMoney = (TextView) view.findViewById(R.id.rl_tv_money);
        TextView listTime = (TextView) view.findViewById(R.id.rl_tv_time);
        listMoney.setText(moneyS.get(position));
        listTime.setText(timeS.get(position));
        return view;
    }
}