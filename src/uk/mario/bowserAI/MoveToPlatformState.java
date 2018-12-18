
package uk.mario.bowserAI;


import uk.mario.enemies.Bowser;
import org.jbox2d.common.Vec2;

/**
 * 
 * Bowser's move to platform state, makes bowser to jump to the platform and change to the other state when he reaches a certain position on the platform
 */

public class MoveToPlatformState extends FSMState<Bowser> {

    protected void update() {
        Bowser bowser = getContext();
        if (bowser.getPosition().x < 132) {
            gotoState(new StandOnPlatformState());
        } 
        
    }

    protected void enter() {
        Bowser bowser = getContext();
        
        bowser.applyImpulse(new Vec2(-250f,900f));
    }

    protected void exit() {
    }
}


