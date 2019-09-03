package com.example.alok.homymarket;

public class Model {
   private String title,base,selling,id,link1,items,link2,link3,link4,descp;



    public Model(String title, String base, String selling, String id, String link1, String link2, String link3, String link4, String items, String descp) {
        this.title = title;
        this.base = base;
        this.selling = selling;
        this.id = id;
        this.link2 = link2;
        this.link3 = link3;
        this.link4 = link4;
        this.descp = descp;

        //this.userid = userid;
        //this.password = password;
        this.link1 = link1;
        this.items=items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSelling() {
        return selling;
    }

    public void setSelling(String selling) {
        this.selling = selling;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public String getLink3() {
        return link3;
    }

    public void setLink3(String link3) {
        this.link3 = link3;
    }

    public String getLink4() {
        return link4;
    }

    public void setLink4(String link4) {
        this.link4 = link4;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
