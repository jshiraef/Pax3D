package javagame;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Poacher {
	
	private Mesh[] mesh;
	private Vector2f pos;
	
	private int list;
	
	public Poacher(float x, float z)
	{
		pos = new Vector2f(x, z);
		mesh = new Mesh[1];
		
		mesh[0] = new Mesh(new Vector3f[]
		{
			new Vector3f(x, 0, z),
			new Vector3f(x + 1, 0, z),
			new Vector3f(x + 1,  1, z),
			new Vector3f(x, 1, z)			
		}, new Vector2f[] 
				{
				new Vector2f(ImageLoader.getTextureWidthPosition(2), ImageLoader.getTextureHeightPosition(20)),
				new Vector2f(ImageLoader.getTextureWidthPosition(3), ImageLoader.getTextureHeightPosition(20)),
				new Vector2f(ImageLoader.getTextureWidthPosition(3), ImageLoader.getTextureHeightPosition(19)),
				new Vector2f(ImageLoader.getTextureWidthPosition(2), ImageLoader.getTextureHeightPosition(19)),
				});
		
		list = glGenLists(1);
		glNewList(list, GL_COMPILE);
		glBegin(GL_QUADS);
		for(int i = 0; i < mesh.length; i++)
		{
			mesh[i].render();
		}
		glEnd();
		glEndList();
	}
	
	public void render()
	{
		glPushMatrix();
		glCallList(list);
		glPopMatrix();
	}

}
