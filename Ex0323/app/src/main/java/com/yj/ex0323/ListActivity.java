package com.yj.ex0323;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.listView);

        //리스트뷰의 항목이 선택된걸 감지해보자!
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //매개변수 설명 (verson. 따라 다를수있음)
                //1. parent -> ListView 자체
                //2. view -> 지금 내가 선택한 그 뷰 (이벤트가 발생한 해당 뷰)
                //3. posotion -> 이벤트가 발생한 뷰의 인덱스(배열)
                //4. id -> 이벤트가 발생한 뷰의 고유 id값 = posotion

                String choice = "";
                if(position == 0){
                    choice = "#CD5C5C";
                }else if(position == 1){
                    choice = "#006400";
                }else if(position == 2){
                    choice = "#00BFFF";
                }

                Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();

                //어떤 색을 선택했는지 저장할 Intent 값을 담아주는 것만 시행
                Intent intent = new Intent();
                intent.putExtra("color", choice); //color 라는 테그값으로 선택한 색상값
                setResult(RESULT_OK, intent); //저장이 올바르게 되어있다고 나타내는 함수(다시올때)
                finish();
            }
        });
    }
}