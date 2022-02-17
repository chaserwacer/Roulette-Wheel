/* 
Chase Balmer 
10/11/19 | period 5 
This is my own work, CJB 
Project Assignemnt2/RoulettePlayerClass 
This is 1 of the classes in assignment 2. This class handles the users money. 
*/ 
  
public class RoulettePlayer 
{ 
    //Variables 
    private String name; 
    private double balance; 
  
    //Constructor | Object is a user 
    public RoulettePlayer(String n, double b) 
    { 
        name = n; 
        balance = b; 
    } 
  
  
    //Methods 
    public void updateMoney(double delta) 
    { 
        //Updates players money(either up or down) 
        balance += delta; 
    } 
  
    public double getMoney() 
    { 
        //Return users balance 
        return balance; 
    } 
  
    public String getName() 
    { 
        //Return players name 
        return name; 
    } 
  
    public String toString() 
    { 
        //Output players info/credentials 
        return "Name: " + name + "\nBalance: " + balance; 
    } 
  
    public boolean hasMoney() 
    { 
        //Determine if user has a positive balance 
        boolean hasMoney; 
  
        if(balance < 1) 
            hasMoney = false; 
        else 
            hasMoney = true; 
  
        return hasMoney; 
    } 
} 

