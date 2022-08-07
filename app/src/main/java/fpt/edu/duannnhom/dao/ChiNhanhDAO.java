package fpt.edu.duannnhom.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.database.DbHelper;
import fpt.edu.duannnhom.model.ChiNhanh;

public class ChiNhanhDAO {
    private SQLiteDatabase db;
    private Context context;
    public ChiNhanhDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ChiNhanh obj) {
        ContentValues values = new ContentValues();
        values.put("tenCN", obj.getTenCN());
        values.put("tenNV", obj.getTenNV());
        values.put("sdt", obj.getSdt());
        values.put("luong", obj.getLuong());
        return db.insert("ChiNhanh", null, values);
    }

    public int update(ChiNhanh obj) {
        ContentValues values = new ContentValues();
        values.put("tenCN", obj.getTenCN());
        values.put("tenNV", obj.getTenNV());
        values.put("sdt", obj.getSdt());
        values.put("luong", obj.getLuong());
        return db.update("ChiNhanh", values, "maCN=?", new String[]{String.valueOf(obj.getMaCN())});
    }

    public int delete(String id) {
        return db.delete("ChiNhanh", "maCN=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ChiNhanh> getData(String sql, String... selectionArgs) {
        List<ChiNhanh> list = new ArrayList<ChiNhanh>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            ChiNhanh obj = new ChiNhanh();
            obj.setMaCN(Integer.parseInt(c.getString(c.getColumnIndex("maCN"))));
            obj.setTenCN(c.getString(c.getColumnIndex("tenCN")));
            obj.setTenNV(c.getString(c.getColumnIndex("tenNV")));
            obj.setSdt((c.getString(c.getColumnIndex("sdt"))));
            obj.setLuong((c.getString(c.getColumnIndex("luong"))));
            list.add(obj);
        }
        return list;
    }

    //get tat ca data
    public List<ChiNhanh> getAll() {
        String sql = "SELECT * FROM ChiNhanh";
        return getData(sql);
    }

    ///theo id
    public ChiNhanh getID(String id) {
        String sql = "SELECT * FROM ChiNhanh WHERE maCN=?";
        List<ChiNhanh> list = getData(sql, id);
        return list.get(0);
    }

    public int getSoTienLuong() {
        String s = "SELECT SUM(luong) FROM ChiNhanh ";
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

