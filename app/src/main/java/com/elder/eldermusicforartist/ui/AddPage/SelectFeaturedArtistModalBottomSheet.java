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
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Adapters.ArtistSelectionRecyclerViewAdapter;
import com.elder.eldermusicforartist.Adapters.GenreSelectionRecyclerViewAdapter;
import com.elder.eldermusicforartist.Adapters.SelectArtistAdapter;
import com.elder.eldermusicforartist.Models.Artist;
import com.elder.eldermusicforartist.Models.Genre;
import com.elder.eldermusicforartist.Models.Template.ArtistTemplate;
import com.elder.eldermusicforartist.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class SelectFeaturedArtistModalBottomSheet extends BottomSheetDialogFragment {
    public static final String TAG = "SelectFeaturedArtistModalBottomSheet";
    private ArrayList<ArtistTemplate> alreadyArtists = null;
    private View rootView;
    private Context context;
    ArrayList<ArtistTemplate> artists = new ArrayList<>();
    ArtistSelectionRecyclerViewAdapter artistSelectionRecyclerViewAdapter;
    ArtistSelectedResultListener mListener;
    MaterialButton addSelectionsBtn;
    private RecyclerView recyclerView;
    private TextView titleTV;

    ArtistSelectionInteractionListener artistSelectionInteractionListener = new ArtistSelectionInteractionListener() {
        @Override
        public void artistSelectionInteraction(ArtistTemplate artist) {
            showArtistQuickInfo();
        }

        @Override
        public void artistDeleteSelectionInteraction(int pos, ArtistTemplate artist) {
            deleteArtistSelection(pos, artist);
        }
    };

    public SelectFeaturedArtistModalBottomSheet() {
    }

    public SelectFeaturedArtistModalBottomSheet(ArtistSelectedResultListener artistSelectedResultListener) {
        this.mListener = artistSelectedResultListener;
    }

    public SelectFeaturedArtistModalBottomSheet(ArrayList<ArtistTemplate> alreadyArtists, ArtistSelectedResultListener artistSelectedResultListener) {
        this.alreadyArtists = alreadyArtists;
        this.mListener = artistSelectedResultListener;
    }

    public static SelectFeaturedArtistModalBottomSheet newInstance() {
        return new SelectFeaturedArtistModalBottomSheet();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.select_feature_artist_bottom_sheet, container, false);
        context = getContext();
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) this.getDialog();

        recyclerView = rootView.findViewById(R.id.list);
        titleTV = rootView.findViewById(R.id.titleTV);
        addSelectionsBtn = rootView.findViewById(R.id.addSelectionsBtn);
        final BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bottomSheetDialog.getBehavior();
        bottomSheetBehavior.setFitToContents(false);
        bottomSheetBehavior.setSkipCollapsed(true);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                bottomSheetBehavior.setPeekHeight(rootView.getHeight());
            }
        });
        loadAutoComplete();
        addSelectionsBtn.setOnClickListener(view -> {
            if (mListener != null)
                mListener.artistSelectedResultInteraction(artists);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (alreadyArtists != null) {
            artists.addAll(alreadyArtists);
        }

        /*
        for (int i = 0; i < alreadyArtists.size(); i++) {
            artists.add(alreadyArtists.get(i));
            addToArtistsList(alreadyArtists.get(i));
        }
*/
        if (!artists.isEmpty())
            titleTV.setVisibility(View.VISIBLE);
        artistSelectionRecyclerViewAdapter = new ArtistSelectionRecyclerViewAdapter(artists, context, artistSelectionInteractionListener);
        recyclerView.setAdapter(artistSelectionRecyclerViewAdapter);

        return rootView;
    }

    private void loadAutoComplete() {
        ArrayList<Artist> artistArrayList;
        AutoCompleteTextView customAutoCompleteTextView;
        SelectArtistAdapter selectArtistAdapter;

        artistArrayList = new ArrayList<>();
        artistArrayList.add(new ArtistTemplate(1, "Davido"));
        artistArrayList.add(new ArtistTemplate(1, "Wizkid"));
        artistArrayList.add(new ArtistTemplate(1, "Olamide"));
        artistArrayList.add(new ArtistTemplate(1, "Rema"));
        artistArrayList.add(new ArtistTemplate(1, "Chris Brown"));
        artistArrayList.add(new ArtistTemplate(1, "Dababy"));
        artistArrayList.add(new ArtistTemplate(1, "Lil Baby"));
        artistArrayList.add(new ArtistTemplate(1, "Fireboy"));
        artistArrayList.add(new ArtistTemplate(1, "Joeboy"));


        selectArtistAdapter = new SelectArtistAdapter(context, R.layout.select_artist_item_view, artistArrayList);
        customAutoCompleteTextView = rootView.findViewById(R.id.customAutoCompleteTextView);
        customAutoCompleteTextView.setThreshold(1);
        customAutoCompleteTextView.setAdapter(selectArtistAdapter);

        customAutoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            ArtistTemplate artist = (ArtistTemplate) adapterView.getItemAtPosition(i);
            customAutoCompleteTextView.setText("");
            rootView.requestFocus();
            addToArtistsList(artist);
        });

    }

    private void addToArtistsList(ArtistTemplate artist) {
        artists.add(artist);
        artistSelectionRecyclerViewAdapter.notifyItemInserted(artists.size());
        titleTV.setVisibility(View.VISIBLE);
    }

    private void deleteArtistSelection(int pos, Artist artist) {
        artists.remove(artist);
        artistSelectionRecyclerViewAdapter.notifyItemRemoved(pos);
        if (artists.isEmpty())
            titleTV.setVisibility(View.GONE);
    }

    private void showArtistQuickInfo() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public interface ArtistSelectionInteractionListener {
        void artistSelectionInteraction(ArtistTemplate artist);

        void artistDeleteSelectionInteraction(int pos, ArtistTemplate artist);
    }

    public interface ArtistSelectedResultListener {
        void artistSelectedResultInteraction(ArrayList<ArtistTemplate> artists);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mListener != null)
            mListener.artistSelectedResultInteraction(artists);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
