package com.yj.ex0417_parking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter <ContactAdapter.ResVH>{
    RequestQueue requestQueue;  // 서버와 통신할 통로
    StringRequest deleteRequest;    // 내가 전송할 데이터

    private List<MarkerItem> markerItems;
    private Context context;

    public ContactAdapter(List<MarkerItem> markerItems) {
        this.markerItems = markerItems;
    }

    @NonNull
    @Override
    public ResVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
        context = parent.getContext();
        return new ResVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResVH holder, int position) {

        MarkerItem markerItem = markerItems.get(position);
        holder.location_txt.setText(markerItem.getMsp_location());
        holder.date_Txt.setText(markerItem.getMsp_date());
        holder.type_txt.setText(markerItem.getMsp_type());
        holder.num_txt.setText(markerItem.getMsp_num());

        boolean isExpandable = markerItems.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return markerItems.size();
    }


    public class ResVH extends RecyclerView.ViewHolder {

        TextView location_txt, date_Txt, type_txt, num_txt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public ResVH(@NonNull View itemView) {
            super(itemView);

            location_txt = itemView.findViewById(R.id.msp_location);
            date_Txt = itemView.findViewById(R.id.msp_date);
            type_txt = itemView.findViewById(R.id.msp_type);
            num_txt = itemView.findViewById(R.id. msp_num);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            requestQueue = Volley.newRequestQueue(context);

            linearLayout.setOnClickListener(new View.OnClickListener() {    // 컨텍스트 메뉴 생성
                @Override
                public void onClick(View v) {
                    MarkerItem markerItem = markerItems.get(getAdapterPosition());
                    markerItem.setExpandable(!markerItem.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}