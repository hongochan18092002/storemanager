package fpt.edu.duannnhom.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fpt.edu.duannnhom.Adapter.TopNVAdapter;
import fpt.edu.duannnhom.R;
import fpt.edu.duannnhom.dao.HoaDonDao;
import fpt.edu.duannnhom.model.TopNV;


public class TopNVFragment extends Fragment {
    ListView lv;
    ArrayList<TopNV> list;
    TopNVAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View v = inflater.inflate(R.layout.fragment_top_n_v, container, false);
        lv = v.findViewById(R.id.lvTop);
        HoaDonDao hoaDonDao = new HoaDonDao(getActivity());
        list = (ArrayList<TopNV>) hoaDonDao.getTop();
        adapter = new TopNVAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return v;

    }
}