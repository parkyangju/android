package com.yj.excustomlist2;

public class ContactVO {

    private String title;
    private String address;

    public ContactVO() {
        super();
    }

    public ContactVO(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getAddress() {
        return address;
    }

//    public void setAddress(String address) {
//        this.address = address;
//    }

   @Override
    public String toString() {
        return "ContactVO{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
