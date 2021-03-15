import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the game board.
 * Always square.
 */
public class Board {
	//A player or ai cannot be in the same location as a wall.
	//All locations must be with in the grid starting at (0, 0) with 
	//dimension size*size.
	public Position player;
	public Position ai;
	public List<Position> walls;
	public int size;
	
	public Board() {
		player = new Position(0, 0);
		ai = new Position(1, 1);
	}
	
	public Board(int s) {
		this();
		size = s;
		walls = new ArrayList<Position>();
	}
	
	public Board(int s, List<Position> w) {
		this();
		size = s;
		walls = w;
	}
	
	/*
	 * Returns whether the operation was successful. 
	 * Fails if the Position is not empty.
	 */
	public boolean addWall(int i, int j) {
		Position p = new Position(i, j);
		if (isEmpty(p)) {
			walls.add(p);
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(Position p) {
		return !p.equals(player) && !p.equals(ai) && !isWall(p);
	}
	
	public boolean isWall(Position p) {
		for (Position q : walls) {
			if (q.equals(p)) return true;
		}
		return false;
	}
	
	public boolean isWall(int r, int c) {
		for (Position q : walls) {
			if (q.equals(r, c)) return true;
		}
		return false;
	}
	
	public String toString() {
		String str = "";
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				str += getSymbol(i, j);
			}
			str += '\n';
		}
		return str;
	}
	
	public String getSymbol(int i, int j) {
		if (player.equals(i, j)) {
			return "O";
		} else if (ai.equals(i, j)) {
			return "X";
		} else if (isWall(i, j)) {
			return "=";
		}
		return " ";
	}
}
