package com.example.sihapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class signupActivity extends AppCompatActivity {
    EditText mailEditText;
    EditText passwordEditText;
    TextView loginTextView;
    TextView phone;
    String number;
    private FirebaseAuth firebaseAuth;  //declearing firebaseauth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();  //creating its instance

        mailEditText = findViewById(R.id.mailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        loginTextView =findViewById(R.id.loginTextView);
        phone = findViewById(R.id.phone);
        number = phone.getText().toString();



    }

    private   void registerUser()    //method for singning up the user
    {
        String email = mailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        //not allowing to executing this function further if any field is kept empty
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

        firebaseAuth.createUserWithEmailAndPassword(email,password)   //firebase method to signing up the user
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())     // if user is created then go for verification of email address
                        {
                            firebaseAuth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) //if mail is sent to regestered email then take user to login activity to log in
                                            {
                                                Toast.makeText(signupActivity.this,"Please check tour email inbox to register and then log in again",Toast.LENGTH_LONG).show();
                                                finish();
                                                startActivity(new Intent(getApplicationContext(),loginActivity.class));
                                            }else
                                            {
                                                Toast.makeText(signupActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                    });

                        }else
                        {
                            Toast.makeText(signupActivity.this,"Failed to register! plese try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signup(View view)  //signup button
    {
        registerUser();
    }
    public  void login(View view) //text view onclicklistener ,if user have already an account then they can log in from there
    {
        finish();
        startActivity(new Intent(getApplicationContext(),loginActivity.class));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        startActivity(new Intent(getApplicationContext(),loginActivity.class));

    }
}
