//Natalie Homnyom 1/29/16
import java.util.*;
import java.io.*;

public class McRonaldVIP
{
   public static void main(String[] args)
   {
      Queue<Integer> line = new LinkedList();
      Queue<Integer> vipLine = new LinkedList();
      int serviceAreas = 3;
      int numVip = 0;
      int vipWaitTime = 0;
      int numCustomers = 0;
      int totalCustomers = 0;
      int longestQueue = 0;
      int totalWaitTime = 0;
      int longestWaitTime = 0;
      int vipServeTime = 0;
      int serveTime = 0;
      
      for(int x = 1; x <= 1080; x++)
      {
         //Arrival
         double arrival = Math.random();
         if(arrival < 0.01)
         {
            vipLine.add(x);
            totalCustomers++;
            numVip++;
            vipServeTime = ((int)(Math.random() * 6) + 2)/serviceAreas;
            vipWaitTime += vipServeTime;
         }
         else if(arrival < 0.5)
         {
            line.add(x); 
            numCustomers++;
            totalCustomers++;
         }
         //Serving
         if(serveTime <= 0 && vipServeTime == 0)
         {
            if(numCustomers != 0)
            {
               int waitTime = x - line.peek();
               totalWaitTime += waitTime;
               if(waitTime > longestWaitTime)
                  longestWaitTime = waitTime;
               
               line.remove();
               numCustomers--;
            }
            serveTime = ((int)(Math.random() * 6) + 2)/serviceAreas;
         }
         else if(!vipLine.isEmpty())
         {
            if(vipServeTime == 0)
               vipLine.remove();
            else
               vipServeTime--;
         }
         else
            serveTime--;
            
         //Listing
         if(!line.isEmpty())
         {
            String s = "{";
            for(int cust: line)
               s += cust + ", ";
            System.out.println(s.substring(0, s.length() - 2) + "}");
         }
         else
            System.out.println("{}");
         
         if(longestQueue < numCustomers)
            longestQueue = numCustomers;
      }
      int overTime = 1080;
      while(!line.isEmpty())
      {
         if(serveTime <= 0)
         {
            if(numCustomers != 0)
            {
               int waitTime = overTime - line.peek();
               totalWaitTime += waitTime;
               if(waitTime > longestWaitTime)
                  longestWaitTime = waitTime;
               
               line.remove();
               numCustomers--;
            }
            serveTime = ((int)(Math.random() * 6) + 2)/serviceAreas;
         }
         else
            serveTime--;
         
         overTime++;
         
         //Listing
         if(!line.isEmpty())
         {
            String s = "{";
            for(int cust: line)
               s += cust + ", ";
            System.out.println(s.substring(0, s.length() - 2) + "}");
         }
         else
            System.out.println("{}");
      }
      System.out.println("Total customers served: " + totalCustomers);
      System.out.println("Average wait time: " + (double)totalWaitTime/totalCustomers);
      System.out.println("Longest wait time: " + longestWaitTime);
      System.out.println("Longest queue: " + longestQueue);
      System.out.println("Number of VIPs served: " + numVip);
      System.out.println("Average wait time for VIPs: " + (double)vipWaitTime/numVip);
   }
}