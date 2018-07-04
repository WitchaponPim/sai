package com.example.tan.mtapp.Model;

public class HistoryMedel {

    /**
     * id_register : 26
     * date : 2018-06-18
     * time : 18:38:27
     * id_activity : 36
     * id_member : 2
     * QR_code : 36ON2
     * type : regis
     * status : 1
     */

    private String id_register;
    private String date;
    private String time;
    private String id_activity;
    private String id_member;
    private String QR_code;
    private String type;
    private String status;

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
}
