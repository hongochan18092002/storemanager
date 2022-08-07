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
import fpt.edu.duannnhom.model.ChiNhanh;

public class ChiNhanhSpinerAdapter extends ArrayAdapter<ChiNhanh> {
    private Context context;
    private ArrayList<ChiNhanh> lists;
    TextView tvMaNV, tvTenCN;

    public ChiNhanhSpinerAdapter(@NonNull Context context, ArrayList<ChiNhanh> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if( v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spiner,null);
        }
        final ChiNhanh item = lists.get(position);
        if(item != null){
            tvMaNV = v.findViewById(R.id.tvMaCNSp);
            tvMaNV.setText(item.getMaCN() + ". ");

            tvTenCN = v.findViewById(R.id.tvTenCNSp);
            tvTenCN.setText(item.getTenCN());
        }

        return  v;


    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View  v = convertView;
        if( v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spiner,null);
        }
        final ChiNhanh item = lists.get(position);
        if(item != null) {
            tvMaNV = v.findViewById(R.id.tvMaCNSp);
            tvMaNV.setText(item.getMaCN() + ". ");

            tvTenCN = v.findViewById(R.id.tvTenCNSp);
            tvTenCN.setText(item.getTenCN());
        }
        return  v;
    }
}
