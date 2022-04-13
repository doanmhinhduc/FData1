package com.example.fdata.controller.Account;

import com.example.fdata.entity.Account;
import com.example.fdata.model.AccountModel;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoginServlet extends HttpServlet {

    private AccountModel accountModel = new AccountModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Admin/accounts/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("Password");
        Account account = accountModel.findAccountByUsername(username);
        if (account == null){
            resp.getWriter().println("Invalid information!");
            return;
        }
        req.getSession().setAttribute("currentAccount",account);
        resp.sendRedirect("/products/list");

    }

}
