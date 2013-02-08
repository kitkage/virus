/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;

import java.util.ArrayList;

/**
 *
 * @author swishept
 */
public abstract class location {
    
    ArrayList<vNode> nodes;
    int numStartingAgents;
    
    public abstract void update();
    public abstract double deadPercent();
    
    
}
