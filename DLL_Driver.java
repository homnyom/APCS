// name: Natalie Homnyom   date: Decemeber 2, 2015
   public class DLL_Driver
   {
      public static void main(String args[])
      {
         DLL list = new DLL();	
      
         list.addLast("Apple");
         list.addLast("Banana");
         list.addLast("Cucumber");
         list.add("Durian");
         list.add("Eggplant");
         System.out.println(list);
         System.out.println("Size: " + list.size());
         Object obj = list.remove(3);
         System.out.println(list);
         System.out.println("Size: " +list.size());
         System.out.println("Removed "+ obj);
         System.out.print("Add at 3:   ");
         list.add(3,"Carrot");
         System.out.println(list);
         System.out.println("Get values at 1 and first: " + list.get(1)+" and " + list.getFirst());
         System.out.println("No change: " +list);
         System.out.print( list.removeFirst() + " is now removed!  "); 
         System.out.println(list);
         System.out.print("Add first:  ");
         list.addFirst("ARtichoke");
         System.out.println(list);
         System.out.println("Size: " + list.size());
         System.out.print("Set the second:  ");
         list.set(2, "Broccoli");
         System.out.println(list);
      }
   }

//////////////////////////////////////////////////////////
    
   class DLL        //DoubleLinkedList
   {
      private int size = 0;
      private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
      public int size()
      {
         return size;
      }
    	/* appends obj to end of list; increases size;
   	  @return true  */
      public boolean add(Object obj)
      {
         addLast(obj);
         return true;
      }
      /* inserts obj at position index.  increments size. 
   		*/
      public void add(int index, Object obj)
      {
         DLNode pointer = head.getNext();
         for(int x = 1; x < index; x++)
            pointer = pointer.getNext();
         DLNode temp = new DLNode(obj, pointer.getPrev(), pointer);
         pointer.setPrev(temp);
         temp.getPrev().setNext(temp);
         size++;
      }
      /* return obj at position index.  
   		*/
      public Object get(int index)
      {
         DLNode pointer = head.getNext();
         for(int x = 1; x < index; x++)
            pointer = pointer.getNext();
         return pointer.getValue();
      }
    /* replaces obj at position index.  
   		*/
      public void set(int index, Object obj)
      {
         DLNode pointer = head.getNext();
         for(int x = 1; x < index; x++)
            pointer = pointer.getNext();
         pointer.setValue(obj);
      }
    /*  removes the node from position index.  decrements size.
   	  @return the object at position index.
   	 */
      public Object remove(int index)
      {
         DLNode pointer = head.getNext();
         for(int x = 1; x < index; x++)
            pointer = pointer.getNext();
         pointer.getPrev().setNext(pointer.getNext());
         pointer.getNext().setPrev(pointer.getPrev());
         size--;
         return pointer.getValue();
      }
    /* inserts obj at front of list; increases size;
   	  */
      public void addFirst(Object obj)
      {
         DLNode temp = new DLNode(obj, head, head.getNext());
         head.setNext(temp);
         head.getNext().setPrev(temp);
         size++;
      }
   	/* appends obj to end of list; increases size;
   	    */
      public void addLast(Object obj)
      {
         DLNode temp = new DLNode(obj, head.getPrev(), head);
         head.setPrev(temp);
         temp.getPrev().setNext(temp);
         size++;
      }
      public Object getFirst()
      {
         return head.getNext().getValue();
      }
      public Object getLast()
      {
         return head.getPrev().getValue();
      }
      public Object removeFirst()
      {
         return remove(1);
      }
      public Object removeLast()
      {
         return remove(size);
      }
      public String toString()
      {
         String start = "";
         DLNode pointer = head.getNext();
         while(pointer.getNext() != head)
         {
            start = start + pointer.getValue() + ", ";
            pointer = pointer.getNext();
         }
         return start + pointer.getValue();
      }
   }
	
	///////////////////////////////////////

	  
   class DLNode 
   {
      private Object value;
      private DLNode prev;
      private DLNode next;
      public DLNode(Object arg, DLNode p, DLNode n)
      {
         value=arg;
         prev=p;
         next=n;
      }
      public DLNode()
      {
         value=null;
         next=this;
         prev=this;
      }
      public void setValue(Object arg)
      {
         value=arg;
      }
      public void setNext(DLNode arg)
      {
         next=arg;
      }
      public void setPrev(DLNode arg)
      {
         prev=arg;
      }
      public DLNode getNext()
      {
         return next;
      }
      public DLNode getPrev()
      {
         return prev;
      }
      public Object getValue()
      {
         return value;
      }
   }
