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
public abstract class vNode 
{
    public ArrayList<vNode> connections; 
    public ArrayList<Agent> inhabitants; 
    public double size; 
    public String name; 
    
    public abstract void inhabitantEnters(Agent a); 
    
    public abstract boolean inhabitantExits(Agent a, vNode v); 
    
    
}
