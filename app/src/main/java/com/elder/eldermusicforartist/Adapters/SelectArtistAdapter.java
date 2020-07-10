package com.elder.eldermusicforartist.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.elder.eldermusicforartist.Models.Artist;
import com.elder.eldermusicforartist.R;

import java.util.ArrayList;
import java.util.List;

public class SelectArtistAdapter extends ArrayAdapter<Artist> {
    private Context context;
    private int resourceId;
    private List<Artist> items, tempItems, suggestions;

    public SelectArtistAdapter(@NonNull Context context, int resourceId, ArrayList<Artist> items) {
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resourceId, parent, false);
            }
            TextView nameText1 = convertView.findViewById(R.id.text1);

            Artist artist = getItem(position);
     //       ImageView imageView = view.findViewById(R.id.imageView);
       //     imageView.setImageResource(artist.getImage());
            if (artist != null) {
                nameText1.setText(artist.name);
            }
        return convertView;
    }

    @Nullable
    @Override
    public Artist getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return fruitFilter;
    }

    private Filter fruitFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Artist fruit = (Artist) resultValue;
            return fruit.name;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (Artist artist : tempItems) {
                    if (artist.name.toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(artist);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Artist> tempValues = (ArrayList<Artist>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (Artist artistObj : tempValues) {
                    add(artistObj);
                }
                notifyDataSetChanged();
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}