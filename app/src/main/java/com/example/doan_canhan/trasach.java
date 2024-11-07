package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_canhan.database.DBSach;
import com.example.doan_canhan.database.DBSach_Tra;
import com.example.doan_canhan.model.SachMuon;

public class trasach extends AppCompatActivity {

    ImageView ivSach,ivthoat;
    TextView tvMaS,tvTenS;
    Button btntrasach;
    static DBSach_Tra dbSach_tra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasach);
        setConstrol();
        setEvent();
    }

    private void setEvent() {
        dbSach_tra=new DBSach_Tra(this);
        MainActivity.dbSach = new DBSach(this);
        Intent a = getIntent();
        SachMuon sach = (SachMuon) getIntent().getSerializableExtra("item");
        tvMaS.setText(sach.getMaSach());
        tvTenS.setText(sach.getTenSach());
        ivSach.setImageResource(sach.getImage());


        ivthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btntrasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    for (SachMuon item: MainActivity.mylist){
                        if (item.getMaSach().equals(tvMaS.getText())){
                            SachMuon s = new SachMuon();
                            s.setImage(sach.getImage());
                            s.setMaSach(sach.getMaSach());
                            s.setTenSach(sach.getTenSach());
                            s.setSoLuong(sach.getSoLuong());
                            s.setSodienthoai(sach.getSodienthoai());
                            //trả sách database và đọc dữ liệu sách trả
                            dbSach_tra.TraSach(s);
                            Homes.mylist_sachtra.clear();
                            Homes.mylist_sachtra.addAll(dbSach_tra.DocDL_SachTra());
                            MainActivity.dbSach.TraSach(item);


                            Toast.makeText(trasach.this,"trả sách thành công",Toast.LENGTH_SHORT).show();


                            if (item.getImage()==R.drawable.sacha){
                                DuLieu.SachMuonA -=1;
                                DuLieu.SachTraA +=1;
                            }
                            if (item.getImage()==R.drawable.sachb){
                                DuLieu.SachMuonB -=1;
                                DuLieu.SachTraB +=1;
                            }
                            if (item.getImage()==R.drawable.sachc){
                                DuLieu.SachMuonC -=1;
                                DuLieu.SachTraC +=1;
                            }
                            if (item.getImage()==R.drawable.sachd){
                                DuLieu.SachMuonD-=1;
                                DuLieu.SachTraD+=1;
                            }
                            break;
                        }

                    }
                    MainActivity.mylist.clear();
                    MainActivity.mylist.addAll(MainActivity.dbSach.DocDL());
                int dem = 0;
                MainActivity.mylist.clear();
                MainActivity.mylistsachmuon.clear();
                MainActivity.mylistsachmuon.addAll(MainActivity.dbSach.DocDL());
                for (SachMuon a:MainActivity.mylistsachmuon){
                    if (a.getSodienthoai().equals(Homes.tk.getSodienthoai().toString())){
                        MainActivity.mylist.add(a);
                        dem++;
                    }

                }
                int demtra=0;
                for (SachMuon b:Homes.mylist_sachtra){
                    if (b.getSodienthoai().toString().equals(Homes.tk.getSodienthoai().toString()))
                    {
                        demtra++;
                    }

                }
                DuLieu.soluongsachmuon=dem;
                DuLieu.soluongsachtra=demtra;
                MainActivity.tvslSachmuon.setText("Số lượng: "+DuLieu.soluongsachmuon);
                MainActivity.myadapter.notifyDataSetChanged();
                    onBackPressed();
            }
        });

    }

    private void setConstrol() {
        ivSach = findViewById(R.id.ivhinh_sachtra);
        ivthoat = findViewById(R.id.ivthoat);
        tvMaS = findViewById(R.id.tvmasachtra);
        tvTenS = findViewById(R.id.tvtensachtra);
        btntrasach = findViewById(R.id.btntrasach);
    }
}