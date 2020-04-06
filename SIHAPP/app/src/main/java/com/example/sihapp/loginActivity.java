package com.example.sihapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    EditText emaileditText;
    EditText passwordEditText;
    TextView signupTex;
    //String email;
   // String password;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null && firebaseAuth.getCurrentUser().isEmailVerified())   //if someone is already logged in and has veriged his email then straight to sensors activity
        {
            finish();
            startActivity(new Intent(getApplicationContext(),farmersActivity.class));
        }
        emaileditText = findViewById(R.id.mailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        signupTex = findViewById(R.id.loginTextView);

      //  email = emaileditText.getText().toString().trim();
       // password = passwordEditText.getText().toString().trim();




    }
    public void login(View view)
    {
        userlogin();
    }

    public  void signiup(View view)
    {

        finish();
        Intent intent = new Intent(getApplicationContext(),signupActivity.class);
        startActivity(intent);
    }
    public  void userlogin()
    {  String email = emaileditText.getText().toString().trim();
       String password = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"plaese enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"plaese enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)        //firebase method to signing in the user
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //if the provided user"s email address is verified then he can access the sensors activity;
                            if (firebaseAuth.getCurrentUser().isEmailVerified())
                            {
                                finish();
                                startActivity(new Intent(getApplicationContext(),farmersActivity.class));
                            }else
                            {
                                Toast.makeText(loginActivity.this,"Please verify your email and try again",Toast.LENGTH_SHORT).show();
                            }

                        }else
                        {
                            Toast.makeText(loginActivity.this,"LOGIN failed please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
