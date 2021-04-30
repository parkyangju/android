package com.yj.excustomlist2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter{
    private Context c;
    private int layout;
    private ArrayList<ContactVO> data;
    private LayoutInflater inflater;

    public ContactAdapter(Context c, int layout, ArrayList<ContactVO> data){
        this.c = c;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){ //get view 처리할때 else처리는 하지않아도됨
            convertView = inflater.inflate(layout, parent, false);
            //최초 convertView가 inflate될때 (첫 항목이 만들어질때)
            holder = new ViewHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_naver);
            holder.tv_address= convertView.findViewById(R.id.tv_address);
            holder.btn_go = convertView.findViewById(R.id.btn_go);
            convertView.setTag(holder); //완성된 holder를 convertView에 달아줍니다~
        }else{ //최초 한번이 아닐때
            holder = (ViewHolder) convertView.getTag();
        }

        //getview는 항목 하나하나가 만들어질때마다 호출이 되는데
        //항목을 만들때마다 findviewById를 하면 굉장히 부하가 걸림
        //ViewHolder 패턴을 이용하여 효율적으로 View들을 관리해보자
        //viewHolder란? view를 저장해둔 객체 (VO)랑 비슷


        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_address.setText(data.get(position).getAddress());
        holder.btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 눌었을때 address로 바로가기
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(position).getAddress()));
                // ActivityClass가 아니기 때문에 (AppCompatActivity 클래스를 상속하지 않았기 때문에
                //바로 startActivity 메소드를 사용할 수 없습니다.
                //생성자를 통해 전달받은 Context의 도움을 받아 StartActivity를 실행

                // startActivity를 수행 하면 task에 process가 쌓이는데
                // 이곳은 Activity가 아니기 때문에 task에 접근할수가 없음
                // 그래서! intent에 옵션 (Flag)을 설정하여 새로운 task 생성!
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        });

        return convertView;
    }

    //ViewHolder 설계
    public class ViewHolder{
        TextView tv_title;
        TextView tv_address;
        Button btn_go;
    }



}
