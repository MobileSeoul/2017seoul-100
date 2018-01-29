package com.example.chip.workout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ListparkView extends LinearLayout {

    TextView textView;
    TextView textView2;
    ImageView imageView;

    public ListparkView(Context context) {
        super(context);
        init(context);
    }

    public ListparkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_park,this,true);

        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        imageView=(ImageView)findViewById(R.id.imageView);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setStimulus(String stimulus){
        textView2.setText(stimulus);
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

}
