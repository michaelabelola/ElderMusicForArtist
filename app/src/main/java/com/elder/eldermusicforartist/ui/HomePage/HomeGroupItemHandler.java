package com.elder.eldermusicforartist.ui.HomePage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.elder.eldermusicforartist.Adapters.MyHomeItemRecyclerViewAdapter;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;
import static android.view.View.inflate;
import static com.elder.eldermusicforartist.Models.ItemCustomValues.*;

public class HomeGroupItemHandler {
    Context context;
    Creator creator;

    public HomeGroupItemHandler(Context context, Creator creator) {
        this.context = context;
        this.creator = creator;
        init();
    }

    private void init() {
        View vV = inflate(context, R.layout.fragment_home_item, null);
        creator.parentView.addView(vV);

        RecyclerView recyclerView = vV.findViewById(R.id.list_view);
        TextView titleTV, moreBtnSelector;
        titleTV = vV.findViewById(R.id.titleTV);
        moreBtnSelector = vV.findViewById(R.id.moreBtnSelector);
        setTextStuffs(creator.getPrimaryText(), titleTV);
        moreBtnSelector.setVisibility(creator.isMoreVisible());

        if (creator.typeZ == STATISTICS) {
              recyclerView.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
        recyclerView.setAdapter(new MyHomeItemRecyclerViewAdapter(creator.getItems(), context));
    }

    private void setTextStuffs(String primaryText, TextView titleTV) {
        titleTV.setText(primaryText);
    }

    protected static class Creator {
        private String primaryText;
        private ViewGroup parentView;
        private int moreVisible = VISIBLE;
        private int typeZ = 0;
        private List<ObjectHolder> items = new ArrayList<>();

        public Creator() {
        }

        public Creator newView() {
            return new Creator();
        }

        public Creator setParentView(ViewGroup parentView) {
            this.parentView = parentView;
            return this;
        }

        public Creator setItems(List<ObjectHolder> items) {
            this.items = items;
            return this;
        }

        public Creator setMoreVisible(int moreVisible) {
            this.moreVisible = moreVisible;
            return this;
        }

        public Creator setPrimaryText(String primaryText) {
            this.primaryText = primaryText;
            return this;
        }

        public Creator setMainType(int typeZ) {
            this.typeZ = typeZ;
            return this;
        }

        public String getPrimaryText() {
            return primaryText;
        }

        public ViewGroup getParentView() {
            return parentView;
        }

        public int isMoreVisible() {
            return moreVisible;
        }

        public List<ObjectHolder> getItems() {
            return items;
        }

        public Creator(int typeZ) {
            this.typeZ = typeZ;
        }
    }
}
