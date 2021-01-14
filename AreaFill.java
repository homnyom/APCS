//name: Natalie Homnyom
//date: 10/7/2015

import java.util.Scanner;
import java.io.*;
public class AreaFill
{
   public static char[][] grid = null;

   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Filename: ");
      String filename = sc.next();
      grid = read(filename);
      display(grid);
      System.out.print("\nEnter ROW COL to fill from: ");
      int row = sc.nextInt();
      int col = sc.nextInt(); 
      // fill(grid, row, col, grid[row][col]);
      // display(grid);
      //  Extension
      int count = fillAndCount(grid, row, col, grid[row][col]);
      display(grid);
      System.out.println("count = " + count);
      System.out.println();
      sc.close();
   }
   public static char[][] read(String filename)throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(filename));
      String coord = infile.nextLine();
      int rows = Integer.parseInt(coord.substring(0, coord.indexOf(" ")));
      int columns = Integer.parseInt(coord.substring(coord.indexOf(" ") + 1));
      
      char[][] grid = new char[rows][columns];
      for(int x = 0; x < rows; x++)
      {
         String temp = infile.nextLine();
         for(int y = 0; y < columns; y++)
            grid[x][y] = temp.charAt(y);
      }
      infile.close();
      return grid;
   }
   
   public static void display(char[][] g)
   {    
      for(int x = 0; x < g.length; x++)
      {
         for(int y = 0; y < g[0].length; y++)
            System.out.print(g[x][y]);
         System.out.println();
      }      
   }
   public static void fill(char[][] g, int r, int c, char ch) //recursive method
   {     
      if(r < 0 || r >= g.length || c < 0 || c >= g[0].length)
         return;
      else if(g[r][c] == ch)
      {
         g[r][c] = '*';
         fill(g, r - 1, c, ch);
         fill(g, r + 1, c, ch);
         fill(g, r, c - 1, ch);
         fill(g, r, c + 1, ch);
      }
   }
	
	// Extension-- count and return the number of asterisks
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      int count = 0;
      if(r < 0 || r >= g.length || c < 0 || c >= g[0].length)
         return 0;
      else if(g[r][c] == ch)
      {
         g[r][c] = '*';
         count = 1;
         count += fillAndCount(g, r - 1, c, ch);
         count += fillAndCount(g, r + 1, c, ch);
         count += fillAndCount(g, r, c - 1, ch);
         count += fillAndCount(g, r, c + 1, ch);
      }
      return count; 
   }
}