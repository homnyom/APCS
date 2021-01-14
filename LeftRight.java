//Natalie Homnyom October 2, 2015
import java.util.*;
import java.io.*;

public class LeftRight
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      String s = sc.next();
      int n = Integer.parseInt(s);
      //recursive("", n);
      //oddDigits("", n);
   	superprime(n);
   }
   public static void recursive(String s, int n)
   {
      if(s.length() == 3)
         System.out.println(s);
      else{
         recursive(s + "L", n);
         recursive(s + "R", n);
      }
   }
   public static void oddDigits(String s, int n)
   {
      if(s.length() == n)
         System.out.println(s);
      else{
         for(int x = 1; x <= 9; x+=2)
            oddDigits(s + x, n);
      }
   }
	//the SuperPrime methods 
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, etc.
      recur(3, n); //all the primes < 10
      recur(5, n);
      recur(7, n);
   }
   private static void recur(int k, int n)
   {
      String num = "" + k;
      if(num.length() == n)
         System.out.println(num);
      else
      {
         for(int x = 1; x <= 9; x++)
         {
            if(isPrime(Integer.parseInt(num + x)))
               recur(Integer.parseInt(num + x), n);
         }
      }
   }
   private static boolean isPrime(int n)
   {
      if(n <= 1)
         return false;
      for(int x = (int) Math.sqrt(n); x > 1; x--)
      {
         if(n % x == 0)
            return false;
      }
      return true;
   }   
}
