package uk.mario.levels;

import city.cs.engine.*;
import uk.mario.enemies.Bowser;
import uk.mario.enemies.Fire_rod;
import uk.mario.Hero;
import uk.mario.levelelements.Platform;

import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;
import uk.mario.control.GameController;

/**
 *
 * The 4th level of the game
 */
public final class Level_4 extends Level implements SensorListener {

    private static Vec2 PLAYER_START_POSITION = new Vec2(80f, 2f); //x: 20 for default
    private final Vec2 FINISH_BARREL_POSITION = new Vec2(-4444f, 2f);

    private BodyImage groundimage;
    private final int LEVEL_CODE = 4;
    public StaticBody left_wall;
    public StaticBody right_wall;
    public StaticBody top_wall;
    public StaticBody ground;
    public StaticBody small_wall;
    private Sensor start_event_sensor;
    private Detonator detonator;

   
    // ------------------------- LEVEL METHODS -------------------------------
    /**
     * The setup method of the level we create every elements here such as, dynamic and static enemies, plaforms
     * pickups, static elements etc
     * @param gameController the controller class for the game
     */
    @Override
    public void setup_level(GameController gameController)  {

        super.setup_level(gameController);

        
        detonator = new Detonator(this, gameController);
        get_fire_rods().add(new Fire_rod(this, new Vec2(135, -3f)));
        get_fire_rods().add(new Fire_rod(this, new Vec2(120, -3f)));
        get_fire_rods().add(new Fire_rod(this, new Vec2(105, -3f)));
        
        get_platforms().add(new Platform(this, 4, new Vec2(82f,6f), "GOLDEN"));
        get_platforms().add(new Platform(this, 3, new Vec2(98f,11f), "GOLDEN"));
        get_platforms().add(new Platform(this, 3, new Vec2(110f,18f), "GOLDEN"));
        get_platforms().add(new Platform(this, 1, new Vec2(113f,6f), "GOLDEN"));
        get_platforms().add(new Platform(this, 3, new Vec2(128f,8f), "GOLDEN"));
        
        {

            // make the ground  
            groundimage = new BodyImage("sprites/background/level_3/ground_sprite.png", 4f);

            Shape shape = new BoxShape(250, 0.1f);
            ground = new StaticBody(this, shape);
            ground.setPosition(new Vec2(170, 0f));
            //ground.addImage(groundimage, new Vec2(-248.5f, 0f));
            ground.setClipped(false);
            for (float i = -248.5f; i < 250f; i = i + 4f) {
                ground.addImage(groundimage, new Vec2(i + 3.5f, -2f));
            }

            Shape wall_shape = new BoxShape(1, 17);
            StaticBody wall = new StaticBody(this, wall_shape);
            wall.setPosition(new Vec2(-3f, 20f));
            wall.addImage(new BodyImage("sprites/platforms/invisible_wall.png"));
            StaticBody wall2 = new StaticBody(this, wall_shape);
            wall2.setPosition(new Vec2(346f, 20f));
            wall2.addImage(new BodyImage("sprites/platforms/invisible_wall.png"));
        }

        Shape side_wall_shape = new BoxShape(1, 16.5f);
        left_wall = new StaticBody(this, side_wall_shape);
        left_wall.setPosition(new Vec2(75f, 22));
        BodyImage wall_piece_image = new BodyImage("sprites/platforms/platform_ground_element.png", 2f);
        for (float i = -17.5f ; i < 15.5f ; i = i + 2f){
            left_wall.addImage(wall_piece_image, new Vec2(0, i +2));
        }
        
        
        right_wall = new StaticBody(this, side_wall_shape);
        right_wall.setPosition(new Vec2(150f, 22));
        for (float i = -17.5f ; i < 15.5f ; i = i + 2f){
            right_wall.addImage(wall_piece_image, new Vec2(0, i +2));
        }
        Shape top_wall_shape = new BoxShape(36.5f, 1);
        top_wall = new StaticBody(this, top_wall_shape);
        top_wall.setPosition(new Vec2(112.5f, 37.5f));
        for (float i = -37.5f ; i < 35.5f ; i = i + 2f){
            top_wall.addImage(wall_piece_image, new Vec2(i + 2, 0));
        }
        
        {

            

        }

        BoxShape event_starter_sensor_shape = new BoxShape(1f, 4f, new Vec2(-80, 6f));
        start_event_sensor = new Sensor(ground, event_starter_sensor_shape);
        start_event_sensor.addSensorListener(this);

        this.start();

    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody().getClass().equals(Hero.class) && !bowser_alive()) {
            BoxShape small_wall_shape = new BoxShape(1f, 2.7f);
            small_wall = new StaticBody(this, small_wall_shape);
            small_wall.setPosition(new Vec2(75f, 2.8f));
            BodyImage wall_piece_image = new BodyImage("sprites/platforms/platform_ground_element.png", 2f);
            for (float i = -3.6f ; i < 1f ; i = i + 2f){
            small_wall.addImage(wall_piece_image, new Vec2(0, i +2));
        }
            
            bowser = new Bowser(this, new Vec2(145f, 5f));
            has_bowser = true;
            PLAYER_START_POSITION = new Vec2 (90f , 2f);
            
        }
    }

    @Override
    public void endContact(SensorEvent e) {

    }

    //----------------- Overriding ABSTRACT METHODS-----------------------------
    //overriding the abstract game_over() to deal with what happens if we die
    @Override
    public void hero_dies() {
    SoundPlayer.playHeroDies();
        this.stop();

        get_hero().setPosition(PLAYER_START_POSITION);
        start();

        // setting every flower to its original position
        //reason: hero can knock the flower out of the pipe when it hits it
        for (int i = 0; i < get_pipes().size(); i++) {
            if (get_pipes().get(i).with_flower()) {
                get_pipes().get(i).get_flower().setLinearVelocity(new Vec2(0f, 0f));
                get_pipes().get(i).get_flower().setPosition(get_pipes().get(i).get_flower().get_original_position());
            }
        }
        get_hero().addLife(-1);
        if (get_hero().get_life() < 1){
            getGameController().gameOver();
           
        }
    }

    @Override
    public Vec2 start_position() {
        return PLAYER_START_POSITION;
    }

    @Override
    public int get_level_code() {
        return LEVEL_CODE;
    }

    @Override
    public Vec2 set_finish_item_position() {
        return FINISH_BARREL_POSITION;
    }

    //--------------- GETTER and SETTER methods --------------------------------
   
    
    private class Detonator extends StaticBody implements SensorListener{
    
        private BodyImage detonator_image  = new BodyImage ("sprites/items/detonator.png", 3f);
        private GameController gameController;
        private Level level;
        public Detonator(Level level, GameController gameController){
            super(level);
            this.level = level;
            this.gameController = gameController;
            BoxShape detonator_shape = new BoxShape (1.5f,1.5f);
            SolidFixture base_fixture = new SolidFixture(this, detonator_shape);
            
            BoxShape sensor_shape = new BoxShape (1.4f,0.1f, new Vec2(0,1.6f));
            Sensor detonator_sensor = new Sensor (this, sensor_shape);
            this.addImage(detonator_image);
            setPosition(new Vec2(180f, 1.6f));
            detonator_sensor.addSensorListener(this);
        
        }

        @Override
        public void beginContact(SensorEvent e) {
                level.stop();
                getGameController().startLevel(5);
            
        }

        @Override
        public void endContact(SensorEvent e) {
        }
    }

}
