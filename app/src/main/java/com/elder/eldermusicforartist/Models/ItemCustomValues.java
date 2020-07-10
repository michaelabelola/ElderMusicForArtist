package com.elder.eldermusicforartist.Models;

public class ItemCustomValues {

    public static final int MUSIC = 1;
    public static final int ALBUM = 2;
    public static final int ARTIST = 3;
    public static final int CUSTOM_AD = 5;
    public static final int ACCOUNT_LINK = 6;
    public static final int PLAYLIST = 7;
    public static final int VIDEO = 8;
    public static final int GENRE = 9;
    public static final int HOME_ITEM = 10;

    public static final int FRIEND = 11;
    public static final int TOP_TRENDING = 12;
    public static final int FAVOURITE = 13;
    public static final int SUGGESTED = 14;
    public static final int NEW_RELEASE = 15;
    public static final int RECENTLY_PLAYED = 16;
    public static final int CUSTOM_LAYOUT = 17;
    public static final int ARTISTE_PAGE_SUGGESTION = 18;
    public static final int COMPLETE_REG_VAL = 19;
    public static final int STATISTICS = 20;


    public int dataType;

    public ItemCustomValues(int dataType) {
        this.dataType = dataType;
    }

    public static String typeToString(int dataType) {
        switch (dataType) {
            case MUSIC:
                return "Music";
            case ALBUM:
                return "Album";
            case ARTIST:
                return "Artiste";
            case PLAYLIST:
                return "Playlist";
            case VIDEO:
                return "Video";
            case CUSTOM_AD:
                return "AD";
            case GENRE:
                return "Genre";
            case HOME_ITEM:
                return "Home Item";
            case FRIEND:
                return "Friends";
            case TOP_TRENDING:
                return "Top Trending";
            case FAVOURITE:
                return "Favourites";
            case SUGGESTED:
                return "Suggestions";
            case NEW_RELEASE:
                return "New Release";
            case RECENTLY_PLAYED:
                return "Recently Played";
            case COMPLETE_REG_VAL:
                return "Complete Registration";
            case STATISTICS:
                return "Stat";
            default:
                return "Unknown";
        }
    }
}
