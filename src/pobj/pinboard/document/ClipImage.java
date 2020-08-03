package pobj.pinboard.document;

import java.io.File;
import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage implements Clip {
	private double left, top;
	private Image image;
	private File file;

	public ClipImage(double left, double top, File file) {
		this.left = left;
		this.top = top;
		this.file = file;
		try {
			image = new Image(file.toURI().toURL().toExternalForm());
		}catch(IOException ioex) {
			ioex.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(image, left, top);
	}

	@Override
	public double getTop() {
		return top;
	}

	@Override
	public double getLeft() {
		return left;
	}

	@Override
	public double getBottom() {
		return top + image.getHeight();
	}

	@Override
	public double getRight() {
		return left + image.getWidth();
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
	}

	@Override
	public void move(double x, double y) {
		left += x;
		top += y;
	}

	@Override
	public boolean isSelected(double x, double y) {
		return (left <= x) && (x <= getRight()) && (top <= y) && (y <= getBottom());
	}

	@Override
	public Clip copy() {
		return new ClipImage(this.left, this.top, this.file);
	}

	@Override
	public void setColor(Color c) {
		
	}

	@Override
	public Color getColor() {
		return null;
	}
}