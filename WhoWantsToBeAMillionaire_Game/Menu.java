import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  //needed for A/Users/namrazahid/Downloads/TemperatureConverter2 (3).javactionListener
//image
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
//to draw shapes
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
class Menu extends JFrame 
{
    public Menu ()
    {
        //set up GridBagLayout and GridBagConstraints
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //create/initialize components
        JButton play = new JButton ("Play");
        play.setFont(new Font("Arial", Font.BOLD, 25));
        c.gridx = 1;
        c.gridy = 3;
        c.ipady = 10; 
        c.ipadx = 150;
        play.addActionListener (new startListener ());
        add(play, c);
        JButton howToPlay = new JButton ("How To Play");  
        howToPlay.setFont(new Font("Arial", Font.BOLD, 25));
        c.gridx = 1;
        c.gridy = 4;
        c.ipady = 10; 
        c.ipadx = 55;
        add(howToPlay, c);
        howToPlay.addActionListener (new howToPlayScreen ());
        //set up this window's attributes
        getContentPane().setBackground(new Color (50, 10, 130));
        setFont(new Font("Courier New", Font.BOLD, 12));
        //image
        try
        {   
            //BufferedImage ==> basically picture set up
            BufferedImage myPicture = ImageIO.read(new File("Millionaire.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            BufferedImage myPicture2 = ImageIO.read(new File("coins.png"));
            c.gridx = 0;
            c.gridy = 7;
            JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
            add(picLabel2);
            c.weighty = 0.1;
            c.gridx = 1;
            c.gridy = 3;
            add(picLabel);    
            BufferedImage myPicture3 = ImageIO.read(new File("coins.png"));
            c.gridx = 2;
            c.gridy = 1;
            JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
            add(picLabel3);
        }
        catch (IOException ex) 
        {
            System.out.println("not found");
        }
    }
    //pane that pops up when they press the 'how to play' button
    class howToPlayScreen implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            JFrame frame = new JFrame();
            frame.setLayout( new FlowLayout() ); 
            JPanel panel = new JPanel();
            setLayout(new GridBagLayout());
            GridBagConstraints d = new GridBagConstraints();
            //loads image that has the rules on it
            try
            {   
                BufferedImage myPicture = ImageIO.read(new File("ScreenShot.png"));
                JLabel picLabel4 = new JLabel(new ImageIcon(myPicture));
                frame.add(picLabel4);
            }
            catch (IOException ex) 
            {
                System.out.println("not found");
            }
            //set up GridBagConstraints, pane size, etc.
            frame.add(panel, new GridBagConstraints());
            frame.setSize(1200,700);
            frame.getContentPane().setBackground(new Color (50, 10, 130));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setVisible(true);
        }
    }
    //listener for when they press the 'play' button (starts the main millionaire game)
    class startListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            setVisible(false);
            try
            {
                Millionaire.main(null);
            }
            catch (Exception ex) 
            {
                System.out.println("not found");
            }
        }
    }
    //initialize pane, etc.
    public static void main (String[] args)
    {
        Menu menuFrame = new Menu ();
        menuFrame.setSize(1200,700); // sets the size of frame
        menuFrame.setVisible( true ); // sets the frame visible
        menuFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // closes when crossed out  
    }
}

