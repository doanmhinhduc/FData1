import com.example.fdata.entity.tbUser;
import com.example.hellot2008m.util.ConnectionHelper;
import com.example.hellot2008m.util.SQLConfig;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AccountModel {
    public boolean save(Account account) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            SQLConfig.INSERT_ACCOUNT);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getFullName());
            preparedStatement.setString(3, account.getPasswordHash());
            preparedStatement.setString(4, account.getSalt());
            preparedStatement.setString(5, account.getCreatedAt());
            preparedStatement.setInt(6, account.getStatus());
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
                String passwordHash = resultSet.getString("passwordHash");
                String salt = resultSet.getString("salt");
                Account account = new Account();
                account.setUsername(usernameDatabase);
                account.setPasswordHash(passwordHash);
                account.setSalt(salt)
                ;
                account.setFullName(resultSet.getString("fullName"));
                account.setStatus(resultSet.getInt("status"));
                account.setFailureCount(resultSet.getInt("failureCount"));
                account.setCreatedAt(resultSet.getString("createAt"));
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    if (resultSet.getString("lockTime") != null) {
                        account.setLockTime(LocalDateTime.parse(resultSet.getString("lockTime"), formatter));
                    }
                } catch (DateTimeParseException ex) {
                    System.out.println(ex.getMessage());
                }
                return account;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateLock(String username, Account account) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(
                            SQLConfig.LOCK_ACCOUNT);
            statement.setInt(1, account.getStatus());
            statement.setInt(2, account.getFailureCount());
            statement.setString(3, account.getLockTime().toString());
            statement.setString(4, username);
            statement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}