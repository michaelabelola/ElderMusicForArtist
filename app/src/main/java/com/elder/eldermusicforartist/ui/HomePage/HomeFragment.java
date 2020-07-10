package com.elder.eldermusicforartist.ui.HomePage;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elder.eldermusicforartist.Models.*;
import com.elder.eldermusicforartist.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.View.inflate;
import static com.elder.eldermusicforartist.Models.CompleteRegistrationItem.Values.*;
import static com.elder.eldermusicforartist.Models.ItemCustomValues.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewGroup scrollViewLinearLayout;
    private View rootView;
    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        scrollViewLinearLayout = rootView.findViewById(R.id.scrollViewLinearLayout);
        context = getContext();


        List<ObjectHolder> itemsMap3 = new ArrayList<ObjectHolder>();
        itemsMap3.add(new ObjectHolder(COMPLETE_REG_VAL, new CompleteRegistrationItem(COMPLETE_REGISTRATION_DISPLAY_PICTURE, "Select Dispaly Picture")));
        itemsMap3.add(new ObjectHolder(COMPLETE_REG_VAL, new CompleteRegistrationItem(COMPLETE_REGISTRATION_ARTIST_NAME, "Add Name")));

        new HomeGroupItemHandler(context,
                new HomeGroupItemHandler.Creator()
                        .newView()
                        .setPrimaryText(ItemCustomValues.typeToString(COMPLETE_REG_VAL))
                        .setItems(itemsMap3)
                        .setMoreVisible(GONE)
                        .setParentView(scrollViewLinearLayout));

        List<ObjectHolder> itemsMap = new ArrayList<ObjectHolder>();
        itemsMap.add(new ObjectHolder(STATISTICS, new StatisticsHomeItem(1, "200,000", "Subscribers")));
        itemsMap.add(new ObjectHolder(STATISTICS, new StatisticsHomeItem(2, "20,000", "Streams")));
        itemsMap.add(new ObjectHolder(STATISTICS, new StatisticsHomeItem(3, "2,000,000", "Total Stream Minutes")));
        itemsMap.add(new ObjectHolder(STATISTICS, new StatisticsHomeItem(4, "20", "Songs")));
        itemsMap.add(new ObjectHolder(STATISTICS, new StatisticsHomeItem(5, "2", "Albums")));
        itemsMap.add(new ObjectHolder(STATISTICS, new StatisticsHomeItem(5, "4", "Playlists")));

        new HomeGroupItemHandler(context,
                new HomeGroupItemHandler.Creator()
                        .newView()
                        .setPrimaryText(ItemCustomValues.typeToString(STATISTICS))
                        .setItems(itemsMap)
                        .setMainType(STATISTICS)
                        .setParentView(scrollViewLinearLayout));

        /*
        new HomeGroupView(context,
                HomeGroupView.Creator.newInstance()
                        .setTitleText("Complete Registration")
                        .setItems(itemsMap3)
                        .create(), scrollViewLinearLayout);
        */

        return rootView;
    }
}