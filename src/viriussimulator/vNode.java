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
    public ArrayList<vNode> map = new ArrayList<vNode>(); 
    public abstract void inhabitantEnters(Agent a); 
    
    public abstract boolean inhabitantExits(Agent a, vNode v); 
    public abstract boolean addConnection(vNode v);
    
    /*Method that gets the node's copy of the map
     * 
     */
    public ArrayList<vNode> getMap()
    {
        if (map.isEmpty()) 
        {
            generateMap();
        }
        return map; 
    }
    
    /*Method that allows the node's map to be set.  Used to avoid having to make each node generate its own map
     * @param m  The map that the node will recieve
     */
    public void setMap(ArrayList<vNode> m)
    {
        map = m; 
    }
    /*Method that deals with agent avoidence procedures within a node
     * 
     */
    
    public void avoidance()
    {
        for (int i = 0; i < inhabitants.size(); i++) {
            Agent avoidAgent = inhabitants.get(i);
            double avoid=0.0;
            for(int j=0; j<inhabitants.size();j++)
            {
                if(j!=i)
                {
                    if(inhabitants.get(j).avoid(avoidAgent))
                    {
                        avoid+=1;
                    }
                }
                avoidAgent.updateApperance(avoid/inhabitants.size());
            }

        }
    }
    /*Method that deals with the agent's change in apperance 
     * 
     */
    public void updateStage()
    {
        for (int a = 0; a < inhabitants.size(); a++) 
        {
            Agent agent = inhabitants.get(a);
            agent.increaseStage();
        }
    }
    /*method that infects the agents
     * 
     */
    public void infected()
    {
        ArrayList<Agent> infected=new ArrayList<>();
        for (int i = 0; i < inhabitants.size(); i++) {
            Agent person = inhabitants.get(i);
            if(person.isInfected()){
                infected.add(person);
            }
            
        }
        if(infected.size()>0)
        {
            
        for (int i = 0; i < inhabitants.size(); i++) {
            Agent agent = inhabitants.get(i);
            if (!agent.isInfected()) 
            {

                double agentinfec=0.0;
                double infecAmount=0.0;
                for (int j = 0; j < infected.size(); j++) 
                {
                    Agent infec = infected.get(j);
                    if (!agent.avoid(infec)) {
                        infecAmount++;
                        agentinfec+=infec.infectionRadius();
                    }
                }
                agent.panicCheck(infecAmount/inhabitants.size());
                if (Math.random()<agentinfec/this.size) {
                    
                    agent.infect(infected.get(0).virus);
                }
            }
            
        }
        
        }
    }
    /*Method that the node uses to generate a map
     * 
     */
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
   /*Method to get the data from a node
    * 
    */
    public String readOut()
    {
        double infected=0.000;
        for (int i = 0; i < inhabitants.size(); i++) {
            Agent agent = inhabitants.get(i);
            if (agent.isInfected()) {
                infected+=1;
            }
        }
        double percent=0;
        if (inhabitants.size()>0) {
            percent=infected/inhabitants.size()*100;
        }
        
        return name+" has "+inhabitants.size()+" inhabitants "+percent+"% infected "+infected;
    }
    
    /*Method that calls all of the update methods for the agents in the node
     * 
     */
    public void update()
    {
        for (int i = 0; i < inhabitants.size(); i++) {
            Agent agent = inhabitants.get(i);
            if(!agent.isDead())
            {
            agent.update();
            }
        }
    }
    
     
}
