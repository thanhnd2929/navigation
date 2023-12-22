package com.example.ph45160_bt_fragment.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    static String DB_NAME = "ql_banhang";
    static int DB_VERSION = 1;

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // câu lệnh tạo bảng viết ở đây
        // tạo bảng thê loại:
        String sql_cat = "CREATE TABLE tb_cat ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL );";
        db.execSQL( sql_cat ); // tạo xong bảng tb_cat



        // tạo bảng sản phẩm
        String sql_product = "CREATE TABLE tb_product ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, price INTEGER DEFAULT (0) NOT NULL,id_cat INTEGER NOT NULL CONSTRAINT fk_pro_cat REFERENCES tb_cat (id) );";

        db.execSQL( sql_product );

        String insert_cat = "INSERT INTO tb_cat (name) VALUES ('Tivi'), ('Tủ lạnh'), ('Điều hòa') ";
        db.execSQL( insert_cat );

        String insert_product = "INSERT INTO tb_product(name, price, id_cat) VALUES ('LG TV', 1000, 1)";
        db.execSQL(insert_product);

//        String sqlJoinTables = "SELECT tb_product.id, " +
//                "tb_product.name AS product_name, " +
//                "tb_product.price, " +
//                "tb_product.id_cat, " +
//                "tb_cat.name AS category_name " +
//                "FROM tb_product " +
//                "INNER JOIN tb_cat ON tb_product.id_cat = tb_cat.id;";
//        db.execSQL(sqlJoinTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
