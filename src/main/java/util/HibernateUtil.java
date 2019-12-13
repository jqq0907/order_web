package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
    private static SessionFactory sessionFactory=null;
    static {
        try {
            Configuration cfg=new Configuration().configure();
            sessionFactory=cfg.buildSessionFactory();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建回话工厂失败");
        }


    }
    /**
     *
     * @return 获取Session
     */
    public static Session getSession(){
        Session session=(Session)threadLocal.get();
        if(session==null||!session.isOpen()) {
            if(sessionFactory==null) {
                rebuildSessionFactory();
            }
            session=(sessionFactory!=null)?sessionFactory.openSession():null;
            threadLocal.set(session);
        }
        return session;

    }
    public  static void rebuildSessionFactory() {
        try {
            Configuration cfg=new Configuration().configure();
            sessionFactory=cfg.buildSessionFactory();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建回话工厂失败");
        }

    }
    /**
     *
     * @return 获取SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    /***
     *
     * @throws 关闭Session
     */
    public static void closeSession() throws HibernateException{
        Session session =(Session)threadLocal.get();
        threadLocal.set(null);
        if(session!=null) {
            session.close();
        }
    }
}
