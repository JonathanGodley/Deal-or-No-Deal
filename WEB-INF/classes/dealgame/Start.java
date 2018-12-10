// Start.java
// Class used to handle the start page of the deal or no deal game
// I've used a servlet due to the amount of code vs small amount of presentation
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  2/05/2018

package dealgame;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("")
public class Start extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
        //HtmlGen generator = new HtmlGen(); // used to quickly generate html tags
        // got a warning about "[static] static method should be qualified by type name, HtmlGen, instead of by an expression"
        // so i switched my generator.doctype() etc to HtmlGen.doctype
        PrintWriter out = response.getWriter();
        out.println(HtmlGen.doctype());
        out.println(HtmlGen.head("Deal Or No Deal!"));

        out.println("\t<body>");
        out.println("\t\t" + HtmlGen.h1("Deal Or No Deal!"));
        out.println("\t\t<p>Enter a username to start a new game or resume an existing session.</p>");

        // our form with validation
        out.println("\t\t<script src=\"js/validateStart.js\"></script>");
        out.println(
                "\t\t<form method=\"post\" onsubmit=\"return validateStart();\">" +

                "\n\t\t\t<label for=\"userID\">UserID*</label>" +
                "\n\t\t\t<input type=\"text\" name=\"userID\" id=\"userID\" /><br>" +

                "\n\t\t\t<input type=\"radio\" name=\"option\" id=\"option1\" value=\"new\" checked> New Game - May Overwrite Saves <br>" +
                "\n\t\t\t<input type=\"radio\" name=\"option\" id=\"option2\" value=\"resume\"> Resume Game - If Available <br>" +

                "\n\t\t\t<input type=\"reset\" value=\"Clear\" />" +
                "\n\t\t\t<input type=\"submit\" value=\"Submit\" />" +

                "\n\t\t</form>");

        out.println("\t\t<p>* - Required (No Registration Neccessary)</p>");
        out.println("\t</body>");
        out.println("</html>");
        out.close();
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
        PrintWriter out = response.getWriter();
        String userID = request.getParameter("userID");
        String option = request.getParameter("option");
        if (option.equals("resume"))
        {
                // first we check if the save file exists
                File file = new File(request.getServletContext().getRealPath("/WEB-INF/Saves/"+userID+".txt"));
                if (file.exists() && !file.isDirectory())
                {
                        // then we initialise our java Bean
                        DealOrNoDealBean bean = new DealOrNoDealBean();

                        // then we overwrite it with the save data
                        try
                        {
                                InputStream input = getServletContext().getResourceAsStream("/WEB-INF/Saves/"+userID+".txt");
                                BufferedReader b = new BufferedReader(new InputStreamReader(input, "UTF-8"));

                                String tmpString; // should be in format ID|VALUE|OPENED
                                String[] tmpStringSplit;

                                // load briefcases
                                for (int i = 0; i < 12; i++)
                                {
                                        tmpString = b.readLine();
                                        tmpStringSplit = tmpString.split("\\|");

                                        bean.setCaseId(i, Integer.parseInt(tmpStringSplit[0]) );
                                        bean.setCaseValue(i, Double.parseDouble(tmpStringSplit[1]) );
                                        bean.setCaseOpened(i, Boolean.parseBoolean(tmpStringSplit[2]) );

                                }
                                // load game state data
                                tmpString = b.readLine(); // should be in format UserID|RoundNumber|CasesLeftinRound
                                tmpStringSplit = tmpString.split("\\|");
                                bean.setUsername(tmpStringSplit[0]);
                                bean.setRound(Integer.parseInt(tmpStringSplit[1]));
                                bean.setCasesLeft(Integer.parseInt(tmpStringSplit[2]));

                                b.close();
                        }
                        catch (IOException e)
                        {
                                e.printStackTrace();
                                out.println("\t\tError Reading Save File");
                        }

                        // then we delete the save file
                        file.delete();

                        // then we store the bean in the session object
                        HttpSession session = request.getSession();
                        session.setAttribute("game", bean);

                        // then we redirect
                        if (bean.getCasesLeft() == 0)
                        {
                                bean.setStatus("offer");
                                response.sendRedirect("Game");
                        }
                        else
                        {
                                bean.setStatus("gameplay");
                                response.sendRedirect("Game");
                        }
                }
                else
                {
                        // no save file exists, so they get a new game, so we just send them straight to Game.jsp after initialising our bean
                        // get the session
                        HttpSession session = request.getSession();

                        // instantiate and set up bean
                        DealOrNoDealBean bean = new DealOrNoDealBean();
                        bean.setUsername(userID);

                        // store bean in the session object
                        session.setAttribute("game", bean);

                        // redirect user to game
                        bean.setStatus("gameplay");
                        response.sendRedirect("Game");

                }
        }
        else
        {
                // new game, so we check for a save, delete it if it exists and then just send
                // them straight to Game.jsp after initialising our bean
                File file = new File(request.getServletContext().getRealPath("/WEB-INF/Saves/"+userID+".txt"));
                if (file.exists() && !file.isDirectory())
                {
                        // then we delete the save file
                        file.delete();
                }
                // get the session
                HttpSession session = request.getSession();

                // instantiate and set up bean
                DealOrNoDealBean bean = new DealOrNoDealBean();
                bean.setUsername(userID);

                // store bean in the session object
                session.setAttribute("game", bean);

                // redirect user to game
                bean.setStatus("gameplay");
                response.sendRedirect("Game");
        }
}
}
