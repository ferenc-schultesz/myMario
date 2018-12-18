package uk.mario.levels;

import uk.mario.levelelements.Platform;
import city.cs.engine.*;
import uk.mario.enemies.Brown_enemy;
import uk.mario.enemies.Fire_rod;
import uk.mario.enemies.Turtle_fly_enemy;
import uk.mario.levelelements.Coin;
import uk.mario.levelelements.Pipe;
import uk.mario.listeners.Hero_killer_sensor;
import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;
import uk.mario.control.GameController;

/**
 *
 * The second level of the game
 */
public final class Level_2 extends Level {

    private static final Vec2 PLAYER_START_POSITION = new Vec2(3f, 2f);
    private final Vec2 FINISH_BARREL_POSITION = new Vec2 (422f,19f);
    
    private Castle castle;
    
    private BodyImage groundimage;
    private final int LEVEL_CODE = 2;
    
    //variables for testing purpose
    private Turtle_fly_enemy turtle;
    private Fire_rod rod;

  

    // ------------------------- LEVEL METHODS -------------------------------
    /**
     * The setup method of the level we create every elements here such as, dynamic and static enemies, plaforms
     * pickups, static elements etc
     * @param gameController the controller class for the game
     */
    @Override
    public void setup_level(GameController gameController) {

        super.setup_level(gameController);
        
        castle = new Castle (this, FINISH_BARREL_POSITION, gameController);
        
        {
            BoxShape bottom_sensor_shape =  new BoxShape (250f,0.2f);
            Body ground = new StaticBody(this,bottom_sensor_shape);
            Sensor killer_sensor = new Sensor(ground, bottom_sensor_shape);
            ground.setPosition(new Vec2(170f,-7f));
            killer_sensor.addSensorListener(new Hero_killer_sensor(this));
            
            
            Shape left_wall_shape = new BoxShape(1, 20);
            StaticBody wall = new StaticBody(this, left_wall_shape);
            wall.setPosition(new Vec2(-3f, 20f));
            wall.addImage(new BodyImage("sprites/platforms/invisible_wall.png"));
        }
        {
            
            
            get_platforms().add(new Platform(this, 8, new Vec2 (3f, -1f), "TRUNK"));
            get_platforms().add(new Platform(this, 5, new Vec2 (20f, 6f), "CLOUD"));
            get_coins().add(new Coin(this, new Vec2(22f, 12f)));
            get_coins().add(new Coin(this, new Vec2(24f, 12f)));
            get_coins().add(new Coin(this, new Vec2(26f, 12f)));
            get_platforms().add(new Platform(this, 18, new Vec2 (42f, 6f), "CLOUD"));         
            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(44f,7f), 4, "TURTLE", this));
            
            
            get_platforms().add(new Platform(this, 4, new Vec2 (96f, 6f), "CLOUD")); 
            get_fire_rods().add(new Fire_rod(this, new Vec2 (90f, 18f)));
            
            get_platforms().add(new Platform(this, 4, new Vec2 (118f, 6f), "CLOUD"));
            get_fire_rods().add(new Fire_rod(this, new Vec2 (112f, 18f)));
            
            get_platforms().add(new Platform(this, 4, new Vec2 (136f, 2f), "CLOUD"));
            get_fire_rods().add(new Fire_rod(this, new Vec2 (138f, 12f)));
            
            get_platforms().add(new Platform(this, 4, new Vec2 (148f, 8f), "CLOUD"));
            get_coins().add(new Coin(this, new Vec2(150f, 14f)));
            get_coins().add(new Coin(this, new Vec2(152f, 14f)));
            get_platforms().add(new Platform(this, 5, new Vec2 (164f, 8f), "CLOUD"));
            
