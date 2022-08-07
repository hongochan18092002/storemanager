package fpt.edu.duannnhom.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.database.DbHelper;
import fpt.edu.duannnhom.model.TaiKhoan;

public class TaiKhoanDAO {
    private SQLiteDatabase db;

    public TaiKhoanDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(TaiKhoan obj){
        ContentValues values = new ContentValues();
        values.put("maTK",obj.getMaTK());
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return db.insert("TaiKhoan",null,values);
    }
    public int updatePass(TaiKhoan obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("matKhau",obj.getMatKhau());
        return db.update("TaiKhoan",values,"maTK=?",new String[]{obj.getMaTK()});

    }
    public int delete(String id){
        return  db.delete("TaiKhoan","maTK=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<TaiKhoan> getData(String sql, String...selectionArgs){
        List<TaiKhoan> list = new ArrayList<TaiKhoan>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            TaiKhoan obj = new TaiKhoan();
            obj.setMaTK((c.getString(c.getColumnIndex("maTK"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));

            list.add(obj);

        }
        return list;
    }
    //getTat cadata
    public List<TaiKhoan> getAll(){
        String sql = "SELECT * FROM TaiKhoan";
        return getData(sql);
    }
    //theo id
    public TaiKhoan getID(String id){
        String sql = "SELECT * FROM TaiKhoan  WHERE maTK=?";
        List<TaiKhoan> list = getData(sql,id);
        return list.get(0);
    }
    //check login
    public int checkLogin(String id,String password){
        String sql = "SELECT * FROM TaiKhoan  WHERE maTK=? AND matKhau=?";
        List<TaiKhoan> list = getData(sql,id,password);
        if(list.size() == 0){
            return -1;
        }
        return 1;
    }
}
