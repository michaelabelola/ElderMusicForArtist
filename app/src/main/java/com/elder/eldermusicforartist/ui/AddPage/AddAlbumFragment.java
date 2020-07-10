package com.elder.eldermusicforartist.ui.AddPage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elder.eldermusicforartist.Adapters.GenreSelectionRecyclerViewAdapter;
import com.elder.eldermusicforartist.Adapters.MusicAddRecyclerViewAdapter;
import com.elder.eldermusicforartist.MainActivity;
import com.elder.eldermusicforartist.Models.Artist;
import com.elder.eldermusicforartist.Models.Genre;
import com.elder.eldermusicforartist.Models.Music;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.Models.Template.AlbumTemplate;
import com.elder.eldermusicforartist.Models.Template.MusicTemplate;
import com.elder.eldermusicforartist.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static android.app.Activity.RESULT_OK;

public class AddAlbumFragment extends Fragment {
    private static final String TAG = "AddAlbumFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_IMAGE = 1;
    private static final int PICK_AUDIO = 2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private MaterialButton addMusicBtn, saveDraftBtn;
    MaterialCardView albumCoverBtn;
    TextInputEditText titleTV, aboutTV;
    private boolean mDataAccessPermissionGranted = false;
    private TextView genreSubTitleTV1,songsSubTitleTV;
    private ChipGroup genreChipGroup;
    private Context context;
    private ImageView coverImageView;
    private AlbumTemplate albumTemplate = new AlbumTemplate();
    MainActivity parentActivity;
    FragmentManager fragmentManager;
    FragmentTransaction transaction1;
    AddMusicFragment addMusicFragment;
    MusicAddRecyclerViewAdapter musicAddRecyclerViewAdapter;
    ArrayList<MusicTemplate> musics = new ArrayList<>();
    musicTemplateReturnListener musicTemplateReturnListener = new musicTemplateReturnListener() {
        @Override
        public void onMusicTemplateReturn(MusicTemplate musicTemplate1) {
            Log.e(TAG, musicTemplate1.toString());
            if (musicTemplate1 != null) {
                albumTemplate.musics.add(musicTemplate1);
          // transaction1.detach(addMusicFragment);
                FragmentTransaction transactionz = fragmentManager.beginTransaction();
                transactionz.detach(addMusicFragment);
                transactionz.remove(addMusicFragment);
                transactionz.commit();
                handleMusicAdded(musicTemplate1);
            }
        }
    };
    private RecyclerView recyclerView;

    private void handleMusicAdded(MusicTemplate musicTemplate1) {
        musics.add(musicTemplate1);
        musicAddRecyclerViewAdapter.notifyItemInserted(musics.size());
        songsSubTitleTV.setText(String.format(Locale.getDefault(),"(%d)", musics.size()));
    }

    public AddAlbumFragment() {
        // Required empty public constructor
    }

    SelectGenreModalBottomSheet.GenreSelectedResultListener genreSelectedResultListener = this::genresReturnResultHandler;

    public static AddAlbumFragment newInstance(String param1, String param2) {
        AddAlbumFragment fragment = new AddAlbumFragment();
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
        rootView = inflater.inflate(R.layout.fragment_add_album, container, false);
        context = getContext();
        parentActivity = (MainActivity) getActivity();
        fragmentManager = parentActivity.fragmentManager;
        coverImageView = rootView.findViewById(R.id.coverImageView);
        recyclerView = rootView.findViewById(R.id.list);
        addMusicBtn = rootView.findViewById(R.id.addMusicBtn);
        albumCoverBtn = rootView.findViewById(R.id.albumCoverBtn);
        titleTV = rootView.findViewById(R.id.titleTV);
        aboutTV = rootView.findViewById(R.id.aboutTV);
        genreChipGroup = rootView.findViewById(R.id.genreChipGroup);
        genreSubTitleTV1 = rootView.findViewById(R.id.genreSubTitleTV1);
        songsSubTitleTV = rootView.findViewById(R.id.songsSubTitleTV);
        MaterialButton addGenre = rootView.findViewById(R.id.addGenre);
        addMusicBtn.setOnClickListener(view -> addMusic());
        addGenre.setOnClickListener(v -> selectGenre());

        albumCoverBtn.setOnClickListener(view -> {
            if (dataAccessPermission()) {
                selectImage();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        musicAddRecyclerViewAdapter = new MusicAddRecyclerViewAdapter(musics, context);
        recyclerView.setAdapter(musicAddRecyclerViewAdapter);
        return rootView;
    }

    public void addMusic() {
        addMusicFragment = new AddMusicFragment(musicTemplateReturnListener);
        transaction1 = fragmentManager.beginTransaction();
        transaction1.add(R.id.fragment_container_view, addMusicFragment);
        transaction1.addToBackStack(null);
        transaction1.commit();

    }

    private void genresReturnResultHandler(ArrayList<Genre> genres) {
        albumTemplate.genres.clear();
        if (genres != null) {
            genreChipGroup.removeAllViews();
            Log.e(TAG, "**************" + genres.toString());
            albumTemplate.genres = genres;
            genreSubTitleTV1.setText(String.format(Locale.getDefault(), "(%d)", genres.size()));
            genreSubTitleTV1.setVisibility(View.VISIBLE);
            genres.forEach(genre -> {
                Chip chip = new Chip(context);
                chip.setText(genre.name);
                genreChipGroup.addView(chip);
            });
        } else {
            genreSubTitleTV1.setText("");
            genreChipGroup.removeAllViews();
            albumTemplate.genres.clear();
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Music Cover"), PICK_IMAGE);
    }

    private void selectGenre() {
        SelectGenreModalBottomSheet selectGenreModalBottomSheet;
        if (albumTemplate.genres.isEmpty())
            selectGenreModalBottomSheet = new SelectGenreModalBottomSheet(genreSelectedResultListener);
        else
            selectGenreModalBottomSheet = new SelectGenreModalBottomSheet(albumTemplate.genres, genreSelectedResultListener);
        selectGenreModalBottomSheet.show(getFragmentManager(), SelectGenreModalBottomSheet.TAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;

        if (requestCode == PICK_IMAGE && data != null) {
            Uri selectedImageUri = data.getData();

            //       File imageFile = new File(selectedImage.getPath());
            // fileSize in byte
            //  imageFile.length()
            //       int file_size = Integer.parseInt(String.valueOf(imageFile.length()/1024));

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            File coverImageFile;
            coverImageFile = new File(selectedImageUri.getPath());
            coverImageFile.setWritable(true);
            coverImageFile.setReadable(true);
            Log.e(TAG, coverImageFile.toString());
            albumTemplate.coverImageFile = coverImageFile;
            coverImageView.setImageURI(selectedImageUri);

        }
    }

    private boolean dataAccessPermission() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Log.d(TAG, "permission Granted");
                        mDataAccessPermissionGranted = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Log.d(TAG, "permission not Granted");
                        mDataAccessPermissionGranted = false;
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {
                        Log.d(TAG, "permission dunno");
                        token.continuePermissionRequest();
                    }
                })
                .check();
        return mDataAccessPermissionGranted;
    }

    public interface musicTemplateReturnListener {
        void onMusicTemplateReturn(@Nullable MusicTemplate musicTemplate);
    }
}