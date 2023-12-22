package com.example.ph45160_bt_fragment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.ph45160_bt_fragment.DTO.CatDTO;
import com.example.ph45160_bt_fragment.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class CatDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public CatDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<CatDTO> getList() {
        ArrayList<CatDTO> listCat = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_cat", null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                CatDTO objCat = new CatDTO();
                objCat.setId(cursor.getInt(0));
                objCat.setName(cursor.getString(1));
                listCat.add(objCat);
            } while (cursor.moveToNext());
        } else {
            Log.d("zzzzzzzzzzzz", "getList: Không có dữ liệu");
        }
        return listCat;
    }

    public int addRow(CatDTO objCat) {
        ContentValues values = new ContentValues();
        values.put("name",objCat.getName());
        long kq = db.insert("tb_cat", null, values);
        return (int) kq;
    }

    public int updateRow(CatDTO catDTO) {
        ContentValues values = new ContentValues();
        values.put("name", catDTO.getName());
        String[] dieuKien = new String[]{String.valueOf(catDTO.getId())};
        return db.update("tb_cat", values, "id=?", dieuKien);

    }

    public int deleteRow(CatDTO catDTO) {
        String[] dieuKien = new String[]{String.valueOf(catDTO.getId())};
        return db.delete("tb_cat", "id=?", dieuKien);

    }
}
