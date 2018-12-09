package Model;

public class Position
{
	int x;
	int y;
	
	public Position(){
            x=0;y=0;
	}
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int get_X() {
		return x;
	}
	public void set_X(int x) {
		this.x = x;
	}
	public int get_Y() {
		return y;
	}
	public void set_Y(int y) {
		this.y = y;
	}
	public boolean isEqual(Position pos)
	{
		return (x==pos.x && y==pos.y);
	}
	public boolean isEqual(int x, int y)
	{
		return (this.x==x && this.y==y);
	}
}
