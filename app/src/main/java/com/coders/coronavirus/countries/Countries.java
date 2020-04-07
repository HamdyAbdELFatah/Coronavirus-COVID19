package com.coders.coronavirus.countries;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.coders.coronavirus.MainActivity;
import com.coders.coronavirus.R;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Delayed;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Countries extends AppCompatActivity {
    RecyclerView recyclerView;
    HashMap<String,String> map;
    ArrayList<DataCountries> arr;
    Adapter adapter;
    ProgressBar progressBar;
    TextView notfound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        recyclerView=findViewById(R.id.countries_list);
        notfound=findViewById(R.id.notfound);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        notfound.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arr=new ArrayList<DataCountries>();
        adapter=new Adapter(Countries.this,arr);
        recyclerView.setAdapter(adapter);
        countriesFlags();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.app_bar_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<DataCountries> temp =new ArrayList<>();
                for(DataCountries t : arr){
                    if(t.country_name.toLowerCase().contains(newText.toLowerCase()))
                        temp.add(t);
                }
                if(temp.size()==0)
                    notfound.setVisibility(View.VISIBLE);
                else
                    notfound.setVisibility(View.INVISIBLE);
                adapter.setfilter(temp);
                return true;
            }
        });
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adapter!=null)
            adapter.onDestroy();
    }
    void countriesFlags(){
        OkHttpClient client = new OkHttpClient();
        map=new HashMap<String,String>();
        Request request = new Request.Builder()
                .url("https://restcountries.eu/rest/v2/all")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Countries.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(myResponse);
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject country = jsonArray.getJSONObject(i);
                                    map.put(country.getString("name"),country.getString("flag"));
                                }
                            } catch (Exception e) {
                                Log.d("Error >>>> ", e.getMessage());
                            }
                            countriesData();
                        }
                    });
                }
            }
        });
    }
    void countriesData(){
        OkHttpClient client = new OkHttpClient();
        arr=new ArrayList<DataCountries>();
        Request request = new Request.Builder()
                .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php")
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "Your-key")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Countries.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(myResponse);
                                JSONArray countries = jsonObject.getJSONArray("countries_stat");
                                for (int i = 0; i < countries.length(); i++) {
                                    JSONObject country = countries.getJSONObject(i);
                                    String country_name= country.getString("country_name");
                                    String cases= country.getString("cases");
                                    String deaths=country.getString("deaths");
                                    String total_recovered= country.getString("total_recovered");
                                    String new_deaths= country.getString("new_deaths");
                                    String new_cases=country.getString("new_cases");
                                    String serious_critical=country.getString("serious_critical");
                                    String active_cases=country.getString("active_cases");
                                    String total_cases_per_1m_population=country.getString("total_cases_per_1m_population");
                                    String flag;
                                    flag=map.get(country_name);
                                    if(country_name.contains("Iran"))
                                        flag = map.get("Iran (Islamic Republic of)");
                                    else if(country_name.contains("USA"))
                                        flag = map.get("United States of America");
                                    else if(country_name.contains("UK"))
                                        flag = map.get("United Kingdom of Great Britain and Northern Ireland");
                                    else if(country_name.contains("S. Korea"))
                                        flag = map.get("Korea (Republic of)");
                                    else if(country_name.contains("Czechia"))
                                        flag = map.get("Czech Republic");
                                    else if(country_name.contains("Russia"))
                                        flag = map.get("Russian Federation");
                                    else if(country_name.contains("Palestine"))
                                        flag = map.get("Palestine, State of");
                                    else if(country_name.contains("Venezuela"))
                                        flag = map.get("Venezuela (Bolivarian Republic of)");
                                    else if(country_name.contains("Bolivia"))
                                        flag = map.get("Bolivia (Plurinational State of)");
                                    else if(country_name.contains("Tanzania"))
                                        flag = map.get("Tanzania, United Republic of");
                                    else if(country_name.contains("Saint Martin"))
                                        flag = map.get("Saint Martin (French part)");
                                    else if(country_name.contains("UAE"))
                                        flag = map.get("United Arab Emirates");
                                    else if(country_name.contains("CAR"))
                                        flag = map.get("Central African Republic");
                                    else if(country_name.contains("DRC"))
                                        flag = map.get("Congo (Democratic Republic of the)");
                                    else if(country_name.contains("Vietnam"))
                                        flag = map.get("Viet Nam");
                                    else if(country_name.contains("Sint Maarten"))
                                        flag = map.get("Sint Maarten (Dutch part)");
                                    else if(country_name.contains("Moldova"))
                                        flag = map.get("Moldova (Republic of)");
                                    else if(country_name.contains("Macedonia"))
                                        flag = map.get("Macedonia (the former Yugoslav Republic of)");
                                    else if(country_name.contains("St. Barth"))
                                        flag = map.get("Saint Barthélemy");
                                    else if(country_name.contains("Ivory Coast"))
                                        flag = map.get("Côte d'Ivoire");
                                    else if(country_name.contains("U.S. Virgin Islands"))
                                        flag = map.get("Virgin Islands (U.S.)");
                                    else if(country_name.contains("Eswatini"))
                                        flag = map.get("Swaziland");
                                    else if(country_name.contains("St. Vincent Grenadines"))
                                        flag = map.get("Saint Vincent and the Grenadines");
                                    else if(country_name.contains("Syria"))
                                        flag = map.get("Syrian Arab Republic");
                                    else if(country_name.contains("Faeroe Islands"))
                                        flag = map.get("Faroe Islands");
                                    else if(country_name.contains("Brunei"))
                                        flag = map.get("Brunei Darussalam");
                                    else if(country_name.contains("Vatican City"))
                                        flag = map.get("Holy See");
                                    if(flag==null)
                                        flag="notFound";
                                    arr.add(new DataCountries(country_name,cases,deaths,total_recovered,new_deaths
                                    ,new_cases,serious_critical,active_cases,total_cases_per_1m_population,flag));
                                    adapter=new Adapter(Countries.this,arr);
                                    recyclerView.setAdapter(adapter);
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            } catch (Exception e) {
                                Log.d("Error >>>> ", e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }
}
