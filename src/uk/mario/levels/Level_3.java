package uk.mario.levels;

import uk.mario.levelelements.Platform;
import city.cs.engine.*;
import uk.mario.enemies.Brown_enemy;
import uk.mario.enemies.Cannon;
import uk.mario.enemies.Fire_rod;
import uk.mario.enemies.Static_enemy;
import uk.mario.enemies.Turtle_fly_enemy;
import uk.mario.levelelements.Coin;
import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;
import uk.mario.control.GameController;

/**
 *
 * The third level of the game
 */
public final class Level_3 extends Level {

    private Door door;

    private static final Vec2 PLAYER_START_POSITION = new Vec2(3f, 2f);
    private final Vec2 FINISH_BARREL_POSITION = new Vec2(320f, 5f);

    private BodyImage groundimage;
    private final int LEVEL_CODE = 3;

    //variables for testinf purpose
    private Turtle_fly_enemy turtle;

  

    // ------------------------- LEVEL METHODS -------------------------------
    /**
     * The setup method of the level we create every elements here such as, dynamic and static enemies, plaforms
     * pickups, static elements etc
     * @param gameController the controller class for the game
     */
    @Override
    public void setup_level(GameController gameController) {

        super.setup_level(gameController);

        door = new Door(this, FINISH_BARREL_POSITION, gameController);

        {
            get_fire_rods().add(new Fire_rod(this, new Vec2(120f, 0.75f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2(135f, 0.75f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2(150f, 0.75f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2(125f, 24f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2(145f, 24f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2(165f, 24f)));

            get_fire_rods().add(new Fire_rod(this, new Vec2(194f, 9f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2(217f, 9f)));
            get_fire_rods().add(new Fire_rod(this, new Vec2(227.5f, 9f)));
        }

        {

            // make the ground  
            groundimage = new BodyImage("sprites/background/level_3/ground_sprite.png", 4f);

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

            get_platforms().add(new Platform(this, 1, new Vec2(8f, 1f), "GROUND"));
            get_static_enemies().add(new Static_enemy(20, new Vec2(8f, 1f), "LAVA", this));
            get_platforms().add(new Platform(this, 1, new Vec2(50f, 1f), "GROUND"));

            get_platforms().add(new Platform(this, 2, new Vec2(14f, 6f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(14f, 12f)));
            get_coins().add(new Coin(this, new Vec2(16f, 12f)));
            get_platforms().add(new Platform(this, 2, new Vec2(24f, 6f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(24f, 12f)));
            get_coins().add(new Coin(this, new Vec2(26f, 12f)));
            get_platforms().add(new Platform(this, 2, new Vec2(34f, 6f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(34f, 12f)));
            get_coins().add(new Coin(this, new Vec2(36f, 12f)));
            get_platforms().add(new Platform(this, 2, new Vec2(44f, 6f), "GROUND"));

            get_cannons().add(new Cannon(this, new Vec2(46f, 9f)));

            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(56f, 2f), 7f, "TURTLE", this));
            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(63f, 2f), -7f, "TURTLE", this));

            get_platforms().add(new Platform(this, 5, new Vec2(65f, 11.7f), "GROUND"));
            get_platforms().add(new Platform(this, 4, new Vec2(65f, 14f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(65f, 22f)));
            get_coins().add(new Coin(this, new Vec2(65f, 24f)));
            get_coins().add(new Coin(this, new Vec2(67f, 22f)));
            get_coins().add(new Coin(this, new Vec2(67f, 24f)));
            get_coins().add(new Coin(this, new Vec2(69f, 22f)));
            get_coins().add(new Coin(this, new Vec2(69f, 24f)));

            get_platforms().add(new Platform(this, 2, new Vec2(80.5f, 6f), "GROUND"));

            get_platforms().add(new Platform(this, 5, new Vec2(90f, 12f), "GROUND"));
            get_static_enemies().add(new Static_enemy(3, new Vec2(88f, 14f), "SPIKE", this));
            get_cannons().add(new Cannon(this, new Vec2(98f, 15.3f)));
            get_coins().add(new Coin(this, new Vec2(98f, 22f)));
            get_coins().add(new Coin(this, new Vec2(98f, 24f)));
            get_platforms().add(new Platform(this, 1, new Vec2(105f, 1f), "GROUND"));

            get_platforms().add(new Platform(this, 20, new Vec2(111f, 7f), "GROUND"));

            get_platforms().add(new Platform(this, 18, new Vec2(113.5f, 9.25f), "GROUND"));

            get_platforms().add(new Platform(this, 1, new Vec2(155.5f, 1f), "GROUND"));

            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(160f, 2f), 5, "TURTLE", this));

            get_cannons().add(new Cannon(this, new Vec2(156.5f, 10.5f)));
            get_cannons().add(new Cannon(this, new Vec2(156.5f, 14.5f)));

            get_platforms().add(new Platform(this, 4, new Vec2(164f, 8.5f), "GROUND"));
            get_static_enemies().add(new Static_enemy(4, new Vec2(162f, 10.25f), "SPIKE", this));

            get_platforms().add(new Platform(this, 50, new Vec2(170.5f, 1f), "GROUND"));
            get_platforms().add(new Platform(this, 48, new Vec2(173f, 3.25f), "GROUND"));

            get_platforms().add(new Platform(this, 1, new Vec2(182.75f, 5.5f), "GROUND"));
            get_static_enemies().add(new Static_enemy(43, new Vec2(183f, 5.4f), "LAVA", this));
            get_platforms().add(new Platform(this, 1, new Vec2(271.25f, 5.5f), "GROUND"));

            get_platforms().add(new Platform(this, 1, new Vec2(189f, 12f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(189f, 20f)));
            get_platforms().add(new Platform(this, 1, new Vec2(200f, 12f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(200f, 20f)));
            get_platforms().add(new Platform(this, 1, new Vec2(211f, 12f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(211f, 20f)));
            get_platforms().add(new Platform(this, 1, new Vec2(222f, 12f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(222f, 20f)));
            get_platforms().add(new Platform(this, 1, new Vec2(233f, 12f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(233f, 20f)));
            get_platforms().add(new Platform(this, 1, new Vec2(244f, 12f), "GROUND"));
            get_platforms().add(new Platform(this, 1, new Vec2(250f, 9.7f), "GROUND"));
            get_cannons().add(new Cannon(this, new Vec2(250f, 13f)));
            get_platforms().add(new Platform(this, 4, new Vec2(255f, 12f), "GROUND"));
            get_coins().add(new Coin(this, new Vec2(256f, 17f)));
            get_coins().add(new Coin(this, new Vec2(258f, 17f)));
            get_coins().add(new Coin(this, new Vec2(260f, 17f)));
            get_dynamic_enemies().add(new Brown_enemy(new Vec2(257f, 14f), 7f, "BROWN", this));
            get_dynamic_enemies().add(new Turtle_fly_enemy(new Vec2(280f, 5f), 5f, "TURTLE", this));
        }

// adding a hero controller 
        // start!
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
        if (get_hero().get_life() < 1) {
            getGameController().gameOver();
            //get_sound_player().stop_level_three_music();
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
    public class Door extends StaticBody implements SensorListener {

        Level level;
        public GameController gameController;

        public Door(Level _level, Vec2 position, GameController gameController) {
            super(_level);
            level = _level;
            this.gameController = gameController;

            BoxShape door_shape = new BoxShape(3.5f, 5f);
            BoxShape sensor_shape = new BoxShape(1.2f, 2f, new Vec2(0, -3f));

            GhostlyFixture door_fixture = new GhostlyFixture(this, door_shape);
            Sensor sensor = new Sensor(this, sensor_shape);
            sensor.addSensorListener(this);

            addImage(new BodyImage("sprites/background/level_3/door.png", 10f));
            setPosition(position);
        }

        @Override
        public void beginContact(SensorEvent e) {
            level.get_hero().setLinearVelocity(new Vec2(0f, 0f));
            level.get_hero().jump(60f);
            level.stop();
            getGameController().startLevel(4);

        }

        @Override
        public void endContact(SensorEvent e) {
        }

    }

}
