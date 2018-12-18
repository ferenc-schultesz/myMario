package uk.mario.control;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * A special JPanel to create pause menu
 */
public class PauseMenu extends JPanel implements ActionListener{
    
    GameController gameController;
    BoxLayout boxlayout;
    JButton saveGameButton;
    JButton loadGameButton;
    JButton mainMenuButton;
    JButton exitGameButton;
    
    ImageIcon saveGameIcon = new ImageIcon("sprites/foreground/menu/savegame.png");
    ImageIcon loadGameIcon = new ImageIcon("sprites/foreground/menu/loadgame.png");
    ImageIcon mainMenuIcon = new ImageIcon("sprites/foreground/menu/mainmenu.png");
    ImageIcon exitIcon = new ImageIcon("sprites/foreground/menu/exitmario.png");
    
    
    /** 
     * creates a pause menu
     * @param gameController the controller class for the whole game
     */
    
    public PauseMenu(GameController gameController){
        
        this.gameController = gameController;
        setPreferredSize(new Dimension(300,400));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
       
        
        saveGameButton = new JButton();
        saveGameButton.setBackground(Color.cyan);
        saveGameButton.setAlignmentX(CENTER_ALIGNMENT);
        saveGameButton.addActionListener(this);
        saveGameButton.setIcon(saveGameIcon);
        
        
        loadGameButton = new JButton ();
        loadGameButton.setBackground(Color.cyan);
        loadGameButton.setAlignmentX(CENTER_ALIGNMENT);
        loadGameButton.addActionListener(this);
        loadGameButton.setIcon(loadGameIcon);
        
        mainMenuButton = new JButton();
        mainMenuButton.setBackground(Color.cyan);
        mainMenuButton.setAlignmentX(CENTER_ALIGNMENT);
        mainMenuButton.addActionListener(this);
        mainMenuButton.setIcon(mainMenuIcon);
                
        exitGameButton = new JButton();
        exitGameButton.setBackground(Color.cyan);
        exitGameButton.setAlignmentX(CENTER_ALIGNMENT);
        exitGameButton.addActionListener(this);
        exitGameButton.setIcon(exitIcon);
        
        
        
        add(Box.createRigidArea(new Dimension(0,150)));
        
        add(saveGameButton);
        add(loadGameButton);
        add(mainMenuButton);
        add(exitGameButton);
        
    }
    
    
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveGameButton){
            //System.out.println("Save Game Button pressed");
            gameController.saveGame();
        }
        if (e.getSource() == loadGameButton){
            //System.out.println("Load Button pressed");
            gameController.removeMarioView();
            gameController.addLoadGameMenu();
        }
        if (e.getSource() == mainMenuButton){
            //System.out.println("Main Menu Button pressed");  
            gameController.removeMarioView();
            gameController.addMainMenu();
            
        }
        if (e.getSource() == exitGameButton){
            System.exit(0);
            
        }
    }
}
