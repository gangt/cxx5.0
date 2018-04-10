package com.xi6666.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mr_yang on 2017/2/23.
 */

public class ReceiveSuccessDialog extends Dialog {
    @BindView(R.id.txt_receive_success_content)
    TextView mTxtReceiveSuccessContent;
    @BindView(R.id.txt_receive_success_know)
    TextView mTxtReceiveSuccessKnow;

    public ReceiveSuccessDialog(Context context) {
        super(context);
        init(context);
    }

    protected ReceiveSuccessDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    public ReceiveSuccessDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context mContext) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_receive_success, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        setCancelable(true);
    }

    public void setContent(String content) {
        mTxtReceiveSuccessContent.setText(content);
    }

    @OnClick({R.id.txt_receive_success_know})
    public void viewOnClick(View view) {
        switch (view.getId()) {
            case R.id.txt_receive_success_know:
                this.dismiss();
                break;
        }
    }
}