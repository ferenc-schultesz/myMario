package uk.mario.listeners;

import uk.mario.Hero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;
import uk.mario.control.GameController;


    /**
     *  The keylistener for the Hero. It listens to the left right and up arrow and the shift keys and controls him by those
     */
public class HeroController implements KeyListener {

    private Hero hero;
    private boolean run, right, left;
    private GameController gameController;
    
    public HeroController(GameController gameController) {
        System.out.println("TEST");
        this.gameController = gameController;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Key pressed"); //for testing
        if (gameController.getLevelStarted()) {
            hero = gameController.getLevel().get_hero();

            if (e.getKeyCode() == 16) {
                run = true;
            }

            if (e.getKeyCode() == 39) {
                right = true;
            }

            if (e.getKeyCode() == 37) {
                left = true;
            }

            Vec2 v = new Vec2(hero.getLinearVelocity());

            if (right) {
                hero.startWalking(5);
                if (run) {
                    hero.startWalking(10f);
                }
                hero.set_hero_look_right(true);
            }

            if (left) {

                hero.startWalking(-5);
                if (run) {
                    hero.startWalking(-10f);
                }
                hero.set_hero_look_right(false);
            }

            if ((e.getKeyCode() == 38) && (Math.abs(v.y) < 0.01f)) {
                hero.jump(21f);
                SoundPlayer.playHeroJumps();
            }

            if (e.getKeyCode() == 27) {
                gameController.addPauseMenu();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameController.getLevelStarted()) {
            hero = gameController.getLevel().get_hero();
            if (e.getKeyCode() == 16) {
                run = false;
            }
            if (e.getKeyCode() == 39) {
                hero.stopWalking();
                if (hero.getLinearVelocity().y > 0) {
                    hero.setLinearVelocity(new Vec2(0f, hero.getLinearVelocity().y));
                }
                hero.set_hero_look_right(true);
                right = false;
            }
            if (e.getKeyCode() == 37) {
                hero.stopWalking();
                if (hero.getLinearVelocity().y > 0) {
                    hero.setLinearVelocity(new Vec2(0f, hero.getLinearVelocity().y));
                }
                hero.set_hero_look_right(false);
                left = false;
            }
        }
    }

}
