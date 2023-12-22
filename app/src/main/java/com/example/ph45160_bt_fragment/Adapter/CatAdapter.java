package com.example.ph45160_bt_fragment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import com.example.ph45160_bt_fragment.DAO.CatDAO;
import com.example.ph45160_bt_fragment.DTO.CatDTO;
import com.example.ph45160_bt_fragment.R;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter {

    ArrayList<CatDTO> listCat;
    Context context;

    public CatAdapter(ArrayList<CatDTO> listCat, Context context) {
        this.listCat = listCat;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int position) {
        return listCat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listCat.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row;
        if (view != null) {
            row = view;
        } else {
            row = View.inflate(context, R.layout.item_cat, null);
        }

        // ra khỏi cấu trúc IF ELS mớ ánh xạ
        CatDTO objCat = listCat.get(position);
        TextView txt_TenTheLoai = row.findViewById(R.id.txt_TenTheLoai);
        TextView txt_SuaTheLoai = row.findViewById(R.id.txt_SuaTheLoai);


        txt_TenTheLoai.setText(objCat.getName());

        CatDAO catDAO = new CatDAO(context);

        txt_SuaTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_sua, null);
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                dialog.show();

                EditText edt_suaten = view1.findViewById(R.id.edt_suaten);
                Button btn_hoantat = view1.findViewById(R.id.btn_hoantat);

                btn_hoantat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edt_suaten.getText().toString();

                        objCat.setName(name);
                        int kq = catDAO.updateRow(objCat);
                        if (kq > 0) {
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

            }
        });



        return row;
    }
}
