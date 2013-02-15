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
    public buildingNode(String n, double area)
    {
        name = n; 
        size = area;
        connections = new ArrayList<vNode>();
        inhabitants = new ArrayList<Agent>();
        
    }
        
    
    
    
    public boolean addConnection(vNode v)
    {
        if (connections.contains(v)) return false; 
        connections.add(v);
        
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
            a.location = connections.get(x); 
            return true; 
        }
        
        return false; 
    }
    
    
    
}
