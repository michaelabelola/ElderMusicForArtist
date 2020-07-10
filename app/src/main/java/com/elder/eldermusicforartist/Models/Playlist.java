package com.elder.eldermusicforartist.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Playlist implements Parcelable {
    public int id;
    public String playlistName;
    public String creatorName;

    public Playlist(int id, String playlistName, String creatorName) {
        this.id = id;
        this.playlistName = playlistName;
        this.creatorName = creatorName;
    }

    protected Playlist(Parcel in) {
        id = in.readInt();
        playlistName = in.readString();
        creatorName = in.readString();
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(playlistName);
        dest.writeString(creatorName);
    }
}
