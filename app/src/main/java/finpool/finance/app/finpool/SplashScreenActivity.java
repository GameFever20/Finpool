package finpool.finance.app.finpool;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import utils.SettingManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        startTimer(3000l);
    }


    public void startTimer(long timer) {


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i;


                if (SettingManager.getLoggedIn(SplashScreenActivity.this)) {
                    i = new Intent(SplashScreenActivity.this, PatternLockActivity.class);

                } else {
                    i = new Intent(SplashScreenActivity.this, LoginActivity.class);

                }

                startActivity(i);

                // close this activity
                finish();
            }
        }, timer);


    }

}
