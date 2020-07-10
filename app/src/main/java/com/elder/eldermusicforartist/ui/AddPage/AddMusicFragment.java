package com.elder.eldermusicforartist.ui.AddPage;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.elder.eldermusicforartist.Models.Genre;
import com.elder.eldermusicforartist.Models.Template.ArtistTemplate;
import com.elder.eldermusicforartist.Models.Template.MusicTemplate;
import com.elder.eldermusicforartist.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.checkbox.MaterialCheckBox;
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
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.grpc.internal.LogExceptionRunnable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMusicFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_IMAGE = 1;
    private static final int PICK_AUDIO = 2;
    public static final String TAG = "AddMusicFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private ChipGroup genreChipGroup, artistsChipGroup;
    private MusicTemplate musicTemplate = new MusicTemplate();
    private Context context;
    private boolean mDataAccessPermissionGranted = false;
    private ImageView coverImageView;
    private TextInputEditText titleTV, detailsTV;
    private MaterialCheckBox uploadNowCheckBox;
    private MaterialButton publishBtn;
    TextView genreSubTitleTV1, artistsSubTitleTV2, fileLinkTV, fileSizeTV, fileLengthTV;
    AddAlbumFragment.musicTemplateReturnListener musicTemplateReturnListener = null;

    public AddMusicFragment() {
        // Required empty public constructor
    }

    public AddMusicFragment(AddAlbumFragment.musicTemplateReturnListener musicTemplateReturnListener) {
        this.musicTemplateReturnListener = musicTemplateReturnListener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMusicFragment newInstance(String param1, String param2) {
        AddMusicFragment fragment = new AddMusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    SelectFeaturedArtistModalBottomSheet.ArtistSelectedResultListener artistSelectedResultListener = artists -> {
        artistsReturnResultHandler(artists);
    };

    SelectGenreModalBottomSheet.GenreSelectedResultListener genreSelectedResultListener = genres -> {
        genresReturnResultHandler(genres);
    };

    private void artistsReturnResultHandler(ArrayList<ArtistTemplate> artists) {
        musicTemplate.artists.clear();
        if (artists != null) {
            artistsChipGroup.removeAllViews();
            Log.e(TAG, "**************" + artists.toString());
            musicTemplate.artists = artists;
            artistsSubTitleTV2.setText(String.format(Locale.getDefault(), "(%d)", artists.size()));
            artistsSubTitleTV2.setVisibility(View.VISIBLE);
            artists.forEach(genre -> {
                Chip chip = new Chip(context);
                chip.setText(genre.name);
                artistsChipGroup.addView(chip);
            });
        } else {
            artistsSubTitleTV2.setText("");
            artistsSubTitleTV2.setVisibility(View.GONE);
            artistsChipGroup.removeAllViews();
            musicTemplate.artists.clear();
        }
    }

    private void genresReturnResultHandler(ArrayList<Genre> genres) {
        musicTemplate.genres.clear();
        if (genres != null) {
            genreChipGroup.removeAllViews();
            Log.e(TAG, "**************" + genres.toString());
            musicTemplate.genres = genres;
            genreSubTitleTV1.setText(String.format(Locale.getDefault(), "(%d)", genres.size()));
            genreSubTitleTV1.setVisibility(View.VISIBLE);
            genres.forEach(genre -> {
                Chip chip = new Chip(context);
                chip.setText(genre.name);
                genreChipGroup.addView(chip);
            });
        } else {
            genreSubTitleTV1.setText("");
            genreSubTitleTV1.setVisibility(View.GONE);
            genreChipGroup.removeAllViews();
            musicTemplate.genres.clear();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_music, container, false);
        context = getContext();
        MaterialCardView addMusicCoverBtn = rootView.findViewById(R.id.addMusicCoverBtn);
        genreChipGroup = rootView.findViewById(R.id.genreChipGroup);
        genreSubTitleTV1 = rootView.findViewById(R.id.genreSubTitleTV1);
        artistsChipGroup = rootView.findViewById(R.id.artistsChipGroup);
        artistsSubTitleTV2 = rootView.findViewById(R.id.artistsSubTitleTV2);
        coverImageView = rootView.findViewById(R.id.coverImageView);
        uploadNowCheckBox = rootView.findViewById(R.id.uploadNowCheckBox);
        publishBtn = rootView.findViewById(R.id.publishBtn);
        MaterialButton saveBtn = rootView.findViewById(R.id.saveBtn);
        titleTV = rootView.findViewById(R.id.titleTV);
        detailsTV = rootView.findViewById(R.id.detailsTV);
        fileLinkTV = rootView.findViewById(R.id.fileLinkTV);
        fileSizeTV = rootView.findViewById(R.id.fileSizeTV);
        fileLengthTV = rootView.findViewById(R.id.fileLengthTV);
        MaterialButton addMusicFile = rootView.findViewById(R.id.addMusicFileBtn);
        MaterialButton addFeatureArtistBtn = rootView.findViewById(R.id.addFeatureArtistBtn);
        MaterialButton addGenre = rootView.findViewById(R.id.addGenre);
        addMusicCoverBtn.setOnClickListener(v -> {
            if (dataAccessPermission()) {
                selectImage();
            }
        });
        addMusicFile.setOnClickListener(view -> {
            if (dataAccessPermission()) {
                selectMusicFile();
            }
        });

        addGenre.setOnClickListener(v -> selectGenre());

        addFeatureArtistBtn.setOnClickListener(v -> selectFeaturedArtists());

        saveBtn.setOnClickListener(v -> {
            save();
        });
        return rootView;
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void selectMusicFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("audio/mpeg");
        startActivityForResult(Intent.createChooser(intent, "Select Audio File"), PICK_AUDIO);
    }

    private void selectGenre() {
        SelectGenreModalBottomSheet selectGenreModalBottomSheet;
        if (musicTemplate.genres.isEmpty())
            selectGenreModalBottomSheet = new SelectGenreModalBottomSheet(genreSelectedResultListener);
        else
            selectGenreModalBottomSheet = new SelectGenreModalBottomSheet(musicTemplate.genres, genreSelectedResultListener);
        selectGenreModalBottomSheet.show(getFragmentManager(), SelectGenreModalBottomSheet.TAG);
    }

    private void selectFeaturedArtists() {
        SelectFeaturedArtistModalBottomSheet selectFeaturedArtistModalBottomSheet;
        if (musicTemplate.artists.isEmpty())
            selectFeaturedArtistModalBottomSheet = new SelectFeaturedArtistModalBottomSheet(artistSelectedResultListener);
        else
            selectFeaturedArtistModalBottomSheet = new SelectFeaturedArtistModalBottomSheet(musicTemplate.artists, artistSelectedResultListener);
        selectFeaturedArtistModalBottomSheet.show(getFragmentManager(), SelectFeaturedArtistModalBottomSheet.TAG);
    }


    private void save() {
        if (titleTV.getText().length() <= 0) {
            musicTemplate.title = null;
        } else {
            musicTemplate.title = titleTV.getText().toString();
        }

        if (detailsTV.getText().length() <= 0) {
            musicTemplate.aboutShortStory = null;
        } else {
            musicTemplate.aboutShortStory = detailsTV.getText().toString();
        }
        if (musicTemplateReturnListener != null) {
            musicTemplateReturnListener.onMusicTemplateReturn(musicTemplate);
        }
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
            musicTemplate.coverImageFile = coverImageFile;
            coverImageView.setImageURI(selectedImageUri);


        } else if (requestCode == PICK_AUDIO && data != null) {
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Audio.Media.DATA};
            Cursor cursor = context.getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            //     Log.e(TAG, filePath);

            File audioFile;
            audioFile = new File(selectedImageUri.getPath());
            audioFile.setWritable(true);
            audioFile.setReadable(true);
            Log.e(TAG, audioFile.toString());

            musicTemplate.audioFile = audioFile;
            fileLinkTV.setText(audioFile.getPath());
            if (titleTV.getText().length() < 1)
                titleTV.setText(audioFile.getName());
            //      Size: 20MB
            MediaPlayer mp = new MediaPlayer();
            try {
                mp.setDataSource(context, selectedImageUri);
                mp.prepare();
                fileSizeTV.setVisibility(View.GONE);
                long mins = TimeUnit.MILLISECONDS.toMinutes(mp.getDuration());
                fileLengthTV.setText(String.format("Duration: %sMins", String.valueOf(mins)));
                mp.getDuration();
                //     mp.prepareAsync();
                //     mp.start();

                mp.release();

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            cursor.close();


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

}