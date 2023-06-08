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
    

    public static void Frame() {
        JFrame frame = new JFrame("Frame"); // main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameHeight, frameWidth);
        frame.getContentPane().setBackground(Color.darkGray);;
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    public static BufferedImage MatrixToImage(int [][] Matrix,int size_x,int size_y){
        try {
            BufferedImage image;
            for(int i=0; i< size_x; i++) {
                for(int j = 0; j < size_y; j++) {
                    int a = Matrix[i][j];
                    Color newColor = new Color(a,a,a);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File("GrayScale.jpg");
            ImageIO.write(image, "jpg", output);
            return image;
        }
        catch(Exception e) {
            return null;
        }
    } 
}
