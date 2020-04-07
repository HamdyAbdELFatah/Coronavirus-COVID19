package com.coders.coronavirus.virus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.coders.coronavirus.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    String arr[];
    Context context;
    public Adapter(String[] arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.corona_question_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int i) {
        holder.myQuestion.setText(arr[i]);
        holder.coronaQuestionItemOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,AnswerCorona.class).putExtra("answer",i));
            }
        });
    }
    @Override
    public int getItemCount() {
        return arr.length;
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView myQuestion;
        CardView coronaQuestionItemOnClick;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            myQuestion=itemView.findViewById(R.id.myQuestion);
            coronaQuestionItemOnClick=itemView.findViewById(R.id.coronaQuestionItemOnClick);
        }
    }

}
