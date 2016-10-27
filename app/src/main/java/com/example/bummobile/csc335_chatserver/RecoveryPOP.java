package com.example.bummobile.csc335_chatserver;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Bum Mobile on 10/25/2016.
 */

public class RecoveryPOP extends BaseActivity {
    private Button recover;
    private TextInputEditText email;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_recovery);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //this sets the size of the screen
        int width = (int) (dm.widthPixels * .65);
        int height = (int) (dm.heightPixels * .17);

        //the rest of the screen is made transparent via AppTheme.transparentPOP in styles
        //this style is then referenced in our manifest

        getWindow().setLayout(width, height);

        initComponents();
    }

    private void initComponents(){
        email = (TextInputEditText)findViewById(R.id.recovery_email);

        recover = (Button)findViewById(R.id.recover);
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
