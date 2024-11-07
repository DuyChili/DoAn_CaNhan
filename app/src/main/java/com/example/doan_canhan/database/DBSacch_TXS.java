package com.example.doan_canhan.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.doan_canhan.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class DBSacch_TXS extends SQLiteOpenHelper {
    public DBSacch_TXS(@NonNull Context context){
        super(context,"dbSachabc",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbSachTXS(image text,masach text,tensach text,soluong text,noidung text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void XoaSach(Sach s){
        String sql ="Delete from tbSachTXS where masach=?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{s.getMasach()});
    }
    public void ThemSach(Sach s){
        String sql = "Insert into tbSachTXS values(?,?,?,?,?)";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{String.valueOf(s.getImage()),s.getMasach(),s.getTenSach(),s.getSoLuong(),s.getNoidung()});
    }
    public void SuaSach(Sach s){
        String sql = "update tbSachTXS set image=?,tensach=?,soluong=?,noidung=? where masach=? ";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{String.valueOf(s.getImage()),s.getTenSach(),s.getSoLuong(),s.getNoidung(),s.getMasach()});

    }
    public List<Sach> DocDL()
    {
        List<Sach> data = new ArrayList<>();
        String sql = "select * from tbSachTXS";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                Sach s = new Sach();
                s.setImage(cursor.getInt(0));
                s.setMasach(cursor.getString(1).toString());
                s.setTenSach(cursor.getString(2).toString());
                s.setSoLuong(cursor.getString(3).toString());
                s.setNoidung(cursor.getString(4).toString());
                data.add(s);
            }while (cursor.moveToNext());
        }
        return data;
    }
}
