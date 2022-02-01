package com.scorpionsstudio.class_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static int point;
    public int numOne;
    public int numTwo;
    public static double timeNow=3.0f;
    public CountDownTimer countDownTimer;
    public boolean isPositive=false;
    public int healthCounter=0;
    public ImageView[] health=new ImageView[3];
    public TextView finalScore;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOne = findViewById(R.id.buttonOne);
        Button buttonTwo = findViewById(R.id.buttonTwo);
        playAgain = findViewById(R.id.playAgain);
        TextView pointText = findViewById(R.id.pointCounter);
        TextView timerCounter = findViewById(R.id.timerCounter);
        TextView prompt = findViewById(R.id.prompt);
        finalScore = findViewById(R.id.finalScore);
        finalScore.setVisibility(View.GONE);
        playAgain.setVisibility(View.GONE);


        health[0] = findViewById(R.id.lifeOne);
        health[1] = findViewById(R.id.lifeTwo);
        health[2] = findViewById(R.id.lifeThree);



        initiateNumbers(buttonOne, buttonTwo, prompt);
        runCountDown(timerCounter, pointText, buttonOne, buttonTwo, prompt);



        buttonOne.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isPositive){
                    if(numOne>numTwo){
                        Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (++point));

                    }else {
                        Toast.makeText(getApplicationContext(), "Point Reduced", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (--point));
                    }
                }else{
                    if(numOne<numTwo){
                        Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (++point));

                    }else {
                        Toast.makeText(getApplicationContext(), "Point Reduced", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (--point));
                    }
                }

                initiateNumbers(buttonOne, buttonTwo, prompt);
                countDownTimer.cancel();
                runCountDown(timerCounter, pointText, buttonOne, buttonTwo, prompt);


            }
        });

        buttonTwo.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isPositive){
                    if(numTwo>numOne){
                        Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (++point));
                    }else {
                        Toast.makeText(getApplicationContext(), "Point Reduced", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (--point));
                    }
                }else{
                    if(numTwo<numOne){
                        Toast.makeText(getApplicationContext(), "Point Added", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (++point));
                    }else {
                        Toast.makeText(getApplicationContext(), "Point Reduced", Toast.LENGTH_SHORT).show();
                        pointText.setText("Point: "+ (--point));
                    }
                }

                initiateNumbers(buttonOne, buttonTwo, prompt);
                countDownTimer.cancel();
                runCountDown(timerCounter, pointText, buttonOne, buttonTwo, prompt);


            }
        });

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                point=0;
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private void initiateNumbers(Button buttonOne, Button buttonTwo, TextView prompt){
        int min = 10;
        int max = 20;
        numOne = (int) ((Math.random() * (min-max)) + min);
        numTwo = (int) ((Math.random() * (min-max)) + min);
        if(numOne==numTwo){
            numTwo = numTwo-1;
        }


        buttonOne.setText(""+numOne);
        buttonTwo.setText(""+numTwo);

        int promptIdentifier = (int) ((Math.random() * (10)) + 0);

        if(promptIdentifier%2==0){
            prompt.setText("Larger");
            prompt.setTextColor(getResources().getColor(R.color.green_custom_01));
            isPositive = true;
        }else{
            prompt.setText("Smaller");
            prompt.setTextColor(getResources().getColor(R.color.red_custom_01));
            isPositive=false;
        }

    }

    private void runCountDown(TextView timerCounter, TextView pointText, Button buttonOne, Button buttonTwo, TextView prompt){
         countDownTimer = new CountDownTimer(2000, 100){

            public void onTick(long millisUntilFinished){
                DecimalFormat locDecFormat = new DecimalFormat("0.00");
                timeNow = (float)millisUntilFinished/1000;
                timerCounter.setText("Timer: "+locDecFormat.format(timeNow));
                if(healthCounter==3){
                    countDownTimer.cancel();
                    prompt.setText("Game Over");
                    prompt.setTextColor(getResources().getColor(R.color.red_custom_01));
                    buttonOne.setEnabled(false);
                    buttonTwo.setEnabled(false);
                    pointText.setVisibility(View.GONE);
                    finalScore.setVisibility(View.VISIBLE);
                    finalScore.setText("Final Score: "+point);
                    timerCounter.setVisibility(View.GONE);
                    playAgain.setVisibility(View.VISIBLE);

                }

            }

            public  void onFinish(){
                countDownTimer.cancel();
                pointText.setText("Point: "+ (--point));
                initiateNumbers(buttonOne, buttonTwo, prompt);
                runCountDown(timerCounter, pointText, buttonOne, buttonTwo, prompt);
                health[healthCounter].setImageAlpha(0);
                healthCounter++;
            }
        }.start();
    }
}