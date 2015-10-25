package co.jibola.locus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.victor.loading.book.BookLoading;
import com.victor.loading.newton.NewtonCradleLoading;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        moveToMainActivity();
    }

    private void moveToMainActivity() {
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        timerThread.start();
    }
}