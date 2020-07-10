package com.elder.eldermusicforartist.Interfaces;

import com.elder.eldermusicforartist.Models.ObjectHolder;

public interface ItemCustomClickListener {
    void onItemClickInteraction(ObjectHolder objectHolder);
    void onItemLongClickInteraction(ObjectHolder objectHolder);
    void onOption1ClickInteraction(ObjectHolder objectHolder);
    void onOption2ClickInteraction(ObjectHolder objectHolder);
    void onDraftItemClickInteraction(ObjectHolder objectHolder);
}
