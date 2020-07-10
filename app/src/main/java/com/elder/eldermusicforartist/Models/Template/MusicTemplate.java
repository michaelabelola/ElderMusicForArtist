package com.elder.eldermusicforartist.Models.Template;

import android.net.Uri;

import com.elder.eldermusicforartist.Models.Genre;

import java.io.File;
import java.util.ArrayList;

public class MusicTemplate {
    public long id;
    public String title;
    public String aboutShortStory;
    public File coverImageFile, audioFile;
    public boolean uploaded = false;


    public String artistNames;
    public ArrayList<ArtistTemplate> artists = new ArrayList<>();
    public ArrayList<Genre> genres = new ArrayList<>();

    public Uri uri;

    public MusicTemplate(String title, String artistNames) {
        this.title = title;
        this.artistNames = artistNames;
    }

    public MusicTemplate() {

    }

    @Override
    public String toString() {
        return "MusicTemplate{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", aboutShortStory='" + aboutShortStory + '\'' +
                ", coverImageFile=" + coverImageFile +
                ", audioFile=" + audioFile +
                ", uploaded=" + uploaded +
                ", artistNames='" + artistNames + '\'' +
                ", artists=" + artists +
                ", genres=" + genres +
                ", uri=" + uri +
                '}';
    }
}
