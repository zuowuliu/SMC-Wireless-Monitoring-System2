package com.ren.bean;

public class IndustrialMessage {
    private String user;
    private String gt;
    private String time;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F = f;
    }

    public String getG() {
        return G;
    }

    public void setG(String g) {
        G = g;
    }

    public String getH() {
        return H;
    }

    public void setH(String h) {
        H = h;
    }

//    public IndustrialMessage(String user, String gt, String time, String a, String b, String c, String d) {
//        this.user = user;
//        this.gt = gt;
//        this.time = time;
//        A = a;
//        B = b;
//        C = c;
//        D = d;
//    }

    public IndustrialMessage(String user, String gt, String time, String a, String b, String c, String d, String e, String f, String g, String h) {
        this.user = user;
        this.gt = gt;
        this.time = time;
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
        G = g;
        H = h;
    }

    public IndustrialMessage() {
    }

    @Override
    //封装发送给客户端的数据信息的格式
    public String toString() {
        return  "user:" +' '+ user + ' ' +
                "gt:" +' '+ gt + ' ' +
                "time:" +' '+ time + ' ' +
                "A" +' '+ A + ' ' +
                "B"+' ' + B + ' ' +
                "C" +' '+ C + ' ' +
                "D" +' '+ D + ' ' +
                "E" +' '+ E + ' ' +
                "F" +' '+ F + ' ' +
                "G" +' '+ G + ' ' +
                "H" +' '+ H;
    }
}

