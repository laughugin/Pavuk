import java.awt.Color;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class GUI {
    public static int  frameHeight = 800; //frame setup
    public static int  frameWidth = 800;
    

    public static void Frame(int[][] Scr) {
        JFrame frame = new JFrame("Frame"); // main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameHeight, frameWidth);
        frame.getContentPane().setBackground(Color.darkGray);;
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

        ImgPanel Screen = new ImgPanel(MatrixToImage(Scr, frameWidth, frameHeight), frameWidth, frameHeight);
        Screen.setBounds(frameHeight, frameHeight, 0, 0);
        frame.add(Screen);
    }

    public static BufferedImage MatrixToImage(int[][] Matrix,int size_x,int size_y){
        try {
            BufferedImage image = new BufferedImage(size_x, size_y, BufferedImage.TYPE_INT_RGB);
            for(int i=0; i< size_x; i++) {
                for(int j = 0; j < size_y; j++) {
                    int a = Matrix[i][j];
                    Color newColor = new Color(a,a,a);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            return image;
        }
        catch(Exception e) {
            return null;
        }
    } 
}
