package pobj.pinboard.document;

import javafx.scene.paint.Color;

public abstract class AbstractClip {
	private double top, bottom, left, right;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.color = color;
	}
	
	public double getTop() {
		return top;
	}

	public double getLeft() {
		return left;
	}

	public double getBottom() {
		return bottom;
	}

	public double getRight() {
		return right;
	}
	
	public double getWidth() {
		return right - left;
	}
	
	public double getHeight() {
		return bottom - top;
	}

	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public void move(double x, double y) {
		this.left += x;
		this.right += x;
		this.top += y;
		this.bottom += y;
	}

	public boolean isSelected(double x, double y) {
		boolean xSelected = false;
		xSelected = x >= left && x <= right;
		
		boolean ySelected = false;
		ySelected = y <= bottom && y >= top;
		
		return xSelected && ySelected;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public Color getColor() {
		return this.color;
	}
}
