package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {
	private List<Clip> toMove = new ArrayList<Clip>();
	private double x, y;

	public CommandMove(EditorInterface editor, Clip toMove, double x, double y) {
		this.toMove.add(toMove);
		this.x = x;
		this.y = y;
	}

	public CommandMove(EditorInterface editor, List<Clip> toMove, double x, double y) {
		this.toMove.addAll(toMove);
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute() {
		for (Clip c : toMove) {
			c.move(x, y);
		}
	}

	@Override
	public void undo() {
		for (Clip c : toMove) {
			c.move(-x, -y);
		}
	}

}
