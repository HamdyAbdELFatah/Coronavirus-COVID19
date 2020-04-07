package com.coders.coronavirus.virus;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.coders.coronavirus.R;
public class Corona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);
        String[] arr = getResources().getStringArray(R.array.corona_questions);
        RecyclerView recyclerView=findViewById(R.id.corona_question_list);
        Adapter adapter=new Adapter(arr,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}
