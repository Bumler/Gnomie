package com.example.bummobile.csc335_chatserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by Bum Mobile on 10/20/2016.
 */

public class SettingsActivity extends AppCompatActivity {

    Button logout;

    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initComponents();
    }

    private void initComponents(){
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            //on registration click the app attempts to register the user.
            //if it succeeds the user is sent back to the login screen otherwise they are notified
            public void onClick(View view) {
                Intent i = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
