package com.example.nekit.start;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Calendar extends AppCompatActivity {
    public static CalendarView calendar;
    public static String date = "";
    static String  Calendresponse="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Выбор даты");
        setContentView(R.layout.activity_calendar);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        long days = 1000*60*60*24*14;
        calendar.setMinDate(calendar.getDate()-1);
        //calendar.setMinDate(1);
        calendar.setMaxDate(calendar.getDate()+days);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + ++month + "-" + dayOfMonth;
            }
        });
    }

    public void selectDate(View view) {

        DataBase db = Factory.createDB();
        if (date==""){
            date = getDate();
        }
        db.execute("date.php", "date=" + date);

        try {
            Calendresponse = db.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(Calendar.this,Time.class));
    }
    public static String getDate(){
        Date d = new Date();
        return String.format("%tF",d);
    }

}

