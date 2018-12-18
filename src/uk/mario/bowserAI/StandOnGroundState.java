
package uk.mario.bowserAI;


import uk.mario.enemies.Bowser;
import org.jbox2d.common.Vec2;
/**
 * 
 * Bowser's stand on the ground state stops bowser movement on the ground. He changes to an other state when the 
 * hero jumps to any platforms
 */
public class StandOnGroundState extends FSMState<Bowser> {

    protected void update() {
        Bowser bowser = getContext();
        if (bowser.player_on_platform()) {
            gotoState(new MoveToPlatformState());}
        else {}
        
    }

    protected void enter() {
        Bowser bowser = getContext();
        bowser.setLinearVelocity(new Vec2(0,0));
        bowser.set_fireball_frequency("SINGLE");
    }

    protected void exit() {
    }
}
