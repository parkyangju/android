package com.yj.ex0406;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    // 1단계) 어떤메뉴를 선택했는지 판별해보자
    BottomNavigationView nv;
    //2 단계) 선택한 메뉴에 따라서 FrameLayout 에 들어갈 Fragment를 갈아끼워줍니다
    //2-1)각각 프래그먼트 생성

    Fragment_1 fg1;
    Fragment_2 fg2;
    Fragment_3 fg3;
    Fragment_4 fg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fg1 = new Fragment_1();
        fg2 = new Fragment_2();
        fg3 = new Fragment_3();
        fg4 = new Fragment_4();

        //2-2) 최초실행될 프래그먼트 고르기
        //2) 갈아끼울 fragment객체

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fg1).commit();

        nv = findViewById(R.id.bottomNavigationView);

        //3)navigation 에서 선택한 메뉴에 따라서 fragment 바꿔끼우기
        nv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) { //내가누른 그 메뉴
                switch (item.getItemId()){
                    case R.id.tab1:
                     //   Toast.makeText(getApplicationContext(),"web메뉴 선택!", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fg1).commit();
                        break;
                    case R.id.tab2:
                    //    Toast.makeText(getApplicationContext(),"stopwatch메뉴 선택!", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fg2).commit();
                        break;
                    case R.id.tab3:
                    //    Toast.makeText(getApplicationContext(),"doduge메뉴 선택!", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fg3).commit();
                        break;
                    case R.id.tab4:
                    //    Toast.makeText(getApplicationContext(),"setting메뉴 선택!", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fg4).commit();
                        break;
                }
                //return false의 의미 -> 아직 현재이벤트가 끝나지 않았다 -> true 전환 이 이벤트는 끝났다!
                return true;
            }
        });
    }
}