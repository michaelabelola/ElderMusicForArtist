package com.elder.eldermusicforartist.loginPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.elder.eldermusicforartist.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class LoginActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    BottomSheetBehavior bottomSheetBehavior;
    View topViewLP;
    LoginFragment.signUpSwitchListener signUpSwitchListener = new LoginFragment.signUpSwitchListener() {
        @Override
        public void onSignUpSwitch(boolean b) {
            bottomSheetBehavior.setFitToContents(true);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ConstraintLayout SignUpBottomSheetView = findViewById(R.id.SignUpBottomSheetView);
        topViewLP = (View) findViewById(R.id.dag);
        bottomSheetBehavior = BottomSheetBehavior.from(SignUpBottomSheetView);
        bottomSheetBehavior.setFitToContents(true);
        bottomSheetBehavior.setSkipCollapsed(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        topViewLP.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                topViewLP.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                bottomSheetBehavior.setPeekHeight(topViewLP.getHeight());
            }
        });
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction1 = fragmentManager.beginTransaction();
        transaction1.replace(R.id.container, new LoginFragment(signUpSwitchListener));
        transaction1.addToBackStack(null);
        transaction1.commit();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.SignUpBottomSheetView, new SignUpFragment());
        transaction.addToBackStack(null);
        transaction.commit();

        loadCarousel();

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void loadCarousel() {
        CarouselView carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(5);
        carouselView.setViewListener(position -> {

            View customView = getLayoutInflater().inflate(R.layout.carousel_view_3, null);
            //set view attributes here
            ImageView carouselImage = customView.findViewById(R.id.carouselImage);
            switch (position) {
                case 0:
                    carouselImage.setImageDrawable(getResources().getDrawable(R.drawable.cover_5));
                    break;
                case 1:
                    carouselImage.setImageDrawable(getResources().getDrawable(R.drawable.cover_1));
                    break;
                case 2:
                    carouselImage.setImageDrawable(getResources().getDrawable(R.drawable.cover_2));
                    break;
                case 3:
                    carouselImage.setImageDrawable(getResources().getDrawable(R.drawable.cover_3));
                    break;
                case 4:
                    carouselImage.setImageDrawable(getResources().getDrawable(R.drawable.cover_4));
                    break;
            }
            return customView;
        });

    }

    @Override
    public void onBackPressed() {

        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN)
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        else
            super.onBackPressed();
    }
}