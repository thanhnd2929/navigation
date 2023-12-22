package com.example.ph45160_bt_fragment.DTO;

public class ProductDTO {

     int id_prduct;
     String product_name;
     int price, id_cat;
     String cat_name;


    public ProductDTO(int id_prduct, String product_name, int price, int id_cat) {
        this.id_prduct = id_prduct;
        this.product_name = product_name;
        this.price = price;
        this.id_cat = id_cat;
        this.cat_name = cat_name;
    }

    public ProductDTO(String product_name, int price, int id_cat) {
        this.product_name = product_name;
        this.price = price;
        this.id_cat = id_cat;
    }

    public ProductDTO(String product_name, int price, int id_cat, String cat_name) {
        this.product_name = product_name;
        this.price = price;
        this.id_cat = id_cat;
        this.cat_name = cat_name;
    }

    public ProductDTO() {
    }

    public int getId_prduct() {
        return id_prduct;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getPrice() {
        return price;
    }

    public int getId_cat() {
        return id_cat;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setId_prduct(int id_prduct) {
        this.id_prduct = id_prduct;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
}
