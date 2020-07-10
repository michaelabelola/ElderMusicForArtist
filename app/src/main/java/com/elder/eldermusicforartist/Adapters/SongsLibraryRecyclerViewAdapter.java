package com.elder.eldermusicforartist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Interfaces.ItemCustomClickListener;
import com.elder.eldermusicforartist.Models.Album;
import com.elder.eldermusicforartist.Models.Music;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.R;
import com.elder.eldermusicforartist.ui.RIV.RoundedImageView;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import static android.view.Gravity.CENTER_HORIZONTAL;
import static com.elder.eldermusicforartist.Models.ItemCustomValues.ALBUM;
import static com.elder.eldermusicforartist.Models.ItemCustomValues.CUSTOM_AD;
import static com.elder.eldermusicforartist.Models.ItemCustomValues.MUSIC;

public class SongsLibraryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ObjectHolder> mValues;
    private final ItemCustomClickListener itemCustomClickListener;
    Context context;
    boolean isMine;

    public SongsLibraryRecyclerViewAdapter(List<ObjectHolder> items, Context context) {
        this.context = context;
        this.isMine = false;
        mValues = items;
        itemCustomClickListener = (ItemCustomClickListener) context;
    }

    public SongsLibraryRecyclerViewAdapter(List<ObjectHolder> items, Context context, boolean isMine) {
        this.context = context;
        this.isMine = isMine;
        mValues = items;
        itemCustomClickListener = (ItemCustomClickListener) context;
    }

    @Override
    public int getItemViewType(int position) {

        if (mValues.get(position).getObjectType() == CUSTOM_AD) {
            return CUSTOM_AD;
        } else if (mValues.get(position).getObjectType() == ALBUM) {
            return ALBUM;
        } else {
            return MUSIC;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ALBUM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_library_item_view, parent, false);
            return new AlbumLibraryRecyclerViewAdapter.ViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.music_library_item_view, parent, false);
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mValues.get(position).getObjectType() == ALBUM) {
            AlbumLibraryRecyclerViewAdapter.albumBindViewHolder((AlbumLibraryRecyclerViewAdapter.ViewHolder) holder, position, mValues.get(position), null);
        } else {
            musicBindViewHolder((ViewHolder) holder, position, mValues.get(position), itemCustomClickListener, isMine);
        }

    }

    public static void musicBindViewHolder(final ViewHolder holder, final int position, final ObjectHolder objectHolder, final ItemCustomClickListener itemCustomClickListener, boolean isMine) {
        Music music = (Music) objectHolder.getObject();
        holder.music = music;
        holder.mIdView.setText(music.title);
        holder.mContentView.setText(music.artistNames);

        //     ArtistLibraryRecyclerViewAdapter.handleRippleColor( position, (MaterialCardView) holder.mView, holder.image_small);

        if (isMine) {
            holder.addBtn.setVisibility(View.GONE);
            holder.deleteIcon.setVisibility(View.VISIBLE);
            holder.editIcon.setVisibility(View.VISIBLE);

            holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != itemCustomClickListener) {
                        //            itemCustomClickListener.onOption2ClickInteraction(objectHolder);
                    }
                }
            });
            holder.editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != itemCustomClickListener) {
                        //         itemCustomClickListener.onOption2ClickInteraction(objectHolder);
                    }
                }
            });
        } else {
            holder.addBtn.setVisibility(View.VISIBLE);
            holder.deleteIcon.setVisibility(View.GONE);
            holder.editIcon.setVisibility(View.GONE);

            holder.addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != itemCustomClickListener) {
                        itemCustomClickListener.onOption2ClickInteraction(objectHolder);
                    }
                }
            });
        }

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
                if (null != itemCustomClickListener) {
                    itemCustomClickListener.onItemLongClickInteraction(objectHolder);
                    return true;
                }
                return false;
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
        public final ImageView downloadBtn, addBtn, deleteIcon, editIcon;
        public Music music;
        public final RoundedImageView image_small;

        public ViewHolder(View view) {
            super(view);
            view.setForegroundGravity(CENTER_HORIZONTAL);
            mView = view;
            mIdView = view.findViewById(R.id.text1);
            mContentView = view.findViewById(R.id.text2);
            downloadBtn = view.findViewById(R.id.optionsIcon);
            addBtn = view.findViewById(R.id.optionsIcon2);
            deleteIcon = view.findViewById(R.id.deleteIcon);
            editIcon = view.findViewById(R.id.editIcon);
            image_small = view.findViewById(R.id.image_small);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public class adViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public adViewHolder(View view) {
            super(view);
            mView = view;
        }

        @Override
        public String toString() {
            return super.toString() + "";
        }
    }
}
