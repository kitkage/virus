/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;
import java.util.ArrayList;

/**
 *
 * @author baroba
 */
public class City extends location 
{
    public City (int numAgents)
    {
     System.out.println("Creating city"); 
     nodes = new ArrayList<vNode>(); 
     numStartingAgents = numAgents; 
     nodes.add(new streetNode("route 1", 400));
     nodes.add(new buildingNode("building 1", 500, 10));
     nodes.add(new buildingNode("building 2", 200, 10));
     nodes.add(new buildingNode("building 3", 600, 10));
     nodes.add(new buildingNode("building 4", 1000, 10));
     System.out.println("Adding connections"); 
     for (int i = 1; i < 5; i++) 
     {
        nodes.get(0).addConnection(nodes.get(i));
        nodes.get(i).addConnection(nodes.get(0)); 
        System.out.println("Number of connections is: " + nodes.get(i).connections.size()); 

     }
     System.out.println("StreetConnection size is: " + nodes.get(0).connections.size()); 
     
      
     for (int i = 0; i < nodes.size(); i++) 
        {
            nodes.get(i).setMap(nodes);
        }
     for (int i = 0; i < nodes.size(); i++) nodes.get(i).createAgents();
     nodes.get(4).inhabitants.get(0).infect(new illness());
    }
    public  void update()
    {
        for (int i = 0; i < nodes.size(); i++) {
            vNode node = nodes.get(i);
            node.infected();
            node.avoidance();
            node.updateStage();

            node.update();
        }
        
        for (int i = 0; i < nodes.size(); i++) {
            vNode node = nodes.get(i);
            System.out.println(node.readOut());
        }
        
    };
    public  double deadPercent()
    {
        int living=0;
        for (int i = 0; i < nodes.size(); i++) {
            vNode node = nodes.get(i);
            living+=node.inhabitants.size();
        }
        return living/numStartingAgents; 
    };
    
}
