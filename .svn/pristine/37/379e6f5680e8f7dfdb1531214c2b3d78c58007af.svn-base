package com.xi6666.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mr_yang on 2017/1/16.
 */

public class TimerText extends LinearLayout {
    private Timer timer;
    private TextView Vhour, Vmin, Vseconds;
    private OnTimeEndListener mOnTimeEndListener;
    private long mday, mhour, mmin, msecond;//天，小时，分钟，秒
    private boolean run = false; //是否启动了
    private Handler mHandler;

    private TimerTask task;

    public TimerText(Context context) {
        this(context, null);
    }

    public TimerText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        iniUI(context);
    }

    public void iniUI(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View myView = mInflater.inflate(R.layout.view_timertextview, null);

        Vhour = (TextView) myView.findViewById(R.id.tv_hours);
        Vmin = (TextView) myView.findViewById(R.id.tv_minutes);
        Vseconds = (TextView) myView.findViewById(R.id.tv_seconds);
        addView(myView);
    }


    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void start() {
        init();
        if (!isRun()) {
            setRun(true);
            timer.schedule(task, 1000, 1000);
        }
    }

    /**
     * 根据传进来的时间差 为textview 赋值
     *
     * @param
     */
    public void setTimes(long mday, long mhour, long mmin, long msecond) {
        this.mday = mday;
        this.mhour = mhour;
        this.mmin = mmin;
        this.msecond = msecond;


     /*   Date date = new Date(duration);
        Date date1 = new Date(1L);
        *//*mday = duration / 60000 / 60 / 24;
        mhour = (duration - mday * 6000 * 60 * 24) / 3600000;

        mmin = (duration - mhour * 6000 * 60 - mday * 3600000 * 24) / 60000;
        msecond = (duration - mmin * 60000 - mhour * 3600000 - mday * 3600000 * 24) / 60000;*//*
//需要修改，测试用
        mday = date.getDay();
        mhour = date.getHours();
        mmin = date.getMinutes();
        msecond = date.getSeconds();*/
    }

    /**
     * 倒计时计算
     */
   /* private void ComputeTime() {
        msecond--;
        if (msecond < 0) {
            mmin--;
            msecond = 59;
            if (mmin < 0) {
                mmin = 59;
                mhour--;
                if (mhour < 0) {
                    // 倒计时结束
                    mhour = 24;
                    mday--;
                }
            }
        }
    }*/

    /**
     * 倒计时计算
     */
    private void ComputeTime() {
        if (msecond == 0) {
            if (mmin == 0) {
                if (mhour == 0) {
                    if (mday == 0) {
                        //当时间归零时停止倒计时
                        run = false;
                        //停止时间计时器
                        timer.cancel();
                        if (mOnTimeEndListener != null) {
                            mOnTimeEndListener.showTimeEnd();
                        }
                        return;
                        //这里是接着倒计时一天的时间
                       /* mday=0;*/
                    } else {
                        mday--;
                    }
                    mhour = 23;
                } else {
                    mhour--;
                }
                mmin = 59;
            } else {
                mmin--;
            }
            msecond = 60;
        }

        msecond--;

    }

    //接口回调倒计时完毕
    public interface OnTimeEndListener {
        void showTimeEnd();
    }

    //设置回调接口
    public void setOnTimeEndListener(OnTimeEndListener onTimeEndListener) {
        mOnTimeEndListener = onTimeEndListener;
    }

    public void init() {

        timer = new Timer();

        mHandler = new Handler() {

        };
        task = new TimerTask() {
            @Override
            public void run() {

                mHandler.post(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        run = true;
                        ComputeTime();
                        if (mday < 0) {
                            //所有数字为0
                        /*setVisibility(View.GONE);*/

                            setRun(false);
                        }
                        Vhour.setText(mhour < 10 ? ("0" + mhour) : mhour + "");
                        Vseconds.setText(msecond < 10 ? ("0" + msecond) : msecond + "");
                        Vmin.setText(mmin < 10 ? ("0" + mmin) : mmin + "");
                    }
                });
            }
        };


    }
}
