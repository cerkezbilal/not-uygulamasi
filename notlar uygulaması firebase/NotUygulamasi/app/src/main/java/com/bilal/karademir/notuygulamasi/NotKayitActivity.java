package com.bilal.karademir.notuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotKayitActivity extends AppCompatActivity {

    Button buttonKaydet;
    EditText editTextDers,editTextNot1,editTextNot2;
    Toolbar toolbar;
    Context context = this;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);

        toolbar = findViewById(R.id.toolbar);


        buttonKaydet = findViewById(R.id.buttonKaydet);

        editTextDers = findViewById(R.id.editTextDers);

        editTextNot1 = findViewById(R.id.editTextNot1);

        editTextNot2 = findViewById(R.id.editTextNot2);



        toolbar.setTitle("Not Kayıt");
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notlar");

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                //Kayıt işlemi

                Notlar not = new Notlar("",ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));
                myRef.push().setValue(not);




               startActivity(new Intent(context,MainActivity.class));
               finish();


            }
        });

    }
}
