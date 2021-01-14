// Name: Natalie Homnyom   Date: March 16, 2016
import java.util.*;
import java.io.*;
public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      //Scanner sc = new Scanner(System.in);
      //System.out.print("Enter the file name: ");
      //String fileName = sc.next();
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fn));
      String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String lower = "abcdefghijklmnopqrstuvwxyz";
      Set<Character> upperSet = new TreeSet<Character>();
      Set<Character> lowerSet = new TreeSet<Character>();
      Set<Character> misc = new TreeSet<Character>();
      Set<Character> commonUpper = new TreeSet<Character>();
      Set<Character> commonLower = new TreeSet<Character>();
      Set<Character> commonMisc = new TreeSet<Character>();
      
      if(sc.hasNextLine())
      {
         String s = sc.nextLine();
         
         for(int x = 1; x < s.length(); x++)
         {
            char c = s.charAt(x);
            if(upper.contains(c + ""))
            {
               commonUpper.add(c);
               upperSet.add(c);
            }
            else if(lower.contains(c + ""))
            {
               commonLower.add(c);
               lowerSet.add(c);
            }
            else
            {
               commonMisc.add(c);
               misc.add(c);
            } 
         }  
         System.out.println("Lower case: " + lowerSet);
         System.out.println("Upper case: " + upperSet);
         System.out.println("Other: " + misc);
         
         upperSet.clear();
         lowerSet.clear();
         misc.clear();
      }
       
      while(sc.hasNextLine())
      {
         String s = sc.nextLine();
         for(int x = 1; x < s.length(); x++)
         {
            char c = s.charAt(x);
            if(upper.contains(c + ""))
               upperSet.add(c);
            else if(lower.contains(c + ""))
               lowerSet.add(c);
            else
               misc.add(c);
         }
         
         System.out.println(s);
         System.out.println("Lower case: " + lowerSet);
         System.out.println("Upper case: " + upperSet);
         System.out.println("Other: " + misc + "\n");
         
         Iterator<Character> upIt = commonUpper.iterator();
         Iterator<Character> lowIt = commonLower.iterator();
         Iterator<Character> miscIt = commonMisc.iterator();
         
         String deleteU = "";
         String deleteL = "";
         String deleteM = "";
         
         while(upIt.hasNext())
         {
            char c = upIt.next();
            if(!upperSet.contains(c))
               deleteU += c;
         }
         while(lowIt.hasNext())
         {
            char c = lowIt.next();
            if(!lowerSet.contains(c))
               deleteL += c;
         }
         while(miscIt.hasNext())
         {
            char c = miscIt.next();
            if(!misc.contains(c))
               deleteM += c;
         }
         
         for(int x = 0; x < deleteU.length(); x++)
            commonUpper.remove(deleteU.charAt(x));
         for(int x = 0; x < deleteL.length(); x++)
            commonLower.remove(deleteL.charAt(x));
         for(int x = 0; x < deleteM.length(); x++)
            commonMisc.remove(deleteM.charAt(x));
         
         upperSet.clear();
         lowerSet.clear();
         misc.clear();
      }
      System.out.println("Common Lower Case: " + commonLower);
      System.out.println("Common Upper Case: " + commonUpper);
      System.out.println("Common Other: " + commonMisc);
   }
}