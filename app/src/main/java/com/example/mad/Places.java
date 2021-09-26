package com.example.mad;

public class Places {
    private String placename;
    private String placetype;
    private String ownername;
    private String address;
    private String email;
    private String phone1;
    private String phone2;
    private String description;

    public Places(){

    }


    public Places(String placename, String placetype, String ownername, String address, String email, String phone1, String phone2, String description) {
        this.placename = placename;
        this.placetype = placetype;
        this.ownername = ownername;
        this.address = address;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.description = description;
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

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
