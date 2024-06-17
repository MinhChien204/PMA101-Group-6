package com.example.less3.model;

public class Aopolo {
    private String _id;
    private String image_polo;
    private String brand_polo;

    private String name_polo;
    private int price_polo;

    private String mota_polo;

    public Aopolo() {
    }

    public Aopolo(String _id, String image_polo, String brand_polo, String name_polo, int price_polo, String mota_polo) {
        this._id = _id;
        this.image_polo = image_polo;
        this.brand_polo = brand_polo;
        this.name_polo = name_polo;
        this.price_polo = price_polo;
        this.mota_polo = mota_polo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage_polo() {
        return image_polo;
    }

    public void setImage_polo(String image_polo) {
        this.image_polo = image_polo;
    }

    public String getBrand_polo() {
        return brand_polo;
    }

    public void setBrand_polo(String brand_polo) {
        this.brand_polo = brand_polo;
    }

    public String getName_polo() {
        return name_polo;
    }

    public void setName_polo(String name_polo) {
        this.name_polo = name_polo;
    }

    public int getPrice_polo() {
        return price_polo;
    }

    public void setPrice_polo(int price_polo) {
        this.price_polo = price_polo;
    }

    public String getMota_polo() {
        return mota_polo;
    }

    public void setMota_polo(String mota_polo) {
        this.mota_polo = mota_polo;
    }
}
