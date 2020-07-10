package com.elder.eldermusicforartist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Interfaces.ItemCustomClickListener;
import com.elder.eldermusicforartist.Models.Album;
import com.elder.eldermusicforartist.Models.Music;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.Models.Playlist;
import com.elder.eldermusicforartist.R;

import java.util.List;

import static com.elder.eldermusicforartist.Models.ItemCustomValues.*;

public class DraftsRecyclerViewAdapter extends RecyclerView.Adapter<DraftsRecyclerViewAdapter.ViewHolder> {

    private final List<ObjectHolder> mValues;
    private final ItemCustomClickListener mListener;
    private final Context mContext;
    View view;

    public DraftsRecyclerViewAdapter(List<ObjectHolder> items, Context context) {
        mValues = items;
        mContext = context;
        mListener = (ItemCustomClickListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.draft_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ObjectHolder oHolder = mValues.get(position);
        holder.objectHolder = oHolder;
        if (oHolder.getObjectType() == ALBUM) {
            Album album = (Album) oHolder.getObject();
            holder.text1.setText(album.name);
            holder.text2.setText("Album Draft");
        } else if (oHolder.getObjectType() == MUSIC) {
            Music music = (Music) oHolder.getObject();
            holder.text1.setText(music.title);
            holder.text2.setText("Single Draft");
        } else if (oHolder.getObjectType() == PLAYLIST) {
            Playlist playlist = (Playlist) oHolder.getObject();
            holder.text1.setText(playlist.playlistName);
            holder.text2.setText("Playlist Draft");
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onDraftItemClickInteraction(mValues.get(position));
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    mListener.onDraftItemClickInteraction(mValues.get(position));
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView text1, text2;
        public ObjectHolder objectHolder;
        public final ImageView image_small;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            text1 = (TextView) view.findViewById(R.id.text1);
            text2 = (TextView) view.findViewById(R.id.text2);
            image_small = (ImageView) view.findViewById(R.id.image_small);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + text2.getText() + "'";
        }
    }
}
