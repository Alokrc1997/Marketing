package com.example.alok.homymarket;

public class ModelOrder {
   private String id,items,link,title,price,size;

    public ModelOrder(String id, String items, String link, String title, String price,String size) {
        this.id = id;
        this.items = items;
        this.link = link;
        this.title = title;
        this.price = price;
        this.size=size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
