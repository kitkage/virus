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
import java.util.Queue; 
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
    
    
    public void findRoute(vNode v)
    {
        
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
}
