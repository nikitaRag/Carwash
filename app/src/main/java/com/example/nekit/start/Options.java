package com.example.nekit.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;
import static com.example.nekit.start.Login.client;


public class Options extends AppCompatActivity {
    String item = "A";
    TextView price;
    String i_cleaning = "0", diagn = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Выбор услуг");
        setContentView(R.layout.activity_options);
        price = (TextView) findViewById(R.id.textView7);
        String[] classes = {"A", "B", "C", "D", "E", "F", "J", "M", "S"};

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, classes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        AdapterView.OnItemSelectedListener itemClickListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = (String) parent.getItemAtPosition(position);
                price.setText(priceCalc(item, i_cleaning, diagn));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemClickListener);
    }

    public void checkClick1(View view) {
        CheckBox checkBox = (CheckBox) view;

        if (checkBox.isChecked()) {
            i_cleaning = "1";
        } else i_cleaning = "0";
        price.setText(priceCalc(item, i_cleaning, diagn));
    }

    public void checkClick2(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            diagn = "1";
        } else diagn = "0";
        price.setText(priceCalc(item, i_cleaning, diagn));
    }

    public static String priceCalc(String carclass, String interior, String diagnostics) {
        int interiorcleaning = 0, diagnoisticsprice = 0;
        double washprice = 300;
        double rate = 1;
        switch (carclass) {
            case "A":
                rate = 1.0;
                break;
            case "B":
                rate = 1.1;
                break;
            case "C":
                rate = 1.2;
                break;
            case "D":
                rate = 1.3;
                break;
            case "E":
                rate = 1.4;
                break;
            case "F":
                rate = 1.5;
                break;
            case "J":
                rate = 1.4;
                break;
            case "M":
                rate = 1.5;
                break;
            case "S":
                rate = 1.5;
                break;
        }
        switch (interior) {
            case "1":
                interiorcleaning = 400;
                break;
            case "0":
                interiorcleaning = 0;
                break;
        }
        switch (diagnostics) {
            case "1":
                diagnoisticsprice = 400;
                break;
            case "0":
                diagnoisticsprice = 0;
                break;
        }

        washprice = 300 * rate + interiorcleaning * rate + diagnoisticsprice * rate;
        String tsena = Double.toString(washprice).substring(0, Double.toString(washprice).length() - 2);
        return tsena;
    }

    public void sendData(View view) {
        String resp="";
        String appointmentstring = "client_id="+client.getClient_id() +"&time=" + Time.time + "&date=" + Calendar.date + "&car_type=" + item + "&i_cleaning=" + i_cleaning + "&diagnostocs=" + diagn + "&price=" + price.getText().toString();
        DataBase dataBase = Factory.createDB();
        dataBase.execute("add_appointment.php",appointmentstring);
        try {
            resp = dataBase.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (resp.equals("succes")) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Оформлена запись на имя: " +client.getNameSecondname()+"\nНа дату: "+Calendar.date+"\nНа время: "+ Time.time, Toast.LENGTH_LONG);
            toast1.show();
            Intent intent = new  Intent(Options.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


}

