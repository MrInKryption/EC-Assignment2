public class Point
{
    private double x;
    private double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void swapXY()
    {
        double temp = x;
        x = y;
        y = temp;
    }
    
    // Returns the distance between this point and the
    // other point.
    public double distance(Point other)
    {
        double x_dist = Math.abs(x - other.x);
        double y_dist = Math.abs(y - other.y);
        x_dist = Math.pow(x_dist, 2);
        y_dist = Math.pow(y_dist, 2);
        
        double dist = Math.sqrt(x_dist + y_dist);
        return dist;
    }
    
    // If this point is A, and the other point is B,
    // Returns a new point C = A - B.
    // Returns a new point so doesn't change parents.
    public Point subtract(Point other)
    {
        double x_diff = x - other.x;
        double y_diff = y - other.y;
        
        Point result = new Point(x_diff, y_diff);
        return result;
    }
    
    // If this point is A, and the other point is B,
    // Returns a new point C = A + B.
    // Returns a new point so doesn't change parents.
    public Point add(Point other)
    {
        double x_new = x + other.x;
        double y_new = y + other.y;
        
        Point result = new Point(x_new, y_new);
        return result;
    }
    
    // Multiplies this point by the scalar factor. 
    // Warning: Modifies the original point!
    public void scalar_factor(double factor)
    {
        x = x * factor;
        y = y * factor;
    }
}
