package fpt.edu.duannnhom.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.database.DbHelper;
import fpt.edu.duannnhom.model.SanPham;

public class SanPhamDAO {
    private SQLiteDatabase db;
    public SanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(SanPham obj){
        ContentValues values = new ContentValues();
        values.put("tenSanPham",obj.getTenSanPham());
        values.put("gia",obj.getGia());
        values.put("maLoai",obj.getMaLoai());
        return db.insert("SanPham",null,values);
    }
    public int update(SanPham obj){
        ContentValues values = new ContentValues();
        values.put("tenSanPham",obj.getTenSanPham());
        values.put("gia",obj.getGia());
        values.put("maLoai",obj.getMaLoai());
        return db.update("SanPham",values,"maSanPham=?",new String[]{String.valueOf(obj.getMaSanPham())});
    }
    public  int delete(String id){
        return db.delete("SanPham","maSanPham=?",new String[]{id});
    }
    //get data nhieu tham so
    @SuppressLint("Range")
    public List<SanPham> getData(String sql, String...selectionArgs){
        List<SanPham> list = new ArrayList<SanPham>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            SanPham obj = new SanPham();
            obj.setMaSanPham(Integer.parseInt(c.getString(c.getColumnIndex("maSanPham"))));
            obj.setTenSanPham(c.getString(c.getColumnIndex("tenSanPham")));
            obj.setGia((c.getString(c.getColumnIndex("gia"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));

            list.add(obj);

        }
        return list;
    }
    //get tat ca data
    public List<SanPham> getAll(){
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }
    ///theo id
    public SanPham getID(String id){
        String sql = "SELECT * FROM SanPham WHERE maSanPham=?";
        List<SanPham> list = getData(sql, id);
        return list.get(0);

    }
    public int getTongTienHang() {
        String s = "SELECT SUM(gia) FROM SanPham ";
        List<Integer> list = new ArrayList<Integer>();

        Cursor c = db.rawQuery(s, null);

        while (c.moveToNext()) {
            try{
                list.add(Integer.parseInt(c.getString(0)));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }


}
