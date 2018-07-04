package com.example.tan.mtapp.Model;

import java.util.List;

public class SeatModel {



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
         * id_detail_sit : 751
         * id_sit : 1
         * status : 0
         * id_activity : 39
         * id_member : 0
         * id_reserve : 0
         * number_sit : a1
         * id_room : 8
         * line : a
         */

        private String id_detail_sit;
        private String id_sit;
        private String status;
        private String id_activity;
        private String id_member;
        private String id_reserve;
        private String number_sit;
        private String id_room;
        private String line;

        public String getId_detail_sit() {
            return id_detail_sit;
        }

        public void setId_detail_sit(String id_detail_sit) {
            this.id_detail_sit = id_detail_sit;
        }

        public String getId_sit() {
            return id_sit;
        }

        public void setId_sit(String id_sit) {
            this.id_sit = id_sit;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getId_reserve() {
            return id_reserve;
        }

        public void setId_reserve(String id_reserve) {
            this.id_reserve = id_reserve;
        }

        public String getNumber_sit() {
            return number_sit;
        }

        public void setNumber_sit(String number_sit) {
            this.number_sit = number_sit;
        }

        public String getId_room() {
            return id_room;
        }

        public void setId_room(String id_room) {
            this.id_room = id_room;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }
    }
}
