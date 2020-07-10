package com.elder.eldermusicforartist.ui.AddPage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Models.Template.MusicTemplate;
import com.elder.eldermusicforartist.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;

public class NewItemModalBottomSheet extends BottomSheetDialogFragment {
    public static final String TAG = "NewItemModalBottomSheet";
    private View rootView;
    FrameLayout materialCardView;
    //   public List<Artiste> artistes = new ArrayList<Artiste>();
    private RecyclerView recyclerView;
    private Context context;
    private int mColumnCount = 1;

    public static NewItemModalBottomSheet newInstance() {
        return new NewItemModalBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.select_edit_bottom_sheet, container, false);
        context = getContext();
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) this.getDialog();
        final BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bottomSheetDialog.getBehavior();
        //      bottomSheetBehavior.setFitToContents(false);
        //    bottomSheetBehavior.setHideable(true);

        View album = rootView.findViewById(R.id.albumV);
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container_view, new AddAlbumFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        MaterialCardView musicV = rootView.findViewById(R.id.musicV);
        musicV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




/*
                // DialogFragment.show() will take care of adding the fragment
                // in a transaction.  We also want to remove any currently showing
                // dialog, so make our own transaction and take care of that here.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                */

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container_view, new AddMusicFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
