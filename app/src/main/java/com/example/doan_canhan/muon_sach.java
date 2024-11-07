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
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_canhan.database.DBSacch_TXS;
import com.example.doan_canhan.database.DBSach;
import com.example.doan_canhan.database.DBthongkesachmuon;
import com.example.doan_canhan.model.Sach;
import com.example.doan_canhan.model.SachMuon;
import com.example.doan_canhan.model.thongkesachmuon;

import java.util.ArrayList;

public class muon_sach extends AppCompatActivity {

    TextView tvtensach,tvnoidung;
    ImageView ivthoat,ivhinh;
    Spinner sptensach;
    Button btnmuonsach;
    EditText edtngaymuon;
    ArrayList<String> arrlist = new ArrayList<>();
    String soluong ="0";
    ArrayAdapter adapter;
    static DBthongkesachmuon dBthongkesachmuon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muon_sach);
        setControl();
        setEvent();
    }

    private void setEvent() {
        Sach_TXS.dbSacchTxs = new DBSacch_TXS(this);
       MainActivity.dbSach = new DBSach(this);
       dBthongkesachmuon = new DBthongkesachmuon(this);
        Khoitao();

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptensach.setAdapter(adapter);
        sptensach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (sptensach.getSelectedItem().equals("Tin Vào Chính Mình")){
                    for (Sach b : Sach_TXS.mylist_sach){
                        if (b.getTenSach().equals(arrlist.get(position))){
                            ivhinh.setImageResource(R.drawable.sacha);
                            tvtensach.setText(arrlist.get(position));
                            tvnoidung.setText(b.getNoidung());
                            soluong = b.getSoLuong();
                        }
                    }

                }
                if (sptensach.getSelectedItem().equals("Phép màu để trở thành chính mình")){
                    for (Sach b : Sach_TXS.mylist_sach){
                        if (b.getTenSach().equals(arrlist.get(position))){
                            ivhinh.setImageResource(R.drawable.sachb);
                            tvtensach.setText(arrlist.get(position));
                            tvnoidung.setText(b.getNoidung());
                            soluong = b.getSoLuong();
                        }
                    }
                }
                if (sptensach.getSelectedItem().equals("Dám thay đổi chính mình")){
                    for (Sach b : Sach_TXS.mylist_sach){
                        if (b.getTenSach().equals(arrlist.get(position))){
                            ivhinh.setImageResource(R.drawable.sachc);
                            tvtensach.setText(arrlist.get(position));
                            tvnoidung.setText(b.getNoidung());
                            soluong = b.getSoLuong();
                        }
                    }
                }
                if (sptensach.getSelectedItem().equals("Luật hình sự")){
                    for (Sach b : Sach_TXS.mylist_sach){
                        if (b.getTenSach().equals(arrlist.get(position))){
                            ivhinh.setImageResource(R.drawable.sachd);
                            tvtensach.setText(arrlist.get(position));
                            tvnoidung.setText(b.getNoidung());
                            soluong = b.getSoLuong();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnmuonsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dema =0;
                int demb=0;
                int demc=0;
                int demd=0;


                for (SachMuon a :MainActivity.mylist){

                    if (a.getImage() == R.drawable.sacha){
                        dema++;
                    }
                    if (a.getImage() == R.drawable.sachb){
                        demb++;
                    }
                    if (a.getImage() == R.drawable.sachc){
                        demc++;
                    }
                    if (a.getImage() == R.drawable.sachd) {
                        demd++;
                    }


                }
                DuLieu.SachMuonA=dema;
                DuLieu.SachMuonB=demb;
                DuLieu.SachMuonC=demc;
                DuLieu.SachMuonD=demd;
                 int dem=0;
                int ma=1;

                MainActivity.mylist.clear();
                MainActivity.mylist.addAll(MainActivity.dbSach.DocDL());
                for (SachMuon a :MainActivity.mylist){
                    DuLieu.dem+=1;
                    if (a.getMaSach().equals("S"+DuLieu.dem)){
                        DuLieu.dem+=1;
                    }
                    else{
                        DuLieu.demma= DuLieu.dem;
                        break;
                    }

                }
                String[]thang = edtngaymuon.getText().toString().split("/");
                if ( (Integer.parseInt( thang[1])>0 && Integer.parseInt( thang[1])<13)){
                    if (tvtensach.getText().equals("Tin Vào Chính Mình")){
                        if (DuLieu.SachMuonA < Integer.parseInt(soluong) && edtngaymuon.getText().length() > 0){
                            SachMuon s = new SachMuon();
                            s.setImage(R.drawable.sacha);
                            s.setMaSach("S"+(DuLieu.demma));
                            s.setTenSach(tvtensach.getText().toString());
                            s.setSoLuong("1");
                            s.setSodienthoai(Homes.tk.getSodienthoai().toString());
                            MainActivity.dbSach.MuonSach(s);
                            trasach.dbSach_tra.MuonSach(s);
                            //thong kê sách mượn
                            thongkesachmuon a = new thongkesachmuon();
                            a.setImage(R.drawable.sacha);
                            a.setMasach("S"+(DuLieu.demma));
                            a.setTenSach(tvtensach.getText().toString());
                            a.setSoLuong("1");
                            a.setNgaythang(edtngaymuon.getText().toString());
                            dBthongkesachmuon.MuonSach(a);
                            Toast.makeText(muon_sach.this,"mượn thành công",Toast.LENGTH_SHORT).show();
                            DuLieu.soluongsachmuon=0;
                            MainActivity.mylist.clear();
                            MainActivity.mylistsachmuon.clear();
                            MainActivity.mylistsachmuon.addAll(MainActivity.dbSach.DocDL());
                            for (SachMuon l:MainActivity.mylistsachmuon){
                                if (l.getSodienthoai().equals(Homes.tk.getSodienthoai().toString())){
                                    MainActivity.mylist.add(l);
                                    DuLieu.soluongsachmuon++;
                                }

                            }

                            MainActivity.tvslSachmuon.setText("Số lượng: "+DuLieu.soluongsachmuon);
                            MainActivity.myadapter.notifyDataSetChanged();
                            DuLieu.demma++;

                        }
                        else {
                            Toast.makeText(muon_sach.this,"đã hết sách để mượn",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (tvtensach.getText().equals("Phép màu để trở thành chính mình")){
                        if (DuLieu.SachMuonB < Integer.parseInt(soluong)){
                            SachMuon s = new SachMuon();
                            s.setImage(R.drawable.sachb);
                            s.setMaSach("S"+(DuLieu.demma));
                            s.setTenSach(tvtensach.getText().toString());
                            s.setSoLuong("1");
                            s.setSodienthoai(Homes.tk.getSodienthoai().toString());
                            MainActivity.dbSach.MuonSach(s);
                            trasach.dbSach_tra.MuonSach(s);
                            thongkesachmuon a = new thongkesachmuon();
                            a.setImage(R.drawable.sachb);
                            a.setMasach("S"+(DuLieu.demma));
                            a.setTenSach(tvtensach.getText().toString());
                            a.setSoLuong("1");
                            a.setNgaythang(edtngaymuon.getText().toString());
                            dBthongkesachmuon.MuonSach(a);
                            Toast.makeText(muon_sach.this,"mượn thành công",Toast.LENGTH_SHORT).show();
                            DuLieu.soluongsachmuon=0;
                            MainActivity.mylist.clear();
                            MainActivity.mylistsachmuon.clear();
                            MainActivity.mylistsachmuon.addAll(MainActivity.dbSach.DocDL());
                            for (SachMuon l:MainActivity.mylistsachmuon){
                                if (l.getSodienthoai().equals(Homes.tk.getSodienthoai().toString())){
                                    MainActivity.mylist.add(l);
                                    DuLieu.soluongsachmuon++;
                                }

                            }
                            MainActivity.tvslSachmuon.setText("Số lượng: "+DuLieu.soluongsachmuon);
                            MainActivity.myadapter.notifyDataSetChanged();
                            DuLieu.demma++;

                        }
                        else {
                            Toast.makeText(muon_sach.this,"đã hết sách để mượn",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (tvtensach.getText().equals("Dám thay đổi chính mình")){
                        if (DuLieu.SachMuonC < Integer.parseInt(soluong)){
                            SachMuon s = new SachMuon();
                            s.setImage(R.drawable.sachc);
                            s.setMaSach("S"+(DuLieu.demma));
                            s.setTenSach(tvtensach.getText().toString());
                            s.setSoLuong("1");
                            s.setSodienthoai(Homes.tk.getSodienthoai().toString());
                            MainActivity.dbSach.MuonSach(s);
                            trasach.dbSach_tra.MuonSach(s);
                            thongkesachmuon a = new thongkesachmuon();
                            a.setImage(R.drawable.sachc);
                            a.setMasach("S"+(DuLieu.demma));
                            a.setTenSach(tvtensach.getText().toString());
                            a.setSoLuong("1");
                            a.setNgaythang(edtngaymuon.getText().toString());
                            dBthongkesachmuon.MuonSach(a);
                            Toast.makeText(muon_sach.this,"mượn thành công",Toast.LENGTH_SHORT).show();
                            DuLieu.soluongsachmuon=0;
                            MainActivity.mylist.clear();
                            MainActivity.mylistsachmuon.clear();
                            MainActivity.mylistsachmuon.addAll(MainActivity.dbSach.DocDL());
                            for (SachMuon l:MainActivity.mylistsachmuon){
                                if (l.getSodienthoai().equals(Homes.tk.getSodienthoai().toString())){
                                    MainActivity.mylist.add(l);
                                    DuLieu.soluongsachmuon++;
                                }

                            }
                            MainActivity.tvslSachmuon.setText("Số lượng: "+DuLieu.soluongsachmuon);
                            MainActivity.myadapter.notifyDataSetChanged();
                            DuLieu.demma++;

                        }
                        else {
                            Toast.makeText(muon_sach.this,"đã hết sách để mượn",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (tvtensach.getText().equals("Luật hình sự")){
                        if (DuLieu.SachMuonD < Integer.parseInt(soluong)){
                            SachMuon s = new SachMuon();
                            s.setImage(R.drawable.sachd);
                            s.setMaSach("S"+(DuLieu.demma));
                            s.setTenSach(tvtensach.getText().toString());
                            s.setSoLuong("1");
                            s.setSodienthoai(Homes.tk.getSodienthoai().toString());
                            MainActivity.dbSach.MuonSach(s);
                            trasach.dbSach_tra.MuonSach(s);
                            thongkesachmuon a = new thongkesachmuon();
                            a.setImage(R.drawable.sachd);
                            a.setMasach("S"+(DuLieu.demma));
                            a.setTenSach(tvtensach.getText().toString());
                            a.setSoLuong("1");
                            a.setNgaythang(edtngaymuon.getText().toString());
                            dBthongkesachmuon.MuonSach(a);
                            Toast.makeText(muon_sach.this,"mượn thành công",Toast.LENGTH_SHORT).show();
                            MainActivity.mylist.clear();
                            MainActivity.mylistsachmuon.clear();
                            MainActivity.mylistsachmuon.addAll(MainActivity.dbSach.DocDL());
                            DuLieu.soluongsachmuon=0;
                            for (SachMuon l:MainActivity.mylistsachmuon){
                                if (l.getSodienthoai().equals(Homes.tk.getSodienthoai().toString())){
                                    MainActivity.mylist.add(l);
                                    DuLieu.soluongsachmuon++;
                                }

                            }
                            MainActivity.tvslSachmuon.setText("Số lượng: "+DuLieu.soluongsachmuon);

                            MainActivity.myadapter.notifyDataSetChanged();
                            DuLieu.demma++;

                        }
                        else {
                            Toast.makeText(muon_sach.this,"đã hết sách để mượn",Toast.LENGTH_SHORT).show();
                        }
                }
                }else{
                    Toast.makeText(muon_sach.this,"yêu cầu nhập theo d/m/yyyy",Toast.LENGTH_SHORT).show();
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

    private void Khoitao() {
        Sach_TXS.mylist_sach.clear();
        Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());
        for (Sach a : Sach_TXS.mylist_sach){
            arrlist.add(a.getTenSach());

        }
    }


    private void setControl() {
        edtngaymuon = findViewById(R.id.edtngaymuon);
        btnmuonsach = findViewById(R.id.btnmuonsach);
        ivthoat = findViewById(R.id.ivthoat);
        ivhinh = findViewById(R.id.ivhinh_muon);
        tvnoidung = findViewById(R.id.tvnoidung_muonsach);
        tvtensach = findViewById(R.id.tvtensach_muon);
        sptensach = findViewById(R.id.spTenSach_muon);
    }
}