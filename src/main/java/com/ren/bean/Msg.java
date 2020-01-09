package com.ren.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private int code;//定义状态码
    private String msg;//定义状态信息
    private Map<String, Object> extend = new HashMap<>();//用于保存处理完成后的数据信息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getExtend() {
        return extend;
    }

    public void setExtend(Map map) {
        this.extend = map;
    }

    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("处理成功");
        return msg;
    }
    public static Msg fail(){
        Msg msg = new Msg();
        msg.setCode(500);
        msg.setMsg("处理失败");
        return msg;
    }

    public Msg add(String key, Object value ){
        this.getExtend().put(key, value);
        return this;
    }
}
