package com.yj.ex0417_parking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MarkerItem> markerItems;
    private RecyclerView recyclerView;

    TextView msp_location, msp_date, msp_type, msp_num;
    Button btn;
//    ListView lv;
    ImageView img;

    MarkerItem markerItem;

    final int add = 1;

    String url = "http://172.30.1.23:8081/smartcar/api/parkingListSelect?msp_type=2";
    RequestQueue requestQueue;

    //ArrayList<MarkerItem> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        manager = new DBManager(getApplicationContext());

        btn = findViewById(R.id.btn_add);
        //img = findViewById(R.id.img);

        msp_location = findViewById(R.id.msp_location);
        msp_date = findViewById(R.id.msp_date);
        msp_type = findViewById(R.id.msp_type);
        msp_num = findViewById(R.id.msp_num);

        recyclerView = findViewById(R.id.recyclerView);
//        lv = findViewById(R.id.listview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        markerItems = new ArrayList<>();
        markerItems.add(new MarkerItem(markerItem));

        requestQueue = Volley.newRequestQueue(this);
        ContactAdapter adapter = new ContactAdapter(markerItems);
        //ResvAdapter adapter = new ResvAdapter(markerItems);

//        lv.setAdapter(adapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                //파라미터로 응답받은 결과 JsonArray를 분석
                markerItems.clear();
                adapter.notifyDataSetChanged();
                try {

                    //for (int i = response.length(); i > 0; i--) {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //int no= Integer.parseInt(jsonObject.getString("no")); //no가 문자열이라서 바꿔야함.
                        String msp_no = jsonObject.getString("msp_no");
                        String msm_no = jsonObject.getString("msm_no");
                        String msp_location = jsonObject.getString("msp_location");
                        String msp_lat = jsonObject.getString("msp_lat");
                        String msp_lon = jsonObject.getString("msp_lon");
                        //String msp_price = jsonObject.getString("msp_price");
                        String msp_num = jsonObject.getString("msp_num");
                        String msp_type = jsonObject.getString("msp_type");
                        String msp_date = jsonObject.getString("msp_date");

                        markerItems.add(new MarkerItem(msp_no, msm_no, msp_location, msp_lat, msp_lon,
                                msp_num, msp_type, msp_date));
                        //System.out.println(markerItems.toString());
                        //adapter.notifyDataSetInvalidated();
                        adapter.notifyItemInserted(0);
                        adapter.notifyItemChanged(0);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);

                        //   lv.setAdapter(adapter);
                        //    Log.v("adapter :: ", String.valueOf(adapter));

//                        adapter.notifyDataSetChanged();
                        //adapter.notifyItemInserted(0);
                        //adapter.notifyItemChanged(0);
                        //adapter.notifyItemRemoved(0);
                        //Log.v("adapter :: ", String.valueOf(adapter));
                        //lv.setAdapter(adapter);
                        //Log.v("adapter count :: ", String.valueOf(lv.getAdapter().getCount()));
                        //lv.setHasFixedSize(true);
                        //adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
        Toast.makeText(getApplicationContext(), "성공!", Toast.LENGTH_SHORT).show();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });


//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("삭제하기");
//                builder.setTitle("삭제하시겠습니까?");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        TextView tv_location = view.findViewById(R.id.msp_location);
//                  //      manager.delete(tv_location.getText().toString());
//                        markerItems.remove(position);
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//
//                builder.setNegativeButton("cancel", null);
//                builder.show();
//            }
//        });


//        Cursor cursor = manager.getAllData();
//        Cursor랑 JDBC 에서 ResultSet과 같은역할 화살표와 같은 역할
//        데이터 (행)을 가리키고있는 화살표


//        while (cursor.moveToNext()) {
        //커서를 아래칸으로 옮기는데 데이터가 있으면 True, 없으면 False
        //해당 행의 데이터 가지고 와서 ArrayList 축척하기

//            String msp_no= cursor.getString(0);
//            String msm_no= cursor.getString(1);
//            String msp_location= cursor.getString(2);
//            double msp_lat= cursor.getDouble(3);
//            double msp_lon= cursor.getDouble(4);
//            int msp_price= cursor.getInt(5);
//            int msp_num = cursor.getInt(6);
//            int msp_type = cursor.getInt(7);
//            String msp_date= cursor.getString(8);

//            contacts.add(new MarkerItem(msp_no, msm_no, msp_location, msp_lat, msp_lon, msp_price, msp_num,msp_type,msp_date));
//        }
//        adapter.notifyDataSetChanged();
//
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == add) {
//            if (resultCode == RESULT_OK) {
//                String msp_no= data.getStringExtra("msp_no");
//                String msm_no= data.getStringExtra("msm_no");
//                String msp_location= data.getStringExtra("msp_location");
//                String msp_lat= data.getStringExtra("msp_lat");
//                String msp_lon= data.getStringExtra("msp_lon");
//                String msp_num= data.getStringExtra("msp_num");
//                String msp_type= data.getStringExtra("msp_type");
//                String msp_price=data.getStringExtra("msp_price");
//                String msp_date= data.getStringExtra("msp_date");
//
//                this.markerItems.add(new MarkerItem(msp_no, msm_no, msp_location, msp_lat, msp_lon, msp_price, msp_num,msp_type,msp_date));
//
//                //manager 클래스의 insert  메소드 호출
////                manager.insert(msp_location,msp_date, msp_no, msm_no);
//            }
//        }
//adapter.notifyDataSetChanged(); 쓰지 않아도 자동으로 업데이트 됩니다.
//    }
    }
}




