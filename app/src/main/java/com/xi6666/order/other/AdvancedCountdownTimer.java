package com.xi6666.order.other;

import android.os.Handler;
import android.os.Message;
public abstract class AdvancedCountdownTimer {
    private static final int MSG_RUN = 1;
    private final long mCountdownInterval;// 定时间隔，以毫秒计
    private long mTotalTime;// 定时时间
    private long mRemainTime;// 剩余时间

    // 构造函数
    public AdvancedCountdownTimer(long millisInFuture, long countDownInterval) {
        mTotalTime = millisInFuture;
        mCountdownInterval = countDownInterval;
        mRemainTime = millisInFuture;
    }

    // 取消计时
    public final void cancel() {
        mHandler.removeMessages(MSG_RUN);
    }

    // 重新开始计时
    public final void resume() {
        mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_RUN));
    }

    // 暂停计时
    public final void pause() {
        mHandler.removeMessages(MSG_RUN);
    }

    // 开始计时
    public synchronized final AdvancedCountdownTimer start() {
        if (mRemainTime <= 0) {// 计时结束后返回
            onFinish();
            return this;
        }
        // 设置计时间隔
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_RUN),
                mCountdownInterval);
        return this;
    }

    public abstract void onTick(long millisUntilFinished, int percent); // 计时中

    public abstract void onFinish();// 计时结束

    // 通过handler更新android UI，显示定时时间
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (AdvancedCountdownTimer.this) {
                if (msg.what == MSG_RUN) {
                    mRemainTime = mRemainTime - mCountdownInterval;

                    if (mRemainTime <= 0)
                    {
                        onFinish();
                    }
                    else if (mRemainTime < mCountdownInterval)
                    {
                        sendMessageDelayed(obtainMessage(MSG_RUN), mRemainTime);
                    }
                    else
                    {

                        onTick(mRemainTime, new Long(100
                                * (mTotalTime - mRemainTime) / mTotalTime)
                                .intValue());

                        sendMessageDelayed(obtainMessage(MSG_RUN),
                                mCountdownInterval);
                    }
                }
            }
        }
    };

}
