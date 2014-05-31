package javagame;


import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image playNow, playNow2;
	Image exitGame, exitGame2;
	Image ThreeDeeIcon;
	
	private boolean transition = false;
	
	Music menuMusic;
	Sound clickSound;
	Sound buttonHover;
	
	private int counter = 0;
	public Menu(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		
		playNow = new Image("res/playNow.png");
		playNow2 = new Image("res/playNow2.png");
		exitGame = new Image("res/exitGame.png");
		exitGame2 = new Image("res/exitGame2.png");
		ThreeDeeIcon = new Image("res/3Dicon.png");
		
		menuMusic = new Music("res/sounds/menuMusic.wav");
		clickSound = new Sound("res/sounds/clickSound.wav");
		buttonHover = new Sound("res/sounds/button.wav");
		
		
		
		menuMusic.loop();
		
		
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		if(!transition)
		{
//			g.drawString("Welcome to Penguin Land!", 100, 50);
			
			if((posX > 320 && posX < 470) && (posY > 420 && posY < 505))
			{
//			buttonHover.play();
			playNow2.draw(320, 140);
			}
			else playNow.draw(320, 195);
			if((posX > 370 && posX < 525) && (posY > 170 && posY < 320))
			{
			exitGame2.draw(240, 240);
//			buttonHover.play();
			}
			else exitGame.draw(240, 245);
//			ThreeDeeIcon.draw(350, 200);
		}
		
		if(transition){
		String[] greeting = {"S", "o", "m", "e", "w", "h", "e", "r", "e", " ", " " ,"c", "o", "l", "d", " "};
        int xCoord = 350;
        int yCoord = 300;
        int timerIncrement = 1000;
        int letterCounter = 0;
        
        	for(int i = 0; i < greeting.length; i++)
        	{
        		
        		counter++;
        	
        		if(counter > timerIncrement)
        		{
        			
        		g.drawString(greeting[letterCounter], xCoord, yCoord);
        		xCoord = xCoord + 10;
        		timerIncrement += 1000;
        		clickSound.play();
        			letterCounter ++;
        		}
        	}
        	 if(letterCounter >= greeting.length)
        	 {
        		 try {
					Thread.sleep(2500L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	
        		 sbg.enterState(1);
        	 }
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		Input input = gc.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		System.out.println("posX: " + posX + "posY: " + posY);
		
//		System.out.println("x coordinate: " + posX + "  y coordinate: " + posY);
		
		if((posX > 320 && posX < 470) && (posY > 420 && posY < 505)) {
						
		  if(input.isMouseButtonDown(0)) {
			  clickSound.play();
			  transition = true;
//			  sbg.enterState(1);
		  }
		}
		
		if((posX > 370 && posX < 525) && (posY > 170 && posY < 320)) {
			
			exitGame2.draw(100, 200);
			
			  if(input.isMouseButtonDown(0)) {
				  clickSound.play();
				  System.exit(0);
			  }
			}
		
//		if((posX > 350 && posX < 510) && (posY > 0 && posY < 155)) {
//			  if(input.isMouseButtonDown(0)) {
//				  clickSound.play();				  
//
//				  
//				  sbg.enterState(2);
//			  }
//			}
		/*
		if(input.isKeyDown(Input.KEY_UP)){
			penguinY -= 1;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			penguinY += 1;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			penguinX -= 1;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			penguinX += 1;
		}
	*/
		
		

		
	}
	
	public int getID(){
		return 0;
	}
}

