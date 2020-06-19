import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Logs extends Rectangle {
	static final int UP_DIRECTION = 0;
	static final int DOWN_DIRECTION = 1;
	
	int dx = 0, dy = 0;
	frog frog;
	Rectangle log1,log2, log3;
	Boolean show2nd = false, show3rd = false;
	int direction;

	public Logs(int xPos, frog x, int speed, int directionY) {
		if (directionY == UP_DIRECTION) {
			log1 = new Rectangle(xPos, -200, 50, 125);
			log2 = new Rectangle(xPos, -200, 50, 175);
			log3 = new Rectangle(xPos, -200, 50, 150);
			speed *= -1;
		} else {
			log1 = new Rectangle(xPos, 700, 50, 125);
			log2 = new Rectangle(xPos, 700, 50, 175);
			log3 = new Rectangle(xPos, 700, 50, 150);
		}
		dy = speed;
		direction = directionY;
		frog = x;

	}

	public void draws(Graphics2D win) {
		win.setColor(Color.darkGray);
		log1.translate(dx, dy);
		win.fill(log1);
		win.fill(log2);
		win.fill(log3);
		
		//Moving downwards
		if (direction == DOWN_DIRECTION) { 
			//Decides when to show each log on screen
			if (log1.getY() < 400) { 
				show2nd = true;
			}
			if (log2.getY() < 400) {
				show3rd = true;
			}
			if (show2nd) {
				log2.translate(dx, dy);
			}
			if (show3rd) {
				log3.translate(dx, dy);
			}
			
			//Send first logs back after off screen
			if (log1.getY() + log1.getHeight() < 0) {
				log1.setLocation((int) log1.getX(), 700); 
			}
			if (log2.getY() + log2.getHeight() < 0) {
				log2.setLocation((int) log2.getX(), 700); 
				show2nd = false;
			}
			if (log3.getY() + log3.getHeight() < 0) {
				log3.setLocation((int) log3.getX(), 700);
				show3rd = false;
			}
		}

		//Moving upwards
		if (direction == UP_DIRECTION) {
			//Decides when to show logs
			if (log1.getY() + log1.getHeight() > 300) {
				show2nd = true;
			}
			if (log2.getY() + log2.getHeight() > 300) {
				show3rd = true;
			}
			
			//Decides when to move logs
			if (show2nd) {
				log2.translate(dx, dy);
			}
			if (show3rd) {
				log3.translate(dx, dy);
			}

			//Moves logs back to beginning once logs is off screen
			if (log1.getY() > 700) {
				log1.setLocation((int) log1.getX(), -200);
			}

			if (log2.getY() > 700) {
				log2.setLocation((int) log2.getX(), -200);
				show2nd = false;
			}

			if (log3.getY() > 700) {
				log3.setLocation((int) log3.getX(), -200);
				show3rd = false;
			}
		}

		//If frog is on log, moves frog with the log
		if (log1.intersects(frog)) {
			frog.translate(dx, dy);
		}
		if (log2.intersects(frog)) {
			frog.translate(dx, dy);
		}
		if (log3.intersects(frog)) {
			frog.translate(dx, dy);
		}

	}

}
