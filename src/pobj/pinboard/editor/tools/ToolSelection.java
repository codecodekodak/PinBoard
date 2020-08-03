package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class ToolSelection implements Tool {
	double pressedX, pressedY;
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		pressedX = e.getX();
		pressedY = e.getY();
		if(e.isShiftDown())
			i.getSelection().toogleSelect(i.getBoard(), pressedX, pressedY);
		else
			i.getSelection().select(i.getBoard(), pressedX, pressedY);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		double X = e.getX();
		double Y = e.getY();
		for(Clip c : i.getSelection().getContents()) {
			c.move(X-pressedX, Y - pressedY);
		}
		pressedX = X;
		pressedY = Y;
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Selection Tool";
	}

}
