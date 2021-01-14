    /* M.L. Billington, 10/02/2006.
    Uses the helper classes Selection and Insertion. 
	 Students are to write the Selection and Insertion classes.
    */
import java.util.*;
import java.io.*;
public class Sorts
{
   public static void main(String[] args) throws Exception
   {
        //Part 1, for doubles
      int n = (int)(Math.random()*100);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();	
      print(array);
      System.out.println("*************  *************");
      //Selection.sort(array);
      Insertion.sort(array);
      print(array);
         
      	//Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      String[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
      print(arrayStr);
      System.out.println("*************  *************");
      //Selection.sort(arrayStr);
      Insertion.sort(arrayStr);
      print(arrayStr);
   }
   public static void print(double[] a)
   {
         // for(int k = 0; k < a.length; k++)
            // System.out.println(a[k]);
      for(double d : a)
         System.out.println(d);
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object item : papaya)     //for-each
         System.out.println( item );
   }
}
   //*******************************************************************
  //Name: Natalie Homnyom             Date: 11/4/2015
  //The Selection class will have methods sort(), findMax() and swap().
  //Three versions of each method will have to be written, to work 
  //for doubles, Strings, and Comparables.
  
class Selection
{
   public static void sort(double[] array)
   {
      for(int x = array.length - 1; x >= 1; x--)
      {
         int maxLocation = findMax(array, x);
         swap(array, maxLocation, x);
      }
   }
   private static int findMax(double[] array, int n)
   {
      int maxLocation = 0;
      for(int x = 1; x < n; x++)
      {
         if(array[maxLocation] < array[x])
            maxLocation = x;
      }
      return maxLocation;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   	/***************************************************
   	  for Strings
   	  ***********************************************/
   public static void sort(String[] array)
   {
      for(int x = array.length - 1; x >= 1; x--)
      {
         int maxLocation = findMax(array, x);
         swap(array, maxLocation, x);
      }
   }
   public static int findMax(String[] array, int upper)
   {
      int maxLocation = 0;
      for(int x = 1; x < upper; x++)
      {
         if(array[maxLocation].compareTo(array[x]) < 0)
            maxLocation = x;
      }
      return maxLocation;
   }
   public static void swap(String[] array, int a, int b)
   {
      String temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   	/***************************************************
   	 for Comparables,
   	      Swap() is for Objects.
   	      make sure that print() is for Objects, too.
   	  ***********************************************/
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
       public static void sort(Comparable[] array)
   {
      for(int x = array.length - 1; x >= 1; x--)
      {
         int tempLocation = findMax(array, x);
         swap(array, tempLocation, x);
      }
   }
   @SuppressWarnings("unchecked")
       public static int findMax(Comparable[] array, int upper)
   {
      int maxLocation = 0;
      for(int x = 1; x < upper; x++)
      {
         if(array[maxLocation].compareTo(array[x]) < 0)
            maxLocation = x;
      }
      return maxLocation;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}   

//**********************************************************
  //Name: Natalie Homnyom          Date: 11/4/2015
  //The Insertion class 
  //write enough methods (sort() and shift()) to handle doubles and Comparables.
class Insertion
{
   public static void sort(double[] array)
   {
      for(int x = 1; x < array.length; x++)
      {
         int newPos = x;
         for(int y = x - 1; y >= 0; y--)
            if(array[x] < array[y])
               newPos = y;
         shift(array, x, newPos);
      }
   }
   public static void shift(double[] array, int originalPos, int newPos)
   {
      double temp = array[originalPos];
      for(int x = originalPos; x >= newPos + 1; x--)
         array[x] = array[x - 1];
      array[newPos] = temp;
   }
   
   public static void sort(Comparable[] array)
   {
      for(int x = 1; x < array.length; x++)
      {
         int newPos = x;
         for(int y = x - 1; y >= 0; y--)
            if(array[x].compareTo(array[y]) < 0)
               newPos = y;
         shift(array, x, newPos);
      }
   }
   public static void shift(Comparable[] array, int originalPos, int newPos)
   {
      Comparable temp = array[originalPos];
      for(int x = originalPos; x >= newPos + 1; x--)
         array[x] = array[x - 1];
      array[newPos] = temp;
   }
}
