package com.example.nekit.start;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import static com.example.nekit.start.R.id.pass;

public class Registration extends AppCompatActivity {
    private EditText name;
    private EditText secondname;
    private EditText surname;
    private EditText number;
    private EditText email;
    private EditText password;
    private EditText password_2;
    private Toast toast1, toast2, toast3, toast4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Регистрация");
        name = (EditText) findViewById(R.id.name);
        secondname = (EditText) findViewById(R.id.secondname);
        surname = (EditText) findViewById(R.id.surname);
        number = (EditText) findViewById(R.id.number);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(pass);
        password_2 = (EditText) findViewById(R.id.pass2);

        InputFilter IF = new InputFilter() {
            public CharSequence filter(CharSequence src, int start,
                                       int end, Spanned dst, int dstart, int dend) {
                if (src.equals("")) { // for backspace
                    return src;
                }
                if (src.toString().matches("[а-яА-Я ]+")) {
                    return src;
                }
                return "";
            }
        };
        name.setFilters(new InputFilter[]{IF});
        secondname.setFilters(new InputFilter[]{IF});
        surname.setFilters(new InputFilter[]{IF});
        toast1 = Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT);
        toast2 = Toast.makeText(getApplicationContext(), "Введите правильный E-mail", Toast.LENGTH_SHORT);
        toast3 = Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT);
        toast4 = Toast.makeText(getApplicationContext(), "Этот E-mail уже занят", Toast.LENGTH_SHORT);
    }

    public void registration(View view) {
        if (name.getText().toString().equals("") || secondname.getText().toString().equals("") || surname.getText().toString().equals("") || number.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || password_2.getText().toString().equals("")) {
            toast1.show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
            toast2.show();
        } else if (!password.getText().toString().equals(password_2.getText().toString())) {
            toast3.show();
        } else {


            String dict = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/*-+.><,";
            char[] symb = dict.toCharArray();
            String salt = "";
            Random rnd = new Random(System.currentTimeMillis());
            for (int i = 0; i < 5; i++) {
                int numb = rnd.nextInt(dict.length());
                salt += symb[numb];
            }
            String passhash = hash(password.getText().toString());
            passhash = hash(passhash + salt);
            passhash = hash(passhash + "kjudxKJHfKxGV");
            String date = Calendar.getDate();
            String newacc = "login=" + email.getText().toString() + "&salt=" + salt + "&password=" + passhash + "&reg_date=" + date + "&name=" + surname.getText().toString() + " " + name.getText().toString() + " " + secondname.getText().toString() + "&number=" + number.getText().toString();
            DataBase db = Factory.createDB();
            String resp = null;
            try {
                db.execute("registration.php", newacc);
                resp = db.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            try {
                if (!String.format("%x", new BigInteger(1, resp.getBytes("UTF-8"))).equals("efbbbf30")){
                    startActivity(new Intent(Registration.this, Login.class));
                } else toast4.show();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

    public static String hash(String str) {
        StringBuilder stringbuilder = new StringBuilder();
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] bytes = sha256.digest(str.getBytes());
            for (byte b : bytes) {
                stringbuilder.append(String.format("%02X", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringbuilder.toString();
    }
}
