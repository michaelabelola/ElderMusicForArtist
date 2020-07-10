package com.elder.eldermusicforartist.Models.Template;

import com.elder.eldermusicforartist.Models.Artist;

public class ArtistTemplate extends Artist {
    private boolean registered = true;

    public ArtistTemplate(int id, String name) {
        super(id, name);
    }

    public ArtistTemplate(int id, String name, boolean registered) {
        super(id, name);
        this.registered = registered;
    }

    public ArtistTemplate(int id, String name, int img, boolean registered) {
        super(id, name, img);
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "ArtistTemplate{" +
                "registered=" + registered +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", followersCount=" + followersCount +
                ", img=" + img +
                '}';
    }
}
