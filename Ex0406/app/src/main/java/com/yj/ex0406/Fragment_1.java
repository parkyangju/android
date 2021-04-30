package com.yj.ex0406;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Fragment_1 extends Fragment { //Fragment를 상속받는다.
    //1) web view 쓰는법
    //2)저장된 url 정보 가져오는 법 -> spf
    WebView webview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //여기서 oncreate 다른데서도 쓰이는 것
                             Bundle savedInstanceState) {
        //fragment.xml 파일을 view로 inflate(id->view)하여 변환

        View view = inflater.inflate(R.layout.fragment_1, container, false);
        webview=view.findViewById(R.id.webview);

        //spf에서 값꺼내기 한줄로 간단하게 써보자
        String url = getActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE).getString("address",null);





        //1-1) webView 환경설정해주기(자바스크립트 지원)
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        //1-2)webClient 지정하기
        webview.setWebViewClient(new WebViewClient());

        //1-3) 띄워줄 url 지정하기
        webview.loadUrl(url);

        //1-4) 인터넷 권한 부여해주기

        return view;
    }
}