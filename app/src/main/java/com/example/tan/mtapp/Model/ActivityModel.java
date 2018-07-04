package com.example.tan.mtapp.Model;

import java.util.List;

public class ActivityModel {

    /**
     * status : 200
     * msg : Get Success
     * detail : [{"ac_name":"test 2 ","id_activity":"50","activity_name":"test 2 ","id_activity_type":"1","activity_pay":"20","picture":"../project/picactivity/img_5b29e7c32e2aa.jpg","Id_room_type":"2","people":"1000","start_time":"09:00:00","end_time":"17:00:00","detail":"เสียตัง","status":"ประชาสัมพันธ์กิจกรรม","alert":"1","id_room":"9","start_date":"2018-06-23","end_date":"2018-06-30","id_emp":"8","room_name":"พระราชประวัติ","seat":"0"},{"ac_name":"test 2 ","id_activity":"50","activity_name":"test 2 ","id_activity_type":"1","activity_pay":"20","picture":"../project/picactivity/img_5b29e7c32e2aa.jpg","Id_room_type":"2","people":"1000","start_time":"09:00:00","end_time":"17:00:00","detail":"เสียตัง","status":"ประชาสัมพันธ์กิจกรรม","alert":"1","id_room":"9","start_date":"2018-07-01","end_date":"2018-07-01","id_emp":"9","room_name":"พระราชประวัติ","seat":"0"}]
     */

    private String status;
    private String msg;
    private List<DetailBean> detail;
    private String QR;


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

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * ac_name : test 2
         * id_activity : 50
         * activity_name : test 2
         * id_activity_type : 1
         * activity_pay : 20
         * picture : ../project/picactivity/img_5b29e7c32e2aa.jpg
         * Id_room_type : 2
         * people : 1000
         * start_time : 09:00:00
         * end_time : 17:00:00
         * detail : เสียตัง
         * status : ประชาสัมพันธ์กิจกรรม
         * alert : 1
         * id_room : 9
         * start_date : 2018-06-23
         * end_date : 2018-06-30
         * id_emp : 8
         * room_name : พระราชประวัติ
         * seat : 0
         */

        private String ac_name;
        private String id_activity;
        private String activity_name;
        private String id_activity_type;
        private String activity_pay;
        private String picture;
        private String Id_room_type;
        private String people;
        private String start_time;
        private String end_time;
        private String detail;
        private String status;
        private String alert;
        private String id_room;
        private String start_date;
        private String end_date;
        private String id_emp;
        private String room_name;
        private String seat;
        private String Id_reserve;

        public String getAc_name() {
            return ac_name;
        }

        public void setAc_name(String ac_name) {
            this.ac_name = ac_name;
        }

        public String getId_activity() {
            return id_activity;
        }

        public void setId_activity(String id_activity) {
            this.id_activity = id_activity;
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

        public String getActivity_pay() {
            return activity_pay;
        }

        public void setActivity_pay(String activity_pay) {
            this.activity_pay = activity_pay;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getId_room_type() {
            return Id_room_type;
        }

        public void setId_room_type(String Id_room_type) {
            this.Id_room_type = Id_room_type;
        }

        public String getPeople() {
            return people;
        }

        public void setPeople(String people) {
            this.people = people;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAlert() {
            return alert;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public String getId_room() {
            return id_room;
        }

        public void setId_room(String id_room) {
            this.id_room = id_room;
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

        public String getId_emp() {
            return id_emp;
        }

        public void setId_emp(String id_emp) {
            this.id_emp = id_emp;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public String getId_reserve() {
            return Id_reserve;
        }

        public void setId_reserve(String Id_reserve) {
            this.seat = Id_reserve;
        }
    }
}
