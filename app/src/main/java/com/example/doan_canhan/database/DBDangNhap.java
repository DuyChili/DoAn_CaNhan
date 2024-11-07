package com.example.doan_canhan.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.doan_canhan.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class DBDangNhap extends SQLiteOpenHelper {
    public DBDangNhap(@NonNull Context context){
        super(context,"dbDN",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbdangnhap(sodienthoai text,tendn text,matkhau text,quyen text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void DangNhap(TaiKhoan s){
        String sql = "Insert into tbdangnhap values(?,?,?,?)";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{s.getSodienthoai(),s.getTendangnhap(),s.getMatkhau(),s.getQuyen()});
    }
    public void Out(TaiKhoan s){
        String sql ="Delete from tbdangnhap where sodienthoai=?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql,new String[]{s.getSodienthoai()});
    }
    public List<TaiKhoan> DocDL()
    {
        List<TaiKhoan> data = new ArrayList<>();
        String sql = "select * from tbdangnhap";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                TaiKhoan s = new TaiKhoan();

                s.setSodienthoai(cursor.getString(0).toString());
                s.setTendangnhap(cursor.getString(1).toString());
                s.setMatkhau(cursor.getString(2).toString());
                s.setQuyen(cursor.getString(3).toString());
                data.add(s);
            }while (cursor.moveToNext());
        }
        return data;
    }
}
