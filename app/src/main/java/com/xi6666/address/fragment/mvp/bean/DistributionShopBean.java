package com.xi6666.address.fragment.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/25 下午7:00.
 * 个人公众号 ardays
 */

public class DistributionShopBean {
    public boolean success;
    public String info;
    public List<DataBean> data;

    public static class DataBean implements Parcelable {
        public String id;
        public String shop_name;
        public String shop_banner;
        public String shop_tel;
        public String shop_address;
        public String distance;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.shop_name);
            dest.writeString(this.shop_banner);
            dest.writeString(this.shop_tel);
            dest.writeString(this.shop_address);
            dest.writeString(this.distance);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.shop_name = in.readString();
            this.shop_banner = in.readString();
            this.shop_tel = in.readString();
            this.shop_address = in.readString();
            this.distance = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
