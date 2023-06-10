import java.lang.Math;

public class Leg {
    int X = 400;
    int Y = 400;
    int speedX = 0;
    int speedY = 0;
    int targetX = 0;
    int targetY = 0;
    Boolean free = true;
    Boolean locked = false;

    public void Move() {
        if (Math.abs(targetX - X) <= Math.abs(speedX) + 5){
            X = targetX;
            speedX = 0;
            locked = true;
        }
        else{
            X = X + speedX;
            locked = false;
        }
        if (Math.abs(targetY - Y) <= Math.abs(speedY) + 5){
            Y = targetY;
            speedY = 0;
            locked = true;
        }
        else{
            Y = Y + speedY;
            locked = false;
        }
    }

    public void SetTarget(int TargetX,int TargetY) {
        targetX = TargetX;
        targetY = TargetY;
        speedX = (targetX - X)/7;
        speedY = (targetY - Y)/7;
    }
}
