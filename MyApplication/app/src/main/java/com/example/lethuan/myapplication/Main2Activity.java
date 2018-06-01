package com.example.lethuan.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Main2Activity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextView countdownTimerText;
//    EditText minutes;
    EditText seconds;
    Button startTimer;
    Button resetTimer;
    int kt;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference led = db.getReference("led");
    DatabaseReference fan = db.getReference("fan");
    DatabaseReference light = db.getReference("light");
    DatabaseReference temperature = db.getReference("temperature");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button bt1 = (Button)findViewById(R.id.bt1);
        Button bt3 = (Button)findViewById(R.id.bt3);
        Button btBat1 = (Button)findViewById(R.id.btBat1);
        Button btTat1 = (Button)findViewById(R.id.btTat1);
        Button btBat2 = (Button)findViewById(R.id.btBat2);
        Button btTat2 = (Button)findViewById(R.id.btTat2);
        Button btAuto = (Button)findViewById(R.id.btAuto);

        final EditText edLight = (EditText)findViewById(R.id.edLight);
        final EditText edTemperature = (EditText)findViewById(R.id.edTemperature);

        btAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tempLight = edLight.getText().toString();
                String tempTemperature = edTemperature.getText().toString();

                final int[] currentLight = new int[0];
//                final float[] currentTemperature = new float[0];

                light.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int value = dataSnapshot.getValue(Integer.class);
//                        currentLight[0] = value;
                        if(value > Integer.parseInt(tempLight)) {
                            led.setValue("0");
                        } else {
                            led.setValue("1");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//                temperature.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        float value = dataSnapshot.getValue(Float.class);
//                        currentTemperature[0] = value;
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

//                if(value[0] > Integer.parseInt(tempLight)) {
//                    led.setValue("0");
//                } else {
//                    led.setValue("1");
//                }

//                if(currentTemperature[0] > Float.parseFloat(tempTemperature)){
//                    fan.setValue("1");
//                } else {
//                    fan.setValue("0");
//                }
            }
        });


        btBat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kt = 1; //bat thiet bi 1
                time();

            }
        });

        btTat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kt = 2; //bat thiet bi 2
                time();

            }
        });

        btBat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kt = 3; //bat thiet bi 1
                time();

            }
        });

        btTat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kt = 4; //bat thiet bi 2
                time();

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main2 = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(main2);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main3 = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(main3);
            }
        });

    }


    //Stop Countdown method
    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    //Start Countodwn method
    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                countdownTimerText.setText(hms);//set text
            }

            public void onFinish() {
                if(kt == 1){
                    countdownTimerText.setText("Bat thiet bi 1!!"); //dem xong roi thuc hien
                    led.setValue("1");
                }
                else if(kt == 2){
                    countdownTimerText.setText("Tat thiet bi 1!!"); //On finish change timer text
                    led.setValue("0");
                }
                else if(kt == 3){
                    countdownTimerText.setText("Bat thiet bi 2!!"); //On finish change timer text
                    fan.setValue("1");
                }
                else if(kt == 4){
                    countdownTimerText.setText("Tat thiet bi 2!!"); //On finish change timer text
                    fan.setValue("0");
                }
                countDownTimer = null;//set CountDownTimer to null
                startTimer.setText(getString(R.string.start_timer));//Change button text
            }
        }.start();

    }
    void time(){
        LayoutInflater li = LayoutInflater.from(Main2Activity.this);
        View customDialogView = li.inflate(R.layout.dialog_time, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main2Activity.this);
        alertDialogBuilder.setView(customDialogView);

        countdownTimerText = (TextView) customDialogView.findViewById(R.id.countdownText);
//        minutes = (EditText) customDialogView.findViewById(R.id.enterMinutes);
        seconds = (EditText) customDialogView.findViewById(R.id.enterMinutes);
        startTimer = (Button) customDialogView.findViewById(R.id.startTimer);
        resetTimer = (Button) customDialogView.findViewById(R.id.resetTimer);

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.startTimer:
                        //If CountDownTimer is null then start timer
                        if (countDownTimer == null) {
//                            String getMinutes = minutes.getText().toString();//Get minutes from edittexf
                            String getSeconds = seconds.getText().toString();
                            //Check validation over edittext
                            if (!getSeconds.equals("") && getSeconds.length() > 0) {
                                //int noOfMinutes = Integer.parseInt(getMinutes) * 60 * 1000;//Convert minutes into milliseconds
                                int noOfMinutes = Integer.parseInt(getSeconds) * 1000;
                                startTimer(noOfMinutes);//start countdown
                                startTimer.setText(getString(R.string.stop_timer));//Change Text

                            } else
                                Toast.makeText(Main2Activity.this, "Please enter no. of Minutes.", Toast.LENGTH_SHORT).show();//Display toast if edittext is empty
                        } else {
                            //Else stop timer and change text
                            stopCountdown();
                            startTimer.setText(getString(R.string.start_timer));
                        }
                        break;
                    case R.id.resetTimer:
                        stopCountdown();//stop count down
                        startTimer.setText(getString(R.string.start_timer));//Change text to Start Timer
                        countdownTimerText.setText(getString(R.string.timer));//Change Timer text
                        break;
                }
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
