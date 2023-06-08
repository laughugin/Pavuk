import java.awt.MouseInfo;
import java.awt.Point;

public class Main {
    public static int[][] frameMatrix; // matrix of the frame
    public static int numberOfPoints = 1000; // num of generated points
    public static int CursorPosX = 0;
    public static int CursorPosY = 0;
    public static void main(String[] args) {
        GUI gui = new GUI();
        frameMatrix = Matrix.Fill(0, 800, 800);
        gui.Frame(frameMatrix);
        frameMatrix = new int[gui.frameWidth][gui.frameHeight];
        PointGenerator gen1 = new PointGenerator(numberOfPoints, frameMatrix, gui.frameWidth, gui.frameHeight);
        frameMatrix = gen1.generatePoints();
        gui.Refresh(frameMatrix);
        while (true) {
            Point cursorPos = MouseInfo.getPointerInfo().getLocation();
            CursorPosX = (int) cursorPos.getX();
            CursorPosY = (int) cursorPos.getY();

            //print it
             System.out.println("Cursor:\nx = " + CursorPosX + "\ny = " + CursorPosY);

            try {
                Thread.sleep(100); // delay between each cursor position read
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}