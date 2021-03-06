package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolEllipse implements Tool {
	double pressedX, pressedY, lastDragX, lastDragY;
	boolean holding = false;
	ClipEllipse ellipse = null;

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
			ellipse = new ClipEllipse(Math.min(pressedX, releaseX), Math.min(pressedY,releaseY),
					Math.max(pressedX, releaseX), Math.max(pressedY,releaseY), c);
			b.addClip(ellipse);
			i.getUndoStack().addCommand(new CommandAdd(i, ellipse));
			ellipse = null;
		}
		holding = false;
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
		if(holding)
			gc.strokeOval(Math.min(pressedX, lastDragX), Math.min(pressedY,lastDragY), Math.abs(lastDragX-pressedX), Math.abs(lastDragY-pressedY));
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Ellipse Tool";
	}
}
