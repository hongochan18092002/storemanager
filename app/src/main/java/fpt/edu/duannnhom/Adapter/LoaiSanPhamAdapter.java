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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.fragment.LoaiSanPhamFragment;
import fpt.edu.duannnhom.model.LoaiSanPham;

public class LoaiSanPhamAdapter extends ArrayAdapter<LoaiSanPham> {
    private Context context;
    LoaiSanPhamFragment fragment;
    private ArrayList<LoaiSanPham> lists;
    TextView tvMaSanPham, tvTenSanPham;
    ImageView imgDel;
    ImageView imgLoaiSP;
    LinearLayout lvLoaiSP;
//    ImageView imgLoaiSach;
    public LoaiSanPhamAdapter(@NonNull Context context, LoaiSanPhamFragment fragment, ArrayList<LoaiSanPham> lists) {
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
            v = inflater.inflate(R.layout.loai_san_pham_item,null);
        }
        final LoaiSanPham item = lists.get(position);
        if (item != null) {

            imgLoaiSP=v.findViewById(R.id.imgLoaiSp);
            Animation ls = AnimationUtils.loadAnimation(context, R.anim.transition2);
            imgLoaiSP.setAnimation(ls);

            lvLoaiSP = v.findViewById(R.id.cnssanpham);
            Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
            lvLoaiSP.setAnimation(aa);

            tvMaSanPham = v.findViewById(R.id.tvMaSanPham);
            tvMaSanPham.setText("Mã Sản Phẩm:  "+item.getMaLoai());
            tvTenSanPham = v.findViewById(R.id.tvTenSanPham);
            tvTenSanPham.setText("Loại sản Phẩm:  "+item.getTenLoai());


            imgDel = v.findViewById(R.id.imgDeleteSP);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xóa
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });
        return v;



    }
}
