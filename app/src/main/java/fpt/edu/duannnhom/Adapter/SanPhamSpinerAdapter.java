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
import fpt.edu.duannnhom.fragment.HoaDonFragment;
import fpt.edu.duannnhom.model.HoaDon;
import fpt.edu.duannnhom.model.SanPham;

public class SanPhamSpinerAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private ArrayList<SanPham> lists;
    TextView tvMaSP, tvTenSp;
    public SanPhamSpinerAdapter(@NonNull Context context, ArrayList<SanPham> lists) {
        super(context,0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if( v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.san_pham_item_spiner,null);
        }
        final SanPham item = lists.get(position);
        if(item != null){
            tvMaSP = v.findViewById(R.id.tvMaSanPhamSp);
            tvMaSP.setText(item.getMaSanPham() + ". ");

            tvTenSp = v.findViewById(R.id.tvTenSanPhamSp);
            tvTenSp.setText(item.getTenSanPham());
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if( v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.san_pham_item_spiner,null);
        }
        final SanPham item = lists.get(position);
        if(item != null){
            tvMaSP = v.findViewById(R.id.tvMaSanPhamSp);
            tvMaSP.setText(item.getMaSanPham() + ". ");

            tvTenSp = v.findViewById(R.id.tvTenSanPhamSp);
            tvTenSp.setText(item.getTenSanPham());
        }
        return v;
    }
}
