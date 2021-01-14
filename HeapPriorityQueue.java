 //Name: Natalie Homnyom   Date: 5/9/16
import java.util.*;
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
   }
   private boolean add(E e)
   {
      myHeap.add(e);
      heapUp(myHeap.size()-1);
      return true;
   }
   private E remove()
   {
      E e = myHeap.get(1); 
      swap(1, myHeap.size()-1);
      heapUp(myHeap.size());
      return e;
   }
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }
   private void heapUp(int k)
   {
      int parent = k/2;
      if(parent >= 1 && myHeap.get(k).compareTo(myHeap.get(parent)) > 0)
      {
         swap(parent, k);
         heapUp(parent);
      }  
   }
   private void heapDown(int k, int size)
   {
      int left = 2*k;
      int right = 2*k + 1;
      if(k > size || left == size || right > size)
         return;
      else{
         int maxChild = (myHeap.get(left).compareTo(myHeap.get(right)) > 0) ? left:right;
         if(myHeap.get(k).compareTo(myHeap.get(maxChild)) < 0){
            swap(k, maxChild);
            heapDown(maxChild, size);
         }
      }
   }
}
