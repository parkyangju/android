package com.yj.iotproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] sensor = {"dht11", "mq2"};
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, sensor);
        Spinner spinner = (Spinner) findViewById(R.id.spinner); //모양 생성
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                //다이얼로그
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("센서 로그 정보 수신중...");
                dialog.show();
                //2. 받는방법 -> Listener 볼리 자체가 thread 형식으로 되어있음
                //listener(Json)
                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) { //[{},{},{}] ->json array
                        dialog.dismiss();
                        try {
                            JSONArray array = new JSONArray(response);
                            items.clear();
                            for (int i = 0; i < array.length(); i++) {
                                //{tmp,hum,create_at}
                                JSONObject obj = array.getJSONObject(i);
                                //Itemvo(묶) - ArrayList(담) - 아래쪽에
                                items.add(new Item(obj.getInt("tmp"),
                                        obj.getInt("hum"),
                                        obj.getString("create_at")));
                            }
                            //중간에 Adapter를 만들어 주어야 함. ListView에 데이터를 연결
                            //DATA -> Adapter(제공,커스텀)
                            ItemAdapter adapter = new ItemAdapter(MainActivity.this);
                            ListView listView = findViewById(R.id.listview);
                            listView.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                //node.js 서버로 dht11 센서의 온도, 습도, 날짜 데이터를 요청
                //1. 요청하는 방법 ->API(Volley) :별도의 class - DHT11Sensor
                StringRequest dht11 = new DHT11Sensor(sensor[i], listener);
                dht11.setShouldCache(false); //항상 새롭게 데이터 받으려고 하는것
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(dht11); //실행

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }//onCreate

    public void clickLedOnButton(View view) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "LED가 켜짐:" + obj.get("led"),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        StringRequest led = new LEDSensor("on", listener);
        led.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(led); //실행
    }// clickLedOnButton

    public void clickLedOffButton(View view) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "LED가 꺼짐:" + obj.get("led"),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        StringRequest led = new LEDSensor("off", listener);
        led.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(led); //실행
    }

    class Item {
        public String created_at;
        int temp, humidity;
        String create_at;

        Item(int temp, int humidity, String create_at) {
            this.temp = temp;
            this.humidity = humidity;
            this.create_at = create_at;
        }
    }//_Item_

    ArrayList<Item> items = new ArrayList<Item>();

    //커스텀 뷰
    class ItemAdapter extends ArrayAdapter {

        public ItemAdapter(Context context) {
            super(context, R.layout.list_sensor_item, items);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater =
                        (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_sensor_item, null);
            }
            TextView tempText = view.findViewById(R.id.temp);
            TextView humidityText = view.findViewById(R.id.humidity);
            TextView createdAtText = view.findViewById(R.id.created_at);
            tempText.setText("온도:" + items.get(position).temp);
            humidityText.setText("습도:" + items.get(position).humidity);
            createdAtText.setText("수집정보(날짜/시간)" + items.get(position).created_at);
            return view;
        }
    }

}// class