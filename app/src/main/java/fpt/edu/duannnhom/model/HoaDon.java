package fpt.edu.duannnhom.model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private int maTV;
    private int maSanPham;
    private Date ngay;
    private int tienThue;
private String ngaySinh;
    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maTV, int maSanPham, Date ngay, int tienThue, String ngaySinh ) {
        this.maHoaDon = maHoaDon;
        this.maTV = maTV;
this.ngaySinh = ngaySinh;
        this.maSanPham = maSanPham;
        this.ngay = ngay;
        this.tienThue = tienThue;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    public String getNgaySinnh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }


    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int      getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
