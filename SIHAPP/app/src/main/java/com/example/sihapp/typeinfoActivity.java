package com.example.sihapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

public class typeinfoActivity extends AppCompatActivity {
    TextView multilinetextview;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeinfo);
        multilinetextview = findViewById(R.id.multilinetext);
        textView3=findViewById(R.id.textView3);
        textView3.setInputType(InputType.TYPE_NULL);
        textView3.setText("");
        multilinetextview.setText("");
        Intent intent = getIntent();
        int type = intent.getIntExtra("Type",0);

        switch (type)
        {
            case 0:
                textView3.setText("J-34");
                multilinetextview.setText("*Commonly known as ROLLED cotton \n" +
                        "*standard (ICS-106)\n" +
                        "*largly produced in punjab haryana rajasthan\n" +
                        "*Good dye absorption \n" +
                        "*High trash"); break;
            case 1:
                textView3.setText("H-4");
                multilinetextview.setText("*commonly known as Hybrid 4\n" +
                        "*standard (ICS-105)\n" +
                        "*largly produced in gujrat maharastra Madhya pradesh\n" +
                        "*low water absorption,better tribiological properties\n" +
                        "*expensive seed");break;
            case 2:
                textView3.setText("S-6");
                multilinetextview.setText("*commonly known as shankar-6\n" +
                        "*commonly known as Hybrid 4\n" +
                        "*standard (ICS-110)\n" +
                        "*only produced in gujrat\n" +
                        "*high demand across global market \n" +
                        "*production limited to certain area");break;
            case 3:
                textView3.setText("DCH-32");
                multilinetextview.setText("*commonl known as Dhaward cotton hybrid No.32\n" +
                        "*standard (ICS-113)\n" +
                        "* produced in MP,maharastra,Tamil nadu,oddisha\n" +
                        "*has very strength \n" +
                        "*minimum fabric rapture");break;
            case 4:
                textView3.setText("BUNNY BRAHMA");
                multilinetextview.setText("*commonly known as Bunny/brahma\n" +
                        "*standard (ICS-111)\n" +
                        "*only produced in Maharastra,karnattka,Tamil nadu\n" +
                        "*can bear extreme climate change\n" +
                        "*provide good strength thus use in weaving");break;
            case 5:
                textView3.setText("BENGALI DESI");
                multilinetextview.setText("*also known as absorbent cotton\n" +
                        "*standard (ICS-101)\n" +
                        "*produced in UP,Assam,Rajasthan \n" +
                        "*Low cost \n" +
                        "*High demand in Europe");
            case 6:
                textView3.setText("V 797");
                multilinetextview.setText("*also known as Gossypium hersaccum\n" +
                        "*standard (ICS-103)\n" +
                        "*mainly produced in gujrat\n" +
                        "*cheap and demanded globally and locally\n" +
                        "*limited area of production");break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(),cottontypes.class));
    }
}
