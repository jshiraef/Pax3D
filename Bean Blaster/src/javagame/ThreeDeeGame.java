package javagame;
  
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
// Add "extends BasicGameState" after the class declaration if you want to switch it back to state based
// You will also have to disable comments out of the necessary methods to get it to run
public class ThreeDeeGame 
{
    static Player player;
    Level level;
    static boolean aquariumLevel = false;
    public ThreeDeeGame(int state)
    {
    	
        player = new Player(0, .5f, 100);
        if(!aquariumLevel)  
        level = new Level();
        
        if(aquariumLevel)
        level = new Level();
        
        player.setPosition(level.getStartPos().x, level.getStartPos().y);
        
    }
    public void update(float delta)
    {
        player.update(delta);
        level.checkCollision(player); 
//    	System.out.println(player.pos.x + "  " + player.pos.z);
    }
    
    public void renderThreeDee()
    {
       glRotatef(player.getRotation().x, 1, 0, 0);
	        glRotatef(player.getRotation().y, 0, 1, 0);
	        glTranslatef(-player.getPos().x, -player.getPos().y, -player.getPos().z);
	          
	        level.render();
    }
    
//	@Override
//	public void init(GameContainer arg0, StateBasedGame arg1)
//			throws SlickException {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
//			throws SlickException {
//		 
//		
//	}
	
		
	
//	@Override
//	public int getID() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public void update(GameContainer arg0, StateBasedGame arg1, int delta)
//			throws SlickException {
//		player.update((float)delta);
//        level.checkCollision(player);
//		
//	}
//	public void update(float delta) {
//		// TODO Auto-generated method stub
//		
//	}
} 