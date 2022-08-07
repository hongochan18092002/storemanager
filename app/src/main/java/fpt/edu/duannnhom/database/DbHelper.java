package fpt.edu.duannnhom.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
private  static  final  String DB_NAME = "PNLI";
private  static final  int DB_VERSION = 1;

static final String CREATE_TABLE_TAI_KHOAN =
      "  create table TaiKhoan (" +
    "maTK TEXT PRIMARY KEY, " +
            "hoTen   TEXT NOT NULL, " +
          "matKhau TEXT NOT NULL)";

    static final String CREATE_TABLE_LOAI_SAN_PHAM =
            "create table LoaiSanPham (" +
                    "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLoai TEXT NOT NULL)";

    static final String CREATE_TABLE_SAN_PHAM =
            "create table SanPham ( " +
                    "maSanPham INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenSanPham TEXT NOT NULL, " +
                    "gia INTEGER NOT NULL, " +
                    "maLoai INTEGER REFERENCES LoaiSanPham(maloai))";
    static final String CREATE_TABLE_CHI_NHANH =
            "create table ChiNhanh ( " +
                    "maCN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenCN TEXT NOT NULL, " +
                    "tenNV TEXT NOT NULL, " +
                    "sdt INTEGER NOT NULL, " +
                    "luong INTEGER NOT NULL)";
    static final String CREATE_TABLE_HOA_DON =
            "create table HoaDon ( " +
                    "maHoaDon     INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    "maCN      TEXT    REFERENCES ChiNhanh (maCN), " +
                    "maSanPham   INTEGER  REFERENCES SanPham (maSanPham), " +
                    "ngay     DATE     NOT NULL, "+
                    "tienThue INTEGER NOT NULL, "+
                    "tenNV TEXT NOT NULL)";



    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang thu thu
        db.execSQL(CREATE_TABLE_TAI_KHOAN);
        // thanh vein
        db.execSQL(CREATE_TABLE_LOAI_SAN_PHAM);
        db.execSQL(CREATE_TABLE_SAN_PHAM);
        db.execSQL(CREATE_TABLE_CHI_NHANH);
        db.execSQL(CREATE_TABLE_HOA_DON);
        //insert data
        db.execSQL(Data_SQLite.INSERT_TAI_KHOAN);
        db.execSQL(Data_SQLite.INSERT_LOAI_SAN_PHAM);
        db.execSQL(Data_SQLite.INSERT_SAN_PHAM);
        db.execSQL(Data_SQLite.INSERT_CHI_NHANH);
        db.execSQL(Data_SQLite.INSERT_HOA_DON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
String dropTableLoaiTaiKhoan = "drop table if exists TaiKhoan";
db.execSQL(dropTableLoaiTaiKhoan);
        String dropTableLoaiSanPham = "drop table if exists LoaiSanPham";
        db.execSQL(dropTableLoaiSanPham);
        String dropTableSanPham = "drop table if exists SanPham";
        db.execSQL(dropTableSanPham);
        String dropTableChiNhanh = "drop table if exists ChiNhanh";
        db.execSQL(dropTableChiNhanh);

        String dropTableHoaDon = "drop table if exists HoaDon";
        db.execSQL(dropTableHoaDon);
        onCreate(db);

    }
}
