/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.enemies;

import city.cs.engine.BodyImage;

import city.cs.engine.PolygonShape;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.Walker;
import uk.mario.bowserAI.FSM;
import uk.mario.bowserAI.StandOnGroundState;
import uk.mario.Hero;
import uk.mario.levels.Level_4;

import uk.mario.listeners.Fireball_listener;
import uk.mario.listeners.Hero_killer_sensor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;

/**
 *
 * The main boss of the game which is a Walker with a behaviour  
 */
public class Bowser extends Walker implements SensorListener, ActionListener {

    Vec2 start_position;
    Level_4 level;

    private final BodyImage BOWSER_LOOKS_LEFT_1 = new BodyImage("sprites/enemies/bowser/bowser_looks_left_1.png", 7f);
    private final BodyImage BOWSER_LOOKS_LEFT_2 = new BodyImage("sprites/enemies/bowser/bowser_looks_left_2.png", 7f);
   
    private BodyImage last_sprite;

    private int life = 3;

    private ArrayList<Fireball> fireballs = new ArrayList<Fireball>();

    public FSM<Bowser> fsm;
    private Timer timer, triple_timer2, triple_timer3;
    private String fireball_frequency;

    /** 
     * Creates a bowser on level 4 at the specified position
     * @param level the level where bowser will be created
     * @param position the position where bowser will be created
     */
    public Bowser(Level_4 level, Vec2 position) {
        super(level);
        this.level = level;
        start_position = position;

        PolygonShape top_shape = new PolygonShape(-2.26f, 1.34f, -1.76f, 3.0f, 0.93f, 3.0f, 1.56f, 1.34f);
        PolygonShape bottom_shape = new PolygonShape(-2.74f, 1.32f, -2.57f, -3.0f, -2.41f, -3.17f, 2.41f, -3.17f, 2.57f, -3.0f, 2.18f, 1.33f);

        SolidFixture top_fixture = new SolidFixture(this, top_shape);
        SolidFixture bottom_fixture = new SolidFixture(this, bottom_shape);

        Sensor top_sensor = new Sensor(this, top_shape);
        Sensor bottom_sensor = new Sensor(this, bottom_shape);

        bottom_sensor.addSensorListener(new Hero_killer_sensor(level));
        top_sensor.addSensorListener(this);

        setPosition(start_position);
        last_sprite = BOWSER_LOOKS_LEFT_1;
        addImage(last_sprite);
        fireball_frequency = "SINGLE";
        fsm = new FSM<Bowser>(this, new StandOnGroundState());

    }
/** 
     * gets bowser
     * @return returns the class itself
     */
    public Bowser get_bowser() {
        return this;
    }
    /** 
     * Makes bowser to shot fireballs
     * @param frame is used to decide when bowser shoots
     */
    public void update_behaviour(int frame) {
        if (frame % 191 == 0) {
            if (fireball_frequency.equalsIgnoreCase("SINGLE")) {
                timer = new Timer(1, this);
                timer.setRepeats(false);
                timer.start();
                //System.out.println(fireball_frequency);
            }
            if (fireball_frequency.equalsIgnoreCase("TRIPLE")) {
                timer = new Timer(1, this);
                timer.setRepeats(false);
                timer.start();

                triple_timer2 = new Timer(300, this);
                triple_timer2.setRepeats(false);
                triple_timer2.start();

                triple_timer3 = new Timer(600, this);
                triple_timer3.setRepeats(false);
                triple_timer3.start();
                //System.out.println(fireball_frequency);
            }

        }
    }
    /** 
     * Change how should bowser shoot fireballs
     * @param frequency the frequency of how often it shoots the fireball
     */
    public void set_fireball_frequency(String frequency) {

        fireball_frequency = frequency;
    }
/** 
     * gets bowser's shooting frequency
     * @return returns the frequency
     */
    public String get_fireball_frequency() {
        return fireball_frequency;
    }

    public int get_life() {
        return life;
    }
/** 
     *  checks if the player is on a platform
     * @return true is he is on a platform or false if he isn't
     */
    public boolean player_on_platform() {
        if (level.get_hero().getPosition().y > 4) {
            return true;
        }
        return false;
    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody().getClass().equals(Hero.class)) {

            level.get_hero().jump(40f);
            life = life - 1;
            if (life == 0) {
                destroy();
            }

        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireballs.add(new Fireball());
       

    }
/** 
     * Creates the animaton for bowser's movement
     * @param frame the actual frame where the game is at
     */
    public void update_sprite(int frame) {
        float bowser_xPos = this.getPosition().x;

        //and he moves, then change image so it look like he walks
        if (Math.abs(this.getLinearVelocity().x) > 0) {
            if ((int) bowser_xPos % 2 == 0) {
                this.swap_sprite(BOWSER_LOOKS_LEFT_2);
            }
            if ((int) bowser_xPos % 2 == 1) {
                this.swap_sprite(BOWSER_LOOKS_LEFT_1);
            }
        }
    }
    /** 
     * Helper method to change the image of bowser for the animation
     */
    public void swap_sprite(BodyImage new_image) {
        this.removeImage(this.last_sprite);
        this.addImage(new_image);
        this.last_sprite = new_image;
    }

    
    /** 
     * Inner class for the fireball what bowser shoots
     */
    public class Fireball extends Dynamic_enemy {

