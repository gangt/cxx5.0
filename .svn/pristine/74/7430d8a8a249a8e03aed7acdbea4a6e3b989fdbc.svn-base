package com.xi6666.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by Mr_yang on 2017/1/17.
 */

public class CouponsView extends RelativeLayout {

    private static final String TAG = "CouponsView";
    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 8;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;

    private float remainY;

    private int with;
    private int height;


    public CouponsView(Context context) {
        this(context, null);
    }

    public CouponsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (w - gap) % (2 * radius + gap);
            remainY = (int) (h - gap) % (2 * radius + gap);
            Log.d(TAG, "remain--->" + remain);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));

        with = w;
        height = h;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (int i = 0; i < circleNum; i++) {
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
            float y = gap + radius + remainY / 2 + ((gap + radius * 2) * i);
            canvas.drawCircle(0, y, radius, mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
        RectF rectF = new RectF(0, 0, with, height);
        canvas.drawRoundRect(rectF, 50, 50, mPaint);
    }
}
