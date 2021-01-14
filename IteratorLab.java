 //Name: Natalie Homnyom     Date: 1/12/16
 // use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
      // pre: an array of just int values 
   	// post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> numbers = new ArrayList();
      for(int x: rawNumbers)
         numbers.add(x);
      return numbers;
   }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> movies = new ArrayList();
      for(String s: rawWords)
         movies.add(s); 
      return movies;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
      Iterator<Integer> it = a.iterator();
      int count = 0;
      while(it.hasNext())
      {
         int x = it.next();
         if(x < 0)
            count++;
      }
      return count;
   }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
      Iterator<Integer> it = a.iterator();
      double sum = 0;
      while(it.hasNext())
         sum += it.next();
      return sum/a.size();
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ArrayList<Integer> noNeg = new ArrayList();
      for(int x: a)
      {
         if(x < 0)
            noNeg.add(0);
         else
            noNeg.add(x);
      }
      return noNeg;
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
      Iterator<Integer> it = a.iterator();
      while(it.hasNext())
      {
         int x = it.next();
         if(x == 0)
            a.remove(x);
      }
      return a;
   }
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return ArrayList without duplicate movie titles
		// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> noDupes = new ArrayList();
      Iterator<String> it = a.iterator();
      while(it.hasNext())
      {
         String s = it.next();
         if(!noDupes.contains(s))
            noDupes.add(s);
      } 
      return noDupes;
   }
   
}

