/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;
import java.util.ArrayList;

/**
 *
 * @author baroba
 */
public class City extends location 
{
    public City (int numAgents)
    {
     nodes = new ArrayList<vNode>(); 
     numStartingAgents = numAgents; 
    }
    public  void update()
    {
        
    };
    public  double deadPercent()
    {
        return 0; 
    };
    
}
