import java.lang.Math;

public class Leg {
    int X = 400;
    int Y = 400;
    int speedX = 0;
    int speedY = 0;
    int targetX = 0;
    int targetY = 0;
    Boolean free = true;

    public void Move() {
        if ((targetX - X) <= speedX + 5){
            X = targetX;
        }
        else{
            X = X + speedX;
        }
        if ((targetY - Y) <= speedY + 5){
            Y = targetY;
        }
        else{
            Y = Y + speedY;
        }
    }

    public void SetTarget(int TargetX,int TargetY) {
        targetX = TargetX;
        targetY = TargetY;
        speedX = (targetX - X)/3;
        speedY = (targetY - Y)/3;
    }
}
