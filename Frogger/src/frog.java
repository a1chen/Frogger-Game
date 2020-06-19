import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class frog extends Rectangle implements KeyListener {

	int lives = 5, score = 0, resetNum = 1;
	boolean onLog = false, madeAcross = false;
	Logs mainLog;
	Font impactSmall = new Font("Impact", 80, 80);
	boolean left = false, right = true, up = false, down = false;

	static SoundDriver soundEffects;

	public frog() {
		super(25, 300, 50, 30);

		String[] effects = new String[2];
		effects[0] = "jumping.wav";
		effects[1] = "scorePoint.wav";
		soundEffects = new SoundDriver(effects);

	}

	public void draw(Graphics2D win) {
		win.setColor(Color.black);
		
		// check if frog is out of bounds
		if (this.getY() < 0 || this.getY() + 50 > 700) { 
			this.death();
		}
		
		//Made to right side
		if (this.getX() > 1700 && !madeAcross) {
			soundEffects.play(1);
			score += 100;
			madeAcross = true;
			resetNum++;

		}

		//Made to left side
		if (this.getX() == 28 && madeAcross) {
			soundEffects.play(1);
			score += 100;
			madeAcross = false;
			resetNum++;
		}
		
		//Keep track of totals
		win.setFont(impactSmall);
		win.drawString("Score: " + this.score, 10, 785);
		win.drawString("Lives: " + this.lives, 400, 785);
		win.drawString("Resets: " + this.resetNum, 790, 785);

	}
	
	//Respawn and decrease lives when dead
	public void death() {
		lives--;
		if (!madeAcross) {
			this.setLocation(28, 300);
		} else {
			this.setLocation(1728, 300);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//Moves frogs using arrow keys
		if (e.getKeyCode() == KeyEvent.VK_LEFT && this.getX() - 100 > 0) {
			this.setLocation((int) this.getX() - 100, (int) this.getY());
			left = true;
			right = false;
			up = false;
			down = false;
			soundEffects.play(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.getX() + 100 < 1800) {
			this.setLocation((int) this.getX() + 100, (int) this.getY());
			left = false;
			right = true;
			up = false;
			down = false;
			soundEffects.play(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.setLocation((int) this.getX(), (int) this.getY() + 50);
			left = false;
			right = false;
			up = false;
			down = true;
			soundEffects.play(0);

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.setLocation((int) this.getX(), (int) this.getY() - 50);
			left = false;
			right = false;
			up = true;
			down = false;
			soundEffects.play(0);

		}

	}

	public boolean onLog(Logs mainLog) {
		if (this.intersects(mainLog.log2) || this.intersects(mainLog.log3) || this.intersects(mainLog.log1)) {
			return true;
		}
		return false;
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
