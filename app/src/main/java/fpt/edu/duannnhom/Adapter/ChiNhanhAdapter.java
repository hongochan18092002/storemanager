package fpt.edu.duannnhom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.fragment.ChiNhanhFragment;
import fpt.edu.duannnhom.model.ChiNhanh;

public class ChiNhanhAdapter extends ArrayAdapter<ChiNhanh> {
    private Context context;
    ChiNhanhFragment fragment;
    private ArrayList<ChiNhanh> lists;
    TextView tvMaCN, tvTenCN, tvTenNVi, tvSdt, tvLuong;
    ImageView imgDelNV,imgCHiNhanh;
    LinearLayout lvChiNhanh;

    public ChiNhanhAdapter(@NonNull Context context, ChiNhanhFragment fragment, ArrayList<ChiNhanh> lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.chi_nhanh_item,null);
        }
        final ChiNhanh item = lists.get(position);
        if (item != null) {

            imgCHiNhanh=v.findViewById(R.id.imgCHiNhanh);
            Animation ls = AnimationUtils.loadAnimation(context, R.anim.transition2);
            imgCHiNhanh.setAnimation(ls);

            lvChiNhanh = v.findViewById(R.id.lvChiNhanh);
            Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
            lvChiNhanh.setAnimation(aa);


            tvMaCN = v.findViewById(R.id.tvMaCNitem);
            tvMaCN.setText("M?? :  "+item.getMaCN());
            tvTenCN = v.findViewById(R.id.tvTenCNitem);
            tvTenCN.setText("Chi nh??nh:  "+item.getTenCN());
            tvTenNVi = v.findViewById(R.id.tvTenNVitem);
            tvTenNVi.setText("Nh??n vi??n qu???n l??:  "+item.getTenNV());
            tvSdt = v.findViewById(R.id.tvSdt);
            tvSdt.setText("S??T:+08"+item.getSdt());
            tvLuong = v.findViewById(R.id.tvLuong);
            tvLuong.setText("L????ng:  "+item.getLuong());

            imgDelNV = v.findViewById(R.id.imgDelNV);
        }
        imgDelNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //x??a
                fragment.xoa(String.valueOf(item.getMaCN()));
            }
        });
        return v;



    }
}
