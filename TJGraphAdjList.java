//name: Natalie Homnyom   date: June 3, 2016
//EXTENSION (if depthFirstRecur is an extension)
//for use with Graphs3: EdgeList
//             Graphs4: DFS-BFS
//             Graphs5: EdgeListCities

import java.io.*;
import java.util.*;
/*********************  Graphs 3:  EdgeList *******************************/
interface VertexInterface
{
   public String toString();     //just return the name
   public String getName();
   public ArrayList<Vertex> getAdjacencies();
   public void addEdge(Vertex v);
}  

interface TJGraphAdjListInterface 
{ 
   public List<Vertex> getVertices();
   public Vertex getVertex(int i) ;
   public Vertex getVertex(String vertexName);
   public Map<String, Integer> getVertexMap();
   public void addVertex(String v);
   public void addEdge(String source, String target);
   public String toString();
  
  
   /*********************Graphs 4:  DFS and BFS ****************************/
   public List<Vertex> depthFirstSearch(String name);
   public  List<Vertex> breadthFirstSearch(String name);
   public  List<Vertex> depthFirstRecur(String name);


   /****************Graphs 5:  EdgeList with Cities  *********/
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   public int edgeCount();
   public boolean isReachable(String source, String target);
   public boolean isConnected();
}
/***********************************************************/
class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
   public Vertex(String s){
      name = s;
      adjacencies = new ArrayList<Vertex>();
   }
   public void addEdge(Vertex v){
      adjacencies.add(v);
   }
   public String getName(){
      return name;
   }
   public ArrayList<Vertex> getAdjacencies(){
      return adjacencies;
   }
   public String toString(){
      return name;
   }
}  
/*******************************************************/
public class TJGraphAdjList implements TJGraphAdjListInterface
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  
   public void addVertex(String v){
      if(nameToIndex.get(v) == null){
         vertices.add(new Vertex(v));
         nameToIndex.put(v, nameToIndex.size());
      }
   }
   public void addEdge(String source, String target){
      Vertex v, other;
      
      if(nameToIndex.get(target) == null)
         addVertex(target);
      if(nameToIndex.get(source) == null)
         addVertex(source);
      
      v = vertices.get(nameToIndex.get(source));   
      other = vertices.get(nameToIndex.get(target));   
      v.addEdge(other);
   }
   public List<Vertex> getVertices(){
      return vertices;
   }
   public Vertex getVertex(int i){
      return vertices.get(i);
   }
   public Vertex getVertex(String vertexName){
      return vertices.get(nameToIndex.get(vertexName));
   }
   public Map<String, Integer> getVertexMap(){
      return nameToIndex;
   }
   public String toString(){
      String s = "";
      for(Vertex v: vertices)
         s += v.getName() + " " + v.getAdjacencies() + "\n";
      return s;
   }
   public List<Vertex> depthFirstSearch(String name){
      return depthFirstSearch(vertices.get(nameToIndex.get(name)));
   }
   private List<Vertex> depthFirstSearch(Vertex start){
      Stack<Vertex> stack = new Stack<Vertex>();
      List<Vertex> list = new ArrayList<Vertex>();
      
      stack.push(start);
      while(!stack.empty()){
         Vertex v = stack.pop();
         if(!list.contains(v))
            list.add(v);
         
         Iterator<Vertex> it = v.getAdjacencies().iterator();
         while(it.hasNext()){
            Vertex edge = it.next();
            if(!list.contains(edge))
               stack.push(edge);
         }  
      }
      return list;
   }
   public List<Vertex>breadthFirstSearch(String name){
      return breadthFirstSearch(vertices.get(nameToIndex.get(name)));
   }
   private List<Vertex> breadthFirstSearch(Vertex start){
      Queue<Vertex> queue = new LinkedList<Vertex>();
      List<Vertex> list = new ArrayList<Vertex>();
      
      queue.add(start);
      while(!queue.isEmpty()){
         Vertex v = queue.remove();
         if(!list.contains(v))
            list.add(v);
         
         Iterator<Vertex> it = v.getAdjacencies().iterator();
         while(it.hasNext()){
            Vertex edge = it.next();
            if(!list.contains(edge))
               queue.add(edge);
         }  
      }
      return list;
   }
   public List<Vertex> depthFirstRecur(String name){
        return depthFirstRecur(vertices.get(nameToIndex.get(name)), new ArrayList<Vertex>());
   }
   private List<Vertex> depthFirstRecur(Vertex v, List list){
      if(list.contains(v))
         return list;
      else{
         list.add(v);
         Iterator it = v.getAdjacencies().iterator();
         while(it.hasNext())
            depthFirstRecur((Vertex) it.next(), list);
         return list;
      }
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException{
      Scanner sc = new Scanner(new File(fileName));
      while(sc.hasNextLine()){
         String[] s = sc.nextLine().split(" ");
         String city = s[0];
         addVertex(city);
         for(int x = 1; x < s.length; x++)
            addEdge(city, s[x]);  
      }
   }
   public int edgeCount(){
      int count = 0;
      for(Vertex v: vertices)
         count += v.getAdjacencies().size();
      return count;
   }
   public boolean isReachable(String source, String target){
      List<Vertex> l = depthFirstSearch(source);
      return l.contains(vertices.get(nameToIndex.get(target)));
   }
   public boolean isConnected(){
      for(Vertex v: vertices){
         List l = depthFirstSearch(v.getName());
         if(l.size() != vertices.size())
            return false;  
      }
      return true;  
   }
}