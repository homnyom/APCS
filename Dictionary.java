//Name: Natalie Homnyom     Date: March 18, 2016
import java.io.*;
import java.util.*;
public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
         System.out.println("File does not exist. System will now exit.");
         System.exit(0);
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> output = new TreeMap<String, Set<String>>();
      
      while(infile.hasNext())
      {
         String eng = infile.next();
         String span = infile.next();
         add(output, eng, span);
      }
      
      return output;
   }
   private static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
      if(dictionary.get(word) == null)
         dictionary.put(word, new TreeSet<String>());
      dictionary.get(word).add(translation);
   }
   public static void display(Map<String, Set<String>> m)
   {
      Iterator<String> it = m.keySet().iterator();
      
      while(it.hasNext())
      {
         String s = it.next();
         System.out.println(s + " " + m.get(s));
      }
      
      System.out.println();
   }
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
      
      Iterator<String> it = dictionary.keySet().iterator();
      while(it.hasNext())
      {
         String eng = it.next();
         Set<String> holder = dictionary.get(eng);
         Iterator<String> it2 = holder.iterator();
         
         while(it2.hasNext())
         {
            String span = it2.next();
            add(map, span, eng);
         }
      }
      
      return map;
   }
}
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/