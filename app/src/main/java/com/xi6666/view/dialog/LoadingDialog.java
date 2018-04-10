package com.xi6666.view.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xi6666.R;

/**
 * Created by Mr_yang on 2016/11/24.
 */

public class LoadingDialog {

    public Dialog LoadingDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Translucent_Dialog);
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
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading7), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.drawable.loading8), 100);
        gifView.setBackgroundDrawable(animationDrawable);
        animationDrawable.setOneShot(false);
        animationDrawable.start();

        AlertDialog show = builder.show();
        show.setContentView(view);
        WindowManager.LayoutParams lp = show.getWindow().getAttributes();
        lp.dimAmount = 0.5f;
        show.getWindow().setAttributes(lp);
        return show;
    }
}
