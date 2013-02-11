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
     nodes = new ArrayList<vNode>(); 
     numStartingAgents = numAgents; 
     nodes.add(new streetNode("route 1", 0));
     nodes.add(new buildingNode("building 1", numAgents/4));
     nodes.add(new buildingNode("building 2", numAgents/4));
     nodes.add(new buildingNode("building 3", numAgents/4));
     nodes.add(new buildingNode("building 4", numAgents/4));
     for (int i = 1; i < 5; i++) 
     {
        nodes.get(0).addConnection(nodes.get(i));

      }
    }
    public  void update()
    {
        
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
