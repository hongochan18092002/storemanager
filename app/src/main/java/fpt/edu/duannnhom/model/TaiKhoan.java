package fpt.edu.duannnhom.model;

public class TaiKhoan {
    private String maTK;
    private String hoTen;
    private String matKhau;

    public TaiKhoan() {
    }

    public TaiKhoan(String maTK, String hoTen, String matKhau) {
        this.maTK = maTK;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maTK='" + maTK + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", matKhau='" + matKhau + '\'' +
                '}';
    }
}
