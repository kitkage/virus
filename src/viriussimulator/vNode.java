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
public abstract class vNode
{
    public ArrayList<vNode> connections; 
    public ArrayList<Agent> inhabitants; 
    public double size; 
    public String name; 
    public ArrayList<vNode> map; 
    public abstract void inhabitantEnters(Agent a); 
    
    public abstract boolean inhabitantExits(Agent a, vNode v); 
    public abstract boolean addConnection(vNode v);
    
    public ArrayList<vNode> getMap()
    {
        return map; 
    }
    
    public void avoidance()
    {
        for (int i = 0; i < inhabitants.size(); i++) {
            Agent avoidAgent = inhabitants.get(i);
            int avoid=0;
            for(int j=0; i<inhabitants.size();j++)
            {
                if(j!=i)
                {
                    if(inhabitants.get(j).avoid(avoidAgent))
                    {
                        avoid+=1;
                    }
                }
                avoidAgent.updateApperance((float) avoid/inhabitants.size());
            }

        }
    }
    public void updateStage()
    {
        for (int a = 0; a < inhabitants.size(); a++) 
        {
            Agent agent = inhabitants.get(a);
            agent.increaseStage();
        }
    }
    public void infected(){
        int numbofinfected=0;
        double infectionrad=0;
        for (int i = 0; i < inhabitants.size(); i++) {
            Agent person = inhabitants.get(i);
            if(person.isInfected()){
                numbofinfected+=1;
                infectionrad=person.infectionRadios();
            }
            
        }
        
    }
    
    private ArrayList<vNode> generateMap()
    {
        boolean all = false; 
        nodeQueue nVisited = new nodeQueue(); 
        nVisited.push(this);
        ArrayList<vNode> map = new ArrayList<vNode>(); 
        while (!all)
        {
          vNode current = nVisited.pull(); 
          map.add(current);
          for (int i = 0; i < current.connections.size(); i++)
          {
              vNode c = current.connections.get(i);
              if (!map.contains(c))
              {
                  nVisited.push(c);
              }
          }
          
          if (nVisited.isEmpty()) all = true; 
          
            
        }
        
        return map; 
    }
   
}
