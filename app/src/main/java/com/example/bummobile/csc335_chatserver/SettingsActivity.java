package com.example.bummobile.csc335_chatserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Bum Mobile on 10/20/2016.
 */

public class SettingsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initComponents();
    }

    private void initComponents(){
    }
}
