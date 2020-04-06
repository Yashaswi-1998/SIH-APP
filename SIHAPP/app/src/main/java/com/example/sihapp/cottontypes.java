package com.example.sihapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class cottontypes extends AppCompatActivity {
    ListView listView;
    TextView textView;
    TextView textView2;
    ArrayList <String> listofcottons;
    ArrayAdapter <String > arrayAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cottontypes);
        listView = findViewById(R.id.listview);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        listofcottons = new ArrayList<String>();
        listofcottons.add("J-34");
        listofcottons.add("H-4");
        listofcottons.add("S-6");
        listofcottons.add("DCH-32");
        listofcottons.add("BUNNY BRAHMA");
        listofcottons.add("BENGALI DESI");
        listofcottons.add("V 797");

         arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listofcottons);
         listView.setAdapter(arrayAdapter);
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 Intent intent = new Intent(getApplicationContext(),typeinfoActivity.class);
                 intent.putExtra("Type",position);
                 finish();
                 startActivity(intent);

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
