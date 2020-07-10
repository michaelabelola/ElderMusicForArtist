package com.elder.eldermusicforartist.ui.AccountLinksPage;

import android.content.Context;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elder.eldermusicforartist.Adapters.MyAccountLinksRecyclerViewAdapter;
import com.elder.eldermusicforartist.Models.AccountLink;
import com.elder.eldermusicforartist.R;

import java.util.ArrayList;
import java.util.List;

import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_ABOUT_DEVELOPERS;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_PAYMENT;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_PRIVACY_POLICY;
import static com.elder.eldermusicforartist.Models.AccountLink.ACCOUNT_LINK_PROFILE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountLinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountLinksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    public List<AccountLink> accountLinkList = new ArrayList<AccountLink>();
    private Context context;
    private RecyclerView recyclerView;
    private int mColumnCount = 1;
    private OnAccountLinksInteractionListener mListener;
    NestedScrollView scrollView;

    public AccountLinksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountLinksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountLinksFragment newInstance(String param1, String param2) {
        AccountLinksFragment fragment = new AccountLinksFragment();
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
        rootView = inflater.inflate(R.layout.fragment_account_links, container, false);
        scrollView = rootView.findViewById(R.id.scrollView);
        context = rootView.getContext();
        recyclerView = rootView.findViewById(R.id.list);
        mListener = (OnAccountLinksInteractionListener) context;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        accountLinkList.clear();
        //     accountLinkList.add(new AccountLink(ACCOUNT_LINK_ADS, "ads", "ads"));
        accountLinkList.add(new AccountLink(ACCOUNT_LINK_PROFILE, "Profile", "Abel Michael", R.drawable.ic_baseline_account_circle_24));
        //     accountLinkList.add(new AccountLink(ACCOUNT_LINK_ROYALTY_MANAGER, "Royalty Manager", "Not Active", R.drawable.ic_crown));
        accountLinkList.add(new AccountLink(ACCOUNT_LINK_PAYMENT, "Payments / Accounts", "No Registered Card", R.drawable.ic_baseline_account_balance_wallet_24));
        //   accountLinkList.add(new AccountLink(ACCOUNT_LINK_CONTACT_US, "Contact us", "eldermusic@gmail.com", R.drawable.ic_online_support));
        accountLinkList.add(new AccountLink(ACCOUNT_LINK_ABOUT_DEVELOPERS, "About Developers", "info", R.drawable.ic_baseline_info_24));
        accountLinkList.add(new AccountLink(ACCOUNT_LINK_PRIVACY_POLICY, "Privacy Policy", "updated", R.drawable.ic_baseline_priority_high_24));
        recyclerView.setAdapter(new MyAccountLinksRecyclerViewAdapter(accountLinkList, mListener));
        return rootView;
    }

    public interface OnAccountLinksInteractionListener {
        // TODO: Update argument type and name
        void onAccountLinksInteraction(AccountLink accountLink, int type);
    }
}