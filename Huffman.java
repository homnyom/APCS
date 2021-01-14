// name: Natalie Homnyom       date: May 20, 2016
import java.util.*;
import java.io.*;
public class Huffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Encode what message? ");
      String message = keyboard.nextLine();
      
      Map<String, Integer> m = createFrequencyTable(message);
      Iterator<String> it = m.keySet().iterator();
      Queue<HuffmanTreeNode> q = new PriorityQueue<HuffmanTreeNode>();
     
      while(it.hasNext())
      {
         String character = it.next();
         int freq = m.get(character);
         q.add(new HuffmanTreeNode(character, freq));
      }
   
      System.out.print("What file name? ");
      String file = keyboard.nextLine();
      HuffmanTreeNode root = createSchemeTree(q);
      
      PrintStream scheme = new PrintStream(new File("scheme." + file + ".txt"));
      createScheme(scheme,root, "");
      PrintStream code = new PrintStream(new File("message." + file + ".txt"));
      createCode(code, m, root, message);
      	//Read the string
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter.  As you go, build the binary path, where going 
   		//       left is 0 and going right is 1.  
      	//Write the binary path to the hard drive as message.xxx.txt
      	//Write the scheme to the hard drive as scheme.xxx.txt
   }
   
   public static Map createFrequencyTable(String s)
   {
      Map<String, Integer> m = new HashMap<String, Integer>();
      for(int x = 0; x < s.length(); x++){
         String letter = s.charAt(x) + "";
            
         if(m.containsKey(letter)){
            int freq = m.get(letter);
            m.put(letter, freq + 1);
         }
         else
            m.put(letter, 1);
      }
      return m; 
   }
    
   public static HuffmanTreeNode createSchemeTree(Queue<HuffmanTreeNode> q)
   {
      while(!q.isEmpty())
      {
         HuffmanTreeNode less = q.remove();
         if(q.isEmpty())
            return less;
         else{
            HuffmanTreeNode greater = q.remove();
            q.add(new HuffmanTreeNode("*", less.getFrequency() + greater.getFrequency(), less, greater));  
         }
      }
      return null;
   }
   
   public static void createScheme(PrintStream print, HuffmanTreeNode root, String s)
   {
      if(root.getLeft() == null && root.getRight() == null)
         print.println(root.getValue() + s);
      else{
         s += "0";
         createScheme(print, root.getLeft(), s);
         s = s.substring(0, s.length() - 1) + "1";
         createScheme(print, root.getRight(), s);
      }
   }
   
   public static void createCode(PrintStream code, Map<String, Integer> m, HuffmanTreeNode root, String message)
   {
      for(int x = 0; x < message.length(); x++)
      {
         String s = message.charAt(x) + "";
         code.print(findCode(root, s, m.get(s), ""));
      }
      code.close();
   }
   private static String findCode(HuffmanTreeNode current, String target, int freq, String s)
   {
      HuffmanTreeNode left = current.getLeft();
      HuffmanTreeNode right = current.getRight();
      String obj = (String) current.getValue();
      
      if(target.equals(obj))
         return s;
      else if((left == null && right == null) || current.getFrequency() < freq)
         return "";
      else{
         String one = findCode(current.getLeft(), target, freq, s + "0");
         String two = findCode(current.getRight(), target, freq, s + "1");
         
         if(one.equals(""))
            return two;
         return one;
      }
   }
}

	/*
	* This node stores two values.  
	* The compareTo method must ensure that the lowest frequency has the highest priority.
	*/
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private Object value;
   private int frequency; 
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(Object initValue, int freq)
   { 
      value = initValue;
      frequency = freq; 
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(Object initValue, int freq, HuffmanTreeNode l, HuffmanTreeNode r)
   {
      value = initValue;
      frequency = freq;
      left = l;
      right = r;
   } 
   
   public Object getValue()
   { 
      return value; 
   }
   
   public int getFrequency()
   {
      return frequency;
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void increment()
   {
      frequency++;
   }
   
   public void increment(int n)
   {
      frequency += n;
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   
   public int compareTo(HuffmanTreeNode other)
   {
      return frequency - other.frequency;
   }
}