package com.example.tan.mtapp.Model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    /**
     * status : 200
     * msg : OK
     * profile : {"id_member":"2","member_name":"Charanthorn","member_surname":"Reanfai","tel":"0802802012","email":"charanthorn93@gmail.com","username":"11","password":"11","address":"179 Sukumvit 62","last active-time":"","id_emp":"","identity":"0"}
     */

    private String status;
    private String msg;
    private ProfileBean profile;

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

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public static class ProfileBean {
        /**
         * id_member : 2
         * member_name : Charanthorn
         * member_surname : Reanfai
         * tel : 0802802012
         * email : charanthorn93@gmail.com
         * username : 11
         * password : 11
         * address : 179 Sukumvit 62
         * last active-time :
         * id_emp :
         * identity : 0
         */

        private String id_member;
        private String member_name;
        private String member_surname;
        private String tel;
        private String email;
        private String username;
        private String password;
        private String address;
        @SerializedName("last active-time")
        private String _$LastActivetime150; // FIXME check this code
        private String id_emp;
        private String identity;

        public String getId_member() {
            return id_member;
        }

        public void setId_member(String id_member) {
            this.id_member = id_member;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getMember_surname() {
            return member_surname;
        }

        public void setMember_surname(String member_surname) {
            this.member_surname = member_surname;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String get_$LastActivetime150() {
            return _$LastActivetime150;
        }

        public void set_$LastActivetime150(String _$LastActivetime150) {
            this._$LastActivetime150 = _$LastActivetime150;
        }

        public String getId_emp() {
            return id_emp;
        }

        public void setId_emp(String id_emp) {
            this.id_emp = id_emp;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }
    }
}
