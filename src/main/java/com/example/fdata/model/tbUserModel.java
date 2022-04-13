package com.example.fdata.model;

import com.example.fdata.entity.tbUser;
import com.example.fdata.util.ConnectionHelper;
import com.example.fdata.util.SQLConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class tbUserModel {
    public boolean save(tbUser tbUser) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            SQLConfig.INSERT_ACCOUNT);
            preparedStatement.setString(1, tbUser.getUsername());
            preparedStatement.setString(2, tbUser.getFullName());
            preparedStatement.setString(3, tbUser.getPasswordHash());
            preparedStatement.setString(4, tbUser.getSalt());
            preparedStatement.setString(5, tbUser.getCreatedAt());
            // PrepareStatement
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public tbUser findAccountByUsername(String username) {
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
                String passwordHash = resultSet.getString("passwordHash");
                String salt = resultSet.getString("salt");
                tbUser account = new tbUser();
                tbUser.setUsername(usernameDatabase);
                tbUser.setPasswordHash(passwordHash);
                tbUser.setSalt(salt)
                ;
                tbUser.setFullName(resultSet.getString("fullName"));
                tbUser.setFailureCount(resultSet.getInt("failureCount"));
                tbUser.setCreatedAt(resultSet.getString("createAt"));
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    if (resultSet.getString("lockTime") != null) {
                        tbUser.setLockTime(LocalDateTime.parse(resultSet.getString("lockTime"), formatter));
                    }
                } catch (DateTimeParseException ex) {
                    System.out.println(ex.getMessage());
                }
                return tbUser;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateLock(String username, tbUser tbUser) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(
                            SQLConfig.LOCK_ACCOUNT);
            statement.setInt(1, tbUser.getStatus());
            statement.setInt(2, tbUser.getFailureCount());
            statement.setString(3, tbUser.getLockTime().toString());
            statement.setString(4, username);
            statement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
