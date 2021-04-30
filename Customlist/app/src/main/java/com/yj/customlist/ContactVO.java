package com.yj.customlist;

public class ContactVO {
    //1.필드
    //2.생성자
    //3. get/set
    //4. toString

    public ContactVO() {
        super();
    }

    private int img;
    private String name;
    private String phone_num;

    public ContactVO(int img, String name, String phone_num) {
        this.img = img;
        this.name = name;
        this.phone_num = phone_num;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    @Override
    public String toString() {
        return "ContactVO{" +
                "img=" + img +
                ", name='" + name + '\'' +
                ", phone_num='" + phone_num + '\'' +
                '}';
    }
}
