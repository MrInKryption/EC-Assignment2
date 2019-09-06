import java.util.ArrayList;
import java.util.Random;

// Class for performing a mutation for TSP.
// This is a composite of the implosion and explosion mutations - 
// Points in the radius have a 50% chance of being imploded and
// a 50% chance of being exploded.
public class SuperNovaMutation implements ITSPMutation
{
    // Explosion centres will only be placed between (min_x, min_y)
    // And (max_x, max_y). 
    private double min_x;
    private double max_x;
    private double min_y;
    private double max_y;
    
    // Explosion radius is between these two values. 
    private double min_nova_size;
    private double max_nova_size;
    
    // Constructor for supernova mutator.
    // x_min should be the smallest x value in the instance intended to be mutated.
    // y_min should be the smallest y value in the instance intended to be mutated.
    // The max should be the largest x and y value respecively in the instance intended to be mutated.
    // The min and max nova size is set to be 20% and 60% of the smaller of the range of x values and y values. 
    public SuperNovaMutation(double x_min_value, double y_min_value, double x_max_value, double y_max_value)
    {
        // Min and max values are used to determine where the nova can occur.
        min_x = x_min_value;
        min_y = y_min_value;
        max_x = x_max_value;
        max_y = y_max_value;
        
        // The min of difference between x min and y min
        // Is used to cap the radius of a nova. 
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
    
    // Returns a nova mutated TSP instance based on parent. 
    public TSP_Instance mutate(TSP_Instance parent)
    {
        ArrayList<Point> points = new ArrayList<Point>(parent.getCoordinates());
        ArrayList<Point> result = new ArrayList<Point>();
        Random rand = RandomNumberGenerator.getRandom();
        
        // Generate the centre of the nova.
        double x_center = min_x + (max_x - min_x) * rand.nextDouble();
        double y_center = min_y + (max_y - min_y) * rand.nextDouble();
        Point center = new Point(x_center, y_center);
        
        // Get the radius of the nova.
        double radius = min_nova_size + (max_nova_size - min_nova_size) * rand.nextDouble();
       
        // For each point 
        for (Point point : points)
        {
            double temp = rand.nextDouble();
            
            // 50% chance of exploding the point, 50% chance of imploding it.
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
    
    // Explodes a point within radius of center,
    // pushing it outside of the circle with center and radius.
    private Point explode(Point target, Point center, double radius)
    {
        // Get distance from center of explosion.
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
    
    // Imploads a point within radius, bringing it close to center.
    private Point implode(Point target, Point center, double radius)
    {
        // Get the distance from the center of the nova.
        double distance = center.distance(target);
        
        // Ignore points outside of the radius.
        if (distance > radius)
        {
            return target;
        }
        
        // Determine the power of the explosion from a normal distribution.
        Random rand = RandomNumberGenerator.getRandom();
        double power = Math.abs(rand.nextGaussian());
        
        // Performs center - target to get direction vector from center to target. 
        Point result = center.subtract(target);
        
        // Set power to radius if it is smaller. 
        if (radius < power)
        {
            power = radius;
        }
        
        // Multiply direction vector by factor to get dist from center
        result.scalar_factor(power);
        
        // Add the center to the direction vector to get the final point.
        result = result.add(target);
        
        return target;
    }
}
