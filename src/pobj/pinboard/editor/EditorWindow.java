package pobj.pinboard.editor;

import java.io.File;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface, ClipboardListener{
	private Board board;
	private Stage stage;
	private Selection selection = new Selection();
	Menu File = new Menu("File");
	Menu Edit = new Menu("Edit");
	Menu Tools = new Menu("Tools");
	MenuItem paste;
	Button Box = new Button("Box");
	Button Ellipse = new Button("Ellipse");
	Button Img = new Button("Img...");
	Button Select = new Button("Selection");
	Canvas canvas;
	Separator separator = new Separator();
	Label label = new Label("OpenWindow");
	VBox vbox = new VBox();
	Tool currentTool = new ToolSelection();
    int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
	

	public EditorWindow(Stage stage) {
        // Responsive Design 
        int canvasWidth = 0;
        int canvasHeight = 0;
        if (screenWidth <= 800 && screenHeight <= 600) {
            canvasWidth = 600;
            canvasHeight = 350;
        } else if (screenWidth <= 1280 && screenHeight <= 768) {
            canvasWidth = 800;
            canvasHeight = 450;
        } else if (screenWidth <= 1920 && screenHeight <= 1080) {
            canvasWidth = 1000;
            canvasHeight = 650;
        }else {
        	 canvasWidth = 1500;
             canvasHeight = 1000;
        }
        canvas = new Canvas(canvasWidth, canvasHeight);
		board = new Board();
		this.stage = stage;
		stage.setTitle("PinBoard");

		configMenuBar();
		configToolBar();
		configCanvas();
		vbox.getChildren().add(separator);
		vbox.getChildren().add(label);

		Scene scene = new Scene(vbox);
		this.stage.setScene(scene);
		this.stage.show();
		Clipboard.getInstance().addListener(this);
		Clipboard.getInstance().clipboardChanged();
	}
	
	/**
	 * Configure Menu Bar
	 */
	private void configMenuBar() {
		MenuBar menuBar = new MenuBar(File, Edit, Tools);
		
		//File
		MenuItem newWindow = new MenuItem("New");
		newWindow.setOnAction((e) -> {
			new EditorWindow(new Stage());
		});
		MenuItem close = new MenuItem("Close");
		close.setOnAction((e) -> {
			Clipboard.getInstance().removeListener(this);
			stage.close();
		});
		
		// Edit
		MenuItem copy = new MenuItem("Copy");
		copy.setOnAction((e) -> {
			Clipboard.getInstance().copyToClipboard(selection.getContents());
			Clipboard.getInstance().clipboardChanged();
			
		});
		paste = new MenuItem("Paste");
		paste.setOnAction((e) -> {
			board.addClip(Clipboard.getInstance().copyFromClipboard());
			board.draw(canvas.getGraphicsContext2D());
		});
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction((e) -> {
			board.removeClip(selection.getContents());
			board.draw(canvas.getGraphicsContext2D());
		});
		
		// Tools
		MenuItem select = new MenuItem("Selection");
		select.setOnAction((e) -> {
			currentTool = new ToolSelection();
			label.setText(currentTool.getName(this));
		});
		MenuItem rect = new MenuItem("Rectangle");
		rect.setOnAction((e) -> {
			currentTool = new ToolRect();
			label.setText(currentTool.getName(this));
		});
		MenuItem ell = new MenuItem("Ellipse");
		ell.setOnAction((e) -> {
			currentTool = new ToolEllipse();
			label.setText(currentTool.getName(this));
		});
		MenuItem img = new MenuItem("Image");
		img.setOnAction((e) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
					new ExtensionFilter("All Files", "*.*"));
			File file = fileChooser.showOpenDialog(stage);
			System.out.println(file);
			currentTool = new ToolImage(file);
			label.setText(currentTool.getName(this));
		});
		
		Edit.getItems().addAll(copy, paste, delete);
		Tools.getItems().addAll(select, rect, ell, img);
		File.getItems().addAll(newWindow, close);
		vbox.getChildren().add(menuBar);
	}
	
	

	/**
	 * Configures toolbar
	 */
	private void configToolBar() {
		Separator verticalSep = new Separator(Orientation.VERTICAL);
		ColorPicker ColorCustom = new ColorPicker(Color.BLACK);
		ColorCustom.setOnAction((e) ->{
			Color choice = ColorCustom.getValue();
			board.setColor(choice);
		});
		ToolBar toolBar = new ToolBar(Select, Box, Ellipse, Img,verticalSep, ColorCustom);		
		Select.setOnAction((e) -> {
			currentTool = new ToolSelection();
			label.setText(currentTool.getName(this));
		});
		Box.setOnAction((e) -> {
			currentTool = new ToolRect();
			label.setText(currentTool.getName(this));
		});
		Ellipse.setOnAction((e) -> {
			currentTool = new ToolEllipse();
			label.setText(currentTool.getName(this));
		});
		Img.setOnAction((e) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
					new ExtensionFilter("All Files", "*.*"));
			File file = fileChooser.showOpenDialog(stage);
			System.out.println(file);
			currentTool = new ToolImage(file);
			label.setText(currentTool.getName(this));
		});
		vbox.getChildren().addAll(toolBar);
	}

	/**
	 * Configures canvas (mouse calls)
	 */
	private void configCanvas() {
		canvas.setOnMousePressed((e) -> {
			currentTool.press(this, e);
			currentTool.drawFeedback(this, canvas.getGraphicsContext2D());
		});
		canvas.setOnMouseDragged((e) -> {
			currentTool.drag(this, e);
			currentTool.drawFeedback(this, canvas.getGraphicsContext2D());
		});
		canvas.setOnMouseReleased((e) -> {
			currentTool.release(this, e);
			currentTool.drawFeedback(this, canvas.getGraphicsContext2D());
		});
		vbox.getChildren().add(canvas);
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return selection;
	}
	
	@Override
	public void clipboardChanged() {
		if(Clipboard.getInstance().isEmpty()){
			paste.setDisable(true);
		}
		else{
			paste.setDisable(false);
		}
	}
	
	
}
