package finpool.finance.app.finpool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class LoginActivity extends AppCompatActivity {

    EditText userNameEditText, passwordEditText;
    TextView headingText;

    final String userName = "priyank", password = "12345678";


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

        openMenuActivity();

        YoYo.with(Techniques.Bounce).duration(700)
                .playOn(view);


    }

    private void openMenuActivity() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }


}
