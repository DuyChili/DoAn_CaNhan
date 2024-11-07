package com.example.doan_canhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doan_canhan.ArrayAdapter.MyArrayAdapter;
import com.example.doan_canhan.database.DBSach;
import com.example.doan_canhan.model.SachMuon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    //khai báo ListView
    static ArrayList<SachMuon> mylist = new ArrayList<>();
    static ArrayList<SachMuon> mylistsachmuon = new ArrayList<>();
    static MyArrayAdapter myadapter;
    ListView lvSachmuon;
    Button btnmuonsach;
    static TextView tvslSachmuon;

    ImageView ivthoat,ivhome,ivsettings,ivbook;
    static DBSach dbSach ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setConstrol();
        setEvent();
    }



    private void setEvent() {
        //đọc dữ liệu sách mượn và đếm số lượng sách mượn

        int dem = 0;
        dbSach = new DBSach(this);
        mylist.clear();
        mylistsachmuon.clear();
        mylistsachmuon.addAll(dbSach.DocDL());
        for (SachMuon a:mylistsachmuon){
            if (a.getSodienthoai().equals(Homes.tk.getSodienthoai().toString())){
                mylist.add(a);
                dem++;
            }

        }
        DuLieu.soluongsachmuon=dem;
        tvslSachmuon.setText("Số lượng: "+DuLieu.soluongsachmuon);
        myadapter = new MyArrayAdapter(MainActivity.this,R.layout.layout_item,mylist);
        lvSachmuon.setAdapter(myadapter);
        //xử lý click listview
       lvSachmuon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                   Intent a = new Intent(MainActivity.this, trasach.class);
                   a.putExtra("item", mylist.get(i));
                   startActivity(a);
           }
       });


       ivhome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent a = new Intent(MainActivity.this, Homes.class);
               startActivity(a);
           }
       });
       btnmuonsach.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                   Intent a = new Intent(MainActivity.this, muon_sach.class);
                   startActivity(a);
           }
       });
       ivsettings.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent a = new Intent(MainActivity.this, Caidat.class);
               startActivity(a);
           }
       });
        ivbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, Sach_TXS.class);
                startActivity(a);
            }
        });


    }


    private void setConstrol() {
        lvSachmuon = findViewById(R.id.lvsachdamuon);
        tvslSachmuon = findViewById(R.id.tvslSachmuon);

        ivhome = findViewById(R.id.ivhome);
        btnmuonsach = findViewById(R.id.btnmuonsach_phieumuon);
        ivsettings=findViewById(R.id.ivsettings);
        ivbook=findViewById(R.id.ivbook);
    }
}