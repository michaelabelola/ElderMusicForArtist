package com.elder.eldermusicforartist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Models.Genre;
import com.elder.eldermusicforartist.R;
import com.elder.eldermusicforartist.ui.AddPage.SelectGenreModalBottomSheet;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.List;

public class GenreSelectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SelectGenreModalBottomSheet.GenreSelectItem> mValues;
    private final Context context;
    private SelectGenreModalBottomSheet.GenreSelectionInteractionListener mListener;

    public GenreSelectionRecyclerViewAdapter(List<SelectGenreModalBottomSheet.GenreSelectItem> creator, Context context, SelectGenreModalBottomSheet.GenreSelectionInteractionListener genreSelectionInteractionListener) {
        this.mValues = creator;
        this.context = context;
        this.mListener = genreSelectionInteractionListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenreListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_select_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        handleView(holder, position);
    }

    public void handleView(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GenreListViewHolder viewHolder = (GenreListViewHolder) holder;
        SelectGenreModalBottomSheet.GenreSelectItem genreSelectItem = mValues.get(position);
        genreSelectItem.setPosition(position);
        Genre genre = genreSelectItem.getGenre();
if (genreSelectItem.isSelected()) {
    viewHolder.checkBox.setChecked(true);
}
        viewHolder.mItem = genre;
        viewHolder.text1.setText(genre.name);

        viewHolder.mView.setOnClickListener(view -> {
            if (viewHolder.checkBox.isChecked()) {
                viewHolder.checkBox.setChecked(false);
                genreSelectItem.setSelected(false);
                mListener.GenreSelectionInteraction(genreSelectItem);
            }
            else {
                viewHolder.checkBox.setChecked(true);
                genreSelectItem.setSelected(true);
                mListener.GenreSelectionInteraction(genreSelectItem);
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
        Genre mItem;
        final MaterialCheckBox checkBox;

        GenreListViewHolder(View view) {
            super(view);
            mView = view;
            text1 = view.findViewById(R.id.text1);
            checkBox = view.findViewById(R.id.checkBox);
        }

    }

}
