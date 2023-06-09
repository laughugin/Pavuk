import java.awt.MouseInfo;
import java.awt.Point;

public class Main {
    public static int[][] frameMatrix; // matrix of the frame
    public static int numberOfPoints = 100; // num of generated points
    public static int CursorPosX = 0;
    public static int CursorPosY = 0;
    private static int spiderX = 0;
    private static int spiderY = 0;
    private static final int SPIDER_WIDTH = 15;
    private static final int SPIDER_HEIGHT = 11;
    private static final int SPIDER_WIDTH2 = 9;
    private static final int SPIDER_HEIGHT2 = 15;
    
    public static void main(String[] args) {
        GUI gui = new GUI();


        spiderX = gui.frameWidth/2;
        spiderY = gui.frameHeight/2;
        frameMatrix = Matrix.Fill(0, gui.frameWidth, gui.frameHeight);
        gui.Frame(frameMatrix);
        frameMatrix = new int[gui.frameWidth][gui.frameHeight];
        PointGenerator gen1 = new PointGenerator(numberOfPoints, frameMatrix, gui.frameWidth, gui.frameHeight);
        frameMatrix = gen1.generatePoints();
        
        

        // Read cursor position
        while (true) {
            fillRectangle(frameMatrix, spiderX, spiderY, SPIDER_WIDTH, SPIDER_HEIGHT);
            fillRectangle(frameMatrix, spiderX, spiderY, SPIDER_WIDTH2, SPIDER_HEIGHT2);
            // Get the cursor position relative to the screen
            Point cursorPos = MouseInfo.getPointerInfo().getLocation();

            // Calculate the cursor position relative to the frame
            Point framePos = gui.frame.getLocationOnScreen();
            int cursorRelativeX = cursorPos.x - framePos.x;
            int cursorRelativeY = cursorPos.y - framePos.y;

            if ((cursorRelativeX >= 0 && cursorRelativeX <= gui.frameWidth) && (cursorRelativeY >= 0 && cursorRelativeY <= gui.frameHeight)) {
                // Update the cursor position variables
                CursorPosX = cursorRelativeX;
                CursorPosY = cursorRelativeY;
            } else {
                System.out.println("Out of bounds");
            }

            clearConsole();

            // Print the cursor position
            
            System.out.println("Cursor:\nx = " + CursorPosX + "\ny = " + CursorPosY);

            try {
                Thread.sleep(100); // Delay between each cursor position read
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gui.Refresh(frameMatrix);
        }
    }

    public static void fillRectangle(int[][] matrix, int x, int y, int width, int height) {
        for (int i = y - height/2; i < y + height/2; i++) {
            for (int j = x - width/2; j < x + width/2; j++) {
                matrix[i][j] = 255;
            }
        }
    }


    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name").toLowerCase();

            // Command to clear console based on the operating system
            String command = "";
            if (operatingSystem.contains("windows")) {
                command = "cmd /c cls";
            } else if (operatingSystem.contains("linux") || operatingSystem.contains("mac")) {
                command = "clear";
            }

            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}