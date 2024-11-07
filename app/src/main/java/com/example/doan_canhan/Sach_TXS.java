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
import android.widget.Toast;

import com.example.doan_canhan.ArrayAdapter.MyArrayAdapter_Sach_TXS;
import com.example.doan_canhan.database.DBSacch_TXS;
import com.example.doan_canhan.model.Sach;

import java.util.ArrayList;

public class Sach_TXS extends AppCompatActivity {

    Button btnthem;
    ImageView ivHome,ivMuonSach,ivCaidat;
    ListView lvsachtxs;
    static TextView tvslsach_txs;
    static ArrayList<Sach> mylist_sach = new ArrayList<>();
    static MyArrayAdapter_Sach_TXS adapterSachTxs;
    static DBSacch_TXS dbSacchTxs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_txs);
        setControl();
        setEvent();
    }

    private void setEvent() {

       dbSacchTxs=new DBSacch_TXS(this);
       mylist_sach.clear();
       mylist_sach.addAll(dbSacchTxs.DocDL());
       int dem=0;
       for (Sach a:mylist_sach){
           dem++;
       }
       tvslsach_txs.setText("số lượng: "+dem);


        adapterSachTxs = new MyArrayAdapter_Sach_TXS(Sach_TXS.this,R.layout.layout_item_sach,mylist_sach);
        lvsachtxs.setAdapter(adapterSachTxs);
        lvsachtxs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Homes.tk.getQuyen().toString().equals(Homes.quyen.toString())) {
                    Toast.makeText(Sach_TXS.this,"bạn chưa được cấp quyền",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent a = new Intent(Sach_TXS.this, ThongTinSach_TXS.class);
                    a.putExtra("a", mylist_sach.get(position));
                    startActivity(a);

                }
            }
        });






        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Homes.tk.getQuyen().toString().equals(Homes.quyen.toString())) {

                    Toast.makeText(Sach_TXS.this,"bạn chưa được cấp quyền",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent a = new Intent(Sach_TXS.this, ThemSach_TXS.class);
                    startActivity(a);
                }
            }
        });
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Sach_TXS.this, Homes.class);
                startActivity(a);
            }
        });
        ivMuonSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Sach_TXS.this, MainActivity.class);
                startActivity(a);
            }
        });
        ivCaidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Sach_TXS.this, Caidat.class);
                startActivity(a);
            }
        });
    }



    private void setControl() {
        lvsachtxs=findViewById(R.id.lvsachdamuon_TXS);
        tvslsach_txs=findViewById(R.id.tvslSach_TXS);
        btnthem =findViewById(R.id.btnthem_TXS);
        ivHome=findViewById(R.id.ivhome);
        ivMuonSach=findViewById(R.id.ivsachdamuon);
        ivCaidat=findViewById(R.id.ivsettings);
    }
}