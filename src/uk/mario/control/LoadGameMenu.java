package uk.mario.control;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * A special JPanel for the Load game menu
 */
public class LoadGameMenu extends JPanel implements ActionListener {
    
    GameController gameController;
    JPanel holdingPanel;
    BoxLayout boxlayout;
    JButton loadButton;
    JTextField nameField;
    Font font;
    Image menuBackgroundImage = new ImageIcon("sprites/background/loadgame.png").getImage();
    ImageIcon loadprofile = new ImageIcon("sprites/foreground/menu/loadprofile.png");
    
    /** 
     * creates a load game menu
     * @param gameController the controller class for the whole game
     */
    public LoadGameMenu(GameController gameController) {

        this.gameController = gameController;
        setPreferredSize(new Dimension(1300, 700));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        holdingPanel = new JPanel();
        holdingPanel.setPreferredSize(new Dimension(150, 150));
        holdingPanel.setMaximumSize(new Dimension(250, 150));  
        holdingPanel.setMinimumSize(new Dimension(150, 150));
        
        holdingPanel.setLayout(new BorderLayout());
        holdingPanel.setOpaque(false);
        
        font = new Font("testFont", Font.BOLD, 25);
        nameField = new JTextField("Enter your name.." , 15);
        nameField.setEditable(true);
        nameField.setFont(font);
        
        loadButton = new JButton();
        loadButton.setBackground(Color.cyan);
        loadButton.setAlignmentX(CENTER_ALIGNMENT);
        loadButton.addActionListener(this);
        loadButton.setIcon(loadprofile);

        add(Box.createRigidArea(new Dimension(0, 250)));
        add(holdingPanel);
        
        holdingPanel.add(nameField, BorderLayout.NORTH);
        holdingPanel.add(loadButton, BorderLayout.SOUTH);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBackgroundImage, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loadButton) {
            //System.out.println("Load Button pressed");
            gameController.removeLoadGameMenu();
            gameController.loadProfile(nameField.getText());
        }
    }
}
