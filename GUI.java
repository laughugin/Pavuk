import java.awt.Color;

import javax.swing.*;
import java.lang.Math;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class GUI {
    public static int  frameHeight = 800; //frame setup
    public static int  frameWidth = 800;
    static ImgPanel Screen;
    static JFrame frame;

    public static void Frame(int[][] Scr) {
        frame = new JFrame("Frame"); // main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameHeight, frameWidth);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.darkGray);;
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

        Screen = new ImgPanel(MatrixToImage(Scr, frameWidth, frameHeight), frameWidth, frameHeight);
        Screen.setBounds(0, 0, frameWidth, frameHeight);
        Screen.setBackground(Color.BLACK);
        frame.add(Screen);
    }

    public static void Refresh(int[][] Scr){
        Screen.changeImage(MatrixToImage(Scr, frameWidth, frameHeight));
    }

    public static BufferedImage MatrixToImage(int[][] Matrix,int size_x,int size_y){
        
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

    public static int[][] MakeGlow(int[][] Matrix,int size_x,int size_y, int coefficient, int deepness) {
        int[][] MatrixBuff = new int[size_x][size_y];
        double r, p;
        for(int i=0; i< size_x; i++) {
            for(int j = 0; j < size_y; j++) {
                int a = Matrix[i][j];
                if(a == 255){
                    for(int in= i - deepness; in < i + deepness; in++) {
                        for(int jn = j - deepness; jn < j + deepness; jn++) {
                            r = Math.sqrt(Math.pow(Math.abs(in - i) , 2) + Math.pow(Math.abs(jn - j), 2));
                            p = -(r/coefficient) + 1;
                            try {
                                MatrixBuff[in][jn] = (int) (MatrixBuff[in][jn] + Math.round(94.096*Math.pow(Math.E, p)));
                            } catch (Exception e) {
                                // TODO: handle exception
                            } 
                        }
                    }
                }
            }
        }
        for(int i=0; i< size_x; i++) {
            for(int j = 0; j < size_y; j++) {
                if(MatrixBuff[i][j] > 255){
                    MatrixBuff[i][j] = 255;
                }
                else if(MatrixBuff[i][j] < 0){
                    MatrixBuff[i][j] = 0;
                }
            }
        }
        return MatrixBuff;
    }
}
