import processing.core.*;

public class Plug {

	int connection1;
	int connection2;
	PlugPoint point1;
	PlugPoint point2;
	boolean move1 = false;
	boolean move2 = false;
	private PApplet parent;

	public Plug(int c1, int c2, PlugPoint p1, PlugPoint p2, PApplet parent) {
		connection1 = c1;
		connection2 = c2;
		point1 = p1;
		point2 = p2;
		this.parent = parent;
	}

	void showLines() {

		parent.stroke(100, 100, 200, 150);
		parent.strokeWeight(3);

		if (move1) {
			parent.line(parent.mouseX, parent.mouseY, point2.pos.x, point2.pos.y + 15);
		} else if (move2) {
			parent.line(point1.pos.x, point1.pos.y + 15, parent.mouseX, parent.mouseY);
		} else {
			parent.line(point1.pos.x, point1.pos.y + 15, point2.pos.x, point2.pos.y + 15);
		}
	}

	void showPlugs() {
		parent.stroke(200);
		parent.fill(40);
		parent.rectMode(PConstants.CENTER);

		if (move1) {
			parent.rect(parent.mouseX, parent.mouseY, 30, 70);

			parent.rect(point2.pos.x, point2.pos.y + 15, 30, 70);
		} else if (move2) {
			parent.rect(point1.pos.x, point1.pos.y + 15, 30, 70);
			parent.rect(parent.mouseX, parent.mouseY, 30, 70);
		} else {
			parent.rect(point1.pos.x, point1.pos.y + 15, 30, 70);

			parent.rect(point2.pos.x, point2.pos.y + 15, 30, 70);
			parent.fill(255);
			parent.textSize(10);
			parent.text(point2.letter, point1.pos.x, point1.pos.y + 15);
			parent.text(point1.letter, point2.pos.x, point2.pos.y + 15);
		}
	}

	boolean click(int x, int y) {
		if (x < point1.pos.x + 15 && x > point1.pos.x - 15 && y < point1.pos.y + 50 && y > point1.pos.y - 20) {
			move1 = true;
			point1.occupied = false;
			return true;
		} else if (x < point2.pos.x + 15 && x > point2.pos.x - 15 && y < point2.pos.y + 50 && y > point2.pos.y - 20) {
			move2 = true;
			point2.occupied = false;

			return true;
		}
		return false;
	}

	void setPlugPoint(int plugPointNo, PlugPoint newPoint, int connectionNo) {
		newPoint.occupied = true;
		switch (connectionNo) {
		case (1):
			point1 = newPoint;
			connection1 = plugPointNo;
			break;
		case (2):
			point2 = newPoint;
			connection2 = plugPointNo;
			break;
		}
	}
}
