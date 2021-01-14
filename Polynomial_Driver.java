 //Name: Natalie Homnom  Date: March 18, 2016
 //modeling a polynomial using a treeMap
import java.util.*;
public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
   	   	
      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1); 
      System.out.println(poly2.toString());
   	
      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Comparator c = new polyComparator();
   private Map<Integer, Integer> terms = new TreeMap<Integer, Integer>(c);
   
   public Map<Integer, Integer> getTerms()
   {
      return terms;
   }
   public void makeTerm(Integer exp, Integer coef)
   {
      terms.put(exp, coef);
   }
   public double evaluateAt(double x)
   {
      double sum = 0;
      Iterator<Integer> it = terms.keySet().iterator();
      
      while(it.hasNext())
      {
         int exp = it.next();
         sum += Math.pow(x, exp) * terms.get(exp);
      }
      
      return sum;
   }
   public Polynomial add(Polynomial other)
   {
      Polynomial poly = new Polynomial();
      Map<Integer, Integer> otherTerms = other.getTerms();
      Iterator<Integer> poly1 = terms.keySet().iterator();
      Iterator<Integer> poly2 = otherTerms.keySet().iterator();
      
      while(poly1.hasNext() && poly2.hasNext())
      {
         int exp1 = poly1.next();
         int exp2 = poly2.next();
      
         if(exp1 == exp2)
         {
            int coeff1 = terms.get(exp1);
            int coeff2 = otherTerms.get(exp2);
            
            poly.addTerm(exp1, exp2)
         }
         else
         {
            if(exp1 > exp2)
            
            else
         }
      }
   }
   public Polynomial multiply(Polynomial other)
   {
      return null;
   }
   public String toString()
   {
      Iterator<Integer> it = terms.keySet().iterator();
      String s = "";
      
      while(it.hasNext())
      {
         int exp = it.next();
         int coeff = terms.get(exp);
         
         if(coeff == 1)
         {
            if(exp > 1)
               s = "x^" + exp + " + " + s;
            else if(exp == 1)
               s = "x + " + s; 
         }
         else
         {
            if(exp > 1)
               s += coeff + "x^" + exp + " + " + s;
            else if(exp == 1)
               s += coeff + "x + " + s;
            else
               s += coeff + s; 
         }
      }
      
      return s;
   }
}
public polyComparator<Integer> extends Comparator<Integer>
{
   public int compare(int first, int second)
   {
      return second - first;
   }
   public boolean equals()
   {
      return false;
   }
}
/*  
expected output
   2x^3 + -4x + 2
   10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */