package com.bilal.karademir.notuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DetayActivity extends AppCompatActivity {

    EditText editTextDers,editTextNot1,editTextNot2;
    Toolbar toolbar;
    Notlar not;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);

        toolbar = findViewById(R.id.toolbar);

        not = (Notlar) getIntent().getSerializableExtra("nesne");

        toolbar.setTitle("Not Detay");
        setSupportActionBar(toolbar);

        editTextDers.setText(not.getDers_adi());
        editTextNot1.setText(String.valueOf(not.getNot1()));
        editTextNot2.setText(String.valueOf(not.getNot2()));




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_duzenle :


                String url = "https://mobildenemebilal.tk/notlar/update_not.php";

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
                        params.put("not_id",String.valueOf(not.getNot_id()));
                        params.put("ders_adi",editTextDers.getText().toString());
                        params.put("not1",editTextNot1.getText().toString());
                        params.put("not2",editTextNot2.getText().toString());

                        return params;
                    }
                };

                Volley.newRequestQueue(context).add(stringRequest);





                startActivity(new Intent(context, MainActivity.class));
                finish();

                return  true;
            case R.id.action_sil :




                Snackbar.make(toolbar,"Notu silmek istiyor musun?",Snackbar.LENGTH_SHORT)
                        .setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                String url = "https://mobildenemebilal.tk/notlar/delete_not.php";

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
                                        params.put("not_id",String.valueOf(not.getNot_id()));


                                        return params;
                                    }
                                };

                                Volley.newRequestQueue(context).add(stringRequest);




                                startActivity(new Intent(context, MainActivity.class));
                                finish();
                            }
                        }).show();

                return true;

            default:
                return false;
        }

    }
}
