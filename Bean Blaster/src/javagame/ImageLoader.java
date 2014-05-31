package javagame;
  
import java.awt.image.BufferedImage;
import java.io.IOException;
  
import javax.imageio.ImageIO;
  
import static org.lwjgl.opengl.GL11.*;
  
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
  
public class ImageLoader 
{
    public static final float texturewidth = 64f/512f;
    public static final float textureheight = 64f/2048f;
    public static float getTextureWidthPosition(int x)
    {
        return x * texturewidth;
    }
    public static float getTextureHeightPosition(int y)
    {
        return y * textureheight;
    }
    public static BufferedImage loadBufferedImage(String path)
    {
        try 
        {
            return ImageIO.read(ImageLoader.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    public static Texture loadTexture(String path)
    {
        try 
        {
            return TextureLoader.getTexture("PNG", ImageLoader.class.getClassLoader().getResourceAsStream(path), GL_NEAREST);
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}  
