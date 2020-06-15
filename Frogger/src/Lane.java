import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Lane extends Rectangle implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int decide;
	Logs main;
	frog mainFrog;
	Random gen = new Random();
	Car mainCar;
	boolean onLog = false, resetLanes = false;

	static SoundDriver soundEffects;
	
	public Lane(int xPos, int randomInt, frog frog2) {
		super(xPos, 0, 100, 700);
		decide = randomInt;
		mainFrog = frog2;
		main = new Logs((int) this.getX() + 25, mainFrog, (gen.nextInt(3) + 2) * -1, gen.nextInt(2));
		mainCar = new Car((int) this.getX() + 25, gen.nextInt(2), gen.nextInt(2), mainFrog);
		
		String[] effects = new String[1];
		effects[0] = "drowning.wav";
		soundEffects = new SoundDriver(effects);

	}

	public void draw(Graphics2D win) {
		if (decide == 0) {
			win.setColor(Color.green);
			win.fill(this);
		}
		if (decide == 1) {
			win.setColor(Color.cyan);
			win.fill(this);
			main.draws(win);
			if (mainFrog.getX() > this.x && !mainFrog.onLog(main) && mainFrog.getX() < this.x + 100) {
				mainFrog.death();
				soundEffects.play(0);

			}
		}

		if (decide == 2) {
			win.setColor(Color.black);
			win.fill(this);
			mainCar.draw(win);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
//			this.decide = gen.nextInt(3);
//		}
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
