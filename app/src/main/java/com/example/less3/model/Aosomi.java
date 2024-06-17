package com.example.less3.model;

public class Aosomi {
    private String _id;
    private String image_somi;
    private String brand_somi;

    private String name_somi;
    private int price_somi;

    private String mota_somi;

    public Aosomi() {
    }

    public Aosomi(String _id, String image_somi, String brand_somi, String name_somi, int price_somi, String mota_somi) {
        this._id = _id;
        this.image_somi = image_somi;
        this.brand_somi = brand_somi;
        this.name_somi = name_somi;
        this.price_somi = price_somi;
        this.mota_somi = mota_somi;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage_somi() {
        return image_somi;
    }

    public void setImage_somi(String image_somi) {
        this.image_somi = image_somi;
    }

    public String getBrand_somi() {
        return brand_somi;
    }

    public void setBrand_somi(String brand_somi) {
        this.brand_somi = brand_somi;
    }

    public String getName_somi() {
        return name_somi;
    }

    public void setName_somi(String name_somi) {
        this.name_somi = name_somi;
    }

    public int getPrice_somi() {
        return price_somi;
    }

    public void setPrice_somi(int price_somi) {
        this.price_somi = price_somi;
    }

    public String getMota_somi() {
        return mota_somi;
    }

    public void setMota_somi(String mota_somi) {
        this.mota_somi = mota_somi;
    }
}
