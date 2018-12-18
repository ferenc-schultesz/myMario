package uk.mario.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * A special JPanel to create MAinMenu
 */
public class MainMenu extends JPanel implements ActionListener{
    
    GameController gameController;
    BoxLayout boxlayout;
    JButton startButton;
    JButton loadButton;
    JButton exitButton;
    Image menuBackgroundImage;
    Image gameOverMenuBackgroundImage = new ImageIcon("sprites/background/gameover.png").getImage() ;
    Image newGameMenuBackgroundImage = new ImageIcon("sprites/background/mainmenu.png").getImage() ;
    ImageIcon exitIcon = new ImageIcon("sprites/foreground/menu/exitmario.png");
    ImageIcon newgameIcon = new ImageIcon("sprites/foreground/menu/startgame.png");
    ImageIcon loadgameIcon = new ImageIcon("sprites/foreground/menu/loadgame.png");
    
    
    /** 
     * creates a game menu
     * @param gameController the controller class of the whole game
     */
    
    public MainMenu(GameController gameController){
        setFocusable(true);
        requestFocusInWindow();
        this.gameController = gameController;
        setPreferredSize(new Dimension(1300,700));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        startButton = new JButton();
        startButton.setBackground(Color.cyan);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.addActionListener(this);
        startButton.setIcon(newgameIcon);
        
        
        loadButton = new JButton ();
        loadButton.setBackground(Color.cyan);
        loadButton.setAlignmentX(CENTER_ALIGNMENT);
        loadButton.addActionListener(this);
        loadButton.setIcon(loadgameIcon);
        
        exitButton = new JButton();
        exitButton.setBackground(Color.cyan);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(this);
        exitButton.setIcon(exitIcon);
        
        add(Box.createRigidArea(new Dimension(0,250)));
        add(startButton);
        add(loadButton);
        add(exitButton);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(menuBackgroundImage, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            //System.out.println("Start Button pressed");
            gameController.removeMainMenu();
            gameController.addNewPlayerMenu();
        }
        if (e.getSource() == loadButton){
            //System.out.println("Load Button pressed");
            gameController.getGameWindow().remove(this);
            gameController.addLoadGameMenu();
        }
        if (e.getSource() == exitButton){
            //System.out.println("exit game Button pressed");
            System.exit(0);
        }
    }
    
    /** 
     * changes the background to the game over screen background
     */
    public void setBackgroundGameOver(){
        menuBackgroundImage = gameOverMenuBackgroundImage;
    }
    /** 
     *  changes the background for the new game background
     */
    public void setBackgroundNewGame() {
        menuBackgroundImage = newGameMenuBackgroundImage;
    }
}
