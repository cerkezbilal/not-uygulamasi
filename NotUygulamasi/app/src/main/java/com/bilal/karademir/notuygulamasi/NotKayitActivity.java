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

public class NotKayitActivity extends AppCompatActivity {

    Button buttonKaydet;
    EditText editTextDers,editTextNot1,editTextNot2;
    Toolbar toolbar;
    Context context = this;
    Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);

        toolbar = findViewById(R.id.toolbar);
        vt = new Veritabani(context);

        buttonKaydet = findViewById(R.id.buttonKaydet);

        editTextDers = findViewById(R.id.editTextDers);

        editTextNot1 = findViewById(R.id.editTextNot1);

        editTextNot2 = findViewById(R.id.editTextNot2);

        toolbar.setTitle("Not Kayıt");
        setSupportActionBar(toolbar);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                if(TextUtils.isEmpty(ders_adi)){

                    Snackbar.make(v,"Ders Adı Giriniz",Snackbar.LENGTH_SHORT).show();
                    return;


                }
                if(TextUtils.isEmpty(not1)){

                    Snackbar.make(v,"1.Notunuzu Giriniz",Snackbar.LENGTH_SHORT).show();
                    return;


                }
                if(TextUtils.isEmpty(not2)){

                    Snackbar.make(v,"2.Notunuzu Giriniz",Snackbar.LENGTH_SHORT).show();
                    return;


                }



               new NotlarDao().notEkle(vt,ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));






                startActivity(new Intent(context, MainActivity.class));
                finish();

            }
        });

    }
}
