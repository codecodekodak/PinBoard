package pobj.pinboard.editor;

import java.util.Stack;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	private Stack<Command> undo = new Stack<>();
	private Stack<Command> redo = new Stack<>();
	
	public void addCommand(Command cmd) {
		redo.clear();
		undo.add(cmd);
	}
	
	public void redo() {
		Command cmd = redo.pop();
		cmd.execute();
		undo.push(cmd);
	}
	
	
	public void undo() {
		Command cmd = undo.pop();
		cmd.undo();
		redo.push(cmd);
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}

}
