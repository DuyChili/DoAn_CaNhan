package com.example.doan_canhan.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.doan_canhan.model.thongkesachmuon;

import java.util.ArrayList;
import java.util.List;

public class DBthongkesachmuon extends SQLiteOpenHelper {
    public DBthongkesachmuon(@NonNull Context context){
        super(context,"dbthongkesachmuon",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbSachmuon(image text,mas text,tensach text,soluong text,ngaymuon text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void MuonSach(thongkesachmuon s){
        String sql = "Insert into tbSachmuon values(?,?,?,?,?)";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{String.valueOf(s.getImage()),s.getMasach(),s.getTenSach(),s.getSoLuong(),String.valueOf(s.getNgaythang())});
    }
    public List<thongkesachmuon> DocDL()
    {
        List<thongkesachmuon> data = new ArrayList<>();
        String sql = "select * from tbSachmuon";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                thongkesachmuon s = new thongkesachmuon();
                s.setImage(cursor.getInt(0));
                s.setMasach(cursor.getString(1).toString());
                s.setTenSach(cursor.getString(2).toString());
                s.setSoLuong(cursor.getString(3).toString());
                s.setNgaythang(cursor.getString(4).toString());
                data.add(s);
            }while (cursor.moveToNext());
        }
        return data;
    }
}
