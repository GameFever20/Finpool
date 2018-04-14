package finpool.finance.app.finpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;

import java.util.List;

import utils.SettingManager;

public class PatternLockActivity extends AppCompatActivity {

    private PatternLockView mPatternLockView;
    private int type;
    //1= for logging in 2 for setting pattern

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lock);

        type = getIntent().getIntExtra("type",1);

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {

                StringBuilder lockPattern = new StringBuilder();

                for (int i =0; i<pattern.size(); i++){

                    PatternLockView.Dot dot = pattern.get(i);
                    lockPattern.append(dot.getId());

                }

                if (type==2){
                    setPatternLock(lockPattern.toString());
                }else {
                    checkPatternLock(lockPattern.toString());
                }

                //Toast.makeText(PatternLockActivity.this, "Lock pattern is - "+lockPattern, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCleared() {

            }
        });
    }

    private void checkPatternLock(String pattern) {
        String savedPattern = SettingManager.getPatternLock(this);

        if (savedPattern.equalsIgnoreCase(pattern)){
            openMainActivity();
        }else {
            Toast.makeText(this, "Wrong Pattern", Toast.LENGTH_SHORT).show();
        }

    }

    private void openMainActivity() {

        Intent intent = new Intent(this, PortfolioActivity.class);
        startActivity(intent);

        finish();

    }

    private void setPatternLock(String pattern) {

        SettingManager.setPatternLock(this,pattern);
        Toast.makeText(this, "Pattern saved", Toast.LENGTH_SHORT).show();

        openMainActivity();


    }


}
