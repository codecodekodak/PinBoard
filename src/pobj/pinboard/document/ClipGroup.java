package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup implements Composite {
	private List<Clip> clips = new ArrayList<>();

	@Override
	public void draw(GraphicsContext ctx) {
		for (Clip c : clips)
			c.draw(ctx);
	}

	@Override
	public double getTop() {
		double top = clips.get(0).getTop();
		for (Clip c : clips) {
			if (c.getTop() < top) {
				top = c.getTop();
			}
		}
		return top;
	}

	@Override
	public double getLeft() {
		double left = clips.get(0).getLeft();
		for (Clip c : clips) {
			if (c.getLeft() < left) {
				left = c.getLeft();
			}
		}
		return left;
	}

	@Override
	public double getBottom() {
		double bot = clips.get(0).getBottom();
		for (Clip c : clips) {
			if (c.getBottom() > bot) {
				bot = c.getBottom();
			}
		}
		return bot;
	}

	@Override
	public double getRight() {
		double right = clips.get(0).getRight();
		for (Clip c : clips) {
			if (c.getRight() > right) {
				right = c.getRight();
			}
		}
		return right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		for (Clip c : clips) {
			c.setGeometry(left, top, right, bottom);
		}
	}

	@Override
	public void move(double x, double y) {
		for (Clip c : clips) {
			c.move(x, y);
		}
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
	public void setColor(Color c) {
		for (Clip clip : clips) {
			clip.setColor(c);
		}
	}

	@Override
	public Color getColor() {
		return clips.get(0).getColor();
	}

	@Override
	public Clip copy() {
		ClipGroup copy = new ClipGroup();
		for (Clip c : clips) {
			copy.addClip(c.copy());
		}
		return copy;
	}

	@Override
	public List<Clip> getClips() {
		return clips;
	}

	@Override
	public void addClip(Clip toAdd) {
		clips.add(toAdd);

	}

	@Override
	public void removeClip(Clip toRemove) {
		clips.remove(toRemove);
	}

}
