package com.example.less3.model;

public class Address {
    private String _id;
    private String nameAddress;
    private String phoneAddress;
    private String locationAddress;

    public Address() {
    }

    public Address(String _id, String nameAddress, String phoneAddress, String locationAddress) {
        this._id = _id;
        this.nameAddress = nameAddress;
        this.phoneAddress = phoneAddress;
        this.locationAddress = locationAddress;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public String getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(String phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
