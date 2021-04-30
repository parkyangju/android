package com.kmg.naver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.ImageButton;

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


public class boardActivity extends AppCompatActivity {
    //ArrayList<BoardVO> board = new ArrayList<>();
    private List<BoardVO> boardVOList;
    private RecyclerView recyclerView;

    // 여기
    //private TextView msnb_no;
    private TextView msnb_subject;
    private TextView msnb_content;
    private TextView msnb_date;
    private TextView msnb_no;
    //private TextView msm_no;

    private ImageButton btn_write;

    BoardVO boardVO;


    private final int WRITE = 1;

    String url = "http://172.30.1.23:8081/smartcar/api/noticeList";
    RequestQueue requestQueue;  // 서버와 통신할 통로


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //msnb_no = findViewById(R.id.msnb_no);
        //msm_no = findViewById(R.id.msm_no);
        msnb_subject = findViewById(R.id.msm_subject);
        msnb_content = findViewById(R.id.msnb_content);
        msnb_date = findViewById(R.id.msnb_date);
        msnb_no = findViewById(R.id.msnb_no);

        recyclerView = findViewById(R.id.recyclerView);
        btn_write = findViewById(R.id.btn_write);

        //리사이클러뷰의 레이아웃 매니저 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);//, LinearLayoutManager.VERTICAL, false

        recyclerView.setLayoutManager(layoutManager);

        boardVOList = new ArrayList<>();
        boardVOList.add(new BoardVO(boardVO));

        requestQueue = Volley.newRequestQueue(this);
        boardAdapter adapter = new boardAdapter(boardVOList);

        //결과를 JsonArray 받을 것이므로.. StringRequest가 아니라.. JsonArrayRequest를 이용할 것임
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                //파라미터로 응답받은 결과 JsonArray를 분석

                boardVOList.clear();
                adapter.notifyDataSetChanged();
                //System.out.println(response.length());
                try {

                    //for (int i = response.length(); i > 0; i--) {
                    for (int i = 0; i<response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);

                        //int no= Integer.parseInt(jsonObject.getString("no")); //no가 문자열이라서 바꿔야함.
                        String msnb_no = jsonObject.getString("msnb_no");
                        String msm_no = jsonObject.getString("msm_no");
                        String msnb_subject = jsonObject.getString("msnb_subject");
                        String msnb_content = jsonObject.getString("msnb_content");
                        String msnb_date = jsonObject.getString("msnb_date");
                        String msnb_hit = jsonObject.getString("msnb_hit");
                        String msm_name = jsonObject.getString("msm_name");
                        //System.out.println(msnb_subject);

                        boardVOList.add(new BoardVO(msnb_no, msm_no, msnb_subject, msnb_content, msnb_date, msnb_hit, msm_name));
                        //System.out.println(boardVOList.toString());
                        adapter.notifyItemInserted(0);
                        //adapter.notifyItemRemoved(0);
                        adapter.notifyItemChanged(0);

                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);
        Toast.makeText(getApplicationContext(), "성공ㅋ", Toast.LENGTH_SHORT).show();

        btn_write.setOnClickListener(new View.OnClickListener() {   // 글작성 버튼 눌렀을 때
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(boardActivity.this, newboard.class);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });
    }


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WRITE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "작성완료", Toast.LENGTH_SHORT).show();
                boardAdapter adapter = new boardAdapter(boardVOList);

                String msm_no = data.getStringExtra("msm_no");
                String msnb_subject = data.getStringExtra("msnb_subject");
                String msnb_content = data.getStringExtra("msnb_content");


                recyclerView.setAdapter(adapter);

                //RequestQueue requestQueue= Volley.newRequestQueue(this);
                boardVOList.add(0, new BoardVO(msnb_subject, msnb_content, msm_no));
                System.out.println("---------------------------------" + boardVOList.toString());
                adapter.notifyDataSetChanged();
                //requestQueue.add(jsonArrayRequest);

            }
        }

    }*/
}