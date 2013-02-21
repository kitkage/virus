/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viriussimulator;

import java.util.ArrayList;
import java.util.*; 
/**
 *
 * @author baroba
 */
public class Control {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      //  Random generator = new Random(); 
        //int num = generator.nextInt(2000); 
        //if (num ==0) num = 1; 
       // int num = 5000; 

        int num = 2000; 
        City city = new City(2,num); 
        //ZombieCity city = new ZombieCity(2, num);
        for (int updates = 0; updates < 100; updates++)
        {
            city.update();
        
        }
        city.deadamount();
      
       
    }
}
