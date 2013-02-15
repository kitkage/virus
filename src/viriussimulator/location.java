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

    public void deadamount(){
        int dead=0;
        int total=0;
        for (int i = 0; i < nodes.size(); i++) {
            vNode node = nodes.get(i);
            for (int j = 0; j < node.inhabitants.size(); j++) {
                Agent agent = node.inhabitants.get(j);
                total++;
                if (agent.isDead()) {
                    dead++;
                }
                
            }
            
        }
        System.out.println(dead+" people dead out of "+total);
    }
    
}
