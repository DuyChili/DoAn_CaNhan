package com.example.doan_canhan.model;

import java.io.Serializable;

public class SachMuon implements Serializable {
    private int image;
    private String maSach,tenSach,SoLuong,sodienthoai;

    public SachMuon() {
    }

    public SachMuon(int image, String maSach, String tenSach,String SoLuong,String sodienthoai) {
        this.image = image;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.SoLuong=SoLuong;
        this.sodienthoai=sodienthoai;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }



    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    @Override
    public String toString() {
        return "SachMuon{" +
                "image=" + image +
                ", maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", SoLuong='" + SoLuong + '\'' +
                ", sodienthoai='" + sodienthoai + '\'' +
                '}';
    }
}
