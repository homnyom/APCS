//Author: Natalie Homnyom
//Date: 10/5/2015
import java.util.Scanner;
  
public class Hailstone
{
   public static void main(String[] args)
   {
      System.out.println("Hailstone Numbers!");
      System.out.print("Enter the start value: ");
      Scanner sc = new Scanner(System.in);
      int start = sc.nextInt();
      int count = hailstone(start);
      System.out.println(" takes " + count + " steps." );
      int count2 = hailstone(start, 1);
      System.out.println(" takes " + count2 + " steps." );
   }
   //recursive, counts the steps with a variable
   public static int hailstone(int n, int count)
   {
      System.out.print(n + " ");
      if(n == 1)
         return count;
      else{
         count++;
         if(n % 2 == 0)
            return hailstone(n / 2, count);
         else
            return hailstone(n * 3 + 1, count);
      }
   }
	//recursive, counts the steps without a variable
   public static int hailstone(int n)
   {
      int count = 1;
      System.out.print(n + " ");
      while(n != 1)
      {
         count++;
         if(n % 2 == 0)
            n = n/2;
         else
            n = n * 3 + 1;
         System.out.print(n + " ");
      }
      return count;
   }
}