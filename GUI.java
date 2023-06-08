import java.awt.Color;

import javax.swing.*;

public class GUI {
    public static int  frameHeight = 800; //frame setup
    public static int  frameWidth = 800;
    

    public static void Frame() {
        JFrame frame = new JFrame("Frame"); // main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameHeight, frameWidth);
        frame.getContentPane().setBackground(Color.darkGray);;
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}
