import processing.core.*;

public class Plug {

	private int connection1;
	private int connection2;
	private PlugPoint point1;
	private PlugPoint point2;
	private boolean move1 = false;
	private boolean move2 = false;
	private PApplet parent;

	public Plug(int c1, int c2, PlugPoint p1, PlugPoint p2, PApplet parent) {
		setConnection1(c1);
		setConnection2(c2);
		point1 = p1;
		point2 = p2;
		this.parent = parent;
	}

	void showLines() {

		parent.stroke(100, 100, 200, 150);
		parent.strokeWeight(3);

		if (getMove1()) {
			parent.line(parent.mouseX, parent.mouseY, point2.getPos().x, point2.getPos().y + 15);
		} else if (getMove2()) {
			parent.line(point1.getPos().x, point1.getPos().y + 15, parent.mouseX, parent.mouseY);
		} else {
			parent.line(point1.getPos().x, point1.getPos().y + 15, point2.getPos().x, point2.getPos().y + 15);
		}
	}

	void showPlugs() {
		parent.stroke(200);
		parent.fill(40);
		parent.rectMode(PConstants.CENTER);

		if (getMove1()) {
			parent.rect(parent.mouseX, parent.mouseY, 30, 70);

			parent.rect(point2.getPos().x, point2.getPos().y + 15, 30, 70);
		} else if (getMove2()) {
			parent.rect(point1.getPos().x, point1.getPos().y + 15, 30, 70);
			parent.rect(parent.mouseX, parent.mouseY, 30, 70);
		} else {
			parent.rect(point1.getPos().x, point1.getPos().y + 15, 30, 70);

			parent.rect(point2.getPos().x, point2.getPos().y + 15, 30, 70);
			parent.fill(255);
			parent.textSize(10);
			parent.text(point2.getLetter(), point1.getPos().x, point1.getPos().y + 15);
			parent.text(point1.getLetter(), point2.getPos().x, point2.getPos().y + 15);
		}
	}

	boolean click(int x, int y) {
		if (x < point1.getPos().x + 15 && x > point1.getPos().x - 15 && y < point1.getPos().y + 50 && y > point1.getPos().y - 20) {
			setMove1(true);
			point1.setOccupied(false);
			return true;
		} else if (x < point2.getPos().x + 15 && x > point2.getPos().x - 15 && y < point2.getPos().y + 50 && y > point2.getPos().y - 20) {
			setMove2(true);
			point2.setOccupied(false);

			return true;
		}
		return false;
	}

	void setPlugPoint(int plugPointNo, PlugPoint newPoint, int connectionNo) {
		newPoint.setOccupied(true);
		switch (connectionNo) {
		case (1):
			point1 = newPoint;
			setConnection1(plugPointNo);
			break;
		case (2):
			point2 = newPoint;
			setConnection2(plugPointNo);
			break;
		}
	}

	public boolean getMove1() {
		return move1;
	}

	public void setMove1(boolean move1) {
		this.move1 = move1;
	}

	public boolean getMove2() {
		return move2;
	}

	public void setMove2(boolean move2) {
		this.move2 = move2;
	}

	public int getConnection1() {
		return connection1;
	}

	public void setConnection1(int connection1) {
		this.connection1 = connection1;
	}

	public int getConnection2() {
		return connection2;
	}

	public void setConnection2(int connection2) {
		this.connection2 = connection2;
	}
}
