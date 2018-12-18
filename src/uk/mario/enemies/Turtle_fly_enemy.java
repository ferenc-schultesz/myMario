/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.enemies;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import uk.mario.listeners.Dynamic_enemy_killer_sensor;
import uk.mario.listeners.Enemy_collision_detector;
import uk.mario.listeners.Hero_killer_sensor;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;

/**
 *
 * A special dynamic enemy for the game
 */
public class Turtle_fly_enemy extends Dynamic_enemy {
    private boolean looks_right;
    private BodyImage last_sprite;
    private BodyImage turtle_move_left_one = new BodyImage("sprites/enemies/Enemy_Dynamic/enemy_turtle_fly_left_one.png", 2.7f);
    private BodyImage turtle_move_left_two = new BodyImage("sprites/enemies/Enemy_Dynamic/enemy_turtle_fly_left_two.png", 2.7f);
    private BodyImage turtle_move_right_one = new BodyImage("sprites/enemies/Enemy_Dynamic/enemy_turtle_fly_right_one.png", 2.7f);
    private BodyImage turtle_move_right_two = new BodyImage("sprites/enemies/Enemy_Dynamic/enemy_turtle_fly_right_two.png", 2.7f);
    private BodyImage last_image;
    private float size = 2.7f;
    private float speed;
    private Vec2 startposition;

    
    /** 
     * Creates a turtle enemy for the game at the specified position, level, with a speed
     * @param _position the position where we want the turtle
     * @param _speed the starting speed of the enemy
     * @param _level the level where we create the nemy
     * @param type the type of the turtle enemy(at the moment we only have one type we might extend later
     */
    public Turtle_fly_enemy(Vec2 _position, float _speed, String type, Level _level) {
        super(_level);
        speed = _speed;
        startposition = _position;
        
        PolygonShape top_shape = new PolygonShape(-0.73f,0.114f, -0.62f,1.239f, 0.623f,1.246f, 0.72f,0.104f);
        PolygonShape bottom_shape = new PolygonShape(-1.038f,0.104f, -0.834f,-1.35f, 0.834f,-1.357f, 1.035f,0.097f);

        Sensor top_sensor = new Sensor(this, top_shape);
        Sensor bottom_sensor = new Sensor(this, bottom_shape);

        SolidFixture top_fixture = new SolidFixture(this, top_shape);
        SolidFixture bottom_fixture = new SolidFixture(this, bottom_shape);

        bottom_sensor.addSensorListener(new Hero_killer_sensor(_level));
        top_sensor.addSensorListener(new Dynamic_enemy_killer_sensor(_level));
        
        
        if (speed > 0){looks_right = true;} else {
            looks_right = false;
        }
        addCollisionListener(new Enemy_collision_detector(_level));
        startWalking(speed);
        setPosition(_position);
        addImage(turtle_move_right_one);
        last_image = turtle_move_right_one;
        _level.get_dynamic_enemies().add(this);
        
    }

    @Override
    public void turn() {
       speed = speed * -1;
        startWalking(speed);
        looks_right = !looks_right;
    }

    @Override
    public void die() {
       destroy();
       SoundPlayer.playTurtleEnemyDies();
    }

    @Override
    public void update_sprite() {
        float turtle_xPos = this.getPosition().x;
        
        
        //if he looks right
        if (this.looks_right) {
            //and he moves, then change image so it look like he walks
            if (Math.abs(this.getLinearVelocity().x) > 0) {
                if ((int) turtle_xPos % 2 == 0) {
                    this.swap_sprite(turtle_move_right_one);
                }
                if ((int) turtle_xPos % 2 == 1) {
                    this.swap_sprite(turtle_move_right_two);
                }
            }
            
        }

        //if he look left
        if (!this.looks_right) {
            //and he moves, then change image so it look like he walks
            if (Math.abs(this.getLinearVelocity().x) > 0) {
                if ((int) turtle_xPos % 2 == 0) {
                    this.swap_sprite(turtle_move_left_one);
                }
                if ((int) turtle_xPos % 2 == 1) {
                    this.swap_sprite(turtle_move_left_two);
                }
            }
           
        }

    }
    /** 
     * Changes the image of the enemy we use the for the animation
     * @param new_image the next image of the animation
     */
     public void swap_sprite(BodyImage new_image) {
        this.removeImage(this.last_image);
        this.addImage(new_image);
        this.last_image = new_image;
    }

    @Override
    public void move_to_startposition() {
        setPosition(startposition);
    }

    @Override
    public void jump(int frame) {
        if (frame % 150 == 0) {
            this.jump(10f);

        }
    }

}
