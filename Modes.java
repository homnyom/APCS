//Natalie Homnyom
//September 11, 2015
public class Modes
{
   public static void main(String[] args)
   {
      int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
      display(tally);
      int[] modes = calculateModes(tally);
      display(modes);
      int sum = 0;
      for(int k = 0; k < tally.length; k++)
         sum += tally[k];
      System.out.println("kth \tindex"); 
      for(int k = 1; k <= sum; k++)
         System.out.println(k + "\t\t" + kthDataValue(tally, k));
   }
   public static int[] calculateModes(int[] tally)
   {
      int num = 0;
      for(int x = 0; x < tally.length; x++)
      {
         if(tally[x] == findMax(tally))
            num++;
      }
      int[] modes = new int[num];
      int current = 0;
      for(int x = 0; x < tally.length; x++)
      {
         if(tally[x] == findMax(tally))
         {
            modes[current] = x;
            current++;
         }
      }
      return modes;
   }
   public static int kthDataValue(int[] tally, int k)
   {
   	int value = 0;
      for(int x = 0; x < tally.length; x++)
      {
         value+=tally[x];
         if(value >= k)
            return x;
      }
      return -1;
   }
   public static int findMax(int[] nums)
   {
      int pos = 0;
      for(int k = 1; k < nums.length; k++)
         if(nums[k] > nums[pos])
            pos = k;
      return nums[pos];
   }
   public static void display(int[] args)
   {
      for(int k = 0; k < args.length; k++)
         System.out.print(args[k] + " ");
      System.out.println();
      System.out.println();
   }
}
