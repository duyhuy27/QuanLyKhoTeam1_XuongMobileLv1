package team1XuongMobile.fpoly.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import team1XuongMobile.fpoly.myapplication.Fragment.NhanVien.FilterSearchNhanVien;
import team1XuongMobile.fpoly.myapplication.Model.NhanVien;
import team1XuongMobile.fpoly.myapplication.R;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> implements Filterable {
    private Context context;
    public ArrayList<NhanVien> nhanVienArrayList,list;
    private nhanvienInterface listener;
    FilterSearchNhanVien filterSearchNhanVien;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> nhanVienArrayList, nhanvienInterface listener) {
        this.context = context;
        this.nhanVienArrayList = nhanVienArrayList;
        this.listener = listener;
        this.list = nhanVienArrayList;
    }

    @Override
    public Filter getFilter() {
        if(filterSearchNhanVien == null){
            filterSearchNhanVien = new FilterSearchNhanVien(list,this);
        }
        return filterSearchNhanVien;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenNhanVien, sdt;
        ImageView chitiet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenNhanVien = itemView.findViewById(R.id.tv_tenkhachhang_item_khachhang);
            sdt = itemView.findViewById(R.id.tv_sdt_item_khachhang);
            chitiet = itemView.findViewById(R.id.imgv_chitiet_itemnhanvien);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);
        return new ViewHolder(view);
    }

    public interface nhanvienInterface {
        void updateNVClick(String id);

        void deleteNVClick(String id);

        void chiTietNVClick(String id);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NhanVien nhanVien = nhanVienArrayList.get(position);
        holder.tenNhanVien.setText(nhanVienArrayList.get(position).getTen());
        holder.sdt.setText(nhanVienArrayList.get(position).getSdt());
        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.popup_menu_nhanvien, menuBuilder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionNV = new MenuPopupHelper(context, menuBuilder, v);
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.popup_menuNV_chitiet) {
                            listener.chiTietNVClick(nhanVien.getId_nv());
                            return true;
                        } else if (item.getItemId() == R.id.popup_menuNV_sua) {
                            listener.updateNVClick(nhanVien.getId_nv());
                            return true;
                        } else if (item.getItemId() == R.id.popup_menuNV_xoa) {
                            listener.deleteNVClick(nhanVien.getId_nv());
                            return true;
                        } else {
                            return false;
                        }

                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });
                optionNV.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return nhanVienArrayList.size();
    }


}
