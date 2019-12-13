package dao;

import domain.Product;

public class ProductDao {

    public static Product getProducts(String title,int price){
            Product product=new Product();
            product.setTitle(title);
            product.setPrice(price);
            return product;
    }
}
