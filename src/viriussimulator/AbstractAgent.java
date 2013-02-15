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
public abstract class AbstractAgent 
{
    vNode location; 
    ArrayList<vNode> route;
    ArrayList<vNode> schedule; 
    String name; 
    boolean infected;
    int stage;
    double apearence;
    Virus virus;
    double avoidance; 
   
   
    
    public abstract void update(); 
    
    
     
    public abstract boolean changeLocation(vNode v);
    
    
    protected abstract ArrayList<vNode> generateSchedule(); 
    
    
    
    public abstract ArrayList<vNode> findRoute(vNode v);
    
    
    
    public abstract void infect(Virus vir); 
    
    public abstract boolean isInfected(); 
    
    public abstract Virus infection(); 
    
    public abstract void increaseStage();
    
    public abstract double getApperance(); 
    
    public abstract boolean avoid(Agent person); 
    
    public abstract void updateApperance(double avoidingPercent); 
    
    public abstract boolean isDead(); 
  
    public abstract double infectionRadius(); 
    
    public abstract void panicCheck(double percent);
    
}
