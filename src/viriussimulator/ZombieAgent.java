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
        int maxSpot = 0; 
        int max = 0; 
        for (int x = 0; x < spots.size(); x++)
        {
            int comp = humans(spots.get(x));
            if (comp > max)
            {
                maxSpot = x; 
                max = comp; 
            }
            
        }
        
        this.changeLocation(spots.get(maxSpot));
    }
    
    private int humans (vNode spot)
    {
        int humans = 0; 
        for (int x = 0; x < spot.inhabitants.size(); x++)
        {
            if (!spot.inhabitants.get(x).getClass().equals(this.getClass())) humans++; 
        }
        
        return humans; 
    }
    
}
