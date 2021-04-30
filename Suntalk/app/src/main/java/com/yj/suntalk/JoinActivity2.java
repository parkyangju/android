package com.yj.suntalk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class JoinActivity2 extends AppCompatActivity {
    //입력한 id, pw정보를 서버로 전송
    //Volly Library 사용
    //1. Volly Library 임포트
    //2. requestQueue, StringRequest

    RequestQueue requestQueue; //서버와 통신할 통로
    StringRequest stringRequest; //내가 전송할 데이터
    EditText edt_id,edt_pw;
    Button btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);

        btn_join = findViewById(R.id.btn_join);

        //3.requestQueue생성 강사컴
        String url="http://211.48.213.190:8081/MemberServer/JoinServlet";

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //4. StringRequest 생성
        //1) 전송받식
        //2) url(서버의 주소)
        //3) 응답리스너
        //4) 에러리스너

        //5. 전송할 데이터 미리 정의!
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //서버에서 돌려준 응답을 처리
                if(response.equals("1")){
                    Toast.makeText(getApplicationContext(),"회원가입 완료!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"회원가입 실패...", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            //stringrequest 객체범위

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //전송할 데이터 key, value로 셋팅하기
                Map<String, String> temp = new HashMap<>();
                temp.put("id", edt_id.getText().toString());
                temp.put("pw", edt_pw.getText().toString());

                return temp;
            }
        };

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //6. 서버로 StringRequest쏘기
                requestQueue.add(stringRequest);
            }
        });
        //7. 인터넷 권한주기 (통신이 되어야 하니까)
        //강사자리 서버 연결 후 여기서 회원가입 버튼 실행하게 되면 servlet 창에 띄우게 된다.

        //login 버튼 눌렀을 때 성공하면 성공했다는 Toast, 실패하면 실패했다는 Toast
        //로그인 성공 시 "true"라는 문자열이 반환된다.
        //loginServlet 으로 접속
    }
}