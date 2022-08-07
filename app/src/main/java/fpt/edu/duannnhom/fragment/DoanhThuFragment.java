package fpt.edu.duannnhom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.dao.HoaDonDao;
import fpt.edu.duannnhom.dao.ChiNhanhDAO;
import fpt.edu.duannnhom.dao.SanPhamDAO;


public class DoanhThuFragment extends Fragment {
TextView tvTongLuong,tvTongHangXuat,tvTongHangNhap, tvTienLai;
    static HoaDonDao dao;
    static SanPhamDAO spDao;
static ChiNhanhDAO nvDao;

AnyChartView a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);


        tvTongLuong = v.findViewById(R.id.tvTongLuong);
        tvTongHangNhap = v.findViewById(R.id.tvTongHangNhap);
        tvTongHangXuat = v.findViewById(R.id.tvTongHangXuat);
        tvTienLai = v.findViewById(R.id.tvTienLai);

        dao = new HoaDonDao(getActivity());
        spDao = new SanPhamDAO(getActivity());
        nvDao = new ChiNhanhDAO(getActivity());
        tvTongHangXuat.setText("Tổng Tiền thu được: " + dao.getTongHoaDon() + " VND");
        tvTongLuong.setText("Tổng Lương phải trả: " + nvDao.getSoTienLuong() + " VND");
        tvTongHangNhap.setText("Tổng tiền nhập hàng:  " + spDao.getTongTienHang() + " VND");
        int f = dao.getTongHoaDon() - (nvDao.getSoTienLuong()+ spDao.getTongTienHang());
        tvTienLai.setText("lãi được: " + f + "VND");
        a=v.findViewById(R.id.any_chart_view);
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Tổng lương Nhân viên", nvDao.getSoTienLuong()));
        data.add(new ValueDataEntry("Tổng tiền thu được", dao.getTongHoaDon()));
        data.add(new ValueDataEntry("Tổng tiền nhập hàng", spDao.getTongTienHang()));
        data.add(new ValueDataEntry("Lãi",f));

        pie.data(data);

        a.setChart(pie);
        return v;
    }
}