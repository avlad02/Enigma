import processing.core.PApplet;

public class Enigma extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Enigma");
	}

	private Key[] keyboard;
	private PlugBoard plugBoard;
	private Rotor rotor1, rotor2, rotor3;
	private Reflector ref;
	private boolean showPlugs = false;
	private String letterOrder = "QWERTYUIOPASDFGHJKLZXCVBNM";
	private String letterOrderLower = "qwertyuiopasdfghjklzxcvbnm";
	private String word = "";

	public void settings() {
		fullScreen();
	}

	public void setup() {
		fullScreen();
		background(100);
		rotor1 = new Rotor(1, this);
		rotor2 = new Rotor(2, this);
		rotor3 = new Rotor(3, this);
		ref = new Reflector();
		plugBoard = new PlugBoard(this);
		keyboard = new Key[26];
		for (int i = 0; i < keyboard.length; i++) {
			keyboard[i] = new Key(letterOrder.charAt(i), i, this);
		}
	}

	public void draw() {
		background(100);
		if (showPlugs) {
			plugBoard.show();
		} else {
			stroke(255);
			strokeWeight(1);
			for (int i = 0; i < keyboard.length; i++)
				keyboard[i].show();
			rotor1.show();
			rotor2.show();
			rotor3.show();
			textSize(25);
			fill(255);
			text(word, 60, 60);
		}
	}

	public void keyPressed() {
		int output;
		output = plugBoard.code(letterOrderLower.indexOf(key));
		output = rotor1.code(output, true);
		output = rotor2.code(output, true);
		output = rotor3.code(output, true);
		output = ref.code(output, true);
		output = rotor3.code(output, false);
		output = rotor2.code(output, false);
		output = rotor1.code(output, false);
		output = plugBoard.code(output);
		moveRotors();
		keyboard[output].setPressed(true);
		word = word + letterOrderLower.charAt(output);

	}

	public void keyReleased() {
		for (int i = 0; i < keyboard.length; i++)
			keyboard[i].setPressed(false);
	}

	public void mousePressed() {
		if (mouseY > height * (9.0 / 10.0)) {// if clicking the bottom of the screen then switch between plugs and
												// lamps
			showPlugs = !showPlugs;
		} else {
			rotor1.click(mouseX, mouseY);
			rotor2.click(mouseX, mouseY);
			rotor3.click(mouseX, mouseY);
		}
		if (showPlugs) {
			plugBoard.click(mouseX, mouseY);
		}
	}

	void moveRotors() {
		rotor1.incrementPosition();
		if (rotor1.getPosition() == 26) {
			rotor1.setPosition(0);
			rotor2.incrementPosition();
			if (rotor2.getPosition() == 26) {
				rotor2.setPosition(0);
				rotor3.incrementPosition();
				if (rotor3.getPosition() == 26) {
					rotor3.setPosition(0);
				}
			}
		}
	}

}
