package com.example.lethuan.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt2 = (Button)findViewById(R.id.bt2);
        Button bt3 = (Button)findViewById(R.id.bt3);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main2 = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(main2);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main3 = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(main3);
            }
        });

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference led = db.getReference("led");
        final DatabaseReference fan = db.getReference("fan");
        DatabaseReference light = db.getReference("light");
        DatabaseReference temperature = db.getReference("temperature");
        DatabaseReference humidity = db.getReference("humidity");

        ToggleButton toggleLED = (ToggleButton) findViewById(R.id.tgled);
        toggleLED.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    led.setValue("1");
                } else {
                    led.setValue("0");
                }
            }
        });

        ToggleButton toggleFAN = (ToggleButton) findViewById(R.id.tgfan);
        toggleFAN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    fan.setValue("1");
                } else{
                    fan.setValue("0");
                }
            }
        });

        final TextView tvLight = (TextView) findViewById(R.id.tvAnhSang);
        light.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                tvLight.setText(Integer.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final TextView tvTemperature = (TextView) findViewById(R.id.tvC);
        temperature.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float value = dataSnapshot.getValue(Float.class);
                tvTemperature.setText(Float.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final TextView tvHumidity = (TextView) findViewById(R.id.tvDoAm);
        humidity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float value = dataSnapshot.getValue(Float.class);
                tvHumidity.setText(Float.toString(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
