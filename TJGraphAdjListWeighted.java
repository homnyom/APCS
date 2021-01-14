//name:Natalie Homnyom   date: June 13, 2016
//for use with Graphs6: Dijkstra
//             Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
   public final wVertex target;
   public final double weight;
   
   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
   public wVertex getTarget(){
      return target;
   } 
   public double getWeight(){
      return weight;
   }
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;
   
   public wVertex(String argName){
      name = argName;
      adjacencies = new ArrayList<Edge>();
      adjacencies.add(new Edge(this, 0));
   }
   public String getName(){
      return name;
   }
   public ArrayList<Edge> getAdjacencies(){
      return adjacencies;
   }
   public double getMinDistance(){
      return minDistance;
   }
   public void setMinDistance(double m){
      minDistance = m;
   }
   public wVertex getPrevious(){
      return previous;
   }   
   public void setPrevious(wVertex v){
      previous = v;
   }
   public int compareTo(wVertex other){
      return (int) (minDistance - other.minDistance);
   }
   public String toString(){
      return name;
   }
} 

public class TJGraphAdjListWeighted implements TJGraphAdjListWeightedInterface
{
   private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   
   public List<wVertex> getVertices(){
      return vertices;
   }
   public wVertex getVertex(int i){
      return vertices.get(i);
   }
   public wVertex getVertex(String vertexName){
      return vertices.get(nameToIndex.get(vertexName));
   }
   public void addVertex(String v){
      if(nameToIndex.get(v) == null){
         vertices.add(new wVertex(v));
         nameToIndex.put(v, nameToIndex.size());
      }
   }
   public void addEdge(String source, String target, double weight){
      wVertex v = vertices.get(nameToIndex.get(source));
      wVertex w = vertices.get(nameToIndex.get(target));
      v.getAdjacencies().add(new Edge(w, weight));
   } 
   public void minimumWeightPath(String vertexName){   //Dijkstra's
      Queue<wVertex> queue = new PriorityQueue<wVertex>();
      List<wVertex> visited = new ArrayList<wVertex>();
      vertices.get(nameToIndex.get(vertexName)).setMinDistance(0);
      queue.add(vertices.get(nameToIndex.get(vertexName)));
      
      while(!queue.isEmpty()){  
         wVertex v = queue.remove();
         visited.add(v);
         for(Edge e: v.getAdjacencies()){
            wVertex other = e.getTarget();
            if(v != other){
               if(v.getMinDistance() + e.getWeight() < other.getMinDistance()){
                  other.setMinDistance(v.getMinDistance() + e.getWeight());
                  other.setPrevious(v);
               }
               if(!visited.contains(other))
                  queue.add(other);
            }
         }      
      }
   }
   public List<wVertex> getShortestPathTo(String vertexName){
      return getShortestPathTo(vertices.get(nameToIndex.get(vertexName)));
   }    
   public List<wVertex> getShortestPathTo(wVertex v){
      List<wVertex> l = new ArrayList<wVertex>();
      Stack<wVertex> s = new Stack<wVertex>();
      wVertex current = v;
      
      while(current.getPrevious() != null){
         s.push(current.getPrevious());
         current = current.getPrevious();
      }
      
      while(!s.isEmpty())
         l.add(s.pop());
      l.add(v);
      
      return l;
   }
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException{
      TJGraphAdjListWeighted tj = new TJGraphAdjListWeighted();
      Scanner vn = new Scanner(vertexNames);
      Scanner el = new Scanner(edgeListData);
      
      vn.next();
      while(vn.hasNext())
         tj.addVertex(vn.next());
      vn.close();
      
      while(el.hasNextLine()){
         String[] s = el.nextLine().split(" ");
         tj.addEdge(s[0], s[1], Integer.parseInt(s[2]));
      }   
      el.close();
      
      return tj;
   }
} 

interface wVertexInterface 
{
   public String toString();
   
   public String getName();
   
   public double getMinDistance();
   
   public void setMinDistance(double m);
   
   public wVertex getPrevious();         //Graphs 7
    
   public void setPrevious(wVertex v);   //Graphs 7
   
   public ArrayList<Edge> getAdjacencies();
   
   public int compareTo(wVertex other);
}
   
interface TJGraphAdjListWeightedInterface 
{
   public List<wVertex> getVertices();
   
   public wVertex getVertex(int i);
   
   public wVertex getVertex(String vertexName);
   
   public void addVertex(String v);
   
   public void addEdge(String source, String target, double weight);
     
   public void minimumWeightPath(String vertexName);   //Dijkstra's
   
   /*  Graphs 7 */
   public List<wVertex> getShortestPathTo(String vertexName);
       
   public List<wVertex> getShortestPathTo(wVertex v);
    
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException;
 
}