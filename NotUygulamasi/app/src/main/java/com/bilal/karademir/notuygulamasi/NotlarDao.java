package com.bilal.karademir.notuygulamasi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotlarDao {

    public ArrayList<Notlar> tumNotlar(Veritabani vt){

        ArrayList<Notlar> notlarArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM notlar",null);

        while (c.moveToNext()){

            Notlar n = new Notlar(c.getInt(c.getColumnIndex("not_id"))
                    ,c.getString(c.getColumnIndex("ders_adi"))
                    ,c.getInt(c.getColumnIndex("not1"))
                    ,c.getInt(c.getColumnIndex("not2")));


            notlarArrayList.add(n);
        }

        return notlarArrayList;
    }

    public void notSil(Veritabani vt, int not_id){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("notlar","not_id=?",new String[]{String.valueOf(not_id)});
        db.close();
    }

    public void notEkle(Veritabani vt, String ders_adi, int not1,int not2){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ders_adi",ders_adi);
        contentValues.put("not1",not1);
        contentValues.put("not2",not2);

        db.insertOrThrow("notlar",null,contentValues);
        db.close();
    }
    public void notDuzenle(Veritabani vt,int not_id, String ders_adi, int not1,int not2){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ders_adi",ders_adi);
        contentValues.put("not1",not1);
        contentValues.put("not2",not2);

        db.update("notlar",contentValues,"not_id=?",new String[]{String.valueOf(not_id)});
        db.close();
    }



}
