package com.xi6666.car.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/12 0012.
 * 功能
 */
public class MyCarBean {
    public String success;
    public String info;
    public List<DataBean> data;

    public static class DataBean implements Parcelable {
        /*
            "Car_id": "爱车编号",

            "car_brand": "品牌名称",

            "car_chexing": "车型",

            "car_pailiang": "排量",

            "car_nianfen": "上市时间",

            "car_displacement": "2008-10",

            "car_plate": "车牌",

            "car_kilometre": "里程",

            "car_engine_no": "发动机号",

            "car_classno": "车架",
         */
        public String car_id;
        public String car_brand;
        public String car_logo;
        public String car_chexing;
        public String car_pailiang;
        public String car_nianfen;
        public String car_displacement;
        public String car_plate;
        public String car_kilometre;
        public String car_engine_no;
        public String car_classno;
        public String is_default;
        public String car_brand_id;
        public String car_chexing_id;
        public String car_pailiang_id;
        public String car_nianfen_id;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.car_id);
            dest.writeString(this.car_brand);
            dest.writeString(this.car_logo);
            dest.writeString(this.car_chexing);
            dest.writeString(this.car_pailiang);
            dest.writeString(this.car_nianfen);
            dest.writeString(this.car_displacement);
            dest.writeString(this.car_plate);
            dest.writeString(this.car_kilometre);
            dest.writeString(this.car_engine_no);
            dest.writeString(this.car_classno);
            dest.writeString(this.is_default);
            dest.writeString(this.car_brand_id);
            dest.writeString(this.car_chexing_id);
            dest.writeString(this.car_pailiang_id);
            dest.writeString(this.car_nianfen_id);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.car_id = in.readString();
            this.car_brand = in.readString();
            this.car_logo = in.readString();
            this.car_chexing = in.readString();
            this.car_pailiang = in.readString();
            this.car_nianfen = in.readString();
            this.car_displacement = in.readString();
            this.car_plate = in.readString();
            this.car_kilometre = in.readString();
            this.car_engine_no = in.readString();
            this.car_classno = in.readString();
            this.is_default = in.readString();
            this.car_brand_id = in.readString();
            this.car_chexing_id = in.readString();
            this.car_pailiang_id = in.readString();
            this.car_nianfen_id = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
