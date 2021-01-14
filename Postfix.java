   //name: Natalie Homnyom    date: 1/15/2016
   
import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postExp = new ArrayList<String>();
      /*  enter your code here  */
      postExp.add("345*+");
      postExp.add("34*5+");
      postExp.add("45+53*-");
      postExp.add("34+56+*");
      postExp.add("345+*2-5/");
      postExp.add("812*+93/-");
      
      for( String pf : postExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static int eval(String postfix)
   {
      Stack<Integer> stack = new Stack<Integer>();
      for(int x = 0; x < postfix.length(); x++)
      {
         char ch = postfix.charAt(x);
         String s = ch + "";
         
         if(isOperator(ch))
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