package service;

import dao.ProductDao;
import domain.Product;

public class ProductService {
    /***
     * 获取产品
     * @param title
     * @param price
     * @return
     */
    public Product getProducts(String title, int price){
        return ProductDao.getProducts(title,price);
    }
}
