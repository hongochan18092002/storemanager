package fpt.edu.duannnhom.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


import fpt.edu.duannnhom.Adapter.LoaiSanPhamAdapter;
import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.dao.LoaiSanPhamDAO;
import fpt.edu.duannnhom.model.LoaiSanPham;


public class LoaiSanPhamFragment extends Fragment {
ListView lv;
ArrayList<LoaiSanPham>list;
FloatingActionButton fab;
Dialog dialog;
EditText edMaSanPham,edTenSanPham;
Button btnSave,btnCancel;
static LoaiSanPhamDAO dao;
LoaiSanPhamAdapter adapter;
LoaiSanPham item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        lv= v.findViewById(R.id.lvLoaiSanPham);
        fab =v.findViewById(R.id.fab);
        dao = new LoaiSanPhamDAO(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        openDialog(getActivity(),0);
    }
        });
lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        item = list.get(position);
        openDialog(getActivity(),1);//1 la se update

        return false;
    }
});
    return v;
    }

    public void  openDialog(final Context context,final  int type){
    dialog = new Dialog(context);
    dialog.setContentView(R.layout.loai_san_pham_dialog);
    edMaSanPham = dialog.findViewById(R.id.edMaSanPham);
    edTenSanPham = dialog.findViewById(R.id.edTenLoaiSanPham);
    btnCancel = dialog.findViewById(R.id.btnCancelSP);
    btnSave = dialog.findViewById(R.id.btnSaveSP);
    //kiem tra
        edMaSanPham.setEnabled(false);
        if(type != 0){
            edMaSanPham.setText(String.valueOf(item.getMaLoai()));
            edTenSanPham.setText(item.getTenLoai());
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
                item = new LoaiSanPham();
                item.setTenLoai(edTenSanPham.getText().toString());
                if(validate() >0){
                    if(type ==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"th??m th??nh c??ng",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"th??m kh??ng  th??nh c??ng",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaLoai(Integer.parseInt(edMaSanPham.getText().toString()));
                        if(dao.update(item)>0){
                            Toast.makeText(context,"S???a th??nh c??ng",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"S???a kh??ng th??nh c??ng",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id){
//alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("B???n c?? mu???n x??a kh??ng?");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "C??",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dao.delete(Id);
                        //cap nhat lv
                        capNhatLV();
                        dialog.cancel();

                    }
                });
                 builder.setNegativeButton(
                "kh??ng",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alert = builder.create();
        builder.show();
    }


    void capNhatLV(){
        list = (ArrayList<LoaiSanPham>)dao.getAll();
        adapter= new LoaiSanPhamAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        if(edTenSanPham.getText().length()==0){
            Toast.makeText(getContext(),"B???n p???i nh???p ?????y ????? th??ng tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    }
