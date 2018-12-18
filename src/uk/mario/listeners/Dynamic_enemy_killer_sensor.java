
package uk.mario.listeners;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import uk.mario.enemies.Dynamic_enemy;
import uk.mario.Hero;
import uk.mario.levels.Level;

/**
 *
 * A sensor listener that destroys the dynamic enemy where the sensor is attached if it meets with a hero
 */
public class Dynamic_enemy_killer_sensor implements SensorListener{

    Level level;
    Dynamic_enemy enemy;
    public Dynamic_enemy_killer_sensor(Level _level){
        level = _level;
    }
    
    
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody().getClass().equals(Hero.class)){
            enemy = (Dynamic_enemy) e.getSensor().getBody();
            enemy.die();
            level.get_hero().add_score(1);
            
        }
    }

    @Override
    public void endContact(SensorEvent e) {
        
    }
    
}