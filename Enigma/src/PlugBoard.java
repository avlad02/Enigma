import java.util.ArrayList;
import processing.core.*;
import java.util.Random;

public class PlugBoard {

	private Plug[] plugs = new Plug[10];
	private PlugPoint[] plugPoints = new PlugPoint[26];
	private boolean showing = false;
	private boolean movingPlug = false;
	private int movingPlugNo = 0;
	private PApplet parent;

	public PlugBoard(PApplet parent) {
		for (int i = 0; i < plugPoints.length; i++) {
			plugPoints[i] = new PlugPoint(i, parent);
			this.parent = parent;
		}
		randomisePlugs();
	}

	void randomisePlugs() {
		Random r = new Random();
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			int rand1 = r.nextInt(26);
			while (chosen.contains(rand1)) {
				rand1 = r.nextInt(26);
			}
			chosen.add(rand1);
			int rand2 = r.nextInt(26);
			while (chosen.contains(rand2)) {
				rand2 = r.nextInt(26);
			}
			chosen.add(rand2);
			plugs[i] = new Plug(rand1, rand2, plugPoints[rand1], plugPoints[rand2], parent);
			plugPoints[rand1].setOccupied(true);
			plugPoints[rand2].setOccupied(true);
		}
	}

	void show() {
		for (int i = 0; i < 26; i++) {
			plugPoints[i].show();
		}

		for (int i = 0; i < plugs.length; i++) {
			plugs[i].showPlugs();
		}

		for (int i = 0; i < plugs.length; i++) {
			plugs[i].showLines();
		}
	}

	int code(int input) {
		for (int i = 0; i < plugs.length; i++) {
			if (plugs[i].getConnection1() == input) {
				return plugs[i].getConnection2();
			} else if (plugs[i].getConnection2() == input) {
				return plugs[i].getConnection1();
			}
		}

		return input;// if no plugs on that letter then just return the input
	}

	void click(int x, int y) {
		if (!movingPlug) {
			for (int i = 0; i < plugs.length; i++) {
				if (plugs[i].click(x, y)) {
					movingPlug = true;
					movingPlugNo = i;
					return;
				}
			}
		} else {
			for (int i = 0; i < plugPoints.length; i++) {
				if (plugPoints[i].click(x, y)) {
					if (!plugPoints[i].getOccupied()) {
						movingPlug = false;
						if (plugs[movingPlugNo].getMove1()) {
							plugs[movingPlugNo].setPlugPoint(i, plugPoints[i], 1);
							plugs[movingPlugNo].setMove1(false);
						} else {
							plugs[movingPlugNo].setPlugPoint(i, plugPoints[i], 2);
							plugs[movingPlugNo].setMove2(false);
						}
					}
					return;
				}
			}
		}
	}

	public boolean getShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
	}
}
