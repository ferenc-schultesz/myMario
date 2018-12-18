/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.enemies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import uk.mario.levels.Level;
import uk.mario.listeners.Bullet_body_sensor;
import uk.mario.listeners.Dynamic_enemy_killer_sensor;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;

/**
 *
 * A cannon enemy for the game that shoots bullets at random timew
 */
public class Cannon extends StaticBody {
    
    Vec2 position;
    BodyImage cannon_image = new BodyImage("sprites/enemies/enemy_static/cannon.png", 4.2f);
    Level level;
    static ArrayList<Bullet> bullets = new ArrayList <Bullet>();
    /** 
     * Creates a cannon at the specified level and location
     * @param level the level where the cannon will appear
     * @param position the position on the actual level
     */
    public Cannon (Level level, Vec2 position){
        super(level);
        
        this.position = position;
        this.level = level;
        
        
        BoxShape base_shape = new BoxShape (0.95f,2f);
        BoxShape left_fixture_shape = new BoxShape(0.05f, 1.99f, new Vec2(-1f,0f)); 
        BoxShape right_fixture_shape = new BoxShape(0.05f, 1.99f, new Vec2(1f,0f)); 
        SolidFixture left_fixture = new SolidFixture (this, left_fixture_shape);
        SolidFixture right_fixture = new SolidFixture (this, right_fixture_shape);
        left_fixture.setFriction(0);        
        right_fixture.setFriction(0);

        SolidFixture base_fixture = new SolidFixture (this,base_shape);
        addImage(cannon_image);
        setPosition(position);
        
    }
    /** 
     * Shoot the cannon
     * @param frame the frame where the game is, helps us calculate when the cannon shoots
     */
    public void update_cannon(int frame){
        if (frame % 191 == 0){
           bullets.add(new Bullet());
           
        }
    }
    
    
    /** 
     * An inner class for the bullets
     */
    
    public class Bullet extends Dynamic_enemy{
        BodyImage bullet_image = new BodyImage ("sprites/enemies/enemy_static/bullet.png", 2f);
        
        /** 
     * Creates a bullet at the cannon that starts to move in a straight line to the left
     */
        
        public Bullet (){
            super(level);
            setGravityScale(0);
            BoxShape bullet_shape = new BoxShape (1f, 0.7f);
            BoxShape top_sensor_shape = new BoxShape (0.9f,0.05f, new Vec2(0f, 0.8f));
            
            Sensor top_sensor = new Sensor(this,top_sensor_shape);
            top_sensor.addSensorListener(new Dynamic_enemy_killer_sensor(level));
            Sensor base_sensor = new Sensor (this, bullet_shape);
            base_sensor.addSensorListener(new Bullet_body_sensor(this, level));
            
            GhostlyFixture bullet_fixture = new GhostlyFixture(this, bullet_shape);
            
            
            setPosition(new Vec2 (position.x - 2.2f, position.y+ 1f));
            setLinearVelocity(new Vec2(-10f,0));
            
            addImage(bullet_image);
            SoundPlayer.playCannon();
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
            
        }

        @Override
        public void move_to_startposition() {
            
        }

        @Override
        public void jump(int frame) {
            
        }
    }
    
}
