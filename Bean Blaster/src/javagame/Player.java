package javagame;
  
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
  
public class Player extends Entity
{
    private Vector2f rotation;
    private final int SPEED = 4;
    public Player(float x, float y, float z)
    {
        super(0.6f, 0.6f);
        pos = new Vector3f(x, y, z);
        rotation = new Vector2f(0, 180);
    }
    public void update(float delta)
    {
        float dx = Mouse.getX() - Display.getWidth()/2;
        float dy = Mouse.getY() - Display.getHeight()/2;
          
        rotation.y += dx / 15f;
        rotation.x -= dy / 15f;
          
        if(rotation.x > 90) rotation.x = 90;
        if(rotation.x < -90) rotation.x = -90;
          
        Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
          
        oldpos.x = pos.x;
        oldpos.y = pos.y;
        oldpos.z = pos.z;
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            pos.x += Math.cos(Math.toRadians(rotation.y - 90)) * SPEED * delta;
            pos.z += Math.sin(Math.toRadians(rotation.y - 90)) * SPEED * delta;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            pos.x -= Math.cos(Math.toRadians(rotation.y - 90)) * SPEED * delta;
            pos.z -= Math.sin(Math.toRadians(rotation.y - 90)) * SPEED * delta;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            pos.x += Math.cos(Math.toRadians(rotation.y)) * SPEED * delta;
            pos.z += Math.sin(Math.toRadians(rotation.y)) * SPEED * delta;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            pos.x -= Math.cos(Math.toRadians(rotation.y)) * SPEED * delta;
            pos.z -= Math.sin(Math.toRadians(rotation.y)) * SPEED * delta;
        }
    }
    public Vector3f getPos()
    {
        return pos;
    }
    
    public float getVectorPosx()
    {
    	return pos.x;
    }
    
    public float getVectorPosz()
    {
    	return pos.z;
    }
    
    public Vector2f getRotation()
    {
        return rotation;
    }
    public void setPosition(float x, float z)
    {
        pos.x = x;
        pos.z = z;
    }
} 