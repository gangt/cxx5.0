package com.xi6666.cardbag.view.custom;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.xi6666.utils.LogUtil;

/**
 * 创建者 sunsun
 * 时间 2016/12/5 下午2:20.
 * 个人公众号 ardays
 */

public class CardInputEditText extends EditText {

    private Context mContext;

    public CardInputEditText(Context ctx) {
        this(ctx, null);
    }

    public CardInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        addTextChangedListener(watcher);
    }

    public CardInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            LogUtil.d("TAG", "s:" + s + ",start:" + start + ",before:" + before + ",count:" + count);
            if (s == null) {
                return;
            }
            //判断是否是在中间输入，需要重新计算
            boolean isMiddle = (start + count) < (s.length());
            //在末尾输入时，是否需要加入空格
            boolean isNeedSpace = false;
            if (!isMiddle && s.length() > 0 && s.length() % 5 == 0) {
                isNeedSpace = true;
            }
            if (isMiddle || isNeedSpace) {
                String newStr = s.toString();
                newStr = newStr.replace(" ", "");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < newStr.length(); i += 4) {
                    if (i > 0) {
                        sb.append(" ");
                    }
                    if (i + 4 <= newStr.length()) {
                        sb.append(newStr.substring(i, i + 4));
                    } else {
                        sb.append(newStr.substring(i, newStr.length()));
                    }
                }
                removeTextChangedListener(watcher);
                setText(sb);
                //如果是在末尾的话,或者加入的字符个数大于零的话（输入或者粘贴）
                if (!isMiddle || count > 1) {
                    setSelection(sb.length());
                } else if (isMiddle) {
                    //如果是删除
                    if (count == 0) {
                        //如果删除时，光标停留在空格的前面，光标则要往前移一位
                        if ((start - before + 1) % 5 == 0) {
                            setSelection((start - before) > 0 ? start - before : 0);
                        } else {
                            setSelection((start - before + 1) > sb.length() ? sb.length() : (start - before + 1));
                        }
                    }
                    //如果是增加
                    else {
                        if ((start - before + count) % 5 == 0) {
                            setSelection((start + count - before + 1) < sb.length() ? (start + count - before + 1) : sb.length());
                        } else {
                            setSelection(start + count - before);
                        }
                    }
                }
                addTextChangedListener(watcher);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public String getTextWithoutSpace() {
        return super.getText().toString().replace(" ", "");
    }


    /**
     * 监听文本菜单事件
     */
    public boolean onTextContextMenuItem(int id) {

        switch (id) {
            case android.R.id.paste:
                ClipboardManager clipboard =
                        (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                String newStr = clipboard.getText().toString();
                newStr = newStr.replace(" ", "");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < newStr.length(); i += 4) {
                    if (i > 0) {
                        sb.append(" ");
                    }
                    if (i + 4 <= newStr.length()) {
                        sb.append(newStr.substring(i, i + 4));
                    } else {
                        sb.append(newStr.substring(i, newStr.length()));
                    }
                }

                clipboard.getText();
                Log.e("TAG","选择了粘贴，粘贴的文字是--->" + newStr + ",粘贴后---->" + sb.toString());
                setText(sb.toString());
                return false;
        }
        return super.onTextContextMenuItem(id);
    }
}