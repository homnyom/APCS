//Name: Natalie Homnyom    Date: 5/5/16
import java.text.*;

public class HeapSort
{
   public static final int SIZE = 100;  //9 or 100
	
   public static void main(String[] args)
   {
   //Part 1: Given a heap, sort it. Do this part first.  
      // double heap[] = {-1,99,80,85,17,30,84,2,16,1};
//       display(heap);
//       sort(heap);
//       display(heap);
      
   //Part 2:  Generate 100 random numbers, make a heap, sort it.
      double[] heap = new double[SIZE + 1];
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap); 
      sort(heap);
      display(heap);
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   public static void sort(double[] array)
   {
      for(int x = array.length - 2; x > 1; x--)
      {         
         swap(array, x + 1, 1);
         heapDown(array, 1, x);
      }
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   private static void heapDown(double[] array, int k, int size)
   {
      int left = 2*k;
      int right = 2*k + 1;
      if(k > size || left == size || right > size)
         return;
      else{
         int maxChild = (array[left] > array[right]) ? left:right;
         if(array[k] < array[maxChild]){
            swap(array, k, maxChild);
            heapDown(array, maxChild, size);
         }
      }
   }
   
   // ****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {
      DecimalFormat d = new DecimalFormat("0.00");
      array[0] = -1;   //because it will become a heap
      
      for(int x = 1; x < array.length; x++)
         array[x] = Double.parseDouble(d.format(Math.random() * 100 + 1));
         
      return array;
   }
   //turn the random array into a heap
   private static void makeHeap(double[] array, int size)
   {
      for(int x = array.length / 2; x >= 1; x--)
         heapDown(array, x, array.length - 1);
   }
}

