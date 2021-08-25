package kr.ac.daegu.jspmvc.controller;

import kr.ac.daegu.jspmvc.model.TestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/dbTest")
public class DBConnectTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            boolean isConnected = TestDAO.getConnection();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("db 연결 성공.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
