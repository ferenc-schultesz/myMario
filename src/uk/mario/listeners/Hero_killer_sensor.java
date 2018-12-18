/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.listeners;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import uk.mario.Hero;
import uk.mario.levels.Level;

/**
 *
 * A sensor listener that kills the hero when it meets the sensor. Used by enemies all over the game
 */
public class Hero_killer_sensor implements SensorListener{

    Level level;
    
    public Hero_killer_sensor(Level _level){
        level = _level;
    }
    
    
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody().getClass().equals(Hero.class)){
            level.hero_dies();
        }
    }

    @Override
    public void endContact(SensorEvent e) {
        
    }
    
}
