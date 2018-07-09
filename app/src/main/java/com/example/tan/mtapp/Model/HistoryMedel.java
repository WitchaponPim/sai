package com.example.tan.mtapp.Model;

import java.util.List;

public class HistoryMedel {

    /**
     * id_register : 38
     * date : 2018-06-20
     * time : 12:07:35
     * id_activity : 44
     * id_member : 2
     * QR_code : 44ON2
     * activity_name : กิจกรรมที่ 2
     * id_activity_type : 2
     * people : 50
     * type : regis
     * status : 1
     * picture : ../project/picactivity/img_5b29c78767e38.jpg
     * detail : [{"activity_name":"402","start_date":"2018-06-20","start_time":"08:00:00","end_date":"2018-06-21","end_time":"17:00:00"}]
     */

    private String id_register;
    private String date;
    private String time;
    private String id_activity;
    private String id_member;
    private String QR_code;
    private String activity_name;
    private String id_activity_type;
    private String people;
    private String type;
    private String status;
    private String picture;
    private List<DetailBean> detail;

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

    public String getId_activity() {
        return id_activity;
    }

    public void setId_activity(String id_activity) {
        this.id_activity = id_activity;
    }

    public String getId_member() {
        return id_member;
    }

    public void setId_member(String id_member) {
        this.id_member = id_member;
    }

    public String getQR_code() {
        return QR_code;
    }

    public void setQR_code(String QR_code) {
        this.QR_code = QR_code;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getId_activity_type() {
        return id_activity_type;
    }

    public void setId_activity_type(String id_activity_type) {
        this.id_activity_type = id_activity_type;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * activity_name : 402
         * start_date : 2018-06-20
         * start_time : 08:00:00
         * end_date : 2018-06-21
         * end_time : 17:00:00
         */

        private String activity_name;
        private String start_date;
        private String start_time;
        private String end_date;
        private String end_time;

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
