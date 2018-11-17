package com.niconico.dataserviceapp.WebServices;

public class SearchStoreParams {

    String latitude;
    String longitude;
    Integer distance;
    Long itemId;

    public SearchStoreParams(String latitude, String longitude, Integer distance, Long itemId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.itemId = itemId;
    }
}
