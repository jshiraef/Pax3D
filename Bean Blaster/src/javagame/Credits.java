
package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends BasicGameState{
	
	
	
	Animation peggy, movingUp, movingDown, movingLeft, movingRight, celebrate;
	Image worldMap;
	Image igloo;
	boolean quit = false;
	boolean winner = false;
	int[] duration = {200, 200};
	
	static Music endMusic;
	
	float peggyPositionX = 501;
	float peggyPositionY = 250;
	
	float shiftX = peggyPositionX + 350;
	float shiftY = peggyPositionY + 350;
	
	float iglooPositionX = peggyPositionX + 400;
	float iglooPositionY = peggyPositionY + 400;
	
	public Credits(int state){
		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{	
		endMusic = new Music("res/sounds/CanonJenkees.wav");
//		endMusic.play();
//		worldMap = new Image("res/world.png");
//		igloo = new Image("res/igloo.png");
//		Image[] walkUp = {new Image("res/playerUp.png"), new Image("res/playerUp.png")};
//		Image[] walkDown = {new Image("res/player.png"), new Image("res/player.png")};
//		Image[] walkLeft = {new Image("res/playerLeft.png"), new Image("res/playerLeft.png")};
//		Image[] walkRight = {new Image("res/playerRight.png"), new Image("res/playerRight.png")};
//		Image[] highFive = {new Image("res/penguinDance1.png"), new Image("res/penguinDance2.png")};
//		
//		movingUp = new Animation(walkUp, duration, false);
//		movingDown = new Animation(walkDown, duration, false);
//		movingLeft = new Animation(walkLeft, duration, false);
//		movingRight = new Animation(walkRight, duration, false);
//		celebrate = new Animation(highFive, duration, false);
//		
//		peggy = movingDown;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
//		igloo.draw(iglooPositionX, iglooPositionY);
//		worldMap.draw(peggyPositionX, peggyPositionY);
//		peggy.draw(shiftX, shiftY);
		
		
		
		g.drawString("Thanks so much for trying out the most awesome game demo ever made!", peggyPositionX - 400, peggyPositionY + 300);
		
		g.drawString("music from @ronaldJenkees", peggyPositionX - 200, peggyPositionY + 800);
		
//		g.drawString("Executive Producer: Joey Shiraef", peggyPositionX - 200, peggyPositionY + 1300);
	
		g.drawString("Game designer: Joey Shiraef", peggyPositionX - 200, peggyPositionY + 1800);
		g.drawString("Written by: Joey Shiraef", peggyPositionX - 200, peggyPositionY + 2300);
		
		g.drawString("Engine Programming: Joey Shiraef", peggyPositionX - 200, peggyPositionY+ 2800);
		
		g.drawString("Quality Assurance: ...?? Who do you think?? ", peggyPositionX - 200, peggyPositionY + 3300);
		
		g.drawString("Human Resources: ... Seriously?? ", peggyPositionX - 200, peggyPositionY + 3800);
		
		
		g.drawString("Distraction Assurance: Titus Bishop Shiraef", peggyPositionX - 200, peggyPositionY + 4300);
		
		g.drawString("Special thanks to: Your mother", peggyPositionX - 200, peggyPositionY + 4800);
		
		g.drawString("Testers: You, yourself, & your mother", peggyPositionX - 200, peggyPositionY + 5300);
		
		
		
		
		g.drawString("Check back soon for more updates.", peggyPositionX - 200, peggyPositionY + 5800);
		
		
		g.drawString("Coming soon to the Mocs Arcade Cabinet...", peggyPositionX - 200, peggyPositionY + 6300);
		g.drawString("Bean Blaster: episode II", peggyPositionX - 200, peggyPositionY + 6800);
		
		g.drawString("Are you still here???", peggyPositionX - 200, peggyPositionY + 9000);
		
		g.drawString("Shouldn't you be studying???", peggyPositionX - 200, peggyPositionY + 11000);
		
		g.drawString("Geez, what is your deal? Go do something", peggyPositionX - 200, peggyPositionY + 13000);
		
		
//		if(quit == true){
//			g.drawString("Resume (R)", 250, 100);
//			g.drawString("Main Menu (M)", 250, 150);
//			g.drawString("Quit Game (Q)", 250, 200);
//			if(quit == false){
//				g.clear();
//			}
//		}
//		
//		if (winner) {
//			g.drawString("Congratulations! \n You found an oddly shaped structure \n with some very interesting creatures \n inside.", 300, 100);
//		}
//		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
//		Input input = gc.getInput();
//		int posX = Mouse.getX();
//		int posY = Mouse.getY();
//		
		if(peggyPositionY < 2000)
			peggyPositionY -= delta* .1f;
		
		
//		System.out.println("x coordinate: " + posX + "y coordinate: " + posY);
//		
//		if(input.isKeyDown(Input.KEY_UP)){
//			peggy = movingUp;
//			peggyPositionY += delta* .1f;
//			if(peggyPositionY > 101)
//				peggyPositionY = 101;
//			
//			
//		}
//		if(input.isKeyDown(Input.KEY_DOWN)){
//			peggy = movingDown;
//			peggyPositionY -= delta* .1f;
//			if(peggyPositionY < -730)
//				peggyPositionY = -730;
//		}
//		if(input.isKeyDown(Input.KEY_LEFT)){
//			peggy = movingLeft;
//			peggyPositionX += delta* .1f;
//			if(peggyPositionX > 97)
//				peggyPositionX = 97;
//		}
//		if(input.isKeyDown(Input.KEY_RIGHT)){
//			peggy = movingRight;
//			peggyPositionX -= delta* .1f;
//			if(peggyPositionX < -1170)
//				peggyPositionX = -1170;
//		}
//		
//		if(input.isKeyDown(Input.KEY_ESCAPE)){
//			quit = true;
//		}
//		
//		if((peggyPositionX < -1084) && (peggyPositionY > -430) && (peggyPositionY < -305)){
//			winner = true;
//			peggy = celebrate;
//			sbg.enterState(2);
//		}
		
		
			
		
		//menu is up
//		if(quit == true){
//			if(input.isKeyDown(Input.KEY_R))
//				quit = false;
//			if(input.isKeyDown(Input.KEY_M))
//				sbg.enterState(0);
//			if(input.isKeyDown(Input.KEY_Q))
//				System.exit(0);
//			
//		
//		}
		
		
	}
	
	public int getID(){
		return 7;
	}
}