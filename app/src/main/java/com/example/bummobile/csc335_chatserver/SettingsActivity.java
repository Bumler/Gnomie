package com.example.bummobile.csc335_chatserver;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;

/**
 * Created by Bum Mobile on 10/20/2016.
 */

public class SettingsActivity extends AppCompatActivity {

    Button logout;
    ImageView backToMain;

    TextView username;
    // [START declare_auth]
    private FirebaseAuth auth;
    // [END declare_auth]
    private FirebaseAuth.AuthStateListener authListener;

    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        initComponents();
    }

    private void initComponents(){
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            //on registration click the app attempts to register the user.
            //if it succeeds the user is sent back to the login screen otherwise they are notified
            public void onClick(View view) {
                signOut();
            }
        });

        username = (TextView)findViewById(R.id.username_display);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            username.setText(user.getDisplayName());
        }

        backToMain = (ImageView)findViewById(R.id.backToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(SettingsActivity.this, ChatScreenActivity.class);
                startActivity(i);
            }
        });
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplication(), "Signed Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
        finish();
    }
}
