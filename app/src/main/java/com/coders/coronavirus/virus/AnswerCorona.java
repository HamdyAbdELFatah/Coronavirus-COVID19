package com.coders.coronavirus.virus;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.coders.coronavirus.R;

public class AnswerCorona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_corona);
        String[] arr = getResources().getStringArray(R.array.corona_answer);

        Intent i=getIntent();
        TextView coronaAnswer=findViewById(R.id.coronaAnswer);
        coronaAnswer.setText(arr[i.getIntExtra("answer",0)]);

    }
}
