package com.example.fdata.controller.Account;

import com.example.fdata.entity.tbUser;
import com.example.fdata.model.tbUserModel;
import com.example.fdata.util.PasswordHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class RegisterServlet extends HttpServlet {
    private tbUserModel accountModel = new tbUserModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String fullName = req.getParameter("FullName");
        String password = req.getParameter("Password");
        String confirmPassword = req.getParameter("confirmPassword");
        tbUser tbUser = new tbUser();
        tbUser.setUsername(username);
        tbUser.setFullName(fullName);
        String salt = PasswordHandler.generateSalt();
        tbUser.setSalt(salt);
        String passwordHash = PasswordHandler.getMd5(password, salt);
        tbUser.setPasswordHash(passwordHash);
        tbUser.setStatus(1);
        tbUser.setCreatedAt(Calendar.getInstance().getTime().toString());
       boolean result = accountModel.save(tbUser);
        if(result){
            req.getRequestDispatcher("Account/login.jsp").forward(req, resp);
        }else {
            resp.getWriter().println("Error occurs, please try again later!");
        }
    }
}
