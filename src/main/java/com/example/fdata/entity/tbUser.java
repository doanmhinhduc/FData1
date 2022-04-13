package com.example.fdata.entity;

public class tbUser {

     private String  username;
     private String  password;

     public tbUser() {
     }

     public tbUser(String username, String password) {
          this.username = username;
          this.password = password;
     }

     @Override
     public String toString() {
          return "tbUser{" +
                  "username='" + username + '\'' +
                  ", password='" + password + '\'' +
                  '}';
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }
}
