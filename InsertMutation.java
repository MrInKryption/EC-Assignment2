import java.util.Random;
import java.util.ArrayList;

public class InsertMutation implements ITSPMutation
{
	public TSP_Instance mutate(TSP_Instance parent)
	{
		// Copy permutation to avoid changing the orignal. 
		ArrayList<Point> points = new ArrayList<Point>(parent.getCoordinates());
		ArrayList<Point> result = new ArrayList<Point>();
		
		int length = points.size();
		Random rand = RandomNumberGenerator.getRandom();
		int start = rand.nextInt(length);
		int end = rand.nextInt(length);
		int temp;
		int current;
		
		// If start is before end, swap start and end. 
		if (start > end)
		{
			temp = start;
			start = end;
			end = temp;
		}
		
		// Start at the item before end
		current = end - 1;
		double nextPoint;

		// Until we reach start
		while (current > start)
		{
			// Move the x coord which started at end
			// one space back to the next point in the sequence.
			nextPoint = points.get(current-1).getX();
			points.get(current-1).setX(points.get(current).getX());
			points.get(current).setX(nextPoint);
			current -= 1;
		}
		return new TSP_Instance(points);
	}
}