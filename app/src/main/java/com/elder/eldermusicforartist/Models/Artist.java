package com.elder.eldermusicforartist.Models;

public class Artist {

    public int id;
    public String name;
    public long followersCount;
    public int img;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist(int id, String name, int img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public Artist(int id, String name, int followersCount, int img) {
        this.id = id;
        this.name = name;
        this.followersCount = followersCount;
        this.img = img;
    }

}
