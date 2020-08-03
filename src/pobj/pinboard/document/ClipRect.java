package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip {

	public ClipRect(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		//ctx.strokeRect(left, top, width, height);
		ctx.setFill(this.getColor());
		ctx.fillRect(getLeft(), getTop(), getWidth(), getHeight());
	}

	@Override
	public Clip copy() {
		Clip copy = new ClipRect(getLeft(), getTop(), getRight(), getBottom(), getColor());
		return copy;
	}

}
