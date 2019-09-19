import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;

public class PmxCrossover implements ITSPCrossover
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
        int start = rand.nextInt(length);
        int end = rand.nextInt(length);
        int temp;

        // If start is before end, swap start and end. 
        if (start > end)
        {
            temp = start;
            start = end;
            end = temp;
        }
        
        // EDIT THESE
        resultA = crossoverhelper(first_points, second_points, start, end);
        resultB = crossoverhelper(second_points, first_points, start, end);
        
        // Place the children in an arraylist and return them.
        ArrayList<TSP_Instance> finalResult = new ArrayList<TSP_Instance>();
        finalResult.add(new TSP_Instance(resultA));
        finalResult.add(new TSP_Instance(resultB));
        return finalResult;
    }
    
    private ArrayList<Point> crossoverhelper(ArrayList<Point> firstParent, ArrayList<Point> secondParent, int start, int end)
    {
        ArrayList<Point> child = new ArrayList<Point>();
        HashSet<Point> points_in_child = new HashSet<Point>();
        int size = firstParent.size();
        int child_size = 0;
        int parent_index, child_index;
        
        // Child starts off filled with null points    
        for (int i = 0; i < size; i++)
        {
            child.add(null);
        }
        
        // Copy specified section from first parent to child
        for (int j = start; j < end; j++)
        {
            child.set(j, firstParent.get(j));
            // Add the points in the child to the set. 
            points_in_child.add(child.get(j));
            child_size += 1;
        }
        
        // New stuff for PMX crossover.
        for (int k = start; k < end; k++)
        {
            // If point at index k is not in the child. 
            if (points_in_child.contains(secondParent.get(k)) == false)
            {
                // Get the point we want to add. 
                Point point_to_add = secondParent.get(k);
                // Find the point at that position in the parent, as
                // that is the point whose position in sequence we want to place
                // the above point in. 
                Point point_to_replace = child.get(k);
                
                // Find the position of point_to_replace. 
                for (int l = 0; l < size; l++)
                {
                    if (secondParent.get(l) == point_to_replace)
                    {
                        // If this location is null in the child,
                        // Put the point there. 
                        if (child.get(l) == null)
                        {
                            child.set(l, point_to_add);
                            points_in_child.add(child.get(l));
                            child_size += 1;
                        }
                    }
                }
            }
        }
        
        parent_index = end;
        child_index = end;
        while(child_size < size)
        {
            // Find a valid location to place the next point. 
            if (child.get(child_index) != null)
            {
                child_index = (child_index + 1) % size;
                continue;
            }
            
            // If the current point in the parent is already in the child,
            // move to the next point. 
            if (points_in_child.contains(secondParent.get(parent_index)) == true)
            {
                parent_index = (parent_index + 1) % size;
                continue;
            }
            
            // Add the next point to the child. 
            child.set(child_index, secondParent.get(parent_index));
            points_in_child.add(child.get(child_index));
            
            // Update the child and parent indexes. 
            parent_index = (parent_index + 1) % size;
            child_index = (child_index + 1) % size;
            child_size += 1;
        }
        return child;
    }
}
