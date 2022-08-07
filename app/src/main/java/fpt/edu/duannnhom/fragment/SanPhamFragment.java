package fpt.edu.duannnhom.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.Adapter.LoaiSanPhamSpinnerAdapter;
import fpt.edu.duannnhom.Adapter.SanPhamAdapter;
import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.dao.LoaiSanPhamDAO;
import fpt.edu.duannnhom.dao.SanPhamDAO;
import fpt.edu.duannnhom.model.LoaiSanPham;
import fpt.edu.duannnhom.model.SanPham;


public class SanPhamFragment extends Fragment {
ListView lvSanPham;
SanPhamDAO sanPhamDAO;
    TextView tvTongTienHang;


FloatingActionButton fab;
Dialog dialog;
EditText edMaSanPham, edTenSanPham,edGia;
Spinner spinner;
Button btnSave,btnCancel;

SanPhamAdapter adapter;
SanPham item;
List<SanPham> list;

LoaiSanPhamSpinnerAdapter spinnerAdapter;
ArrayList<LoaiSanPham> listLoaiSanPham;
LoaiSanPhamDAO loaiSanPhamDAO;
LoaiSanPham loaiSanPham;
int maLoaiSanPham,position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sanpham, container, false);
        tvTongTienHang = v.findViewById(R.id.tvTongTienSanPham);
lvSanPham = v.findViewById(R.id.lvSanPham);
sanPhamDAO = new SanPhamDAO(getActivity());
capNhatLv();

fab = v.findViewById(R.id.fab);
fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    openDialog(getActivity(),0);
    }
});
lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        item = list.get(position);
        openDialog(getActivity(),1);
        return false;
    }
});
        return  v;

    }
    void capNhatLv(){
        tongTien();
        list = (List<SanPham>) sanPhamDAO.getAll();
        adapter = new SanPhamAdapter(getActivity(),this, list);
        lvSanPham.setAdapter(adapter);
    }
    public void xoa(final String Id){
        //dung alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("DELETE");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
          sanPhamDAO.delete(Id);
          capNhatLv();

          dialog.cancel();
                    }
                }
        );
        builder.setNegativeButton(
                "no",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alert = builder.create();
        builder.show();
    }
    protected void openDialog(final Context context, final int type){
        //custom
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sanpham_dialog);
        edMaSanPham = dialog.findViewById(R.id.edMaSanPham);
        edTenSanPham = dialog.findViewById(R.id.edTenSanPham);
        edGia = dialog.findViewById(R.id.edGia);
        spinner = dialog.findViewById(R.id.spLoaiSanPham);
        btnCancel = dialog.findViewById(R.id.btnCancelSp);
        btnSave = dialog.findViewById(R.id.btnSaveSp);

        listLoaiSanPham = new ArrayList<LoaiSanPham>();
        loaiSanPhamDAO = new LoaiSanPhamDAO(context);
        listLoaiSanPham = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();

        spinnerAdapter = new LoaiSanPhamSpinnerAdapter(context,listLoaiSanPham);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSanPham = listLoaiSanPham.get(position).getMaLoai();
//                Toast.makeText(context, "chọn "+listLoaiSanPham.get(position).getTenLoai(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //kiem tra type insert 1 hay o
        edMaSanPham.setEnabled(false);
        if(type != 0){
            edMaSanPham.setText(String.valueOf(item.getMaSanPham()));
            edTenSanPham.setText(item.getTenSanPham());
            edGia.setText(String.valueOf(item.getGia()));//thử og=log tên sp thủ
            for (int i = 0; i< listLoaiSanPham.size();i++)
                if(item.getMaLoai() == (listLoaiSanPham.get(i).getMaLoai())){
                    position = i;
                }
            Log.i("demo","posSanPham "+position);
                spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new SanPham();
                item.setTenSanPham(edTenSanPham.getText().toString());
                item.setGia((edGia.getText().toString()));
                item.setMaLoai(maLoaiSanPham);
                if (validate() > 0) {
                    if (type == 0) {
                        if (sanPhamDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //type=1
                        item.setMaSanPham(Integer.parseInt(edMaSanPham.getText().toString()));
                        if (sanPhamDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    capNhatLv();
                    dialog.dismiss();
                }
            }

        });
        dialog.show();

    }
    public int  validate(){
        int check =1;
        if(edTenSanPham.getText().length()==0||edGia.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        check = -1;
        }
        return check;
    }
    void tongTien(){
        sanPhamDAO = new SanPhamDAO(getActivity());
        tvTongTienHang.setText("Tổng tiền hàng: " + sanPhamDAO.getTongTienHang() + " VND");
    }
}