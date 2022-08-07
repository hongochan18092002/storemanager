package fpt.edu.duannnhom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


import fpt.edu.duannnhom.dao.TaiKhoanDAO;

import fpt.edu.duannnhom.fragment.DoanhThuFragment;
import fpt.edu.duannnhom.fragment.DoiMatKhauFragment;
import fpt.edu.duannnhom.fragment.HoaDonFragment;
import fpt.edu.duannnhom.fragment.LoaiSanPhamFragment;
import fpt.edu.duannnhom.fragment.ChiNhanhFragment;
import fpt.edu.duannnhom.fragment.SanPhamFragment;
import fpt.edu.duannnhom.fragment.TopNVFragment;

import fpt.edu.duannnhom.fragment.XinChaoFragment;
import fpt.edu.duannnhom.model.TaiKhoan;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawer;
Toolbar toolbar;
View mHeaderView;
TextView edUser;

TaiKhoanDAO taiKhoanDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///tao dâtbese
//        DemoDB demoDB = new DemoDB(getApplicationContext());
//        demoDB.thanhVien();

        drawer=findViewById(R.id.drawer_layout);

        toolbar=findViewById(R.id.toolbar1);

        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager = getSupportFragmentManager();
        HoaDonFragment hoaDonFragment = new HoaDonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,hoaDonFragment)
                .commit();

        NavigationView nv = findViewById(R.id.nvView);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        taiKhoanDAO = new TaiKhoanDAO(this);
        TaiKhoan thu = taiKhoanDAO.getID(user);
        String username = thu.getHoTen();
        edUser.setText("Welcome: "+username );
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()){
                    case R.id.loaiSanpham:

                        Toast.makeText(getApplicationContext(),"Phân loại sản phẩm",Toast.LENGTH_SHORT).show();
                        LoaiSanPhamFragment loaiSachFragment = new LoaiSanPhamFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,loaiSachFragment)
                                .commit();

                        setTitle("Thêm loại sản phẩm");


                        break;
                    case R.id.sanPham:

                        Toast.makeText(getApplicationContext(),"Quản lý Sản Phẩm",Toast.LENGTH_SHORT).show();
                        SanPhamFragment sanPhamFragment = new SanPhamFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,sanPhamFragment)
                                .commit();
                        setTitle("Quản lý sản phẩm");


                        break;
                    case R.id.chiNhanh:
                        Toast.makeText(getApplicationContext(),"Chi nhánh và nhân viên ",Toast.LENGTH_SHORT).show();
                        ChiNhanhFragment chiNhanhFragment = new ChiNhanhFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent, chiNhanhFragment)
                                .commit();
                        setTitle("Quản lý chi nhánh");



                        break;
                    case R.id.hangXuat:
                        Toast.makeText(getApplicationContext(),"Hàn xuất ",Toast.LENGTH_SHORT).show();
                        HoaDonFragment hoaDonFragment = new HoaDonFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,hoaDonFragment)
                                .commit();
                        setTitle("Hàng xuất");
                        break;
                    case R.id.sub_Top:
                        Toast.makeText(getApplicationContext(),"Chi Nhánh bán chạy",Toast.LENGTH_SHORT).show();
                        TopNVFragment topNVFragment = new TopNVFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,topNVFragment)
                                .commit();
                        setTitle("Chi nhánh bán chạy");


                        break;
                    case R.id.sub_DoanhThu:
                        Toast.makeText(getApplicationContext(),"Doanh thu",Toast.LENGTH_SHORT).show();
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,doanhThuFragment)
                                .commit();
                        setTitle("Doanh thu");


                        break;

                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        Toast.makeText(getApplicationContext(),"Thay đổi mật khẩu",Toast.LENGTH_SHORT).show();
                        DoiMatKhauFragment doiMatKhauFragment = new DoiMatKhauFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,doiMatKhauFragment)
                                .commit();
                        break;
                    case R.id.sub_Logout:
             startActivity(new Intent(getApplicationContext(),LoginActivity.class));
             finish();
                        Toast.makeText(getApplicationContext(),"Xin chào và hẹn gặp lại",Toast.LENGTH_SHORT).show();
                        break;


                }

                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}