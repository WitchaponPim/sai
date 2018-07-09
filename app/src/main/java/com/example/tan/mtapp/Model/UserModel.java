package com.example.tan.mtapp.Model;
public class UserModel {

    /**
     * status : 200
     * msg : OK
     * profile : {"id_member":"2","member_name":"Charant","member_surname":"Reanfai112","tel":"080280201212","email":"charanthorn93@gmail.com","username":"11","password":"11","address":"179 Sukumvit 62","lastactivetime":null,"identity":"0","IDcard":"0","job":"","sex":"","age":"0","type":"member"}
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
         * member_name : Charant
         * member_surname : Reanfai112
         * tel : 080280201212
         * email : charanthorn93@gmail.com
         * username : 11
         * password : 11
         * address : 179 Sukumvit 62
         * lastactivetime : null
         * identity : 0
         * IDcard : 0
         * job : 
         * sex : 
         * age : 0
         * type : member
         */

        private String id_member;
        private String member_name;
        private String member_surname;
        private String tel;
        private String email;
        private String username;
        private String password;
        private String address;
        private String lastactivetime;
        private String identity;
        private String IDcard;
        private String job;
        private String sex;
        private String age;
        private String type;

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

        public String getLastactivetime() {
            return lastactivetime;
        }

        public void setLastactivetime(String lastactivetime) {
            this.lastactivetime = lastactivetime;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getIDcard() {
            return IDcard;
        }

        public void setIDcard(String IDcard) {
            this.IDcard = IDcard;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
