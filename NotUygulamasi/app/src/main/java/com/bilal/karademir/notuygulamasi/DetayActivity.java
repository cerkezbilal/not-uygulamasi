package com.bilal.karademir.notuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class DetayActivity extends AppCompatActivity {

    EditText editTextDers,editTextNot1,editTextNot2;
    Toolbar toolbar;
    Notlar not;
    Context context = this;
    Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);
        vt = new Veritabani(context);
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

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                if(TextUtils.isEmpty(ders_adi)){

                    Snackbar.make(toolbar,"Ders Adı Giriniz",Snackbar.LENGTH_SHORT).show();
                    return false;


                }
                if(TextUtils.isEmpty(not1)){

                    Snackbar.make(toolbar,"1.Notunuzu Giriniz",Snackbar.LENGTH_SHORT).show();
                    return false;


                }
                if(TextUtils.isEmpty(not2)){

                    Snackbar.make(toolbar,"2.Notunuzu Giriniz",Snackbar.LENGTH_SHORT).show();
                    return false;


                }



                new NotlarDao().notDuzenle(vt,not.getNot_id(),ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));

                startActivity(new Intent(context, MainActivity.class));
                finish();

                return  true;
            case R.id.action_sil :




                Snackbar.make(toolbar,"Notu silmek istiyor musun?",Snackbar.LENGTH_SHORT)
                        .setAction("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {



                                new NotlarDao().notSil(vt,not.getNot_id());
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
