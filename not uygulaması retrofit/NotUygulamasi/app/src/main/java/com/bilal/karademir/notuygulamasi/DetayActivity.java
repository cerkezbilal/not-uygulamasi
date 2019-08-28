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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetayActivity extends AppCompatActivity {

    EditText editTextDers,editTextNot1,editTextNot2;
    Toolbar toolbar;
    Notlar not;
    Context context = this;
    NotlarInterface notlarInterface;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);

        toolbar = findViewById(R.id.toolbar);
        notlarInterface = ApiUtils.getNotlarDaoInterfeace();

        not = (Notlar) getIntent().getSerializableExtra("nesne");

        toolbar.setTitle("Not Detay");
        setSupportActionBar(toolbar);

        editTextDers.setText(not.getDersAdi());
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

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                notlarInterface.notDuzenle(Integer.parseInt(not.getNotId()),ders_adi,Integer.parseInt(not1),Integer.parseInt(not2)).enqueue(new Callback<CRUDCevap>() {
                    @Override
                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                    }

                    @Override
                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    }
                });






                startActivity(new Intent(context, MainActivity.class));
                finish();

                return  true;
            case R.id.action_sil :




                Snackbar.make(toolbar,"Notu silmek istiyor musun?",Snackbar.LENGTH_SHORT)
                        .setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                notlarInterface.notSil(Integer.parseInt(not.getNotId())).enqueue(new Callback<CRUDCevap>() {
                                    @Override
                                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                                    }
                                });







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
