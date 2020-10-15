import processing.core.*;

public class Key {

	char letter;
	int index;
	PVector pos;
	boolean pressed = false;
	private PApplet parent;

	public Key(char letterIn, int indexIn, PApplet parent) {
		this.parent = parent;
		pos = new PVector();
		this.letter = letterIn;
		this.index = indexIn + 1;
		if (index <= 10) {
			pos.x = 200 * index;
			pos.y = parent.height / 2;
		}
		if (index > 10 && index <= 19) {
			pos.x = 200 * (index - 10) + 100;
			pos.y = parent.height / 2 + 200;
		}
		if (index > 19) {
			pos.x = 200 * (index - 19) + 200;
			pos.y = parent.height / 2 + 400;
		}
	}

	void show() {
		if (pressed == false) {
			parent.fill(50);
		} else {
			parent.fill(200);
		}
		parent.ellipse(pos.x, pos.y, 50, 50);
		parent.textSize(25);
		parent.fill(255);
		parent.text(letter, pos.x - 8, pos.y + 10);
	}

}
