package com.niconico.dataserviceapp.Models;

public class Brand {
    private long id;
    private String name;
    private long typeId;

    public Brand() {}

    public Brand(long id, String name, long typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
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

    public long getTypeId() {
        return typeId;
    }
    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return name;
    }
}