            get_fire_rods().add(new Fire_rod(this, new Vec2 (179f, 4f)));
            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(167f,9f), 4, "TURTLE", this));
            
            get_platforms().add(new Platform(this, 5, new Vec2 (184f, 8f), "CLOUD"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(187f, 9f), 4, "BROWN", this));
            
            get_platforms().add(new Platform(this, 3, new Vec2 (204, 8f), "CLOUD"));
            get_coins().add(new Coin(this, new Vec2(206f, 14f)));
            get_platforms().add(new Platform(this, 3, new Vec2 (214, 13f), "CLOUD"));
            
            get_fire_rods().add(new Fire_rod(this, new Vec2 (230f, 10.5f)));
            get_platforms().add(new Platform(this, 3, new Vec2 (228, 13f), "CLOUD"));
            
            get_fire_rods().add(new Fire_rod(this, new Vec2 (244f, 10.5f)));
            get_platforms().add(new Platform(this, 3, new Vec2 (242, 13f), "CLOUD"));
            get_coins().add(new Coin(this, new Vec2(246f, 19f)));
            get_coins().add(new Coin(this, new Vec2(246f, 21f)));
            get_coins().add(new Coin(this, new Vec2(244f, 19f)));
            get_coins().add(new Coin(this, new Vec2(244f, 21f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2 (258f, 10.5f)));
            get_platforms().add(new Platform(this, 3, new Vec2 (256, 13f), "CLOUD"));
            
            get_platforms().add(new Platform(this, 3, new Vec2 (266, 18f), "CLOUD"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(268f, 19.25f), 4, "BROWN", this));
            
            get_platforms().add(new Platform(this, 3, new Vec2 (276f, 5f), "TRUNK"));
            get_coins().add(new Coin(this, new Vec2(279f, 21f)));
            get_coins().add(new Coin(this, new Vec2(279f, 19f)));
            get_coins().add(new Coin(this, new Vec2(279f, 17f)));
            get_coins().add(new Coin(this, new Vec2(279f, 15f)));
            
            get_platforms().add(new Platform(this, 6, new Vec2 (292f, 4f), "TRUNK"));
            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(294f,6f), -4, "TURTLE", this));
            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(298f,6f), 4, "TURTLE", this));
            
            get_platforms().add(new Platform(this, 2, new Vec2 (312f, 5f), "TRUNK"));
            
            get_platforms().add(new Platform(this, 2, new Vec2 (322f, 8f), "TRUNK"));
                        
            get_platforms().add(new Platform(this, 1, new Vec2 (333f, 11f), "CLOUD"));
            
            
            
            
            get_fire_rods().add(new Fire_rod(this, new Vec2 (351f, 11.5f)));
            get_platforms().add(new Platform(this, 1, new Vec2 (343f, 15f), "CLOUD"));
            get_platforms().add(new Platform(this, 1, new Vec2 (351, 15f), "CLOUD"));
            
            get_fire_rods().add(new Fire_rod(this, new Vec2 (357f, 27.5f)));
            
            get_platforms().add(new Platform(this, 2, new Vec2 (359, 15f), "CLOUD"));
            
            get_platforms().add(new Platform(this, 2, new Vec2 (373, 6f), "TRUNK"));
            get_pipes().add(new Pipe(this, new Vec2(374.3f,11f) , true));
            get_platforms().add(new Platform(this, 3, new Vec2 (389, 12f), "CLOUD"));
            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(391f,14f), -4, "TURTLE", this));
            
            get_platforms().add(new Platform(this, 20, new Vec2 (399, 3f), "GROUND"));
            
            
            
            
            

        }
        
       this.start();

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
        
        for (int i = 0; i < get_fire_rods().size(); i++) {
            get_fire_rods().get(i).reset_position();
        }
        get_hero().addLife(-1);
       if (get_hero().get_life() < 1){
            getGameController().gameOver();
            //get_sound_player().stop_level_two_music();
        }
    }

    @Override
    public Vec2 start_position() {
        return PLAYER_START_POSITION;
    }
    
    @Override
    public int get_level_code (){
        return LEVEL_CODE;
    }
    
  
    
    @Override
    public  Vec2 set_finish_item_position(){
        return FINISH_BARREL_POSITION;
    }
  
    
    //--------------- GETTER and SETTER methods --------------------------------

    
    
    

   public class Castle extends StaticBody implements SensorListener {

        Level level;
        public GameController gameController;

        public Castle(Level _level, Vec2 position, GameController gameController) {
            super(_level);
            level = _level;
            this.gameController = gameController;

            BoxShape castle_shape = new BoxShape(15f, 15f);
            BoxShape sensor_shape = new BoxShape(1.2f, 2f, new Vec2(0, -12f));

            
           
            GhostlyFixture castle_fixture = new GhostlyFixture(this, castle_shape);
            Sensor sensor = new Sensor(this, sensor_shape);
            sensor.addSensorListener(this);

            addImage(new BodyImage("sprites/background/level_2/castle.png", 30f));
            setPosition(position);
        }

        @Override
        public void beginContact(SensorEvent e) {
            level.get_hero().setLinearVelocity(new Vec2(0f, 0f));
            level.get_hero().jump(60f);
            
           level.stop();
           getGameController().startLevel(3);
           
        }

        @Override
        public void endContact(SensorEvent e) {
        }

    }
    
   
}