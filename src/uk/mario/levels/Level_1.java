package uk.mario.levels;

import uk.mario.enemies.Static_enemy;
import uk.mario.enemies.Brown_enemy;
import uk.mario.levelelements.Pipe;
import uk.mario.levelelements.Platform;
import uk.mario.levelelements.Coin;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import uk.mario.Hero;
import uk.mario.SoundPlayer;
import uk.mario.control.GameController;

/**
 *
 * The first level of the game
 */
public final class Level_1 extends Level {

    private static final Vec2 PLAYER_START_POSITION = new Vec2(2f, 2f);
    private final Vec2 FINISH_BARREL_POSITION = new Vec2(338f, 9.5f);

    private BodyImage groundimage;
    private final int LEVEL_CODE = 1;

    private Finish_barrel finish_barrel;

    /**
     * The setup method of the level we create every elements here such as, dynamic and static enemies, plaforms
     * pickups, static elements etc
     * @param gameController the controller class for the game
     */
 
    @Override
    public void setup_level(GameController gameController) {

        super.setup_level(gameController);

        finish_barrel = new Finish_barrel(this, FINISH_BARREL_POSITION, gameController);

        {

            // make the ground  
            groundimage = new BodyImage("sprites/background/level_1/ground_sprite.png", 4f);

            Shape shape = new BoxShape(250, 2f);
            Body ground = new StaticBody(this, shape);
            ground.setPosition(new Vec2(170, -2f));
            ground.addImage(groundimage, new Vec2(-248.5f, 0f));
            ground.setClipped(false);
            for (float i = -248.5f; i < 250f; i = i + 4f) {
                ground.addImage(groundimage, new Vec2(i + 3.5f, 0f));
            }

            Shape wall_shape = new BoxShape(1, 20);
            StaticBody wall = new StaticBody(this, wall_shape);
            wall.setPosition(new Vec2(-3f, 20f));
            wall.addImage(new BodyImage("sprites/platforms/invisible_wall.png"));
            StaticBody wall2 = new StaticBody(this, wall_shape);
            wall2.setPosition(new Vec2(346f, 20f));
            wall2.addImage(new BodyImage("sprites/platforms/invisible_wall.png"));
        }
        {

            // Creating platforms   Constructor: this level, platform's length, position as a vector but the firt block's position from left
            // and the type: GOLDEN/CLOUD/BRICK for the different kind of platforms
            get_platforms().add(new Platform(this, 3, new Vec2(15f, 5f), "BRICK"));

            get_platforms().add(new Platform(this, 2, new Vec2(24f, 11f), "BRICK"));

            get_platforms().add(new Platform(this, 1, new Vec2(33f, 17f), "BRICK"));
            get_coins().add(new Coin(this, new Vec2(33f, 23f)));

            get_platforms().add(new Platform(this, 1, new Vec2(42f, 10f), "BRICK"));
            get_platforms().add(new Platform(this, 1, new Vec2(48f, 11f), "BRICK"));
            get_platforms().add(new Platform(this, 1, new Vec2(54f, 16f), "BRICK"));

            get_platforms().add(new Platform(this, 1, new Vec2(60f, 22f), "BRICK"));
            get_coins().add(new Coin(this, new Vec2(60f, 28f)));
            get_coins().add(new Coin(this, new Vec2(60f, 30f)));
            get_coins().add(new Coin(this, new Vec2(60f, 32f)));

            get_platforms().add(new Platform(this, 4, new Vec2(45f, 1.25f), "GROUND"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(70f, 0), -5f, "BROWN", this));

            get_platforms().add(new Platform(this, 1, new Vec2(77.5f, 1f), "GROUND"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(84f, 0), 5f, "BROWN", this));

            get_platforms().add(new Platform(this, 3, new Vec2(65f, 6f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(66f, 7.25f), 5f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(66f, 3f)));
            get_coins().add(new Coin(this, new Vec2(72f, 3f)));

            get_platforms().add(new Platform(this, 1, new Vec2(75f, 6f), "BRICK"));

            get_platforms().add(new Platform(this, 2, new Vec2(85f, 6f), "BRICK"));
            get_coins().add(new Coin(this, new Vec2(86f, 3f)));
            //-------------------------------------------------------------------------
            get_platforms().add(new Platform(this, 2, new Vec2(110f, 6f), "BRICK"));
            get_platforms().add(new Platform(this, 2, new Vec2(116f, 11f), "BRICK"));
            get_platforms().add(new Platform(this, 1, new Vec2(128f, 11f), "BRICK"));

            get_platforms().add(new Platform(this, 1, new Vec2(136f, 17f), "BRICK"));
            get_coins().add(new Coin(this, new Vec2(136f, 21f)));
            get_coins().add(new Coin(this, new Vec2(136f, 23f)));
            get_coins().add(new Coin(this, new Vec2(136f, 25f)));

            get_platforms().add(new Platform(this, 7, new Vec2(134f, 5f), "BRICK"));
            get_platforms().add(new Platform(this, 4, new Vec2(153f, 1.25f), "GROUND"));

            get_platforms().add(new Platform(this, 5, new Vec2(165f, 5f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(167f, 6.5f), 5f, "BROWN", this));

            get_platforms().add(new Platform(this, 1, new Vec2(177.5f, 1f), "GROUND"));

            get_platforms().add(new Platform(this, 4, new Vec2(182f, 8f), "BRICK"));

            get_platforms().add(new Platform(this, 3, new Vec2(194f, 15f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(196f, 16.5f), 7f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(196, 20f)));
            get_coins().add(new Coin(this, new Vec2(198, 20f)));

            get_platforms().add(new Platform(this, 4, new Vec2(182f, 21f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(184f, 22.5f), 4f, "BROWN", this));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(185f, 9.5f), 5f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(183, 26f)));
            get_coins().add(new Coin(this, new Vec2(185, 26f)));
            get_coins().add(new Coin(this, new Vec2(187, 26f)));

            get_platforms().add(new Platform(this, 2, new Vec2(210f, 6f), "BRICK"));
            //-----------------------------------------------------------------------
            get_platforms().add(new Platform(this, 2, new Vec2(232f, 6f), "BRICK"));
            get_platforms().add(new Platform(this, 2, new Vec2(238f, 11f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(239.25f, 12.5f), 4f, "BROWN", this));

            get_platforms().add(new Platform(this, 3, new Vec2(250f, 11f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(251.25f, 12.5f), 6f, "BROWN", this));

            get_platforms().add(new Platform(this, 5, new Vec2(262f, 5f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(263.25f, 6.5f), 6f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(272, 9.5f)));

            get_platforms().add(new Platform(this, 4, new Vec2(272f, 12f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(274f, 13.5f), 5f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(276, 17f)));
            get_coins().add(new Coin(this, new Vec2(273, 17f)));
            get_coins().add(new Coin(this, new Vec2(279, 17f)));

            get_platforms().add(new Platform(this, 5, new Vec2(280f, 5f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(282f, 6.5f), 7f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(280, 9.5f)));

            get_platforms().add(new Platform(this, 4, new Vec2(300f, 8f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(302f, 9.5f), 5f, "BROWN", this));

            get_platforms().add(new Platform(this, 3, new Vec2(310f, 15f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(312f, 16.5f), 5f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(315f, 20f)));

            get_platforms().add(new Platform(this, 4, new Vec2(300f, 21f), "BRICK"));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(302f, 22.5f), 7f, "BROWN", this));
            get_coins().add(new Coin(this, new Vec2(302f, 26f)));
            get_coins().add(new Coin(this, new Vec2(304f, 26f)));
            get_coins().add(new Coin(this, new Vec2(306f, 26f)));

            get_platforms().add(new Platform(this, 6, new Vec2(332f, 6f), "BRICK"));

        }
        // Creating pipes   - if boolean parameter is true it will have a flower inside Vec2 is the position of the pipe
        {
            get_pipes().add(new Pipe(this, new Vec2(32f, 3.4f), true));
            get_pipes().add(new Pipe(this, new Vec2(100f, 3.4f), true));
            get_pipes().add(new Pipe(this, new Vec2(222f, 3.4f), true));

        }
        { // CREATING Static Enemies Constructor: Level, position, type, level
            get_static_enemies().add(new Static_enemy(23, new Vec2(102f, 1f), "BLACK_FLOWER", this));
            get_static_enemies().add(new Static_enemy(6, new Vec2(163f, 1f), "BLACK_FLOWER", this));

            get_static_enemies().add(new Static_enemy(60, new Vec2(223.5f, 1f), "BLACK_FLOWER", this));
        }

        this.start();

    }

    //----------------- Overriding ABSTRACT METHODS-----------------------------
    //overriding the abstract game_over() to deal with what happens if we die
    @Override
    public void hero_dies() {
        
        SoundPlayer.playHeroDies();
        stop();

        get_hero().setPosition(PLAYER_START_POSITION);
        start();

        // setting every dynamic enemies positions to its original position
        for (int i = 0; i < get_dynamic_enemies().size(); i++) {
            get_dynamic_enemies().get(i).move_to_startposition();
        }
        Hero.addLife(-1);
        
        if (get_hero().get_life() < 1){
            getGameController().gameOver();
            //get_sound_player().stop_level_one_music();
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

    
    /**
     * Inner class for the barrel that moves us to the next level
     */
    
    
    public class Finish_barrel extends StaticBody implements SensorListener {

        Level level;
        public GameController gameController;

        public Finish_barrel(Level _level, Vec2 position, GameController gameController) {
            super(_level);
            level = _level;
            this.gameController = gameController;

            BoxShape left_side_shape = new BoxShape(0.1f, 2.2f, new Vec2(-1.5f, 0f));
            BoxShape right_side_shape = new BoxShape(0.1f, 2.2f, new Vec2(1.5f, 0f));
            BoxShape middle_shape = new BoxShape(1.3f, 2.2f);
            BoxShape sensor_shape = new BoxShape(1.2f, 0.1f, new Vec2(0, -1.2f));

            SolidFixture left_fixture = new SolidFixture(this, left_side_shape);
            SolidFixture right_fixture = new SolidFixture(this, right_side_shape);
            left_fixture.setFriction(0f);
            right_fixture.setFriction(0f);
            GhostlyFixture middle_fixture = new GhostlyFixture(this, middle_shape);
            Sensor sensor = new Sensor(this, sensor_shape);
            sensor.addSensorListener(this);

            addImage(new BodyImage("sprites/finish_barrel.png", 5f));
            setPosition(position);
        }

        @Override
        public void beginContact(SensorEvent e) {
            level.get_hero().setLinearVelocity(new Vec2(0f, 0f));
            level.get_hero().jump(60f);
            level.stop();
            gameController.startLevel(2);
            
        }

        @Override
        public void endContact(SensorEvent e) {
        }

    }

}
