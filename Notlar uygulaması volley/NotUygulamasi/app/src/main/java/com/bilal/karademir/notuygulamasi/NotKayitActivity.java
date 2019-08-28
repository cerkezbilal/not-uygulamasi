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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NotKayitActivity extends AppCompatActivity {

    Button buttonKaydet;
    EditText editTextDers,editTextNot1,editTextNot2;
    Toolbar toolbar;
    Context context = this;


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

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://mobildenemebilal.tk/notlar/insert_not.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("cevap",response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("ders_adi",editTextDers.getText().toString());
                        params.put("not1",editTextNot1.getText().toString());
                        params.put("not2",editTextNot2.getText().toString());

                        return params;
                    }
                };

                Volley.newRequestQueue(context).add(stringRequest);

               startActivity(new Intent(context,MainActivity.class));
               finish();


            }
        });

    }
}
