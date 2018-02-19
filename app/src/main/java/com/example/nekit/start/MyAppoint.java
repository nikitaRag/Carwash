package com.example.nekit.start;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MyAppoint extends AppCompatActivity {


    ListView lv;
    SimpleAdapter adapter;
    int pos = 0;
    TextView zero;
    String[][] appointments=null;
    ArrayList<HashMap<String, String>> list=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appoint);
        zero = (TextView) findViewById(R.id.zero);
        lv = (ListView) findViewById(R.id.lv);
        String today = Calendar.getDate();
        final String post = "id=" + Login.client.client_id + "&date=" + today;
        DataBase db = new DataBase();
        db.execute("showAppoint.php", post);
        String resp = "";
        try {
            resp = db.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (!resp.equals("")) {
            String[] data = resp.split(" ");
            appointments = new String[data.length / 5][5];
            list = new ArrayList<>();
            HashMap<String, String> map;
            int k = 0;
            for (int i = 0; i < data.length / 5; i++) {
                for (int j = 0; j < 5; j++) {
                    appointments[i][j] = data[k];
                    k++;
                }
                map = new HashMap<>();
                map.put("date", transformDate(appointments[i][2]));
                map.put("time",tranformtime(appointments[i][1]));
                map.put("opt",options(appointments[i][3],appointments[i][4]));
                list.add(map);
            }
            registerForContextMenu(lv);
            adapter = new SimpleAdapter(this, list, R.layout.list_layout, new String[]{"date", "time", "opt"}, new int[]{R.id.textdate, R.id.texttime, R.id.textopt});
            lv.setAdapter(adapter);
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    pos = position;
                    return false;
                }
            });
        } else {
            zero.setVisibility(View.VISIBLE);
        }
    }
    public static String transformDate(String date) {
        String[] tdate = date.split("-");
        return tdate[2] + "." + tdate[1];
    }

    public static String tranformtime(String time) {
        String[] ttime = time.split(":");
        return ttime[0] + ":" + ttime[1];
    }

    public static String options(String interior, String diag) {
        switch (interior + diag) {
            case "00":
                return "Мойка";
            case "01":
                return "Мойка + Диагностика";
            case "10":
                return "Мойка c салоном";
            case "11":
                return "Мойка с салоном + Диагностика";
        }
        return null;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(Menu.NONE,1,Menu.NONE,"Отменить запись");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        String send = "id=" + appointments[pos][0];
        DataBase db2 = Factory.createDB();
        db2.execute("deleteAppointment.php",send);
        String get="";
        try {
           get =  db2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        list.remove(pos);
        adapter.notifyDataSetChanged();//обновление адаптера после изменения данных
        if (list.isEmpty()){
            zero.setVisibility(View.VISIBLE);
        }
        Toast toast = Toast.makeText(this, "Запись отменена", Toast.LENGTH_LONG);
        toast.show();
        return true;
    }
}

