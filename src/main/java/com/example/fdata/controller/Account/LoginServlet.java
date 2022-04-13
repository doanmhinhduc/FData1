package com.example.fdata.controller.Account;

import com.example.fdata.entity.tbUser;
import com.example.fdata.model.tbUserModel;
import com.example.fdata.util.PasswordHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoginServlet extends HttpServlet {

    private tbUserModel accountModel = new tbUserModel();
    private static final int MAX_COUNT = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Account/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("Password");
         tbUser tbUser = accountModel.findAccountByUsername(username);
        if (tbUser == null) {
            resp.getWriter().println("Invalid information!");
            return;
        }
        if (tbUser.getStatus() == 2) {
            if (LocalDateTime.now().compareTo(tbUser.getLockTime()) > 0){
                tbUser.setStatus(1);
                tbUser.setFailureCount(0);
                accountModel.updateLock(tbUser.getUsername(),tbUser);
            }else {
                resp.getWriter().println("Your account has been locked, please try again after" + tbUser.getUsername());
                return;
            }
        }
        boolean result = PasswordHandler.checkPassword(password, tbUser.getPasswordHash(), tbUser.getSalt());
        if (result){
            HttpSession session = req.getSession();
            session.setAttribute("currentAccount", tbUser);
            resp.sendRedirect("/");
        }else {
            tbUser.setFailureCount(tbUser.getFailureCount() + 1);
            if (tbUser.getFailureCount() == MAX_COUNT) {
                tbUser.setStatus(2);
                tbUser.setLockTime(LocalDateTime.now().plusMinutes(5));
                accountModel.updateLock(tbUser.getUsername(),tbUser);
            }
            resp.getWriter().println("login fail!");
        }
    }
}
