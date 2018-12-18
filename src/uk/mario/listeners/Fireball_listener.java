package uk.mario.listeners;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import uk.mario.enemies.Bowser.Fireball;
import uk.mario.Hero;
import uk.mario.levels.Level_4;
import org.jbox2d.common.Vec2;

/**
 *
 * A collision listener for the fireballs. It turns back the fireballs when they hit the wall, and kills the hero and the fireball when they meet eachother
 */
public class Fireball_listener implements CollisionListener {

    Level_4 level;
    Fireball fireball;
    Vec2 velocity;

    public Fireball_listener(Level_4 level) {
        this.level = level;

    }

    @Override
    public void collide(CollisionEvent e) {
        fireball = (Fireball) e.getReportingBody();
        velocity = fireball.getLinearVelocity();

        if (e.getOtherBody() == level.left_wall) {
            fireball.turn(level.left_wall);
        } else {
            if (e.getOtherBody() == level.right_wall) {
                fireball.turn(level.right_wall);
            } else {
                if (e.getOtherBody() == level.top_wall) {
                    fireball.turn(level.top_wall);
                } else {

                    if (e.getOtherBody() == level.ground) {
                        fireball.turn(level.ground);
                    } else {
                        if (e.getOtherBody() == level.small_wall) {
                            fireball.turn(level.small_wall);
                        } else {
                            e.getReportingBody().destroy();
                        }
                    }
                }
            }
        }


        if (e.getOtherBody().getClass().equals(Hero.class)) {
            level.hero_dies();
            fireball.destroy();
        }
    }

}
