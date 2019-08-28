package com.bilal.karademir.notuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    FirebaseDatabase database;
    DatabaseReference myRef;




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

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        notlarArrayList = new ArrayList<>();
        adapter = new NotlarAdapter(context,notlarArrayList);
        rv.setAdapter(adapter);

        tumNotlar();











        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotKayitActivity.class));
                finish();

            }
        });

    }
            //not görüntüleme
    public void tumNotlar(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                notlarArrayList.clear();
                double toplam = 0;

                for (DataSnapshot d : dataSnapshot.getChildren()){

                    Notlar not = d.getValue(Notlar.class);
                    not.setNot_id(d.getKey());
                    notlarArrayList.add(not);
                    toplam = toplam + (not.getNot1()+not.getNot2())/2;


                }

                adapter.notifyDataSetChanged();
                toolbar.setSubtitle("Ortalama : "+toplam/notlarArrayList.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
