//Natalie Homnyom; March 30, 2016

import java.util.Scanner;
import java.util.*;
import java.io.*;
   
/*
   This program implements encoding and decoding Morse code
   Originally Written by: Stuart Hansen
   Date: April 29, 2014
   Slightly modified by:  Ria Galanos
   Date: March 24, 2016
*/

public class MorseCode_Driver
{
   public static void main (String []args) throws Exception
   {
      MorseCode test = new MorseCode();
      Scanner scan = new Scanner (System.in);
      while( true )
      {
         System.out.print("Press 1 to Encode, 2 to Decode, 3 to Quit: ");
         int choice = scan.nextInt();
         if( choice == 3 )
         {
            System.out.println("Goodbye");
            System.exit(0);
         }
         else if (choice == 1)
         {
            System.out.print("Enter file to encode: --> ");
            Scanner scan2 = new Scanner(System.in);
            String toBeEncoded = scan2.next();
            test.encodeFile(toBeEncoded, "encoded.txt");
            System.out.println("Encoded.");
         }
         else if (choice == 2)
         {
         // System.out.print("Enter file to decode: --> ");
         // Scanner scan3 = new Scanner(System.in);
         // String toBeDecoded = scan3.next();
            test.decodeFile("encoded.txt", "decoded.txt");
            System.out.println("Decoded.");
         }
         else
            System.out.println(":{"); 
      }  
   }
}      
  
class MorseCode 
{
   private TreeMap<Character, String> toCode;  // used for encoding
   private TreeNode root;

   // The constructor builds both the encoding tree and decoding tree
   // by reading codes from a file.
   public MorseCode() throws Exception 
   {
      toCode = new TreeMap<Character, String>();
      root = new TreeNode(null);
   
      File file = new File("MorseCode.txt");
      Scanner fin = new Scanner(file);
   
      // Continue to add codes until the end of the file
      while (fin.hasNext()) 
      {
         String letter = fin.next();
         String code = fin.next();
         toCode.put(letter.charAt(0), code);
         addToDecodeTree(letter, code);
      }
        
      // Add a couple of special characters.
      toCode.put(' ', "*");
      toCode.put('\n', "+");
   }

   // This private method adds a letter to the decoding map
   private void addToDecodeTree(String letter, String code) 
   {
      // Find or build the location
      TreeNode current = root;
        
      // Because the data may not be in a left to right ordering
      // we will build the tree and leave the elements empty if we
      // don't know currently know their values.
      for (int i = 0; i < code.length(); i++) 
      {
         char dotDash = code.charAt(i);
            
         // Move left for '.' and right for '-'
         if (dotDash == '.') 
         {
            if (current.getLeft() == null) 
            {
               current.setLeft(new TreeNode(null));
            }
            current = current.getLeft();
         } 
         else 
         {
            if (current.getRight() == null) 
            {
               current.setRight(new TreeNode(null));
            }
            current = current.getRight();
         }
      }
      // Store the data in the leaf node.
      current.setValue(letter);
   }

   // This method uses the TreeMap to convert a text file (encode) 
   // to a MorseCodeComplete file. Use TreeMap<Character, String> toCode.  
   // Keys are characters, space, and /n.
   // Assume all uppercase.  Value is morse code string where 
   // space is * and /n is +.
   public void encodeFile(String inputFilename, String outputFilename) throws Exception 
   {
      Scanner in = new Scanner(new File(inputFilename));
      PrintStream out = new PrintStream(new File(outputFilename));
      
      while(in.hasNext())
      {
         String s = in.next();
         for(int x = 0; x < s.length(); x++)
         {
            if(s.charAt(x) == ' ')
               out.println("*");
            else
               out.println(toCode.get(s.charAt(x)));
         }
         out.println("+");
      }     
   }
    
   // This method decodes a file using the morse code tree
   // created in the constructor
   // Read a code String from the input file, translate it to its corresponding char,
   // and write that character to the file. 
   // To translate the String to a character, use the morseRoot tree 
   // (except for the two special strings, * and +).
   public void decodeFile(String inputFilename, String outputFilename) throws Exception 
   {
      Scanner in = new Scanner(new File(inputFilename));
      PrintStream out = new PrintStream(new File(outputFilename));
      
      while(in.hasNext())
      {
         String s = in.next();
         
         if(s.equals("+"))
            out.println();
         else
         {
            TreeNode current = root;
            for(int x = 0; x < s.length(); x++)
            {
               if(s.equals("*"))
                  out.print(" ");
               else if(s.charAt(x) == '.')
                  current = current.getLeft();
               else
                  current = current.getRight();  
            }
            out.print(current.getValue());
         }
      }
   }   
}

class TreeNode
{
   private Object value; 
   private TreeNode left, right;
    
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
    
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode 
               initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
    
   public Object getValue()
   { 
      return value; 
   }
    
   public TreeNode getLeft() 
   { 
      return left; 
   }
    
   public TreeNode getRight() 
   { 
      return right; 
   }
    
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
    
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
    
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
      
      
