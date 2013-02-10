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
    boolean infected;
    int stage;
    float apearence;
    Virus virus;
    float avoidance;
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
    
    
    public ArrayList<vNode> findRoute(vNode v)
    {
        nodeQueue nVisited = new nodeQueue();
        ArrayList<vNode> visited = new ArrayList(); 
        boolean found = false;
        vNode current = location;
        nVisited.push(current);
        while (!found)
        {
            current = nVisited.pull(); 
            if (current.name.equals(v.name))
            {
                return nVisited.toArrayList(); 
            }
            else 
            {
                visited.add(current);
                for (int i = 0; i < current.connections.size(); i++)
                {
                    vNode c = current.connections.get(i);
                    if (!visited.contains(c))
                    {
                        nVisited.push(c);
                        break; 
                    }
                }
                
            }
                
        }
        return null; 
    }
    public void infect(Virus vir)
    {
        infected=true;
        stage=1;
        virus=vir;
    }
    public boolean isInfected()
    {
        return infected;
    }
    public Virus infection(){
        return virus;
    }
    public void increaseStage()
    {
        if (infected) 
        {
        stage+=1;    
        }
        
    }
    public float getApperance()
    {
        return apearence;
    }
    public boolean avoid(Agent person)
    {
        if (person.getApperance()<avoidance) {
            return true;
        }
        return false;
    }
    public void updateApperance(float avoidingPercent)
    {
        apearence=virus.stageApperence(stage)-avoidingPercent;
    }
    
    public boolean isDead()
    {
        return virus.agentDeath(this);
    }
    public double infectionRadios(){
            if (infected) {
                return virus.infectionArea(stage);
            }
            return 0;
        }
    private class nodeQueue
    {
        private ArrayList<vNode> theQueue; 
        
        public nodeQueue()
        {
            theQueue = new ArrayList<vNode>(); 
        }
        
        public void push (vNode n)
        {
            theQueue.add(n);
        }
        
        public vNode pull()
        {
            vNode temp = theQueue.get(0); 
            theQueue.remove(0);
            return temp; 
        }
        
        public boolean contains (vNode v)
        {
            return theQueue.contains(v); 
        }
        
        public ArrayList<vNode> toArrayList()
        {
            return theQueue; 
        }
        
    }
}
