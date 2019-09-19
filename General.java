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
}