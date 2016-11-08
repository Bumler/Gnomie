package com.example.bummobile.csc335_chatserver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Bum Mobile on 10/18/2016.
 */

public class ChatScreenActivity extends AppCompatActivity{

    FloatingActionButton email;
    FloatingActionButton openChats;
    ImageView userIcon;

    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatscreen);

        initComponents();
    }

    private void initComponents(){
        email = (FloatingActionButton) findViewById(R.id.sendEmail);
        email.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                sendEmail();
            }
        });

        openChats = (FloatingActionButton) findViewById(R.id.open_chats);
        openChats.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent i = new Intent(ChatScreenActivity.this, AvaliableChatsPOP.class);
                startActivity(i);
            }
        });

        userIcon = (ImageView)findViewById(R.id.userIcon);
        userIcon.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vew){
                Intent i = new Intent(ChatScreenActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    protected void sendEmail(){
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ChatScreenActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
