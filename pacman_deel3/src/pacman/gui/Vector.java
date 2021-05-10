package pacman.gui;

public class Vector {
	
	public final double x;
	public final double y;
	
	public Vector(double x, double y) { this.x = x; this.y = y; }
	
	public double getSize() { return Math.sqrt(x * x + y * y); }
	
	public Vector scaled(double factor) { return new Vector(x * factor, y * factor); }

	public Vector plus(Vector other) { return new Vector(x + other.x, y + other.y); }
	
	public Vector minus(Vector other) { return new Vector(x - other.x, y - other.y); }
	
	public double getAngle() { return Math.atan2(y, x) * 180 / Math.PI; }
	
}
