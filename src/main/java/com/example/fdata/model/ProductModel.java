package com.example.fdata.model;

import com.example.fdata.entity.Product;
import com.example.fdata.util.ConnectionHelper;
import com.example.fdata.util.SQLConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    public boolean save(Product obj)   {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "insert into products (name,amount, price,details) value (?,?,?,?)"
                    );
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getAmount());
            preparedStatement.setDouble(3, obj.getPrice());
            preparedStatement.setString(4,obj.getDetails());
            preparedStatement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Product> findAll()  {
        List<Product> ListObj = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlSelect = "Select * from products ";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                Product obj = new Product(id,name,amount,price,details);
                ListObj.add(obj);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ListObj;

    }


    public Product findById(int id)  {
        Product obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlSelect = String.format("Select * from products where id =%d",id);
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            if (resultSet.next()){
                String name = resultSet.getString("name");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                String details = resultSet.getString("details");
                obj = new Product(id,name,amount,price,details);
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return obj;
    }
}
