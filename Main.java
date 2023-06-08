public class Main {
    public static int[][] frameMatrix; // matrix of the frame
    public static int numberOfPoints = 50; // num of generated points

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.Frame();
        frameMatrix = new int[gui.frameWidth][gui.frameHeight];
        pointGenerator gen1 = new pointGenerator(numberOfPoints, frameMatrix);
    }
}