//Author:  Natalie Homnyom
//Date: 10/5/2015
   import java.util.Scanner;
   public class Fibonacci
   {
      public static void main(String[] args)
      {
         long start, end, fib;
         int[] fibNumber = {1, 5, 10, 20, 30, 40, 41, 42};
         System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
         for(int n = fibNumber[0]; n <= fibNumber[fibNumber.length - 1]; n++)
         { 
            start = System.nanoTime();
            fib = fibIterate(n);
            end = System.nanoTime();
            System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
            start = System.nanoTime();   	
            fib = fibRecur(n);      
            end = System.nanoTime();
            System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
         }
      }
   	/***********************
   	Calculates the nth Fibonacci number by iteration
   	***********************/
      public static long fibIterate(int n)
      {
         int first = 1;
         int second = 1;
         int num = 1;
         if(n == 1 || n == 2)
            return 1;
         for(int x = 3; x <= n; x++)
         {
            num = first + second;
            first = second;
            second = num;
         } 
         return num;
      }
   	/***********************
   	Calculates the nth Fibonacci number by recursion
   	***********************/
      public static long fibRecur(int n)
      {
         if(n <= 2)
            return 1;
         else
            return fibRecur(n - 1) + fibRecur(n - 2);
      }
   }