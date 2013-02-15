/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;

/**
 *
 * @author swishept
 */
public class zombievirus extends Virus{
     @Override
    public double stageApperence(int Stage) {
        return .8-(Stage/8);
    }


    @Override
    public boolean agentDeath(Agent agent) 
    {
        if (agent.stage>=10) 
        {
            ZombieAgent a = new ZombieAgent(agent.name, agent.location); 
            vNode l = agent.location;
            l.inhabitants.remove(agent);
            l.inhabitants.add(a);
            return true;
        }
        
        
    return false;
    }

    @Override
    public double infectionArea(int stage) {
    return 1;    
    }
    
}
