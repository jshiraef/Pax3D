package javagame;
  
public class Collision
{
    public static boolean rectToRect(float rect1x, float rect1y, float width1, float height1,
            float rect2x, float rect2y, float width2, float height2)
    {
        return rect1x <= rect2x + width2&&rect2x <= rect1x + width1&&
                rect1y <= rect2y + height2&&rect2y <= rect1y + height1;
    }
} 