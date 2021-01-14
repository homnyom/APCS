//name: Natalie Homnyom   date: 11/4/2015
import java.io.*;      //the File 
import java.util.*;    //the Scanner 
import javax.swing.*;  //the JOptionPane
public class Search_Driver  {
   public static void main(String[] args) throws Exception
   {
      String[] array = input("declaration.txt");
      String word = JOptionPane.showInputDialog("Enter a word:", null);
      Arrays.sort(array);
      int linear = Searches.linear(array, word);
      int binary = Searches.binary(array, word);
      if(linear == -1)
         System.out.println("Word not found with Linear Search.");
      else
         System.out.println("Linear Search found it at location " + linear + " in " + Searches.linearCount + " comparisons.");
      if(binary == -1)
         System.out.println("Word not found with Binary Search.");
      else
         System.out.println("Binary Search found it at location " + binary + " in " + Searches.binaryCount() + " comparisons.");
   }
   public static String[] input(String filename) throws Exception
   {
      Scanner sc = new Scanner(new File(filename));
      
      String punct = "!?.,:;";
      String s = sc.nextLine();
      
      for(int x = 0; x < punct.length(); x++)
         s = s.replace(punct.charAt(x) +  "", "");
      
      String[] array = s.split(" ");
      return array;
   }
}
/////////////////////////////////////////////////////////
class Searches
{
   public static int linearCount = 0;
   private static int binaryCount = 0;      
   public static int binaryCount()
   {
      return binaryCount;
   }
   public static int linear(Comparable[] array, Comparable target)
   { 
      for(int x = 0; x < array.length; x++)
      {
         linearCount++;
         if(array[x].compareTo(target) == 0)
            return x;
      }
      return -1;
   }
   public static int binary(Comparable[] array, Comparable target)
   {
      return binaryhelper(array, target, 0, array.length - 1);
   }
   private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
   {
      if(start > end)
         return -1;
      else
      {
         binaryCount++;
         int compared = (start + end)/2;
         if(array[compared].compareTo(target) == 0)
            return compared;
         else if(array[compared].compareTo(target) < 0)
            return binaryhelper(array, target, compared + 1, end);
         else
            return binaryhelper(array, target, start, compared - 1);
      }
   }
}