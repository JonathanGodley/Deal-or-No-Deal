<%--
// ShowError.jsp
// EXTREMELY simple Java Server Page used to catch errors
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  2/05/2018
--%>
<%@ page isErrorPage = "true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <title>ERROR</title>
      <link rel="stylesheet" type="text/css" href="css/mystyle.css">
   </head>
   <body>
      <h1>Error!</h1>
      <p>Sorry, an error has occurred.</p>
      <a href="./">Home</a> 
   </body>
</html>
