package com.example.doan_canhan.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.doan_canhan.model.SachMuon;

import java.util.ArrayList;
import java.util.List;

public class DBSach_Tra extends SQLiteOpenHelper {
    public DBSach_Tra(@NonNull Context context){
        super(context,"dbSachTraeb",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbSachTra(image text,mas text,tensach text,soluong text,sodienthoai text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void MuonSach(SachMuon s){
        String sql ="Delete from tbSachTra where mas=?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{s.getMaSach()});
    }
    public void TraSach(SachMuon s){
        String sql = "Insert into tbSachTra values(?,?,?,?,?)";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{String.valueOf(s.getImage()),s.getMaSach(),s.getTenSach(),s.getSoLuong(),s.getSodienthoai()});
    }
    public List<SachMuon> DocDL_SachTra()
    {
        List<SachMuon> data = new ArrayList<>();
        String sql = "select * from tbSachTra";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                SachMuon s = new SachMuon();
                s.setImage(cursor.getInt(0));
                s.setMaSach(cursor.getString(1).toString());
                s.setTenSach(cursor.getString(2).toString());
                s.setSoLuong(cursor.getString(3).toString());
                s.setSodienthoai(cursor.getString(4).toString());
                data.add(s);
            }while (cursor.moveToNext());
        }
        return data;
    }
}
