package finpool.finance.app.finpool;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import utils.RecentTransaction;
import utils.RecentTransactionAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PortfolioRecentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PortfolioRecentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortfolioRecentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recentPurchaseRecyclerView, recentRedemptionRecyclerView, recentSipPurchaseRecyclerView;
    ArrayList<RecentTransaction> recentPurchaseArrayList = new ArrayList<>(), recentRedemptionArrayList = new ArrayList<>(), recentSipPurchaseArrayList = new ArrayList<>();

    RecentTransactionAdapter recentPurchaseAdapter, recentRedemptionAdapter, recentSipPurchaseAdapter;


    public PortfolioRecentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PortfolioRecentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PortfolioRecentFragment newInstance(String param1, String param2) {
        PortfolioRecentFragment fragment = new PortfolioRecentFragment();
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


        recentPurchaseAdapter = new RecentTransactionAdapter(recentPurchaseArrayList, getContext());
        recentRedemptionAdapter = new RecentTransactionAdapter(recentRedemptionArrayList, getContext());
        recentSipPurchaseAdapter = new RecentTransactionAdapter(recentSipPurchaseArrayList, getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolio_recent, container, false);

        recentPurchaseRecyclerView = view.findViewById(R.id.portfolioFragment_recentPurchase_recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recentPurchaseRecyclerView.setLayoutManager(mLayoutManager);
        recentPurchaseRecyclerView.setItemAnimator(new DefaultItemAnimator());


        recentPurchaseRecyclerView.setAdapter(recentPurchaseAdapter);


        recentRedemptionRecyclerView = view.findViewById(R.id.portfolioFragment_recentRedemption_recyclerView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        recentRedemptionRecyclerView.setLayoutManager(mLayoutManager2);
        recentRedemptionRecyclerView.setItemAnimator(new DefaultItemAnimator());


        recentRedemptionRecyclerView.setAdapter(recentRedemptionAdapter);


        recentSipPurchaseRecyclerView = view.findViewById(R.id.portfolioFragment_recentSipPurchase_recyclerView);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getContext());
        recentSipPurchaseRecyclerView.setLayoutManager(mLayoutManager3);
        recentSipPurchaseRecyclerView.setItemAnimator(new DefaultItemAnimator());


        recentSipPurchaseRecyclerView.setAdapter(recentSipPurchaseAdapter);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}