
import javax.swing.JFrame;

public class froggerFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame j1 = new JFrame();
		JFrame j2 = new JFrame();
		j1.setTitle("Frogger");
		j1.setSize(1830, 900);
		j1.setLocationRelativeTo(null);
		j2.setLocation(j1.getX() + 1830, j1.getY());
		j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j1.add(new froggerComp());
		

		j1.setVisible(true);
	}

}
