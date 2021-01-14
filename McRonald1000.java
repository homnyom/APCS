//Natalie Homnyom 1/29/16
import java.util.*;
import java.io.*;

public class McRonald1000
{
   public static void main(String[] args)
   {
      int totalCustomers = 0;
      int totalWaitTime = 0;
      int longestWaitTime = 0;
      int longestQueue = 0;
      int longestDay = 0;
      for(int day = 1; day <= 1000; day++)
      {
         Queue<Integer> line = new LinkedList();
         int dayCustomers = 0;
         int queueLength = 0;
         int serveTime = 0;
      
         for(int x = 1; x <= 1080; x++)
         {
            //Arrival
            double arrival = Math.random();
            if(arrival < 0.2)
            {
               line.add(x); 
               queueLength++;
               dayCustomers++;
            }
            //Serving
            if(serveTime == 0)
            {
               if(!line.isEmpty())
               {
                  int waitTime = x - line.peek();
                  totalWaitTime += waitTime;
                  if(waitTime > longestWaitTime)
                     longestWaitTime = waitTime;
               
                  line.remove();
                  queueLength--;
               }
               serveTime = (int)(Math.random() * 6) + 2;
            }
            else
               serveTime--;
            //Misc
            if(queueLength > longestQueue)
               longestQueue = queueLength;
         }
         int overTime = 1080;
         while(!line.isEmpty())
         {
            if(serveTime == 0)
            {
               if(!line.isEmpty())
               {
                  int waitTime = overTime - line.peek();
                  totalWaitTime += waitTime;
                  if(waitTime > longestWaitTime)
                     longestWaitTime = waitTime;
               
                  line.remove();
               }
               serveTime = (int)(Math.random() * 6) + 2;
            }
            else
               serveTime--;
         
            overTime++;
         }
         if(dayCustomers > longestDay)
            longestDay = dayCustomers;
         totalCustomers += dayCustomers;
      }
      System.out.println("Total customers served: " + totalCustomers);
      System.out.println("Average wait time: " + (double)totalWaitTime/totalCustomers);
      System.out.println("Longest wait time: " + longestWaitTime);
      System.out.println("Longest queue: " + longestQueue);
      System.out.println("Average served per day: " + totalCustomers/1000.0);
      System.out.println("Largest day: " + longestDay);
   }
}