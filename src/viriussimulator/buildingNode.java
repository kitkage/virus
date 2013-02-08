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
public class buildingNode extends vNode
{ 
    public buildingNode(String n, double s)
    {
        name = n; 
        size = s;
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
            return true; 
        }
        
        return false; 
    }
    
}
