package fpt.edu.duannnhom.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.dao.TaiKhoanDAO;
import fpt.edu.duannnhom.model.TaiKhoan;

/**

 */
public class DoiMatKhauFragment extends Fragment {
    TextInputEditText edPassOld, edPass,edRePass;
    Button btnSave, btnCancel;
    TaiKhoanDAO dao;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        dao = new TaiKhoanDAO(getActivity());
        edPassOld = v.findViewById(R.id.edPassOld);
        edPassOld = v.findViewById(R.id.edPassOld);
        edPass = v.findViewById(R.id.edPassChange);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSaveUserChange);
        btnCancel = v.findViewById(R.id.btnCancelUserChange);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPass.setText("");
                edPassOld.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if(validate()>0){
                    TaiKhoan taiKhoan = dao.getID(user);
                    taiKhoan.setMatKhau(edPass.getText().toString());
                    dao.updatePass(taiKhoan);
                    if(dao.updatePass(taiKhoan) > 0){
                        Toast.makeText(getActivity(),"Thay ?????i m???t kh???u th??nh c??ng",Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edPassOld.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Thay ?????i m???t kh???u kh??ng th??nh c??ng",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return v;

    }
    public  int validate(){
        int check = 1;
        if(edPassOld.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getContext(),"B???n ph???i nh???p ?????y ????? th??ng tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            //doc user pass
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(),"B???n nh???p sai m???t kh???u c??",Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(),"M???t kh???u kh??ng tr??ng kh???p",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }


        return check;
    }
}