
package javagame;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Play3 extends BasicGameState{
	
	Animation peggy, movingUp, movingDown, movingLeft, movingRight, celebrate, converse, pacing, female, femaleShy, femaleSad;
	Image worldMap;
	Image igloo;
	Sound dripSound;
	Sound clickSound;
	
	boolean quit = false;
	boolean winner = false;
	boolean transition = false;
	
	boolean enter3d = false;
	
	int[] duration = {200, 200};
	int[] femaleDuration = {2000, 2000};
	int[] conversationDuration = {250, 300, 300, 10000};
	
	private int counter = 0;
	
	float peggyPositionX = 0;
	float peggyPositionY = -200;
	
	float shiftX = peggyPositionX + 150;
	float shiftY = peggyPositionY + 550;
	
	float iglooPositionX = peggyPositionX + 400;
	float iglooPositionY = peggyPositionY + 400;
	
	public Play3(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		
		
		worldMap = new Image("res/penguinAquarium4.png");
		dripSound = new Sound("res/sounds/CaveDrip.wav");
		clickSound = new Sound("res/sounds/clickSound.wav");
//		igloo = new Image("res/igloo.png");
		Image[] walkUp = {new Image("res/walkingUp2.png"), new Image("res/walkingUp3.png")};
		Image[] walkDown = {new Image("res/player.png"), new Image("res/walkingDown2.png")};
		Image[] walkLeft = {new Image("res/playerLeft.png"), new Image("res/walkingLeft2.png")};
		Image[] walkRight = {new Image("res/playerRight.png"), new Image("res/walkingRight2.png")};
		Image[] highFive = {new Image("res/penguinDance1.png"), new Image("res/penguinDance2.png") };
		
		Image[] textBox = {new Image("res/textBox1.png"), new Image("res/textBox2.png"), new Image("res/textBox3.png"), new Image("res/textBox5.png")};
		Image[] peggySad = {new Image("res/peggySad.png"), new Image("res/peggySad.png") };
		Image[] peggyShy = {new Image("res/peggySad.png"), new Image("res/peggyShy.png") };
		
		movingUp = new Animation(walkUp, duration, true);
		movingDown = new Animation(walkDown, duration, true);
		
		movingLeft = new Animation(walkLeft, duration, true);
		movingRight = new Animation(walkRight, duration, true);
		
		converse = new Animation(textBox, conversationDuration, true);
		femaleShy = new Animation(peggyShy, femaleDuration, true);
		celebrate = new Animation(highFive, femaleDuration, true);
		
		femaleSad = new Animation(peggySad, femaleDuration, true);
		
		peggy = movingDown;
		female = femaleSad;
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		if(!transition)
		{
		//		igloo.draw(iglooPositionX, iglooPositionY);
		worldMap.draw(peggyPositionX, peggyPositionY);
		peggy.draw(shiftX, shiftY);
		female.draw(peggyPositionX + 1300, peggyPositionY + 820);
		
		g.drawString("peggy's X: " + peggyPositionX + " \n peggy's Y: " + peggyPositionY, 400, 20);
		g.drawString("shiftX " + shiftX + "\n shiftY: " + shiftY, 200, 20);
		
		dripSound.play(1, (float) .15);
		
		if(quit == true){
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if(quit == false){
				g.clear();
			}
		}
		}
		
		if(transition)
		{
			String[] greeting = {"E", "s", "c", "a", "p", "e", " ", "t", "h", "e", " " ,"a", "q", "u", "a", "r", "i", "u", "m", " "};
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
//	        		 enter3d = true;
	        		 sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
	        	 }
			}
		
		
		if (winner) {
			g.drawString("Congratulations! \n You found an oddly shaped structure \n with some very interesting creatures \n inside.", 300, 100);
		}
//if(shiftX > 500) {
//			
//			converse.draw(peggyPositionX + 1000, peggyPositionY + 300);
//			talkedToFemale = true;
//			
//			if(talkedToFemale && !talkedToFemaleTwice)			
//			g.drawString(" 'c' for conversation ", peggyPositionX + 100, peggyPositionY + 275);
//			
//			if(!YouHaveMetFemale)
//			{
//				if(input.isKeyDown(Input.KEY_C))
//				{
//					//talkedToWilly = false;
//					talkedToFemaleTwice = true;
//				}
//			}
//			
//			else if(input.isKeyDown(Input.KEY_C))
//				{
//					//talkedToWilly = false;
//				talkedToFemaleThrice = true;
//				}
//			
//				
//				if(talkedToFemaleTwice && !YouHaveMetFemale)
//				{
//					String[] greeting = {"T", "h", "a", "t", " ", "s", "e", "c", "u", "r", "i", "t", "y", " ", "g", "u","a", "r", "d", "." ,".", ".", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
//					String[] greeting2 = {"T", "h", "e", " ", "l", "a", "z", "y", " ", "o", "n", "e", " ", ".", ".", ".", " ", "P", "l", "e", "a", "s", "e", " ", " ", " ", "(", "c", ")", " "};
//					int xCoord = (int) (peggyPositionX + 75);
//					int yCoord = (int) (peggyPositionY + 275);
//					int xCoord1 = (int) (peggyPositionX + 75);
//					int yCoord1 = (int) (peggyPositionY + 290);
//					int timerIncrement = 1000;
//					int letterCounter = 0;
//					
//					int timerIncrement2 = 1000;
//					int letterCounter2 = 0;
//		        
//		        	for(int i = 0; i < greeting.length; i++)
//		        	{		
//		        		counter++;	        	
//		        		if(counter > timerIncrement)
//		        		{		        			
//		        		g.drawString(greeting[letterCounter], xCoord, yCoord);
//		        		xCoord = xCoord + 10;
//		        		timerIncrement += 1500;		        		
//		        			letterCounter ++;
//		        		}
//		        	}
//		        	
//		        	if(letterCounter >= greeting.length) 
//		        	{
//
//		        		for(int i = 0; i < greeting2.length; i++)
//		        		{		
//		        			counter2++;	        	
//		        			if(counter2 > timerIncrement2)
//		        			{		        			
//		        				g.drawString(greeting2[letterCounter2], xCoord1, yCoord1);
//		        				xCoord1 = xCoord1 + 10;
//		        				timerIncrement2 += 1500;		        		
//		        				letterCounter2 ++;
//		        			}
//		        		}
//		        	}
//		        	 if(letterCounter2 >= greeting2.length && input.isKeyDown(Input.KEY_C))
//		        	 {
//		        		 YouHaveMetFemale = true;
//		        	 }
//		        }
//			
//				if(talkedToFemaleThrice)
//				{
//					String[] greeting = {"P", "l", "e", "a", "s", "e", " ", "m", "a", "k", "e", " ", "s", "u", "r", "e", " ", "y", "o","u", " ", "s", "l" ,"a", "p", " ", "h", "i", "m", ".", ".", ".", " ", " ", " ", " "};
//					String[] greeting2 = {"R", "e", "a", "l", "l", "y", " ", "h", "a", "r", "d", " ", "w", "i", "t", "h", " ", "B", " ", " ", " ", " ", };
//					String[] greeting3 = {"Y", "o", "u", " ", "l", "e", "a", "r", "n", "e", "d", " ", "S", "u", "p", "e", "r", "-", "s", "l", "a", "p", "(", "B", ")", " ", "f", "r", "o", "m", " ", "C", "r", "o", "n", "k", " ", " ", " "};
//					int xCoord = (int) (peggyPositionX + 72);
//					int yCoord = (int) (peggyPositionY + 260);
//					int xCoord1 = (int) (peggyPositionX + 72);
//					int yCoord1 = (int) (peggyPositionY + 275);
//					int xCoord2 = (int) (peggyPositionX + 72);
//					int yCoord2 = (int) (peggyPositionY + 305);
//					int timerIncrement = 1000;
//					int letterCounter = 0;
//					
//					int timerIncrement2 = 1000;
//					int letterCounter2 = 0;
//					
//					int timerIncrement3 = 10000;
//					int letterCounter3 = 0;
//		        
//		        	for(int i = 0; i < greeting.length; i++)
//		        	{		
//		        		counter3++;	        	
//		        		if(counter3 > timerIncrement)
//		        		{		        			
//		        		g.drawString(greeting[letterCounter], xCoord, yCoord);
//		        		xCoord = xCoord + 10;
//		        		timerIncrement += 1300;		        		
//		        			letterCounter ++;
//		        		}
//		        	}
//		        	
//		        	if(letterCounter >= greeting.length) 
//		        	{
//
//		        		for(int i = 0; i < greeting2.length; i++)
//		        		{		
//		        			counter4++;	        	
//		        			if(counter4 > timerIncrement2)
//		        			{		        			
//		        				g.drawString(greeting2[letterCounter2], xCoord1, yCoord1);
//		        				xCoord1 = xCoord1 + 10;
//		        				timerIncrement2 += 1800;		        		
//		        				letterCounter2 ++;
//		        			}
//		        		}
//		        	}
//		        	
//		        	if(letterCounter2 >= greeting2.length) 
//		        	{
//
//		        		for(int i = 0; i < greeting3.length; i++)
//		        		{		
//		        			counter5++;	        	
//		        			if(counter5 > timerIncrement3)
//		        			{		        			
//		        				g.drawString(greeting3[letterCounter3], xCoord2, yCoord2);
//		        				xCoord2 = xCoord2 + 10;
//		        				timerIncrement3 += 3500;		        		
//		        				letterCounter3 ++;
//		        			}
//		        		}
//		        		
//		        		SlapIsUnlocked = true;
//		        	}
//				}
//				
//		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
		Input input = gc.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		System.out.println("x coordinate: " + posX + "y coordinate: " + posY);
		
		if(input.isKeyDown(Input.KEY_UP)){
			peggy = movingUp;
			peggyPositionY += delta* .18f;
			if(peggyPositionY > 101)
				peggyPositionY = 101;
		
			
			
			if(shiftY > 350)
				peggyPositionY = -288;
			if(shiftY > 350)
				shiftY -= delta* .18f;
			
			//collisions
			if(shiftX >= -90 && shiftX < 190 && peggyPositionY >= -190)
				peggyPositionY = -190;
			if(shiftX >= 190 && shiftX < 300 && peggyPositionY >= -130)
				peggyPositionY = -130;
			if(peggyPositionX >= -140 && peggyPositionX < -38 && shiftY < 400)
				shiftY = 400;
			if(peggyPositionX < -140 && shiftY <= 450)
				shiftY = 450;
			
			
			
//			if(shiftY > 350)
//				shiftY -= delta* .18f;
//			if(shiftY > 350)
//				peggyPositionY = -20;
			
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			peggy = movingDown;
			peggyPositionY -= delta* .18f;
			if(peggyPositionY < -288)
				peggyPositionY = -288;
			if(peggyPositionY <= -288)
				shiftY += delta* .18f;
			if(shiftY >= 520)
				shiftY = 520;
			
//			if(peggyPositionY <= -20)
//				shiftY += delta* .18f;
//			if(shiftY >= 525)
//				shiftY = 525;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			peggy = movingLeft;
			peggyPositionX += delta* .18f;
			if(peggyPositionX > 0)
				peggyPositionX = 0;
			if(peggyPositionX >= 0)
				shiftX -= delta* .18f;
			if(shiftX <= -90)
				shiftX = -90;
			if(shiftX > 351)
				shiftX -= delta* .18f;
			if(shiftX > 351)
				peggyPositionX = -592;
			
			
			//collisions
			if(peggyPositionY > -190 && peggyPositionY <= 130 && shiftX <= 190)
				shiftX = 190;
			if(peggyPositionY > -130 && shiftX <= 300)
				shiftX = 300;
			
			
//			if(peggyPositionX >= 0)
//				shiftX -= delta* .18f;
//			if(shiftX <= -100)
//				shiftX = -100;
//			
//			if(shiftX > 351)
//				peggyPositionX = -600;
//			if(shiftX > 351)
//				shiftX -=delta* .18f;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			peggy = movingRight;
			peggyPositionX -= delta* .18f;
			if(peggyPositionX < -592)
				peggyPositionX = -592;
			if(peggyPositionX <= -592)
				shiftX += delta* .18f;
			if(shiftX >= 780)
				shiftX = 780;
			
			if(shiftX < 349)
				shiftX += delta* .18f;
			if(shiftX < 349)
				peggyPositionX = 0;
			
			//collisions
			if(shiftY < 400 && peggyPositionY <= 210 && peggyPositionX <= -38)
				peggyPositionX = -38;
			if(shiftY >= 400 && shiftY < 450 && peggyPositionX <= -140)
				peggyPositionX = -140;
				
//			if(shiftX >= 780)
//				shiftX = 780;
//			if(peggyPositionX <= -600)
//				shiftX += delta* .18f;
//			
//			
//			if(shiftX < 350)
//				peggyPositionX = 0;
//			if(shiftX < 350)
//				shiftX += delta* .18f;
		}
		
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			quit = true;
		}
		
		if(peggyPositionY > -50)
		{
			enter3d = true;
//			transition = true;
		}
		
		if(shiftX > 500)
			female = femaleShy;
		
		if(enter3d)
		{
		//	ThreeDeeGame.player.setPosition(40, 50);
			MainThreeDee.endGL = true;
			MainThreeDee2.quit = false;
			MainThreeDee2.captured = false;
			MainThreeDee2.timeRunning = false;
			ThreeDeeGame2.player.setPosition(35, 3);
			transition = true;
//			ThreeDeeGame.aquariumLevel = true;
			
// 			sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
// 			ThreeDeeGame.player.setPosition(40, 4);
 			
		}
		
			
		
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
		return 4;
	}
}
