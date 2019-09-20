import java.util.ArrayList;
import java.util.Collections;

public class General
{
    public ArrayList<ArrayList<Point>> create_population(TSP_Instance instance, int pop_size)
    {
        ArrayList<ArrayList<Point>> pop = new  ArrayList<ArrayList<Point>>();
        ArrayList<Point> points = instance.getCoordinates();
        ArrayList<Point> points_copy;

        for(int i = 0; i < pop_size; i++)
        {
            Collections.shuffle(points);
            points_copy = new ArrayList<Point>(points);
            pop.add(points_copy);
        }

        return pop;
    }

     //Kinda like index%size, but it handles -1 to making indexing easier
        //Direction is False for left and True for right
        public int shift(int index, int size, Boolean direction){
            if(direction == false){
                if(index == 0){
                    return size - 1;
                }
                else{
                    return index - 1;
                }
            }
            else{
                if(index == size - 1){
                    return 0;
                }
                else{
                    return index + 1;
                }
            }
        }
}