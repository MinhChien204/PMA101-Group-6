package com.example.less3.model;

public class Cart {
    private String _id;
    private String productid_item;
    private String productsize_item;
    private int productquantity_item;

    public Cart() {
    }

    public Cart(String _id, String productid_item, String productsize_item, int productquantity_item) {
        this._id = _id;
        this.productid_item = productid_item;
        this.productsize_item = productsize_item;
        this.productquantity_item = productquantity_item;
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
}
