//Miss Galanos
//version 12.8.2015

import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.2.jar
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.ListIterator;
import java.text.*;

public class Twitter_Driver
{
   private static PrintStream consolePrint;
   
   public static void main (String []args) throws TwitterException, IOException
   {
      consolePrint = System.out;
      
      // PART 1
      // set up classpath and properties file
             
      TJTwitter bigBird = new TJTwitter(consolePrint);
   //       String message = "FISH FISH FISH";
   //       bigBird.tweetOut(message);
     
      // PART 2
      // Choose a public Twitter user's handle 
         
   //       Scanner scan = new Scanner(System.in);
   //          consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
   //          String twitter_handle = scan.next();
   //             
   //          while (!twitter_handle.equals("done"))
   //          {
   //             // Print the most popular word they tweet
   //             bigBird.makeSortedListOfWordsFromTweets(twitter_handle);
   //             consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.mostPopularWord());
   //             consolePrint.println();
   //             consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
   //             twitter_handle = scan.next();
   //          }
   //          
      // PART 3
      bigBird.investigate();
         
         
   }//end main         
         
}//end driver        
         
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private List<String> sortedTerms;
   
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      twitter = TwitterFactory.getSingleton(); //connects to Twitter and performs authorizations
      consolePrint = console;
      statuses = new ArrayList<Status>();
      sortedTerms = new ArrayList<String>();   
   }
   
   /******************  Part 1 *******************/
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);
   }
   @SuppressWarnings("unchecked")
   /******************  Part 2 *******************/
   public void makeSortedListOfWordsFromTweets(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      sortedTerms.clear();
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); // Creates file for dedebugging purposes
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      int numberTweets = statuses.size();
      fileout.println("Number of tweets = " + numberTweets);
      
      fileout = new PrintStream(new FileOutputStream("garbageOutput.txt"));
   
      int count=1;
      for (Status j: statuses)
      {
         fileout.println(count+".  "+j.getText());
         count++;
      }
     	//Makes a list of words from user timeline
      for (Status status : statuses)
      {			
         String[]array = status.getText().split(" ");
         for (String word : array)
            sortedTerms.add(removePunctuation(word).toLowerCase());
      }
      // Remove common English words, which are stored in commonWords.txt
      sortedTerms = removeCommonEnglishWords(sortedTerms);
      sortAndRemoveEmpties();
   }
   
   // Sort words in alpha order. You should use your old code from the Sort/Search unit.
   // Remove all empty strings ""
   @SuppressWarnings("unchecked")
   private void sortAndRemoveEmpties()
   {
      Selection.sort(sortedTerms);
      List<String> newList = new ArrayList();
      for(String thing: sortedTerms)
         if(!thing.equals("") || !thing.contains("\n"))
            newList.add(thing);
      sortedTerms = newList;
   }
   
   // Removes common English words from list
   // Remove all words found in commonWords.txt  from the argument list.
   // The count will not be given in commonWords.txt. You must count the number of words in this method.
   // This method should NOT throw an exception. Use try/catch.
   @SuppressWarnings("unchecked")
   private List removeCommonEnglishWords (List<String> list)
   {
      //Reading file and inputting into a list called commonWords.
      Scanner input = null;
      try{
         input = new Scanner(new File("commonWords.txt"));
      }
      catch(Exception e)
      {
         System.out.println("File not found.");
         System.exit(0);
      }
      List<String> commonWords = new ArrayList<String>();
      while(input.hasNext())
         commonWords.add(input.next());
      input.close();
         
      //Removes words and counts
      List<String> newList = new ArrayList();
      for(String s: list)
      {
         boolean contained = false;
         for(String t: commonWords)
         {
            if(t.equalsIgnoreCase(s))
               contained = true;
         }
         if(!contained)
            newList.add(s);
      }
      return newList;
   }
   
   //Remove punctuation - borrowed from previous lab
   //Consider if you want to remove the # or @ from your words. They could be interesting to keep (or remove).
   public String removePunctuation(String s)
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
   //Should return the most common word from sortedTerms. 
   //Consider case. Should it be case sensitive? The choice is yours.
   @SuppressWarnings("unchecked")
   public String mostPopularWord()
   {
      int wordCount = 0;
      int freq = 0;
      String prev = null;
      String returned = sortedTerms.get(0);
      for(String s: sortedTerms)
      {
         if(prev != null && prev.equalsIgnoreCase(s))
            wordCount++;
         else
         {
            if(wordCount > freq)
            {
               returned = prev;
               freq = wordCount;  
            }
            wordCount = 1;
         }
         prev = s;
      }
      return returned;
   }
   

   /******************  Part 3 *******************/
   public void investigate() throws TwitterException, IOException
   {
      //Unfollows people who have mentioned Donald Trump in more than 5% of their tweets in the last 100 tweets
      DecimalFormat decimal = new DecimalFormat("0.00%");
      PagableResponseList<User> following = null;   
      following = twitter.getFriendsList(twitter.getId(), -1);
      
      for(User u: following)
      {  
         long numbers = u.getId();
         
         Paging page = new Paging (1);
         List<Status> stat = new ArrayList(100);
         for(int x = 1; x <= 5; x++)
         {
            page.setPage(x);
            stat.addAll(twitter.getUserTimeline(numbers, page));
         }
         
         if(stat.size() <= 100)
         {
            double trumpMentions = 0;
            for(Status s: stat)
            {
               String cont = s.getText();
               
               if(cont.contains("Trump") || cont.contains("trump"))
                  trumpMentions++;  
            }
            if(trumpMentions/stat.size() >= 0.05)
            {
               twitter.destroyFriendship(u.getScreenName());
               System.out.println("You have unfollowed @" + u.getScreenName() + ".");
               System.out.println(decimal.format(trumpMentions/stat.size()) + " of " + "@" + u.getScreenName() + "'s tweets included something about Trump.");
            }
         } 
      }
   }
   // A sample query to determine how many people in Arlington, VA tweet about the Miami Dolphins
   public void sampleInvestigate ()
   {
      Query query = new Query("Miami Dolphins");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(38.8372839,-77.1082443), 5, Query.MILES);
      query.setSince("2015-12-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
   }  
   
}  
// Consider adding a sorter class here.
class Selection
{
   public static void sort(List<String> list)
   {
      for(int x = list.size() - 1; x >= 1; x--)
      {
         int maxLocation = findMax(list, x);
         swap(list, maxLocation, x);
      }
   }
   private static int findMax(List<String> list, int n)
   {
      int maxLocation = 0;
      for(int x = 1; x < n; x++)
      {
         if(list.get(maxLocation).compareTo(list.get(x)) < 0)
            maxLocation = x;
      }
      return maxLocation;
   }
   private static void swap(List<String> list, int a, int b)
   {
      String temp = list.get(a);
      list.set(a, list.get(b));
      list.set(b, temp);
   }
}
