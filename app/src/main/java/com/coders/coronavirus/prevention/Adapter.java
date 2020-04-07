package com.coders.coronavirus.prevention;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.coders.coronavirus.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    String arr[];
    int img[];
    Context context;
    public Adapter(String[] arr,int[] img, Context context) {
        this.arr = arr;
        this.img = img;
        this.context = context;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.prevention_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int i) {
        holder.preventionName.setText(arr[i]);
        holder.preventionImage.setImageResource(img[i]);
        holder.preventionCardIdemOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x="";
                Intent intent=new Intent(context,AnswerPrevention.class);
                switch(i){
                    case 0:x=context.getResources().getString(R.string.wash_answer);break;
                    case 1:x=context.getResources().getString(R.string.home_answer);break;
                    case 2:x=context.getResources().getString(R.string.mask_answer);break;
                    case 3:x=context.getResources().getString(R.string.crowding_answer);break;
                    case 4:x=context.getResources().getString(R.string.smoking_answer);break;
                    case 5:x=context.getResources().getString(R.string.travel_answer);break;
                }
                context.startActivity(intent.putExtra("answer",x));
            }
        });
    }
    @Override
    public int getItemCount() {
        return arr.length;
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        CardView preventionCardIdemOnClick;
        ImageView preventionImage;
        TextView preventionName;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            preventionImage=itemView.findViewById(R.id.PreventionImage);
            preventionName=itemView.findViewById(R.id.preventionName);
            preventionCardIdemOnClick=itemView.findViewById(R.id.preventionCardIdemOnClick);
        }
    }

}
