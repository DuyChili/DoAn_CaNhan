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
import com.example.doan_canhan.model.Sach;

import java.util.ArrayList;

public class MyArrayAdapter_Sach_TXS extends ArrayAdapter<Sach> {

    Activity context;
    int IdLayout;
    ArrayList<Sach> mylist;

    public MyArrayAdapter_Sach_TXS(Activity context, int idLayout, ArrayList<Sach> mylist) {
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
        Sach sach = mylist.get(position);
        ImageView ivsach = convertView.findViewById(R.id.ivSach_item);
        ivsach.setImageResource(sach.getImage());
        TextView tvten=convertView.findViewById(R.id.tvtensach_item);
        tvten.setText(sach.getTenSach());
        TextView tvSoluong=convertView.findViewById(R.id.tvsoluong_item);
        tvSoluong.setText("Số lượng: "+sach.getSoLuong());

        return convertView;

    }

}
