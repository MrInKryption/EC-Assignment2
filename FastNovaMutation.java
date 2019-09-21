import java.util.ArrayList;
import java.util.Random;

// Class for performing a mutation for TSP.
// This is based on supernova mutation, but designed to be
// faster by only considering six points (three are imploded,
// three are exploded)
public class FastNovaMutation implements ITSPMutation
{   
    // Number of points to target in each iteration. 
    int targets;
    //added to push
    // Constructor for fastnova mutator.
    // Target count is the number of points to alter in each mutation -
    // targetCount points are exploded, and targetCount points are imploded. 
    public FastNovaMutation(int targetCount)
    {
        targets = targetCount;
    }
    
    // Returns a nova mutated TSP instance based on parent.
    // TargetCount points are exploded at least 10% further away from
    // the mean of all points selected. 
    // TargetCount points are imploded towards the center.  
    public TSP_Instance mutate(TSP_Instance parent)
    {
        // Copy the arraylist of points. 
        ArrayList<Point> points = new ArrayList<Point>(parent.getCoordinates());
        ArrayList<Integer> explosionPoints = new ArrayList<Integer>();
        ArrayList<Integer> implosionPoints = new ArrayList<Integer>();
        int length = points.size();
        Random rand = RandomNumberGenerator.getRandom();
        
        // Choose the random points to explode and implode (targets points
        // for each)
        for (int i = 0; i < targets; i++)
        {
            explosionPoints.add(rand.nextInt(length));
            implosionPoints.add(rand.nextInt(length));
        }
        
        double x_center = 0;
        double y_center = 0;
        
        // Determine the mean of all the points selected - this is to be
        // the exlosion/implosion center. 
        for (int j = 0; j < targets; j++)
        {
            x_center += points.get(explosionPoints.get(j)).getX();
            x_center += points.get(implosionPoints.get(j)).getX();
            y_center += points.get(explosionPoints.get(j)).getY();
            y_center += points.get(implosionPoints.get(j)).getY();
        }
        
        Point center = new Point(x_center, y_center);
        
        // Explode and implode the points selected. 
        for (int k = 0; k < targets; k++)
        {
            int explodeIndex = explosionPoints.get(k);
            int implodeIndex = implosionPoints.get(k);
            Point explodeTarget = points.get(explodeIndex);
            Point implodeTarget = points.get(implodeIndex);
            points.set(explodeIndex, explode(explodeTarget, center, explodeTarget.distance(center) * 1.1));
            points.set(implodeIndex, implode(implodeTarget, center, implodeTarget.distance(center) * 1.1));
        }
        
        return new TSP_Instance(points);
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
