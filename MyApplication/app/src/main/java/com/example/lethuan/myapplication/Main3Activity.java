package com.example.lethuan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button bt1 = (Button)findViewById(R.id.bt1);
        Button bt2 = (Button)findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main2 = new Intent(Main3Activity.this,MainActivity.class);
                startActivity(main2);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main3 = new Intent(Main3Activity.this,Main2Activity.class);
                startActivity(main3);
            }
        });
    }
}
