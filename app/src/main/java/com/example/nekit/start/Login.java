package com.example.nekit.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;
import static com.example.nekit.start.Registration.hash;

public class Login extends AppCompatActivity {
    private EditText login;
    private EditText pass;
    private String account;
    private Toast toast1, toast2, toast3;
    private String[] client_data;
    public static Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Вход");
        account = "";
        toast1 = Toast.makeText(getApplicationContext(), "Введите логин и пароль", Toast.LENGTH_SHORT);
        toast2 = Toast.makeText(getApplicationContext(), "Введите правильный логин", Toast.LENGTH_SHORT);
        toast3 = Toast.makeText(getApplicationContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT);
        login = (EditText) findViewById(R.id.login);
        pass = (EditText) findViewById(R.id.password);
        pass.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        enter();
                    }
                }
                return false;
            }
        });
    }

    public void OnClickEnter(View view) {
        enter();
    }

    public void enter() {
        if (login.getText().toString().equals("") || pass.getText().toString().equals("")) {
            toast1.show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(login.getText()).matches()) {
            toast2.show();
        } else {
            DataBase DB1 = Factory.createDB();
            DB1.execute("authorizationSalt.php", "login=" + login.getText().toString());
            String response1 = "";
            try {
                response1 = DB1.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            String passhash = hash(pass.getText().toString());
            passhash = hash(passhash + response1);
            passhash = hash(passhash + "kjudxKJHfKxGV");
            DataBase DB2 = Factory.createDB();
            account = "login=" + login.getText().toString() + "&pass=" + passhash;
            DB2.execute("authorisation.php",account);
            String response2 = "";
            try {
                response2 = DB2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if (!response2.equals("0")) {
                client_data = response2.split(" ");
                client = new Client(Integer.parseInt(client_data[0]),client_data[2],client_data[3],client_data[1]);
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                login.setText("");
                pass.setText("");
            } else toast3.show();
        }

    }
    public void OnClickRegistration(View view){
        startActivity(new Intent(Login.this, Registration.class));
    }

}
