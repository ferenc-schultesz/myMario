package uk.mario.listeners;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import uk.mario.enemies.Dynamic_enemy;

/**
 *
 * A sensor listener that makes the dynamic enemies turn if the meet with an other dynamic enemy
 */
public class Dynamic_enemy_turner_sensor implements SensorListener {

    @Override
    public void beginContact(SensorEvent e) {

        if (e.getContactBody().getClass().getSuperclass().equals(Dynamic_enemy.class)) {
            Dynamic_enemy enemy = (Dynamic_enemy) e.getContactBody();
            enemy.turn();
        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }

}
