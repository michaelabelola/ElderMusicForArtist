package com.elder.eldermusicforartist.ui.AddPage;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elder.eldermusicforartist.Adapters.DraftsRecyclerViewAdapter;
import com.elder.eldermusicforartist.Models.Album;
import com.elder.eldermusicforartist.Models.Music;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.Models.Playlist;
import com.elder.eldermusicforartist.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;

import static com.elder.eldermusicforartist.Models.ItemCustomValues.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    public List<ObjectHolder> objectHolders = new ArrayList<ObjectHolder>();
    private Context context;
    private RecyclerView recyclerView;
    private int mColumnCount = 1;
    FloatingActionButton addActionBtn;

    public AddPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPageFragment newInstance(String param1, String param2) {
        AddPageFragment fragment = new AddPageFragment();
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

        rootView = inflater.inflate(R.layout.fragment_add_page, container, false);
        FloatingActionButton addActionBtn = rootView.findViewById(R.id.addActionBtn);
        addActionBtn.setOnClickListener(v -> {
            NewItemModalBottomSheet newItemModalBottomSheet = NewItemModalBottomSheet.newInstance();
            newItemModalBottomSheet.show(getFragmentManager(), NewItemModalBottomSheet.TAG);
        });
        context = rootView.getContext();
        recyclerView = rootView.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(PLAYLIST, new Playlist(2, "Groove", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        objectHolders.add(new ObjectHolder(MUSIC, new Music("Jaho", "Davido", "www.music.com/music.mp3")));
        objectHolders.add(new ObjectHolder(ALBUM, new Album(2, "Invasion Of Privacy", "davido")));
        recyclerView.setAdapter(new DraftsRecyclerViewAdapter(objectHolders, context));

        loadCarousel();

        return rootView;
    }

    private void loadCarousel() {
        CarouselView carouselView = rootView.findViewById(R.id.carouselView);
        carouselView.setPageCount(5);
        carouselView.setViewListener(new ViewListener() {
            @Override
            public View setViewForPosition(int position) {

                View customView = getLayoutInflater().inflate(R.layout.carousel_view_1, null);
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
            }
        });

    }

}