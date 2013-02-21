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
public class City extends location 
{   int numAgents; 
    public City (int streets, int nAgents)
    {
        /*numAgents = nAgents; 
        Random generator = new Random();
        System.out.println("Creating city");        
        nodes = new ArrayList<vNode>();        
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
        }
        buildingNode start = new buildingNode("start point", 5000);
        */
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
        buildingNode start = new buildingNode("start point", 5000);

        Virus infection = new illness();
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
            System.out.println(node.readOut());
        }
        
    };

    
    public ArrayList streetMaker(int size, int population, int street, int building)
    {
        Random generator = new Random();        
        ArrayList<vNode> val=new ArrayList<>();
        val.add(new streetNode("street "+street,generator.nextInt(100)));
        for (int i = 0; i < size; i++) 
        {
            building++;
            
            buildingNode b = new buildingNode("building "+ building, generator.nextInt(2000));
            //for (int y =0; y < population; y++) this.createAgents(b);
            val.add(b);
        }
        System.out.println("Adding connections"); 
     for (int i = 1; i < val.size(); i++) 
     {
        val.get(0).addConnection(val.get(i));
        val.get(i).addConnection(val.get(0)); 

     }
     System.out.println(val.get(0).connections.size()+" val connections");

      
     System.out.println(val.size()+" street size");
     return val;
    }
    public void createAgents(vNode n)
    {
         
            Random id = new Random(); 
            Integer i = id.nextInt(100000000);
            Agent a = new Agent(i.toString() , n); 
            n.inhabitants.add(a);
        
    }
    
}
