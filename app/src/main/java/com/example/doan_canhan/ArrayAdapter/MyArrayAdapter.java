package com.example.doan_canhan.ArrayAdapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan_canhan.R;
import com.example.doan_canhan.model.SachMuon;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<SachMuon> {
    Activity context;
    int IdLayout;
    ArrayList<SachMuon> mylist;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<SachMuon> mylist) {
        super(context, idLayout,mylist);
        this.context = context;
        IdLayout = idLayout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = context.getLayoutInflater();
        convertView = myInflater.inflate(IdLayout, null);
        //lấy 1 phần tử trong mảng
        SachMuon sach = mylist.get(position);
        //khai báo tham chiếu id và hiển thị hình ảnh, chữ lên ImageView,TextView
        ImageView img_sach = convertView.findViewById(R.id.ivSach);
        img_sach.setImageResource(sach.getImage());
        TextView tvMaS = convertView.findViewById(R.id.tvMaSach);
        tvMaS.setText(sach.getMaSach());
        TextView tvTenS = convertView.findViewById(R.id.tvTenSach);
        tvTenS.setText(sach.getTenSach());
        return convertView;
    }

}
