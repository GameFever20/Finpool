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
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText userNameEditText, passwordEditText;

    final String userName= "priyank",password="12345678";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        userNameEditText = findViewById(R.id.login_userName_editText);
        passwordEditText = findViewById(R.id.login_password_editText);









    }


    public void onLoginclick(View view) {

        Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT).show();

        if (userNameEditText.getText().toString().equalsIgnoreCase(userName)){

            if (passwordEditText.getText().toString().equalsIgnoreCase(password)){

                openMenuActivity();

            }

        }


    }

    private void openMenuActivity() {

        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);

    }


}
