///name: Natalie Homnyom   date: 1/20/16
//first program on queues.
import java.io.*;
import java.util.*;
public class SongQueue
{
   private static Scanner infile;
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      fillPlayList();
      printSongList();
      infile = new Scanner(System.in);
      String prompt = "\tAdd song (A), Play song (P), Delete song (D), Quit (Q):  ";
      System.out.print(prompt);
      String str = infile.next().toUpperCase();
      while(!str.equals("Q"))
      { 
         processRequest( str );
         
         printSongList();
         
         System.out.print(prompt);
         str = infile.next().toUpperCase();;
      } 
      System.out.println();
      System.out.println("No more music for you today.  Goodbye!");
      infile.close();
   }
   public static void fillPlayList()throws IOException
   {
      songQueue = new LinkedList<String>();
      
      Scanner read = new Scanner(new File("songs.txt"));
      while(read.hasNextLine())
      {
         String s = read.nextLine();
         int pos = s.indexOf('-');
         songQueue.add(s.substring(0, pos - 1));
      }
      
      read.close();
   }
   public static void processRequest(String str)
   {
      if(str.equalsIgnoreCase("A"))
      {
         System.out.print("Song to add? ");
         add();
      }
      else if(str.equalsIgnoreCase("P"))
      {
         System.out.print("Now playing: ");
         play();       
      }
      else if(str.equalsIgnoreCase("D"))
      {
         System.out.print("Enter song to delete (exact match): ");
         delete();
      }
      else
         System.out.println("Invalid command.");
   }
   public static void add()
   {
      Scanner read = new Scanner(System.in);
      String song = read.next();
      songQueue.add(song);
   }
   public static void play()
   {
      if(songQueue.peek() != null)   
         System.out.println(songQueue.remove());
      else
      {
         System.out.println("No more music for you today.  Goodbye!");
         System.exit(0);
      }
   }
   public static void delete()
   {
      Scanner read = new Scanner(System.in);
      String song = read.nextLine();
      songQueue.remove(song);
   }
   public static void printSongList()
   {
      String s = "Your music queue: [";
      
      for(String song: songQueue)
         s += song + ", ";
      
      System.out.println(s.substring(0, s.length()-2) + "]");
   }
}