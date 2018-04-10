package com.xi6666.app;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.Html;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.platform.comapi.map.A;
import com.xi6666.R;
import com.xi6666.view.dialog.AddOilPromotionDialog;

import static com.xi6666.illegal.alert.AlertView.Style.Alert;

public class ShowDialogService extends Service {
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
       /* String str_username = intent.getExtras().getString("username");
        String str_password = intent.getExtras().getString("password");*/
        Toast.makeText(this, "弹窗", Toast.LENGTH_SHORT).show();
     /*   AddOilPromotionDialog addOilPromotionDialog = new AddOilPromotionDialog(this, R.style.transpant_bg_dialog);
        addOilPromotionDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        addOilPromotioDialog.show();*/

       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题");
        builder.setMessage("简单消息框");
        builder.setPositiveButton("确定", null);
        AlertDialog show = builder.show();
        show.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);*/

        AddOilPromotionDialog addOilPromotionDialog = new AddOilPromotionDialog(this);
        addOilPromotionDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        addOilPromotionDialog.show();


       /* addOilPromotionDialog.setTitle("提示");
        String html = "<p>已将您注册为会员</p><p>用户名：" + str_username + "</p><p>密&nbsp;&nbsp;&nbsp; 码：" + str_password + "</p>";
        dialog.setMessage(Html.fromHtml(html).toString());*/

/*         AlertDialog.Builder dialog=new AlertDialog.Builder(ShowDialogService.this);
           // TextView view=new TextView(ShowDialogService.this);
            View view=LayoutInflater.from(ShowDialogService.this).inflate(R.layout.slt_cnt_type, null);

            LinearLayout linear=(LinearLayout) view.findViewById(R.id.dialog_conent);
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) linear.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.height = 100;// 控件的高强制设成20
            linearParams.width = 300;
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(linearParams);
            TextView username=new TextView(ShowDialogService.this);
            TextView password=new TextView(ShowDialogService.this);
            username.setText("用户名：");
            password.setText("密    码：");
            linear.addView(username);
            linear.addView(password);
            dialog.setView(view);
            final AlertDialog d = dialog.create();
            d.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            d.show();*/
/*            Window window=d.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = 0f;
            window.setAttributes(params);
            */
    }
}
