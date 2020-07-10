package com.elder.eldermusicforartist.Models;

public class Album {
    public int id;
    public String name;
    public String artisteName;

    public Album(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Album(int id, String name, String artisteName) {
        this.id = id;
        this.name = name;
        this.artisteName = artisteName;
    }

}
