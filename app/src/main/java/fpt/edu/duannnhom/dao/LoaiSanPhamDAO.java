package fpt.edu.duannnhom.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.database.DbHelper;
import fpt.edu.duannnhom.model.LoaiSanPham;

public class LoaiSanPhamDAO {
    private SQLiteDatabase db;

    public LoaiSanPhamDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(LoaiSanPham obj){
        ContentValues values = new ContentValues();
        //value.put("maLoai",obj.maLoai);
        values.put("tenLoai",obj.getTenLoai());
        return db.insert("LoaiSanPham",null,values);
    }
    public  int update(LoaiSanPham obj){
        ContentValues values = new ContentValues();
        //values.put("maLoai",obj.maLoai);
      values.put("tenLoai",obj.getTenLoai());
        return db.update("LoaiSanPham",values,"maLoai=?",new String[]{String.valueOf(obj.getMaLoai())});

    }
    public int delete(String id){
        return db.delete("LoaiSanPham","maLoai=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<LoaiSanPham> getData(String sql, String...selectionArgs){
        List<LoaiSanPham> list = new ArrayList<LoaiSanPham>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiSanPham obj = new LoaiSanPham();
         obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
         obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);

        }
        return list;
    }
    //get tat ca data
    public List<LoaiSanPham> getAll(){
        String sql = "SELECT * FROM LoaiSanPham";
        return getData(sql);
    }
    ///theo id
    public LoaiSanPham getID(String id){
        String sql = "SELECT * FROM LoaiSanPham WHERE maLoai=?";
        List<LoaiSanPham> list = getData(sql, id);
        return list.get(0);
    }
}
