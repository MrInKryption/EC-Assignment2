import java.util.ArrayList;
import java.util.Random;

public class SuperNovaMutation implements ITSPMutation
{
    // Explosion centres will only be placed between (min_x, min_y)
    // And (max_x, max_y). 
    private double min_x;
    private double max_x;
    private double min_y;
    private double max_y;
    
    private double min_nova_size;
    private double max_nova_size;
    
    public SuperNovaMutation(double x_min_value, double y_min_value, double x_max_value, double y_max_value)
    {
        min_x = x_min_value;
        min_y = y_min_value;
        max_x = x_max_value;
        max_y = y_max_value;
        
        double x_dist = max_x - min_x;
        double y_dist = max_y - min_y;
        
        double dist = 0;
        
        if (x_dist > y_dist)
        {
            dist = x_dist;
        }
        else
        {
            dist = y_dist;
        }
        
        // Novas are between 20% and 60% of the size of the maximum distance
        // Between points. 
        min_nova_size = dist * 0.2;
        max_nova_size = dist * 0.6;
    }
    
    public TSP_Instance mutate(TSP_Instance parent)
    {
        ArrayList<Point> points = new ArrayList<Point>(parent.getCoordinates());
        ArrayList<Point> result = new ArrayList<Point>();
        Random rand = RandomNumberGenerator.getRandom();
        
        // Get the centre of the nova.
        double x_center = min_x + (max_x - min_x) * rand.nextDouble();
        double y_center = min_y + (max_y - min_y) * rand.nextDouble();
        
        System.out.println(x_center + "," + y_center);
        Point center = new Point(x_center, y_center);
        center = new Point((min_x + max_x)/2, (min_y + max_y)/2);
        // Get the radius of the nova.
        double radius = min_nova_size + (max_nova_size - min_nova_size) * rand.nextDouble();
       
        System.out.println(radius);
        
        for (Point point : points)
        {
            double temp = rand.nextDouble();
            
            if (temp < 0.5)
            {
                point = explode(point, center, radius);
                result.add(point);
            }
            else
            {
                point = implode(point, center, radius);
                result.add(point);
            }
        }
        
        return new TSP_Instance(result);
    }
    
    private Point explode(Point target, Point center, double radius)
    {
        double distance = center.distance(target);
        // Ignore points not within the explosion.
        if (distance > radius)
        {
            return target;
        }
        
        // Determine the power of the nova (ie - how far to carry this
        // point out of the radius).
        // Drawn from an exponential distribution. 
        Random rand = RandomNumberGenerator.getRandom();
        double power = (Math.log(1 - rand.nextDouble()) / -0.1) + radius;
        
        // Get difference between target and center.
        Point result = center.subtract(target);
        
        // Divide result by distance.
        result.scalar_factor(1 / distance);
        
        // Multiply result by factor. 
        result.scalar_factor(power);
        
        // Optimisation: Make function of add which doesn't make a copy.
        result = result.add(center);
        
        return result;
    }
    
    private Point implode(Point target, Point center, double radius)
    {
        double distance = center.distance(target);
        if (distance > radius)
        {
            return target;
        }
        
        Random rand = RandomNumberGenerator.getRandom();
        double power = Math.abs(rand.nextGaussian());
        
        Point result = center.subtract(target);
        
        if (radius < power)
        {
            power = radius;
        }
        
        result.scalar_factor(power);
        
        result = result.add(target);
        
        return target;
    }
}
