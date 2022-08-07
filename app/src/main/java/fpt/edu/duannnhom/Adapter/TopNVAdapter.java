package fpt.edu.duannnhom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.fragment.TopNVFragment;
import fpt.edu.duannnhom.model.TopNV;

public class TopNVAdapter extends ArrayAdapter<TopNV> {
    private Context context;
    TopNVFragment topNVFragment;
    private ArrayList<TopNV> list;
TextView tvTenNV,tvSoLuong,tvXepLoai,tvThuong;
LinearLayout lvtop;

    public TopNVAdapter(@NonNull Context context, TopNVFragment topNVFragment, ArrayList<TopNV> list) {
        super(context, 0,list);
        this.context = context;
        this.topNVFragment = topNVFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater =(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.topnv_item,null);
        }
        final TopNV item  = list.get(position);
        if(item != null){

            lvtop = v.findViewById(R.id.lvTop);
            Animation aa = AnimationUtils.loadAnimation(context, R.anim.transition);
            lvtop.setAnimation(aa);

            tvTenNV= v.findViewById(R.id.tvTenCNTop);
            tvTenNV.setText("Tên chi nhánh: "+item.getTenNV());

            tvSoLuong= v.findViewById(R.id.tvSoLuongTop);
            tvSoLuong.setText("Số lượng hàng bán được: "+item.getSoLuong());

            tvXepLoai = v.findViewById(R.id.tvXepLoai);
            tvThuong = v.findViewById(R.id.tvThuong);
            if(item.getSoLuong()>=3){
                tvXepLoai.setTextColor(Color.RED);
                tvXepLoai.setText("Nhân viên : Được lên chức ");
                tvThuong.setText("Thưởng: có");
            }
            else if(item.getSoLuong()>=2) {
                tvXepLoai.setText("Xếp loại: Nhân viên cần cù");
                tvThuong.setText("Thưởng: có");
            }else {
                tvXepLoai.setText("Xếp loại: Nhân viên bình thường");
                tvThuong.setText("Thưởng: Không");
            }
        }
        return v;

    }

}


