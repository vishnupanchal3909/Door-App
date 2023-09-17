package com.vishnu.doorapp.Modul;

public class Userprofile {
    String productname,productUID,productprice,productdesc;

    public Userprofile(String productname, String productUID, String productprice, String productdesc) {
        this.productname = productname;
        this.productUID = productUID;
        this.productprice = productprice;
        this.productdesc = productdesc;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductUID() {
        return productUID;
    }

    public void setProductUID(String productUID) {
        this.productUID = productUID;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }
}
