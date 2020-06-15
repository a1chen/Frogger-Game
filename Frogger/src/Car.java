import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Car extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int dx = 0, dy = 0, speed = 0, type, direction;
	frog frogMain;
	Rectangle car1, car2;

	static SoundDriver effects;
	
	public Car(int xPos, int direction1, int type1, frog frog1) {
		
		String[] soundEffects = new String[1];
		soundEffects[0] = "carCrush.wav";
		effects = new SoundDriver(soundEffects);
		
		if (direction1 == 0) { // car going up
			if (type1 == 0) { // fast cars
				speed = -20;
				car1 = new Rectangle(xPos, 700, 50, 70);
				car2 = new Rectangle(xPos, 850, 50, 70);
			}
			if (type1 == 1) { // truck
				speed = -5;
				car1 = new Rectangle(xPos, 700, 75, 250);
				car2 = new Rectangle(xPos, 1250, 75, 250);
			}
		}

		if (direction1 == 1) {
			if (type1 == 0) {
				speed = 20;
				car1 = new Rectangle(xPos, -100, 50, 70);
				car2 = new Rectangle(xPos, -250, 50, 70);
			}
			if (type1 == 1) {
				speed = 5;
				car1 = new Rectangle(xPos, -100, 75, 250);
				car2 = new Rectangle(xPos, -650, 75, 250);
			}
		}

		direction = direction1;
		type = type1;
		dy = speed;
		frogMain = frog1;
	}

	public void draw(Graphics2D win) {
		car1.translate(dx, dy);
		car2.translate(dx, dy);

		if (direction == 0) { // resets cars once off screen
			if (type == 0) {
				if (car2.getY() < -1500) {
					car1.setLocation(car1.x, 700);
					car2.setLocation(car2.x, 850);
				}
			}
			if (type == 1) {
				if (car2.getY() < -250) {
					car1.setLocation(car1.x, 700);
					car2.setLocation(car1.x, 1250);
				}
			}
		}

		if (direction == 1) { // resets cars once off screen
			if (type == 0) {
				if (car2.getY() > 2200) {
					car1.setLocation(car1.x, -100);
					car2.setLocation(car2.x, -250);
				}
			}
			if (type == 1) {
				if (car2.getY() > 750) {
					car1.setLocation(car1.x, -200);
					car2.setLocation(car2.x, -650);
				}
			}
		}

		if (car1.intersects(frogMain) || car2.intersects(frogMain)) { // if car intersects then kill frog
			effects.play(0);
			frogMain.death();
		}

//		win.setColor(Color.white);
//		win.fill(car1);
//		win.fill(car2);
	}

}
