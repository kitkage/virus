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
import java.util.*; 
public class Agent 
{
    vNode location; 
    ArrayList<vNode> route;
    ArrayList<vNode> schedule; 
    String name; 
    boolean infected;
    int stage;
    double apearence;
    Virus virus;
    double avoidance=0.6;
    int scheduleplace; 
    public Agent(String n, vNode start)
    {
        name = n; 
        location = start; 
        route = new ArrayList<vNode>(); 
        schedule = new ArrayList<vNode>(); 
        schedule = generateSchedule(); 
    }
    
    public void update()
    {
        if (route.isEmpty())
        {
            route = this.findRoute(schedule.get(scheduleplace)); 
        }
        if (location.name.equals(route.get(0).name) && schedule.contains(location))
        {
            scheduleplace++; 
            if (scheduleplace >= schedule.size())
            {
                scheduleplace = 0; 
            }
            if (!schedule.get(scheduleplace).name.equals(location.name))
            {
                route = this.findRoute(schedule.get(scheduleplace)); 
                vNode next = route.get(0); 
                route.remove(0); 
                changeLocation(next); 
                return; 
            }
            
        
        }
        
        
        vNode next = route.get(0); 
        route.remove(0);
        changeLocation(next); 
        
    }
    public boolean changeLocation(vNode v)
    {
        if (!location.connections.contains(v)) return false; 
        return location.inhabitantExits(this, v); 
    }
    
    private ArrayList<vNode> generateSchedule()
    {
         
        ArrayList<vNode> s = new ArrayList<vNode>(); 
        ArrayList<vNode> map = location.getMap();
        
        Random num = new Random(); 
        scheduleplace = num.nextInt(10) + 1; 
        for (int i = 0; i < scheduleplace; i++)
        {
            int next = num.nextInt(map.size()); 
            for (int x = 0; x < 4; x ++)
            {
                s.add(map.get(next));
            }
        }
        return s; 
    }
    
    
    
    
    public ArrayList<vNode> findRoute(vNode v)
    {
        
        nodeQueue route = new nodeQueue(); 
       // System.out.println("Finding route"); 
        //System.out.println("Start location is: " + location.name + " and Destination is: " + v.name); 
        return DFS (v, location, route, new nodeQueue()); 
    }
    
    private ArrayList<vNode> DFS (vNode v, vNode current, nodeQueue route, nodeQueue explored)
    {
        route.push(current);
        if (current.name.equals(v.name))
        {
            return route.toArrayList(); 
        }
        
        else 
        {
            
            explored.push(current);
            for (int i = 0; i < current.connections.size(); i++)
            {
                if(!explored.contains(current.connections.get(i)))
                {
                    
                    ArrayList<vNode> further = new ArrayList<vNode>();  
                    further = DFS(v, current.connections.get(i), route, explored);
                    
                    if (!further.isEmpty())
                    {
                        /*System.out.println("Destination is: " + v.name); 
                        for (int x = 0; x < further.size(); x++)
                        {
                            System.out.println("Route step " + x + " is: " + further.get(x).name); 
                        }*/
                        return further; 
                    }
                    
                }
            }
            //System.out.println("Returning empty list"); 
            return new ArrayList<vNode>();
        }
        
         
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
    public double getApperance()
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
    public void updateApperance(double avoidingPercent)
    {
        if (infected) 
        {
            apearence=virus.stageApperence(stage)-avoidingPercent;
        }
    }
    
    public boolean isDead()
    {
        return virus.agentDeath(this);
    }
    public double infectionRadios()
    {
            if (infected) {
                return virus.infectionArea(stage);
            }
            return 0;
    }
    
    
    

}
