//name: Natalie Homnyom   date: October 21, 2015
import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename: ");
      Maze m = new Maze(sc.next());
      m.display();
      m.solve();
      m.display();  
   } 
}
 

class Maze
{
   private final char wall = 'W';
   private final char path = '.';
   private final char start = 'S';
   private final char exit = 'E';
   private final char step = '*';
   private char[][] maze;
   private boolean solved = false;
   private int[][] allPaths;
   private int lastRow, lastCol;
   private int startRow, startCol;
   private boolean S_Exists=false, E_Exists=false;

   public Maze(String filename)  
   {
      Scanner infile = null;
      try{
         infile = new Scanner(new File(filename + ".txt"));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
         System.exit(0);
      }
      //read the file
      int rows, cols;
      String[] dimen = infile.nextLine().split(" ");
      rows = Integer.parseInt(dimen[0]);
      cols = Integer.parseInt(dimen[1]);
      
      allPaths = new int[rows][cols];
      maze = new char[rows][cols];
      for(int x = 0; x < rows; x++)
      {
         String s = infile.nextLine();
         for(int y = 0; y < cols; y++)
         {
            maze[x][y] = s.charAt(y);
            if(s.charAt(y) == start)
            {
               startRow = x;
               startCol = y;
               S_Exists = true;
            }
            if(s.charAt(y) == exit)
               E_Exists = true;
         }
      }
      lastRow = lastCol = -1;
   }
   
   public void display()
   {
      for(int x = 0; x < maze.length; x++)
      {
         for(int y = 0; y < maze[0].length; y++)
            System.out.print(maze[x][y]);
         System.out.println();
      }
   }
   public void solve()
   {
      /*  1  */
      //solveAndMark(startRow, startCol);
      
      /*  2  */
      int count = solveAndCount(startRow, startCol);
      System.out.println("Number of steps = " + count);
      
      /*  3  */
      //markThePath(startRow, startCol);
      
      /*  4  */
      //if( !markThePath(startRow, startCol) )
         //System.out.println("No solution");   
              
      /*  5  */
      //markThePathAndCount(startRow, startCol, 0);
      
   }
   public void solveAndMark(int r, int c)
   {
      if(solved)
         return;
      else if(r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == wall)
         return;
      else if(maze[r][c] == exit)
         solved = true;
      else if(allPaths[r][c] >= 4)
         return;
      else
      {
         if(lastRow != -1 && lastCol != -1)
            if(maze[lastRow][lastCol] == step && maze[r][c] == step)
               maze[lastRow][lastCol] = path;
            
         maze[r][c] = step;
         
         lastRow = r;
         lastCol = c;
         
         allPaths[r][c]++;
         solveAndMark(r + 1, c);
         allPaths[r][c]++;
         solveAndMark(r, c + 1);
         allPaths[r][c]++;
         solveAndMark(r, c - 1);
         allPaths[r][c]++;
         solveAndMark(r - 1, c);
      }
   }
        
   public int solveAndCount(int r, int c)
   {
      int count = 0;
      if(solved)
         return count;
      else if(r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == wall)
         return 0;
      else if(allPaths[r][c] >= 4)
         return 0;
      else if(maze[r][c] == exit)
      {
         solved = true;
         return 0;
      }
      else
      {
         if(lastRow != -1 && lastCol != -1)
            if(maze[lastRow][lastCol] == step && maze[r][c] == step)
            {
               maze[lastRow][lastCol] = path;
               count -= 2;
            }
         
         maze[r][c] = step;
         count++;
         
         lastRow = r;
         lastCol = c;
         
         allPaths[r][c]++;
         count += solveAndCount(r + 1, c);
         allPaths[r][c]++;
         count += solveAndCount(r, c + 1);
         allPaths[r][c]++;
         count += solveAndCount(r, c - 1);
         allPaths[r][c]++;
         count += solveAndCount(r - 1, c);
         
         return count;
      }
   }

   public boolean markThePath(int r, int c)
   {
      if(solved)
         return true;
      else if(r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == wall)
         return false;
      else if(maze[r][c] == exit)
      {
         solved = true;
         return true;
      }
      else if(allPaths[r][c] >= 4)
         return false;
      else
      {
         if(lastRow != -1 && lastCol != -1)
            if(maze[lastRow][lastCol] == step && maze[r][c] == step)
               maze[lastRow][lastCol] = path;
         
         maze[r][c] = step;
         
         lastRow = r;
         lastCol = c;
         
         allPaths[r][c]++;
         boolean down = markThePath(r + 1, c);
         allPaths[r][c]++;
         boolean right = markThePath(r, c + 1);
         allPaths[r][c]++;
         boolean left = markThePath(r, c - 1);
         allPaths[r][c]++;
         boolean up = markThePath(r - 1, c);
         
         return up || right || down || left;
      }
   }   
   public boolean markThePathAndCount(int r, int c, int count)
   {
      if(solved)
         return true;
      else if(r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == wall)
         return false;
      else if(allPaths[r][c] >= 4)
         return false;
      else if(maze[r][c] == exit)
      {
         solved = true;
         return true;
      }
      else
      {
         if(lastRow != -1 && lastCol != -1)
            if(maze[lastRow][lastCol] == step && maze[r][c] == step)
            {
               maze[lastRow][lastCol] = path;
               count -= 2;
            }
         
         maze[r][c] = step;
         count++;
         
         lastRow = r;
         lastCol = c;
         
         allPaths[r][c]++;
         boolean down = markThePathAndCount(r + 1, c, count);
         allPaths[r][c]++;
         boolean right = markThePathAndCount(r, c + 1, count);
         allPaths[r][c]++;
         boolean left = markThePathAndCount(r, c - 1, count);
         allPaths[r][c]++;
         boolean up = markThePathAndCount(r - 1, c, count);
         
         return up || right || down || left;
      }
   }
}
