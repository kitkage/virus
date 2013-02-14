/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;
import java.util.ArrayList;
import java.util.*; 
/**
 *
 * @author baroba
 */
public class buildingNode extends vNode
{ 
    
    private int numAgents; 
    public buildingNode(String n, double area, int numAgent)
    {
        name = n; 
        size = area;
        connections = new ArrayList<vNode>();
        inhabitants = new ArrayList<Agent>();
        numAgents = numAgent; 
    }
        
    
    
    
    public boolean addConnection(vNode v)
    {
        if (connections.contains(v)) return false; 
        connections.add(v);
        v.addConnection(this);
        return true; 
    }
    
    public String getName()
    {
        return name; 
    }
    
    public double getSize()
    {
        return size; 
    }
    public void inhabitantEnters(Agent a)
    {
        inhabitants.add(a);
    }
    
    public boolean inhabitantExits(Agent a, vNode v)
    {
        if (connections.contains(v) && inhabitants.contains(a))
        {
            inhabitants.remove(a);
            int x = connections.indexOf(v); 
            connections.get(x).inhabitantEnters(a);
            return true; 
        }
        
        return false; 
    }
    
    public void createAgents()
    {
          for (int x = 0; x <numAgents; x++)
        {
            Random id = new Random(); 
            Integer i = id.nextInt(100000000);
            Agent a = new Agent(i.toString() , this); 
            inhabitants.add(a);
        }
    }
    
}
