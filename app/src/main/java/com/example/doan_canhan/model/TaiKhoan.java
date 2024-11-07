package com.example.doan_canhan.model;

public class TaiKhoan {
    String sodienthoai,tendangnhap,matkhau,quyen;

    public TaiKhoan() {
    }

    public TaiKhoan(String sodienthoai, String tendangnhap, String matkhau, String quyen) {
        this.sodienthoai = sodienthoai;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.quyen = quyen;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "sodienthoai='" + sodienthoai + '\'' +
                ", tendangnhap='" + tendangnhap + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", quyen='" + quyen + '\'' +
                '}';
    }
}
