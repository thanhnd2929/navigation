package com.example.ph45160_bt_fragment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph45160_bt_fragment.DTO.ProductDTO;
import com.example.ph45160_bt_fragment.DbHelper.MyDbHelper;



import java.util.ArrayList;

public class ProductDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public ProductDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    //    get all/ lay danh sach Cat
    public ArrayList<ProductDTO> getAll() {
        ArrayList<ProductDTO> listProd = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_product tbpd JOIN tb_cat tbc ON tbc.id = tbpd.id_cat;", null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {

                ProductDTO productDTO = new ProductDTO();
                productDTO.setId_prduct(c.getInt(0));
                productDTO.setProduct_name(c.getString(1));
                productDTO.setPrice(c.getInt(2));
                productDTO.setId_cat(c.getInt(3));


                listProd.add(productDTO);

            } while (c.moveToNext());
        }
        return listProd;
    }


    // hàm thêm mới
    public int addRow(ProductDTO productDTO) {
        ContentValues values = new ContentValues();
        values.put("name", productDTO.getProduct_name());
        values.put("price", productDTO.getPrice());
        values.put("id_cat", productDTO.getId_cat());

        return (int) db.insert("tb_product", null, values);


    }

    //Hàm update
    public int updateRow(ProductDTO productDTO) {
        ContentValues values = new ContentValues();
        values.put("name", productDTO.getProduct_name());
        values.put("price", productDTO.getPrice());
        values.put("id_cat", productDTO.getId_cat());
        String[] dieuKien = new String[]{String.valueOf(productDTO.getId_prduct())};
        return db.update("tb_product", values, "id=?", dieuKien);

    }

    //Hàm delete
    public int deleteRow(ProductDTO productDTO) {

        String[] dieuKien = new String[]{String.valueOf(productDTO.getId_prduct())};
        return db.delete("tb_product", "id=?", dieuKien);

    }


}
