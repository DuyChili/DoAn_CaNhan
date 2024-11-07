package com.example.doan_canhan.model;

import java.util.Date;

public class thongkesachmuon  {
    private int image;
    private String masach,tenSach,SoLuong;
    private String ngaythang;

    public thongkesachmuon() {
    }

    public thongkesachmuon(int image, String masach, String tenSach, String soLuong, String ngaythang) {
        this.image = image;
        this.masach = masach;
        this.tenSach = tenSach;
        SoLuong = soLuong;
        this.ngaythang = ngaythang;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
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

    public String getNgaythang() {
        return ngaythang;
    }

    public void setNgaythang(String ngaythang) {
        this.ngaythang = ngaythang;
    }

    @Override
    public String toString() {
        return "thongkesachmuon{" +
                "image=" + image +
                ", masach='" + masach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", SoLuong='" + SoLuong + '\'' +
                ", ngaythang=" + ngaythang +
                '}';
    }
}
