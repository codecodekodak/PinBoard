package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool {
	private File file;
	private Clip img = null;
	boolean holding = false;

	public ToolImage(File file) {
		this.file = file;
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		Board b = i.getBoard();
		img = new ClipImage(e.getX(), e.getY(), file);
		b.addClip(img);
		holding = true;
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		Board b = i.getBoard();
		if (img != null)
			b.removeClip(img);
		img = new ClipImage(e.getX(), e.getY(), file);
		b.addClip(img);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		Board b = i.getBoard();
		b.removeClip(img);
		img = new ClipImage(e.getX(), e.getY(), file);
		b.addClip(img);
		img = null;
		holding = false;
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
		if(holding)
			gc.strokeRect(img.getLeft(), img.getTop(), Math.abs(img.getLeft()-img.getRight()), Math.abs(img.getBottom()-img.getTop()));

	}

	@Override
	public String getName(EditorInterface i) {
		return "Image tool";
	}

}
