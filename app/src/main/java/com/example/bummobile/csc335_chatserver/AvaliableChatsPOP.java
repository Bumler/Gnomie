package com.example.bummobile.csc335_chatserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by Bum Mobile on 11/7/2016.
 */

public class AvaliableChatsPOP extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_avaliable_chats);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //this sets the size of the screen
        int width = (int) (dm.widthPixels * .8);
        int height = (int) (dm.heightPixels * .8);

        //the rest of the screen is made transparent via AppTheme.transparentPOP in styles
        //this style is then referenced in our manifest

        getWindow().setLayout(width, height);

        initComponents();
    }

    private void initComponents(){

    }
}
