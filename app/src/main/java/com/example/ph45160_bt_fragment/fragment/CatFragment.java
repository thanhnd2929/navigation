package com.example.ph45160_bt_fragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ph45160_bt_fragment.Adapter.CatAdapter;
import com.example.ph45160_bt_fragment.DAO.CatDAO;
import com.example.ph45160_bt_fragment.DTO.CatDTO;
import com.example.ph45160_bt_fragment.R;

import java.util.ArrayList;

public class CatFragment extends Fragment {

    EditText edt_tentheloai;
    Button btn_them;
    ListView lv_theloai;
    ArrayList<CatDTO> listCat;
    CatDAO catDAO;
    CatAdapter catAdapter;

    TextView txt_SuaTheLoai;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.cat_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_tentheloai = view.findViewById(R.id.edt_tentheloai);
        btn_them = view.findViewById(R.id.btn_them);
        lv_theloai = view.findViewById(R.id.lv_theLoai);

        catDAO  = new CatDAO(getContext());
        listCat = catDAO.getList();

        catAdapter = new CatAdapter(listCat, getContext());
        lv_theloai.setAdapter(catAdapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_tentheloai.getText().toString();
                CatDTO catDTO = new CatDTO(name);
                int id_moi = catDAO.addRow(catDTO);
                if (id_moi>0) {
                    listCat.clear();
                    listCat.addAll(catDAO.getList());
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    edt_tentheloai.setText("");
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv_theloai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CatDTO objCat = listCat.get(position);
                int check = catDAO.deleteRow(objCat);
                if (check > 0) {
                    listCat.remove(objCat);
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Xoá thất bại", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

    }
}
