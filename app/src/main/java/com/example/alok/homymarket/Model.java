package com.example.alok.homymarket;

public class Model {
    String title,base,selling,id,link,items;

    public Model(String title, String base, String selling, String id, String link, String items) {
        this.title = title;
        this.base = base;
        this.selling = selling;
        this.id = id;
        //this.userid = userid;
        //this.password = password;
        this.link = link;
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



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
