/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;

/**
 *
 * @author swishept
 */
public abstract class Virus 
{
    public double infection;
    
    public abstract float stageApperence(int Stage);
    
    public abstract void spreadInfection(vNode location);
    
    public abstract boolean agentDeath(Agent agent);
}
