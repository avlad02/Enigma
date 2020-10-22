import processing.core.*;

public class PlugPoint {

	private String letterOrder = "QWERTYUIOPASDFGHJKLZXCVBNM";
	private PVector pos = new PVector();
	private char letter;
	private int letterNo;
	private boolean occupied = false;
	private PApplet parent;

	public PlugPoint(int no, PApplet parent) {
		this.parent = parent;
		setLetterNo(no);
		setLetter(letterOrder.charAt(no));
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
		setPos(new PVector((float) x, y));
	}

	void show() {
		parent.textAlign(PConstants.CENTER, PConstants.CENTER);
		parent.textSize(20);
		parent.fill(255);
		parent.text(getLetter(), getPos().x, getPos().y - 40);
		parent.fill(20);
		parent.stroke(255);

		parent.ellipse(getPos().x, getPos().y, 20, 20);
		parent.ellipse(getPos().x, getPos().y + 30, 20, 20);
	}

	boolean click(int x, int y) {
		if (x < getPos().x + 15 && x > getPos().x - 15 && y < getPos().y + 35 && y > getPos().y - 35) {
			return true;
		}
		return false;
	}

	public boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(int letterNo) {
		this.letterNo = letterNo;
	}
}
