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

import com.elder.eldermusicforartist.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class LoginActivity extends AppCompatActivity {
    FrameLayout loginFragHolder;
    private FragmentManager fragmentManager;
    BottomSheetBehavior bottomSheetBehavior;
    LoginFragment.signUpSwitchListener signUpSwitchListener = new LoginFragment.signUpSwitchListener() {
        @Override
        public void onSignUpSwitch(boolean b) {
/*
        SignUpModalBottomSheet signUpModalBottomSheet = SignUpModalBottomSheet.newInstance();
        signUpModalBottomSheet.show(getSupportFragmentManager(), SignUpModalBottomSheet.TAG);
 */
            bottomSheetBehavior.setFitToContents(true);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction1 = fragmentManager.beginTransaction();
        transaction1.replace(R.id.container, new LoginFragment(signUpSwitchListener));
        transaction1.addToBackStack(null);
        transaction1.commit();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.SignUpBottomSheetView, new SignUpFragment());
        transaction.addToBackStack(null);
        transaction.commit();
        ConstraintLayout SignUpBottomSheetView = findViewById(R.id.SignUpBottomSheetView);
        bottomSheetBehavior = BottomSheetBehavior.from(SignUpBottomSheetView);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        final View topViewLP = findViewById(R.id.topViewLP);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}