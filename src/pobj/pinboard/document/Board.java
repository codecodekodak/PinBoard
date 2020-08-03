package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board implements Clip {
	private List<Clip> clips = new ArrayList<>();
	private Color c = Color.BLACK; //default color

	public Board() {

	}

	public List<Clip> getContents() {
		return clips;
	}

	public void addClip(Clip clip) {
		clips.add(clip);
	}

	public void addClip(List<Clip> clips) {
		this.clips.addAll(clips);
	}

	public void removeClip(Clip clip) {
		clips.remove(clip);
	}
	
	public void removeClip(List<Clip> clips) {
		this.clips.removeAll(clips);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		//clear canvas
		double h = ctx.getCanvas().getHeight();
		double w = ctx.getCanvas().getWidth();
		ctx.setFill(Color.WHITE);
		ctx.fillRect(0.0, 0.0, w, h);

		for(Clip c : clips) {
			c.draw(ctx);
		}
	}

	@Override
	public double getTop() {
		return 0;
	}

	@Override
	public double getLeft() {
		return 0.0;
	}

	@Override
	public double getBottom() {
		return 0.0;
	}

	@Override
	public double getRight() {
		return 0;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		
	}

	@Override
	public void move(double x, double y) {
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		for(Clip c : clips) {
			if(c.isSelected(x, y))
				return true;
		}
		return false;
	}

	@Override
	public void setColor(Color c) {
		this.c = c;
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
