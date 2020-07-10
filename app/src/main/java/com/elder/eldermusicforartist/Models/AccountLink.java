package com.elder.eldermusicforartist.Models;

import androidx.annotation.Nullable;

public class AccountLink {
    public final static int ACCOUNT_LINK_ADS = 1;
    public final static int ACCOUNT_LINK_PROFILE = 2;
    public final static int ACCOUNT_LINK_MUSIC_PAGE = 3;
    public final static int ACCOUNT_LINK_ROYALTY_MANAGER = 4;
    public final static int ACCOUNT_LINK_PAYMENT = 5;
    public final static int ACCOUNT_LINK_CONTACT_US = 6;
    public final static int ACCOUNT_LINK_THEME = 7;
    public final static int ACCOUNT_LINK_SETTINGS = 8;
    public final static int ACCOUNT_LINK_ABOUT_DEVELOPERS = 9;
    public final static int ACCOUNT_LINK_PRIVACY_POLICY = 10;
    public final static int ACCOUNT_LINK_FAMILY_MANAGER = 11;
    public final static int ACCOUNT_LINK_PAYMENT_HISTORY = 12;
    public final static int ACCOUNT_LINK_DOWNLOAD = 13;
    public int id;
    public String primaryText;
    public String text;
    public int image;

    public AccountLink(int id, String primaryText, String text) {
        this.id = id;
        this.primaryText = primaryText;
        this.text = text;
    }

    public AccountLink(int id, String primaryText, String text, @Nullable int image) {
        this.id = id;
        this.primaryText = primaryText;
        this.text = text;
        this.image = image;
    }
}
