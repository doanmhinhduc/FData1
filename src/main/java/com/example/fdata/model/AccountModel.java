package com.example.fdata.model;

import com.example.fdata.entity.Account;
import com.example.fdata.util.ConnectionHelper;
import com.example.fdata.util.SQLConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel {
    public boolean save(Account account) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            SQLConfig.INSERT_ACCOUNT);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPasswordHash());

            // PrepareStatement
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account findAccountByUsername(String username) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            SQLConfig.SELECT_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1, username);
            // PrepareStatement
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String usernameDatabase = resultSet.getString("username");
                String passwordHash = resultSet.getString("password");
                Account account = new Account();
                account.setUsername(usernameDatabase);
                account.setPasswordHash(passwordHash);
                return account;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
