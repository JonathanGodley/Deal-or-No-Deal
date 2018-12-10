<%--
// Prize.jsp
// Java Server Page used to show player how much they've won.
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  2/05/2018
--%>
<%@ page errorPage = "ShowError.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="game" scope="session" class="dealgame.DealOrNoDealBean" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Deal Or No Deal!</title>
		<link rel="stylesheet" type="text/css" href="css/mystyle.css">
	</head>
	<body>
		<h1>Deal Or No Deal!</h1>
    <h2>Winner!</h2>
    <p>The last case that you opened was the case containing $<%=game.getLastValue()%></p>
    <p>You've won $<%=game.getPrize()%>!</p>
    <form method=POST action="Game">
      <input type="submit" name="action" value="Main Menu">
    </form>
	</body>
</html>
