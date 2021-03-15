package gridgame;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	public enum direction {up, down, left, right};
	// Entry point to JavaFX program.
	public void start(Stage stage) {
		// Set up the window.
		stage.setTitle("A Game!");
		VBox root = new VBox();
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);	//Should probably make this dynamic.
		stage.setResizable(false);
		
		// Prepare the board:
		Board board = testBoard();

		
		//Create graphics elements here.
		
		//Display the board:
		Text t = new Text(board.toString());
		root.getChildren().add(t);
		
		//Sample event. Changes the board whenever the player clicks on it.
		scene.setOnKeyPressed(
				new EventHandler<KeyEvent>() {
					public void handle(KeyEvent E) {
						KeyCode keyPressed = E.getCode();
						if (keyPressed == KeyCode.UP) {
							board.checkPlayerMove("up", true);
						} else if (keyPressed == KeyCode.RIGHT) {
							board.checkPlayerMove("right", true);
						} else if (keyPressed == KeyCode.DOWN) {
							board.checkPlayerMove("down", true);
						} else if (keyPressed == KeyCode.LEFT) {
							board.checkPlayerMove("left", true);
						}
						t.setText(board.toString());
					}
				});
		scene.setOnMouseClicked(
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent E) {
						double mouse_x = E.getScreenX();
						double mouse_y = E.getScreenY();
						int player_x = board.player.r;
						int player_y = board.player.c;
						double a = (mouse_y-player_y)/(mouse_x-player_x);
						double b = player_y-a*player_x;
						int bullet_x = player_x;
						int bullet_y = player_y;
						board.setBulletPosition(bullet_x, bullet_y);
						while (board.isEligible(bullet_x, bullet_y)) {
							System.out.println(bullet_x+" "+bullet_y+" "+board.size);
							if (mouse_x < player_x) {
								bullet_x += 1;
							} else {
								bullet_x -= 1;
							}
							bullet_y = (int) (bullet_x*a+b);
							boolean hit_target = board.isTarget(bullet_x, bullet_y);
							boolean hit_wall = board.isWall(bullet_x, bullet_y);
							board.setBulletPosition(bullet_x, bullet_y);
							t.setText(board.toStringWithBullet(hit_target, hit_wall));
						}
					}
				});
		
		// Animation:
		long startTime = System.nanoTime();	//Unused right now, but can be referenced for time-based events.
		//Basically, everything in the following block runs once per frame.
		new AnimationTimer() {
			public void handle(long now) {
				//Update any elements of the scene that might have been changed.
				//t.setText(board.toString());
			}
		}.start();	
		stage.show();
	}
	
	// Simple test board.
	private Board testBoard() {
		List<Position> walls = new ArrayList<Position>();
		walls.add(new Position(0, 1));
		walls.add(new Position(1, 0));
		
		Board board = new Board(100);
		return board;
	}
}
