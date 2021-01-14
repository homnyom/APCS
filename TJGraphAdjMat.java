//name: Natalie Homnyom   date: 5/27/2016
//for use with Graphs0: Intro
//             Graphs1: Wardhall
//             Graphs2: Floyd
import java.util.*;
import java.io.*;

public class TJGraphAdjMat implements AdjacencyMatrix, Warshall,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;    
   
   public TJGraphAdjMat(int numNodes)
   {
      grid = new int[numNodes][numNodes];
      vertices = new HashMap<String, Integer>();
   }
   public TJGraphAdjMat()
   {
      vertices = new HashMap<String, Integer>();
   }
   public void addEdge(int source, int target)
   {
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target)
   {
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to)
   {
      return grid[from][to] < 9999;
   }
   public boolean isEdge(String from, String to)
   {
      return grid[vertices.get(from)][vertices.get(to)] < 9999;
   }
   public void displayGrid()
   {
      for(int r = 0; r < grid.length; r++){
         for(int c = 0; c < grid[0].length; c++)
            System.out.print(" " + grid[r][c]); 
         System.out.println();
      }
   }
   public int edgeCount()
   {
      int numEdges = 0;
      for(int r = 0; r < grid.length; r++)
         for(int c = 0; c < grid[0].length; c++)
            if(grid[r][c] < 9999 && grid[r][c] != 0)
               numEdges++;
      
      return numEdges;
   }
   public List<Integer> getNeighbors(int source) 
   {
      List<Integer> neighbors = new ArrayList<Integer>();
      for(int c = 0; c < grid[0].length; c++)
         if(grid[source][c] == 1)
            neighbors.add(c);
      return neighbors;      
   }
   public Map<String, Integer> getVertices()
   {
      return vertices;
   } 
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName));
      int numVert = Integer.parseInt(sc.next());
      int count = 0;
      
      while(count != numVert){
         vertices.put(sc.next(), count);
         count++;
      }
      
      sc.close();
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName));
      int size = Integer.parseInt(sc.nextLine());
      grid = new int[size][size];
      
      int row = 0;
      
      while(sc.hasNextLine() && row < size)
      {
         String[] s = sc.nextLine().split(" ");
         for(int x = 0; x < s.length; x++)
            grid[row][x] = Integer.parseInt(s[x]);
         row++;
      }
   }
   public void displayVertices()
   {
      Iterator<String> it = vertices.keySet().iterator();
      while(it.hasNext())
      {
         String s = it.next();
         System.out.println(vertices.get(s) + "-" + s);
      }
   }
   public void allPairsReachability()
   {
      for(int one = 0; one < grid.length; one++){
         for(int two = 0; two < grid.length; two++){
            for(int three = 0; three < grid.length; three++){
               if(isEdge(one, two) && isEdge(two, three))
                  addEdge(one, three);
            }
         }
      }
   }
   public int getCost(int from, int to)
   {
      return grid[from][to];
   }
   public int getCost(String from, String to)
   {
      return grid[vertices.get(from)][vertices.get(to)];
   }
   public void allPairsWeighted()
   {
      for(int one = 0; one < grid.length; one++){
         for(int two = 0; two < grid.length; two++){
            for(int three = 0; three < grid.length; three++){
               if(isEdge(one, two) && isEdge(two, three) && one != three && getCost(one, three) > getCost(one, two) + getCost(two, three)){
                  grid[one][three] = grid[one][two] + grid[two][three];
                  for(int four = 0; four < grid.length; four++)
                     if(isEdge(three, four) && one != four && getCost(one, four) > getCost (one, three) + getCost(three, four))
                        grid[one][four] = grid[one][three] + grid[three][four];
               }
            }
         }  
      }
   }
}

interface AdjacencyMatrix
{
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public void displayGrid();
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   
  /**********  User-friendly    **************/
   // public boolean isEdge(String from, String to);
   // public Map<String, Integer> getVertices();     
   // public void readNames(String fileName) throws FileNotFoundException;
   // public void readGrid(String fileName) throws FileNotFoundException;
   // public void displayVertices();
 /************* end  User-friendly   **************/
}

interface Warshall
{
   public void allPairsReachability();
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}
