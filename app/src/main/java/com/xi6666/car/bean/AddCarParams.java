package com.xi6666.car.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:51.
 * 个人公众号 ardays
 */

public class AddCarParams implements Parcelable {
    /**
     * cx_name:车型名称
     * cx_id:车型id
     * placment:排量
     *  plate:省份简称
     * city:城市字母简称
     *
     * kilometre:公里
     * n_road:新手上路时间
     *  number:车牌号
     */
    private boolean c_bol;
    private String cx_id;
    private String c_id;
    private String number;
    private String n_road;
    private String city;
    private String user_id;
    private String is_deft;

    public boolean isAdd;       //是否添加
    public String cx_img;       //车型图片
    public String cx_name;      //车型名称
    public String cx_pailiang;      //车型名排量

    public String car_brand;        //车型ID
    public String car_chexing;      //车型
    public String car_displacemen;  //上路时间 1998-02
    public String car_id;           //爱车ID
    public String car_pailiang;     //排量
    public String is_default;       //是否默认爱车
    public String car_nianfen;      //年份
    public String car_kilometre;    //行驶公里
    public String car_plate;        //车牌


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.c_bol ? (byte) 1 : (byte) 0);
        dest.writeString(this.cx_id);
        dest.writeString(this.c_id);
        dest.writeString(this.number);
        dest.writeString(this.n_road);
        dest.writeString(this.city);
        dest.writeString(this.user_id);
        dest.writeString(this.is_deft);
        dest.writeString(this.cx_img);
        dest.writeString(this.cx_name);
        dest.writeString(this.cx_pailiang);
        dest.writeString(this.car_brand);
        dest.writeString(this.car_chexing);
        dest.writeString(this.car_displacemen);
        dest.writeString(this.car_id);
        dest.writeString(this.car_pailiang);
        dest.writeString(this.is_default);
        dest.writeString(this.car_nianfen);
        dest.writeString(this.car_kilometre);
        dest.writeString(this.car_plate);
    }

    public AddCarParams() {
    }

    protected AddCarParams(Parcel in) {
        this.c_bol = in.readByte() != 0;
        this.cx_id = in.readString();
        this.c_id = in.readString();
        this.number = in.readString();
        this.n_road = in.readString();
        this.city = in.readString();
        this.user_id = in.readString();
        this.is_deft = in.readString();
        this.cx_img = in.readString();
        this.cx_name = in.readString();
        this.cx_pailiang = in.readString();
        this.car_brand = in.readString();
        this.car_chexing = in.readString();
        this.car_displacemen = in.readString();
        this.car_id = in.readString();
        this.car_pailiang = in.readString();
        this.is_default = in.readString();
        this.car_nianfen = in.readString();
        this.car_kilometre = in.readString();
        this.car_plate = in.readString();
    }

    public static final Parcelable.Creator<AddCarParams> CREATOR = new Parcelable.Creator<AddCarParams>() {
        @Override
        public AddCarParams createFromParcel(Parcel source) {
            return new AddCarParams(source);
        }

        @Override
        public AddCarParams[] newArray(int size) {
            return new AddCarParams[size];
        }
    };
}

