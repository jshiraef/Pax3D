
package javagame;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import static org.lwjgl.opengl.GL11.*;

public class Play extends BasicGameState{
	
	Animation pax, willy, movingUp, movingDown, movingLeft, movingStopped, movingRight, celebrate, pacing, pacingLeft, conversation, converse;
	Image worldMap;
	Image worldMap2;
	Image igloo;
	
	
	static boolean nextLevelReady = false;
	static boolean ThirdLevelReady = false;
	
	boolean YouHaveMetWilly = false;
	boolean talkedToWilly = false;
	boolean talkedToWillyTwice = false;
	boolean talkedToWillyThrice = false;
	
	boolean quit = false;
	static boolean enter3d = false;
	static boolean exit3d = false;
	static boolean willysMood = false;
	
	private int counter = 0;
	private int counter2 = 0;
	private int counter3 = 0;
	private int counter4 = 0;
	
	
	int[] duration = {200, 200};
	int[] willyDuration = {150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150};
	int[] conversationDuration = {250, 300, 300, 10000};
	
	
	float paxPositionX = - 100;
	float paxPositionY = - 150;
	
	float shiftX = paxPositionX + 500;
	float shiftY = paxPositionY + 400;
	
	float iglooPositionX = paxPositionX + 400;
	float iglooPositionY = paxPositionY + 400;
	
	
	
	public Play(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		
		worldMap = new Image("res/world.png");
		worldMap2 = new Image("res/world2.png");
		igloo = new Image("res/igloo.png");
		
		
		Image[] walkUp = {new Image("res/walkingUp2.png"), new Image("res/walkingUp3.png")};
		Image[] walkDown = {new Image("res/player.png"), new Image("res/walkingDown2.png")};
		Image[] walkLeft = {new Image("res/playerLeft.png"), new Image("res/walkingLeft2.png")};
		Image[] walkRight = {new Image("res/playerRight.png"), new Image("res/walkingRight2.png")};
		Image[] highFive = {new Image("res/penguinDance1.png"), new Image("res/penguinDance2.png") };
		Image[] standStill = {new Image("res/player.png"), new Image("res/player.png")};
		
		Image[] willyPacing = {new Image("res/willyPacing2.png"), new Image("res/willyPacing3.png"), new Image("res/willyPacing4.png"), new Image("res/willyPacing5.png"), new Image("res/willyPacing6.png"), new Image("res/willyPacing7.png"), new Image("res/willyPacing8.png"), new Image("res/willyPacing9.png"), new Image("res/willyPacing10.png"), new Image("res/willyPacing11.png"), new Image("res/willyPacing12.png"), new Image("res/willyPacing13.png")};
		Image[] textBox = {new Image("res/textBox1.png"), new Image("res/textBox2.png"), new Image("res/textBox3.png"), new Image("res/textBox5.png")};
		
		movingUp = new Animation(walkUp, duration, true);
		movingDown = new Animation(walkDown, duration, true);
		
		movingLeft = new Animation(walkLeft, duration, true);
		movingStopped = new Animation(standStill, duration, false);
		
		movingRight = new Animation(walkRight, duration, true);
		
		
		
		
		converse = new Animation(textBox, conversationDuration, true);
		
		celebrate = new Animation(highFive, duration, true);
		
		pacing = new Animation(willyPacing, willyDuration, true);
		
		
		
		
		pax = movingStopped;
		willy = pacing;
		conversation = converse;
		
		
		
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Input input = gc.getInput();
//		igloo.draw(iglooPositionX, iglooPositionY);
		
		
		
		
		
		worldMap.draw(paxPositionX, paxPositionY);
		worldMap2.draw(paxPositionX + 1320, paxPositionY);
				
		pax.draw(shiftX, shiftY);
		willy.draw(paxPositionX + 2100, paxPositionY + 100);
//		converse.draw(paxPositionX + 200, paxPositionY + 250);
		
		
					
		
		
//		g.drawString("pax's X: " + paxPositionX + " \n pax's Y: " + paxPositionY, 400, 20);
//		g.drawString("shiftX " + shiftX + "\n shiftY: " + shiftY, 200, 20);
		if(quit == true){
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if(quit == false){
				g.clear();
			}
		}
		
		if (paxPositionX < -767 && paxPositionX > -850 && paxPositionY > -220 && paxPositionY < -120) {
			g.drawString(" E to enter igloo", 300, 140);
				pax = celebrate;
		}
		
		if ((paxPositionX <= -1765))
		{
			
			converse.draw(paxPositionX + 2100, paxPositionY + 400);
			talkedToWilly = true;
			
			if(talkedToWilly && !talkedToWillyTwice)			
			g.drawString(" Hey check it out!" + "\n We can like totally have conversations and stuff" + " \n \n (c to continue) ", paxPositionX + 2180, paxPositionY + 650);
			
			if(!YouHaveMetWilly)
			{
				if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToWillyTwice = true;
				}
			}
			else if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToWillyThrice = true;
				}
			
				
				if(talkedToWillyTwice && !YouHaveMetWilly)
				{
					String[] greeting = {"I", "f", " ", "I", " ", "w", "e", "r", "e", " ", "y", "o", "u", ",", " ","I", " ", "w", "o" ,"u", "l", "d", " ", "t", "h", "i", "n", "k", " ", "t", "w", "i", "c", "e", " ", "b", "e", "f", "o", "r", "e", " "};
					String[] greeting2 = {"h", "a", "n", "g", "i", "n", " ", "a", "r", "o", "u", "n", "d", " ", "t", "h", "a", "t", " ", "w", "e", "i", "r", "d", " ", "i", "c", "e", " ", "s", "t", "r", "u", "c", "t", "u", "r", "e", " "};
					int xCoord = (int) (paxPositionX + 2200);
					int yCoord = (int) (paxPositionY + 675);
					int xCoord1 = (int) (paxPositionX + 2200);
					int yCoord1 = (int) (paxPositionY + 690);
					int timerIncrement = 1000;
					int letterCounter = 0;
					
					int timerIncrement2 = 1000;
					int letterCounter2 = 0;
		        
		        	for(int i = 0; i < greeting.length; i++)
		        	{		
		        		counter++;	        	
		        		if(counter > timerIncrement)
		        		{		        			
		        		g.drawString(greeting[letterCounter], xCoord, yCoord);
		        		xCoord = xCoord + 10;
		        		timerIncrement += 1000;		        		
		        			letterCounter ++;
		        		}
		        	}
		        	
		        	if(letterCounter >= greeting.length) 
		        	{

		        		for(int i = 0; i < greeting2.length; i++)
		        		{		
		        			counter2++;	        	
		        			if(counter2 > timerIncrement2)
		        			{		        			
		        				g.drawString(greeting2[letterCounter2], xCoord1, yCoord1);
		        				xCoord1 = xCoord1 + 10;
		        				timerIncrement2 += 1000;		        		
		        				letterCounter2 ++;
		        			}
		        		}
		        	}
		        	 if(letterCounter2 >= greeting2.length && input.isKeyDown(Input.KEY_C))
		        	 {
		        		 YouHaveMetWilly = true;
		        	 }
		        }
				
//				if(talkedToWilly && talkedToWillyTwice && input.isKeyDown(Input.KEY_C))
//				{
//					talkedToWillyTwice = false;
//					talkedToWillyThrice = true;
//				}
				
