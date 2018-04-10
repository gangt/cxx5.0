package com.xi6666.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xi6666.R;

/**
 * @项目名称: Mykkk
 * @包名: com.mykkk
 * @作者: Mr.yang
 * @创建时间:2015-10-15下午3:21:16
 * @描述: 弹出等待的弹窗工具类
 * @当前的版本号: $Rev$
 * @更新人: $Author$
 * @更新时间: $Date$
 * @更新描述: TODO
 */
public class ShowDialogUitls {
    public static Dialog showDio(Context context) {
        Builder builder = new Builder(context, R.style.Translucent_Dialog);
       /* Builder builder = new Builder(context);*/
        AlertDialog show = builder.show();
        View view = View.inflate(context, R.layout.waitdialog, null);
        ImageView gifView = (ImageView) view.findViewById(R.id.wait_dialog_iv);
        //制作帧动画
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading1), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading2), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading3), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading4), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading5), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading6), 100);
        gifView.setBackgroundDrawable(animationDrawable);
        animationDrawable.setOneShot(false);
        animationDrawable.start();
        show.setContentView(view);
        WindowManager.LayoutParams lp = show.getWindow().getAttributes();
        lp.dimAmount = 0.5f;
        show.getWindow().setAttributes(lp);
        return show;
    }
}
