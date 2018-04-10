package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2017/3/12.
 */

public class RechargeListBean {

    /**
     * data : {"back_list":[{"add_datetime":"2017-01-13 18:45:30","back_data":[{"back_money":"100.00(1/1)","back_status":"已到帐","back_time":"2017-01-13"}],"not_already":0,"oil_order_id":"14853","order_paytime":"2017-01-13 18:45:38","package_cash":"100.00","package_left_number":"0","package_name":"即时充值","package_return_number":"1"},{"add_datetime":"2016-10-24 11:28:11","back_data":[{"back_money":"700.00(1/2)","back_status":"已到帐","back_time":"2016-10-24"},{"back_money":"700.00(2/2)","back_status":"已到帐","back_time":"2016-11-24"}],"not_already":0,"oil_order_id":"10942","order_paytime":"2016-10-24 11:29:52","package_cash":"700.00","package_left_number":"0","package_name":"2个月加油套餐","package_return_number":"2"},{"add_datetime":"2016-10-24 09:11:52","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-10-24"},{"back_money":"500.00(2/2)","back_status":"已到帐","back_time":"2016-11-24"}],"not_already":0,"oil_order_id":"10778","order_paytime":"2016-10-24 09:12:03","package_cash":"500.00","package_left_number":"0","package_name":"2个月加油套餐","package_return_number":"2"},{"add_datetime":"2016-10-12 17:28:13","back_data":[{"back_money":"100.00(1/1)","back_status":"已到帐","back_time":"2016-10-12"}],"not_already":0,"oil_order_id":"10230","order_paytime":"2016-10-12 17:28:28","package_cash":"100.00","package_left_number":"0","package_name":"即时充值","package_return_number":"1"},{"add_datetime":"2016-07-30 12:28:37","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-07-30"},{"back_money":"500.00(2/2)","back_status":"未到账","back_time":"2016-08-30"}],"not_already":0,"oil_order_id":"6285","order_paytime":"2016-07-30 12:29:00","package_cash":"500.00","package_left_number":"0","package_name":"15天充值套餐","package_return_number":"2"},{"add_datetime":"2016-05-13 13:34:56","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-05-13"},{"back_money":"500.00(2/2)","back_status":"未到账","back_time":"2016-06-13"}],"not_already":0,"oil_order_id":"2278","order_paytime":"2016-05-13 13:35:44","package_cash":"500.00","package_left_number":"0","package_name":"15天充值套餐","package_return_number":"2"},{"add_datetime":"2016-05-13 09:43:41","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-05-13"},{"back_money":"500.00(2/2)","back_status":"未到账","back_time":"2016-06-13"}],"not_already":0,"oil_order_id":"2262","order_paytime":"2016-05-13 09:47:12","package_cash":"500.00","package_left_number":"0","package_name":"15天充值套餐","package_return_number":"2"}],"count_num":"7","data":[{"order_paytime":"2017-01-13 18:45:38","package_amount":"100.00","package_name":"即时充值"},{"order_paytime":"2016-10-24 11:29:52","package_amount":"1400.00","package_name":"2个月加油套餐"},{"order_paytime":"2016-10-24 09:12:03","package_amount":"1000.00","package_name":"2个月加油套餐"},{"order_paytime":"2016-10-12 17:28:28","package_amount":"100.00","package_name":"即时充值"},{"order_paytime":"2016-07-30 12:29:00","package_amount":"1000.00","package_name":"15天充值套餐"}],"soon_money":0}
     * info : 获取信息成功
     * success : true
     * version : 5
     */

