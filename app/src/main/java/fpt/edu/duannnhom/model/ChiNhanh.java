package fpt.edu.duannnhom.model;

public class ChiNhanh {
    private int maCN;
    private String tenCN;
    private String tenNV;
    private String sdt;

    private String luong;

    public ChiNhanh() {
    }

    public ChiNhanh(int maCN, String tenCN, String tenNV, String sdt, String luong) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.tenNV = tenNV;
        this.sdt = sdt;

        this.luong = luong;
    }


    public int getMaCN() {
        return maCN;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }


    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }
}
