package viriussimulator;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author swishept
 */
public class illness extends Virus{

    @Override
    public float stageApperence(int Stage) {
        return 0;
    }

    @Override
    public void spreadInfection(vNode location) {
        ArrayList<Agent> people= location.inhabitants;
        int numbInfected=0;
        for (int i = 0; i < people.size(); i++) {
            Agent person = people.get(i);
            if(person.isInfected()){
                numbInfected+=1;
            }
        }
        for (int i = 0; i < people.size(); i++) {
            Agent person = people.get(i);
            if(!person.isInfected()&&Math.random()<numbInfected/people.size()){
                person.infect(this);
            }
        }
        
    }

    @Override
    public boolean agentDeath(Agent agent) {
    return false;
    }
    
}
