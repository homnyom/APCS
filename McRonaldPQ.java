//Natalie Homnyom 2/8/16
import java.util.*;

public class McRonaldPQ
{
   public static void main(String[] args)
   {
      myPriorityQueue line = new myPriorityQueue();
      int serviceAreas = 3;
      int serveTime = 0;
      for(int x = 1; x <= 1080; x++)
      {
         //Arrival
         double arrival = Math.random();
         if(arrival < 0.5)
         {
            int grade = (int) (Math.random() * 4) + 9; 
            Customer c = new Customer(grade, x);
            line.add(c);
         }
         //Serving
         if(serveTime <= 0)
         {
            if(!line.isEmpty())
            {
               Customer c = line.remove();
               int bleh = c.getEnterTime();
               switch(c.getGrade()){
                  case 12:
                     Customer.addTotalWaitSeniors(x - bleh);
                     if(x - bleh > Customer.getWaitSeniors())
                        Customer.setWaitSeniors(bleh);
                     break;
                  case 11:
                     Customer.addTotalWaitJuniors(x - bleh);
                     if(x - bleh > Customer.getWaitJuniors())
                        Customer.setWaitJuniors(bleh);
                     break;
                  case 10:
                     Customer.addTotalWaitSophomores(x - bleh);
                     if(x - bleh > Customer.getWaitSophomores())
                        Customer.setWaitSophomores(bleh);
                     break;
                  case 9:
                     Customer.addTotalWaitFreshmen(x - bleh);
                     if(x - bleh > Customer.getWaitFreshmen())
                        Customer.setWaitFreshmen(bleh);
                     break;
                  default:
                     System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR ERROR");
               }
            
               serveTime = ((int)(Math.random() * 6) + 2)/serviceAreas;
            }
         }
         else
            serveTime--;
      }
      int overTime = 1080;
      while(!line.isEmpty())
      {
         //Serving
         if(serveTime <= 0)
         {
            Customer c = line.remove();
            int bleh = c.getEnterTime();
            switch(c.getGrade()){
               case 12:
                  Customer.addTotalWaitSeniors(overTime - bleh);
                  if(overTime - bleh > Customer.getWaitSeniors())
                     Customer.setWaitSeniors(bleh);
                  break;
               case 11:
                  Customer.addTotalWaitJuniors(overTime - bleh);
                  if(overTime - bleh > Customer.getWaitJuniors())
                     Customer.setWaitJuniors(bleh);
                  break;
               case 10:
                  Customer.addTotalWaitSophomores(overTime - bleh);
                  if(overTime - bleh > Customer.getWaitSophomores())
                     Customer.setWaitSophomores(bleh);
                  break;
               case 9:
                  Customer.addTotalWaitFreshmen(overTime - bleh);
                  if(overTime - bleh > Customer.getWaitFreshmen())
                     Customer.setWaitFreshmen(bleh);
                  break;
               default:
                  System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR ERROR");
            }
            serveTime = ((int)(Math.random() * 6) + 2)/serviceAreas;
         }
         else
            serveTime--;
         overTime++;
      }
      System.out.println("Customer\tTotal Served\tLongest Wait\tAverage Wait");
      System.out.println("Senior\t\t" + Customer.getSeniors() + "\t\t" + Customer.getWaitSeniors() + "\t\t" + (double) (Customer.getTotalWaitSeniors())/Customer.getSeniors());
      System.out.println("Junior\t\t" + Customer.getJuniors() + "\t\t" + Customer.getWaitJuniors() + "\t\t" + (double) (Customer.getTotalWaitJuniors())/Customer.getJuniors());
      System.out.println("Sophomore\t" + Customer.getSophomores() + "\t\t" + Customer.getWaitSophomores() + "\t\t" + (double) (Customer.getTotalWaitSophomores())/Customer.getSophomores());
      System.out.println("Freshman\t" + Customer.getFreshmen() + "\t\t" + Customer.getWaitFreshmen() + "\t\t" + (double) (Customer.getTotalWaitFreshmen())/Customer.getFreshmen());
   }
}

class Customer implements Comparable<Customer>
{
   private int grade;
   private int enterTime;
   private static int numSeniors = 0;
   private static int numJuniors = 0;
   private static int numSophomores = 0;
   private static int numFreshmen = 0;
   private static int waitSeniors = 0;
   private static int waitJuniors = 0;
   private static int waitSophomores = 0;
   private static int waitFreshmen = 0;
   private static int totalWaitSeniors = 0;
   private static int totalWaitJuniors = 0;
   private static int totalWaitSophomores = 0;
   private static int totalWaitFreshmen = 0;
   public Customer(int n, int x)
   {
      grade = n;
      enterTime = x;
      if(grade == 12)
         numSeniors++;
      else if(grade == 11)
         numJuniors++;
      else if(grade == 10)
         numSophomores++;
      else if(grade == 9)
         numFreshmen++;
   }
   public int getGrade()
   {
      return grade;
   }
   public int getEnterTime()
   {
      return enterTime;
   }
   public static int getSeniors()
   {
      return numSeniors;
   }
   public static int getJuniors()
   {
      return numJuniors;
   }
   public static int getSophomores()
   {
      return numSophomores;
   }
   public static int getFreshmen()
   {
      return numFreshmen;
   }
   public static int getWaitSeniors()
   {
      return waitSeniors;
   }
   public static int getWaitJuniors()
   {
      return waitJuniors;
   }
   public static int getWaitSophomores()
   {
      return waitSophomores;
   }
   public static int getWaitFreshmen()
   {
      return waitFreshmen;
   }
   public static int getTotalWaitSeniors()
   {
      return totalWaitSeniors;
   }
   public static int getTotalWaitJuniors()
   {
      return totalWaitJuniors;
   }
   public static int getTotalWaitSophomores()
   {
      return totalWaitSophomores;
   }
   public static int getTotalWaitFreshmen()
   {
      return totalWaitFreshmen;
   }
   public static void setWaitSeniors(int n)
   {
      waitSeniors = n;
   }
   public static void setWaitJuniors(int n)
   {
      waitJuniors = n;
   }
   public static void setWaitSophomores(int n)
   {
      waitSophomores = n;
   }
   public static void setWaitFreshmen(int n)
   {
      waitFreshmen = n;
   }
   public static void addTotalWaitSeniors(int n)
   {
      totalWaitSeniors += n;
   }
   public static void addTotalWaitJuniors(int n)
   {
      totalWaitJuniors += n;
   }
   public static void addTotalWaitSophomores(int n)
   {
      totalWaitSophomores += n;
   }
   public static void addTotalWaitFreshmen(int n)
   {
      totalWaitFreshmen += n;
   }
   public int compareTo(Customer s)
   {
      return s.getGrade() - grade;
   }
}
class myPriorityQueue extends ArrayList<Customer>
{
   public Customer remove()
   {
      int maxPos = 0;
      for(int x = 1; x < size(); x++)
      {
         Customer c1 = get(maxPos);
         Customer c2 = get(x);
         if(c1.compareTo(c2) > 1)
            maxPos = x;
      }
      return get(maxPos);
   }
}