package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doan_canhan.database.DBSacch_TXS;
import com.example.doan_canhan.model.Sach;

import java.util.ArrayList;

public class ThemSach_TXS extends AppCompatActivity {

    ImageView ivthoat,ivHinh;
    Button btnthemsach;
    EditText edtma,edtten,edtsoluong,edtnoidung;
    Spinner spsach;
    ArrayAdapter adapter;
    ArrayList<String> arrlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach_txs);
        setControl();
        setEvent();
    }
    private void KhoiTao() {
        arrlist.add("Tin Vào Chính Mình");
        arrlist.add("Phép màu để trở thành chính mình");
        arrlist.add("Dám thay đổi chính mình");
        arrlist.add("Luật hình sự");

    }
    private void setEvent() {
        Sach_TXS.dbSacchTxs = new DBSacch_TXS(this);
        KhoiTao();
        Sach s = new Sach();
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spsach.setAdapter(adapter);
        spsach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spsach.getSelectedItem().equals("Tin Vào Chính Mình")){
                    ivHinh.setImageResource(R.drawable.sacha);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sacha);
                }
                if (spsach.getSelectedItem().equals("Phép màu để trở thành chính mình")){
                    ivHinh.setImageResource(R.drawable.sachb);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sachb);
                }
                if (spsach.getSelectedItem().equals("Dám thay đổi chính mình")){
                    ivHinh.setImageResource(R.drawable.sachc);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sachc);
                }
                if (spsach.getSelectedItem().equals("Luật hình sự")){
                    ivHinh.setImageResource(R.drawable.sachd);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sachd);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnthemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtma.getText().length()>0&&edtten.getText().length()>0&&edtsoluong.getText().length()>0){
                    s.setMasach(edtma.getText().toString());
                    s.setTenSach(edtten.getText().toString());
                    s.setSoLuong(edtsoluong.getText().toString());
                    s.setNoidung(edtnoidung.getText().toString());
                    Sach_TXS.mylist_sach.clear();
                    Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
                    int dems=0;
                    for (Sach s :Sach_TXS.mylist_sach){
                        dems++;
                    }


                    if (dems==0){
                        Sach_TXS.dbSacchTxs.ThemSach(s);
                        Sach_TXS.mylist_sach.clear();
                        Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
                        Toast.makeText(ThemSach_TXS.this,"Thêm sách thành công",Toast.LENGTH_SHORT).show();
                        edtma.getText().clear();
                        edtten.getText().clear();
                        edtsoluong.getText().clear();
                        edtnoidung.getText().clear();
                        Sach_TXS.adapterSachTxs.notifyDataSetChanged();
                        int dem=0;
                        for (Sach s:Sach_TXS.mylist_sach){
                            dem++;
                        }
                        Sach_TXS.tvslsach_txs.setText("số lượng: "+dem);
                    }
                    else {


                        if (ThemSach(s) == true){
                            Toast.makeText(ThemSach_TXS.this,"sách này thư viện đã có!!",Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Sach_TXS.dbSacchTxs.ThemSach(s);
                            Sach_TXS.mylist_sach.clear();
                            Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
                            Toast.makeText(ThemSach_TXS.this,"Thêm sách thành công",Toast.LENGTH_SHORT).show();
                            edtma.getText().clear();
                            edtten.getText().clear();
                            edtsoluong.getText().clear();
                            edtnoidung.getText().clear();
                            Sach_TXS.adapterSachTxs.notifyDataSetChanged();
                            int dem=0;
                            for (Sach s:Sach_TXS.mylist_sach){
                                dem++;
                            }
                            Sach_TXS.tvslsach_txs.setText("số lượng: "+dem);

                        }
                    }




           }
                else{

                    Toast.makeText(ThemSach_TXS.this,"bạn chưa nhập thông tin",Toast.LENGTH_SHORT).show();

                }

            }
        });
        ivthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }
     public boolean ThemSach(Sach s){
        boolean kq = false;
        Sach_TXS.mylist_sach.clear();
        Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
        for (Sach a :Sach_TXS.mylist_sach){
            if (a.getMasach().equals(s.getMasach()) || a.getImage()==s.getImage()){
                 kq = true;
            }
            else {
                kq =false;
            }
        }
        return kq;
    }
    private void setControl() {

        ivthoat=findViewById(R.id.ivthoat);
        btnthemsach= findViewById(R.id.btnthem_themsach);
        ivHinh=findViewById(R.id.ivhinh_Themsach);
        edtma=findViewById(R.id.edtmasach_themsach);
        edtten=findViewById(R.id.edttensach_themsach);
        edtsoluong=findViewById(R.id.edtsoluong_themsach);
        edtnoidung=findViewById(R.id.edtNoidung_themsach);
        spsach=findViewById(R.id.spThemSach);
    }
}