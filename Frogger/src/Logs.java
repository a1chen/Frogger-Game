import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Logs extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int dx = 0, dy = 0;
	frog main;
	Rectangle log, log2, logMain;
	Boolean show2nd = false, show3rd = false;
	int direction;

	public Logs(int xPos, frog x, int speed, int directionY) {
		if (directionY == 1) {
			logMain = new Rectangle(xPos, -200, 50, 125);
			log = new Rectangle(xPos, -200, 50, 175);
			log2 = new Rectangle(xPos, -200, 50, 150);
			speed *= -1;
		} else {
			logMain = new Rectangle(xPos, 700, 50, 125);
			log = new Rectangle(xPos, 700, 50, 175);
			log2 = new Rectangle(xPos, 700, 50, 150);
		}
		dy = speed;
		direction = directionY;
		main = x;

	}

	public void draws(Graphics2D win) {
		win.setColor(Color.darkGray);
		logMain.translate(dx, dy);
		win.fill(logMain);
		win.fill(log);
		win.fill(log2);

		if (direction == 0) { // logs moving upward
			if (logMain.getY() < 400) { // toggles whether or not to show log
				show2nd = true;
			}
			if (log.getY() < 400) {
				show3rd = true;
			}

			if (show2nd) { // moves logs when show is true
				log.translate(dx, dy);
			}
			if (show3rd) {
				log2.translate(dx, dy);
			}

			if (logMain.getY() + logMain.getHeight() < 0) {
				logMain.setLocation((int) logMain.getX(), 700); // send main log back
			}

			if (log.getY() + log.getHeight() < 0) {
				log.setLocation((int) log.getX(), 700); // send second log back
				show2nd = false;
			}

			if (log2.getY() + log2.getHeight() < 0) {
				log2.setLocation((int) log2.getX(), 700); // send third log back
				show3rd = false;
			}
		}

		if (direction == 1) { // moving downward
			if (logMain.getY() + logMain.getHeight() > 300) { // toggles whether or not to show log
				show2nd = true;
			}
			if (log.getY() + log.getHeight() > 300) {
				show3rd = true;
			}

			if (show2nd) { // moves logs when show is true
				log.translate(dx, dy);
			}
			if (show3rd) {
				log2.translate(dx, dy);
			}

			if (logMain.getY() > 700) {
				logMain.setLocation((int) logMain.getX(), -200); // send main log back
			}

			if (log.getY() > 700) {
				log.setLocation((int) log.getX(), -200); // send second log back
				show2nd = false;
			}

			if (log2.getY() > 700) {
				log2.setLocation((int) log2.getX(), -200); // send third log back
				show3rd = false;
			}
		}

		if (logMain.intersects(main)) {
			main.translate(dx, dy); // moves frog with the main log
		}
		if (log.intersects(main)) {
			main.translate(dx, dy); // moves frog with the log
		}
		if (log2.intersects(main)) {
			main.translate(dx, dy); // moves frog with the log2
		}

	}

}
