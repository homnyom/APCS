//Natalie Homnyom 11/18/2015
import java.util.Scanner;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Extension  ****/
      
   	System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   		
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   public void delete(ListNode head, int index)
   {
      if(index == 0)
         head = head.getNext();
      else
      {
         ListNode pointer = head;
         int current = 0;
         while(current < index - 1)
         {
            pointer = pointer.getNext();
            current++;
         }
         pointer.setNext(pointer.getNext().getNext());
      }
   }
   public void insert(ListNode head, Object val, int index)
   {
      ListNode newFirst;
      if(index == 0)
         newFirst = new ListNode(val, head);
      else
      {
         ListNode pointer = head;
         int current = 0;
         while(current < index)
         {
            pointer = pointer.getNext();
            current++;
         }
         ListNode temp = new ListNode(pointer.getValue(), pointer.getNext());
         pointer = new ListNode(val, temp);
      }
   }
   private static ListNode copyNode(ListNode arg)
   {
      ListNode copy = new ListNode(arg.getValue(), arg.getNext());
      return copy;
   }
   private static ListNode copyList(ListNode arg)
   {
      ListNode copy = arg;
      while(copy.getNext() != null)
         copy = new ListNode(arg.getValue(), copyList(arg.getNext()));
      return copy;
   }
   private static ListNode rest(ListNode h)
   {
      ListNode copy = new ListNode(h.getNext(), copyList(h.getNext().getNext()));
      return copy;
   }
   public static Object first(ListNode head)
   {
      return head.getValue();
   }
   public static Object second(ListNode head)
   {
      return head.getNext().getValue();
   }
   public static ListNode pointerToLast(ListNode head)
   {
      ListNode pointer = head;
      while(pointer.getNext() != null)
         pointer = pointer.getNext();
      return pointer;
   }
   public static ListNode copyOfLast(ListNode head)
   {
      ListNode copy;
      if(head.getNext() == null)
      {
         copy = new ListNode(head.getValue(), head.getNext());
         return copy;
      }
      else
         return copyOfLast(head.getNext());
   }
   public static ListNode insertFirst(ListNode head, Object arg)
   {
      ListNode newFirst = new ListNode(arg, head);
      return newFirst;
   }
   public static ListNode insertLast(ListNode head, Object arg)
   {
      ListNode copy = copyNode(head); 
      while(copy.getNext() != null)
         copy = copy.getNext();
      copy.setNext(new ListNode(arg, null));
      return head;
   }
}

class ListNode
{
   private Object value;
   private ListNode next;
   
   public ListNode(Object initValue, ListNode initNext)
   {
      value = initValue;
      next = initNext;
   }
   public Object getValue()
   {
      return value;
   }
   public ListNode getNext()
   {
      return next;
   }
   public void setValue(Object o)
   {
      value = o;
   }
   public void setNext(ListNode o)
   {
      next = o;
   }
}