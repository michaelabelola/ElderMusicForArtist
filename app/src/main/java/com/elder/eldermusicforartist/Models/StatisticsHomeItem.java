package com.elder.eldermusicforartist.Models;

public class StatisticsHomeItem {
    private int id;
    private String primaryText;
    private String secondaryText;

    public StatisticsHomeItem() {
    }

    public StatisticsHomeItem(int id, String primaryText) {
        this.id = id;
        this.primaryText = primaryText;
    }

    public StatisticsHomeItem(int id, String primaryText, String secondaryText) {
        this.id = id;
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }
}
