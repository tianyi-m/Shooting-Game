
//Class representing Positions as integer duples.
public class Position {
	public int r;
	public int c;
	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public boolean equals(Position p) {
		return r==p.r && c==p.c;
	}
	
	public boolean equals(int r2, int y2) {
		return r==r2 && c==y2;
	}
}
