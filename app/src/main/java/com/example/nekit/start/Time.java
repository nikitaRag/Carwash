package com.example.nekit.start;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Time extends AppCompatActivity {
    Button[] buttons;
    static String time="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        setTitle("Выбор времени");
        String[] busyTime=null;
        if (!Calendar.Calendresponse.equals("")) {
            busyTime= Calendar.Calendresponse.trim().split(" ");
        }
        buttons = new Button[]{
                (Button) findViewById(R.id.button11), (Button) findViewById(R.id.button12), (Button) findViewById(R.id.button13), (Button) findViewById(R.id.button14),
                (Button) findViewById(R.id.button15), (Button) findViewById(R.id.button16), (Button) findViewById(R.id.button17), (Button) findViewById(R.id.button18),
                (Button) findViewById(R.id.button19), (Button) findViewById(R.id.button20), (Button) findViewById(R.id.button21), (Button) findViewById(R.id.button22),
                (Button) findViewById(R.id.button23), (Button) findViewById(R.id.button24), (Button) findViewById(R.id.button25), (Button) findViewById(R.id.button26),
                (Button) findViewById(R.id.button27), (Button) findViewById(R.id.button28), (Button) findViewById(R.id.button29), (Button) findViewById(R.id.button30),
                (Button) findViewById(R.id.button31), (Button) findViewById(R.id.button32), (Button) findViewById(R.id.button33), (Button) findViewById(R.id.button34),
                (Button) findViewById(R.id.button35), (Button) findViewById(R.id.button36), (Button) findViewById(R.id.button37), (Button) findViewById(R.id.button38),
                (Button) findViewById(R.id.button39), (Button) findViewById(R.id.button40), (Button) findViewById(R.id.button41), (Button) findViewById(R.id.button42),
                (Button) findViewById(R.id.button43), (Button) findViewById(R.id.button44), (Button) findViewById(R.id.button45), (Button) findViewById(R.id.button46),
        };
        if (busyTime != null) {
            for (int i = 0; i < 36; i++) {
                for (int j = 0; j < busyTime.length; j++) {
                    if (buttons[i].getText().toString().equals(busyTime[j].substring(0, 5))) {
                        buttons[i].setEnabled(false);
                        buttons[i].setTextColor(Color.RED);
                    }
                }
            }
        }
        for (int i = 0; i < 36; i++) {
            final  int iter = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    time = buttons[iter].getText().toString();
                    startActivity(new Intent(Time.this,Options.class));
                }
            });
        }
    }
}
