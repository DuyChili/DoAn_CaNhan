package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.doan_canhan.database.DBTaiKhoan;
import com.example.doan_canhan.model.TaiKhoan;

public class dang_ky extends AppCompatActivity {
    EditText edtsodienthoai,edttendangnhap,edtmatkhau,edtnhaplaimatkhau;
    static RadioButton rdadmin,rduser;
    Button btnOK;

    DBTaiKhoan dbTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbTaiKhoan = new DBTaiKhoan(this);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtsodienthoai.getText().length()>0&&edttendangnhap.getText().length()>0&&edtmatkhau.getText().length()>0&&edtnhaplaimatkhau.getText().length()>0&&(rdadmin.isChecked()==true||rduser.isChecked()==true)){
                    TaiKhoan a = new TaiKhoan();
                    a.setSodienthoai(edtsodienthoai.getText().toString());
                    a.setTendangnhap(edttendangnhap.getText().toString());
                    a.setMatkhau(edtmatkhau.getText().toString());
                    if (rdadmin.isChecked()==true){
                        a.setQuyen(rdadmin.getText().toString());
                    }
                    if (rduser.isChecked()==true){
                        a.setQuyen(rduser.getText().toString());
                    }
                    if (edtnhaplaimatkhau.getText().toString().equals(edtmatkhau.getText().toString())){
                        dbTaiKhoan.DangKy(a);
                        dang_nhap.mylist_TK.clear();
                        dang_nhap.mylist_TK.addAll(dbTaiKhoan.DocDL());
                        Toast.makeText(dang_ky.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                    else {
                        Toast.makeText(dang_ky.this,"mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(dang_ky.this,"yêu cầu điền thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setControl() {
        edtsodienthoai=findViewById(R.id.edtsodienthoai_dangky);
        edttendangnhap=findViewById(R.id.edttendangnhap_dangky);
        edtmatkhau=findViewById(R.id.edtmatkhau_dangky);
        edtnhaplaimatkhau=findViewById(R.id.edtnhaplaimatkhau_dangky);
        rdadmin=findViewById(R.id.rdadmin);
        rduser=findViewById(R.id.rduser);
        btnOK=findViewById(R.id.btnOK_dangky);
    }
}