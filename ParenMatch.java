// name: Natalie Homnyom    date: 1/15/16

import java.util.*;
public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter tests here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7]*3)");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3]");
                  
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   public static boolean checkParen(String exp)
   {
      String nums = "0123456789";
      String oper = "/*+-";
      
      Stack<Character> stack = new Stack<Character>();
      boolean check = false;
      for(int x = 0; x < exp.length(); x++)
      {
         String s = exp.charAt(x) + "";
         char c = exp.charAt(x);
         if(oper.contains(s))
            check = true;
         else if(left.contains(s))
         {
            stack.push(c);
            check = false;
         }
         else if(right.contains(s))
         {
            if(check)
               return false;
            else if(!stack.empty() && match(stack.peek(), c))
               stack.pop();
            else
               return false;
         }
         else if(nums.contains(s))
            check = false;
         else if(c != ' ')
            return false;
      }
      if(!stack.empty())
         return false;
      return true;
   }
   public static boolean match(char last, char current)
   {
      if(last == '(' && current == ')')
         return true;
      else if(last == '[' && current == ']')
         return true;
      else if(last == '{' && current == '}')
         return true;
      else if(last == '<' && current == '>')
         return true;
      return false;
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
