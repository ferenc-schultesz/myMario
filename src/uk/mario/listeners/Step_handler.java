package uk.mario.listeners;

import uk.mario.enemies.Dynamic_enemy;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

import uk.mario.enemies.Static_enemy;
import uk.mario.MarioView;
import uk.mario.levelelements.Pipe;
import uk.mario.levels.GameEndAnimation;
import java.util.ArrayList;
import uk.mario.levels.Level;
import uk.mario.levels.Level_4;

import org.jbox2d.common.Vec2;

/**
 *
 * The steplistener of the game is used for enemies animations and behaviours, also for other animations.
 * It sends the frame counter variable's value to the enemies, elements so they can do their animations and behaviours at a certain time
 */
public class Step_handler implements StepListener {

    int frame_counter = 0;
    Level level;
    MarioView view;
   
    public Step_handler(Level _level, MarioView _view) {
        level = _level;
        view = _view;
    }

    @Override
    public void preStep(StepEvent e) {
        frame_counter += 1;
        if (frame_counter == 10000) {
            frame_counter = 0;
        }

        level.get_hero().update_sprite();
        update_dynamic_enemy_sprites();
        update_black_flowers();
        jump_flowers();
        update_coin_sprites();
        update_rod_sprites();
        update_cannons();
        if (level.getClass().equals(Level_4.class) && level.bowser_alive()) {
            level.get_bowser().update_behaviour(frame_counter);
            level.get_bowser().fsm.update();
            level.get_bowser().update_sprite(frame_counter);
            //System.out.println(level.get_bowser().get_fireball_frequency());
        }
    }

    @Override
    public void postStep(StepEvent e) {
        if (!level.bowser_alive() || level.get_hero().getPosition().x > 147) {
            view.setCentre(new Vec2(level.get_hero().getPosition().x, 17.5f));
        } else {
            view.setCentre(new Vec2(112.5f, 17.5f));
        }
        if (level.getClass().equals(GameEndAnimation.class)){
            view.setCentre(new Vec2(112.5f, 18.5f));
        }
    }
/**
     *  Calls every dynamic enemies update method to make the animations
     */
    private void update_dynamic_enemy_sprites() {
        ArrayList<Dynamic_enemy> enemies = level.get_dynamic_enemies();
        for (int i = 0; i < enemies.size(); i++) {

            Dynamic_enemy enemy = enemies.get(i);
            enemy.update_sprite();
            enemy.jump(frame_counter);
        }
    }
/**
     *  Sends the frame for the flowers to make them jump
     */
    private void jump_flowers() {
        ArrayList<Pipe> pipes = level.get_pipes();
        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).with_flower()) {
                pipes.get(i).jump_flower(frame_counter);
            }
        }
    }
/**
     * Send the frames for the black flowers to make their animations
     */
    private void update_black_flowers() {
        ArrayList<Static_enemy> flowers = level.get_static_enemies();
        for (int i = 0; i < flowers.size(); i++) {
            flowers.get(i).update_sprite(frame_counter);
        }
    }
/**
     * Send the frames for the coins to make their animations
     */
    public void update_coin_sprites() {
        for (int i = 0; i < level.get_coins().size(); i++) {
            level.get_coins().get(i).update_sprite(frame_counter);
        }
    }
/**
     * Send the frames for the fire rods to make their animations
     */
    public void update_rod_sprites() {
        for (int i = 0; i < level.get_fire_rods().size(); i++) {
            level.get_fire_rods().get(i).update_sprite(frame_counter);
        }
    }
/**
     * Send the frames for the cannons to make their animations
     */
    public void update_cannons() {
        for (int i = 0; i < level.get_cannons().size(); i++) {
            level.get_cannons().get(i).update_cannon(frame_counter);
        }
    }

}
