<%--
// Offer.jsp
// Java Server Page used to show player the Bank Offer and allow them to accept or deny it.
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  2/05/2018
--%>
<%@ page errorPage = "ShowError.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="game" scope="session" class="dealgame.DealOrNoDealBean"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Deal Or No Deal!</title>
		<link rel="stylesheet" type="text/css" href="css/mystyle.css">
	</head>
	<body>
		<script src="js/gameLogic.js"></script>
		<h1>Deal Or No Deal!</h1>
		<h2>Bank Offer!</h2>
		<p>You've just opened the case containing $<%=game.getLastValue()%></p>
		<p>The most you can still win is $<%=game.getHighest()%></p>
		<p>But the bank has made you a guaranteed offer of $<%=game.getOffer()%></p>
		<table class="center">
			<tr>
				<td>
					<form method=POST action="Game">
						<input type="submit" name="action" value="Deal">
				  </form>
				</td>
				<td>
						<form method=POST action="Game">
							<input type="submit" name="action" value="No Deal">
						</form>
				</td>
			</tr>
		</table>
		<p>This is Round <%=game.getRound()%></p>
		<form method=POST action="Game">
			<input type="submit" name="action" value="Save & Quit">
		</form>
	</body>
</html>
