package com.niconico.dataserviceapp.WebServices;

public interface IAPIListener<T> {
    void onSuccess(T response);
    void onError(String s);
}
