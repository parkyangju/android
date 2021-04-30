package com.yj.suntalk;

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

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    StringRequest stringRequest;
    Button btn_login, btn_join;
    EditText edt_id, edt_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        btn_join = findViewById(R.id.btn_join);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);

        String url="http://211.48.213.190:8081/MemberServer/LoginServlet";
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("true")){ //1이 아닌 true로 변경
                    Toast.makeText(getApplicationContext(),"로그인 완료!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    intent.putExtra("id",edt_id.getText().toString()); //키값을 id라는 키값을 넘겨줌
                    startActivity(intent);
                    //startActivity(new Intent(MainActivity.this, ChatActivity.class)
                    // .putExtra("id",edt_id.getText().toString())); 이방법도 사용가능하다

                }else{
                    Toast.makeText(getApplicationContext(),"로그인 실패...", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> temp = new HashMap<>();
                temp.put("id", edt_id.getText().toString());
                temp.put("pw", edt_pw.getText().toString());

                return temp;
            }
        };

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest);
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JoinActivity2.class));
            }
        });
    }
}