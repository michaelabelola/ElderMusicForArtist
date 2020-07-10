package com.elder.eldermusicforartist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Interfaces.HomeItemClickListener;
import com.elder.eldermusicforartist.Models.ObjectHolder;
import com.elder.eldermusicforartist.Models.StatisticsHomeItem;
import com.elder.eldermusicforartist.R;

import java.util.List;

import static com.elder.eldermusicforartist.Models.ItemCustomValues.*;

public class MyHomeItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ObjectHolder> mValues;
    private final Context context;
    private HomeItemClickListener homeItemClickListener;

    public MyHomeItemRecyclerViewAdapter(List<ObjectHolder> creator, Context context) {
        this.mValues = creator;
        this.context = context;
        homeItemClickListener = (HomeItemClickListener) context;
    }

    @Override
    public int getItemViewType(int position) {
        //  return super.getItemViewType(position);
        switch (mValues.get(position).getObjectType()) {
            case COMPLETE_REG_VAL:
                return COMPLETE_REG_VAL;
            case STATISTICS:
                return STATISTICS;
            default:
                return super.getItemViewType(0);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case COMPLETE_REG_VAL:
                return new DefaultViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_item_1, parent, false));
            case STATISTICS:
                return new StatisticsViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_item_2, parent, false));
            default:
                return new DefaultViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_item_2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Object vall = mValues.get(position).getObject();
        //      if (vall instanceof CompleteRegistrationItem) {
        //      }

        switch (mValues.get(position).getObjectType()) {
            case COMPLETE_REG_VAL:
                handleView(holder, position);
                return;
            case STATISTICS:
                handleStatisticsView((StatisticsViewHolder) holder, position);
                return;
            default:
                handleView(holder, position);
        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                homeItemClickListener.onHomeItemLongClickInteraction(mValues.get(position));
                return true;
            }
        });

    }

    private void handleStatisticsView(StatisticsViewHolder holder, final int position) {
        final StatisticsHomeItem statisticsHomeItem = (StatisticsHomeItem) mValues.get(position).getObject();
        holder.text1.setText(statisticsHomeItem.getPrimaryText());
        holder.text2.setText(statisticsHomeItem.getSecondaryText());
        holder.image_main.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_timeline_24));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeItemClickListener.onHomeItemClickInteraction(mValues.get(position));
            }
        });
    }

    public void handleView(@NonNull RecyclerView.ViewHolder holder, final int position) {
        // CompleteRegistrationItem completeRegistrationItem = (CompleteRegistrationItem) mValues.get(position).getObject();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class DefaultViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        //     final RoundedImageView image_main;
        ObjectHolder mItem;

        DefaultViewHolder(View view) {
            super(view);
            mView = view;
            //      image_main = (RoundedImageView) view;
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }

    public static class StatisticsViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView image_main;
        final TextView text1, text2;
        ObjectHolder mItem;

        StatisticsViewHolder(View view) {
            super(view);
            mView = view;
            image_main = view.findViewById(R.id.image_main);
            text1 = view.findViewById(R.id.text1);
            text2 = view.findViewById(R.id.text2);
        }

        @Override
        public String toString() {
            return "StatisticsViewHolder{" +
                    ", image_main=" + image_main +
                    ", text1=" + text1 +
                    ", text2=" + text2 +
                    ", mItem=" + mItem +
                    '}';
        }
    }

}
