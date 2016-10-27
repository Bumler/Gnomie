package com.example.bummobile.csc335_chatserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Bum Mobile on 10/25/2016.
 */

public class RecoveryPOP extends BaseActivity {
    private Button recover;
    private TextInputEditText emailIn;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

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

    //email logic taken from http://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/
    private void initComponents(){
        mAuth = FirebaseAuth.getInstance();

        emailIn = (TextInputEditText)findViewById(R.id.recovery_email);

        recover = (Button)findViewById(R.id.recover);
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailIn.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }

                showProgressDialog();
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                hideProgressDialog();
                                if (task.isSuccessful()) {
                                    Toast.makeText(RecoveryPOP.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(RecoveryPOP.this, LoginActivity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(RecoveryPOP.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
