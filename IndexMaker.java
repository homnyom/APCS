//  Name: Natalie Homnyom     date: January 6, 2015
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
   public DocumentIndex()
   {
      super();
   }
   public DocumentIndex(int newCapacity)
   {
      super(newCapacity);
   }
   /** calls foundOrInserted, which returns an index.  At that position,  
   updates that IndexEntry's list of line numbers with num. */
   public void addWord(String word, int num)
   {
      int index = foundOrInserted(word);
      IndexEntry ent = get(index);
      ent.add(num);
   }
      
    /** extracts all the words from str, skipping punctuation and whitespace 
    and for each word calls addWord(word, num).  A good way to skip punctuation 
    and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int num) 
   {
      if(!str.equals(""))
      {
         String[] words = str.split(" ");
         for(String s: words)
         {
            s = removePunct(s);
            if(!s.equals(""))
               addWord(s, num);
         }
      }
   }
   public String removePunct(String s)
   {
      String punct = ":\",.!?;()\n";
      for(int x = 0; x < punct.length(); x++)
      {
         String c = punct.charAt(x) + "";
         while(!c.equals("") && s.contains(c))
         {
            int pos = s.indexOf(c);
            if(pos == 0)
               s = s.substring(1);
            else if(pos != s.length() - 2)
               s = s.substring(0, pos) + s.substring(pos + 1);
            else
               s = s.substring(0, pos);
         }
      }
      return s;
   }
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      ListIterator<IndexEntry> it = listIterator();
      boolean found = false;
      int count = 0;
      while(!found && count != size())
      {
         String s = it.next().getWord();
         if(word.toUpperCase().compareTo(s) < 0)
            break;
         else if(s.equalsIgnoreCase(word))
            found = true;
         else
            count++;
      }
      if(!found)
      {
         IndexEntry ent = new IndexEntry(word);
         add(count, ent);
      }
         
      return count;
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
   private String word;
   private ArrayList<Integer> numslist;
     //constructors
   public IndexEntry(String s)
   {
      word = s.toUpperCase(); 
      numslist = new ArrayList<Integer>();  
   }
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      if(!numslist.contains(num))   
         numslist.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
   
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String s = word;
      for(int x: numslist)
         s = s + " " + x + ", ";
      return s.substring(0, s.length() - 2);
   }
   
   public int compareTo(IndexEntry i)
   {
      return word.compareTo(i.getWord());
   } 
}

