package com.elder.eldermusicforartist.Models;

public class ObjectHolder {
    int objectType;
    Object object;

    public ObjectHolder(int objectType, Object object) {
        this.objectType = objectType;
        this.object = object;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
