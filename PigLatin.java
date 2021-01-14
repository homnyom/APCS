import java.util.*;
import java.io.*;

public class PigLatin
{
   public static void main(String[] args) throws FileNotFoundException
   {
      part_1();
      //part_2();
   }
   public static String pig(String s)
   {
      String vowels = new String("aeiouAEIOU");
      String capital = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
      String punct = new String(".?!:;,");
      int start = 0; //Starting index of the manipulated part of the string
      int stop = s.length() - 1; //Last index of the manipulated part of the string
      boolean capitalized = false; //Indicates if a String is capitalized
      
      //Checks for blank string
      if(s.equals(""))
         return s;
      //Does not contain vowel
      int count = 0;
      for(int x = 0; x < vowels.length(); x++)
      {
         if(!s.contains("" + vowels.charAt(x)))
         {
            count++;
            if(count == 10 && !s.contains("y"))
               return "INVALID";
         }
      }
      //Checks for quotations
      if(s.charAt(start) == '"' && s.charAt(stop) == '"')
      {
         start++;
         stop--;
      }
      //Checks for punctuation
      int tempStop = stop;
      for(int x = 0; x < punct.length(); x++)
      {
         for(int y = start; y <= tempStop; y++)
         {
            if(punct.charAt(x) == s.charAt(y))
               stop--;
         }
      }
      //Checks for capitalization
      for(int x = 0; x < capital.length(); x++)
      {
         if(capital.charAt(x) == s.charAt(start))
         {
            capitalized = true;
            s = lower(s, start);
         }
      }       
      //Starts with vowel
      if(vowels.contains("" + s.charAt(start)))
      {
         if(capitalized)
            return s.substring(0, start) + capitalize(s.substring(start, stop + 1)) + "way" + s.substring(stop + 1);
         else
            return s.substring(0, start) + s.substring(start, stop + 1) + "way" + s.substring(stop + 1);
      }
      
      vowels = "aeiouy"; //includes y as a vowel
   
      //Starts with consonant
      for(int x = start + 1; x <= stop; x++)
      {
         if(vowels.contains("" + s.charAt(x)))
         {
            //"qu" special case
            if(s.charAt(x) == 'u' && s.charAt(x - 1) == 'q')
            {
               int vowelPos = x + 1;
               if(capitalized)
                  return s.substring(0, start) + capitalize(s.substring(vowelPos, stop + 1)) + s.substring(start, vowelPos) + "ay" + s.substring(stop + 1);
               else
                  return s.substring(0, start) + s.substring(vowelPos, stop + 1) + s.substring(start, vowelPos) + "ay" + s.substring(stop + 1);
            }
            //Basic case
            if(capitalized)
               return s.substring(0, start) + capitalize(s.substring(x, stop + 1)) + s.substring(start, x) + "ay" + s.substring(stop + 1);
            else
               return s.substring(0, start) + s.substring(x, stop + 1) + s.substring(start, x) + "ay" + s.substring(stop + 1);
         }
      }
      return s;
   }
   //Helper Methods
   private static String capitalize(String s) //Formats given String to title case
   {
      return s.substring(0, 1).toUpperCase() + s.substring(1);
   }
   private static String lower(String s, int index)//Makes a the character at a given index in a given string lowercase
   {
      return s.substring(0, index) + s.substring(index, index + 1).toLowerCase() + s.substring(index + 1);
   }
   
   public static void part_1()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println("***** " + p + " *****");
      }		
   }
   public static void part_2() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("What filename? ");
      String filename = sc.next();
      sc.close();
      piglatenizeFile(filename);
   }
   public static void piglatenizeFile(String filename)
   {
      Scanner infile = null;
      PrintStream outfile = null;
      
      try{
         infile = new Scanner(new File(filename));
      } 
      catch(Exception e){
         System.out.print("File not found.");
         System.exit(0);
      }
   
      String newFilename = pig(filename.substring(0, filename.length() - 4)) + ".txt";
      try{
         outfile = new PrintStream(newFilename);
      } 
      catch(FileNotFoundException e)
      {
         System.out.print("File not found.");
         System.exit(0);
      }
      
      while(infile.hasNextLine())
      {
         String s = infile.nextLine();
         while(s.contains(" "))
         {
            outfile.print(pig(s.substring(0, s.indexOf(" "))) + " ");
            s = s.substring(s.indexOf(" ") + 1);
         }
         outfile.print(pig(s));
         outfile.println();
      }
      
      infile.close();
      outfile.close();
   }
}
