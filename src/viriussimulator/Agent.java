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
public class Agent extends AbstractAgent
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
    private int timer;
    private boolean atspot; 
    public double panic=.4;
    public vNode home;
    public boolean paniced=false;
    public Agent(String n, vNode start)
    {
        name = n; 
        location = start; 
        home=start;
        route = new ArrayList<vNode>(); 
        schedule = new ArrayList<vNode>(); 
        schedule = generateSchedule();
        timer = 0; 
        atspot = true; 
        scheduleplace = 0; 
    }
   
    
    public void update()
    {
        
        if (paniced&&location.name.equals(home.name)) {
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
    
     
    public boolean changeLocation(vNode v)
    {
        

        if (!location.connections.contains(v)) return false;
        

        return location.inhabitantExits(this, v); 
    }
    
    protected ArrayList<vNode> generateSchedule()
    {
         
        ArrayList<vNode> s = new ArrayList<vNode>(); 
        ArrayList<vNode> map = location.getMap();
        
        Random num = new Random(); 
        scheduleplace = num.nextInt(10) + 1; 
        for (int i = 0; i < scheduleplace; i++)
        {
            int next = num.nextInt(map.size()); 
           
            s.add(map.get(next));
           
        }
        return s; 
    }
    
    
    
    
    public ArrayList<vNode> findRoute(vNode v)
    {
       
        nodeQueue route = new nodeQueue(); 
       
        return DFS (v, location, route, new nodeQueue()); 
    }
    
    private ArrayList<vNode> DFS (vNode v, vNode current, nodeQueue iroute, nodeQueue explored)
    {
        iroute.push(current);
        if (current.name.equals(v.name))
        {
            return iroute.toArrayList(); 
        }
        
        else 
        {
            explored.push(current);
            
            for (int i = 0; i < current.connections.size(); i++)
            {
                if(!explored.contains(current.connections.get(i)))
                {
                    
                    ArrayList<vNode> further = new ArrayList<vNode>();
                    nodeQueue temp = new nodeQueue(); 
                    for(int x = 0; x < iroute.toArrayList().size(); x++)
                    {
                        temp.push(iroute.toArrayList().get(x));
                    }
                    
                    further = DFS(v, current.connections.get(i), temp, explored);
                    
                    if (!further.isEmpty())
                    {
                     
                        return further; 
                    }
                    
                }
            }
            
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
        if (infected) {
            return virus.agentDeath(this);
        }
        return false;
    }
    public double infectionRadius()
    {
            if (infected) {
                return virus.infectionArea(stage);
            }
            return 0;
    }

    public void panicCheck(double percent) {
        if (location.name.equals(home.name)||paniced) {
            if (paniced==false) {
                paniced=true;
            }
            return;
        }
        if (percent>panic) {
            paniced=true;
            route=findRoute(home);
        }
    }
    
    
    

}
