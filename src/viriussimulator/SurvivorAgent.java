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
public class SurvivorAgent extends Agent 
{
    
    
    public SurvivorAgent(String n, vNode start)
    {
        super(n, start);  
    }
    
    public void update()
    {
       if (paniced)
       {
           ArrayList havens = new ArrayList(); 
           havens.add(this.location);
           havens.add(this.zombieSurviorRatio(this.location));
           for (int i = 0; i < this.location.connections.size(); i++)
           {
            
               havens.add(this.location.connections.get(i)); 
               havens.add(this.zombieSurviorRatio(this.location.connections.get(i))); 
           }
           
           int maxSpot = 0; 
           double max = (double) havens.get(1); 
           for (int y = 1; y < havens.size(); y += 2)
           {
               double comp = (double) havens.get(y); 
               if (comp > max)
               {
                   max = comp; 
                   maxSpot = y -1; 
               }
           }
           vNode spot = (vNode)havens.get(maxSpot); 
           this.changeLocation(spot);
           return; 
       }
       Random generator = new Random();
       if (!(schedule.size() > 1))
       {
           return; 
       }
       if (atspot)
       {
           if (timer == 1)
           {
               int neg = generator.nextInt(1);
               if (neg == 0) timer =4; 
           }
           else timer ++;
       }
        
   
       if (timer == 4 && atspot)
       {
            scheduleplace++; 
            if(scheduleplace >= schedule.size()) scheduleplace = 0; 
            timer = 0;
            atspot = false; 
            this.route = this.findRoute(schedule.get(scheduleplace)); 
            if (route.size() > 1 && route.get(0).name.equals(this.location.name)) route.remove(0);
          

        }
        
        if (!atspot)
        {
            if (route.get(0).name.equals(schedule.get(scheduleplace).name))
            {
                atspot = true;
                if(!changeLocation(route.get(0)))
                {
                 
                    return; 
                }
            }
            else 
            {
             
                vNode temp = route.get(0); 
                route.remove(0); 
                if(!changeLocation(temp))
                {
                   
                    return; 
                }
                
            }
        }
        
    }
    
    private double zombieSurviorRatio(vNode spot)
    {
        double surviors = 0; 
        double zombies = 0; 
        
        for (int x = 0; x < spot.inhabitants.size(); x++)
        {
            if (spot.inhabitants.get(x).getClass().equals(this.getClass()))
            {
                surviors+=1; 
            }
            else zombies+=1; 
        }
        if (zombies == 0) return surviors; 
        return (surviors/zombies); 
        
    }
    
    public void panicCheck(double percent) 
    {
        
        if (percent>panic) 
        {
            paniced=true;
            
        }
    }
    
    
    
}
