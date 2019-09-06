import java.util.ArrayList;
import java.util.Random;

public class RandomTSPCrossover
{
    public ArrayList<TSP_Instance> crossover(TSP_Instance first_parent, TSP_Instance second_parent)
    {
        ArrayList<Point> first_points = first_parent.getCoordinates();
        ArrayList<Point> second_points = second_parent.getCoordinates();
        ArrayList<Point> resultA = new ArrayList<Point>();
        ArrayList<Point> resultB = new ArrayList<Point>();
        int length = first_points.size();
        Random rand = RandomNumberGenerator.getRandom();
        
        for (int i = 0; i < length; i++)
        {
            double temp = rand.nextDouble();
            
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
        
        ArrayList<TSP_Instance> finalResult = new ArrayList<TSP_Instance>();
        finalResult.add(new TSP_Instance(resultA));
        finalResult.add(new TSP_Instance(resultB));
        
        return finalResult;
    }
}
