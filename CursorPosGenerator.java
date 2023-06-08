import java.awt.MouseInfo;
import java.awt.Point;

public class CursorPosGenerator {
    public static void main(String[] args) {
        while (true) {
            Point cursorPos = MouseInfo.getPointerInfo().getLocation();
            int x = (int) cursorPos.getX();
            int y = (int) cursorPos.getY();

            try {
                Thread.sleep(100); // delay between each cursor position read
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}