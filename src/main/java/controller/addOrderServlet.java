package controller;

import domain.Product;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "addOrderServlet" ,value = "/addOrderServlet")
public class addOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response, List list) throws ServletException, IOException {
        String cust_name = request.getParameter("cust_name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        OrderService os=new OrderService();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print("addOrderServlet");
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
