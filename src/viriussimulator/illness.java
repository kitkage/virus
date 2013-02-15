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
    public double stageApperence(int Stage) {
        return .8-(Stage/8);
    }


    @Override
    public boolean agentDeath(Agent agent) {
        if (agent.stage>=10) {
            return true;
        }
    return false;
    }

    @Override
    public double infectionArea(int stage) {
    return 10;    
    }
    
}
