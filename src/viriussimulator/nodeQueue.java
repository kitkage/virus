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
public class nodeQueue 
{
    private ArrayList<vNode> theQueue; 
        
        public nodeQueue()
        {
            theQueue = new ArrayList<vNode>(); 
        }
        
        public void push (vNode n)
        {
            theQueue.add(n);
        }
        
        public vNode pull()
        {
            vNode temp = theQueue.get(0); 
            theQueue.remove(0);
            return temp; 
        }
        
        public boolean contains (vNode v)
        {
            return theQueue.contains(v); 
        }
        
        public ArrayList<vNode> toArrayList()
        {
            return theQueue; 
        }
        
        public boolean isEmpty()
        {
            return theQueue.isEmpty(); 
        }
    
}
