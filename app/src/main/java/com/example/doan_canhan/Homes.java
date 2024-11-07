package com.example.doan_canhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_canhan.database.DBDangNhap;
import com.example.doan_canhan.database.DBSacch_TXS;
import com.example.doan_canhan.database.DBSach;
import com.example.doan_canhan.database.DBSach_Tra;
import com.example.doan_canhan.model.SachMuon;
import com.example.doan_canhan.model.TaiKhoan;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Homes extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle drawerToggle;
    ImageView ivSachdamuon,ivThongke,ivsetting,ivbook,ivsachdanhmuc,ivmenu;
    TextView tvslsachmuon,tvslsachtra;


    static ArrayList<SachMuon> mylist_sachtra = new ArrayList<>();

    ArrayList<TaiKhoan> mylist_TK = new ArrayList<>();
    DBDangNhap dbDangNhap;
    static TaiKhoan tk = new TaiKhoan();
    static String quyen ="user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);
        setConstrol();
        setEvent();
    }

    private void setEvent() {
     dbDangNhap = new DBDangNhap(this);
     mylist_TK.clear();
     mylist_TK.addAll(dbDangNhap.DocDL());
     LayDuLieu();

             Sach_TXS.dbSacchTxs = new DBSacch_TXS(this);
             Sach_TXS.mylist_sach.clear();
             Sach_TXS.mylist_sach.addAll(Sach_TXS.dbSacchTxs.DocDL());

            trasach.dbSach_tra = new DBSach_Tra(this);
            mylist_sachtra.clear();
            mylist_sachtra.addAll(trasach.dbSach_tra.DocDL_SachTra());
            int demtra = 0;
            for (SachMuon a : mylist_sachtra) {
                if (a.getSodienthoai().toString().equals(tk.getSodienthoai().toString())){
                    demtra++;
                }

            }
            DuLieu.soluongsachtra = demtra;
            //doc du liệu sách mượn và đếm số lượng
            int demmuon = 0;
            MainActivity.dbSach = new DBSach(this);
            MainActivity.mylist.clear();
            MainActivity.mylist.addAll(MainActivity.dbSach.DocDL());
            for (SachMuon a : MainActivity.mylist) {
                if (a.getSodienthoai().toString().equals(tk.getSodienthoai().toString())){
                    demmuon++;
                }

            }
            DuLieu.soluongsachmuon = demmuon;
            tvslsachmuon.setText("" + DuLieu.soluongsachmuon);
            tvslsachtra.setText("" + DuLieu.soluongsachtra);

                //drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
                //drawerLayout.addDrawerListener(drawerToggle);
                //drawerToggle.syncState();
        ivmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDrawer(drawerLayout);
            }
        });
                //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                       if (item.getItemId()==R.id.mnsachmuon){
                           Intent a = new Intent(Homes.this,MainActivity.class);
                           startActivity(a);
                       }
                        if (item.getItemId()==R.id.mncrudsach){
                            Intent a = new Intent(Homes.this,Sach_TXS.class);
                            startActivity(a);
                        }
                        if (item.getItemId()==R.id.mnthongke){
                            if (tk.getQuyen().toString().equals(quyen.toString())){

                                Toast.makeText(Homes.this,"bạn chưa được cấp quyền",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent a = new Intent(Homes.this,ThongKe_Sach.class);
                                startActivity(a);
                            }

                        }
                        if (item.getItemId()==R.id.mnsetting){
                            Intent a = new Intent(Homes.this,Caidat.class);
                            startActivity(a);
                        }
                        drawerLayout.closeDrawers();
                        return false;
                    }
                });



        ivThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tk.getQuyen().toString().equals(quyen.toString())){
                    Toast.makeText(Homes.this,"bạn chưa được cấp quyền",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent a = new Intent(Homes.this,ThongKe_Sach.class);
                    startActivity(a);
                }
            }
        });
        ivSachdamuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Homes.this, MainActivity.class);
                startActivity(a);
            }
        });
        ivsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Homes.this, Caidat.class);
                startActivity(a);
            }
        });
        ivbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Homes.this, Sach_TXS.class);
                startActivity(a);
            }
        });
        ivsachdanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Homes.this, Sach_TXS.class);
                startActivity(a);
            }
        });

    }

    private void LayDuLieu() {
        for (TaiKhoan s :mylist_TK){
            tk.setSodienthoai(s.getSodienthoai());
            tk.setQuyen(s.getQuyen());
            tk.setMatkhau(s.getMatkhau());
            tk.setTendangnhap(s.getTendangnhap());
        }
    }


    public static void OpenDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void CloseDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setConstrol() {

        ivmenu=findViewById(R.id.ivmenu);
        ivSachdamuon = findViewById(R.id.ivsachdamuon_home);
        ivThongke = findViewById(R.id.ivthongke_home);
        tvslsachmuon = findViewById(R.id.tvsachmuon_home);
        tvslsachtra = findViewById(R.id.tvsachtra_home);
        drawerLayout=findViewById(R.id.drawerlayout);
        navView=findViewById(R.id.navView);
        ivsetting=findViewById(R.id.ivsettings);
        ivbook=findViewById(R.id.ivbook);
        ivsachdanhmuc=findViewById(R.id.sach_danhmuc);
    }
}