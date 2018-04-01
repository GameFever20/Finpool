package finpool.finance.app.finpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.irozon.alertview.AlertActionStyle;
import com.irozon.alertview.AlertStyle;
import com.irozon.alertview.AlertView;
import com.irozon.alertview.interfaces.AlertActionListener;
import com.irozon.alertview.objects.AlertAction;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.JsonParser;
import utils.KYCStatus;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class InvestOnlineActivity extends AppCompatActivity {

    EditText editText;

    String panNumber;

    KYCStatus kycStatus;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_online);

        editText = findViewById(R.id.investOnline_pan_editText);


    }

    public void onCheckPanClick(View view) {

        panNumber = editText.getText().toString();
        fetchPanDetail();



    }


    private void fetchPanDetail() {

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/video.php?numer="+panNumber;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{


                            kycStatus = new JsonParser().parseKycDetail(response);


                            refreshUI();


                        }catch (Exception e){
                            e.printStackTrace();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG, "onErrorResponse: " + error);

                    }
                });





        jsonObjectRequest.setShouldCache(true);
        VolleyManager.getInstance().addToRequestQueue(jsonObjectRequest, "Group request");




    }

    private void refreshUI() {

        if (kycStatus.getStatus()!=null){

            if (kycStatus.getStatus().isEmpty()){
               showNotRegisteredDialog();
            }else {

                showRegisteredDialog();
                //Toast.makeText(this, "Status Successful", Toast.LENGTH_SHORT).show();
            }



        }else{
            showNotRegisteredDialog();
        }

    }

    private void showNotRegisteredDialog() {




        AlertView alert = new AlertView("You are not KYC-Certified", "Press proceed to register", AlertStyle.BOTTOM_SHEET);

        alert.addAction(new AlertAction("Proceed", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {



            }
        }));

        alert.addAction(new AlertAction("Cancel", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {



            }
        }));




        alert.show(this);


    }


    private void showRegisteredDialog() {




        AlertView alert = new AlertView("Hello "+kycStatus.getName(), "Your KYC Details \n• "+kycStatus.getPanNo()+"\n• "+kycStatus.getStatus()+"\n• "+kycStatus.getKycDate()+"\n• "+kycStatus.getClientType(), AlertStyle.BOTTOM_SHEET);

        alert.addAction(new AlertAction("Proceed", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {

                Intent intent = new Intent(InvestOnlineActivity.this, BankAccountActivity.class);
                startActivity(intent);

            }
        }));

        alert.addAction(new AlertAction("Cancel", AlertActionStyle.DEFAULT, new AlertActionListener() {
            @Override
            public void onActionClick(AlertAction alertAction) {




            }
        }));




        alert.show(this);


    }


}
