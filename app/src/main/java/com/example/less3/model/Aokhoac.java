package com.example.less3.model;

public class Aokhoac {

    private String _id;
    private String image_khoac;
    private String brand_khoac;

    private String name_khoac;
    private int price_khoac;

    private String mota_khoac;

    public Aokhoac() {
    }

    public Aokhoac(String _id, String image_khoac, String brand_khoac, String name_khoac, int price_khoac, String mota_khoac) {
        this._id = _id;
        this.image_khoac = image_khoac;
        this.brand_khoac = brand_khoac;
        this.name_khoac = name_khoac;
        this.price_khoac = price_khoac;
        this.mota_khoac = mota_khoac;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage_khoac() {
        return image_khoac;
    }

    public void setImage_khoac(String image_khoac) {
        this.image_khoac = image_khoac;
    }

    public String getBrand_khoac() {
        return brand_khoac;
    }

    public void setBrand_khoac(String brand_khoac) {
        this.brand_khoac = brand_khoac;
    }

    public String getName_khoac() {
        return name_khoac;
    }

    public void setName_khoac(String name_khoac) {
        this.name_khoac = name_khoac;
    }

    public int getPrice_khoac() {
        return price_khoac;
    }

    public void setPrice_khoac(int price_khoac) {
        this.price_khoac = price_khoac;
    }

    public String getMota_khoac() {
        return mota_khoac;
    }

    public void setMota_khoac(String mota_khoac) {
        this.mota_khoac = mota_khoac;
    }
}
