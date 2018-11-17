package com.niconico.dataserviceapp.Models;

public class StoreAvailability {
    private long id;
    private String name;
    private String address;
    private String postcode;
    private String city;
    private String latitude;
    private String longitude;
    private int quantity;
    private float distance;
    private int visitor;

    public StoreAvailability() {}

    public StoreAvailability(long id, String name, String address, String postcode, String city, String latitude, String longitude, int quantity, float distance, int visitor) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.quantity = quantity;
        this.distance = distance;
        this.visitor = visitor;
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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getVisitor() {
        return visitor;
    }
    public void setVisitor(int visitor) {
        this.visitor = visitor;
    }
}
