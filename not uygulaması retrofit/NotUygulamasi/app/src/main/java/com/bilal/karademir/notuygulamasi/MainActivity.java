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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    FloatingActionButton fb;
    Context context = this;
    ArrayList<Notlar> notlarArrayList;
    NotlarAdapter adapter;
    NotlarInterface notlarInterface;


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

        notlarInterface = ApiUtils.getNotlarDaoInterfeace();
        notlarInterface.tumNotlar().enqueue(new Callback<NotlarCevap>() {
            @Override
            public void onResponse(Call<NotlarCevap> call, Response<NotlarCevap> response) {

                List<Notlar> liste = response.body().getNotlar();
                double toplam = 0;
                for(Notlar n : liste){

                    toplam = toplam +(Integer.parseInt(n.getNot1())+Integer.parseInt(n.getNot2()))/2;

                }

                adapter = new NotlarAdapter(context,liste);
                rv.setAdapter(adapter);
                toolbar.setSubtitle("Ortalama: "+toplam/liste.size());
            }

            @Override
            public void onFailure(Call<NotlarCevap> call, Throwable t) {

            }
        });










        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotKayitActivity.class));
                finish();

            }
        });

    }


}
