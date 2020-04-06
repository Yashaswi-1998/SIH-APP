package com.example.sihapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewcottontypes;
    TextView textViewproduction;
    TextView textViewpredictiom;
    /////creatting the menu option log in


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.loginmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.login)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), loginActivity.class));

            return true;
        }
        else
            return false;

    }

    public  void cottontypes (View view)
    {
        finish();
        startActivity(new Intent(getApplicationContext(), cottontypes.class));
    }
    public void production(View view)
    {
        finish();
        startActivity(new Intent(getApplicationContext(),productionActivity.class));
    }
    public void prediction(View view)
    {
        finish();
        startActivity(new Intent(getApplicationContext(),predictionActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewcottontypes = findViewById(R.id.textViewconsumption);
        textViewproduction  = findViewById(R.id.textViewproduction);
        textViewpredictiom  = findViewById(R.id.textViewprediction);





     /*   input= (EditText) findViewById(R.id.number);
        result=(Button) findViewById(R.id.result);
        output=(TextView)findViewById(R.id.answer);

        try{
            tflite= new Interpreter(loadModelFile());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float prediction=func(input.getText().toString());
                output.setText(Float.toString(prediction));
            }
        });*/
    }

    /*private MappedByteBuffer loadModelFile() throws IOException{
        AssetFileDescriptor fileDescriptor= this.getAssets().openFd("");
        FileInputStream inputStream= new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel=inputStream.getChannel();
        long startoffset=fileDescriptor.getStartOffset();
        long declaredLength=fileDescriptor.getLength();
        return  fileChannel.map(FileChannel.MapMode.READ_ONLY,startoffset,declaredLength);
    }

    private float func (String inputString)
    {
        float[] inputVal=new float[1];
        inputVal[0]=Float.valueOf(inputString);
        float[][] outputVal=new float[1][1];
        tflite.run(inputVal,outputVal);
        return outputVal[0][0];
    }*/
}
