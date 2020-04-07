package com.coders.coronavirus;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coders.coronavirus.countries.Countries;
import com.coders.coronavirus.prevention.Prevention;
import com.coders.coronavirus.symptoms.Symptoms;
import com.coders.coronavirus.virus.Corona;

public class MainActivity extends AppCompatActivity {
    ImageView preventiomImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preventiomImage=findViewById(R.id.preventionImage);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sharedElementTransition(Intent intent, ImageView imageView){
        ActivityOptionsCompat activityOptions= null;
        Pair<View, String> p1= Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1);
        }

        assert activityOptions != null;
        startActivity(intent,activityOptions.toBundle());
    }
    public void coronaOnClick(View view) {
        Intent i =new Intent(this, Corona.class);
        startActivity(i);
    }

    public void countriesOnClick(View view) {
        Intent i =new Intent(this, Countries.class);
        startActivity(i);
    }

    public void preventionOnClick(View view) {
        Intent i =new Intent(this, Prevention.class);
        //sharedElementTransition(i,preventiomImage);
        startActivity(i);

    }

    public void symptomsOnClick(View view) {
        Intent i =new Intent(this, Symptoms.class);
        startActivity(i);
    }
}
