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

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play2 extends BasicGameState{
	
	Game game;
	
	Animation sadPax, sadPaxRight, sadPaxLeft, peggy, cronk, pongo, movingUp, movingDown, movingLeft, movingRight, celebrate, pongoWalking, scared, converse, converse2, conversation, bitchSlap, smirk;
	Image worldMap;
	Image worldMap2;
	Image igloo;
	
	Sound clickSound;
	Sound slap;
	Sound whip;
	Music playMusic;
	
	boolean quit = false;
	boolean winner = false;
	boolean afterTransition = false;
	
	boolean SlapIsUnlocked = false;
	
	boolean talkedToGrump = false;
	boolean talkedToGrumpTwice = false;
	boolean talkedToGrumpThrice = false;
	boolean YouHaveMetGrump = false;
	
	boolean talkedToCronk = false;
	boolean talkedToCronkTwice = false;
	boolean talkedToCronkThrice = false;
	boolean YouHaveMetCronk = false;
	
	boolean heMovedLeft = false;
	boolean heMovedRight = false; 
	boolean heMovedDown = false;
	boolean isMoving = false;
	
    private int punchCounter = 0;
	
	int slapCounter = 0;
	boolean smack = false;
	boolean smackGuard = false;
	
	private int counter = 0;
	private int counter2 = 0;
	private int counter3 = 0;
	private int counter4 = 0;
	private int counter5 = 0;
	private int counter6 = 0;
	private int counter7 = 0;
	
	
	private int cronkCounter = 0;
	private int cronkCounter2 = 0;
	private int cronkCounter3 = 0;
	private int cronkCounter4 = 0;
	private int cronkCounter5 = 0;
	
	
	int[] duration = {200, 200};
	int[] duration3 = {200, 200, 200};
	int[] crazyDuration = {150, 150, 150, 150, 150, 150, 150, 150};
	int[] conversationDuration = {250, 300, 300, 10000};
	int[] bitchSlapDuration = {100, 100, 100, 1, 225, 150};
	
	float peggyPositionX = - 0;
	float peggyPositionY = - 0;
	
	float shiftX = peggyPositionX + 350;
	float shiftY = peggyPositionY + 350;
	
	float iglooPositionX = peggyPositionX + 400;
	float iglooPositionY = peggyPositionY + 400;
	
	public Play2(int state){
		
		
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		clickSound = new Sound("res/sounds/clickSound.wav");
		playMusic = new Music("res/sounds/Clutter.wav");
		
		slap = new Sound("res/sounds/slap-oof.wav");
		whip = new Sound("res/sounds/Whip.wav");
		
		worldMap = new Image("res/world4.png");
		worldMap2 = new Image("res/world4FlippedVertical.png");
		igloo = new Image("res/igloo.png");
		Image[] walkUp = {new Image("res/walkingUp2.png"), new Image("res/walkingUp3.png")};
		Image[] walkDown = {new Image("res/player.png"), new Image("res/walkingDown2.png")};
		Image[] walkLeft = {new Image("res/playerLeft.png"), new Image("res/walkingLeft2.png")};
		Image[] walkRight = {new Image("res/playerRight.png"), new Image("res/walkingRight2.png")};
		Image[] highFive = {new Image("res/penguinDance1.png"), new Image("res/penguinDance2.png")};
		
		Image[] sadPaxDown = {new Image("res/sadPaxDown.png"), new Image("res/sadPaxDown.png")};
		Image[] mopeyPaxLeft = {new Image("res/sadPaxLeft.png"), new Image("res/sadPaxLeft.png")};
		Image[] mopeyPaxRight = {new Image("res/sadPaxRight.png"), new Image("res/sadPaxRight.png")};
		
		Image[] penguinSlap = {new Image("res/bitchSlap1.png"), new Image("res/bitchSlap2.png"), new Image("res/bitchSlap3.png"), new Image("res/LastLevelPlayerRight.png"), new Image("res/playerUp.png"), new Image("res/iconic.png")};
		Image[] smirking = {new Image("res/iconic.png"), new Image("res/iconic.png")};
		
		Image[] pongoWalk = {new Image("res/pongo10.png"), new Image("res/pongo9.png")};
		
		Image[] crazyWalk = {new Image("res/crazyAssPenguin.png"), new Image("res/crazyAssPenguin2.png"), new Image("res/crazyAssPenguin3.png"), new Image("res/crazyAssPenguin4.png"), new Image("res/crazyAssPenguin5.png"), new Image("res/crazyAssPenguin6.png"), new Image("res/crazyAssPenguin7.png"), new Image("res/crazyAssPenguin8.png")};
		
		Image[] textBox = {new Image("res/textBox1.png"), new Image("res/textBox2.png"), new Image("res/textBox3.png"), new Image("res/textBox6.png")};
		Image[] textBox2 = {new Image("res/textBox1.png"), new Image("res/textBox2.png"), new Image("res/textBox3.png"), new Image("res/textBox7.png")};
		
		movingUp = new Animation(walkUp, duration, true);
		movingDown = new Animation(walkDown, duration, true);
		movingLeft = new Animation(walkLeft, duration, true);
		movingRight = new Animation(walkRight, duration, true);
		pongoWalking = new Animation(pongoWalk, duration, true);
		celebrate = new Animation(highFive, duration, false);
		scared = new Animation(crazyWalk, crazyDuration, true);
		
		sadPax = new Animation(sadPaxDown, duration, false);
		sadPaxRight = new Animation(mopeyPaxRight, duration, false);
		sadPaxLeft = new Animation(mopeyPaxLeft, duration, false);
		
		bitchSlap = new Animation(penguinSlap, bitchSlapDuration, true);
		smirk = new Animation(smirking, duration, false);
		
		converse = new Animation(textBox, conversationDuration, true);
		converse2 = new Animation(textBox, conversationDuration, true);
		
		peggy = sadPax;
		pongo = pongoWalking;
		cronk = scared;
		conversation = converse;
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		Input input = gc.getInput();
		
		if(afterTransition)
		{
			
//		igloo.draw(iglooPositionX, iglooPositionY);
		worldMap.draw(peggyPositionX, peggyPositionY);
		worldMap2.draw(peggyPositionX, peggyPositionY - 720);
		peggy.draw(shiftX, shiftY);
		pongo.draw(peggyPositionX + 1300, peggyPositionY + 525);
		cronk.draw(peggyPositionX + 100, peggyPositionY + 325);
		
//		g.drawString("peggy's X: " + peggyPositionX + " \n peggy's Y: " + peggyPositionY, 400, 20);
//		g.drawString("shiftX " + shiftX + "\n shiftY: " + shiftY, 200, 20);
		
			if(quit == true)
				{
					g.drawString("Resume (R)", 250, 100);
					g.drawString("Main Menu (M)", 250, 150);
					g.drawString("Quit Game (Q)", 250, 200);
					if(quit == false)
					{
						g.clear();
					}
				}
		}
		
		if(!afterTransition)
		{
			String[] greeting = {"S", "o", "m", "e", "w", "h", "e", "r", "e", " ", "." ,".", ".", " ", "m", "u", "c", "h", " ", "w", "a", "r", "m", "e", "r", " "};
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
	        	
	        		 afterTransition = true;
	        	 }
			}
		
		if(shiftX < -50) {
			
			converse.draw(peggyPositionX , peggyPositionY + 50);
			talkedToCronk = true;
			
			if(talkedToCronk && !talkedToCronkTwice)			
			g.drawString(" 'c' for conversation ", peggyPositionX + 100, peggyPositionY + 275);
			
			if(!YouHaveMetCronk)
			{
				if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToCronkTwice = true;
				}
			}
			
			else if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToCronkThrice = true;
				}
			
				
				if(talkedToCronkTwice && !YouHaveMetCronk)
				{
					String[] greeting = {"T", "h", "a", "t", " ", "s", "e", "c", "u", "r", "i", "t", "y", " ", "g", "u","a", "r", "d", "." ,".", ".", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
					String[] greeting2 = {"T", "h", "e", " ", "l", "a", "z", "y", " ", "o", "n", "e", " ", ".", ".", ".", " ", "P", "l", "e", "a", "s", "e", " ", " ", " ", "(", "c", ")", " "};
					int xCoord = (int) (peggyPositionX + 75);
					int yCoord = (int) (peggyPositionY + 275);
					int xCoord1 = (int) (peggyPositionX + 75);
					int yCoord1 = (int) (peggyPositionY + 290);
					int timerIncrement = 1000;
					int letterCounter = 0;
					
					int timerIncrement2 = 1000;
					int letterCounter2 = 0;
		        
		        	for(int i = 0; i < greeting.length; i++)
		        	{		
		        		cronkCounter++;	        	
		        		if(cronkCounter > timerIncrement)
		        		{		        			
		        		g.drawString(greeting[letterCounter], xCoord, yCoord);
		        		xCoord = xCoord + 10;
		        		timerIncrement += 1500;		        		
		        			letterCounter ++;
		        		}
		        	}
		        	
		        	if(letterCounter >= greeting.length) 
		        	{

		        		for(int i = 0; i < greeting2.length; i++)
		        		{		
		        			cronkCounter2++;	        	
		        			if(cronkCounter2 > timerIncrement2)
		        			{		        			
		        				g.drawString(greeting2[letterCounter2], xCoord1, yCoord1);
		        				xCoord1 = xCoord1 + 10;
		        				timerIncrement2 += 1500;		        		
		        				letterCounter2 ++;
		        			}
		        		}
		        	}
		        	 if(letterCounter2 >= greeting2.length && input.isKeyDown(Input.KEY_C))
		        	 {
		        		 YouHaveMetCronk = true;
		        	 }
		        }
			
				if(talkedToCronkThrice)
				{
					String[] greeting = {"P", "l", "e", "a", "s", "e", " ", "m", "a", "k", "e", " ", "s", "u", "r", "e", " ", "y", "o","u", " ", "s", "l" ,"a", "p", " ", "h", "i", "m", ".", ".", ".", " ", " ", " ", " "};
					String[] greeting2 = {"R", "e", "a", "l", "l", "y", " ", "h", "a", "r", "d", " ", "w", "i", "t", "h", " ", "B", " ", " ", " ", " ", };
					String[] greeting3 = {"Y", "o", "u", " ", "l", "e", "a", "r", "n", "e", "d", " ", "S", "u", "p", "e", "r", "-", "s", "l", "a", "p", "(", "B", ")", " ", "f", "r", "o", "m", " ", "C", "r", "o", "n", "k", " ", " ", " "};
					int xCoord = (int) (peggyPositionX + 72);
					int yCoord = (int) (peggyPositionY + 260);
					int xCoord1 = (int) (peggyPositionX + 72);
					int yCoord1 = (int) (peggyPositionY + 275);
					int xCoord2 = (int) (peggyPositionX + 72);
					int yCoord2 = (int) (peggyPositionY + 305);
					int timerIncrement = 1000;
					int letterCounter = 0;
					
					int timerIncrement2 = 1000;
					int letterCounter2 = 0;
					
					int timerIncrement3 = 10000;
					int letterCounter3 = 0;
		        
		        	for(int i = 0; i < greeting.length; i++)
		        	{		
		        		cronkCounter3++;	        	
		        		if(cronkCounter3 > timerIncrement)
		        		{		        			
		        		g.drawString(greeting[letterCounter], xCoord, yCoord);
		        		xCoord = xCoord + 10;
		        		timerIncrement += 1300;		        		
		        			letterCounter ++;
		        		}
		        	}
		        	
		        	if(letterCounter >= greeting.length) 
		        	{

		        		for(int i = 0; i < greeting2.length; i++)
		        		{		
		        			cronkCounter4++;	        	
		        			if(cronkCounter4 > timerIncrement2)
		        			{		        			
		        				g.drawString(greeting2[letterCounter2], xCoord1, yCoord1);
		        				xCoord1 = xCoord1 + 10;
		        				timerIncrement2 += 1800;		        		
		        				letterCounter2 ++;
		        			}
		        		}
		        	}
		        	
		        	if(letterCounter2 >= greeting2.length) 
		        	{

		        		for(int i = 0; i < greeting3.length; i++)
		        		{		
		        			cronkCounter5++;	        	
		        			if(cronkCounter5 > timerIncrement3)
		        			{		        			
		        				g.drawString(greeting3[letterCounter3], xCoord2, yCoord2);
		        				xCoord2 = xCoord2 + 10;
		        				timerIncrement3 += 3500;		        		
		        				letterCounter3 ++;
		        			}
		        		}
		        		
		        		SlapIsUnlocked = true;
		        	}
				}
				
		}
		
		if (shiftX > 500)
		{
			
			converse2.draw(peggyPositionX + 850, peggyPositionY + 180);
			talkedToGrump = true;
			
			if(talkedToGrump && !talkedToGrumpTwice)			
			g.drawString(" Hey, Don't worry there big guy. " + " \n Aquarium life isn't so bad" + " \n \n (c to continue) ", peggyPositionX + 930, peggyPositionY + 375);			
			
			
			if(!YouHaveMetGrump)
			{
				if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToGrumpTwice = true;
				}
			}
			
			else if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToGrumpThrice = true;
				}
			
				
				if(talkedToGrumpTwice && !YouHaveMetGrump)
				{
					String[] greeting = {"h", "a", " ", "J", "u", "s", "t", " ", "K", "i", "d", "d", "i", "n", "g"," ", "I", "t", " " ,"R", "E", "A", "L", "L", "Y", " ", "b", "l", "o", "w", "s" };
					String[] greeting2 = {"b", "e", "i", "n", "g", " ", "s", "t", "u", "c", "k", " ", "i", "n", " ", "t", "h", "i", "s", " ", "j", "o", "i", "n", "t", " "};
					int xCoord = (int) (peggyPositionX + 930);
					int yCoord = (int) (peggyPositionY + 375);
					int xCoord1 = (int) (peggyPositionX + 930);
					int yCoord1 = (int) (peggyPositionY + 390);
					int timerIncrement = 1000;
					int letterCounter = 0;
					
					int timerIncrement2 = 1000;
					int letterCounter2 = 0;
		        
		        	for(int i = 0; i < greeting.length; i++)
		        	{		
		        		counter2++;	        	
		        		if(counter2 > timerIncrement)
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
		        			counter3++;	        	
		        			if(counter3 > timerIncrement2)
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
		        		 YouHaveMetGrump = true;
		        	 }
		        }
				
				if(talkedToGrumpThrice)
				{
					String[] greeting = {"T", "h", "a", "t", "'", "s", " ", "c", "r", "a", "z", "y", " ", "C", "r", "o", "n", "k", " ","o", "v", "e", "r" ," ", "t", "h", "e", "r", "e", ".", " ", " "};
					String[] greeting2 = { "H", "e", " ", "a", "l", "m", "o", "s", "t", " ", "b", "u", "s", "t", "e", "d", " ", "o", "u", "t", " ", "o", "n", "e", " ", "t", "i", "m", "e", ".", " "};
					String[] greeting3 = {"P", "o", "o", "r", " ", "g", "u", "y", " ", "w", "a", "s", " ", "n", "o", " ", "m", "a", "t", "c", "h", " ", "f", "o", "r", " ", "t", "h", "e", " ", "b", "a", "r", "b", "e", "d"};
					String[] greeting4 = {"w", "i", "r", "e", " ", ".", ".", ".", "h", "a", "s", "n", "'", "t", " ", "b", "e", "e", "n", " ", "t", "h", "e", " ", "s", "a", "m", "e", " ", "s", "i", "n", "c", "e", " "};
					int xCoord = (int) (peggyPositionX + 930);
					int yCoord = (int) (peggyPositionY + 375);
					int xCoord1 = (int) (peggyPositionX + 930);
					int yCoord1 = (int) (peggyPositionY + 390);
					int xCoord2 = (int) (peggyPositionX + 930);
					int yCoord2 = (int) (peggyPositionY + 405);
					int xCoord3 = (int) (peggyPositionX + 930);
					int yCoord3 = (int) (peggyPositionY + 420); 
					int GrumpTimerIncrement = 1000;
					int letterCounter = 0;
					
					int timerIncrement2 = 25000;
					int letterCounter2 = 0;
					
					int timerIncrement3 = 20000;
					int letterCounter3 = 0;
					
					int timerIncrement4 = 18000;
					int letterCounter4 = 0;
		        
		        	for(int i = 0; i < greeting.length; i++)
		        	{		
		        		counter4++;	        	
		        		if(counter4 > GrumpTimerIncrement)
		        		{		        			
		        		g.drawString(greeting[letterCounter], xCoord, yCoord);
		        		xCoord = xCoord + 10;
		        		GrumpTimerIncrement += 2000;		        		
		        			letterCounter ++;
		        		}
		        	}
		        	
		        	if(letterCounter >= greeting.length) 
		        	{

		        		for(int i = 0; i < greeting2.length; i++)
		        		{		
		        			counter5++;	        	
		        			if(counter5 > timerIncrement2)
		        			{		        			
		        				g.drawString(greeting2[letterCounter2], xCoord1, yCoord1);
		        				xCoord1 = xCoord1 + 10;
		        				timerIncrement2 += 2000;		        		
		        				letterCounter2 ++;
		        			}
		        		}
		        	}
		        	
		        	if(letterCounter2 >= greeting2.length) 
		        	{

		        		for(int i = 0; i < greeting3.length; i++)
		        		{		
		        			counter6++;	        	
		        			if(counter6 > timerIncrement3)
		        			{		        			
		        				g.drawString(greeting3[letterCounter3], xCoord2, yCoord2);
		        				xCoord2 = xCoord2 + 10;
		        				timerIncrement3 += 2000;		        		
		        				letterCounter3 ++;
		        			}
		        		}
		        	}
		        	
		        	if(letterCounter3 >= greeting3.length) 
		        	{

		        		for(int i = 0; i < greeting4.length; i++)
		        		{		
		        			counter7++;	        	
		        			if(counter7 > timerIncrement4)
		        			{		        			
		        				g.drawString(greeting4[letterCounter4], xCoord3, yCoord3);
		        				xCoord3 = xCoord3 + 10;
		        				timerIncrement4 += 2400;		        		
		        				letterCounter4 ++;
		        			}
		        		}
		        	}
				}
				
		}
		
	
				
		

		
		
		if(peggyPositionX <= -300 && peggyPositionY == 70)
			g.drawString("E to enter cave", 350, 300);
		if(peggyPositionX <= -300 && peggyPositionY == 70 && input.isKeyDown(Input.KEY_E))
			sbg.enterState(4);
		
		if (winner) {
//			g.drawString("Congratulations! \n You found an oddly shaped structure \n with some very interesting creatures \n inside.", 300, 100);
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
		Input input = gc.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		System.out.println("x coordinate: " + posX + "  y coordinate: " + posY);
		System.out.println("Pax's X: " + peggyPositionX + "  Pax's Y: " + peggyPositionY);
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			peggy = movingUp;
			peggyPositionY += delta* .18f;
			if(peggyPositionY > 101)
				peggyPositionY = 101;	
			
			if(shiftY > 350)
				shiftY -= delta* .18f;
			if(shiftY > 350)
				peggyPositionY = -20;
			
			//collisions
			if(shiftX < 781 && shiftX > 540 && shiftY <= 444)
				shiftY = 444;
			if(peggyPositionX <= -270 && shiftX <= 540 && peggyPositionX >= -600 && shiftY <= 390 && peggyPositionY <= -20)
			shiftY = 390;
			if(peggyPositionX <= -100 && peggyPositionX >= -430 && peggyPositionY >= 70)
				peggyPositionY = 70;
			if(peggyPositionX > -100 && peggyPositionY > 30)
				peggyPositionY = 30;
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			peggy = movingDown;
			
			peggyPositionY -= delta* .18f;
			if(peggyPositionY <= -20)
				peggyPositionY = -20;
			if(peggyPositionY <= -20)
				shiftY += delta* .18f;
			if(shiftY >= 525)
				shiftY = 525;
			
			if(peggyPositionX >= -430 && peggyPositionX < -260 && peggyPositionY < 20 && peggyPositionY > 19)
				peggyPositionY = 20;		
		}
		
		if ((peggy == movingDown) && (!input.isKeyDown(Input.KEY_DOWN)))
			peggy = sadPax;
		
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			peggy = movingLeft;
			peggyPositionX += delta* .18f;
			if(peggyPositionX > 0)
				peggyPositionX = 0;
			if(peggyPositionX >= 0)
				shiftX -= delta* .18f;
			if(shiftX <= -100)
				shiftX = -100;
			
			if(shiftX > 351)
				peggyPositionX = -600;
			if(shiftX > 351)
				shiftX -=delta* .18f;
			
			//collision
			if(peggyPositionY <= 70 && peggyPositionY > 30 && peggyPositionX >= -100)
				peggyPositionX = -100;
		}
		
		if ((peggy == movingLeft) && (!input.isKeyDown(Input.KEY_LEFT)))
			peggy = sadPaxLeft;
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			peggy = movingRight;
			peggyPositionX -= delta* .18f;
			if(peggyPositionX <= -600)
				peggyPositionX = -600;
			if(shiftX >= 780)
				shiftX = 780;
			if(peggyPositionX <= -600)
				shiftX += delta* .18f;
			
			
			if(shiftX < 350)
				peggyPositionX = 0;
			if(shiftX < 350)
				shiftX += delta* .18f;
			
			//collisions
			if(shiftY < 444 && shiftY >= 390 && shiftX >= 540)
				shiftX = 540;
			if(shiftY < 390 && shiftY > 349 && peggyPositionY <= 20 && peggyPositionX <= -260 && peggyPositionX >= -261)
				peggyPositionX = -260;
			if(peggyPositionY <= 70 && peggyPositionY >= 20 && peggyPositionX <= -430)
				peggyPositionX = -430;	
		}
		
		if ((peggy == movingRight) && (!input.isKeyDown(Input.KEY_RIGHT)))
			peggy = sadPaxRight;
		
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			quit = true;
		}
		
		
		if(SlapIsUnlocked)
		{
		
			if(input.isKeyDown(Input.KEY_B)){
				peggy = bitchSlap;
				smack = true;
			}
		
			if(input.isKeyDown(Input.KEY_B) && peggyPositionX < -1500){
				peggy = bitchSlap;
				smackGuard = true;
				punchCounter += 1;
			}
		
			if(smackGuard){
				counter++;
			
				if( counter > 50)
					slap.play();
				if(counter > 60)
					slap.stop();
			}
		
			if(smack){
				counter++;
			
				if( counter > 50)
					whip.play();
				if(counter > 60)
					whip.stop();
			}
		
		
			if (smack && (!input.isKeyDown(Input.KEY_B)))
			{
				peggy = movingDown;
			
				if(counter > 100)
				{
					counter  = 0;
					smack = false;
				}
			
			}
			
		}
		
		
			
		
		//menu is up
		if(quit == true)
		{
			if(input.isKeyDown(Input.KEY_R))
				quit = false;
			if(input.isKeyDown(Input.KEY_M))
				sbg.enterState(0);
			if(input.isKeyDown(Input.KEY_Q))
				System.exit(0);	
		}
		
		
	}
	
	public int getID(){
		return 3;
	}
}