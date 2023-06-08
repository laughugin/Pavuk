public class Main {
    public static int[][] frameMatrix; // matrix of the frame
    public static int numberOfPoints = 50; // num of generated points

    public static void main(String[] args) {
        GUI gui = new GUI();
        frameMatrix = Matrix.Fill(0, 800, 800);
        gui.Frame(frameMatrix);
        frameMatrix = new int[gui.frameWidth][gui.frameHeight];
        PointGenerator gen1 = new PointGenerator(numberOfPoints, frameMatrix, gui.frameWidth, gui.frameHeight);
        frameMatrix = gen1.generatePoints();
        gui.Refresh(frameMatrix);
    }
}