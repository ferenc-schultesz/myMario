/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.listeners;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.StaticBody;
import uk.mario.enemies.Cannon.Bullet;
import uk.mario.Hero;
import uk.mario.levels.Level;

/**
 *
 * A sensor listener for the sensor that attached to the body of the bullets
 */
public class Bullet_body_sensor implements SensorListener {
    Bullet bullet;
    Level level;
    
    public Bullet_body_sensor (Bullet bullet, Level level){
        this.bullet = bullet;
        this.level = level;
    }
    /**
     * if this sensor meets with the hero it kills him if with anything else the bullet gets destroyed
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody().getClass().getSuperclass().equals(StaticBody.class)){
            
            bullet.destroy();
        }
        if (e.getContactBody().getClass().equals(Hero.class)){
            Hero hero = (Hero) e.getContactBody();
            level.hero_dies();
        }
    }

    @Override
    public void endContact(SensorEvent e) {
        
    }
    
}
