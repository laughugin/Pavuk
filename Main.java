import java.awt.MouseInfo;
import java.awt.Point;

public class Main {
    public static int[][] frameMatrix; // matrix of the frame
    public static int numberOfPoints = 100; // num of generated points
    public static int CursorPosX = 0;
    public static int CursorPosY = 0;

    public static void main(String[] args) {
        GUI gui = new GUI();
        frameMatrix = Matrix.Fill(0, gui.frameWidth, gui.frameHeight);
        gui.Frame(frameMatrix);
        frameMatrix = new int[gui.frameWidth][gui.frameHeight];
        PointGenerator gen1 = new PointGenerator(numberOfPoints, frameMatrix, gui.frameWidth, gui.frameHeight);
        frameMatrix = gen1.generatePoints();
        gui.Refresh(frameMatrix);
        
        // Read cursor position
        while (true) {
            // Get the cursor position relative to the screen
            Point cursorPos = MouseInfo.getPointerInfo().getLocation();
            
            // Calculate the cursor position relative to the frame
            Point framePos = gui.frame.getLocationOnScreen();
            int cursorRelativeX = cursorPos.x - framePos.x;
            int cursorRelativeY = cursorPos.y - framePos.y;
            
            if ((cursorRelativeX >= 0 && cursorRelativeX <= gui.frameWidth) && (cursorRelativeY >= 0 && cursorRelativeX <= gui.frameHeight)){
                // Update the cursor position variables
                CursorPosX = cursorRelativeX;
                CursorPosY = cursorRelativeY;
            }
            else {
                System.out.println("Out of bounds");
            }
            // Print the cursor position
            System.out.println("Cursor:\nx = " + CursorPosX + "\ny = " + CursorPosY);
            
            try {
                Thread.sleep(100); // Delay between each cursor position read
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}