
package javagame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainGame extends StateBasedGame {
	
	public static final String gameName = "Bean Blaster!";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int mainThreeDee = 2;
	public static final int play2 = 3;
	public static final int play3 = 4;
	public static final int mainThreeDee2 = 5;
	public static final int play4 = 6;
	public static final int credits = 7;
	
	public static AppGameContainer appgc;
	
	public static boolean ThreeDeeLevel = false;
	
	public MainGame(String gameName) {
		super(gameName);	
		this.addState(new Play(play));
		this.addState(new Play2(play2));
		this.addState(new Play3(play3));
		this.addState(new Play4(play4));
		this.addState(new Credits(credits));
		this.addState(new Menu(menu));
		this.addState(new MainThreeDee(mainThreeDee));
		this.addState(new MainThreeDee2(mainThreeDee2));
	} 
	
	public void initStatesList(GameContainer gc) throws SlickException {
		
//		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(play2).init(gc, this);
		this.getState(play3).init(gc, this);
		this.getState(play4).init(gc, this);
		this.getState(credits).init(gc, this);
		this.getState(mainThreeDee).init(gc, this);
		this.getState(mainThreeDee2).init(gc, this);
		this.enterState(menu);
		
	}
	

	public static void main(String[] args)throws SlickException {
		
		
		
		          
					
        
//        while(!Display.isCloseRequested())
//        {
        	
        	if(ThreeDeeLevel == false)
        	{
				appgc = new AppGameContainer(new MainGame(gameName));
				appgc.setDisplayMode(900, 700, false);
				appgc.start();
				
				
        	}
//        	else
//        	{
//        		try {
//					Display.setDisplayMode(new DisplayMode(800, 500));				
//		            Display.setTitle("Who dat");
//		            Display.create();
//					} catch (LWJGLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//        			Frame f = new Frame();
//                
//        			long lastFrame = System.currentTimeMillis();
//        		while(!Display.isCloseRequested())
//        		{
//        		
//        		
//                long thisFrame = System.currentTimeMillis();
//                float delta = (thisFrame - lastFrame) / 1000f;
//                lastFrame = thisFrame;
//              
//                f.update(delta);
//                System.out.println("We made it here!");
//                f.render();
//              
//                Display.update();
//                ThreeDeeLevel = true;
//        		}
//              Display.destroy();
//            
//        		}
//            Display.sync(60);

    }
}



