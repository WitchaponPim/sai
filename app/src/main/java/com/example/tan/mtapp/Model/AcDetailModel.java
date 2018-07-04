package com.example.tan.mtapp.Model;

import java.util.List;

public class AcDetailModel {

    /**
     * status : 200
     * msg : Get Success
     * detail : [{"id_register":"31","date":"2018-06-13","time":"22:18:55","start_date":"2018-05-31","end_date":"2018-06-01","activity_name":"602"}]
     */

    private String status;
    private String msg;
    private List<DetailBean> detail;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * id_register : 31
         * date : 2018-06-13
         * time : 22:18:55
         * start_date : 2018-05-31
         * end_date : 2018-06-01
         * activity_name : 602
         */

        private String id_register;
        private String date;
        private String time;
        private String start_date;
        private String end_date;
        private String activity_name;

        public String getId_register() {
            return id_register;
        }

        public void setId_register(String id_register) {
            this.id_register = id_register;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }
    }
}
