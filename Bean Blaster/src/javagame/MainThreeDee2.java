package javagame;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CCW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainThreeDee2 extends BasicGameState
{
MainGame game;
long lastFrame;
Frame2 f2;

Music playMusic;
Sound RunningMan;
Sound GettingClose;
Sound Punch;

static boolean quit = false;
boolean enter2d = false;

private int counter = 0;
static boolean timeRunning = false;
static boolean captured = false;


Image igloo;

public MainThreeDee2(int state) {
		
}

public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
//	if(Level.aquariumLevel == true) 
//		{
			f2 = new Frame2();
    
			lastFrame = System.currentTimeMillis();
	
			playMusic = new Music("res/sounds/Clutter.wav");
			GettingClose = new Sound("res/sounds/GettingClose.wav");
			RunningMan = new Sound("res/sounds/ManRunning.wav");
			Punch = new Sound("res/sounds/punchPenguin.wav");
	
			igloo = new Image("res/igloo.png");
			
//		}
	}

public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
{
	if(quit == false){
		
	f2.render();
	System.out.println("We are in 3d level 2!");
	
	if(Level.aquariumLevel == true)
		System.out.println("The aquarium level is ago! It should be working!");
	
	}
	else{
		
		
		
//	  	glDisable(GL_DEPTH_TEST);
//    	glDisable(GL_CULL_FACE);
//    	glDisable(GL_TEXTURE_2D);
//    	glDisable(GL_MODELVIEW);
//    	glDisable(GL_PROJECTION);
//    	glDisable(GL_MATRIX_MODE);
//    	glDisable(GL_FRONT_FACE);
//		glEnd();
		
		glDisable(GL_DEPTH_TEST);

		
		glFrontFace(GL_CCW);

		glEnable(GL_BLEND);
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glOrtho(0.0f, (float) Display.getWidth(), (float) Display.getHeight(), 0.0f, 0.0f, 1.0f);
	
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
		
		
    	System.out.println("The next level should be ready");
    	
//    	sbg.enterState(1);
	}
	
		
}

public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
{
	Input input = gc.getInput();
	

//	while(!Display.isCloseRequested())
//	{
	
	
	
		long thisFrame = System.currentTimeMillis();
		 float delta2 =  ((thisFrame - lastFrame) / 1000f);
		lastFrame = thisFrame;
		System.out.println(f2.PaxX + "  " + f2.PaxZ);
		f2.update(delta2);
//		System.out.println("We made it here!Fo show nigga 100%");
//		f2.render();
//
//		Display.update();
		

		
//		if(f2.PaxZ < 3.5 && f2.PaxX < 41 && f2.PaxX > 40)
//		
//		{	
////			Level.poachers.add(new Poacher(42, 15));
//			quit = true;
////			f.disableInitGL();
//			playMusic.play();
////			make2d();
//			
//			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
//			ThreeDeeGame.player.setPosition(40, 4);
//		}
		
		if(f2.PaxZ > 35.2 && f2.PaxX > 23.2 && f2.PaxX < 23.8)
		{
			quit = true;
			sbg.enterState(6);
		}
//			
//		
//		if(f.PaxZ > 26.5 && f.PaxX > 44)
//		{
//			timeRunning = true;
//			
//
//		}
//			
//			if(timeRunning)
//			{
//			
//			counter ++;
//			}
//			if( counter > 100)
//			{
//				Level.poachers.add(new Poacher(45, 31));
//				Level.poachers.remove(0);
//			}
//			
//			if( counter > 200)
//			{
//				Level.poachers.add(new Poacher(45, (float) 30.5));
//				Level.poachers.remove(0);
//			}
//			if(counter > 300) 
//			{
//				Level.poachers.add(new Poacher(45, 30));
//				Level.poachers.remove(0);
//				
//			}
//			if(counter > 400) 
//			{
//				Level.poachers.add(new Poacher(45, (float) 29.5));
//				Level.poachers.remove(0);
//			}
//			if(counter > 500) 
//			{
//				Level.poachers.add(new Poacher((float) 44.8, (float) 29));
//				Level.poachers.remove(0);
//			}
//			if(counter > 600) 
//			{
//				Level.poachers.add(new Poacher((float) 44.6, (float) 28.5));
//				Level.poachers.remove(0);
//				Punch.play();
//			}
//
//
//				
//			
//			
//			if(counter > 2000)
//			{
//				System.out.println("Now it should change");
//			try {
//				Thread.sleep(1000L);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//				captured = true;
//			}
			
			
			
			
				
				
			
				
				
			
				
				
				
//			if(captured == true)
//			{
//				counter = 0;
//				quit = true;
//				
//				sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
//				playMusic.play();
//				ThreeDeeGame.player.setPosition(35, 3);
////				sbg.enterState(1);
////				f.render();
////				long thisFrame2 = System.currentTimeMillis();
////				 float delta3 =  ((thisFrame2 - lastFrame) / 1000f);
////				lastFrame = thisFrame2;
////				System.out.println(f.PaxX + "  " + f.PaxZ);
////				f.update(delta3);
//			}
		
		
		
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			
//			f.disableInitGL();
//			glFrontFace(GL_CCW);
			quit = true;
			
			if(enter2d == true) {
//			sbg.enterState(1);
				}
			
			}
		
																																	}
		
		


public int getID(){
	return 5;
}

public static void make2d() throws SlickException {
//glEnable(GL_BLEND);
//GL11.glMatrixMode(GL11.GL_PROJECTION);
//GL11.glLoadIdentity();
//glOrtho(0.0f, (float) Display.getWidth(), (float) Display.getHeight(), 0.0f, 0.0f, 1.0f);
//
//GL11.glMatrixMode(GL11.GL_MODELVIEW);
//GL11.glLoadIdentity();
//	AppGameContainer appgc = null;
//	
//	appgc.setDisplayMode(640, 480, false);
//	appgc.start();

}
}

