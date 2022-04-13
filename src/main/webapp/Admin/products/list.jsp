<%@ page import="com.example.fdata.entity.Account" %>
<%@ page import="com.example.fdata.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Account account = (Account) session.getAttribute("currentAccount");
    boolean isLoggedIn = false;
    if (account != null){
        isLoggedIn = true;
    }
    String currentUsername = account == null ? "Guest" : account.getUsername();

    List<Product> list = (List<Product>) request.getAttribute("listObj");
    HashSet<Product> recentView = (HashSet<Product>) session.getAttribute("recentView");
    if (recentView==null){
        recentView=new HashSet<>();
    }
%>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<head>
    <style>
        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #customers tr:hover {
            background-color: #ddd;
        }

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>

<h1>List Student</h1>
<div class="w3-container">
    <h2>Tab as a Card</h2>

    <%
        if (isLoggedIn){
    %>
    <strong>This is me: <%=currentUsername%>
        <a href="/account/logout">(Logout)</a></strong>
    <%}else {%>
    <strong>T
        <a href="/account/login">Login</a></strong>
    <%
        }
    %>

    <a href="/products/create" class="w3-btn w3-black">Create new products</a>
    <p>The w3-card-* classes makes the table look like a card (add shadow):</p>
</div>

<table id="customers">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>amount</th>
        <th>Price</th>
        <th>details</th>
    </tr>
    <%
        for (int i = 0; i < list.size(); i++) {
            Product obj = list.get(i);
    %>
    <tr>
        <td><%=obj.getId()%>
        </td>
        <td><%=obj.getName()%>
        </td>
        <td><%=obj.getAmount()%></td>
        <td><%=obj.getPrice()%>
        </td>
        <td><%=obj.getDetails()%></td>

        <td><a href="/products/detail?id=<%=obj.getId()%>">Detail</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <td/>
    </tr>
    <%
        }
    %>
    <script>

        document.addEventListener('DOMContentLoaded', function () {
            let listDeleteBtn = document.querySelectorAll('.btn-delete');
            for (let i = 0; i < listDeleteBtn.length; i++) {
                listDeleteBtn[i].addEventListener('click', function (event) {
                    event.preventDefault();
                    if (confirm("Are U sure?")) {
                        let xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState == 4 && xhr.status == 200) {
                                alert("Deleted!")
                                window.location.href = "/products/list";
                            }
                        }
                        xhr.open('POST', this.href, false);
                        xhr.send();
                    }
                })
            }
        })

    </script>
</table>
</body>
</html>
