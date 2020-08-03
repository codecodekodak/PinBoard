package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	List<Clip> selected = new ArrayList<>();
	
	public void select(Board board, double x, double y) {
		clear();
		for(Clip c : board.getContents()) {
			if(c.isSelected(x, y))
				selected.add(c);
		}
	}
	
	public void toogleSelect(Board board, double x, double y) {
		for(Clip c : board.getContents()) {
			if(c.isSelected(x, y)) {
				if(selected.contains(c))
					selected.remove(c);
				else
					selected.add(c);
			}
		}
	}
	
	
	public void clear() {
		selected.clear();
	}
	
	public List<Clip> getContents(){
		return selected;
	}
	
	public void drawFeedback(GraphicsContext gc) {
		double top = 0.0;
		double bottom = 0.0;
		double left = 0.0;
		double right = 0.0;
		if(!selected.isEmpty()) {
			top = selected.get(0).getTop();
			bottom = selected.get(0).getBottom();
			left = selected.get(0).getLeft();
			right = selected.get(0).getRight();
		}
		for(Clip c : selected) {
			if(c.getBottom() > bottom)
				bottom = c.getBottom();
			if(c.getTop() < top)
				top = c.getTop();
			if(c.getLeft() < left)
				left = c.getLeft();
			if(c.getRight() > right)
				right = c.getRight();
		}
		gc.strokeRect(left, top, right-left, bottom-top);
	}
}
