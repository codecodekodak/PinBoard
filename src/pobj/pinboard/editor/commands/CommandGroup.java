package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command {
	private EditorInterface editor;
	private List<Clip> toGroup = new ArrayList<>();
	private ClipGroup cg;

	public CommandGroup(EditorInterface editor, Clip toGroup) {
		this.editor = editor;
		this.toGroup.add(toGroup);
	}

	public CommandGroup(EditorInterface editor, List<Clip> toGroup) {
		this.editor = editor;
		this.toGroup.addAll(toGroup);
	}

	@Override
	public void execute() {
		cg = new ClipGroup();
		editor.getBoard().removeClip(toGroup);
		for(Clip c : toGroup)
			cg.addClip(c);
		editor.getBoard().addClip(cg);
	}

	@Override
	public void undo() {
		editor.getBoard().removeClip(cg);
		editor.getBoard().addClip(toGroup);
	}

}
