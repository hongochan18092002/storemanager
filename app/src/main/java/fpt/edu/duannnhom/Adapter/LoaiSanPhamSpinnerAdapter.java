package fpt.edu.duannnhom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.model.LoaiSanPham;


public class LoaiSanPhamSpinnerAdapter extends ArrayAdapter<LoaiSanPham> {
    Context context;
    ArrayList<LoaiSanPham> lists;
    TextView tvMaLoaiSanPham,tvTenLoaiSanPham;

    public LoaiSanPhamSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSanPham> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater =(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_san_pham_item_spinner, null);

        }
        final LoaiSanPham item = lists.get(position);
        if(item != null){
            tvMaLoaiSanPham = v.findViewById(R.id.tvMaLoaiSanPham);
            tvMaLoaiSanPham.setText(item.getMaLoai() + "");

            tvTenLoaiSanPham = v.findViewById(R.id.tvTenLoaiSanPham);
            tvTenLoaiSanPham.setText(item.getTenLoai());
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater =(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_san_pham_item_spinner, null);

        }
        final LoaiSanPham item = lists.get(position);
        if(item != null){
            tvMaLoaiSanPham = v.findViewById(R.id.tvMaLoaiSanPham);
            tvMaLoaiSanPham.setText(item.getMaLoai() + "");

            tvTenLoaiSanPham = v.findViewById(R.id.tvTenLoaiSanPham);
            tvTenLoaiSanPham.setText(item.getTenLoai());
        }

        return v;
    }
}
