package fpt.edu.duannnhom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.dao.HoaDonDao;
import fpt.edu.duannnhom.dao.ChiNhanhDAO;
import fpt.edu.duannnhom.dao.SanPhamDAO;


public class XinChaoFragment extends Fragment {

    TextView tvTongLuong,tvTongHangXuat,tvTongHangNhap, tvTienLai;
    static HoaDonDao dao;
    static SanPhamDAO spDao;
    static ChiNhanhDAO nvDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_xin_chao, container, false);

        tvTongLuong = v.findViewById(R.id.tvTongLuongXC);
        tvTongHangNhap = v.findViewById(R.id.tvTongNhapXC);
        tvTongHangXuat = v.findViewById(R.id.tvTongXuatXC);
        tvTienLai = v.findViewById(R.id.tvTienLaiXC);
        dao = new HoaDonDao(getActivity());
        spDao = new SanPhamDAO(getActivity());
        nvDao = new ChiNhanhDAO(getActivity());

        tvTongHangXuat.setText("" + dao.getTongHoaDon() + " VND");
        tvTongLuong.setText("" + nvDao.getSoTienLuong() + " VND");
        tvTongHangNhap.setText(" " + spDao.getTongTienHang() + " VND");
        int f = dao.getTongHoaDon() - (nvDao.getSoTienLuong()+ spDao.getTongTienHang());
        tvTienLai.setText("" + f + "VND");
    return v;
    }
}