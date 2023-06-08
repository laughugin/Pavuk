import java.util.Random;
public class PointGenerator {
    private int numberOfPoints;
    private int[][] frameMatrix;
    private int frameWidth;
    private int frameHeight;
    
    public PointGenerator(int numberOfPoints, int[][] frameMatrix, int frameWidth, int frameHeight) {
        this.numberOfPoints = numberOfPoints;
        this.frameMatrix = frameMatrix;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    public int[][] generatePoints() {
        Random random = new Random();

        int numberToFill = 1;

        for (int i = 0; i < numberOfPoints; i++) {
            int x = random.nextInt(frameWidth);
            int y = random.nextInt(frameHeight);

            frameMatrix[x][y] = numberToFill;
            numberToFill++;
        }
        return frameMatrix;
    }


}