    private DataBeanX data;
    private String info;
    private boolean success;
    private String version;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBeanX {
        /**
         * back_list : [{"add_datetime":"2017-01-13 18:45:30","back_data":[{"back_money":"100.00(1/1)","back_status":"已到帐","back_time":"2017-01-13"}],"not_already":0,"oil_order_id":"14853","order_paytime":"2017-01-13 18:45:38","package_cash":"100.00","package_left_number":"0","package_name":"即时充值","package_return_number":"1"},{"add_datetime":"2016-10-24 11:28:11","back_data":[{"back_money":"700.00(1/2)","back_status":"已到帐","back_time":"2016-10-24"},{"back_money":"700.00(2/2)","back_status":"已到帐","back_time":"2016-11-24"}],"not_already":0,"oil_order_id":"10942","order_paytime":"2016-10-24 11:29:52","package_cash":"700.00","package_left_number":"0","package_name":"2个月加油套餐","package_return_number":"2"},{"add_datetime":"2016-10-24 09:11:52","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-10-24"},{"back_money":"500.00(2/2)","back_status":"已到帐","back_time":"2016-11-24"}],"not_already":0,"oil_order_id":"10778","order_paytime":"2016-10-24 09:12:03","package_cash":"500.00","package_left_number":"0","package_name":"2个月加油套餐","package_return_number":"2"},{"add_datetime":"2016-10-12 17:28:13","back_data":[{"back_money":"100.00(1/1)","back_status":"已到帐","back_time":"2016-10-12"}],"not_already":0,"oil_order_id":"10230","order_paytime":"2016-10-12 17:28:28","package_cash":"100.00","package_left_number":"0","package_name":"即时充值","package_return_number":"1"},{"add_datetime":"2016-07-30 12:28:37","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-07-30"},{"back_money":"500.00(2/2)","back_status":"未到账","back_time":"2016-08-30"}],"not_already":0,"oil_order_id":"6285","order_paytime":"2016-07-30 12:29:00","package_cash":"500.00","package_left_number":"0","package_name":"15天充值套餐","package_return_number":"2"},{"add_datetime":"2016-05-13 13:34:56","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-05-13"},{"back_money":"500.00(2/2)","back_status":"未到账","back_time":"2016-06-13"}],"not_already":0,"oil_order_id":"2278","order_paytime":"2016-05-13 13:35:44","package_cash":"500.00","package_left_number":"0","package_name":"15天充值套餐","package_return_number":"2"},{"add_datetime":"2016-05-13 09:43:41","back_data":[{"back_money":"500.00(1/2)","back_status":"已到帐","back_time":"2016-05-13"},{"back_money":"500.00(2/2)","back_status":"未到账","back_time":"2016-06-13"}],"not_already":0,"oil_order_id":"2262","order_paytime":"2016-05-13 09:47:12","package_cash":"500.00","package_left_number":"0","package_name":"15天充值套餐","package_return_number":"2"}]
         * count_num : 7
         * data : [{"order_paytime":"2017-01-13 18:45:38","package_amount":"100.00","package_name":"即时充值"},{"order_paytime":"2016-10-24 11:29:52","package_amount":"1400.00","package_name":"2个月加油套餐"},{"order_paytime":"2016-10-24 09:12:03","package_amount":"1000.00","package_name":"2个月加油套餐"},{"order_paytime":"2016-10-12 17:28:28","package_amount":"100.00","package_name":"即时充值"},{"order_paytime":"2016-07-30 12:29:00","package_amount":"1000.00","package_name":"15天充值套餐"}]
         * soon_money : 0
         */

        private String count_num;
        private int soon_money;
        private List<BackListBean> back_list;
        private List<DataBean> data;

        public String getCount_num() {
            return count_num;
        }

        public void setCount_num(String count_num) {
            this.count_num = count_num;
        }

        public int getSoon_money() {
            return soon_money;
        }

        public void setSoon_money(int soon_money) {
            this.soon_money = soon_money;
        }

        public List<BackListBean> getBack_list() {
            return back_list;
        }

        public void setBack_list(List<BackListBean> back_list) {
            this.back_list = back_list;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class BackListBean {
            /**
             * add_datetime : 2017-01-13 18:45:30
             * back_data : [{"back_money":"100.00(1/1)","back_status":"已到帐","back_time":"2017-01-13"}]
             * not_already : 0
             * oil_order_id : 14853
             * order_paytime : 2017-01-13 18:45:38
             * package_cash : 100.00
             * package_left_number : 0
             * package_name : 即时充值
             * package_return_number : 1
             */

            private String add_datetime;
            private int not_already;
            private String oil_order_id;
            private String order_paytime;
            private String package_cash;
            private String package_left_number;
            private String package_name;
            private String package_return_number;
            private List<BackDataBean> back_data;

            public String getAdd_datetime() {
                return add_datetime;
            }

            public void setAdd_datetime(String add_datetime) {
                this.add_datetime = add_datetime;
            }

            public int getNot_already() {
                return not_already;
            }

            public void setNot_already(int not_already) {
                this.not_already = not_already;
            }

            public String getOil_order_id() {
                return oil_order_id;
            }

            public void setOil_order_id(String oil_order_id) {
                this.oil_order_id = oil_order_id;
            }

            public String getOrder_paytime() {
                return order_paytime;
            }

            public void setOrder_paytime(String order_paytime) {
                this.order_paytime = order_paytime;
            }

            public String getPackage_cash() {
                return package_cash;
            }

            public void setPackage_cash(String package_cash) {
                this.package_cash = package_cash;
            }

            public String getPackage_left_number() {
                return package_left_number;
            }

            public void setPackage_left_number(String package_left_number) {
                this.package_left_number = package_left_number;
            }

            public String getPackage_name() {
                return package_name;
            }

            public void setPackage_name(String package_name) {
                this.package_name = package_name;
            }

            public String getPackage_return_number() {
                return package_return_number;
            }

            public void setPackage_return_number(String package_return_number) {
                this.package_return_number = package_return_number;
            }

            public List<BackDataBean> getBack_data() {
                return back_data;
            }

            public void setBack_data(List<BackDataBean> back_data) {
                this.back_data = back_data;
            }

            public static class BackDataBean {
                /**
                 * back_money : 100.00(1/1)
                 * back_status : 已到帐
                 * back_time : 2017-01-13
                 */

                private String back_money;
                private String back_status;
                private String back_time;

                public String getBack_money() {
                    return back_money;
                }

                public void setBack_money(String back_money) {
                    this.back_money = back_money;
                }

                public String getBack_status() {
                    return back_status;
                }

                public void setBack_status(String back_status) {
                    this.back_status = back_status;
                }

                public String getBack_time() {
                    return back_time;
                }

                public void setBack_time(String back_time) {
                    this.back_time = back_time;
                }
            }
        }

        public static class DataBean {
            /**
             * order_paytime : 2017-01-13 18:45:38
             * package_amount : 100.00
             * package_name : 即时充值
             */

            private String order_paytime;
            private String package_amount;
            private String package_name;

            public String getOrder_paytime() {
                return order_paytime;
            }

            public void setOrder_paytime(String order_paytime) {
                this.order_paytime = order_paytime;
            }

            public String getPackage_amount() {
                return package_amount;
            }

            public void setPackage_amount(String package_amount) {
                this.package_amount = package_amount;
            }

            public String getPackage_name() {
                return package_name;
            }

            public void setPackage_name(String package_name) {
                this.package_name = package_name;
            }
        }
    }
}
