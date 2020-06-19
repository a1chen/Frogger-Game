import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Car extends Rectangle {

	final static int UP_DIRECTION = 0;
	final static int DOWN_DIRECTION = 1;
	final static int FAST_CAR = 0;
	final static int TRUCK_CAR = 1;
	
	private static final long serialVersionUID = 1L;
	int dx = 0, dy = 0, speed = 0, carType, carDirection;
	frog frogMain;
	Rectangle car1, car2;

	static SoundDriver effects;
	
	public Car(int xPos, int direction, int type, frog frog) {
		
		String[] soundEffects = new String[1];
		soundEffects[0] = "carCrush.wav";
		effects = new SoundDriver(soundEffects);
		
		//Create different types of cars (Different direction, speed)
		if (direction == UP_DIRECTION) {
			if (type == FAST_CAR) {
				speed = -20;
				car1 = new Rectangle(xPos, 700, 50, 70);
				car2 = new Rectangle(xPos, 850, 50, 70);
			}
			if (type == TRUCK_CAR) {
				speed = -5;
				car1 = new Rectangle(xPos, 700, 75, 250);
				car2 = new Rectangle(xPos, 1250, 75, 250);
			}
		}
		if (direction == DOWN_DIRECTION) {
			if (type == FAST_CAR) {
				speed = 20;
				car1 = new Rectangle(xPos, -100, 50, 70);
				car2 = new Rectangle(xPos, -250, 50, 70);
			}
			if (type == TRUCK_CAR) {
				speed = 5;
				car1 = new Rectangle(xPos, -100, 75, 250);
				car2 = new Rectangle(xPos, -650, 75, 250);
			}
		}

		//Pass variables
		carDirection = direction;
		carType = type;
		dy = speed;
		frogMain = frog;
	}

	//Draw cars on screen
	public void draw(Graphics2D win) {
		//Move the cars
		car1.translate(dx, dy);
		car2.translate(dx, dy);
		
		//Reset the cars once they are off screen
		if (carDirection == UP_DIRECTION) {
			if (carType == FAST_CAR) {
				if (car2.getY() < -1500) {
					car1.setLocation(car1.x, 700);
					car2.setLocation(car2.x, 850);
				}
			}
			if (carType == TRUCK_CAR) {
				if (car2.getY() < -250) {
					car1.setLocation(car1.x, 700);
					car2.setLocation(car1.x, 1250);
				}
			}
		}
		if (carDirection == DOWN_DIRECTION) {
			if (carType == FAST_CAR) {
				if (car2.getY() > 2200) {
					car1.setLocation(car1.x, -100);
					car2.setLocation(car2.x, -250);
				}
			}
			if (carType == TRUCK_CAR) {
				if (car2.getY() > 750) {
					car1.setLocation(car1.x, -200);
					car2.setLocation(car2.x, -650);
				}
			}
		}

		//If car intersects then kill frog
		if (car1.intersects(frogMain) || car2.intersects(frogMain)) { 
			effects.play(0);
			frogMain.death();
		}
	}

}
