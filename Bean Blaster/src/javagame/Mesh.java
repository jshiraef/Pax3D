package javagame;
  
import static org.lwjgl.opengl.GL11.*;
  
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
  
public class Mesh 
{
    private Vector3f[] vertices;
    private Vector2f[] texCoords;
    public Mesh(Vector3f[] vertices, Vector2f[] texCoords)
    {
        this.vertices = vertices;
        this.texCoords = texCoords;
    }
    public void render()
    {
        for(int i = 0;i < vertices.length;i++)
        {
            glTexCoord2f(texCoords[i].x, texCoords[i].y);
            glVertex3f(vertices[i].x, vertices[i].y, vertices[i].z);
        }
    }
} 