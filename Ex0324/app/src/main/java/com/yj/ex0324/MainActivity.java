package com.yj.ex0324;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.transition.FadeProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //리스트뷰 안에 들어갈 데이터를 생성 => 추가, 삭제가 자유로운
    //ArrayList 생성
    ArrayList<String> list = new ArrayList<String>();
    ListView lv;
    Button btn_add;
    EditText edt_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listview);
        btn_add = findViewById(R.id.btn_add);
        edt_input = findViewById(R.id.edt_input);

        list.add("장승원");
        list.add("김명훈");
        list.add("박양주");
        list.add("여진호");
        list.add("김민관");

        //Adapter 생성
        //Adapter의 역할은 simpkelist.xml(항목 디자인, 템플릿)과 ArrayList(데이터)를 결합
        //Adapter에 차곡차곡 쌓아주는 일을 함ationContext(),R.layout.simplelist,list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.simplelist,list);
        //1. 현재페이지 정보(context)
        //2. simplelist.xml (항목 디자인, 템플릿) -> R.layout.으로 접근가능
        //3. 보여진 데이터
        lv.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //추가버튼 눌렀을때 listView에 데이터가 추가되게 해보자
                String str = edt_input.getText().toString();

                //데이터가 담겨있는 ArrayList에 추가
                list.add(str);

                //adapter 새로고침
                adapter.notifyDataSetChanged();
                edt_input.setText(" ");
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //그냥 지우기
//                list.remove(position);
//                adapter.notifyDataSetChanged();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("삭제하기");
                builder.setMessage("삭제하시겠습니까?");

                //버튼 뭐라고 쓸껀지 , 클릭 리스너 버튼을 클릭했을때 일어날 일
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancle", null);
                //굳이 일어날 일이 없기에
                builder.show();
            }
        });
    }
}