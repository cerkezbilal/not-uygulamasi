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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    FloatingActionButton fb;
    Context context = this;
    ArrayList<Notlar> notlarArrayList;
    NotlarAdapter adapter;
    Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fb = findViewById(R.id.fb);
        rv = findViewById(R.id.rv);
        toolbar = findViewById(R.id.toolbar);
        notlarArrayList = new ArrayList<>();
        vt = new Veritabani(context);

        toolbar.setTitle("Not Uygulaması");
        setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


            notlarArrayList = new NotlarDao().tumNotlar(vt);

            double toplam = 0.0;
            for (Notlar n: notlarArrayList){
                toplam = toplam+(n.getNot1()+n.getNot2())/2;
            }
            toolbar.setSubtitle("Ortalama: "+(toplam/notlarArrayList.size()));




        adapter = new NotlarAdapter(context,notlarArrayList);
        rv.setAdapter(adapter);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotKayitActivity.class));
                finish();

            }
        });

    }
}
