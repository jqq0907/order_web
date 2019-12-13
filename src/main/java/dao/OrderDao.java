package dao;

import domain.Order;
import domain.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;
import util.LogUtil;

import java.util.List;
import java.util.Set;

public class OrderDao {
    /***
     * 获取所有姓名及其添加的商品
     */
    public static List<Order> selectAll(){
        Session session=null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query = session.createQuery("from Order");
            List<Order> list = query.list();
            for (Order o:list
            ) {
                System.out.println("名字:"+o.getCust_name());
                System.out.print("商品:");
                for (Product p:o.getProducts()
                ) {
                    System.out.print(p.getTitle()+" ");
                }
                System.out.println();
            }
            session.getTransaction().commit();
            return list;
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println("捕获异常，事务回滚");
            LogUtil.getLogger().error("selectAll异常，回滚事务");
            e.printStackTrace();
            return null;
        }finally {
            HibernateUtil.closeSession();
        }

    }
    /***
     * 添加订单
     */
    public static void  addOrder(String cust_name,String address,String phone,List<Product> products){
        Session session=null;
        Order order =new Order();
        order.setCust_name(cust_name);
        order.setAddress(address);
        order.setPhone(phone);
/*        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i]);
            order.getProducts().add(products[i]);
        }*/
        for (Product p :
                products) {
            order.getProducts().add(p);
        }
//            order.getProducts().add(product);
        try {
            session= HibernateUtil.getSession();
            session.beginTransaction();
            session.save(order);
          /*  for (int i = 0; i < products.length; i++) {
                session.save(products[i]);
            }*/
            for (Product p :
                    products) {
                session.save(p);
            }
            session.getTransaction().commit();
            System.out.println("添加订单");
        }catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("捕获异常，事务回滚");
            LogUtil.getLogger().error("addOrder异常，回滚事务");
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
    }

    /***
     * 根据订单id删除订单及订单中商品
     */
    public static void deleteOrder(int id){
        Session session=null;
        try{
            session=HibernateUtil.getSession();
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.delete(order);
            session.getTransaction().commit();
            System.out.println("删除订单及商品");
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println("捕获异常，事务回滚");
            LogUtil.getLogger().error("deleteOrder异常，回滚事务");
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
    }
    /***
     * 根据订单id添加商品
     */
    public static void addProduct(int id,Product product){
        Session session=null;
        try {
            session= HibernateUtil.getSession();
            session.beginTransaction();
            Order order = session.get(Order.class, id);
         /*   for (int i = 0; i < products.length; i++) {
                System.out.println(products[i]);
                products[i].setOrder(order);
            }*/
          /*  for (Product p :
                    products) {
                p.setOrder(order);
            }*/
          product.setOrder(order);
            session.saveOrUpdate(order);
          /*  for (int i = 0; i < products.length; i++) {
                session.save(products[i]);
            }*/
         /*   for (Product p :
                    products) {
                session.save(p);
            }*/
            session.save(product);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("捕获异常，事务回滚");
            LogUtil.getLogger().error("addProduct异常，回滚事务");
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }

    }

    /***
     * 根据订单id及商品名删除一个商品
     * cascade=null
     */
    public static void deleteProduct(int id ,String  title){
        Session session=null;
        try {
            session= HibernateUtil.getSession();
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            Set<Product> products = order.getProducts();
            for (Product p :
                    products) {
                if(p.getTitle().equals(title)){
                    System.out.println("删除商品:"+p.getTitle().equals(title));
                    session.delete(p);
                }
            }
//            session.saveOrUpdate(order);
            session.getTransaction().commit();
            System.out.println("删除成功");
        }catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("捕获异常，事务回滚");
            LogUtil.getLogger().error("deleteProduct异常，回滚事务");
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }


    }
}
