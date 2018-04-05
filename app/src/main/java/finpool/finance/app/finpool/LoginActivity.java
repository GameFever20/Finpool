package finpool.finance.app.finpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.JsonParser;
import utils.SettingManager;
import utils.SipTransactionAdapter;
import utils.VolleyManager;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    EditText userNameEditText, passwordEditText;
    TextView headingText;

    final String userName = "priyank", password = "12345678";
    private int loginStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        userNameEditText = (EditText) findViewById(R.id.login_userName_editText);
        passwordEditText = (EditText) findViewById(R.id.login_password_editText);
        headingText = (TextView) findViewById(R.id.login_finpool_textview);


        YoYo.with(Techniques.RubberBand).duration(700)
                .playOn(headingText);



    }


    public void onLoginclick(View view) {

        Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT).show();

        if (userNameEditText.getText().toString().equalsIgnoreCase(userName)) {

            if (passwordEditText.getText().toString().equalsIgnoreCase(password)) {





            }

        }

        fetchLogInInfo();

        YoYo.with(Techniques.Bounce).duration(700)
                .playOn(view);


    }


    public void fetchLogInInfo(){

        String url = "http://choureywealthcreation.com/admin/sdevloop/swealth/app/login.php?user="+userNameEditText.getText().toString().trim()+"&pass="+passwordEditText.getText().toString().trim();


        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject(0);

                            loginStatus = Integer.valueOf( jsonObject.getString("unit"));

                            if (loginStatus==0){
                                openPatternScreen();
                            }else {
                                Toast.makeText(LoginActivity.this, "Wrong user id or password", Toast.LENGTH_SHORT).show();
                            }

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


        jsonArrayRequest.setShouldCache(true);

        VolleyManager.getInstance().addToRequestQueue(jsonArrayRequest, "Group request");

    }

    private void openPatternScreen() {

        SettingManager.setLoggedIn(this,true);

        Intent intent = new Intent(this, PatternLockActivity.class);
        intent.putExtra("type",2);

        startActivity(intent);



    }


    private void openMenuActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }


}
