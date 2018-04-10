package com.xi6666.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi6666.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者 sunsun
 * 时间 16/11/10 下午5:11.
 * 个人公众号 ardays
 * 限制编辑的文字
 */
public class LimitEditTextView extends LinearLayout implements LimitEditTextViewImpl{
    /**
     * 上下文
     */
    Context mContext;
    AttributeSet mA;

    @BindView(R.id.view_limit_content_et)
    EditText mContentEt;//能编辑的文本
    @BindView(R.id.view_limit_size_tv)
    TextView mSizeTv;   //记录当前编辑的文本是否超出限制

    //文本最大数量。默认是250
    int mTextMaxLength = 250;
    //超过最大字数的字体颜色
    int mMoreColor = R.color.text_red;
    //默认颜色
    int mDefaultColor = R.color.text_gray;


    public LimitEditTextView(Context context) {
        this(context, null);
    }

    public LimitEditTextView(Context context, AttributeSet a) {
        this(context, a, 0);
    }

    public LimitEditTextView(Context context, AttributeSet a, int defStyleAttr) {
        super(context, a, defStyleAttr);
        this.mContext = context;
        this.mA = a;
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        View view = View.inflate(mContext, R.layout.view_limit_edittext, this);
        ButterKnife.bind(this, view);
        initXml();
        initEdit();
    }

    /**
     * 初始化取值
     */
    private void initXml() {
        TypedArray a = mContext.obtainStyledAttributes(mA, R.styleable.LimitEditTextView);
        //不设置默认250
        int maxLength = a.getInt(R.styleable.LimitEditTextView_maxLength, mTextMaxLength);
        String hintText = a.getString(R.styleable.LimitEditTextView_hint);
        a.recycle();

        setMaxLength(maxLength);
        setHintText(hintText);
    }

    /**
     *  初始化编辑
     */
    private void initEdit() {
        //监听编辑文本输入
        mContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //设置最大的长度
                updateLength();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void updateLength() {
        Editable editable = mContentEt.getText();
        int len = editable.length();
        if(len > mTextMaxLength)
        {
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0,mTextMaxLength);
            mContentEt.setText(newStr);
            editable = mContentEt.getText();

            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if(selEndIndex > newLen)
            {
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable, selEndIndex);
        }


        int length = mContentEt.getText().length();
        //设置当前文本长度
        mSizeTv.setText(length + "/" + mTextMaxLength);
        int color;
        if(length == mTextMaxLength){
            color = mMoreColor;
        }else{
            color = mDefaultColor;
        }

        mSizeTv.setTextColor(getResources().getColor(color));
    }

    @Override
    public void setMaxLength(int maxLength) {
        this.mTextMaxLength = maxLength;
        updateLength();
    }

    @Override
    public void setHintText(String text) {
        mContentEt.setHint(text);
    }

    /**
     * 获取输入文字
     */
    public Editable getText(){
        return mContentEt.getText();
    }

}