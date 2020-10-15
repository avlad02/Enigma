import processing.core.*;

public class PlugPoint extends Enigma {

	PVector pos = new PVector();
	char letter;
	int letterNo;
	boolean occupied = false;
	public PApplet parent;

	public PlugPoint(int no, PApplet parent) {
		this.parent = parent;
		letterNo = no;
		letter = letterOrder.charAt(no);
		int level;
		int rowPos;
		double x;
		float y;
		if (no < 10) {
			level = 1;
			rowPos = no;
			x = (rowPos + 1.0) * parent.width / 11;
		} else if (no < 19) {
			level = 2;
			rowPos = no - 10;
			x = (rowPos + 1.5) * parent.width / 11;
		} else {

			level = 3;
			rowPos = no - 19;
			x = (rowPos + 2.0) * parent.width / 11;
		}
		y = parent.height / 3 + level * (parent.height * 2 / 3) / 4;
		if (no % 3 == 0) {
			y += 15;
		}
		pos = new PVector((float)x, y);
	}

	void show() {
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		parent.textSize(20);
		parent.fill(255);
		parent.text(letter, pos.x, pos.y - 40);
		parent.fill(20);
		parent.stroke(255);

		parent.ellipse(pos.x, pos.y, 20, 20);
		parent.ellipse(pos.x, pos.y + 30, 20, 20);
	}

	boolean click(int x, int y) {
		if (x < pos.x + 15 && x > pos.x - 15 && y < pos.y + 35 && y > pos.y - 35) {
			return true;
		}
		return false;
	}
}