        private final BodyImage FIREBALL_LEFT_1 = new BodyImage("sprites/enemies/bowser/fireball_left_1.png", 2f);
        private final BodyImage FIREBALL_LEFT_2 = new BodyImage("sprites/enemies/bowser/fireball_left_2.png", 2f);
        private final BodyImage FIREBALL_LEFT_3 = new BodyImage("sprites/enemies/bowser/fireball_left_3.png", 2f);
        private final BodyImage FIREBALL_RIGHT_1 = new BodyImage("sprites/enemies/bowser/fireball_right_1.png", 2f);
        private final BodyImage FIREBALL_RIGHT_2 = new BodyImage("sprites/enemies/bowser/fireball_right_2.png", 2f);
        private final BodyImage FIREBALL_RIGHT_3 = new BodyImage("sprites/enemies/bowser/fireball_right_3.png", 2f);

        private BodyImage last_sprite;
        private Vec2 velocity;

        private int bounced = 0;
/** 
     *  Creates a fireball at bowser's head, which will start to move to random direction
     */
        public Fireball() {
            super(level);
            setGravityScale(0);
            addCollisionListener(new Fireball_listener(level));
            PolygonShape fireball_shape = new PolygonShape(-1.533f, 0.071f, -0.596f, 0.996f, 0.867f, 0.322f, 1.525f, 0.008f, 0.855f, -0.467f, -0.353f, -0.988f, -0.867f, -0.984f);
            SolidFixture fireball_fixture = new SolidFixture(this, fireball_shape);

            setPosition(new Vec2(get_bowser().getPosition().x - 6.2f, get_bowser().getPosition().y + 1f));
            Random random = new Random();
            int randomNumber = (random.nextInt(12) - 6);
            velocity = new Vec2(-20f, randomNumber);
            setLinearVelocity(velocity);
            last_sprite = FIREBALL_LEFT_1;
            addImage(last_sprite);
            level.get_dynamic_enemies().add(this);
            SoundPlayer.playFireball();
        }
        /** 
     * Turns the fireball back if it hits the wall
     */
        public void turn(StaticBody wall) {

            bounced = bounced + 1;
            if (bounced == 6) {
                destroy();
            }
            if (wall == level.left_wall) {
                velocity = new Vec2(velocity.x * -1, velocity.y);
                setLinearVelocity(velocity);
            }
            if (wall == level.right_wall) {
                velocity = new Vec2(velocity.x * -1, velocity.y);
                setLinearVelocity(velocity);
            }
            if (wall == level.top_wall) {
                velocity = new Vec2(velocity.x, velocity.y * -1);
                setLinearVelocity(velocity);
            }
            if (wall == level.ground) {
                velocity = new Vec2(velocity.x, velocity.y * -1);
                setLinearVelocity(velocity);
            }
            if (wall == level.small_wall) {
                velocity = new Vec2(velocity.x * -1, velocity.y);
                setLinearVelocity(velocity);
            }

        }

        @Override
        public void turn() {

        }

        @Override
        public void die() {
            destroy();
        }

        @Override
        public void update_sprite() {
            float fireball_xPos = this.getPosition().x;

            //and he moves, then change image so it look like he walks
            if (this.getLinearVelocity().x < 0) {
                if ((int) fireball_xPos % 3 == 0) {
                    this.swap_sprite(FIREBALL_LEFT_2);
                    //System.out.println("swap to 2");
                }
                if ((int) fireball_xPos % 3 == 1) {
                    this.swap_sprite(FIREBALL_LEFT_3);
                    //System.out.println("swap to 3");
                }
                if ((int) fireball_xPos % 3 == 2) {
                    this.swap_sprite(FIREBALL_LEFT_1);
                    //System.out.println("swap to 1");
                }
            }
            
            if (this.getLinearVelocity().x > 0) {
                if ((int) fireball_xPos % 3 == 0) {
                    this.swap_sprite(FIREBALL_RIGHT_2);
                    //System.out.println("swap to 2");
                }
                if ((int) fireball_xPos % 3 == 1) {
                    this.swap_sprite(FIREBALL_RIGHT_3);
                    //System.out.println("swap to 3");
                }
                if ((int) fireball_xPos % 3 == 2) {
                    this.swap_sprite(FIREBALL_RIGHT_1);
                    //System.out.println("swap to 1");
                }
            }
            
            
        }

        @Override
        public void move_to_startposition() {

        }

        @Override
        public void jump(int frame) {

        }
/** 
     *  Animation for the fireball
     */
        public void swap_sprite(BodyImage new_image) {
            this.removeImage(this.last_sprite);
            this.addImage(new_image);
            this.last_sprite = new_image;
        }
    }
}
