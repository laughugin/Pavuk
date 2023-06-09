import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;

public class ImgPanel extends JPanel{

    private BufferedImage image;
    int w;
    int h;

    public ImgPanel(BufferedImage img, int width, int height) {
                       
        image = img;
        w = width;
        h = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, w, h, this); // see javadoc for more info on the parameters            
    }

    public void changeImage(BufferedImage img){
                     
        image = img;
        paintComponent(getGraphics());
    }

}
