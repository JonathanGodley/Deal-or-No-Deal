<%--
// Game.jsp
// Java Server Page used to display game status & provide interaction
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
		<link rel="stylesheet" type="text/css" href="css/mystyle.css"></head>
		<body>
			<script src="js/gameLogic.js"></script>
			<h1>Deal Or No Deal!</h1>
			<% int i = 0; %>
			<table class="briefcases">
				<% for (int x = 0; x < 3; x++)
        { %>
					<tr>
						<% for (int y = 0; y < 4; y++)
          {
             if (game.getCaseOpened(i))
            { %>
							<td class="briefcase">$<%=game.getCaseValueString(i)%></td>
						<% }
            else
            { %>
							<td class="briefcase" <% if (game.getCasesLeft() != 0) { %> onclick="gameLogic({action: 'Open', index: '<%=i%>'});" <% } %>><%=game.getCaseId(i)%></td>
						<%  }
          i++;
          }  %>
					</tr>
				<%} %>
			</table>
			<p>Cases Left This Round: <%=game.getCasesLeft()%></p>
			<p>This is Round <%=game.getRound()%></p>
			<form method=POST action="Game">
				<input type="submit" name="action" value="Save & Quit">
			</form>
		</body>
	</html>