				if(talkedToWillyThrice)
				{
					String[] greeting = {"M", "y", " ", "b", "u", "d", "d", "y", " ", "w", "a", "s", " ", "o", "v", "e", "r", " ", "t","h", "e", "r", "e" ," ", "a", "w", "h", "i", "l", "e", " ", "a", "g", "o", ",", "a", "n", "d", " ", "I", " "};
					String[] greeting2 = {"h", "a", "v", "e", "n", "'", "t", " ", "s", "e", "e", "n", " ", "h", "i", "m", " ", "s", "i", "n", "c", "e", ".", ".", ".", "I", "'", "m", " ", "W", "i", "l", "l", "y", " ", "b", "y", " ", "t", "h", "e", " ", "w", "a", "y", " "};
					int xCoord = (int) (paxPositionX + 2190);
					int yCoord = (int) (paxPositionY + 675);
					int xCoord1 = (int) (paxPositionX + 2190);
					int yCoord1 = (int) (paxPositionY + 690);
					int timerIncrement = 1000;
					int letterCounter = 0;
					
					int timerIncrement2 = 1000;
					int letterCounter2 = 0;
		        
		        	for(int i = 0; i < greeting.length; i++)
		        	{		
		        		counter3++;	        	
		        		if(counter3 > timerIncrement)
		        		{		        			
		        		g.drawString(greeting[letterCounter], xCoord, yCoord);
		        		xCoord = xCoord + 10;
		        		timerIncrement += 1000;		        		
		        			letterCounter ++;
		        		}
		        	}
		        	
		        	if(letterCounter >= greeting.length) 
		        	{

		        		for(int i = 0; i < greeting2.length; i++)
		        		{		
		        			counter4++;	        	
		        			if(counter4 > timerIncrement2)
		        			{		        			
		        				g.drawString(greeting2[letterCounter2], xCoord1, yCoord1);
		        				xCoord1 = xCoord1 + 10;
		        				timerIncrement2 += 1000;		        		
		        				letterCounter2 ++;
		        			}
		        		}
		        	}
				}
				
				
			
		}
		
		if(nextLevelReady == true && ThirdLevelReady == false) {
			glDisable(GL_BLEND);
		    glMatrixMode(GL_PROJECTION);
		    glLoadIdentity(); // Reset The Projection Matrix
		    GLU.gluPerspective(45, (float)Display.getWidth()/(float)Display.getHeight(), 0.2f, 1000); // Calculate The Aspect Ratio Of The Window

		    glMatrixMode(GL_MODELVIEW);
		    glLoadIdentity();
		    
		    System.out.println("Make ready for 3d");
		    
		    sbg.enterState(2);
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
		Input input = gc.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		

		
//		System.out.println("x coordinate: " + posX + "  y coordinate: " + posY);
		System.out.println("Pax's X: " + paxPositionX + "  Pax's Y: " + paxPositionY);
		
		
		if(input.isKeyDown(Input.KEY_UP)){
			pax = movingUp;
			paxPositionY += delta* .18f;
			
			//border collisions
			if(paxPositionY > 101)
				paxPositionY = 101;	
			if(shiftY > 250)
				shiftY -= delta* .18f;
			if(shiftY > 250)
				paxPositionY = -242;
			if(shiftY <= 250)
				shiftY = 250;
			
		//collisions
			if(shiftX >= -50 && shiftX <= 245 && paxPositionY >= -90)
				paxPositionY = -90;
			if( paxPositionY >= 0)
				paxPositionY = 0;
			if(shiftX > 500 && paxPositionY >= -160)
				paxPositionY = -160;
			if(paxPositionX < -510 && paxPositionX > -750 && paxPositionY >= -150)
				paxPositionY = -150;
//			if(paxPositionX < -0 && paxPositionX > -740 && paxPositionY >= -165)
//			paxPositionY = -165;
//		if(paxPositionX == 0 && paxPositionY >= -222)
//			paxPositionY = -222;
//		if(paxPositionX < -715 && paxPositionX > -900 && paxPositionY >= -303)
//			paxPositionY = -242;
//		if(paxPositionX < -915 && paxPositionX > -2030 && paxPositionY >= -165)
//			paxPositionY = -165;
//		if(shiftX > 230 && paxPositionY >= -230)
//			paxPositionY = -165;
		
		}
//		else pax = movingStopped;
		
		
		if(input.isKeyDown(Input.KEY_DOWN)){
			pax = movingDown;
			paxPositionY -= delta* .18f;
			
			//border collisions
			if(paxPositionY < -730)
				paxPositionY = -730;
			if(paxPositionY <= -242)
				shiftY += delta* .18f;
			if(paxPositionY <= -242)
				paxPositionY = -242;
			if(shiftY >= 535)
				shiftY = 535;
		}
//		else pax = movingStopped;
		
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			
			pax = movingLeft;
			paxPositionX += delta* .18f;
			
			//border collisions
			if(paxPositionX > 0)
				paxPositionX = 0;
			if(paxPositionX >= 0)
				shiftX -= delta* .18f;
			if(shiftX <= -50)
				shiftX = -50;
			if(shiftX > 400)
			   paxPositionX = -1765;
			if(shiftX > 400)
				shiftX -= delta* .18f;

	
	
			//collisions
			if(paxPositionY > -150 && paxPositionY <= 0 && paxPositionX >= -750 && paxPositionX <= -720)
				paxPositionX = -750;
			if(paxPositionY <= 0 && paxPositionY > -90 && shiftX < 245)
				shiftX = 245;	
		
			}
