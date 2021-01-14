  //name: Natalie Homnyom   date: 1/17/2016
import java.util.*;
public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  -->  Postfix  -->  Evaluate");
      /*enter code here  */
      ArrayList<String> infixExp = new ArrayList();
      infixExp.add("3+4*5");
      infixExp.add("3*4+5");
      infixExp.add("(4+5)-5*3");
      infixExp.add("(3+4)*(5+6)");
      infixExp.add("(3*(4+5)-2)/5");
      infixExp.add("8+1*2-9/3"); 
   
      for( String s : infixExp )
      {
         String pf = infixToPostfix(s);
         System.out.println(s + "       " + pf + "       " + Postfix.eval(pf));
      }
   }
   public static String infixToPostfix(String infix)
   {
      Stack<Character> stack = new Stack<Character>();
      String pf = "";
      String operators = "+-*/";
      String integers = "0123456789";
      
      for(int x = 0; x < infix.length(); x++)
      {
         char ch = infix.charAt(x);
         String s = ch + "";
         
         if(integers.contains(s))
            pf += ch;
         else if(ch == '(')
            stack.push(ch);
         else if(ch == ')')
         {
            while(stack.peek() != '(')
               pf += stack.pop();
            stack.pop();
         }
         else if(Postfix.isOperator(ch))
         {
            while(!(stack.empty() || stack.peek() == '(' || isLower(stack.peek(), ch)))
               pf += stack.pop();
            stack.push(ch);
         }
      }
      while(!stack.empty())
         pf += stack.pop();
      
      return pf;
   }
	//returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      int a = attachValue(c1);
      int b = attachValue(c2);
      
      if(a < b)
         return true;
      return false;
   }
         public static int attachValue(char ch)
         {
            if(ch == '*' || ch == '/')
               return 1;
            else
               return 0;
         }
}

class Postfix
{

   public static int eval(String postfix)
   {         
      Stack<Integer> stack = new Stack<Integer>();
      for(int x = 0; x < postfix.length(); x++)
      {
         char ch = postfix.charAt(x);
         String s = ch + "";
         
         if(isOperator(ch) && !stack.empty())
            stack.push(eval(stack.pop(), stack.pop(), ch));
         else
            stack.push(Integer.parseInt(s));
      }
      return stack.pop();
   }
   public static int eval(int a, int b, char ch)
   {
      if(ch == '+')
         return a + b;
      else if(ch == '-')
         return b - a;
      else if(ch == '*')
         return a * b;
      else
         return b / a;
   }
   public static boolean isOperator(char ch)
   {
      String operator = "+-*/";
      String s = ch + "";
      
      if(operator.contains(s))
         return true;
      return false;
   }
}	
	/*
 Infix  -->  Postfix  -->  Evaluate
 3+4*5       345*+       23
 3*4+5       34*5+       17
 (4+5)-5*3       45+53*-       -6
 (3+4)*(5+6)       34+56+*       77
 (3*(4+5)-2)/5       345+*2-5/       5
 8+1*2-9/3       812*+93/-       7
	*/
