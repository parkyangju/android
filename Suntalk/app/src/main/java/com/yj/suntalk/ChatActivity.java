package com.yj.suntalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    //임시 채팅 데이터 생성
    ArrayList<ChatVO> data = new ArrayList<>();
    String login_id = null; //현재 로그인한 사람의 id
    TextView tv_id;
    ListView lv;
    Button btn_send;
    EditText edt_input;

    //FirebaseDatabase를 사용하기 위한 객체생성
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://suntalk-b9e12-default-rtdb.firebaseio.com/");
   //Database의 주소 가지고오기
    DatabaseReference myRef = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        login_id = getIntent().getStringExtra("id");
        tv_id = findViewById(R.id.tv_id);
        lv = findViewById(R.id.listview);

        btn_send = findViewById(R.id.btn_send);
        edt_input = findViewById(R.id.edt_input);

        tv_id.setText(login_id);

        ChatAdatper adatper = new ChatAdatper(getApplicationContext(), R.layout.chatlist, data, login_id);
        lv.setAdapter(adatper);

        //전송버튼 누르면 FirebaseDatabase로 채팅데이터 전송하기
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //채팅데이터 생성ChatVO
                ChatVO vo = new ChatVO(R.drawable.img05, login_id, edt_input.getText().toString(), "11:57");
                myRef.push().setValue(vo); //데이터 베이스 주소 가지고오기
            }
        });

        //Firebase Database에서 데이터 불러오기
        //주소에 값이 변화할 때를 감지하는 listener!
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                //하위 경로에 데이터가 추가 되었을 때
                // 채팅을 새로 올렸을 때 불러오기
                ChatVO vo = snapshot.getValue(ChatVO.class);
                data.add(vo);
                adatper.notifyDataSetChanged();
                edt_input.setText("");

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                //값이 바뀌없을때

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //값이 삭제되었을때

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {
                //값이 이동했을때

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //에러가 발생해서 실패했을때

            }
        });
    }
}