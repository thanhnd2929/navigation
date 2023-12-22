package com.example.ph45160_bt_fragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph45160_bt_fragment.Adapter.ProductAdapter;
import com.example.ph45160_bt_fragment.Adapter.SpinnerAdapter;
import com.example.ph45160_bt_fragment.DAO.CatDAO;
import com.example.ph45160_bt_fragment.DAO.ProductDAO;
import com.example.ph45160_bt_fragment.DTO.CatDTO;
import com.example.ph45160_bt_fragment.DTO.ProductDTO;
import com.example.ph45160_bt_fragment.R;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    EditText edt_TenSanPham, edt_GiaSanPham;

    Spinner spinner_cat;

    ProductDTO productDTO;

    ProductDAO productDAO;

    RecyclerView rv_product;

    ProductAdapter productAdapter;

    Button btn_themSP;

    ArrayList<ProductDTO> listProduct;
    ArrayList<CatDTO> listCat;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        edt_TenSanPham = view.findViewById(R.id.edt_TenSanPham);
        edt_GiaSanPham = view.findViewById(R.id.edt_GiaSanPham);
        rv_product = view.findViewById(R.id.rv_product);
        btn_themSP = view.findViewById(R.id.btn_themSP);
        spinner_cat = view.findViewById(R.id.spinner_cat);

        productDAO = new ProductDAO(getContext());
        listProduct = productDAO.getAll();

        CatDAO catDAO = new CatDAO(getContext());
        listCat = catDAO.getList();

        productAdapter = new ProductAdapter(getContext(), listProduct, listCat);
        rv_product.setAdapter(productAdapter);


        //dua du lieu len spiner


        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(catDAO.getList());
        spinner_cat.setAdapter(spinnerAdapter);

        btn_themSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_TenSanPham.getText().toString();
                int price = Integer.parseInt(edt_GiaSanPham.getText().toString());

                CatDTO selectedCat = (CatDTO) spinner_cat.getSelectedItem();
                int id_cat = selectedCat.getId();



                int id_moi = productDAO.addRow(new ProductDTO(name, price, id_cat));
                if (id_moi>0) {
                    listProduct.clear();
                    listProduct.addAll(productDAO.getAll());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}