//			else pax = movingStopped;
		
		
		if(input.isKeyDown(Input.KEY_RIGHT)){
			
//			if(input.isKeyPressed(Input.KEY_RIGHT)) {
				
				pax = movingRight;
				paxPositionX -= delta* .18f;
				
				//border collisions
				if(paxPositionX < -1765)
					paxPositionX = -1765;
				if(paxPositionX <= -1765)
					shiftX += delta* .18f;
				if(shiftX > 775)
					shiftX = 775;
				if(shiftX < 399)
					shiftX += delta* .18f;
				if(shiftX < 399)
					paxPositionX = 0;
				
				
				//object collisions
				if(paxPositionY > -242 && shiftY <= 165 && paxPositionY <= -165 && paxPositionX <= -687 && paxPositionX >= -700)
					paxPositionX = -687;
				if(paxPositionY <= 0 && paxPositionY >= -155 && paxPositionX <= -510 && paxPositionX >= -550)
					paxPositionX = -510;						
				if(paxPositionY <= 0 && paxPositionY >= -140 && shiftX >= 500)
					shiftX = 500;
//			}
//			else peggy = movingRightStopped;
			
		}
		
//		else pax = movingStopped;
		
		
		
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			quit = true;
		}
		
//		if ((paxPositionX <= -1765)) {
//				if(input.isKeyDown(Input.KEY_C)){
//					g.drawString(" If I were you, I would stay away from that ice building over there")
//				
//			}
//		}
		
		if(paxPositionX < -767 && paxPositionX > -850 && paxPositionY > -220 && paxPositionY < -120 && input.isKeyDown(Input.KEY_E)){
//			enter3d = true;
			
			
			MainThreeDee.quit = false;
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
			ThreeDeeGame.player.setPosition(40, 4);
			
		}
		
		//willy's movement
//		if(willysMood == false && willyPositionX > -2050)
//		{
//			willyPositionX += delta* .1f;
//		}
//		else willyPositionX -= delta* .1f;
		
		
			
		
		//menu is up
		if(quit == true){
			if(input.isKeyDown(Input.KEY_R))
				quit = false;
			if(input.isKeyDown(Input.KEY_M))
				sbg.enterState(0);
			if(input.isKeyDown(Input.KEY_Q))
				System.exit(0);
			
		
		}
		
		
	}
	
	public int getID(){
		return 1;
	}
}

