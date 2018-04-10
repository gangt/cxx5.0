package com.xi6666.illegal.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xi6666.R;
import com.xi6666.common.UserData;
import com.xi6666.illegal.Activity.IllegalFindResultAct;
import com.xi6666.illegal.bean.BreakListBean;
import com.xi6666.illegal.net_data.NetAddress;
import com.xi6666.utils.LogUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QueryListAdapter extends BaseRecyclerAdapter<BreakListBean.DataBean> {
    private Context                      context;
//    public  List<BreakListBean.DataBean> dataBeans;

    public QueryListAdapter(Context context) {
        this.context = context;
//        this.dataBeans = dataBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.breaklist_item, null);
        ViewHolder myHolder = new ViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBind(final RecyclerView.ViewHolder viewHolder, final int RealPosition, final BreakListBean.DataBean data) {
        ((ViewHolder) viewHolder).listcar.setText(data.getCar_no());
        ((ViewHolder) viewHolder).wei_num.setText(data.getCount());
        ((ViewHolder) viewHolder).pay_money.setText("¥" + data.getMoney());
        ((ViewHolder) viewHolder).kou_fen.setText(data.getFen());
        ((ViewHolder) viewHolder).history_tv_address.setText("(" + data.getCity() + ")");


        //删除操作
        ((ViewHolder) viewHolder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit.Builder builder = new Retrofit.Builder();
                Retrofit retrofit = builder.baseUrl(NetAddress.baseUrl).build();
                retrofit.create(NetAddress.class).getBreakDelete(UserData.getUserId(),UserData.getUserToken(), data.getCar_no(), data.getCity_code(), data.getProvince_code()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String result = response.body().string();
                            LogUtil.i("delete response", result);
                            JSONObject object = new JSONObject(result);
                            if (object.optBoolean("success")) {
                                Toast.makeText(context, object.optString("info"), Toast.LENGTH_SHORT).show();
                                getDatas().remove(data);
                                notifyItemRemoved(RealPosition);
                                LogUtil.i("position", RealPosition + "");
                                if (RealPosition != getDatas().size()) {
                                    notifyItemRangeChanged(RealPosition, getDatas().size() - RealPosition);
                                }
                            } else {
                                Toast.makeText(context, object.optString("info"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                    }
                });
            }
        });
        ((ViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IllegalFindResultAct.class);
                intent.putExtra("userid", UserData.getUserId());
                intent.putExtra("car_no", data.getCar_no());
                intent.putExtra("city_code", data.getCity_code());
                intent.putExtra("engineno", data.getEngineno());
                intent.putExtra("classno", data.getClassno());
                intent.putExtra("province_code", data.getProvince_code());
                context.startActivity(intent);
            }
        });

    }

    class ViewHolder extends BaseRecyclerAdapter.Holder {
        private TextView    listcar;
        private TextView    wei_num;
        private TextView    pay_money;
        private TextView    kou_fen;
        private TextView    history_tv_address;
        private ImageView   delete;

        public ViewHolder(View itemView) {
            super(itemView);
            listcar = (TextView) itemView.findViewById(R.id.list_card);
            wei_num = (TextView) itemView.findViewById(R.id.weizhang_num);
            pay_money = (TextView) itemView.findViewById(R.id.pay_money);
            kou_fen = (TextView) itemView.findViewById(R.id.koufen);
            history_tv_address = (TextView) itemView.findViewById(R.id.history_tv_address);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }
    }
}
