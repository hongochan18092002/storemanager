package fpt.edu.duannnhom.database;

import android.content.Context;

public class Data_SQLite {
    public static final String  INSERT_TAI_KHOAN = "Insert into TaiKhoan(MaTK, HoTen,MatKhau) values" +
            "('admin','nguyminhtram','123')," +
            "('tram','minhtu','122')," +
            "('hung','vietkhanh','125')";

    public static  final String INSERT_LOAI_SAN_PHAM = "insert into LoaiSanPham(tenLoai) values" +
            "('điện tử')," +
            "('ăn vặt')" ;
    public static  final String INSERT_SAN_PHAM = "insert into SanPham(tenSanPham, gia, maLoai) values" +
            "('iphoneX','1000','1')," +
            "('KimChi','2000','2')";
    public static  final String INSERT_CHI_NHANH = "insert into ChiNhanh(tenCN, tenNV, sdt, luong) values" +
            "('Hà nội','ho ngoc han','0355176940','1000'), " +
            "('Quảng bình','minh trâm','0355176930','1000')";
    public static  final String INSERT_HOA_DON = "insert into HoaDon(maCN, maSanPham, ngay, tienThue,tenNV) values" +
            "('1','1','01/11/2021','2000','tram')," +
            "('2','2','01/11/2021','5000','tu')" ;

}
