package com.yj.customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//1. BaseAdapter를 상속받는다.
//2. BaseAdapter는 추상 클래스(추상메소드 =몸체가없는 메소드)가 하나이상 포함되어있는 클래스
//이기때문에 내장되어있는 추상 메소드를 반드시 Overrding 해주어야 함
//3. Adapter(템플릿,사진 번호등등 그걸 묶어주는 것) 작업에 필요한 도구를 전달(생성자)받아야함
//4. 전달받은 도구 (Context, layout, data)를 필드변수에 저장!
public class ContactAdapter extends BaseAdapter {
    private Context c;
    private int layout;
    private ArrayList<ContactVO> data;
    private LayoutInflater inflater;

    ContactAdapter(Context c, int layout, ArrayList<ContactVO> data){ //생성자로 전달받음
        this.c = c;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { //listView에 보여질 항목의 개수를 결정
        return data.size();
    }

    @Override
    public Object getItem(int position) { //position 번째의 데이터를 반환
        return data.get(position);
    }

    @Override
    public long getItemId(int position) { //position 번째의 id를 반환
        return position;
    }

    //Adapter의 핵심 모든일은 여기서 처리됩니다!
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){ //이전에 만들어진 뷰가 없다면
            convertView = inflater.inflate(layout,parent, false);
        }

        ImageView img = convertView.findViewById(R.id.imageView);
        img.setImageResource(data.get(position).getImg());

        TextView tv_name = convertView.findViewById(R.id.tv_name);
        tv_name.setText(data.get(position).getName());

        TextView tv_phone = convertView.findViewById(R.id.tv_phone);
        tv_phone.setText(data.get(position).getPhone_num());

        return convertView;
    }
}
