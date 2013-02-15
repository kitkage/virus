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
public class ZombieAgent extends Agent 
{
    public ZombieAgent(String n, vNode start)
    {
        super(n, start);  
    }
    
    public void update()
    {
        ArrayList<vNode> spots = new ArrayList<vNode>(); 
        spots.add(this.location);
        spots.addAll(this.location.connections);
        for (int x = 0; x < this.location.connections.size(); x++)
        {
            
        }
    }
    
}
