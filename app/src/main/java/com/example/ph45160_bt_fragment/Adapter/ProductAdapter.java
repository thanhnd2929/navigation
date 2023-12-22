package com.example.ph45160_bt_fragment.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph45160_bt_fragment.DAO.CatDAO;
import com.example.ph45160_bt_fragment.DAO.ProductDAO;
import com.example.ph45160_bt_fragment.DTO.CatDTO;
import com.example.ph45160_bt_fragment.DTO.ProductDTO;
import com.example.ph45160_bt_fragment.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductDTO> listProduct;

    ArrayList<CatDTO> listCat;

    public ProductAdapter(Context context, ArrayList<ProductDTO> listProduct, ArrayList<CatDTO> listCat) {
        this.context = context;
        this.listProduct = listProduct;
        this.listCat = listCat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductDTO productDTO = listProduct.get(position);
        holder.txt_ID_Product.setText( "ID Product: " + productDTO.getId_prduct()+"");
        holder.txt_Name_Product.setText("Name Product: " +productDTO.getProduct_name());
        holder.txt_Price_Product.setText("Price Product: " +productDTO.getPrice()+"");
        holder.txt_ID_Cat.setText("ID Cat: " +productDTO.getId_cat()+"");

        String catName = getCatName(productDTO.getId_cat());


        holder.txt_Name_Cat.setText("Name Cat: " +catName);

        holder.btn_Xoa_SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDAO productDAO = new ProductDAO(context);
                if (productDAO.deleteRow(productDTO) > 0) {
                    listProduct.remove(productDTO);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Delete OK", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.btn_Sua_SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_update_product, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                ProductDAO productDAO = new ProductDAO(context);
                CatDAO catDAO = new CatDAO(context);
                EditText edtUD_name = view.findViewById(R.id.edtUD_name);
                EditText edtUD_price = view.findViewById(R.id.edtUD_price);
                Spinner spinnerUD_id_cat = view.findViewById(R.id.spinnerUD_id_cat);

                Button btnUpdateprdNow = view.findViewById(R.id.btnUpdateprdNow);
                Button btnCancelUDprd = view.findViewById(R.id.btnCancelUDprd);


                edtUD_name.setText(listProduct.get(position).getProduct_name());
                edtUD_price.setText(listProduct.get(position).getPrice() + "");

                SpinnerAdapter spinnerAdapter = new SpinnerAdapter(catDAO.getList());
                spinnerUD_id_cat.setAdapter(spinnerAdapter);

                btnUpdateprdNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = edtUD_name.getText().toString();
                        int price = Integer.parseInt(edtUD_price.getText().toString());
                        int spinner_id_cat = (int) spinnerUD_id_cat.getSelectedItemId();
                        productDTO.setProduct_name(name);
                        productDTO.setPrice(price);
                        productDTO.setId_cat(spinner_id_cat);


                        int kq = productDAO.updateRow(productDTO);
                        if (kq > 0) {
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "update OK", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(context, "update false", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


                btnCancelUDprd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });



    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_ID_Product, txt_Name_Product, txt_Price_Product,
                txt_ID_Cat, txt_Name_Cat;

        Button btn_Sua_SP, btn_Xoa_SP;

        ProductAdapter productAdapter;
        ProductDAO productDAO;
        ProductDTO productDTO;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productAdapter = new ProductAdapter(context, listProduct, listCat);
            productDAO = new ProductDAO(context);
            productDTO = new ProductDTO();


            txt_ID_Product = itemView.findViewById(R.id.txt_ID_Product);
            txt_Name_Product = itemView.findViewById(R.id.txt_Name_Product);
            txt_Price_Product = itemView.findViewById(R.id.txt_Price_Product);
            txt_ID_Cat = itemView.findViewById(R.id.txt_ID_Cat);
            txt_Name_Cat = itemView.findViewById(R.id.txt_Name_Cat);
            btn_Sua_SP = itemView.findViewById(R.id.btn_Sua_SP);
            btn_Xoa_SP = itemView.findViewById(R.id.btn_Xoa_SP);

        }
    }

    public String getCatName(int id) {
        for (CatDTO cat : listCat) {
            if (cat.getId() == id) {
                return cat.getName();
            }
        }
        return "No Cat";
    }
}
