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
public class streetNode extends vNode 
{
    /*Street constructor method
     * @param n The name/id of the street
     * @param s The size of the street
     */
    public streetNode(String n, double s)
    {
        name = n; 
        size = s; 
        connections = new ArrayList<vNode>();
        inhabitants = new ArrayList<Agent>(); 
    }
    /*Adds a one way connection with a node.  Be sure to call this again on the node that is being connected to create a two way connection
     * @param v  The node being connected
     * 
     */
    public boolean addConnection(vNode v)
    {
        if (connections.contains(v)) return false; 
        connections.add(v);
        
        return true; 
    }
    /*Method that get's the streets name
     * 
     */
    public String getName()
    {
        return name; 
    }
    /*Method that gets the street's size
     * 
     */
    public double getSize()
    {
        return size; 
    }
    /*Method that adds an agent to the inhabitans 
     * @param a  The agent to be added
     */
     public void inhabitantEnters(Agent a)
    {
        inhabitants.add(a);
    }
    
    /*Method that removes an agent and sends them to a different node
     * @param a  The agent
     * @param v  The destination node
     */
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
    /*Method currently empty 
     * 
     */
    public void createAgents()
    {
        
    }
}
