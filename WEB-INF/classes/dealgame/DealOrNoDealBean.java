// DealOrNoDealBean.java
// Java Bean used to manage data and provided limited computation for a
//  simplified version of the Deal or No Deal game.
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  2/05/2018

package dealgame;

import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;

public class DealOrNoDealBean implements java.io.Serializable
{
// variables
private static DecimalFormat df2 = new DecimalFormat(".##");
ArrayList<Case> cases = new ArrayList<Case>();
String username = "notSetup";   // not using null to avoid the possibility of null exceptions
int casesLeft = 4;   // 4,3,2,1,1 (cases per round)
int round = 1;   // 5 rounds
double prize = 0;
double lastValue = 0;
String status = "notSetup";   // game status

// constructor (zero arg since bean)
public DealOrNoDealBean()
{
        // create our 12 briefcases numbered 1-12
        // they all start closed, with values from the list:
        // $0.50, $1, $10, $100, $200, $500, $1000, $2000, $5000, $10000, $20000, $50000
        // both the numbers of the briefcases, and the amount that goes into them needs to be randomised
        // so we'll create two array lists of both data sets, and randomly pull from both until we have our 12 cases

        // create lists & an array containing our values
        Double[] valuesList = {0.50,1.0,10.0,100.0,200.0,500.0,1000.0,2000.0,5000.0,10000.0,20000.0,50000.0};
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ArrayList<Double> values = new ArrayList<Double>();

        // populate lists
        for (int i = 1; i < 13; i++)
        {
                numbers.add(i);
                values.add(valuesList[i-1]);
        }

        // initialise a random number generator
        Random randomGenerator = new Random();

        // now create our 12 briefcases
        int newId, index;
        double newVal;

        for (int i = 0; i < 12; i++)
        {
                // so we'll find a random ID and a random Value and make a briefcase using them,
                // whilst also removing them from the arraylist to prevent duplicates
                index = randomGenerator.nextInt(numbers.size());
                newId = numbers.get(index);
                numbers.remove(index);

                index = randomGenerator.nextInt(values.size());
                newVal = values.get(index);
                values.remove(index);

                // then we create a new case and add it to our cases arraylist
                cases.add(new Case(newId, newVal, false));

                // now we should have 12 unopened cases with randomised values and ID numbers
        }

}

// accessors
public int getCaseId(int i)
{
        return cases.get(i).getId();
}

public boolean getCaseOpened(int i)
{
        return cases.get(i).getOpened();
}

public double getCaseValue(int i)
{
        return cases.get(i).getValue();
}

public String getCaseValueString(int i)
{
        // return value as a formatted string of format x.xx
        String tmpValue;
        tmpValue = df2.format(cases.get(i).getValue());
        if (tmpValue.endsWith(".0"))
        {
                tmpValue = (tmpValue.substring(0, tmpValue.length() - 2));
        }
        if (tmpValue.startsWith("."))
        {
                tmpValue = "0"+tmpValue+"0";
        }
        return tmpValue;
}

public int getCasesLeft()
{
        return casesLeft;
}

public String getStatus()
{
        return status;
}

public int getRound()
{
        return round;
}

public String getUsername()
{
        return username;
}

public String getLastValue()
{
        // return last opened case value as a formatted string of format x.xx
        String tmpValue;
        tmpValue = df2.format(lastValue);
        if (tmpValue.endsWith(".0"))
        {
                tmpValue = (tmpValue.substring(0, tmpValue.length() - 2));
        }
        if (tmpValue.startsWith("."))
        {
                tmpValue = "0"+tmpValue+"0";
        }
        return tmpValue;
}

public String getPrize()
{
        // return prize as a formatted string of format x.xx
        String tmpPrize = df2.format(prize);
        if (tmpPrize.endsWith(".0"))
        {
                tmpPrize = (tmpPrize.substring(0, tmpPrize.length() - 2));
        }
        if (tmpPrize.startsWith("."))
        {
                tmpPrize = "0"+tmpPrize+"0";
        }
        return tmpPrize;
}

public String getHighest()
{
        // return highest remaining value as a formatted string of format x.xx
        String tmpHighest;
        double highest = 0;
        for (Case briefcase : cases)
        {
                if (briefcase.getOpened() == false)
                {
                        if (briefcase.getValue() > highest)
                        {
                                highest = briefcase.getValue();
                        }
                }
        }
        tmpHighest = df2.format(highest);
        if (tmpHighest.endsWith(".0"))
        {
                tmpHighest = (tmpHighest.substring(0, tmpHighest.length() - 2));
        }
        if (tmpHighest.startsWith("."))
        {
                tmpHighest = "0"+tmpHighest+"0";
        }
        return tmpHighest;
}

public String getOffer()
{
        // The amount offered is based on the following formula:
        //  the total amount of the money in the remaining cases divided by the number of cases remaining
        double offer = 0;
        String tmpOffer;
        int casesRemaining = 0;
        double totalAmount = 0;
        for (Case briefcase : cases)
        {
                if (briefcase.getOpened() == false)
                {
                        casesRemaining++;
                        totalAmount += briefcase.getValue();
                }
        }
        // convert to x.xx formatted String
        offer = totalAmount / casesRemaining;
        tmpOffer = df2.format(offer);
        if (tmpOffer.endsWith(".0"))
        {
                tmpOffer = (tmpOffer.substring(0, tmpOffer.length() - 2));
        }
        if (tmpOffer.startsWith("."))
        {
                tmpOffer = "0"+tmpOffer+"0";
        }
        return tmpOffer;

}

// mutators
public void setCaseId(int i, int newId)
{
        cases.get(i).setId(newId);
}

public void setCaseValue(int i, double newVal)
{
        cases.get(i).setValue(newVal);
}

public void setStatus(String newStatus)
{
        status = newStatus;
}

public void setPrize(double newVal)
{
        prize = newVal;
}

public void setCaseOpened(int i, boolean newOpened)
{
        cases.get(i).setOpened(newOpened);
        lastValue = cases.get(i).getValue();
        if (newOpened = true)
        {
                if (casesLeft != 0)
                {
                        casesLeft--;
                }
                //int casesLeft = 4; // 4,3,2,1,1 (cases per round)
                //int round = 1; // 5 rounds
        }
}

public void setCasesLeft(int i)
{
        casesLeft = i;
}

public void setRound(int i)
{
        // 4,3,2,1,1 (round 1 -> 5)
        round = i;
        if (round == 1) { casesLeft = 4; }
        else if (round == 2) { casesLeft = 3; }
        else if (round == 3) { casesLeft = 2; }
        else if (round == 4) { casesLeft = 1; }
        else if (round == 5) { casesLeft = 1; }
}

public void setUsername(String uN)
{
        username = uN;
}

}
