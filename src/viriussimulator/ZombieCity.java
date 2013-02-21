/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author swishept
 */
public class ZombieCity extends City
{

    int numAgents; 
    public ZombieCity (int streets, int nAgents)
    {
        super(streets, nAgents); 
        numAgents = nAgents; 
        Random generator = new Random();
        System.out.println("Creating city");        
        nodes = new ArrayList<vNode>();
        nodes.add(new streetNode("route 1", 400));
        nodes.add(new buildingNode("building 1", 500));
        nodes.add(new buildingNode("building 2", 200));
        nodes.add(new buildingNode("building 3", 600));
        nodes.add(new buildingNode("building 4", 1000));
        nodes.add(new buildingNode("building 5", 1000));
        nodes.add(new buildingNode("building 6", 1000));
        nodes.add(new buildingNode("building 7", 1000));
        nodes.add(new buildingNode("building 8", 1000));
        nodes.add(new buildingNode("building 9", 1000));
        nodes.add(new buildingNode("building 10", 1000));
        nodes.add(new buildingNode("building 11", 1000));
        System.out.println("Adding connections"); 
        for (int i = 1; i < nodes.size(); i++) 
        {
            nodes.get(0).addConnection(nodes.get(i));
            nodes.get(i).addConnection(nodes.get(0)); 

        }
        /*
        nodes.add(new streetNode("Route 1", generator.nextInt(1000)));
        int streetnames = 0;
        for (int i = 0; i < streets; i++) 
        {
            ArrayList<vNode> temp = streetMaker(generator.nextInt(100), numAgents/streets, 2 + i, 1 + streetnames);
            streetnames += temp.size() - 1;
            nodes.get(0).addConnection(temp.get(0));
            temp.get(0).addConnection(nodes.get(0));
            nodes.addAll(temp);
            System.out.println(nodes.size()+" node size");
        }*/
        buildingNode start = new buildingNode("start point", 5000);
        
        Virus infection = new zombievirus();
        nodes.get(0).addConnection(start);
        start.addConnection(nodes.get(0));
        nodes.add(start);
        for (int i = 0; i < nodes.size(); i++) 
        {
            nodes.get(i).setMap(nodes);
        }
        for (int y = 0; y < 20; y++) this.createAgents(start);
        for (int i = 0; i < nodes.size(); i++) 
        {
            for (int ag =0; ag < numAgents/nodes.size(); ag++) this.createAgents(nodes.get(i));
        }
        for (int i = 0; i < start.inhabitants.size(); i++) 
        {
            Agent agent = start.inhabitants.get(i);
            agent.infect(infection);
        }
    /* nodes.add(new streetNode("route 1", 400));
     nodes.add(new buildingNode("building 1", 500, 1));
     nodes.add(new buildingNode("building 2", 200, 0));
     nodes.add(new buildingNode("building 3", 600, 0));
     nodes.add(new buildingNode("building 4", 1000, 0));
     nodes.add(new buildingNode("building 5", 1000, 0));
     nodes.add(new buildingNode("building 6", 1000, 0));
     nodes.add(new buildingNode("building 7", 1000, 0));
     nodes.add(new buildingNode("building 8", 1000, 0));
     nodes.add(new buildingNode("building 9", 1000, 0));
     nodes.add(new buildingNode("building 10", 1000, 0));
     nodes.add(new buildingNode("building 11", 1000, 0));
     System.out.println("Adding connections"); 
     for (int i = 1; i < nodes.size(); i++) 
     {
        nodes.get(0).addConnection(nodes.get(i));
        nodes.get(i).addConnection(nodes.get(0)); 
        //System.out.println("Number of connections is: " + nodes.get(i).connections.size()); 

     }
     //System.out.println("StreetConnection size is: " + nodes.get(0).connections.size()); 
     
      
     for (int i = 0; i < nodes.size(); i++) 
        {
            nodes.get(i).setMap(nodes);
        }
     for (int i = 0; i < nodes.size(); i++) nodes.get(i).createAgents();
     nodes.get(1).inhabitants.get(0).infect(new illness());*/
     
     
    }
    public  void update()
    {
        for (int i = 0; i < nodes.size(); i++) {
            vNode node = nodes.get(i);
            node.infected();
            node.avoidance();
            node.updateStage();

            node.update();
        }
        
        for (int i = 0; i < nodes.size(); i++) 
        {
            vNode node = nodes.get(i);
            System.out.println(node.readOut() + " with  " + numZombies(node) + " people zombies and connected to " + node.connections.get(0).name);
        }
        
    }

    private int numZombies (vNode v)
    {
        int n = 0; 
        
        
        for (int x = 0; x < v.inhabitants.size(); x++)
        {
            if (v.inhabitants.get(x).getClass().equals(ZombieAgent.class)) n++; 
        }
        return n; 
    }
   
    public void createAgents(vNode n)
    {
         
            Random id = new Random(); 
            Integer i = id.nextInt(100000000);
            SurvivorAgent a = new SurvivorAgent(i.toString() , n); 
            n.inhabitants.add(a);
        
    }
    
    public void deadamount()
    {
        int dead=0;
        double total=0.0;
        int infected=0;
        for (int i = 0; i < nodes.size(); i++) 
        {
            vNode node = nodes.get(i);
            for (int j = 0; j < node.inhabitants.size(); j++) {
                Agent agent = node.inhabitants.get(j);
                total++;
                if (agent.isInfected()) {
                    infected++;
                }
                if (agent.stage >= 10) 
                {
                    dead++;
                }
                
            }
            
        }
        System.out.println(infected+" people infected out of "+ total);
        System.out.println(dead+" people zombies out of " + total);
        System.out.println(infected/total+" percent infected");
        System.out.println(dead/total+" percent dead");
        System.out.println((total-dead)/total+" percent living");
        System.out.println((total-infected)/total+" percent clean");

    }
    
    
}
