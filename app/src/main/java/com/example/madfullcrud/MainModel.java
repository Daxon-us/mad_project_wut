package com.example.madfullcrud;

public class MainModel {

    String placename, placetype, address, description, email, ownername, phone1, phone2, iurl;

    MainModel(){

    }
    public MainModel(String placename, String placetype, String address, String description, String email, String ownername, String phone1, String phone2, String iurl) {
        this.placename = placename;
        this.placetype = placetype;
        this.address = address;
        this.description = description;
        this.email = email;
        this.ownername = ownername;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.iurl = iurl;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getPlacetype() {
        return placetype;
    }

    public void setPlacetype(String placetype) {
        this.placetype = placetype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl;
    }
}
