package com.coders.coronavirus.prevention;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coders.coronavirus.R;

public class Prevention extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
        int [] img={R.drawable.wash,R.drawable.home,R.drawable.mask,R.drawable.crowding,R.drawable.smoking,R.drawable.plane};
        String[] arr = getResources().getStringArray(R.array.prevention_category);
        RecyclerView recyclerView=findViewById(R.id.preventionList);
        Adapter adapter=new Adapter(arr,img,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

}
