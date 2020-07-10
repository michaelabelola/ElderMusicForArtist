package com.elder.eldermusicforartist.Models.Template;

import android.net.Uri;

import com.elder.eldermusicforartist.Models.Genre;

import java.io.File;
import java.util.ArrayList;

public class AlbumTemplate {
    public long id;
    public String title;
    public String aboutShortStory;
    public File coverImageFile;
    boolean uploaded = false;
    boolean published = false;


    public String artistNames;
    public ArrayList<MusicTemplate> musics = new ArrayList<>();
    public ArrayList<Genre> genres = new ArrayList<>();

    public Uri uri;

    public AlbumTemplate(String title, String artistNames) {
        this.title = title;
        this.artistNames = artistNames;
    }

    public AlbumTemplate() {

    }

    @Override
    public String toString() {
        return "AlbumTemplate{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", aboutShortStory='" + aboutShortStory + '\'' +
                ", coverImageFile=" + coverImageFile +
                ", uploaded=" + uploaded +
                ", published=" + published +
                ", artistNames='" + artistNames + '\'' +
                ", artists=" + musics +
                ", genres=" + genres +
                ", uri=" + uri +
                '}';
    }
}
