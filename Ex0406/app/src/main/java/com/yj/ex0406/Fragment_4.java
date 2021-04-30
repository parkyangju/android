package com.yj.ex0406;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment_4 extends Fragment {
    //데이터 (캐시) 저장하기 -> sharedPreference -> 간단한 데이터 저장할 때
    //-> 특히 로그인 정보, 환경설정, (중요!!!)App첫실행인지 감지할 때!
    EditText edt_address;
    Button btn_setting;
    SharedPreferences spf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        //app 껏다켜도 데이터 남겨질 수 있도록, MODE_PRIVATE : mySPF라는 이름의 프리퍼런스가 없으면 새로생성
        //혹 있으면 있는거 가져와라!!
        spf = getActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE);

        //값 반영할 수있는 객체
        SharedPreferences.Editor editor = spf.edit();

        edt_address = view.findViewById(R.id.edt_address); //adapter 에서 convert 해주는 것
        btn_setting = view.findViewById(R.id.btn_setting);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast 띄울때 getApplicationContext 가지고 오는데 여기서는 지원하지 않는다.
                //그래서 getActivity(). 를 붙여주게되면 현재 fragment가 포함된 Activity정보를 가지고 올 수있음

                //현재 https://로 시작하지 않는다면 https:// 붙여주기
                String url = edt_address.getText().toString();
                if(!url.startsWith("https://")){ //https로 시작하지 않는다면 ~
                    url = "https://"+url;
                }

                //spf 에 값저장 : 해당값은 app을 꺾다 켜도 유지됨!
                Toast.makeText(getActivity().getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                editor.putString("address",url);
                editor.commit();
            }
        });

        return view;
    }
}