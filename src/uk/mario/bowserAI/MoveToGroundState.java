
package uk.mario.bowserAI;

import uk.mario.enemies.Bowser;
import org.jbox2d.common.Vec2;

/**
 *
 * Bowser's move to ground state. It applies an impulse to "jump" backwards down from the platform, and when he reaches a certain x position
 * on the ground it calls the next state
 */
public class MoveToGroundState extends FSMState<Bowser> {
    protected void update() {
        Bowser bowser = getContext();
        
        if (bowser.getPosition().y< 3.4f){
            gotoState(new StandOnGroundState());
        }
    }

    protected void enter() {
        Bowser bowser = getContext();
        bowser.applyImpulse(new Vec2(350, 0));
    }

    protected void exit() {
    }
}
