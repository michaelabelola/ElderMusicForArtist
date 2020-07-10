package com.elder.eldermusicforartist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Interfaces.ItemCustomClickListener;
import com.elder.eldermusicforartist.Models.Album;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.R;
import com.elder.eldermusicforartist.ui.RIV.RoundedImageView;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import static com.elder.eldermusicforartist.Models.ItemCustomValues.ALBUM;


public class AlbumLibraryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Album> mValues;
    private final ItemCustomClickListener mListener;
    View view;

    public AlbumLibraryRecyclerViewAdapter(List<Album> items, Context context) {
        mValues = items;
        mListener = (ItemCustomClickListener) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_library_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        albumBindViewHolder((ViewHolder) holder, position, new ObjectHolder(ALBUM, mValues.get(position)), mListener);
    }

    public static void albumBindViewHolder(final ViewHolder holder, final int position, final ObjectHolder objectHolder,@Nullable final ItemCustomClickListener itemCustomClickListener) {
        Album album = (Album) objectHolder.getObject();
        holder.mItem = album;
        holder.mIdView.setText(album.name);
        holder.mContentView.setText(album.artisteName);
  //      ArtistLibraryRecyclerViewAdapter.handleRippleColor(position, (MaterialCardView) holder.mView, holder.artisteIcon);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != itemCustomClickListener) {
                    itemCustomClickListener.onItemClickInteraction(objectHolder);
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != itemCustomClickListener)
                itemCustomClickListener.onItemLongClickInteraction(objectHolder);
                return true;
            }
        });
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != itemCustomClickListener) {
                    itemCustomClickListener.onOption2ClickInteraction(objectHolder);
                }
            }
        });
        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != itemCustomClickListener) {
                    itemCustomClickListener.onOption1ClickInteraction(objectHolder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public ImageView downloadBtn, addBtn;
        public Album mItem;
        public final RoundedImageView artisteIcon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.text1);
            mContentView = view.findViewById(R.id.text2);
            addBtn = view.findViewById(R.id.addBtn);
            downloadBtn = view.findViewById(R.id.optionsIcon);
            artisteIcon = view.findViewById(R.id.artisteIcon);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
