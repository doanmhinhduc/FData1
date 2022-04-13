<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.HashMap" %><%
    HashMap<String,String> errros = (HashMap<String,String>) request.getAttribute("errors");
%>

<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<form class="w3-container w3-card-4" action="/products/create" method="post">
    <h2 class="w3-text-blue">Create new Product</h2>
    <p>làm ơn.</p>
    <%
        if (errros!= null&&errros.size()>0){
    %>
    <div class="w3-panel w3-pale-red w3-border">
        <h3>Lỗi</h3>
        <ul>
            <%
                for (String message:
                        errros.values()) {
            %>
            <li><%=message%></li>
            <%}%>
        </ul>
    </div>
    <%
        }
    %>
    <p>
        <label class="w3-text-blue"><b>name</b></label>
        <input class="w3-input w3-border" name="name" type="text">
    </p>

    <p>
        <label class="w3-text-blue"><b>amount</b></label>
        <input class="w3-input w3-border" name="amount" type="number">
    </p>
    <p>
        <label class="w3-text-blue"><b>Price</b></label>
        <input class="w3-input w3-border" name="price" value="0" type="number">
    </p>
    <p>
        <label class="w3-text-blue"><b>details</b></label>
        <input class="w3-input w3-border" name="details"  type="text">
    </p>
    <p>
        <button class="w3-btn w3-blue">submit</button>
    </p>
    <input type="reset" class="w3-btn w3-teal" value="Reset">
</form>
<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

</body>
</html>
