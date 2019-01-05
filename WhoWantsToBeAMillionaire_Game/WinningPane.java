//imports
import java.util.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.*;
public class WinningPane extends JFrame
{
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    public static BufferedImage readX()
    {
        //load winning screen image
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File("Winning Screen.png"));
        }
        catch (IOException e)
        {
        }
        return img;
    }

    public WinningPane()
    {
        //load image
        ImageIcon x = new ImageIcon(readX());
        //new JButton
        JButton thumb;
        JPanel testerino = new JPanel ();
        //set up GridBagLayout and GridBagConstraints
        testerino.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill)
        {
            c.fill = GridBagConstraints.VERTICAL;
        }
        //put image on button
        thumb = new JButton(x);
        thumb.setBorder(null);//make button look like JLabel
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        testerino.add (thumb);
        thumb.addActionListener(new BtnListener());
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        testerino.add(new JLabel("CLICK ON IMAGE TO RETURN TO MENU"),c);
        setContentPane (testerino);
        pack();
        setTitle ("You Won!");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo (null);
        setVisible(true);
    }
    //main
    public static void main (String[] args)
    {
        //set up window
        WinningPane window = new WinningPane ();
        window.setVisible(true);
    }
    //listener for when they press the button (which looks like an image)
    class BtnListener implements ActionListener 
    {
        public void actionPerformed (ActionEvent e)
        {
            //closes the pane and opens the menu
            Menu.main(null);
            setVisible (false);
        }
    }
}
