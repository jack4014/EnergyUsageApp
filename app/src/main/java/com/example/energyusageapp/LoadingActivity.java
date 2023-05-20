package com.example.energyusageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.energyusageapp.*;

public class LoadingActivity extends AppCompatActivity {

    Runnable update;
    View root;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(androidx.navigation.R.layout.activity_loading);
        root = findViewById(androidx.navigation.R.id.loadingRoot);
        bar = findViewById(androidx.navigation.R.id.loadingDisplay);
        if (ResourceManager.Instance() == null) {
            ResourceManager.OnLoaded = () -> {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            };
            new ResourceManager(this);

        }
        update = () -> {
            OnUpdate();
            root.postDelayed(update,1);
        };
        root.postDelayed(update,1);
    }
    static int updateCount = 0;
    void OnUpdate() {
        updateCount++;
    }
}