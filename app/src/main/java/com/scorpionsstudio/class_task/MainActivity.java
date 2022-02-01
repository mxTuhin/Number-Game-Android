package com.scorpionsstudio.class_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static int point;
    public int numOne;
    public int numTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOne = findViewById(R.id.buttonOne);
        Button buttonTwo = findViewById(R.id.buttonTwo);
        TextView pointText = findViewById(R.id.pointCounter);
        TextView timerCounter = findViewById(R.id.timerCounter);


        initiateNumbers(buttonOne, buttonTwo);

        Timer timer  = new Timer();
        TimerTask timerTask = new TimerTask() {
            double timeNow = 3.0;
            @Override
            public void run() {
                --timeNow;
                timerCounter.setText(""+timeNow);

            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000,1000);

        buttonOne.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(numOne>numTwo){
                    Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                    pointText.setText("Point: "+ (++point));

                }else {
                    Toast.makeText(getApplicationContext(), "Point Reduced", Toast.LENGTH_SHORT).show();
                    pointText.setText("Point: "+ (--point));
                }
                initiateNumbers(buttonOne, buttonTwo);

            }
        });

        buttonTwo.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(numTwo>numOne){
                    Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                    pointText.setText("Point: "+ (++point));
                }else {
                    Toast.makeText(getApplicationContext(), "Point Reduced", Toast.LENGTH_SHORT).show();
                    pointText.setText("Point: "+ (--point));
                }
                initiateNumbers(buttonOne, buttonTwo);

            }
        });

    }

    private void initiateNumbers(Button buttonOne, Button buttonTwo){
        numOne = (int) ((Math.random() * (10)) + 0);
        numTwo = (int) ((Math.random() * (10)) + 0);
        if(numOne==numTwo){
            numTwo = numTwo-1;
        }


        buttonOne.setText(""+numOne);
        buttonTwo.setText(""+numTwo);
    }
}