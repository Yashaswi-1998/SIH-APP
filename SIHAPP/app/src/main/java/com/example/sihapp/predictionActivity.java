package com.example.sihapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.lang.*;
import java.util.Random;

public class predictionActivity extends AppCompatActivity {
    Interpreter tflite;

    TextView textView6;
    EditText rain;
    TextView textViewh1;
    TextView textVieweh2;
    Spinner spinnermonth;
    Spinner spinneryear;
    Spinner spinnercotton;
    Spinner spinnerarea;
    ArrayList<String> listofcottons;
    ArrayList<String> monthsList = new ArrayList<>();
    ArrayList<String> yearlist = new ArrayList<>();
    ArrayList<String> arealist = new ArrayList<>();
    private static float month = 0;
    private static float year = 0;
    int cottontypeposition;
    float raininmm = 300;
    int areaposition;
    int[] ct = new int[7];


    ArrayAdapter monthAdapter;
    ArrayAdapter yearAdapter;
    ArrayAdapter cottontypesadapter;
    ArrayAdapter areaadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        textViewh1 = findViewById(R.id.textViewh1);
        textVieweh2 = findViewById(R.id.textViewh2);
        rain = findViewById(R.id.raineditText);
        rain.setText("300");

        spinnermonth = findViewById(R.id.spinnermonth);
        spinneryear = findViewById(R.id.spinnerYear);
        spinnercotton = findViewById(R.id.spinnercotton);
        spinnerarea = findViewById(R.id.spinnerarea);

        textView6 = findViewById(R.id.textView6);


        try {
            tflite = new Interpreter(loadModelFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        arealist.add("Punjab");
        arealist.add("Madhya Pradesh");
        arealist.add("Gujrat");
        arealist.add("Chandigarh");
        arealist.add("Haryana");
        arealist.add("Rajasthan");
        arealist.add("Maharastra");


        monthsList.add("JANUARY");
        monthsList.add("FEBRUARY");
        monthsList.add("MARCH");
        monthsList.add("APRIL");
        monthsList.add("MAY");
        monthsList.add("JUNE");
        monthsList.add("JULY");
        monthsList.add("AUGUST");
        monthsList.add("SEPTEMBER");
        monthsList.add("OCTOBER");
        monthsList.add("NOVEMBER");
        monthsList.add("DECEMBER");

        yearlist.add("2020");
        yearlist.add("2021");
        yearlist.add("2022");
        yearlist.add("2023");
        yearlist.add("2024");
        yearlist.add("2025");
        yearlist.add("2026");
        yearlist.add("2027");
        yearlist.add("2028");
        yearlist.add("2029");
        yearlist.add("2030");
        yearlist.add("2031");

        listofcottons = new ArrayList<>();
        listofcottons.add("BENGALI DESI");
        listofcottons.add("BUNNY BRAHMA");
        listofcottons.add("DCH-32");
        listofcottons.add("H-4");
        listofcottons.add("J-34");
        listofcottons.add("S-6");
        listofcottons.add("V 797");

        setupSpinner();
    }


    private void setupSpinner() {
        monthAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, monthsList);
        yearAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, yearlist);
        cottontypesadapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listofcottons);
        areaadapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arealist);
        // areaadapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,);

        monthAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        cottontypesadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        areaadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnermonth.setAdapter(monthAdapter);
        spinneryear.setAdapter(yearAdapter);
        spinnercotton.setAdapter(cottontypesadapter);
        spinnerarea.setAdapter(areaadapter);


        spinnermonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                month = (float) position + 1;

                Log.i("months", String.valueOf(month));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                month = 1; // Unknown
            }
        });
        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                year = Float.parseFloat((spinneryear.getSelectedItem().toString()));

                Log.i("year", String.valueOf(year));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                year = 2020; // Unknown

            }

        });

        spinnerarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                areaposition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // year = 2020; // Unknown

            }

        });


        spinnercotton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cottontypeposition = position + 3;
                ct = new int[7];
                ct[cottontypeposition]=1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("SIH.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startoffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startoffset, declaredLength);
    }


    public void predict(View view) {


        double v = java.lang.Math.cos(month * (Math.PI) / 12);
        double v2 = java.lang.Math.sin(month * (Math.PI) / 12);
        raininmm = Float.parseFloat(rain.getText().toString());

        float[][] inputs = {{year, raininmm, (float) v, (float) v2, ct[0], ct[1], ct[2], ct[3], ct[4], ct[5], ct[6], ct[7]}};
        if (cottontypeposition == 3)
            inputs[0][cottontypeposition] = 0;
        else
            inputs[0][cottontypeposition] = 1;
        if (areaposition == 9)
            inputs[0][areaposition] = 0;
        else
            inputs[0][areaposition] = 1;


        float[][] output = new float[1][1];
        tflite.run(inputs, output); 

        textView6.setText(String.format("%.2f", output[0][0] * (266.5084)) + "Rs/candy(356KG)");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


}
