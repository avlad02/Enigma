import java.util.ArrayList;
import processing.core.*;
import java.util.Random;
public class PlugBoard {

	Plug[] plugs = new Plug[10];
	PlugPoint[] plugPoints = new PlugPoint[26];
	boolean showing = false;
	boolean movingPlug = false;
	int movingPlugNo = 0;
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
			plugPoints[rand1].occupied = true;
			plugPoints[rand2].occupied = true;
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
			if (plugs[i].connection1 == input) {
				return plugs[i].connection2;
			} else if (plugs[i].connection2 == input) {
				return plugs[i].connection1;
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
					if (!plugPoints[i].occupied) {
						movingPlug = false;
						if (plugs[movingPlugNo].move1) {
							plugs[movingPlugNo].setPlugPoint(i, plugPoints[i], 1);
							plugs[movingPlugNo].move1 = false;
						} else {
							plugs[movingPlugNo].setPlugPoint(i, plugPoints[i], 2);
							plugs[movingPlugNo].move2 = false;
						}
					}
					return;
				}
			}
		}
	}
}
