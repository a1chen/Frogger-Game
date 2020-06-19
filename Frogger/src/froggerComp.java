import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class froggerComp extends GameDriverV3 implements KeyListener {

	private static final long serialVersionUID = 1L;
	int gameState = 0, frogWidth, frogHeight, framePos = 0, timer = 0, timeDelay = 10;
	Rectangle background = new Rectangle(0, 0, 1830, 700), backgroundScore = new Rectangle(0, 700, 1830, 200);
	Random randomizer = new Random();
	Font impactSmall = new Font("Impact", 100, 100);
	Font impactSmaller = new Font("Impact", 65, 65);

	frog mainFrog;
	Lane[] lanes;
	BufferedImage frog, smallFrog, frogSpriteRight, frogSpriteLeft, frogSpriteUp, frogSpriteDown, wood, woodSmall,
			woodMedium, woodLarge, cars, carSmallDown, carSmallUp, truckUp, truckDown;

	static SoundDriver sound;

	BufferedImage[] frogScreen;

	public froggerComp() {
		this.addKeyListener(this);

		String[] stringName = new String[1];
		stringName[0] = "froggerMusic.wav";
		sound = new SoundDriver(stringName);
		sound.loop(0);

		mainFrog = new frog();
		lanes = new Lane[16];
		frog = this.addImage("frogBig.png");
		smallFrog = this.addImage("Frog.png");
		wood = this.addImage("Wood.jpg");
		cars = this.addImage("Cars.png");
		frogScreen = new BufferedImage[5];

		truckUp = this.addImage("Truck.png");
		truckDown = this.addImage("truckDown.png");

		frogWidth = frog.getWidth() / 5;
		frogHeight = frog.getHeight() / 8;

		frogSpriteRight = smallFrog.getSubimage(0, smallFrog.getHeight() / 8 * 2, smallFrog.getWidth() / 5,
				smallFrog.getHeight() / 8 );
		frogSpriteLeft = smallFrog.getSubimage(0, smallFrog.getHeight() / 8 * 6, smallFrog.getWidth() / 5,
				smallFrog.getHeight() / 8);
		frogSpriteUp = smallFrog.getSubimage(0, 0, smallFrog.getWidth() / 5, smallFrog.getHeight() / 8);
		frogSpriteDown = smallFrog.getSubimage(0, smallFrog.getHeight() / 8 * 4, smallFrog.getWidth() / 5,
				smallFrog.getHeight() / 8);

		woodSmall = wood.getSubimage(50, 125, 50, 125);
		woodMedium = wood.getSubimage(50, 150, 50, 150);
		woodLarge = wood.getSubimage(50, 175, 50, 175);

		carSmallDown = cars.getSubimage(cars.getWidth() / 5 * 0, cars.getHeight() / 8 * 0, cars.getWidth() / 5 - 30,
				cars.getHeight() / 8);
		carSmallUp = cars.getSubimage(cars.getWidth() / 5 * 4 + 30, cars.getHeight() / 8 * 1, cars.getWidth() / 5 - 30,
				cars.getHeight() / 8);

		for (int i = 0; i < 5; i++) {
			frogScreen[i] = frog.getSubimage(frogWidth * i, frogHeight * 2, frogWidth, frogHeight);
		}
	}

	public void start() {

		mainFrog = new frog();
		lanes = new Lane[16];
		this.addKeyListener(mainFrog);
		// initialize different lanes
		for (int i = 0; i < 16; i++) { 
			// randomizer decides what type of lane
			lanes[i] = new Lane(100 * (i + 1), randomizer.nextInt(3), mainFrog); 
		}

		gameState = 1;

	}

	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		if (gameState == 0) {
			win.setColor(Color.green);
			win.fill(background);
			win.fill(backgroundScore);
			win.setFont(impactSmall);
			win.setColor(Color.black);
			win.drawString("Frogger", 750, 100);
			win.drawString("By Aaron Chen, Team 409, 2018", 300, 250);
			win.drawString("Press \"ENTER\" to start", 500, 400);
			win.drawString("Press \"I\" to read instructions", 360, 550);

			win.drawImage(frogScreen[this.framePos], null, 750, 580);
			if (timer == timeDelay) {
				framePos++;
				framePos %= 5;
				timer = 0;
			} else {
				timer++;
			}

		}

		if (gameState == 1) {
			win.setColor(Color.white);
			win.fill(background);
			win.setColor(Color.black);

			win.drawLine(0, 700, 1830, 700);

			for (int i = 0; i < 16; i++) { // drawing lanes
				lanes[i].draw(win);
			}
			win.setColor(Color.white);

			for (int i = 0; i < 16; i++) {
				if (lanes[i].laneType == 1) {
					win.drawImage(woodSmall, null, (int) lanes[i].log.log1.getX(), (int) lanes[i].log.log1.getY());																														
					win.drawImage(woodMedium, null, (int) lanes[i].log.log3.getX(), (int) lanes[i].log.log3.getY());
					win.drawImage(woodLarge, null, (int) lanes[i].log.log2.getX(), (int) lanes[i].log.log2.getY());
				}

				if (lanes[i].laneType == 2 && lanes[i].mainCar.carDirection == 0 && lanes[i].mainCar.carType == 0) { 																													
					win.drawImage(carSmallUp, null, (int) lanes[i].mainCar.car1.getX(),
							(int) lanes[i].mainCar.car1.getY());
					win.drawImage(carSmallUp, null, (int) lanes[i].mainCar.car2.getX(),
							(int) lanes[i].mainCar.car2.getY());
				}

				if (lanes[i].laneType == 2 && lanes[i].mainCar.carDirection == 1 && lanes[i].mainCar.carType == 0) {
					win.drawImage(carSmallDown, null, (int) lanes[i].mainCar.car1.getX(),
							(int) lanes[i].mainCar.car1.getY());
					win.drawImage(carSmallDown, null, (int) lanes[i].mainCar.car2.getX(),
							(int) lanes[i].mainCar.car2.getY());
				}

				if (lanes[i].laneType == 2 && lanes[i].mainCar.carDirection == 0 && lanes[i].mainCar.carType == 1) {
					win.drawImage(truckUp, null, (int) lanes[i].mainCar.car1.getX() - 10,
							(int) lanes[i].mainCar.car1.getY());
					win.drawImage(truckUp, null, (int) lanes[i].mainCar.car2.getX() - 10,
							(int) lanes[i].mainCar.car2.getY());
				}
				if (lanes[i].laneType == 2 && lanes[i].mainCar.carDirection == 1 && lanes[i].mainCar.carType == 1) {
					win.drawImage(truckDown, null, (int) lanes[i].mainCar.car1.getX() - 10,
							(int) lanes[i].mainCar.car1.getY());
					win.drawImage(truckDown, null, (int) lanes[i].mainCar.car2.getX() - 10,
							(int) lanes[i].mainCar.car2.getY());
				}

			}

			win.fill(backgroundScore); // fills bottom of screen
			mainFrog.draw(win); // draw frog

			win.setColor(Color.black);
			win.drawLine(0, 700, 1830, 700);
			if (mainFrog.right) {
				win.drawImage(frogSpriteRight, null, (int) mainFrog.getX(), (int) mainFrog.getY() - 25); // drawing frog
																											// right
			}
			if (mainFrog.left) {
				win.drawImage(frogSpriteLeft, null, (int) mainFrog.getX(), (int) mainFrog.getY() - 25);
			}
			if (mainFrog.up) {
				win.drawImage(frogSpriteUp, null, (int) mainFrog.getX(), (int) mainFrog.getY() - 25);
			}
			if (mainFrog.down) {
				win.drawImage(frogSpriteDown, null, (int) mainFrog.getX(), (int) mainFrog.getY());
			}

			if (mainFrog.lives == 0) {
				gameState = 3;
			}

		}

		if (gameState == 2) { // instruction screen
			win.setFont(impactSmaller);
			win.setColor(Color.green);
			win.fill(background);
			win.fill(backgroundScore);
			win.setColor(Color.black);
			win.drawString("Use arrowkeys to move", 590, 100);
			win.drawString("Jump on logs and avoid cars", 510, 250);
			win.drawString("Get across to score points and refill resets", 350, 400);
			win.drawString("Press \"ENTER\" to start", 580, 700);
			win.drawString("Reroll the map by pressing \"BKSP\" ", 445, 550);
		}

		// Death screen
		if (gameState == 3) {
			win.setColor(Color.red);
			win.fill(background);
			win.fill(backgroundScore);
			win.setColor(Color.black);
			win.setFont(impactSmall);
			win.drawString("Final Score: " + mainFrog.score, 600, 200);
			win.drawString("Press \"ENTER\" to play again", 350, 350);
			win.drawString("Press \"ESC\" to return to home page", 200, 500);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { // changes lanes when pressing enter
			start();
		}

		if (e.getKeyCode() == KeyEvent.VK_I) {
			gameState = 2;
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = 0;
		}

		if (mainFrog.getX() > 1600 && !mainFrog.madeAcross && e.getKeyCode() == KeyEvent.VK_RIGHT) { // changes map

			for (int i = 0; i < 16; i++) {
				lanes[i].laneType = randomizer.nextInt(3);
			}

		}

		if (mainFrog.getX() < 200 && mainFrog.madeAcross && e.getKeyCode() == KeyEvent.VK_LEFT) { // changes map //
																									// once across map
			for (int i = 0; i < 16; i++) {
				lanes[i].laneType = randomizer.nextInt(3);
			}
		}

		if (mainFrog.resetNum > 0 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { // reset powerup
			for (int i = 0; i < 16; i++) {
				lanes[i].laneType = randomizer.nextInt(3);
			}
			mainFrog.resetNum -= 1;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
