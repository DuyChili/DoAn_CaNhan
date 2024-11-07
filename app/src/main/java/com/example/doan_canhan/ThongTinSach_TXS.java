package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_canhan.database.DBSacch_TXS;
import com.example.doan_canhan.model.Sach;

import java.util.ArrayList;

public class ThongTinSach_TXS extends AppCompatActivity {

    ImageView ivthoat,ivHinh;
    TextView tvma;
    EditText edtten,edtsoluong,edtnoidung;
    Spinner spthongtinsach;
    Button btnsua,btnxoa;
    ArrayAdapter adapter;
    ArrayList<String> arrlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_sach_txs);
        setConTrol();
        setEvent();
    }
    private void KhoiTao() {
        arrlist.add("Tin Vào Chính Mình");
        arrlist.add("Phép màu để trở thành chính mình");
        arrlist.add("Dám thay đổi chính mình");
        arrlist.add("Luật hình sự");
    }
    private void setEvent() {
        Sach_TXS.dbSacchTxs=new DBSacch_TXS(this);
        KhoiTao();

        Sach s = new Sach();

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spthongtinsach.setAdapter(adapter);
        spthongtinsach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (spthongtinsach.getSelectedItem().equals("Tin Vào Chính Mình")){
                    ivHinh.setImageResource(R.drawable.sacha);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sacha);
                }
                if (spthongtinsach.getSelectedItem().equals("Phép màu để trở thành chính mình")){
                    ivHinh.setImageResource(R.drawable.sachb);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sachb);
                }
                if (spthongtinsach.getSelectedItem().equals("Dám thay đổi chính mình")){
                    ivHinh.setImageResource(R.drawable.sachc);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sachc);
                }
                if (spthongtinsach.getSelectedItem().equals("Luật hình sự")){
                    ivHinh.setImageResource(R.drawable.sachd);
                    edtten.setText(arrlist.get(position));
                    s.setImage(R.drawable.sachd);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent a = getIntent();
        Sach sach = (Sach) getIntent().getSerializableExtra("a");
        ivHinh.setImageResource(sach.getImage());
        tvma.setText(sach.getMasach());
        edtten.setText(sach.getTenSach());
        edtsoluong.setText(sach.getSoLuong());
        edtnoidung.setText(sach.getNoidung());

        if (sach.getTenSach().equals("Tin Vào Chính Mình"))
            spthongtinsach.setSelection(0);
        if (sach.getTenSach().equals("Phép màu để trở thành chính mình"))
            spthongtinsach.setSelection(1);
        if (sach.getTenSach().equals("Dám thay đổi chính mình"))
            spthongtinsach.setSelection(2);
        if (sach.getTenSach().equals("Luật hình sự"))
            spthongtinsach.setSelection(3);
        ivthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.setMasach(tvma.getText().toString());
                s.setTenSach(edtten.getText().toString());
                s.setSoLuong(edtsoluong.getText().toString());
                s.setNoidung(edtnoidung.getText().toString());
                Sach_TXS.dbSacchTxs.SuaSach(s);
                Sach_TXS.mylist_sach.clear();
                Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
                Sach_TXS.adapterSachTxs.notifyDataSetChanged();
                Toast.makeText(ThongTinSach_TXS.this,"Cập nhập thành công",Toast.LENGTH_SHORT).show();
                int dem=0;
                for (Sach e:Sach_TXS.mylist_sach){
                    dem++;
                }
                Sach_TXS.tvslsach_txs.setText("số lượng: "+dem);
                onBackPressed();
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.setMasach(tvma.getText().toString());
                Sach_TXS.dbSacchTxs.XoaSach(s);
                Sach_TXS.mylist_sach.clear();
                Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
                Sach_TXS.adapterSachTxs.notifyDataSetChanged();
                Toast.makeText(ThongTinSach_TXS.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
                int dem=0;
                for (Sach e:Sach_TXS.mylist_sach){
                    dem++;
                }
                Sach_TXS.tvslsach_txs.setText("số lượng: "+dem);
                onBackPressed();
            }
        });


    }

    private void setConTrol() {
        ivHinh=findViewById(R.id.ivhinh_Thongtinsach);
        ivthoat=findViewById(R.id.ivthoat);
        tvma=findViewById(R.id.tvmasach_thongtinsach);
        edtten=findViewById(R.id.edttensach_thongtinsach);
        edtsoluong=findViewById(R.id.edtsoluong_thongtinsach);
        edtnoidung=findViewById(R.id.edtnoidung_thongtinsach);
        btnsua=findViewById(R.id.btnCapNhap_thongtinsach);
        btnxoa=findViewById(R.id.btnxoa_thongtinsach);
        spthongtinsach=findViewById(R.id.spThongtinSach);
    }
}