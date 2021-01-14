//  name: Natalie Homnyom       date: December 9, 2015
//  implements the List interface with a backing array of Objects. 
//	 also overrides toString().
 
public class TJArrayList_Driver
{  
   public static void main(String [] args)
   {
      TJArrayList myList = new TJArrayList();	
   
      myList.add("Apple");
      myList.add("Banana");
      myList.add("Dates");
      myList.add(2, "Cucumber");
      System.out.println(myList);
      System.out.println("Size is " + myList.size());
      System.out.println("Get 2: " + myList.get(2));
      System.out.print("Set at 2: ");
      myList.set(2, "Cheese");
      System.out.println(myList);
      Object obj = myList.remove(2);
      System.out.println("Removed " + obj+ " from " + myList);
      System.out.println("Size is " + myList.size());
      System.out.print("Add too many items: ");
      for(int i = 3; i <= 10; i++)
         myList.add(new Integer(i));
      System.out.println(myList);
      System.out.println("Size is " + myList.size());   	
      System.out.println("Contains \"Bread\"?  " + myList.contains("Bread"));
      System.out.println("Contains \"Banana\"?  " + myList.contains("Banana"));
   }
}

class TJArrayList
{
   private int size;							//stores the number of objects
   private Object[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      size = 0;
      myArray = new Object[10];
   }
   public int size()
   {
      return size;
   }
	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
      @return true  */
   public boolean add(Object obj)
   {
      if(size < myArray.length)
         myArray[size] = obj;
      else
      {
         Object[] newArray = new Object[2 * myArray.length];
         for(int x = 0; x < myArray.length; x++)
            newArray[x] = myArray[x];
         newArray[myArray.length] = obj;
         myArray = newArray;
      }
      size++;
      return true;
      
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, Object obj)
   {
      int position = index - 1;
      if(size < myArray.length)
      {
         for(int x = size; x > position; x--)   
            myArray[x] = myArray[x - 1];
         myArray[position] = obj;
      }
      else
      {
         Object[] newArray = new Object[2 * myArray.length];
         for(int x = 0; x < position; x++)
            newArray[x] = myArray[x];
         newArray[position] = obj;
         for(int x = position + 1; x < size + 1; x++)
            newArray[x] = myArray[x - 1];
         myArray = newArray;
      }
      size++;
   }
   /* return obj at position index.  
		*/
   public Object get(int index)
   {
      return myArray[index - 1];
   }
   /* replaces obj at position index.  
		*/
   public void set(int index, Object obj)
   {
      myArray[index - 1] = obj;
   }
   /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object at position index.
	  */
   public Object remove(int index)
   {
      int position = index - 1;
      Object returnThis = myArray[position];
      for(int x = position; x < myArray.length - 1; x++)
         myArray[x] = myArray[x + 1];
      myArray[myArray.length - 1] = null;
      size--;
      return returnThis;
   }
      /*
   this method compares objects and should use .equals(), not ==
  	*/
   public boolean contains(Object obj)
   {
      for(int x = 0; x < size; x++)
      {
         if(myArray[x].equals(obj))
            return true;
      }
      return false;
   }
   /*returns the Objects in the array, nicely formatted, 
	   e.g. [Apple, Banana, Cucumber, Date]
     	*/
   public String toString()
   {
      String s = "[";
      for(int x = 0; x < size - 1; x++)
      {
         if(myArray[x] != null)
            s += myArray[x] + ", ";
      }
      if(myArray[size - 1] != null)
         return s + myArray[size - 1] + "]";
      else
         return s;
   }
}