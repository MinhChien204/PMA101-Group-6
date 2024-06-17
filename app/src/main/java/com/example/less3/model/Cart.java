package com.example.less3.model;

import java.io.Serializable;

public class Cart implements Serializable {

    private String productid_item;
    private String productsize_item;
    private int productquantity_item;
    private String productimage_item;
    private String productname_item;
    private double productprice_item;
    public Cart() {
    }

    public Cart(String productid_item, String productsize_item, int productquantity_item, String productimage_item, String productname_item, double productprice_item) {
        this.productid_item = productid_item;
        this.productsize_item = productsize_item;
        this.productquantity_item = productquantity_item;
        this.productimage_item = productimage_item;
        this.productname_item = productname_item;
        this.productprice_item = productprice_item;
    }

    public String getProductid_item() {
        return productid_item;
    }

    public void setProductid_item(String productid_item) {
        this.productid_item = productid_item;
    }

    public String getProductsize_item() {
        return productsize_item;
    }

    public void setProductsize_item(String productsize_item) {
        this.productsize_item = productsize_item;
    }

    public int getProductquantity_item() {
        return productquantity_item;
    }

    public void setProductquantity_item(int productquantity_item) {
        this.productquantity_item = productquantity_item;
    }

    public String getProductimage_item() {
        return productimage_item;
    }

    public void setProductimage_item(String productimage_item) {
        this.productimage_item = productimage_item;
    }

    public String getProductname_item() {
        return productname_item;
    }

    public void setProductname_item(String productname_item) {
        this.productname_item = productname_item;
    }

    public double getProductprice_item() {
        return productprice_item;
    }

    public void setProductprice_item(double productprice_item) {
        this.productprice_item = productprice_item;
    }
}
