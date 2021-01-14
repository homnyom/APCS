// Name: Natalie Homnyom             Date: May 18th, 2016
import java.util.Scanner;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode(null);
      TreeNode current = root;
      
      while(huffLines.hasNextLine())
      {
         String s = huffLines.nextLine();
         if(s != ""){
            String leaf = s.substring(0,1);
            String code = s.substring(1);
         
            for(int x = 0; x < code.length(); x++)
            {
               if(code.charAt(x) == '0')
               {
                  if(current.getLeft() == null)
                     current.setLeft(new TreeNode(null));
                  current = current.getLeft();
               }
               else
               {
                  if(current.getRight() == null)
                     current.setRight(new TreeNode(null));
                  current = current.getRight();
               }
            }
            current.setValue(leaf);
            current = root;
         }
      } 
      
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      String s = "";
      TreeNode value = root;
      
      for(int x = 0; x < text.length(); x++)
      {
         Object obj = value.getValue();
         if(obj == null)
         {
            if(text.charAt(x) == '0')
               value = value.getLeft();
            else
               value = value.getRight();
         }
         
         obj = value.getValue();
         if(obj != null)
         {
            s += obj;
            value = root;
         }  
      }   
      return s;
   }
}

 /* TreeNode class for the AP Exams */
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
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
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
