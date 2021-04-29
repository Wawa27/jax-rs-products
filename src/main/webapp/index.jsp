<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Products" %>
</h1>
<br/>
<ul>
    Liste des routes :
    <li>GET /v1/products</li>
    <li>POST /v1/products</li>
    <li>DELETE /v1/products?id={id}</li>
    <li>GET /v1/products/{id}</li>
    <li>DELETE /v1/products/{id}</li>
    <li>GET /v1/products/search/{name}</li>
    <li>POST /v1/products/buy/{id}/{quantity}</li>
</ul>
</body>
</html>