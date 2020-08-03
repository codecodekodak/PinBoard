package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool {
	double pressedX, pressedY, lastDragX, lastDragY;
	boolean holding = false;
	ClipRect rect = null;

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		pressedX = e.getX();
		pressedY = e.getY();
		lastDragX = e.getX();
		lastDragY = e.getY();
		holding = true;
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		lastDragX = e.getX();
		lastDragY = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		double releaseX = e.getX();
		double releaseY = e.getY();
		Board b = i.getBoard();
		Color c = b.getColor();
		if (releaseX == pressedX || releaseY == pressedY) {
			return;
		} else {
			rect = new ClipRect(Math.min(pressedX, releaseX), Math.min(pressedY,releaseY),
					Math.max(pressedX, releaseX), Math.max(pressedY,releaseY), c);
			b.addClip(rect);
			rect = null;
		}
		holding = false;
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
		if(holding)
			gc.strokeRect(Math.min(pressedX, lastDragX), Math.min(pressedY,lastDragY), Math.abs(lastDragX-pressedX), Math.abs(lastDragY-pressedY));
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Rectangle Tool";
	}

}
