package javagame;
  
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
  
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
  
import static org.lwjgl.opengl.GL11.*;
  
public class Level2
{
	static LinkedList<Poacher>	poachers;
    private LinkedList<Mesh> world;
    private BufferedImage map;
    private Texture sheet;
    private Vector2f startposition;
      
    private int worldlist;
    
    static boolean aquariumLevel = false;
      
    public Level2()
    {
        world = new LinkedList<Mesh>();
        poachers = new LinkedList<Poacher>();
        poachers.add(new Poacher(45, 32));
          
        sheet = ImageLoader.loadTexture("gfx/sheet1.png");
          
        startposition = new Vector2f();
        
          
        loadLevel(0);
       
        
        
    }
    public void loadLevel(int level)
    {
        world.clear();
          
        map = ImageLoader.loadBufferedImage("maps/map" + level + ".png");
          
        for(int x = 0;x < map.getWidth();x++)
        {
            for(int y = 0;y < map.getHeight();y++)
            {
                Color c = new Color(map.getRGB(x, y));
                  
                if(c.getRed() == 255&&c.getGreen() == 255&&c.getBlue() == 255) addWalls(x, y, 0, 0);
                else if(c.getRed() == 255&&c.getGreen() == 0&&c.getBlue() == 0) 
                {
                    startposition.x = x;
                    startposition.y = y;
                    addWalls(x, y, 0, 0);
                }
                else if(c.getRed() == 128&&c.getGreen() == 128&&c.getBlue() == 128) addWalls(x, y, 0, 1);
                else if(c.getRed() == 100&&c.getGreen() == 100&&c.getBlue() == 100) addWalls(x, y, 4, 1);
                else if(c.getRed() == 50&&c.getGreen() == 100&&c.getBlue() == 100) addWalls(x, y, 0, 16);
                else if(c.getRed() == 50&&c.getGreen() == 50&&c.getBlue() == 100) addWalls(x, y, 1, 16);
                //pink
                else if(c.getRed() == 255&&c.getGreen() == 100&&c.getBlue() == 255) addWalls(x, y, 2, 12);
                //orange
                else if(c.getRed() == 255&&c.getGreen() == 100&&c.getBlue() == 100) addWalls(x, y, 3, 12);
                //aquarium escape door
                else if(c.getRed() == 255&&c.getGreen() == 100&&c.getBlue() == 50) addWalls(x, y, 4, 1);
                //light blue
                else if(c.getRed() == 150&&c.getGreen() == 150&&c.getBlue() == 250) addWalls(x, y, 2, 15);
                // blue
                else if(c.getRed() == 50&&c.getGreen() == 150&&c.getBlue() == 250) addWalls(x, y, 3, 15);
            }
        }
          
        worldlist = glGenLists(1);
        glNewList(worldlist, GL_COMPILE);
        glBegin(GL_QUADS);
        for(int i = 0;i < world.size();i++)
        {
            world.get(i).render();
        }
        glEnd();
        glEndList();
    }
    public void render()
    {
        glBindTexture(GL_TEXTURE_2D, sheet.getTextureID());
        glCallList(worldlist);
        
        for(int i = 0; i < poachers.size(); i++) 
        {
        	poachers.get(i).render();
        }
    }
    public void checkCollision(Entity e)
    {
        int tileX = (int) (e.getPos().x - e.getWidth()/2);
        int tileY = (int) (e.getPos().z - e.getHeight()/2);
          
        for(int x = 0;x < 2;x++)
        {
            for(int y = 0;y < 2;y++)
            {
                int newTileX = tileX + x;
                int newTileY = tileY + y;
                  
                Color c = new Color(map.getRGB(newTileX, newTileY));
                if(c.getRed() == 0&&c.getGreen() == 0&&c.getBlue() == 0)
                {
                   
                	if(Collision.rectToRect(
                            e.getPos().x - e.getWidth()/2, e.getPos().z - e.getHeight()/2, e.getWidth(), e.getHeight(),
                            newTileX, newTileY, 1, 1))
                    {
                	    boolean collide = false;
                		if(Collision.rectToRect(
                            e.getOldpos().x - e.getWidth()/2, e.getPos().z - e.getHeight()/2, e.getWidth(), e.getHeight(),
                            newTileX, newTileY, 1, 1))
                    {
                        collide = true;
                        if(e.getOldpos().z < newTileY + 0.5f) e.getPos().z = newTileY - e.getHeight()/2 - 0.0001f;
                        else e.getPos().z = newTileY + 1 + e.getHeight()/2 + 0.0001f;
                    }
                    if(Collision.rectToRect(
                            e.getPos().x - e.getWidth()/2, e.getOldpos().z - e.getHeight()/2, e.getWidth(), e.getHeight(),
                            newTileX, newTileY, 1, 1))
                    {
                       collide = true;
                        if(e.getOldpos().x < newTileX + 0.5f) e.getPos().x = newTileX - e.getWidth()/2 - 0.0001f;
                        else e.getPos().x = newTileX + 1 + e.getWidth()/2 + 0.0001f;
                    }
                    if(!collide)
                    {
                    	if(e.getOldpos().z < newTileY + 0.5f) e.getPos().z = newTileY - e.getHeight()/2 - 0.0001f;
                        else e.getPos().z = newTileY + 1 + e.getHeight()/2 + 0.0001f;
                    	
                    	if(e.getOldpos().x < newTileX + 0.5f) e.getPos().x = newTileX - e.getWidth()/2 - 0.0001f;
                        else e.getPos().x = newTileX + 1 + e.getWidth()/2 + 0.0001f;
                    }
                    
                    }
                }
            }
        }
    }
    private void addWalls(int x, int y, int texturex, int texturey)
    {
    	
		world.add(new Mesh(new Vector3f[]
                {
                    new Vector3f(x, 0, y),
                    new Vector3f(x + 1, 0, y),
                    new Vector3f(x + 1, 0, y + 1),
                    new Vector3f(x, 0, y + 1)
                },
                new Vector2f[]
                {
                    new Vector2f(ImageLoader.getTextureWidthPosition(1), ImageLoader.getTextureHeightPosition(7)),
                    new Vector2f(ImageLoader.getTextureWidthPosition(2), ImageLoader.getTextureHeightPosition(7)),
                    new Vector2f(ImageLoader.getTextureWidthPosition(2), ImageLoader.getTextureHeightPosition(6)),
                    new Vector2f(ImageLoader.getTextureWidthPosition(1), ImageLoader.getTextureHeightPosition(6))
                }));
  
        world.add(new Mesh(new Vector3f[]
                {
                    new Vector3f(x, 1, y),
                    new Vector3f(x, 1, y + 1),
                    new Vector3f(x + 1, 1, y + 1),
                    new Vector3f(x + 1, 1, y)
                },
                new Vector2f[]
                {
        		
                        new Vector2f(ImageLoader.getTextureWidthPosition(0), ImageLoader.getTextureHeightPosition(15)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(1), ImageLoader.getTextureHeightPosition(15)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(1), ImageLoader.getTextureHeightPosition(14)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(0), ImageLoader.getTextureHeightPosition(14))
                }));
        System.out.println("We are in the correct addWalls method here. WHAT'S GOING ON??!!");
    	

    	
          
        checkWalls(x, y, texturex, texturey);
    }
    private void checkWalls(int x, int y, int texturex, int texturey)
    {
        checkLeft(x, y, texturex, texturey);
        checkBottom(x, y, texturex, texturey);
        checkTop(x, y, texturex, texturey);
        checkRight(x, y, texturex, texturey);
    }
    private void checkLeft(int x, int y, int texturex, int texturey)
    {
        Color c;
        if(x - 1 < 0) c = new Color(0, 0, 0);
        else c = new Color(map.getRGB(x - 1, y));
        if(c.getRed() == 0&&c.getGreen() == 0&&c.getBlue() == 0)
        {
            world.add(new Mesh(new Vector3f[]
                    {
                        new Vector3f(x, 0, y),
                        new Vector3f(x, 0, y + 1),
                        new Vector3f(x, 1, y + 1),
                        new Vector3f(x, 1, y)
                    },
                    new Vector2f[]
                    {
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey + 1)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey + 1)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey))
                    }));
        }
    }
    private void checkRight(int x, int y, int texturex, int texturey)
    {
        Color c;
        if(x + 1 >= map.getWidth()) c = new Color(0, 0, 0);
        else c = new Color(map.getRGB(x + 1, y));
        if(c.getRed() == 0&&c.getGreen() == 0&&c.getBlue() == 0)
        {
            world.add(new Mesh(new Vector3f[]
                    {
                        new Vector3f(x + 1, 0, y),
                        new Vector3f(x + 1, 1, y),
                        new Vector3f(x + 1, 1, y + 1),
                        new Vector3f(x + 1, 0, y + 1)
                    },
                    new Vector2f[]
                    {
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey + 1)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey + 1))
                    }));
        }
    }
    private void checkTop(int x, int y, int texturex, int texturey)
    {
        Color c;
        if(y - 1 < 0) c = new Color(0, 0, 0);
        else c = new Color(map.getRGB(x, y - 1));
        if(c.getRed() == 0&&c.getGreen() == 0&&c.getBlue() == 0)
        {
            world.add(new Mesh(new Vector3f[]
                    {
                        new Vector3f(x, 0, y),
                        new Vector3f(x, 1, y),
                        new Vector3f(x + 1, 1, y),
                        new Vector3f(x + 1, 0, y)
                    },
                    new Vector2f[]
                    {
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey + 1)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey + 1))
                    }));
        }
        
    }
    private void checkBottom(int x, int y, int texturex, int texturey)
    {
        Color c;
        if(y + 1 >= map.getHeight()) c = new Color(0, 0, 0);
        else c = new Color(map.getRGB(x, y + 1));
        if(c.getRed() == 0&&c.getGreen() == 0&&c.getBlue() == 0)
        {
            world.add(new Mesh(new Vector3f[]
                    {
                        new Vector3f(x, 0, y + 1),
                        new Vector3f(x + 1, 0, y + 1),
                        new Vector3f(x + 1, 1, y + 1),
                        new Vector3f(x, 1, y + 1)
                    },
                    new Vector2f[]
                    {
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey + 1)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey + 1)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex + 1), ImageLoader.getTextureHeightPosition(texturey)),
                        new Vector2f(ImageLoader.getTextureWidthPosition(texturex), ImageLoader.getTextureHeightPosition(texturey))
                    }));
        }
    }
    public Vector2f getStartPos()
    {
        return startposition;
    }
} 
