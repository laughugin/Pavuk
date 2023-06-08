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
}