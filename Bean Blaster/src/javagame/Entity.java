package javagame;
  
import org.lwjgl.util.vector.Vector3f;
  
public class Entity
{
    protected Vector3f pos;
    protected Vector3f oldpos;
    protected float width, height;
      
    public Entity()
    {
        oldpos = new Vector3f();
    }
  
    public Entity(float width, float height)
    {
        this.width = width;
        this.height = height;
        oldpos = new Vector3f();
    }
      
    public Vector3f getPos()
    {
        return pos;
    }
    public void setPos(Vector3f pos)
    {
        this.pos = pos;
    }
    public Vector3f getOldpos()
    {
        return oldpos;
    }
    public void setOldpos(Vector3f oldpos)
    {
        this.oldpos = oldpos;
    }
    public float getWidth()
    {
        return width;
    }
    public void setWidth(float width)
    {
        this.width = width;
    }
    public float getHeight()
    {
        return height;
    }
    public void setHeight(float height)
    {
        this.height = height;
    }
} 