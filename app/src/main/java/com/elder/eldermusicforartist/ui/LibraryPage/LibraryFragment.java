package com.elder.eldermusicforartist.ui.LibraryPage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elder.eldermusicforartist.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private Context context;
    private TabLayout tabView;
    private ViewPager2 viewPager;

    public LibraryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryFragment newInstance(String param1, String param2) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_library, container, false);
        context = getContext();
        loadViewPagerTabs();
        return rootView;
    }

    private void loadViewPagerTabs() {
        viewPager = rootView.findViewById(R.id.pager);
        tabView = rootView.findViewById(R.id.tabView);
        View tabViewHolder = rootView.findViewById(R.id.tabViewHolder);
        //     FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(getActivity());
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(), getLifecycle());
        viewPager.setAdapter(pagerAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabView.selectTab(tabView.getTabAt(position));
            }
        });
        tabView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        static final int PAGE_MUSIC = 0;
        static final int PAGE_ALBUMS = 1;
        static final int PAGE_UNPUBLISHED = 2;
        private int NUM_PAGES = 3;

        ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fragmentManager,
                                       @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                default:

                case PAGE_MUSIC:
                    return new SongsLibraryFragment();
                case PAGE_ALBUMS:
                    return new AlbumLibraryFragment();
                case PAGE_UNPUBLISHED:
                    return new UnpublishedLibraryFragment();
            }
        }

        static String PageName(int pageNumber) {
            switch (pageNumber) {
                case PAGE_MUSIC:
                    return "Music";
                case PAGE_ALBUMS:
                    return "Albums";
                case PAGE_UNPUBLISHED:
                    return "Unpublished";
                default:
                    return "All";
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }

    }
}