package javagame;
  
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
  
public class Frame
{
    ThreeDeeGame game;
    float PaxX;
    float PaxZ;
    
    public Frame()
    {
    	
    	
        game = new ThreeDeeGame(2);
    }
    public void render()
    {
    	initGL();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
          
        glPushMatrix();
        game.renderThreeDee();
        
        glPopMatrix();
        
        
    }
    public void update(float delta)
    {
    	
    	PaxX = game.player.getVectorPosx();
    	PaxZ = game.player.getVectorPosz();
        game.update(delta);
//   	System.out.println(Player.pos.x + "  " + Player.pos.z);
    }
    public void initGL()
    {
    
       glEnable(GL_DEPTH_TEST);
       glMatrixMode(GL_PROJECTION);
       glLoadIdentity();
       GLU.gluPerspective(45, (float)Display.getWidth()/(float)Display.getHeight(), 0.2f, 1000);
       glMatrixMode(GL_MODELVIEW);
     
          
       glFrontFace(GL_CW);
       glCullFace(GL_BACK);
       glEnable(GL_CULL_FACE);
          
       glEnable(GL_TEXTURE_2D);
       
       
  //     glDisable(GL_MATRIX_MODE);
  //     disableInitGL();  
        Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
        Mouse.setGrabbed(true);
    }
    
    public void disableInitGL() {
//    	glDisable(GL_DEPTH_TEST);
//    	glDisable(GL_CULL_FACE);
//    	glDisable(GL_TEXTURE_2D);
//    	glDisable(GL_MODELVIEW);
//    	glDisable(GL_PROJECTION);
//    	glDisable(GL_MATRIX_MODE);
//    	glDisable(GL_FRONT_FACE);
//    	
//    	
//    	Mouse.setCursorPosition(Mouse.getX(), Mouse.getY());
//    	Mouse.setGrabbed(false);
//    	System.out.println("lick my balls");
    	
    	
    }
//    public void renderThreeDee()
//    {
//    	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        
//        glPushMatrix();
//        game.renderThreeDee();
//        glPopMatrix();
//    }
    
} 
