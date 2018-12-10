// Game.java
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

@WebServlet(urlPatterns = {"/Game"})
public class Game extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
        // work out game status and load appropriate JSP
        DealOrNoDealBean bean = (DealOrNoDealBean)request.getSession().getAttribute("game");
        if (bean == null)
        {
                response.sendRedirect("./"); // bean not setup properly.
        }
        else if (bean.getStatus().equals("offer"))
        {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Offer.jsp");
                dispatcher.forward(request,response); // go to offer page
        }
        else if (bean.getStatus().equals("prize"))
        {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Prize.jsp");
                dispatcher.forward(request,response); // go to prize page
        }
        else if (bean.getStatus().equals("gameplay"))
        {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Game.jsp");
                dispatcher.forward(request,response); // go to normal game page
        }
        else
        {
                response.sendRedirect("./");
        }
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        DealOrNoDealBean bean = (DealOrNoDealBean)request.getSession().getAttribute("game"); // load bean from session
        if (bean == null)
        {
                response.sendRedirect("./"); // if bean not loaded correctly, send to start.
        }
        else if (action.equals("Save & Quit")) // save and quit mechanic
        {
                try
                {
                        // first we save the data from our bean.
                        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(getServletContext().getRealPath("/WEB-INF/Saves/") + bean.getUsername() +".txt")));
                        for (int i = 0; i < 12; i++)
                        {
                                writer.write(bean.getCaseId(i)+"|"+bean.getCaseValue(i)+"|"+bean.getCaseOpened(i)+"\n");
                        }
                        writer.write(bean.getUsername()+"|"+bean.getRound()+"|"+bean.getCasesLeft()+"\n");
                        writer.close();

                        // kill the session
                        HttpSession session = request.getSession();
                        session.invalidate();

                        // finally we redirect back to the home page
                        response.sendRedirect("./");
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                        out.println("\t\tError Saving");
                }
        }
        else if (action.equals("Main Menu"))
        {

                // kill the session
                HttpSession session = request.getSession();
                session.invalidate();

                // finally we redirect back to the home page
                response.sendRedirect("./");

        }
        else if (action.equals("Open")) // open a briefcase
        {
                // find which briefcase we're opening
                int index = Integer.parseInt(request.getParameter("index"));

                // open the case
                bean.setCaseOpened(index, true);

                // handle what happens next
                if (bean.getCasesLeft() == 0 && bean.getRound() != 5) // round is over
                {
                        bean.setStatus("offer");
                        response.sendRedirect("Game");
                }
                else if (bean.getCasesLeft() == 0 && bean.getRound() == 5) // 1 case left, game is over
                {
                        // final round is over, player wins final case's value
                        bean.setPrize(Double.parseDouble(bean.getOffer()));
                        bean.setStatus("prize");
                        response.sendRedirect("Game");
                }
                else // normal gameplay resumes
                {
                        bean.setStatus("gameplay");
                        response.sendRedirect("Game");
                }

        }

        else if (action.equals("Deal"))
        {
                // player has accepted bank offer
                bean.setRound(bean.getRound()+1); // increment round
                bean.setStatus("prize");
                bean.setPrize(Double.parseDouble(bean.getOffer()));
                response.sendRedirect("Game");
        }
        else if (action.equals("No Deal"))
        {
                bean.setRound(bean.getRound()+1);
                bean.setStatus("gameplay");
                response.sendRedirect("Game");
        }
        else
        {
                bean.setStatus("gameplay");
                response.sendRedirect("Game");
        }
        out.close();
}
}
