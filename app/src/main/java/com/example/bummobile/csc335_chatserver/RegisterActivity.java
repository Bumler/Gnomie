package com.example.bummobile.csc335_chatserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by Bum Mobile on 10/17/2016.
 */

public class RegisterActivity extends BaseActivity{
    Button registerUser;

    TextInputEditText username;
    TextInputEditText password;
    TextInputEditText passwordConf;
    TextInputEditText email;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();

        initComponents();
    }

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
    }

    private void initComponents(){
        username = (TextInputEditText) findViewById(R.id.new_username);
        password = (TextInputEditText)findViewById(R.id.password);
        passwordConf = (TextInputEditText)findViewById(R.id.password_confirm);
        email = (TextInputEditText)findViewById(R.id.new_email);

        registerUser = (Button)findViewById(R.id.registerUser);
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            //on registration click the app attempts to register the user.
            //if it succeeds the user is sent back to the login screen otherwise they are notified
            public void onClick(View view) {
                createAccount(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //starts that progress animation
        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        //the registration is successful
                        else{
                            Toast.makeText(RegisterActivity.this, R.string.auth_success,
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                        if (task.isSuccessful()){
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    //this method checks that both the email and password fields hold strings
    //it also checks that the password and confirm password fields match
    //if any of these conditions are not met it returns false and the user will not register
    //we do not currently check for username since it is not yet stored in the database
    //no requirements are placed on the passwords. This is a design choice
    private boolean validateForm() {
        boolean valid = true;

        String emailTest = email.getText().toString();
        if (TextUtils.isEmpty(emailTest)) {
            Toast.makeText(RegisterActivity.this, R.string.error_email,
                    Toast.LENGTH_SHORT).show();
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String passwordTest = password.getText().toString();
        String passwordConfTest = passwordConf.getText().toString();
        if (TextUtils.isEmpty(passwordTest)) {
            Toast.makeText(RegisterActivity.this, R.string.error_pw,
                    Toast.LENGTH_SHORT).show();
            password.setError("Required.");
            valid = false;
        }
        else if (!passwordTest.equals(passwordConfTest)){
            Toast.makeText(RegisterActivity.this, R.string.error_pw_match,
                    Toast.LENGTH_SHORT).show();

            valid = false;
        }
        else {
            password.setError(null);
        }

        return valid;
    }
}
