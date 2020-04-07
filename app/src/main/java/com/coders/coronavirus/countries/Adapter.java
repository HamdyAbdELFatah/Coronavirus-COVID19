package com.coders.coronavirus.countries;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.coders.coronavirus.R;

import java.util.ArrayList;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    Adapter(Context context, ArrayList<DataCountries> arr) {
        this.context = context;
        this.arr = arr;
    }
    private Context context;
    private ArrayList<DataCountries> arr;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.country_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int i) {
        holder.countryName.setText(arr.get(i).country_name);
        holder.cases.setText(arr.get(i).cases);
        if(arr.get(i).flag==null)
            holder.flag.setImageResource(R.drawable.noflag);
        else
            SvgLoader.pluck()
                    .with((Activity) context)
                    .setPlaceHolder(R.drawable.noflag, R.drawable.noflag)
                    .load(Uri.parse(arr.get(i).flag), holder.flag);
        holder.itemOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,Country.class);
                DataCountries dataCountries =arr.get(i);
                Bundle bundle=new Bundle();
                bundle.putString("country_name",dataCountries.country_name);
                bundle.putString("cases",dataCountries.cases);
                bundle.putString("deaths",dataCountries.deaths);
                bundle.putString("total_recovered",dataCountries.total_recovered);
                bundle.putString("new_deaths",dataCountries.new_deaths);
                bundle.putString("new_cases",dataCountries.new_cases);
                bundle.putString("serious_critical",dataCountries.serious_critical);
                bundle.putString("active_cases",dataCountries.active_cases);
                bundle.putString("total_cases_per_1m_population",dataCountries.total_cases_per_1m_population);
                bundle.putString("flag",dataCountries.flag);
                intent.putExtras(bundle);
                sharedElementTransition(intent,holder.flag,holder.countryName);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arr.size();
    }
    void setfilter(ArrayList<DataCountries> array){
        arr=new ArrayList<>();
        arr.addAll(array);
        notifyDataSetChanged();
    }
    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView flag;
        TextView countryName;
        TextView cases;
        CardView itemOnClick;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            flag=itemView.findViewById(R.id.flag);
            countryName=itemView.findViewById(R.id.countryName);
            cases=itemView.findViewById(R.id.cases);
            itemOnClick=itemView.findViewById(R.id.countryItemCard);

            if(Locale.getDefault().getLanguage().equals("ar"))
                countryName.setGravity(Gravity.RIGHT|Gravity.CENTER);
            else
                countryName.setGravity(Gravity.LEFT|Gravity.CENTER);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sharedElementTransition(Intent intent,ImageView imageView,TextView textView){
        ActivityOptionsCompat activityOptions= null;
        Activity activity = (Activity) context;
        Pair<View, String> p1= Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
        Pair<View, String> p2=Pair.create((View)textView, ViewCompat.getTransitionName(textView));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,p1,p2);
        }

        assert activityOptions != null;
        //context.startActivity(intent,activityOptions.toBundle());
        context.startActivity(intent);
    }
   void onDestroy() {
    try{
            SvgLoader.pluck().close();
    }
    catch (Exception ignored){

    }
    }
}
