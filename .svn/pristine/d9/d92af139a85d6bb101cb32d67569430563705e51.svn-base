package com.xi6666.car.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:25.
 * 个人公众号 ardays
 */

public class LetterView extends Button {

    /**
     * 列表将要显示的字母
     */
    private final String[] assort = {
            "#" ,
            "A" , "B" , "C" , "D" , "E" , "F" , "G" ,
            "H" , "I" , "J" , "K" , "L" , "M" , "N" ,
            "O" , "P" , "Q" , "R" , "S" , "T" ,
            "U" , "V" , "W" , "X" , "Y" , "Z" };
    private Paint mPaint;

    /**
     * 默认颜色
     */
    private final int mDefaultColor = Color.rgb(31,31,31);

    /**
     * 默认字体大小
     */
    private final int mDefaultSize = 28;

    /**
     * 默认颜色
     */
    private int mSelectTextColor = Color.rgb(20,204,112);

    /**
     * 默认字体大小
     */
    private final int mSelectTextSize = 30;

    private int selectIndex = -1;

    private OnTouchAssortListener mOnTouch;


    public LetterView(Context context) {
        this(context, null);
    }

    public LetterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取高度 和 高度
        int height = getHeight();
        int width = getWidth();
        //字体边距
        int interval = height / assort.length;
        for(int i = 0,length = assort.length; i < length; i++){
            mPaint.setAntiAlias(true);
            //设置字体大小和颜色
            mPaint.setColor(mDefaultColor);
            mPaint.setTextSize(mDefaultSize);

            if (i == selectIndex) {
                mPaint.setColor(mSelectTextColor);
                mPaint.setFakeBoldText(true);
                mPaint.setTextSize(mSelectTextSize);
            }
            float xPos = width / 2 - mPaint.measureText(assort[i]) / 2;
            float yPos = interval * i + interval;
            canvas.drawText(assort[i],xPos,yPos,mPaint);

            mPaint.descent();
        }
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        float y = event.getY();
        int index = (int) (y / getHeight() * assort.length);
        if (index >= 0 && index < assort.length) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    selectIndex = index;
                    if (mOnTouch != null) {
                        mOnTouch.onTouchAssortXyListener(event.getX(), event.getY());
                        mOnTouch.onTouchAssortListener(assort[selectIndex]);
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    selectIndex = index;
                    if (mOnTouch != null) {
                        mOnTouch.onTouchAssortXyListener(event.getX(), event.getY());
                        mOnTouch.onTouchAssortListener(assort[selectIndex]);
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    if (mOnTouch != null) {
                        mOnTouch.onTouchAssortUP();
                    }
                    selectIndex = -1;
                    break;
            }
        } else {
            selectIndex = -1;
            if (mOnTouch != null) {
                mOnTouch.onTouchAssortUP();
            }
        }
        invalidate();

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchAssortListener(OnTouchAssortListener listener){
        this.mOnTouch = listener;
    }

    /**
     * 写入字体颜色
     */
    public void setSelectTextColor(int color){
        this.mSelectTextColor = color;
    }


    public interface OnTouchAssortListener {
        public void onTouchAssortXyListener(float x, float y);

        public void onTouchAssortListener(String s);

        public void onTouchAssortUP();
    }
}
