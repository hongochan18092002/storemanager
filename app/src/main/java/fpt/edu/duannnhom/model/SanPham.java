package fpt.edu.duannnhom.model;

public class SanPham {
private int maSanPham;
private String tenSanPham;
private  String gia;
private int maLoai;

    public SanPham() {
    }

    public SanPham(int maSanPham, String tenSanPham, String gia, int maLoai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.maLoai = maLoai;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) { this.maSanPham = maSanPham;
    }

    public String getTenSanPham() { return tenSanPham; }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }




}
