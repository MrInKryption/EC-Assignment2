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
    
    public int distance(Point other)
    {
        x_dist = Math.abs(x - other.x);
        y_dist = Math.abs(y - other.y);
        x_dist = Math.pow(x_dist, 2);
        y_dist = Math.pow(y_dist, 2);
        
        dist = Math.sqrt(x_dist, y_dist);
        return dist;
    }
}
