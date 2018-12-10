// Autosave.java
// HttpSessionListener to save game data when session expires
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

public class Autosave implements HttpSessionListener
{
@Override
public void sessionCreated(HttpSessionEvent se) {
}

@Override
public void sessionDestroyed(HttpSessionEvent se)
{
        HttpSession sess = se.getSession();
        DealOrNoDealBean bean = (DealOrNoDealBean)sess.getAttribute("game"); // load the bean

        if (bean != null || !bean.getUsername().equals("notSetup")) // if bean exists and has been properly init.
        {
                try
                {
                        // save the data from our bean.
                        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(sess.getServletContext().getRealPath("/WEB-INF/Saves/") + bean.getUsername() +".txt")));
                        for (int i = 0; i < 12; i++)
                        {
                                writer.write(bean.getCaseId(i)+"|"+bean.getCaseValue(i)+"|"+bean.getCaseOpened(i)+"\n");
                        }
                        writer.write(bean.getUsername()+"|"+bean.getRound()+"|"+bean.getCasesLeft()+"\n");
                        writer.close();

                }
                catch (IOException e)
                {
                        e.printStackTrace();
                        System.out.println("Autosave Error"); // print error to console
                }
        }

}
}
