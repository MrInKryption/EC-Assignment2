import java.util.ArrayList;
import java.util.Random;

// Performs random crossover on the two parents.
// For each point in each parent, there is a 50% chance for it to go to
// the first child and a 50% chance for it to go to the second child. 
public class MeanTSPCrossover implements ITSPCrossover
{
    public ArrayList<TSP_Instance> crossover(TSP_Instance first_parent, TSP_Instance second_parent)
    {
        // Extract the points from the parents. 
        ArrayList<Point> first_points = first_parent.getCoordinates();
        ArrayList<Point> second_points = second_parent.getCoordinates();
        
        // Create new arraylists to represent the children. 
        ArrayList<Point> resultA = new ArrayList<Point>();
        ArrayList<Point> resultB = new ArrayList<Point>();
        int length = first_points.size();
        
        // Get the random number generator. 
        Random rand = RandomNumberGenerator.getRandom();
        
        // For each point
        for (int i = 0; i < length; i++)
        {
            // Alpha and beta are used to weight the points effect on the children.
            // Higher alpha means the first parent has more weight on the point in 
            // the first child (and lower weight on the point in the second child)
            // and vice versa. 
            double alpha = rand.nextDouble();
            double beta = 1 - alpha;
            
            // Get the point at position i in each list. 
            Point firstPoint = first_points.get(i);
            Point secondPoint = second_points.get(i);
            
            // Calculate the new points for the children.
            // The first child is alpha * first point + beta * second point.
            // The second child is beta * first point + alpha * second point. 
            double x1 = alpha * firstPoint.getX() + beta * secondPoint.getX();
            double x2 = beta * firstPoint.getX() + alpha * secondPoint.getX();
            
            double y1 = alpha * firstPoint.getY() + beta * secondPoint.getY();
            double y2 = beta * firstPoint.getY() + alpha * secondPoint.getY();
            
            // Create the new points and add them to the list. 
            Point firstNewPoint = new Point(x1, y1);
            Point secondNewPoint = new Point(x2, y2);
            
            resultA.add(firstNewPoint);
            resultB.add(secondNewPoint);
        }
        
        // Place the children in an arraylist and return them.
        ArrayList<TSP_Instance> finalResult = new ArrayList<TSP_Instance>();
        finalResult.add(new TSP_Instance(resultA));
        finalResult.add(new TSP_Instance(resultB));
        
        return finalResult;
    }
}
