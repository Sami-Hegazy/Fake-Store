package net.fakestore.ui.view.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import net.fakestore.R;
import net.fakestore.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding mActivitySplashBinding;
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        getSupportActionBar().hide();

        new Thread(() -> {
            doWork();
            startApp();
            finish();
        }).start();
    }

    private void doWork() {
        for (int progress=0; progress<=100; progress+=30) {
            try {
                Thread.sleep(1000);
                mActivitySplashBinding.splashProgressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWaitHandler.removeCallbacksAndMessages(null);
    }
}