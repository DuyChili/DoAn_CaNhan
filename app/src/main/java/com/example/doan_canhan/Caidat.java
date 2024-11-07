package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan_canhan.database.DBDangNhap;
import com.example.doan_canhan.model.TaiKhoan;

import java.util.ArrayList;

public class Caidat extends AppCompatActivity {

    ImageView ivhome,ivsachmuon,ivsetting,ivbook,ivOut;

    DBDangNhap dbDangNhap;
    ArrayList<TaiKhoan> myliss_TK = new ArrayList<>();
    TextView tvTenTK;
    TaiKhoan s = new TaiKhoan();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbDangNhap=new DBDangNhap(this);
        myliss_TK.clear();
        myliss_TK.addAll(dbDangNhap.DocDL());
        KhoiTao();
        tvTenTK.setText(Homes.tk.getTendangnhap().toString());
        ivOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 dbDangNhap.Out(s);
                 Intent a = new Intent(Caidat.this, dang_nhap.class);
                 startActivity(a);
            }
        });
        ivhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(Caidat.this,Homes.class);
                startActivity(a);
            }
        });
        ivsachmuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(Caidat.this,MainActivity.class);
                startActivity(a);
            }
        });
        ivbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Caidat.this, Sach_TXS.class);
                startActivity(a);
            }
        });
    }

    private void KhoiTao() {
        for (TaiKhoan a :myliss_TK){
            s.setSodienthoai(a.getSodienthoai());
            s.setTendangnhap(a.getTendangnhap());
            s.setMatkhau(a.getMatkhau());
            s.setQuyen(a.getQuyen());
        }
    }

    private void setControl() {
        tvTenTK=findViewById(R.id.tvTendangnhap);
        ivOut=findViewById(R.id.ivout);
        ivhome=findViewById(R.id.ivhome);
        ivsachmuon=findViewById(R.id.ivsachdamuon);
        ivsetting=findViewById(R.id.ivsettings);
        ivbook=findViewById(R.id.ivbook);
    }
}