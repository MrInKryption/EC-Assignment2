import java.util.ArrayList;
import java.util.Random;

// Performs random crossover on the two parents.
// For each point in each parent, there is a 50% chance for it to go to
// the first child and a 50% chance for it to go to the second child. 
public class RandomTSPCrossover
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
            double temp = rand.nextDouble();
            
            // If less than 0.5, then first parent's point
            // is copied to the first child and second parent's
            // point is copied to the second child.
            // If more than 0.5 this is reversed. 
            if (temp < 0.5)
            {
                resultA.add(first_points.get(i));
                resultB.add(second_points.get(i));
            }
            else
            {
                resultA.add(second_points.get(i));
                resultB.add(first_points.get(i));
            }
        }
        
        // Place the children in an arraylist and return them.
        ArrayList<TSP_Instance> finalResult = new ArrayList<TSP_Instance>();
        finalResult.add(new TSP_Instance(resultA));
        finalResult.add(new TSP_Instance(resultB));
        
        return finalResult;
    }
}
