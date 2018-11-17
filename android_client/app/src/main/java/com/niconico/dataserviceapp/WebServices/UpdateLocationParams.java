package com.niconico.dataserviceapp.WebServices;

public class UpdateLocationParams {
    long idUser;
    String latitude;
    String longitude;
    Long currentStore;

    public UpdateLocationParams(long idUser, String latitude, String longitude, Long currentStore) {
        this.idUser = idUser;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentStore = currentStore;
    }
}