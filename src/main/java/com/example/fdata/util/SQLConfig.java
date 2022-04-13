package com.example.fdata.util;

public class SQLConfig {
    public static final String INSERT_ACCOUNT
            = "insert into accounts (username, fullName, passwordHash, salt, createAt, status)" +
            "values (?,?,?,?,?,?)";
    public static final String SELECT_ACCOUNT_BY_USERNAME
            = "select * from accounts where username = ?";
    public  static  final String LOCK_ACCOUNT
            = "update accounts set status = ?, failureCount = ?, lockTime = ? where username = ?";
}
