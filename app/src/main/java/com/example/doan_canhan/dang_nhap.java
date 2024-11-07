package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_canhan.database.DBDangNhap;
import com.example.doan_canhan.database.DBTaiKhoan;
import com.example.doan_canhan.model.TaiKhoan;

import java.util.ArrayList;

public class dang_nhap extends AppCompatActivity {

    EditText edtTendangnhap,edtmatkhau;
    TextView tvdangkyTK;
    Button btnOK;

    static ArrayList<TaiKhoan> mylist_TK = new ArrayList<>();

    DBDangNhap dbDangNhap;
    DBTaiKhoan dbTaiKhoan;
    TaiKhoan arr = new TaiKhoan();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        setConTrol();
        setEvent();
    }
    //
    public boolean KTtenvsMK(String a,String b){
        boolean kq = false;
        for (TaiKhoan s : mylist_TK){
            if (s.getTendangnhap().equals(a)&&s.getMatkhau().equals(b)){
                kq=true;
                arr.setQuyen(s.getQuyen().toString());
                arr.setSodienthoai(s.getSodienthoai().toString());
                break;
            }
        }
        return kq;
    }

    public void docTaiKhoan(){
        dbTaiKhoan = new DBTaiKhoan(this);
        mylist_TK.clear();
        mylist_TK.addAll(dbTaiKhoan.DocDL());
    }
    private void setEvent() {
        dbDangNhap = new DBDangNhap(this);
        docTaiKhoan();
        tvdangkyTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(dang_nhap.this, dang_ky.class);
                startActivity(a);
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTendangnhap.getText().length()>0||edtmatkhau.getText().length()>0) {
                    arr.setTendangnhap(edtTendangnhap.getText().toString());
                    arr.setMatkhau(edtmatkhau.getText().toString());
                      if (KTtenvsMK(arr.getTendangnhap().toString(),arr.getMatkhau().toString())==true){
                            dbDangNhap.DangNhap(arr);
                            Intent e = new Intent(dang_nhap.this,Homes.class);
                            startActivity(e);
                        }
                        else {
                            Toast.makeText(dang_nhap.this,"tên đăng nhập hoặt mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                        }

                }
                else {
                    Toast.makeText(dang_nhap.this,"bạn chưa nhập tên đăng nhập",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }



    private void setConTrol() {
        edtTendangnhap=findViewById(R.id.edttendangnhap);
        edtmatkhau=findViewById(R.id.edtmatkhau);
        tvdangkyTK=findViewById(R.id.tvdangkytaikhoan);
        btnOK=findViewById(R.id.btnOK);
    }
}