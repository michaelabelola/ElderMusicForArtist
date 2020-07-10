package com.elder.eldermusicforartist.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Models.Template.MusicTemplate;
import com.elder.eldermusicforartist.R;
import com.elder.eldermusicforartist.ui.RIV.RoundedImageView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MusicAddRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MusicTemplate> mValues;
    private final Context context;
    //  private SelectGenreModalBottomSheet.GenreSelectionInteractionListener mListener;

    public MusicAddRecyclerViewAdapter(List<MusicTemplate> creator, Context context) {
        this.mValues = creator;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddMusicABViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_music_item_view_3, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        handleView(holder, position);
    }

    public void handleView(@NonNull RecyclerView.ViewHolder holder, final int position) {
        AddMusicABViewHolder viewHolder = (AddMusicABViewHolder) holder;
        MusicTemplate musicTemplate = mValues.get(position);
        viewHolder.mItem = musicTemplate;
        viewHolder.trackNumberTV.setText(String.valueOf(position + 1));
        viewHolder.text1.setText(musicTemplate.title);
        viewHolder.text2.setText(musicTemplate.aboutShortStory);
        if (musicTemplate.uploaded) {
            viewHolder.uploadedIcon.setImageTintList(ColorStateList.valueOf(context.getColor(R.color.colorGreen)));
        } else {
            viewHolder.uploadedIcon.setImageTintList(ColorStateList.valueOf(context.getColor(R.color.colorRed)));
        }

        if (musicTemplate.coverImageFile != null) {
            Chip chip = new Chip(context);
            chip.setText(R.string.cover_image);
            chip.setClickable(false);
            viewHolder.chip_group.addView(chip);
        }
        if (musicTemplate.audioFile != null) {
            Chip chip = new Chip(context);
            chip.setText(R.string.audio_file);
            chip.setClickable(false);
            viewHolder.chip_group.addView(chip);
        }


        musicTemplate.genres.forEach(genre -> {
            Chip chip = new Chip(context);
            chip.setText(genre.name);
            chip.setClickable(false);
            viewHolder.chip_group.addView(chip);
        });
        musicTemplate.artists.forEach(artistTemplate -> {
            Chip chip = new Chip(context);
            chip.setText(artistTemplate.name);
            chip.setClickable(false);
            viewHolder.chip_group.addView(chip);
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class AddMusicABViewHolder extends RecyclerView.ViewHolder {
        MusicTemplate mItem;
        final View mView;
        final TextView text1, text2, trackNumberTV;
        RoundedImageView image_small;
        ImageView uploadedIcon;
        ChipGroup chip_group;

        AddMusicABViewHolder(View view) {
            super(view);
            mView = view;
            trackNumberTV = view.findViewById(R.id.trackNumberTV);
            text1 = view.findViewById(R.id.text1);
            text2 = view.findViewById(R.id.text2);
            image_small = view.findViewById(R.id.image_small);
            uploadedIcon = view.findViewById(R.id.uploadedIcon);
            chip_group = view.findViewById(R.id.chip_group);
        }

    }

}
