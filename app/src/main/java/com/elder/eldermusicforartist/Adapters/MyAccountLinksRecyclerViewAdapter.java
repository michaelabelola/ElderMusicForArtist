package com.elder.eldermusicforartist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elder.eldermusicforartist.Models.AccountLink;
import com.elder.eldermusicforartist.R;
import com.elder.eldermusicforartist.ui.AccountLinksPage.AccountLinksFragment;

import java.util.List;

import static com.elder.eldermusicforartist.Models.ItemCustomValues.ACCOUNT_LINK;

public class MyAccountLinksRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<AccountLink> mValues;
    private final AccountLinksFragment.OnAccountLinksInteractionListener mListener;
    View view;

    public MyAccountLinksRecyclerViewAdapter(List<AccountLink> items, AccountLinksFragment.OnAccountLinksInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.account_link_item_view, parent, false));
        /*
        switch (viewType) {
            case 0:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.advert_view, parent, false));
            default:
                return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_accountlinks, parent, false));
        }
        */
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final ViewHolder viewHolder1 = (ViewHolder) holder;
        viewHolder1.mItem = mValues.get(position);
        viewHolder1.mIdView.setText(mValues.get(position).primaryText);
        viewHolder1.mContentView.setText(mValues.get(position).text);
        viewHolder1.iconView.setImageDrawable(viewHolder1.iconView.getContext().getResources().getDrawable(mValues.get(position).image));
        viewHolder1.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onAccountLinksInteraction(viewHolder1.mItem, ACCOUNT_LINK);
                }
            }
        });

/*
        switch (holder.getItemViewType()) {
            case 0:
                final ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.mItem = mValues.get(position);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.onAccountLinksListFragmentInteraction(viewHolder.mItem, HomeViewValues.ADVERT);
                        }
                    }
                });
                break;
            default:
                final ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                viewHolder1.mItem = mValues.get(position);
                viewHolder1.mIdView.setText(mValues.get(position).primaryText);
                viewHolder1.mContentView.setText(mValues.get(position).text);
                viewHolder1.iconView.setImageDrawable(viewHolder1.iconView.getContext().getResources().getDrawable(mValues.get(position).image));
                viewHolder1.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.onAccountLinksListFragmentInteraction(viewHolder1.mItem, HomeViewValues.ACCOUNT_LINK);
                        }
                    }
                });
                break;
        }
*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView iconView;
        public AccountLink mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.text1);
            mContentView = view.findViewById(R.id.text2);
            iconView = view.findViewById(R.id.image_small);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
