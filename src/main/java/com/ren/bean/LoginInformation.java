package com.ren.bean;

public class LoginInformation {
    private String userName;
    private String loginTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public LoginInformation(String userName, String loginTime) {
        this.userName = userName;
        this.loginTime = loginTime;
    }

    public LoginInformation() {
    }

    @Override
    public String toString() {
        return "LoginInformation{" +
                "userName='" + userName + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
