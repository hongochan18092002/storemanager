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

import java.util.List;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.dao.LoaiSanPhamDAO;
import fpt.edu.duannnhom.fragment.SanPhamFragment;
import fpt.edu.duannnhom.model.LoaiSanPham;
import fpt.edu.duannnhom.model.SanPham;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Context context;
    SanPhamFragment fragment;
    List<SanPham> list;
    TextView tvMaSanPham,tvTenSanPham,tvGia,tvLoai;
    ImageView imgDel,imgSP;
     LinearLayout lv;
    public SanPhamAdapter(@NonNull Context context, SanPhamFragment fragment, List<SanPham> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sanpham_item,null);
        }

        final SanPham item = list.get(position);
        if(item != null){

            imgSP=v.findViewById(R.id.imgSp);
            Animation ls = AnimationUtils.loadAnimation(context, R.anim.transition2);
            imgSP.setAnimation(ls);

            lv = v.findViewById(R.id.lvSP);
            Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
            lv.setAnimation(aa);



            LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(context);
            LoaiSanPham loaiSanPham = loaiSanPhamDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaSanPham = v.findViewById(R.id.tvMaSanPham);
            tvMaSanPham.setText("Mã sản phẩm: "+item.getMaSanPham());

            tvTenSanPham = v.findViewById(R.id.tvTenSanPham);
            tvTenSanPham.setText("Tên sản phẩm: "+item.getTenSanPham());
            tvGia = v.findViewById(R.id.tvGia);
            tvGia.setText("Giá : "+item.getGia());

            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại sản phẩm: "+loaiSanPham.getTenLoai());
 imgDel = v.findViewById(R.id.imgDeleteLSp);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xoa
fragment.xoa(String.valueOf(item.getMaSanPham()));
            }
        });

  return v;

    }
}
