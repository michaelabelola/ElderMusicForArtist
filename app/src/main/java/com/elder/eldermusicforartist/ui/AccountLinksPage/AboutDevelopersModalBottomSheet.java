package com.elder.eldermusicforartist.ui.AccountLinksPage;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.elder.eldermusicforartist.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.MaterialCardView;

public class AboutDevelopersModalBottomSheet extends BottomSheetDialogFragment {
    public static final String TAG = "AboutDevelopersModalBottomSheet";
    private View rootView;
    MaterialCardView iGV,twitterV,emailV;

    public static AboutDevelopersModalBottomSheet newInstance() {
        return new AboutDevelopersModalBottomSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.about_developers_bottom_sheet, container, false);

        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) this.getDialog();
        final BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bottomSheetDialog.getBehavior();

        //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //   bottomSheetBehavior.setHideable(false);
        //  bottomSheetBehavior.setFitToContents(false);

        iGV = rootView.findViewById(R.id.iGV);
        twitterV = rootView.findViewById(R.id.twitterV);
        emailV = rootView.findViewById(R.id.emailV);
        iGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
