package com.yj.suntalk;

public class ChatVO {
    private int img; //주소
    private String name; //사용자이름(채팅주인이름)
    private String msg; //보낸메세지
    private String time; //보낸시간

    public ChatVO(){

    }

    public ChatVO(int img, String name, String msg, String time) {
        this.img = img;
        this.name = name;
        this.msg = msg;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }

    public String getTime() {
        return time;
    }
}
