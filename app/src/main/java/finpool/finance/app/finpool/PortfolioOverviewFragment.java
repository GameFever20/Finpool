package finpool.finance.app.finpool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import utils.JsonParser;
import utils.MFTransactionAdapter;
import utils.PortfolioData;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PortfolioOverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PortfolioOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortfolioOverviewFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    PortfolioData portfolioData;

    public PortfolioOverviewFragment() {
    }


    public static PortfolioOverviewFragment newInstance(String param1, String param2) {
        PortfolioOverviewFragment fragment = new PortfolioOverviewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fetchPortfolioOverView();


    }

    private void fetchPortfolioOverView() {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/abtobusmanytwomobile.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, "onResponse: " + response);

                        portfolioData = new JsonParser().parsePortFolioData(response);

                        Log.d(TAG, "onResponse: "+portfolioData);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG, "onErrorResponse: " + error);

                    }
                });


        jsonArrayRequest.setShouldCache(true);

        VolleyManager.getInstance().addToRequestQueue(jsonArrayRequest, "Group request");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_portfolio_overview, container, false);
    }

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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
