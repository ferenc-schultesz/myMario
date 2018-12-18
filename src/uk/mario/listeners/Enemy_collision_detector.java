package uk.mario.listeners;

import uk.mario.enemies.Dynamic_enemy;
import uk.mario.enemies.Static_enemy;
import city.cs.engine.*;
import uk.mario.levels.Level;


/**
 *
 * The collision detector for the enemies
 */
public class Enemy_collision_detector implements CollisionListener {

    Dynamic_enemy dynamic_colliding_enemy;
    Static_enemy static_colliding_enemy;
    Level level;

    public Enemy_collision_detector(Level _level) {
        this.level = _level;

    }
    /**
     * Checks what is the reporting body and calls the specified methods
     */
    @Override
    public void collide(CollisionEvent e) {
        //Check what is the reporting body
        if (e.getReportingBody().getClass().getSuperclass().equals(Dynamic_enemy.class)) {
            calculate_dynamic_enemy_collision(e);
        }
        if (e.getReportingBody().getClass().equals(StaticBody.class)) {
            calculate_static_enemy_collision(e);
        }
       
    }

    /**
     * If the reporting body is a dynamic enemy and the other body is not a hero it turns the enemy
     */

    public void calculate_dynamic_enemy_collision(CollisionEvent e) {
        // and the other body is not a player
       
        if ( e.getOtherBody().getClass().getSuperclass().equals(Dynamic_enemy.class)
                ){
            // then turn the enemy
            dynamic_colliding_enemy = (Dynamic_enemy)e.getReportingBody();
            dynamic_colliding_enemy.turn();
        }
        
    }

    //if the a static body collide with a hero then kill the hero
/**
     * if the reporting body is a static enemy and the other body is a hero it kills the hero
     */
    private void calculate_static_enemy_collision(CollisionEvent e) {
        if (e.getOtherBody() == level.get_hero()) {
            level.hero_dies();
        }
    }

}
