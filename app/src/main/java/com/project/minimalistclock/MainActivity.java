package com.project.minimalistclock;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textView,textViewMotive;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the action bar
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Hide the navigation bar
        this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



         textView = findViewById(R.id.textView);
        textViewMotive = findViewById(R.id.textViewMotive);
        handler = new Handler();

        textViewMotive.setText("Don't waste time, it's the stuff life is made of.");
        // Start updating the time immediately
        updateTimePeriodically();
    }

    private void updateTimePeriodically() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000); // Update every second (1000 milliseconds)
            }
        }, 0);
    }

    private void updateTime() {
        Date currentTime = Calendar.getInstance().getTime();
        String hour = String.valueOf(currentTime.getHours());
        String minutes = String.valueOf(currentTime.getMinutes());
        String seconds = String.valueOf(currentTime.getSeconds());
        textView.setText(hour + ":" + minutes + ":" + seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any pending callbacks to prevent memory leaks
        handler.removeCallbacksAndMessages(null);
    }
}
