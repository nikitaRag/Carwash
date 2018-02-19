package com.example.nekit.start;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static com.example.nekit.start.Login.client;

public class MainActivity extends AppCompatActivity {
 TextView greeting;
    public static String resp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        String formattedDate = df.format(c.getTime());
        String[] date_time= formattedDate.split(" ");
        String[] time = date_time[1].split(":");
        time[0] = Integer.toString(Integer.parseInt(time[0])+1);
        date_time[1] = time[0]+":"+time[1]+":00";

        String post = "client_id=" + Login.client.client_id +"&date=" +date_time[0] + "&time=" + date_time[1];
        DataBase db = Factory.createDB();
        db.execute("comment.php",post);

        try {
            resp= db.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(!resp.equals("0")){
            AlertDialog.Builder AD = new AlertDialog.Builder(MainActivity.this);
            AD.setMessage("Оставьте отзыв о вашей последней мойке");
            AD.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(MainActivity.this,Review.class));
                }
            });
            AD.setNegativeButton("Не хочу", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DataBase DB = Factory.createDB();
                    DB.execute("sendreview.php","client_id=" + Login.client.getClient_id()+"&appointment_id="+ resp);
                }
            });
            AD.show();
        }

        setTitle("Главное меню");
        greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText("Здравствуйте, " + client.getNameSecondname());

    }
    public void onClick(View View){
        startActivity(new Intent(MainActivity.this,Calendar.class));


    }
    public void cancelClick(View view){
        startActivity(new Intent(MainActivity.this,MyAppoint.class));
    }
    public void close(View view){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
