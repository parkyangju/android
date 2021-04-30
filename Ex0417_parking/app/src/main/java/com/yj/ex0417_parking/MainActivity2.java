package com.yj.ex0417_parking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    RequestQueue requestQueue;  // 서버와 통신할 통로
    StringRequest stringRequest;    // 내가 전송할 데이터


    private EditText edt_location, edt_date, edt_type, edt_num;
    private Button btn_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edt_location = findViewById(R.id.edt_location);
        edt_date = findViewById(R.id.edt_date);
        edt_type = findViewById(R.id.edt_type);
        edt_num = findViewById(R.id.edt_num);
        btn_ok = findViewById(R.id.btn_ok);

        String url ="http://172.30.1.23:8081/smartcar/api/noticeInsert";
        requestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 서버에서 돌려준 응답을 처리
                if (response.equals("1")){
                    Toast.makeText(getApplicationContext(),"등록 완료 ㅋ",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"등록 실패 ㅋ",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error.toString(); 하면 에러 찍힘
            }
        }){
            // StringRequest 객체 범위

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 전송할 데이터 Key, Value 로 셋팅하기
                Map<String, String> temp = new HashMap<>();
                temp.put("msp_location",edt_location.getText().toString());
                temp.put("msp_date",edt_date.getText().toString());
                temp.put("msp_type",edt_type.getText().toString());
                temp.put("msp_num",edt_num.getText().toString());
                return temp;
            }
        };


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);

                requestQueue.add(stringRequest);

            }
        });
    }

}