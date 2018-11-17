package com.niconico.dataserviceapp.Models;

public class Store {
    private long id;
    private String name;
    private String address;
    private String postcode;
    private String city;
    private String latitude;
    private String longitude;
    private long brandId;

    public Store() {}

    public Store(long id, String name, String address, String postcode, String city, String latitude, String longitude, long brandId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.brandId = brandId;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getBrandId() {
        return brandId;
    }
    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }
}
