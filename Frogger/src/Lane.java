import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Lane extends Rectangle {

	final static int GRASS = 0;
	final static int RIVER = 1;
	final static int STREET = 2;

	private static final long serialVersionUID = 1L;
	int laneType;
	Logs log;
	frog mainFrog;
	Random gen = new Random();
	Car mainCar;
	boolean onLog = false, resetLanes = false;

	static SoundDriver soundEffects;

	public Lane(int xPos, int randomInt, frog frog2) {
		super(xPos, 0, 100, 700);
		
		String[] effects = new String[1];
		effects[0] = "drowning.wav";
		soundEffects = new SoundDriver(effects);
		
		laneType = randomInt;
		mainFrog = frog2;
		log = new Logs((int) this.getX() + 25, mainFrog, (gen.nextInt(3) + 2) * -1, gen.nextInt(2));
		mainCar = new Car((int) this.getX() + 25, gen.nextInt(2), gen.nextInt(2), mainFrog);
	}

	public void draw(Graphics2D win) {
		if (laneType == GRASS) {
			win.setColor(Color.green);
			win.fill(this);
		}
		if (laneType == RIVER) {
			win.setColor(Color.cyan);
			win.fill(this);
			log.draws(win);

			// Check if frog is on log, is in water, or off screen
			if (mainFrog.getX() > this.x && !mainFrog.onLog(log) && mainFrog.getX() < this.x + 100) {
				mainFrog.death();
				soundEffects.play(0);
			}
		}
		if (laneType == STREET) {
			win.setColor(Color.black);
			win.fill(this);
			mainCar.draw(win);
		}

	}
}
