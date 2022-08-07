package fpt.edu.duannnhom.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.database.DbHelper;
import fpt.edu.duannnhom.model.HoaDon;
import fpt.edu.duannnhom.model.ChiNhanh;
import fpt.edu.duannnhom.model.TopNV;

public class HoaDonDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDonDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon obj) {

        ContentValues values = new ContentValues();
        values.put("maCN", obj.getMaTV());
        values.put("maSanPham", obj.getMaSanPham());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("tenNV", obj.getNgaySinnh());
        return db.insert("HoaDon", null, values);

    }

    public int update(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("maCN", obj.getMaTV());
        values.put("maSanPham", obj.getMaSanPham());
        values.put("ngay", sdf.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("tenNV", obj.getNgaySinnh());
        return db.update("HoaDon", values, "maHoaDon=?", new String[]{String.valueOf(obj.getMaHoaDon())});
    }

    public int delete(String id) {
        return db.delete("HoaDon", "maHoaDon=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHoaDon(Integer.parseInt(c.getString(0)));
            obj.setMaTV(Integer.parseInt(c.getString(1)));
            obj.setMaSanPham(Integer.parseInt(c.getString(2)));
            try {
                obj.setNgay(sdf.parse(c.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setTienThue(Integer.parseInt(c.getString(4)));
            obj.setNgaySinh(c.getString(5));
            list.add(obj);
        }
        //sửa phần getdata này lại
        return list;
    }
    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }
    public HoaDon getID(String id){
        String sql = "SELECT * FROM HoaDon WHERE maHoaDon=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public List<TopNV> getTop(){
        String sqlTop = "SELECT maCN, count(maCN) as soLuong FROM HoaDon GROUP BY maCN ORDER BY soLuong DESC LIMIT 20";
        List<TopNV> list = new ArrayList<TopNV>();
        ChiNhanhDAO chiNhanhDAO = new ChiNhanhDAO(context);
        Cursor c = db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            TopNV topNV = new TopNV();
            ChiNhanh chiNhanh = chiNhanhDAO.getID(c.getString(0));
            topNV.setTenNV(chiNhanh.getTenNV());
            topNV.setSoLuong(Integer.parseInt(c.getString(1)));

            list.add(topNV);
        }
        return list;
    }

    public int getTongHoaDon() {
        String s = "SELECT SUM(tienThue) FROM HoaDon ";
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

