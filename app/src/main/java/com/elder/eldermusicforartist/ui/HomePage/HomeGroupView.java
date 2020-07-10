package com.elder.eldermusicforartist.ui.HomePage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Adapters.MyHomeItemRecyclerViewAdapter;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.R;

import java.util.ArrayList;
import java.util.List;

public class HomeGroupView extends ViewGroup {
    private final Context context;
    private final Creator creator;
    private ViewGroup viewHolder;
    private View parentView;
    private View rootView;
    private RecyclerView recyclerView;

    public HomeGroupView(Context context, Creator creator, ViewGroup viewHolder) {
        super(context);
        this.context = context;
        this.creator = creator;
        this.viewHolder = viewHolder;

        FrameLayout frameLayout = new FrameLayout(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        frameLayout.setLayoutParams(layoutParams);
        loadIntoView(frameLayout, viewHolder);
        viewHolder.addView(frameLayout);
    }

    public void loadIntoView(View parentView, ViewGroup scrollViewLinearLayout) {
        this.parentView = parentView;
        scrollViewLinearLayout.addView(this);
        loadViews();
    }

    private View loadViews() {
        rootView = inflate(getContext(), R.layout.home_item_1, (ViewGroup) parentView);
    //    recyclerView = rootView.findViewById(R.id.list_view);
    //    TextView titleTV;
    //    titleTV = rootView.findViewById(R.id.titleTV);
     //   setTextStuffs(creator.getTitleText(), titleTV);

  //      recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
  //      recyclerView.setAdapter(new MyHomeItemRecyclerViewAdapter(creator.itemsMap, getContext()));

        return rootView;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void setTextStuffs(String text, TextView textView) {
        textView.setText(text);
    }

    public static class Creator {
        private String titleText = "";
        private List<ObjectHolder> itemsMap = new ArrayList<ObjectHolder>();

        public static Creator newInstance() {
            return new Creator();
        }

        public Creator setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public Creator setItems(List<ObjectHolder> itemsMap) {
            this.itemsMap = itemsMap;
            return this;
        }

        public Creator create() {
            return this;
        }

        public String getTitleText() {
            return titleText;
        }

    }



}
