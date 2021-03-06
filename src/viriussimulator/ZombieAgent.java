/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author baroba
 */
public class ZombieAgent extends Agent 
{
    public ZombieAgent(String n, vNode start)
    {
        
        super(n, start);  
        virus = new zombievirus();
        stage = 10; 
        infected = true;  
    }
    /*Similar to parent class's update, but uses a greedy algorithm to find survivors 
     * 
     */
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
        Random change = new Random(); 
        if (maxSpot == 0 && max == 0) maxSpot = change.nextInt(spots.size() -1 ); 
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
    public void panicCheck(double percent) 
    {
        
            paniced=false;
            
    }
    
    public boolean isDead()
    {
        return false;
    }
}
