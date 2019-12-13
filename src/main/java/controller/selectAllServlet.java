package controller;

import domain.Order;
import domain.Product;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@WebServlet(name = "selectAllServlet",value="/selectAllServlet")
public class selectAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        OrderService os=new OrderService();
        List<Order> orders = os.selectAll();
        for (Order o :
                orders) {
            out.print(o.toString());
            Set<Product> products = o.getProducts();
            for (Product p :
                    products) {
                out.println();
                out.print(p.toString());
            }
            out.println();
            out.print("----------------------------");
            out.println();
        }


    }
}
