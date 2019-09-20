import java.util.ArrayList;
import java.util.Random;

// Class for performing a mutation for TSP.
// It generates one set of offsets in the begining and for every point
// it applies that offset same with a random sign
public class OffsetMutation implements ITSPMutation
{   
    public TSP_Instance mutate(TSP_Instance parent)
    {
        // Set our variables in needed. Offset only gained once
        Random rand = RandomNumberGenerator.getRandom();
        boolean sign_x, sign_y;
        float offset_x = rand.nextFloat();
        float offset_y = rand.nextFloat();

        // Copy the arraylist of points. 
        ArrayList<Point> points = new ArrayList<Point>(parent.getCoordinates());
        int length = points.size();

        for(int i = 0; i < length; i++){
            sign_x = rand.nextBoolean();
            sign_y = rand.nextBoolean();
            points.get(i).setX(points.get(i).getX() + (offset_x * boolean_to_sign(sign_x)));
            points.get(i).setY(points.get(i).getY() + (offset_y * boolean_to_sign(sign_y)));
        }
        
        return new TSP_Instance(points);
    }

    //If true, it returns 1, if false it returns -1
    private int boolean_to_sign(boolean value)
    {
        return (value ? 1 : -1);
    }
}
