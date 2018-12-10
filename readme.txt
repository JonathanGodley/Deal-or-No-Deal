// readme.txt
// program information
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  2/05/2018

•	The application’s structure, i.e. relationships among objects etc.
The application primarily consists of two Servlets, Start.java and Game.java.
These servlets interact with DealOrNoDealBean.java (which makes use of Case.java) to setup the game, save and resume it,
  and process player interactions.
  Austosave.Java is a session listener used to manage session timeouts.
Game.jsp, Offer.jsp and Prize.jsp are used by the Servlets to show game information and game
  status to the player, giving them a front-end where they can interact with the game.
  If an error occurs on one of the JSP pages, the user is directed to ShowError.jsp
•	What is the purpose of each of your objects?
Autosave.java - Session Listener for Autosaving after 1 minute timeout.
Case.java - Briefcase Object for use in game
DealOrNoDealBean.java - java bean for handling game data
Game.java - Controller class for Game, handles almost all code and interaction.
HtmlGen.java - HTML generator for use with Servlets
Start.java - Java Servlet that handles setup of the game and the loading of saves
mystyle.css - stylesheet
gameLogic.js - used to create a http post to Game.java
validateStart.js - ensures user enters a username
Game.jsp - shows the briefcases and their status, allows selecting a briefcase
Offer.jsp - allows the user to accept or decline an offer
Prize.jsp - shows the user how much they've won
ShowError.jsp - basic error display file
•	How did you implement session tracking?
I implemented Session Tracking using Session Attributes from HttpSession, I stored a
  DealOrNoDealBean instance in the session attributes, and accessed that throughout the entire application.
•	How did you implement game saving?
I implemented game saving by creating unique save files for every username used. I stored them in web-inf/Saves
with the format of 12 lines of ID|VALUE|OPENED then 1 line of UserID|RoundNumber|CasesLeftinRound
Users are able to save on every page except the victory page, and can resume a save from the start page.
I've also implemented a session time-out save system, preventing data loss when a session times out.
•	Please identify if you are a MIT student or an undergraduate student.
Undergraduate
•	The URL the marker needs to visit to start the application.
http://localhost:8080/c3188072_assignment2/

Incompatible with IE6 & IE7
