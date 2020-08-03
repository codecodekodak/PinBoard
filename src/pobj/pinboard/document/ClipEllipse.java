package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip implements Clip {

	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public boolean isSelected(double x, double y) {
		double cx = (getLeft() + getRight()) / 2.0;
		double cy = (getTop() + getBottom()) / 2.0;
		double rx = (getLeft() - getRight()) / 2.0;
		double ry = (getTop() - getBottom()) / 2.0;
		double p1 = (x - cx) / rx;
		double p2 = (y - cy) / ry;
		return ((p1 * p1) + (p2 * p2)) <= 1.0;
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillOval(getLeft(), getTop(), getWidth(), getHeight());

	}

	@Override
	public Clip copy() {
		Clip copy = new ClipEllipse(getLeft(), getTop(), getRight(), getBottom(), getColor());
		return copy;
	}

}
