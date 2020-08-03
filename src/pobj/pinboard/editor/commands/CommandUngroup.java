package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command {
	private EditorInterface editor;
	private List<ClipGroup> toUngroup = new ArrayList<>();

	public CommandUngroup(EditorInterface editor, ClipGroup toUngroup) {
		this.editor = editor;
		this.toUngroup.add(toUngroup);
	}
	
	public CommandUngroup(EditorInterface editor, List<ClipGroup> toUngroup) {
		this.editor = editor;
		this.toUngroup.addAll(toUngroup);
	}

	@Override
	public void execute() {
		for(ClipGroup cg : toUngroup) {
			editor.getBoard().removeClip(cg);
			editor.getBoard().addClip(cg.getClips());
		}
		
	}

	@Override
	public void undo() {
		for(ClipGroup cg : toUngroup) {
			editor.getBoard().removeClip(cg.getClips());
			editor.getBoard().addClip(cg);
		}
	}

}
