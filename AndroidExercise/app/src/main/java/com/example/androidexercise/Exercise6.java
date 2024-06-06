package com.example.androidexercise;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exercise6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.i("Exercise6", "State: Created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Exercise6", "State: Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Exercise6", "State: Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Exercise6", "State: Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Exercise6", "State: Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Exercise6", "State: Destroyed");
    }
}
