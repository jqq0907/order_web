package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import domain.Product;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@WebServlet(name = "addOrderServlet" ,value = "/addOrderServlet")
public class addOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }
        JSONObject jsonObject=JSONObject.parseObject(jb.toString());
        String cust_name = jsonObject.get("cust_name").toString();
        String address = jsonObject.get("address").toString();
        String phone= jsonObject.get("phone").toString();
        JSONArray product = jsonObject.getJSONArray("products");
        List<Product> list = JSONObject.parseArray(jsonObject.get("products").toString(), Product.class);
        OrderService os=new OrderService();
        os.addOrder(cust_name,address,phone,list);
        out.println(jb);
        System.out.println(cust_name);
        /*PrintWriter out = response.getWriter();
        String cust_name = request.getParameter("cust_name");
        System.out.println(cust_name);
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");*/
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
