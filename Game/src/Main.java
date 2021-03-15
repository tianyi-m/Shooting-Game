import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class handling graphics.
 * Uses JavaFX.
 */
public class Main extends Application {
	// Called only when launched from terminal.
	// Don't modify.
	public static void main(String[] args) {
		launch(args);
	}
	
	// Entry point to JavaFX program.
	public void start(Stage stage) {
		// Set up the window.
		stage.setTitle("A Game!");
		VBox root = new VBox();
		stage.setScene(new Scene(root, 100, 100));	//Should probably make this dynamic.
		stage.setResizable(false);
		
		// Prepare the board:
		Board board = testBoard();

		
		//Create graphics elements here.
		
		//Display the board:
		Text t = new Text(board.toString());
		//Sample event. Changes the board whenever the player clicks on it.
		t.setOnMouseClicked(
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent E) {
						if (board.player.r==0) {
							board.player.r = 2;
						} else {
							board.player.r = 0;
						}
					}
				});
		root.getChildren().add(t);
		
		
		// Animation:
		long startTime = System.nanoTime();	//Unused right now, but can be referenced for time-based events.
		//Basically, everything in the following block runs once per frame.
		new AnimationTimer() {
			
			public void handle(long currentTime) {
				//Update any elements of the scene that might have been changed.
				t.setText(board.toString());
			}
		}.start();
		
		
		stage.show();
	}
	
	// Simple test board.
	private Board testBoard() {
		List<Position> walls = new ArrayList<Position>();
		walls.add(new Position(0, 1));
		walls.add(new Position(1, 0));
		
		Board board = new Board(3, walls);
		return board;
	}
}
