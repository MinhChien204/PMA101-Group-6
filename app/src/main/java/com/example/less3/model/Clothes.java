package com.example.less3.model;

import java.io.Serializable;

public class Clothes implements Serializable {

    private String _id;
    private String image_cloth;

    private String name_cloth;

    private int price_cloth;

    private String brand;
    private String chatlieu;
    private String mota;

    private boolean tinhtrang;

    public Clothes() {
    }

    public Clothes(String _id, String image_cloth, String name_cloth, int price_cloth, String brand, String chatlieu, String mota, boolean tinhtrang) {
        this._id = _id;
        this.image_cloth = image_cloth;
        this.name_cloth = name_cloth;
        this.price_cloth = price_cloth;
        this.brand = brand;
        this.chatlieu = chatlieu;
        this.mota = mota;
        this.tinhtrang = tinhtrang;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage_cloth() {
        return image_cloth;
    }

    public void setImage_cloth(String image_cloth) {
        this.image_cloth = image_cloth;
    }

    public String getName_cloth() {
        return name_cloth;
    }

    public void setName_cloth(String name_cloth) {
        this.name_cloth = name_cloth;
    }

    public int getPrice_cloth() {
        return price_cloth;
    }

    public void setPrice_cloth(int price_cloth) {
        this.price_cloth = price_cloth;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}