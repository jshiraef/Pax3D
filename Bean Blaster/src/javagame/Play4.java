
package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Play4 extends BasicGameState{
	
	Animation peggy, movingUp, movingDown, movingLeft, movingRight, celebrate, guard, securityGuard, converse, conversation, bitchSlap, smirk, bleeding;
	Image worldMap;
	Image worldMap2;
	Image igloo;
	Image textbox;
	
	Sound slap;
	Sound whip;
	
	boolean quit = false;
	boolean winner = false;
	
	boolean talkedToSign = false;
	boolean talkedToSignTwice = false;
	boolean talkedToSignThrice = false;
	boolean YouHaveMetSign = false;
	
	private int counter = 0;
	private int counter2 = 0;
	private int counter3 = 0;
	private int counter4 = 0;
	private int counter5 = 0;
	
	private int punchCounter = 0;
	
	int slapCounter = 0;
	boolean smack = false;
	boolean smackGuard = false;
	
	int[] duration = {200, 200};
	int[] securityGuardDuration = {170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170};
	int[] conversationDuration = {250, 300, 300, 100000};
	int[] bitchSlapDuration = {100, 100, 100, 1, 225, 150};
	
	float peggyPositionX = 0;
	float peggyPositionY = - 50;
	
	float shiftX = peggyPositionX + 150;
	float shiftY = peggyPositionY + 175;
	
	float iglooPositionX = peggyPositionX + 400;
	float iglooPositionY = peggyPositionY + 400;
	
	public Play4(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		
		slap = new Sound("res/sounds/slap-oof.wav");
		whip = new Sound("res/sounds/Whip.wav");
		
		worldMap = new Image("res/UtcMap1.png");
		worldMap2 = new Image("res/UtcMap2.png");
//		igloo = new Image("res/igloo.png");
		textbox = new Image("res/textbox2.png");
		
		 
		Image[] walkUp = {new Image("res/LastLevelWalkingUp2.png"), new Image("res/LastLevelWalkingUp3.png")};
		Image[] walkDown = {new Image("res/LastLevelplayer.png"), new Image("res/LastLevelWalkingDown2.png")};
		Image[] walkLeft = {new Image("res/LastLevelPlayerLeft.png"), new Image("res/LastLevelWalkingLeft2.png")};
		Image[] walkRight = {new Image("res/LastLevelPlayerRight.png"), new Image("res/LastLevelWalkingRight2.png")};
		Image[] highFive = {new Image("res/penguinDance1.png"), new Image("res/penguinDance2.png")};
		Image[] lazyAss = {new Image("res/Guard/securityGuard1.png"), new Image("res/Guard/securityGuard2.png"), new Image("res/Guard/securityGuard3.png"), new Image("res/Guard/securityGuard4.png"), new Image("res/Guard/securityGuard5.png"), new Image("res/Guard/securityGuard6.png"), new Image("res/Guard/securityGuard7.png"), new Image("res/Guard/securityGuard8.png"), new Image("res/Guard/securityGuard9.png"), new Image("res/Guard/securityGuard10.png"), new Image("res/Guard/securityGuard11.png"), new Image("res/Guard/securityGuard12.png")};
		Image[] textBox = {new Image("res/textBox1.png"), new Image("res/textBox2.png"), new Image("res/textBox3.png"), new Image("res/textBox5.png")};
		Image[] penguinSlap = {new Image("res/bitchSlap1.png"), new Image("res/bitchSlap2.png"), new Image("res/bitchSlap3.png"), new Image("res/LastLevelPlayerRight.png"), new Image("res/playerUp.png"), new Image("res/iconic.png")};
		Image[] smirking = {new Image("res/iconic.png"), new Image("res/iconic.png")};
		Image[] GuardBleeding = {new Image("res/Guard/securityGuardPunched1.png"), new Image("res/Guard/securityGuardPunched2.png"), new Image("res/Guard/securityGuardPunched3.png"), new Image("res/Guard/securityGuardPunched4.png"), new Image("res/Guard/securityGuardPunched5.png"), new Image("res/Guard/securityGuardPunched6.png"), new Image("res/Guard/securityGuardPunched7.png"), new Image("res/Guard/securityGuardPunched8.png"), new Image("res/Guard/securityGuardPunched9.png"), new Image("res/Guard/securityGuardPunched10.png"), new Image("res/Guard/securityGuardPunched11.png"), new Image("res/Guard/securityGuardPunched12.png")};
		
		
		movingUp = new Animation(walkUp, duration, true);
		movingDown = new Animation(walkDown, duration, true);
		movingLeft = new Animation(walkLeft, duration, true);
		movingRight = new Animation(walkRight, duration, true);
		celebrate = new Animation(highFive, duration, false);
		bitchSlap = new Animation(penguinSlap, bitchSlapDuration, true);
		smirk = new Animation(smirking, duration, false);
		
		
		converse = new Animation(textBox, conversationDuration, true);
		
		securityGuard = new Animation(lazyAss, securityGuardDuration, true);
		bleeding = new Animation(GuardBleeding, securityGuardDuration, true);
		
		peggy = movingDown;
		guard = securityGuard;
		conversation = converse;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input input = gc.getInput();
		
		//		igloo.draw(iglooPositionX, iglooPositionY);
		worldMap.draw(peggyPositionX, peggyPositionY);
		worldMap2.draw(peggyPositionX + 1553, peggyPositionY - 10);
		peggy.draw(shiftX, shiftY);
		guard.draw(peggyPositionX + 2254, peggyPositionY + 213);
		
//		g.drawString("peggy's X: " + peggyPositionX + " \n peggy's Y: " + peggyPositionY, 400, 300);
//		g.drawString("shift X: " + shiftX + " \n shift Y: " + shiftY, 200, 300);
		
		if(quit == true){
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if(quit == false){
				g.clear();
			}
		}
		
		if (winner) {
//			g.drawString("Congratulations! \n You found an oddly shaped structure \n with some very interesting creatures \n inside.", 300, 100);
		}
		
		if(shiftX > 80 && shiftX <250 && shiftY > 400)
		{
			g.drawString("hold r to read!", 200, 450);
//			textbox.draw(1600, 0);
		}
			
		
		if(shiftX > 80 && shiftX <250 && shiftY > 400 && input.isKeyDown(Input.KEY_R))
		{
			converse.draw(peggyPositionX = -25, peggyPositionY + 260);
			talkedToSign = true;
			
			if(talkedToSign && !talkedToSignTwice)			
			g.drawString(" Come one, Come all IEEE-CS meeting \n 2:00 AM ... \n at EMCS 302. " + " \n \n (c to continue) ", peggyPositionX + 100, peggyPositionY + 475);
			
			if(!YouHaveMetSign)
			{
				if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToSignTwice = true;
				}
			}
			else if(input.isKeyDown(Input.KEY_C))
				{
					//talkedToWilly = false;
					talkedToSignThrice = true;
				}
			
				
				if(talkedToSignTwice && !YouHaveMetSign)
				{
					String[] greeting = {"I", "f", " ", "y", "o", "u", " ", "s", "e", "e", " ", "a", "n", "y", " ","l", "a", "z", "y" ,"-", "a", "s", "s", " ", "s", "e", "c", "u", "r", "i", "t", "y", " ", "g", "u", "a", "r", "d", "s", ",", " ", " "};
					String[] greeting2 = {"y", "o", "u", " ", "c", "a", "n", " ", "g", "i", "v", "e", " ", "t", "h", "e", "m", " ", "a", " ", "n", "i", "c", "e", " ", "b", "i", "g", "-", "s", "l", "a", "p", " ", "w", "i", "t", "h", " ", "B", "!", " "};
					int xCoord = (int) (peggyPositionX + 100);
					int yCoord = (int) (peggyPositionY + 475);
					int xCoord1 = (int) (peggyPositionX + 100);
					int yCoord1 = (int) (peggyPositionY + 490);
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
		        		 YouHaveMetSign = true;
		        	 }
		        }
				
				if(talkedToSignThrice)
				{
					String[] greeting = {"Y", "o", "u", " ", "w", "o", "u", "l", "d", " ", "b", "e", " ", "d", "o", "i", "n", "g", " ","u", "s", " ", "a" ," ", "g", "r", "e", "a", "t", " ", "j", "u", "s", "t", "i", "c", "e", "!", " ", "G", "o"};
					String[] greeting2 = {"a", "h", "e", "a", "d", " ", "g", "i", "v", "e", " ", "i", "t", " ", "a", " ", "s", "h", "o", "t", ".", " ", "G", "e", "t", " ", "p", "a", "y", "b", "a", "c", "k", " ", "f", "o", "r", " ", "t", "h", "o", "s", "e", " ", };
					String[] greeting3 = {"r", "i", "d", "i", "c", "u", "l", "o", "u", "s", " ", "p", "a", "r", "k", "i", "n", "g", " ", "t", "i", "c", "k", "e", "t", "s", " ", " "};
					int xCoord = (int) (peggyPositionX + 100);
					int yCoord = (int) (peggyPositionY + 475);
					int xCoord1 = (int) (peggyPositionX + 100);
					int yCoord1 = (int) (peggyPositionY + 490);
					int xCoord2 = (int) (peggyPositionX + 100);
					int yCoord2 = (int) (peggyPositionY + 505);
					int timerIncrement = 1000;
					int letterCounter = 0;
					
					int timerIncrement2 = 1000;
					int letterCounter2 = 0;
					
					int timerIncrement3 = 1000;
					int letterCounter3 = 0;
		        
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
		        	
		        	if(letterCounter2 >= greeting2.length) 
		        	{

		        		for(int i = 0; i < greeting3.length; i++)
		        		{		
		        			counter5++;	        	
		        			if(counter5 > timerIncrement3)
		        			{		        			
		        				g.drawString(greeting3[letterCounter3], xCoord2, yCoord2);
		        				xCoord2 = xCoord2 + 10;
		        				timerIncrement3 += 1000;		        		
		        				letterCounter3 ++;
		        			}
		        		}
		        	}
				}
				
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
		Input input = gc.getInput();
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		
		System.out.println("x coordinate: " + posX + "y coordinate: " + posY);
		
		if(input.isKeyDown(Input.KEY_UP)){
			peggy = movingUp;
			peggyPositionY += delta* .2f;
			if(peggyPositionY > 0)
				peggyPositionY = 0;
			if(peggyPositionY >= 0)
				shiftY -= delta* .2f;
			if(shiftY <= -50)
				shiftY = -50;
			
			if(shiftY > 126)
				shiftY -= delta* .2f;
			if(shiftY > 126)
				peggyPositionY = -205;
			
			
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			peggy = movingDown;
			peggyPositionY -= delta* .2f;
			if(peggyPositionY <= -205)
				peggyPositionY = -205;
			if(peggyPositionY <= -205)
				shiftY += delta* .2f;
			if(shiftY >= 550)
				shiftY = 550;
			if(peggyPositionY >= 0 && shiftY < 125)
				shiftY += delta* .2f;
			
			if(shiftY < 125)
				shiftY += delta* .2f;
			if(shiftY < 125)
				peggyPositionY = 0;
			
			
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			peggy = movingLeft;
			peggyPositionX += delta* .2f;
			if(peggyPositionX >= 0)
				peggyPositionX = 0;
			if(peggyPositionX >= 0)
				shiftX -= delta* .2f;
			if(shiftX <= -80)
				shiftX = -80;
			
			if(shiftX > 301)
				shiftX -= delta* .2f;
			if(shiftX > 301)
				peggyPositionX = -1678;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			peggy = movingRight;
			peggyPositionX -= delta* .2f;
			if(peggyPositionX < -1678)
				peggyPositionX = -1678;
			if(peggyPositionX <= -1678)
				shiftX += delta* .2f;
			if(shiftX >= 780)
				shiftX = 780;
			
			
			if(shiftX < 300)
				shiftX += delta* .2f;
			if(shiftX < 299)
				peggyPositionX = 0;
		}
		
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			quit = true;
		}
		
		if(input.isKeyDown(Input.KEY_B)){
			peggy = bitchSlap;
			smack = true;
		}
		
		if(input.isKeyDown(Input.KEY_B) && peggyPositionX < -1500){
			peggy = bitchSlap;
			smackGuard = true;
			punchCounter += 1;
		}
		
		if(punchCounter > 1000)
		{
			guard = bleeding;
		
		      if(punchCounter > 2000)
		    	  peggy = celebrate;
			  sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
		      Credits.endMusic.play();
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
		
//		if(counter > 100)
//			counter = 0;
		
//		if((peggyPositionX < -1084) && (peggyPositionY > -430) && (peggyPositionY < -305)){
//			winner = true;
//			peggy = celebrate;
//			sbg.enterState(2);
//		}
		
		
		
		
			
		
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
		return 6;
	}
}

