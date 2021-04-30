package com.yj.myapplication3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {
    ImageView itemImage;
    TextView programTitle;
    TextView programDescription;
    ProgramViewHolder(View view)
    {
        itemImage = view.findViewById(R.id.img);
        programTitle = view.findViewById(R.id.textView1);
        programDescription = view.findViewById(R.id.textView2);

    }
}
