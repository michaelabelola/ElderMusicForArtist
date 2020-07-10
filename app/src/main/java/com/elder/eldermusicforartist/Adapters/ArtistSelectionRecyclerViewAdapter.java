package com.elder.eldermusicforartist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Models.Template.ArtistTemplate;
import com.elder.eldermusicforartist.R;
import com.elder.eldermusicforartist.ui.AddPage.SelectFeaturedArtistModalBottomSheet;

import java.util.List;

public class ArtistSelectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ArtistTemplate> mValues;
    private final Context context;
    private SelectFeaturedArtistModalBottomSheet.ArtistSelectionInteractionListener mListener;

    public ArtistSelectionRecyclerViewAdapter(List<ArtistTemplate> creator, Context context, SelectFeaturedArtistModalBottomSheet.ArtistSelectionInteractionListener mListener) {
        this.mValues = creator;
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenreListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_artist_item_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        handleView(holder, position);
    }

    public void handleView(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GenreListViewHolder viewHolder = (GenreListViewHolder) holder;
        ArtistTemplate artist = mValues.get(position);

        viewHolder.mItem = artist;
        viewHolder.text1.setText(artist.name);
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.artistSelectionInteraction(artist);
            }
        });
        viewHolder.deleteImage.setVisibility(View.VISIBLE);
        viewHolder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.artistDeleteSelectionInteraction(viewHolder.getAdapterPosition(), artist);
            }
        });

        // CompleteRegistrationItem completeRegistrationItem = (CompleteRegistrationItem) mValues.get(position).getObject();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class GenreListViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView text1;
        ArtistTemplate mItem;
        final ImageView deleteImage;

        GenreListViewHolder(View view) {
            super(view);
            mView = view;
            text1 = view.findViewById(R.id.text1);
            deleteImage = view.findViewById(R.id.deleteImage);
        }

    }

}
