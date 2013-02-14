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
    public boolean agentDeath(Agent agent) {
    return false;
    }

    @Override
    public double infectionArea(int stage) {
    return 10;    
    }
    
}
