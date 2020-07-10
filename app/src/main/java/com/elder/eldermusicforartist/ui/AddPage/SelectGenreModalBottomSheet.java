package com.elder.eldermusicforartist.ui.AddPage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Adapters.GenreSelectionRecyclerViewAdapter;
import com.elder.eldermusicforartist.Models.Genre;
import com.elder.eldermusicforartist.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class SelectGenreModalBottomSheet extends BottomSheetDialogFragment {
    public static final String TAG = "SelectGenreModalBottomSheet";
    private List<Genre> alreadyGenres = null;
    private View rootView;
    public List<GenreSelectItem> genreSelectItems = new ArrayList<GenreSelectItem>();
    public List<GenreSelectItem> genreSelectedGenres = new ArrayList<GenreSelectItem>();
    private Context context;
    private GenreSelectedResultListener mListener;
    GenreSelectionRecyclerViewAdapter genreSelectionRecyclerViewAdapter;

    public SelectGenreModalBottomSheet() {
    }

    public SelectGenreModalBottomSheet(GenreSelectedResultListener genreSelectedResultListener) {
        this.mListener = genreSelectedResultListener;
    }

    public SelectGenreModalBottomSheet(List<Genre> alreadyGenres, GenreSelectedResultListener genreSelectedResultListener) {
        this.alreadyGenres = alreadyGenres;
        this.mListener = genreSelectedResultListener;
    }

    private void handleAlreadyGenre(List<Genre> alreadyGenres) {

        alreadyGenres.forEach(genre -> {
            genreSelectItems.forEach(genreSelectItem -> {
                if (genreSelectItem.genre.id == genre.id) {
                    genreSelectItem.setSelected(true);
                    genreSelectionRecyclerViewAdapter.notifyItemChanged(genreSelectItem.position);
                    genreSelectionHandler(genreSelectItem);
                }
            });

        });
    }


    public static SelectGenreModalBottomSheet newInstance() {
        return new SelectGenreModalBottomSheet();
    }

    GenreSelectionInteractionListener genreSelectionInteractionListener = genreSelectItem -> {
        genreSelectionHandler(genreSelectItem);
    };

    private void genreSelectionHandler(GenreSelectItem genreSelectItem) {
        if (genreSelectItem.isSelected) {
            genreSelectedGenres.add(genreSelectItem);
        } else {
            for (int i = 0; i < genreSelectedGenres.size(); i++) {

                if (genreSelectItem.position == genreSelectedGenres.get(i).position) {
                    genreSelectedGenres.remove(i);
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.select_genre_bottom_sheet, container, false);
        context = getContext();
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) this.getDialog();
        MaterialButton addSelectionsBtn = rootView.findViewById(R.id.addSelectionsBtn);
        final BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bottomSheetDialog.getBehavior();
        bottomSheetBehavior.setSkipCollapsed(true);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                bottomSheetBehavior.setPeekHeight(rootView.getHeight());
            }
        });
        context = rootView.getContext();
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        addSelectionsBtn.setOnClickListener(view -> {


            ArrayList<Genre> finalGenres = new ArrayList<>();
            if (mListener != null)
                if (!genreSelectedGenres.isEmpty()) {
                    genreSelectedGenres.forEach(genreSelectItem -> finalGenres.add(genreSelectItem.genre));
                    mListener.genreSelectedResultInteraction(finalGenres);
                } else {
                    mListener.genreSelectedResultInteraction(null);
                }
        });

        genreSelectItems.add(new GenreSelectItem(new Genre(1, "R & B")));
        genreSelectItems.add(new GenreSelectItem(new Genre(2, "Funk")));
        genreSelectItems.add(new GenreSelectItem(new Genre(3, "Pop")));
        genreSelectItems.add(new GenreSelectItem(new Genre(4, "Hip-Hop")));
        genreSelectItems.add(new GenreSelectItem(new Genre(5, "Electronic / Dance")));
        genreSelectItems.add(new GenreSelectItem(new Genre(6, "Comedy")));
        genreSelectItems.add(new GenreSelectItem(new Genre(7, "Pop Culture")));
        genreSelectItems.add(new GenreSelectItem(new Genre(8, "Jazz")));
        genreSelectItems.add(new GenreSelectItem(new Genre(9, "Classical")));
        genreSelectItems.add(new GenreSelectItem(new Genre(10, "Romance")));
        genreSelectItems.add(new GenreSelectItem(new Genre(11, "Gaming")));
        genreSelectItems.add(new GenreSelectItem(new Genre(12, "Kids & Family")));
        genreSelectItems.add(new GenreSelectItem(new Genre(13, "Arab")));
        genreSelectItems.add(new GenreSelectItem(new Genre(14, "Afro")));
        genreSelectItems.add(new GenreSelectItem(new Genre(15, "Indie")));
        genreSelectItems.add(new GenreSelectItem(new Genre(16, "Focus")));
        genreSelectItems.add(new GenreSelectItem(new Genre(17, "Latin")));
        genreSelectItems.add(new GenreSelectItem(new Genre(18, "Workout")));
        genreSelectItems.add(new GenreSelectItem(new Genre(19, "Party")));
        genreSelectItems.add(new GenreSelectItem(new Genre(20, "Francophone")));
        genreSelectItems.add(new GenreSelectItem(new Genre(21, "Country")));
        genreSelectItems.add(new GenreSelectItem(new Genre(22, "Mood")));
        genreSelectItems.add(new GenreSelectItem(new Genre(23, "Chill")));
        genreSelectItems.add(new GenreSelectItem(new Genre(24, "Gaming")));
        genreSelectItems.add(new GenreSelectItem(new Genre(25, "Desi")));
        genreSelectItems.add(new GenreSelectItem(new Genre(26, "K-Pop")));
        genreSelectItems.add(new GenreSelectItem(new Genre(27, "Metal")));
        genreSelectItems.add(new GenreSelectItem(new Genre(28, "Soul")));
        genreSelectItems.add(new GenreSelectItem(new Genre(29, "Reggae")));
        genreSelectItems.add(new GenreSelectItem(new Genre(30, "Blues")));
        genreSelectItems.add(new GenreSelectItem(new Genre(31, "Tv & Movies")));
        genreSelectItems.add(new GenreSelectItem(new Genre(32, "Elder-Music Singles")));

        genreSelectionRecyclerViewAdapter = new GenreSelectionRecyclerViewAdapter(genreSelectItems, context, genreSelectionInteractionListener);
        recyclerView.setAdapter(genreSelectionRecyclerViewAdapter);
        if (alreadyGenres != null) {
            handleAlreadyGenre(alreadyGenres);
        }
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public interface GenreSelectionInteractionListener {
        void GenreSelectionInteraction(GenreSelectItem genreSelectItem);
    }

    public interface GenreSelectedResultListener {
        void genreSelectedResultInteraction(ArrayList<Genre> genres);
    }


    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        ArrayList<Genre> finalGenres = new ArrayList<>();
        if (mListener != null)
            if (!genreSelectedGenres.isEmpty()) {
                genreSelectedGenres.forEach(genreSelectItem -> finalGenres.add(genreSelectItem.genre));
                mListener.genreSelectedResultInteraction(finalGenres);
            } else {
                mListener.genreSelectedResultInteraction(null);
            }
    }

    public static class GenreSelectItem {
        int position;
        boolean isSelected = false;
        Genre genre;

        public GenreSelectItem(Genre genre) {
            this.genre = genre;
        }

        public GenreSelectItem(int position, boolean isSelected, Genre genre) {
            this.position = position;
            this.isSelected = isSelected;
            this.genre = genre;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public Genre getGenre() {
            return genre;
        }

        public void setGenre(Genre genre) {
            this.genre = genre;
        }
    }

}
