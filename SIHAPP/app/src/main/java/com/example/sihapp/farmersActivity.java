package com.example.sihapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class farmersActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText complain ;
    String complaintyped;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers);
        complain = findViewById(R.id.complain);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null) //if no one is looged in then opening login activity
        {
            finish();
            startActivity(new Intent(getApplicationContext(), loginActivity.class));
        }





    }

    public  void link_funtion(View view) {
        String query = "http://texmin.nic.in/";
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public  void buycotton(View view) {
        String query = "https://my.indiamart.com/buyertools/managebl/";
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void submit(View view)
    {
        complaintyped = complain.getText().toString();
        myRef = database.getReference("Message");
        myRef.getRef().setValue(complaintyped);
        Log.i("complain",complaintyped);

        complain.setText("");

    }
    public void logout(View view)
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(),loginActivity.class));
    }


        @Override
    public void onBackPressed() {
        super.onBackPressed();
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(),loginActivity.class));

    }
}
