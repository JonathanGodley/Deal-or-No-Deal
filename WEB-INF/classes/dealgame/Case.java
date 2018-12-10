// Case.java
// Java object representing a briefcase object in the deal or no deal game.
//
// Programmer:  Jonathan Godley - c3188072
// Course: SENG2050
// Last modified:  1/05/2018

package dealgame;

public class Case
{
// variables
private int id;
private double value;
private boolean opened;

// constructor
// pre: n/a
// post: new Case object is created
public Case(int newId, double newVal, boolean newOpened)
{
        id = newId;
        value = newVal;
        opened = newOpened;
}

// accessors
public int getId()
{
        return id;
}

public double getValue()
{
        return value;
}

public boolean getOpened()
{
        return opened;
}

// mutators
public void setId(int newId)
{
        id = newId;
}

public void setValue(double newVal)
{
        value = newVal;
}

public void setOpened(boolean status)
{
        opened = status;
}
}
