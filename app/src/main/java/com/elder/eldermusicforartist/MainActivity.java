package com.elder.eldermusicforartist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.elder.eldermusicforartist.Interfaces.HomeItemClickListener;
import com.elder.eldermusicforartist.Interfaces.ItemCustomClickListener;
import com.elder.eldermusicforartist.Models.AccountLink;
import com.elder.eldermusicforartist.Models.CompleteRegistrationItem;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.Models.StatisticsHomeItem;
import com.elder.eldermusicforartist.ui.AccountLinksPage.AboutDevelopersModalBottomSheet;
import com.elder.eldermusicforartist.ui.AccountLinksPage.AccountLinksFragment;
import com.elder.eldermusicforartist.ui.AddPage.AddAlbumFragment;
import com.elder.eldermusicforartist.ui.AddPage.AddMusicFragment;
import com.elder.eldermusicforartist.ui.AddPage.AddPageFragment;
import com.elder.eldermusicforartist.ui.AddPage.SelectFeaturedArtistModalBottomSheet;
import com.elder.eldermusicforartist.ui.HomePage.HomeFragment;
import com.elder.eldermusicforartist.ui.LibraryPage.LibraryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Objects;

import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_ABOUT_DEVELOPERS;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_CONTACT_US;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_DOWNLOAD;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_MUSIC_PAGE;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_PAYMENT;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_PRIVACY_POLICY;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_PROFILE;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_SETTINGS;
import static com.elder.eldermusicforartist.Models.ItemCustomValues.COMPLETE_REG_VAL;

public class MainActivity extends AppCompatActivity implements HomeItemClickListener, ItemCustomClickListener, AccountLinksFragment.OnAccountLinksInteractionListener {

    private BottomNavigationView navView;
    public FragmentManager fragmentManager;
    private ConstraintLayout fragment_container_view;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.btm_navigation_music_home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.btm_navigation_music_storage:
                    loadFragment(new LibraryFragment());
                    return true;
                case R.id.btm_navigation_music_add:
                    loadFragment(new AddPageFragment());
                    return true;
                case R.id.btm_navigation_music_account:
                    loadFragment(new AccountLinksFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        fragmentManager = getSupportFragmentManager();
        fragment_container_view = findViewById(R.id.fragment_container_view);

        FragmentTransaction transaction1 = fragmentManager.beginTransaction();
        transaction1.replace(R.id.fragment_container_view, new HomeFragment());
        transaction1.addToBackStack(null);
        transaction1.commit();

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction1;
        transaction1 = fragmentManager.beginTransaction();
        transaction1.replace(R.id.fragment_container_view, fragment);
        transaction1.addToBackStack(null);
        transaction1.commit();
    }

    @Override
    public void onHomeItemClickInteraction(ObjectHolder objectHolder) {

        if (objectHolder.getObject() instanceof CompleteRegistrationItem) {
            CompleteRegistrationItem completeRegistrationItem = (CompleteRegistrationItem) objectHolder.getObject();
            Toast.makeText(this, completeRegistrationItem.getPrimaryText(), Toast.LENGTH_SHORT).show();
        }

        if (objectHolder.getObject() instanceof StatisticsHomeItem) {
            StatisticsHomeItem statisticsHomeItem = (StatisticsHomeItem) objectHolder.getObject();
            Toast.makeText(this, statisticsHomeItem.getPrimaryText(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onHomeItemLongClickInteraction(ObjectHolder objectHolder) {

    }

    @Override
    public void onItemClickInteraction(ObjectHolder objectHolder) {

    }

    @Override
    public void onItemLongClickInteraction(ObjectHolder objectHolder) {

    }

    @Override
    public void onOption1ClickInteraction(ObjectHolder objectHolder) {

    }

    @Override
    public void onOption2ClickInteraction(ObjectHolder objectHolder) {

    }

    @Override
    public void onDraftItemClickInteraction(ObjectHolder objectHolder) {

    }

    @Override
    public void onAccountLinksInteraction(AccountLink accountLink, int type) {
        switch (Objects.requireNonNull(accountLink).id) {
            case ACCOUNT_LINK_PROFILE:
                //         loadFragment(new ProfileFragment());
                return;
            case ACCOUNT_LINK_PAYMENT:
       //         loadFragment(new PaymentFragment());
                return;
            case ACCOUNT_LINK_ABOUT_DEVELOPERS:
                AboutDevelopersModalBottomSheet aboutDevelopersModalBottomSheet = AboutDevelopersModalBottomSheet.newInstance();
                aboutDevelopersModalBottomSheet.show(getSupportFragmentManager(),
                        AboutDevelopersModalBottomSheet.TAG);
                return;
            case ACCOUNT_LINK_PRIVACY_POLICY:
                return;
        }
    }
}