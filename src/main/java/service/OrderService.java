package service;

import dao.OrderDao;
import domain.Order;
import domain.Product;

import java.util.List;

public class OrderService {
    /***
     * 添加订单及商品
     * @param cust_name
     * @param address
     * @param phone
     */
    public void addOrder(String cust_name, String address, String phone,List<Product> products){
        OrderDao.addOrder(cust_name,address,phone,products);
    }

    /***
     * 根据订单id添加商品
     * @param id
     * @param product
     */
    public void addProduct(int id, Product product){
        OrderDao.addProduct(id,product);
    }

    /***
     * 查询所有订单信息
     */
    public List<Order> selectAll(){
       return OrderDao.selectAll();
    }

    /***
     * 根据订单id删除订单
     * @param id
     */
    public void deleteOrder(int id){
        OrderDao.deleteOrder(id);
    }

    /***
     * 根据订单id及商品名称删除商品
     * @param id
     * @param title
     */
    public void deleteProduct(int id,String title){
        OrderDao.deleteProduct(id,title);
    }
}
