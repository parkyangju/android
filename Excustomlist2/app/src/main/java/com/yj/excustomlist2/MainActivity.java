package com.yj.excustomlist2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.analyzer.Direct;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView title, address;
    Button btn, btn_go;
    ListView lv;

    final int add = 1;

    DBManager manager;

    ArrayList<ContactVO> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DBManager(getApplicationContext()); //데이터베이스 파일,테이블,커넥션 생성

        btn = findViewById(R.id.btn_add);
        btn_go = findViewById(R.id.btn_go);

        title = findViewById(R.id.tv_naver);
        address = findViewById(R.id.tv_address);

        lv = findViewById(R.id.listview);

        contacts.add(new ContactVO("네이버", "https://www.naver.com"));
        contacts.add(new ContactVO("다음", "https://www.daum.net"));
        contacts.add(new ContactVO("YouTube", "https://www.youtube.com"));
        contacts.add(new ContactVO("스마트인재개발원", "https://www.smhrd.or.kr"));

        ContactAdapter adapter = new ContactAdapter(getApplicationContext(), R.layout.listview, contacts);
        lv.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, SubActivity.class), add);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //db에서는 position 값으로 지워버리게 되면 안된다
                //결론 title로 지워야 한다 index가 없기때문에

                //지금 클릭할 항목을 알아내야 한다.
                //1. arraylist 의 position번째 값을 꺼내는 방법
                //2. 클릭이 일어난 항목의 textview 에서 gettext 하는 방법

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("삭제하기");
                builder.setTitle("삭제하시겠습니까?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv_address = view.findViewById(R.id.tv_naver);
                        manager.delete(tv_address.getText().toString());
                        //db의 정보를 지우기
                        contacts.remove(position);
                        adapter.notifyDataSetChanged();
                        //눈에보이는 listview 지우기

                    }
                });

                builder.setNegativeButton("cancel",null);
                builder.show();
            }
        });

       Cursor cursor = manager.getAllData();
       //Cursor랑 JDBC 에서 ResultSet과 같은역할 화살표와 같은 역할
        //데이터 (행)을 가리키고있는 화살표

        while(cursor.moveToNext()){ //커서를 아래칸으로 옮기는데 데이터가 있으면 True, 없으면 False
            //해당 행의 데이터 가지고 와서 ArrayList 축척하기
            String title =cursor.getString(0);
            String address =cursor.getString(1);

            contacts.add(new ContactVO(title,address));

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == add) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra("title");
                String address = data.getStringExtra("address");

                this.contacts.add(new ContactVO(title, address));

                //manager 클래스의 insert  메소드 호출
                manager.insert(title,address);
            }
        }
        //adapter.notifyDataSetChanged(); 쓰지 않아도 자동으로 업데이트 됩니다.
    }
}


