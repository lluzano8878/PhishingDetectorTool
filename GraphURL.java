package org.example;

import java.util.*;

public class GraphURL {
   Map<String, List<String>> URLlist;

   public GraphURL(){
       this.URLlist = new HashMap<>();
   }
   public void addNode(String node){
       if(!URLlist.containsKey(node)){
           URLlist.put(node, new ArrayList<>());
       }
   }
   public void addEdge(String from, String to){
       if(!URLlist.containsKey(from)){
           URLlist.put(from, new ArrayList<>());
       }
       if(!URLlist.containsKey(to)){
           URLlist.put(to, new ArrayList<>());
       }
        URLlist.get(from).add(to);
       URLlist.get(to).add(from);
   }
   public void analyze(){
       for(Map.Entry<String, List<String>>  entry : URLlist.entrySet()){
       String node = entry.getKey();
       int connect = entry.getValue().size();
       if (connect > 1){
           System.out.println("suspicious node: " + node + " with " + connect + " connections");
       }
   }
    }
}
