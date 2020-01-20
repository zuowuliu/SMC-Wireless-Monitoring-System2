package com.ren.bean;

public class User {
    /*
    * Ren
    * 建立登录者的数据模型-
    * 包含字段：
    * 用户id
    * 用户名
    * 密码
    * 头像路径
    * 用户角色
    * 权限
    * */
    private int id;
    private String userName;
    private String passWord;
    private String headPic;
    private String role;
    private String permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public User() {
    }

    public User(int id, String userName, String password, String headPic, String role, String permission) {
        this.id = id;
        this.userName = userName;
        this.passWord = password;
        this.headPic = headPic;
        this.role = role;
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + passWord + '\'' +
                ", headPic='" + headPic + '\'' +
                ", role='" + role + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
