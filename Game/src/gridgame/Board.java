package gridgame;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the game board.
 * Always square.
 */
public class Board {
	
	public enum direction {up, down, left, right};
	public direction[] directions = {direction.up, direction.down, direction.left, direction.right};
	
	//A player or ai cannot be in the same location as a wall.
	//All locations must be with in the grid starting at (0, 0) with 
	//dimension size*size.
	public Position player;
	public Position ai;
	public Position bullet;
	public List<Position> walls;
	public int size;
	
	public Board() {
		player = new Position(0, 0);
		ai = new Position(1, 1);
		bullet = null;
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
	
	/**
	 * Returns whether the move is to a valid, empty location.
	 * Moves the player in a given direction if the move is valid and change is true.
	 * 
	 * @param d the direction to move in
	 * @param change whether to update the player's position.
	 * @return whether the move was valid
	 */
	public boolean checkPlayerMove(String d, boolean change) {
		switch(d) {
		case "up":
			if (player.r-1 >= 0 && isEmpty(player.r-1, player.c)) {
				if (change) player.r -= 1;
				return true;
			}
			break;
		case "down":
			if (player.r+1 < size && isEmpty(player.r+1, player.c)) {
				if (change) player.r += 1;
				return true;
			}
			break;
		case "left":
			if (player.c-1 >= 0 && isEmpty(player.r, player.c-1)) {
				if (change) player.c -= 1;
				return true;
			}
			break;
		case "right":
			if (player.c+1 < size && isEmpty(player.r, player.c+1)) {
				if (change) player.c += 1;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isEmpty(Position p) {
		return !p.equals(player) && !p.equals(ai) && !isWall(p);
	}
	public boolean isEmpty(int r, int c) {
		return !player.equals(r,c) && !ai.equals(r,c) && !isWall(r, c);
	}
	
	public boolean isEligible(int r, int c) {
		return !ai.equals(r,c) && !isWall(r, c) && (r>=0 && r < size) && (c >=0 && c < size);
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
	
	public boolean isTarget(int r, int c) {
		return ai.equals(r, c);
	}
	
	public void setBulletPosition(int r, int c) {
		if (bullet == null) {
			bullet = new Position(r, c);
		} else {
			bullet.r = r;
			bullet.c = c;
		}	
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
	
	public String toStringWithBullet(boolean hit_target, boolean hit_wall) {
		String str = "";
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				String curr_symbol = getSymbol(i, j);
				if (hit_target && curr_symbol == "X") {
				}
				if (hit_wall && curr_symbol == "o") {
				}
				str += curr_symbol;
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
		} else if (bullet != null) {
			if (bullet.equals(i, j)) {
				System.out.println("symbol o: inside");
				return "o";
			}
		}
		return "     ";
	}
}
