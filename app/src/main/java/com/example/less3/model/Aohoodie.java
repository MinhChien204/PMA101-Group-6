package com.example.less3.model;

public class Aohoodie {

    private String _id;
    private String image_hoodie;
    private String brand_hoodie;

    private String name_hoodie;
    private int price_hoodie;

    private String mota_hoodie;

    public Aohoodie() {
    }

    public Aohoodie(String _id, String image_hoodie, String brand_hoodie, String name_hoodie, int price_hoodie, String mota_hoodie) {
        this._id = _id;
        this.image_hoodie = image_hoodie;
        this.brand_hoodie = brand_hoodie;
        this.name_hoodie = name_hoodie;
        this.price_hoodie = price_hoodie;
        this.mota_hoodie = mota_hoodie;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage_hoodie() {
        return image_hoodie;
    }

    public void setImage_hoodie(String image_hoodie) {
        this.image_hoodie = image_hoodie;
    }

    public String getBrand_hoodie() {
        return brand_hoodie;
    }

    public void setBrand_hoodie(String brand_hoodie) {
        this.brand_hoodie = brand_hoodie;
    }

    public String getName_hoodie() {
        return name_hoodie;
    }

    public void setName_hoodie(String name_hoodie) {
        this.name_hoodie = name_hoodie;
    }

    public int getPrice_hoodie() {
        return price_hoodie;
    }

    public void setPrice_hoodie(int price_hoodie) {
        this.price_hoodie = price_hoodie;
    }

    public String getMota_hoodie() {
        return mota_hoodie;
    }

    public void setMota_hoodie(String mota_hoodie) {
        this.mota_hoodie = mota_hoodie;
    }
}
