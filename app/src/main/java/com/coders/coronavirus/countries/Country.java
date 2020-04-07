package com.coders.coronavirus.countries;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.coders.coronavirus.R;

import java.util.ArrayList;
import java.util.Locale;

public class Country extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] dataCountryValue;
    String[] dataCountryName ;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        TextView countryName=findViewById(R.id.countryChoosed);
        ImageView countryFlagChoosed=findViewById(R.id.countryFlagChoosed);
        countryFlagChoosed.setImageResource(R.drawable.noflag);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        getData();
        String country_name= bundle.getString("country_name");
        String flag=bundle.getString("flag");
        countryName.setText(country_name);
        if(Locale.getDefault().getLanguage().equals("ar"))
            countryName.setGravity(Gravity.RIGHT|Gravity.CENTER);
        else
            countryName.setGravity(Gravity.LEFT|Gravity.CENTER);
        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.drawable.noflag, R.drawable.noflag)
                .load(Uri.parse(flag),countryFlagChoosed );
        recyclerView=findViewById(R.id.dataCountryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DataCountryAdapter());

    }
    void getData(){
        dataCountryValue=new String[8];
        dataCountryName = getResources().getStringArray(R.array.data_country);
        dataCountryValue[0]=bundle.getString("cases");
        dataCountryValue[1]="+"+bundle.getString("new_cases");
        dataCountryValue[2]=bundle.getString("deaths");
        dataCountryValue[3]=bundle.getString("total_recovered");
        dataCountryValue[4]="+"+bundle.getString("new_deaths");
        dataCountryValue[5]=bundle.getString("serious_critical");
        dataCountryValue[6]=bundle.getString("active_cases");
        dataCountryValue[7]=bundle.getString("total_cases_per_1m_population");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
    private class DataCountryAdapter extends RecyclerView.Adapter<DataCountryAdapter.MyHolder>{

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(Country.this).inflate(R.layout.data_country_item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int i) {
            holder.name.setText(dataCountryName[i]);
            holder.value.setText(dataCountryValue[i]);
            if(dataCountryName[i].equals("New Cases")||dataCountryName[i].contains("حالات جديدة"))
                holder.value.setTextColor(getResources().getColor(R.color.colorAccent));
            else if(dataCountryName[i].equals("New Deaths")||dataCountryName[i].contains("وفيات جديدة"))
                holder.value.setTextColor(Color.RED);
            else
                holder.value.setTextColor(getResources().getColor(R.color.textColor));

        }
        @Override
        public int getItemCount() {
            return dataCountryValue.length;
        }

         class MyHolder extends RecyclerView.ViewHolder{
            TextView name;
            TextView value;
            MyHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.dataCountryName);
                value = itemView.findViewById(R.id.dataCountryValue);
            }
        }
    }

}
