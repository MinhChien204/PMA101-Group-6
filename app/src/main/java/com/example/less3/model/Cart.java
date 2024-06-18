package com.example.less3.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private String _id;
    private String productid_item;
    private String productsize_item;
    private int productquantity_item;
    private String productImage_item;
    private String productName_item;
    private double productPrice_item;

    public Cart() {
    }

    public Cart(String _id, String productid_item, String productsize_item, int productquantity_item, String productImage_item, String productName_item, double productPrice_item) {
        this._id = _id;
        this.productid_item = productid_item;
        this.productsize_item = productsize_item;
        this.productquantity_item = productquantity_item;
        this.productImage_item = productImage_item;
        this.productName_item = productName_item;
        this.productPrice_item = productPrice_item;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getProductImage_item() {
        return productImage_item;
    }

    public void setProductImage_item(String productImage_item) {
        this.productImage_item = productImage_item;
    }

    public String getProductName_item() {
        return productName_item;
    }

    public void setProductName_item(String productName_item) {
        this.productName_item = productName_item;
    }

    public double getProductPrice_item() {
        return productPrice_item;
    }

    public void setProductPrice_item(double productPrice_item) {
        this.productPrice_item = productPrice_item;
    }
}
