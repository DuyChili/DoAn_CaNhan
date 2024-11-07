package com.example.doan_canhan.model;

import java.io.Serializable;

public class Sach implements Serializable {
    private int image;
    private String masach,tenSach,SoLuong,noidung;

    public String toString() {
        return "Sach{" +
                "image=" + image +
                ", masach='" + masach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", SoLuong='" + SoLuong + '\'' +
                ", noidung='" + noidung + '\'' +
                '}';
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

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Sach(int image, String masach, String tenSach, String soLuong, String noidung) {
        this.image = image;
        this.masach = masach;
        this.tenSach = tenSach;
        SoLuong = soLuong;
        this.noidung = noidung;
    }

    public Sach() {
    }
}
