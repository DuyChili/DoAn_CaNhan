package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.doan_canhan.database.DBSach;
import com.example.doan_canhan.database.DBthongkesachmuon;
import com.example.doan_canhan.model.Sach;
import com.example.doan_canhan.model.thongkesachmuon;

import java.util.ArrayList;

public class ThongKe_Sach extends AppCompatActivity {

    Spinner spTenSach_thongke,spngaythang;
    ImageView ivthoat,ivHinh_Thongke;
    TextView tvSoluongsachTK,tvSoluongSachMuon,tvtongsachmuon,tvTenSach_ThongKe,tvtongsach;
    ArrayList<String> arrlist = new ArrayList<>();
    ArrayAdapter adapter;

    ArrayList<String> listthang = new ArrayList<>();
    ArrayList<String> listthangA = new ArrayList<>();
    static ArrayList<thongkesachmuon> mylist_thongke = new ArrayList<>();
    static ArrayAdapter adapterthang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_sach);

        setConstrol();
        setEvent();
    }



    private void setEvent() {
        muon_sach.dBthongkesachmuon = new DBthongkesachmuon(this);
        mylist_thongke.clear();
        mylist_thongke.addAll( muon_sach.dBthongkesachmuon.DocDL());

        //đếm loại sách theo tháng

        listthang.clear();
        KhoiTaoThang();
        ///tháng
        adapterthang = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listthang);
        adapterthang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spngaythang.setAdapter(adapterthang);
        spngaythang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int dema=0;
                int demb=0;
                int demc=0;
                int demd=0;
                int tongsachmuon=0;

                    for (thongkesachmuon a : mylist_thongke) {
                        String[] thang = a.getNgaythang().split("/");
                        if (spngaythang.getSelectedItem().equals("Tháng: "+thang[1])){
                            if (a.getImage() == R.drawable.sacha){
                                dema++;
                            }
                            if (a.getImage() == R.drawable.sachb){
                                demb++;
                            }
                            if (a.getImage() == R.drawable.sachc){
                                demc++;
                            }
                            if (a.getImage() == R.drawable.sachd){
                                demd++;
                            }
                            tongsachmuon+= Integer.parseInt(a.getSoLuong());
                        }
                    }
                    tvtongsachmuon.setText(""+tongsachmuon);

                DuLieu.SachMuonA=dema;
                DuLieu.SachMuonB=demb;
                DuLieu.SachMuonC=demc;
                DuLieu.SachMuonD=demd;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
        MainActivity.dbSach = new DBSach(this);
        MainActivity.mylist.clear();
        MainActivity.mylist.addAll(MainActivity.dbSach.DocDL());
        //


       KhoiTao();
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTenSach_thongke.setAdapter(adapter);
        spTenSach_thongke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (spTenSach_thongke.getSelectedItem().equals("Tin Vào Chính Mình")){
                   for (Sach a:Sach_TXS.mylist_sach){
                       if (a.getTenSach().equals(arrlist.get(position))){
                           ivHinh_Thongke.setImageResource(R.drawable.sacha);
                           tvTenSach_ThongKe.setText(arrlist.get(position));
                           tvSoluongsachTK.setText(a.getSoLuong());
                           tvSoluongSachMuon.setText(""+DuLieu.SachMuonA);

                       }

                   }


               }
               if (spTenSach_thongke.getSelectedItem().equals("Phép màu để trở thành chính mình")){
                   for (Sach a:Sach_TXS.mylist_sach){
                       if (a.getTenSach().equals(arrlist.get(position))){
                           ivHinh_Thongke.setImageResource(R.drawable.sachb);
                           tvTenSach_ThongKe.setText(arrlist.get(position));
                           tvSoluongsachTK.setText(a.getSoLuong());
                           tvSoluongSachMuon.setText(""+DuLieu.SachMuonB);

                       }

                   }

               }
               if (spTenSach_thongke.getSelectedItem().equals("Dám thay đổi chính mình")){
                   for (Sach a:Sach_TXS.mylist_sach){
                       if (a.getTenSach().equals(arrlist.get(position))){
                           ivHinh_Thongke.setImageResource(R.drawable.sachc);
                           tvTenSach_ThongKe.setText(arrlist.get(position));
                           tvSoluongsachTK.setText(a.getSoLuong());
                           tvSoluongSachMuon.setText(""+DuLieu.SachMuonC);

                       }

                   }
               }
               if (spTenSach_thongke.getSelectedItem().equals("Luật hình sự")){
                   for (Sach a:Sach_TXS.mylist_sach){
                       if (a.getTenSach().equals(arrlist.get(position))){
                           ivHinh_Thongke.setImageResource(R.drawable.sachd);
                           tvTenSach_ThongKe.setText(arrlist.get(position));
                           tvSoluongsachTK.setText(a.getSoLuong());
                           tvSoluongSachMuon.setText(""+DuLieu.SachMuonD);

                       }

                   }
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        //tính tổng tất cả các sách
    int Tong=0;
    for (Sach a : Sach_TXS.mylist_sach){
        Tong += Integer.parseInt(a.getSoLuong());
    }
    tvtongsach.setText(""+Tong);


   ivthoat.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           onBackPressed();
       }
   });



    }

    private void KhoiTaoThang() {
        for (int i = 1; i<=12;i++){
            listthang.add("Tháng: "+i);
        }
    }

    private void KhoiTao() {
        Sach_TXS.mylist_sach.clear();
        Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
        for (Sach a : Sach_TXS.mylist_sach){
            arrlist.add(a.getTenSach());

        }
    }

    private void setConstrol() {
        spTenSach_thongke = findViewById(R.id.spTenSachthongke);
        ivthoat = findViewById(R.id.ivthoat);
        ivHinh_Thongke = findViewById(R.id.ivhinh_thongke);
        tvSoluongsachTK = findViewById(R.id.tvsoluongsach);
        tvSoluongSachMuon = findViewById(R.id.tvsoluongsachmuon);
        tvtongsachmuon = findViewById(R.id.tvtongsachmuon);
        tvTenSach_ThongKe = findViewById(R.id.tvtensachthongke);
        tvtongsach = findViewById(R.id.tvtongtatcacacsach_thongke);
        spngaythang = findViewById(R.id.spNgaythang);
    }
}