package com.bilal.karademir.notuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    FloatingActionButton fb;
    Context context = this;
    ArrayList<Notlar> notlarArrayList;
    NotlarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fb = findViewById(R.id.fb);
        rv = findViewById(R.id.rv);
        toolbar = findViewById(R.id.toolbar);
        notlarArrayList = new ArrayList<>();


        toolbar.setTitle("Not Uygulaması");
        setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));




        tumNotlar();


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotKayitActivity.class));
                finish();

            }
        });

    }

    public void tumNotlar(){

        String url = "https://mobildenemebilal.tk/notlar/tum_notlar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                double toplam = 0;

                notlarArrayList = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray notlar = jsonObject.getJSONArray("notlar");

                    for (int i = 0; i<notlar.length();i++){
                        JSONObject not = notlar.getJSONObject(i);
                        int not_id = not.getInt("not_id");
                        String ders_adi = not.getString("ders_adi");
                        int not1 = not.getInt("not1");
                        int not2 = not.getInt("not2");

                        toplam = toplam +(not1+not2)/2;

                        Notlar notlarim = new Notlar(not_id,ders_adi,not1,not2);

                        notlarArrayList.add(notlarim);

                    }

                    adapter = new NotlarAdapter(context,notlarArrayList);
                    rv.setAdapter(adapter);

                    toolbar.setSubtitle("Ortalama : "+(toplam/notlarArrayList.size()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(context).add(stringRequest);








    }
}
