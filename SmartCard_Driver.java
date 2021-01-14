 //Natalie Homnyom
 //September 11, 2015
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
      Station testing = new Station("Testing", 50);
     
      SmartCard jimmy = new SmartCard(20.00); //bought with $20.00 
      jimmy.board(center);            		    //boarded in zone 1
      jimmy.disembark(suburbia);					 //disembark in zone 4
      //jimmy.disembark(uptown);					 //disembark without having boarded
   	jimmy.board(suburbia);
      jimmy.disembark(testing);
   }
}
class SmartCard 
{
   private double balance;
   private int currentZone;
   private String currentPlace;
   private boolean boarded;
   public SmartCard(double d)
   {
      balance = d;
      currentZone = 1;
      currentPlace = "Default";
      boarded = false;
   }
   //Accessor Methods
   public double getBalance()
   {
      return balance;
   }
   public int getCurrentZone()
   {
      return currentZone;
   }
   public boolean getBoarded()
   {
      return boarded;
   }
   public String getCurrentPlace()
   {
      return currentPlace;
   }
   //Modifier Methods
   public void setBalance(double d)
   {
      balance = d;
   }
   public void setCurrentZone(int x)
   {
      currentZone = x;
   }
   public void setBoarded(boolean b)
   {
      boarded = b;
   }
   public void setCurrentPlace(String s)
   {
      currentPlace = s;
   }
   //Instance Methods
   public void addMoney(double d)
   {
      balance+=d;
   }
   public void board(Station s)
   {
      if(boarded)
      {
         System.out.println("Please see station manager!");
         System.exit(0);
      }
      if(balance < 0.5)
      {
         System.out.println("You do not have enough money to travel.");
         System.exit(0);
      }
      boarded = true;
      currentZone = s.getZone();
      currentPlace = s.getName();
      System.out.println("Boarded at: " + currentPlace);
   }
   public void cost(Station s)
   {
      if(currentPlace.equals(s.getName()))
      {
         System.out.println("You are already at that place");
      }
      else
      {
         int distance = Math.abs(currentZone - s.getZone());
         DecimalFormat decimal = new DecimalFormat("$0.00");
         System.out.println("From " + currentPlace + " to " + s.getName() + " costs " + decimal.format(0.75 * distance + 0.5));       
      }
   }
   public void disembark(Station s)
   {
      if(!boarded)
      {
         System.out.println("Please see station manager!");
         System.exit(0);
      }
      
      DecimalFormat decimal = new DecimalFormat("$0.00");
      System.out.println("From " + currentPlace + " to " + s.getName() + " costs " + decimal.format(findCost(s.getZone())));
      if(balance - findCost(s.getZone()) < 0)
      {
         System.out.println("You cannot afford to travel that far.");
         System.exit(0);  
      }
      if(currentPlace.equals(s.getName()))
      {
         System.out.println("You are already at that place");
         System.exit(0);
      }
      else
      {
         balance -= findCost(s.getZone());
         System.out.println("Balance: " + decimal.format(balance));
         boarded = false;
         currentZone = s.getZone();
         currentPlace = s.getName();
       }
   }
   private double findCost(int newZone)
   {
      return Math.abs((currentZone - newZone) * 0.75) + 0.5;
   }
}
class Station
{
   private String name;
   private int zone;
   public Station()
   {
      name = "Default";
      zone = 1;
   }
   public Station(String s, int x)
   {
      name = s;
      zone = x;
   }
   public String getName()
   {
      return name;
   }
   public int getZone()
   {
      return zone;
   }
   public void setName(String s)
   {
      name = s;
   }
   public void setZone(int x)
   {
      zone = x;
   }
   public String toString()
   {
      return name + " " + zone;
   }
}

 
