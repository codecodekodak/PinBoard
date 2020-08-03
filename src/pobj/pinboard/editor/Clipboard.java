package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard implements ClipboardListener{
	private static Clipboard Instance = new Clipboard();
	private List<Clip> Clips = new ArrayList<>();
	private List<ClipboardListener> listeners = new ArrayList<>();

	private Clipboard() {
	}

	public static Clipboard getInstance() {
		return Instance;
	}

	public void copyToClipboard(List<Clip> clips) {
		clear();
		for (Clip c : clips) {
			Clips.add(c.copy());
		}
	}

	public List<Clip> copyFromClipboard() {
		List<Clip> copies = new ArrayList<>();
		for (Clip c : Clips)
			copies.add(c.copy());
		return copies;
	}

	public void clear() {
		Clips.clear();
		clipboardChanged();
	}

	public boolean isEmpty() {
		return Clips.isEmpty();
	}

	public void addListener(ClipboardListener listener) {
		listeners.add(listener);
	}

	public void removeListener(ClipboardListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void clipboardChanged() {
		for(ClipboardListener cl : listeners) {
			cl.clipboardChanged();
		}
	}
}
