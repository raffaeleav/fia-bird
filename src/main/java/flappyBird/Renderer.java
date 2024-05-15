package flappyBird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Renderer extends JPanel {
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		try {
			FlappyBird.flappyBird.repaint(g);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
