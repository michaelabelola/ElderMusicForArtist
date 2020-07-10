package com.elder.eldermusicforartist.Models;

import androidx.annotation.IntDef;

public class CompleteRegistrationItem {
    private Values id;
    private String primaryText;

    public CompleteRegistrationItem(Values id, String primaryText) {
        this.id = id;
        this.primaryText = primaryText;
    }

    public Values getId() {
        return id;
    }

    public void setId(Values id) {
        this.id = id;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
    }

    public enum Values {
        COMPLETE_REGISTRATION_DISPLAY_PICTURE,
        COMPLETE_REGISTRATION_ARTIST_NAME
    }
}
