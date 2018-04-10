package com.xi6666.address.fragment.mvp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/24 下午2:38.
 * 个人公众号 ardays
 */

public class PersonalAddressBean {
    public boolean success;
    public String info;
    public List<DataBean> data;


    public static class DataBean implements Parcelable {
        public String address_id;
        public String consignee;
        public String mobile;
        public String province;
        public String city;
        public String district;
        public String address;
        public String is_default;
        public String add_datetime;
        public String province_name;
        public String city_name;
        public String district_name;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.address_id);
            dest.writeString(this.consignee);
            dest.writeString(this.mobile);
            dest.writeString(this.province);
            dest.writeString(this.city);
            dest.writeString(this.district);
            dest.writeString(this.address);
            dest.writeString(this.is_default);
            dest.writeString(this.add_datetime);
            dest.writeString(this.province_name);
            dest.writeString(this.city_name);
            dest.writeString(this.district_name);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.address_id = in.readString();
            this.consignee = in.readString();
            this.mobile = in.readString();
            this.province = in.readString();
            this.city = in.readString();
            this.district = in.readString();
            this.address = in.readString();
            this.is_default = in.readString();
            this.add_datetime = in.readString();
            this.province_name = in.readString();
            this.city_name = in.readString();
            this.district_name = in.readString();
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
