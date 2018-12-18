
package uk.mario.bowserAI;


import uk.mario.enemies.Bowser;
import org.jbox2d.common.Vec2;
/**
 * 
 * Bowser's stand on platform state makes bowser to stop and stand still after he reaches a certain coordinate on the platform, and he stays in this state until the 
 * hero jumps of the platforms to the ground
 */

public class StandOnPlatformState extends FSMState<Bowser> {

    protected void update() {
        Bowser bowser = getContext();
        if (!bowser.player_on_platform()) {
            gotoState(new MoveToGroundState());}
        else {}
        
    }

    protected void enter() {
        Bowser bowser = getContext();
        bowser.setLinearVelocity(new Vec2(0,0));
        bowser.set_fireball_frequency("TRIPLE");
    }

    protected void exit() {
    }
}
