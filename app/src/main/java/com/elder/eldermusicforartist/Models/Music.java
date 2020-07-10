package com.elder.eldermusicforartist.Models;

import android.net.Uri;

public class Music {
    public long id;
    public String title;
    public int artisteId;
    public String artistNames;
    public boolean isDownloaded;
    public boolean isLiked;
    public int commentCount;
    public String musicCoverURL;
    public String musicURL;
    public Uri uri;

    public Music(String title, String artistNames) {
        this.title = title;
        this.artistNames = artistNames;
    }

    public Music(String title, String artistNames, String musicCoverURL) {
        this.title = title;
        this.artistNames = artistNames;
        this.musicCoverURL = musicCoverURL;
    }

}
