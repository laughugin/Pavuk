import java.awt.MouseInfo;
import java.awt.Point;

public class Main {
    public static int[][] frameMatrix; // matrix of the frame
    public static int[][] dotsMatrix; // matrix of the dots
    public static int[][] spiderMatrix; // matrix of the spider
    public static int[][] legsMatrix; // matrix of the legs
    public static int[][] emptyMatrix; // matrix of the legs
    public static int numberOfPoints = 100; // num of generated points
    public static int CursorPosX = 0;
    public static int CursorPosY = 0;
    private static int spiderX = 0;
    private static int spiderY = 0;
    private static final int SPIDER_WIDTH = 15; //spider consisting of two rectangles parameters
    private static final int SPIDER_HEIGHT = 11;
    private static final int SPIDER_WIDTH2 = 9;
    private static final int SPIDER_HEIGHT2 = 15;
    public static final int POINT_COLOR = 255;
    public static final int SPIDER_COLOR = 255;
    public static final int PATH_COLOR = 255;
    public static final int SPIDER_PATH_COLOR = 50;
    
    
    public static void main(String[] args) {
        GUI gui = new GUI(); // run gui
        spiderX = gui.frameWidth/2; // first center position of the spider
        spiderY = gui.frameHeight/2; 
        emptyMatrix = Matrix.Fill(0, gui.frameWidth, gui.frameHeight);
        frameMatrix = emptyMatrix;
        dotsMatrix = emptyMatrix;
        spiderMatrix = emptyMatrix;
        legsMatrix = emptyMatrix; // fill the matrix
        gui.Frame(frameMatrix);
        dotsMatrix = new int[gui.frameWidth][gui.frameHeight];
        PointGenerator gen1 = new PointGenerator(numberOfPoints, frameMatrix, gui.frameWidth, gui.frameHeight); // generate inital points 
        dotsMatrix = gen1.generatePoints();
        int[][] dotsMatrixBuff = dotsMatrix;
        
        

        // Read cursor position
        while (true) {
            emptyMatrix = Matrix.Fill(0, gui.frameWidth, gui.frameHeight);
            frameMatrix = emptyMatrix;
            spiderMatrix = emptyMatrix;
            legsMatrix = emptyMatrix;
            dotsMatrixBuff = dotsMatrix; 

            spiderMatrix[spiderY][spiderX] = 255;
            spiderMatrix = gui.MakeGlow(spiderMatrix, 800, 800, 17, 100);

            fillRectangle(spiderMatrix, spiderX, spiderY, SPIDER_WIDTH, SPIDER_HEIGHT); // fill the spider on the frame
            fillRectangle(spiderMatrix, spiderX, spiderY, SPIDER_WIDTH2, SPIDER_HEIGHT2);
            for (int i = 0; i < gui.frameWidth; i++){
                for (int j = 0; j < gui.frameHeight; j++){
                    if(dotsMatrix[j][i] == POINT_COLOR){  // if the cell is 255 (so it's white meaning belongs to spider or point) -> fill the line
                        fillBetweenPoints(legsMatrix, spiderX, spiderY, i, j, gui);
                    }
                }

            }

            dotsMatrixBuff = gui.MakeGlow(dotsMatrixBuff, 800, 800, 9, 60);
            //legsMatrix = gui.MakeGlow(legsMatrix, 800, 800, 1, 9);

            for (int i = 0; i < gui.frameWidth; i++){
                for (int j = 0; j < gui.frameHeight; j++){
                    frameMatrix[i][j] = dotsMatrixBuff[i][j] + spiderMatrix[i][j] + legsMatrix[i][j];
                    if(frameMatrix[i][j] > 255){
                        frameMatrix[i][j] = 255;
                    }
                    else if(frameMatrix[i][j] < 0){
                        frameMatrix[i][j] = 0;
                    }
                }
            }
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
            int dx = CursorPosX - spiderX;
            int dy = CursorPosY - spiderY;
                
            // Define the speed at which the spider moves
            int speed = 10; // Adjust as needed
                
            // Calculate the total distance
            double distance = Math.sqrt(dx * dx + dy * dy);
                
            // Calculate the normalized direction vector
            double directionX = dx / distance;
            double directionY = dy / distance;
                
            // Calculate the movement increments
            int incrementX = (int) Math.round(directionX * speed);
            int incrementY = (int) Math.round(directionY * speed);
                
            // Delete the previous path with color value of 100
            //deletePreviousPath(frameMatrix);
                
            // Update the spider position with the increments
            spiderX += incrementX;
            spiderY += incrementY;


            gui.Refresh(frameMatrix);
            //deletePreviousPath(frameMatrix);
            //deletePreviousSpider(frameMatrix);
        }
    }

static void fillBetweenPoints(int[][] matrix, int startX, int startY, int endX, int endY, GUI gui) {
    int dx = Math.abs(endX - startX);
    int dy = Math.abs(endY - startY);
    int sx = startX < endX ? 1 : -1;
    int sy = startY < endY ? 1 : -1;
    int err = dx - dy;

    int spiderToPointDistance = (int) Math.sqrt(dx * dx + dy * dy); // Distance from spider to the current point

    if (spiderToPointDistance <= 100) { // Check if the distance is within the desired range
        while (startX != endX || startY != endY) {
            if (matrix[startY][startX] != POINT_COLOR) {
                matrix[startY][startX] = PATH_COLOR;
            }

            int err2 = 2 * err;

            if (err2 > -dy) {
                err -= dy;
                startX += sx;
            }
            if (err2 < dx) {
                err += dx;
                startY += sy;
            }
        }
        
        if (matrix[endY][endX] != POINT_COLOR) {
            matrix[endY][endX] = PATH_COLOR;
        }
        
        
    }
}
    
    public static void fillRectangle(int[][] matrix, int x, int y, int width, int height) {
        for (int i = y - height/2; i < y + height/2; i++) {
            for (int j = x - width/2; j < x + width/2; j++) {
                matrix[i][j] = SPIDER_COLOR;
            }
        }
    }
    
    private static void deletePreviousPath(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == PATH_COLOR) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static void deletePreviousSpider(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == SPIDER_COLOR) {
                    matrix[i][j] = 0;
                }
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