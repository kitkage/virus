/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;

/**
 *
 * @author baroba
 */
import java.util.ArrayList; 
public class Agent 
{
    vNode location; 
    ArrayList<vNode> route; 
    String name; 
    public Agent(String n, vNode start)
    {
        name = n; 
        location = start; 
        route = new ArrayList<vNode>(); 
    }
    
    public boolean changeLocation(vNode v)
    {
        if (!location.connections.contains(v)) return false; 
        return location.inhabitantExits(this, v); 
    }
    
    
    public void findRoute(vNode v)
    {
        
    }
}
