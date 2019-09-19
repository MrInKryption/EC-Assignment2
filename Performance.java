import java.util.ArrayList;
import java.util.Collections;


public class Performance
{
    private InverOver inver; //inverOver algorithm
    //private 2op two; this will be used when we have it
    //private algorithm3 alg3; this will be used when we have it

    //----------------------------------------------this section is probably not needed-----------

    //Populations for - comparison
    private ArrayList<TSP_Instance> inver_2op_1;  //population for inver vs 2op
    private ArrayList<TSP_Instance> inver_alg_1;  //population for inver vs our algorithm
    private ArrayList<TSP_Instance> alg_2op_1;    //population for our alg vs 2op
    
    //Populations for / comparison
    private ArrayList<TSP_Instance> inver_2op_2;  //population for inver vs 2op
    private ArrayList<TSP_Instance> inver_alg_2;  //population for inver vs our algorithm
    private ArrayList<TSP_Instance> alg_2op_2;    //population for our alg vs 2op


    //sets up all populations with an initial selection of instances
    public Performance(int problem_size, double min, double max, int population_size)
    {
        for(int i = 0; i < population_size; i++)
        {
            TSP_Instance new_tsp = new TSP_Instance(problem_size, min, max);

            inver_2op_1.add(new_tsp);
            inver_alg_1.add(new_tsp);
            alg_2op_1.add(new_tsp);

            inver_2op_2.add(new_tsp);
            inver_alg_2.add(new_tsp);
            alg_2op_2.add(new_tsp);
        }
        

    }

    //--------------------------------------------------------------------------------------------


    //create a population of paths of size pop_size from an instance
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

    public void fitnessPairInverTwoOp(TSP_Instance instance)
    {
        //run EA with InverTwoOptFitnessFunction 
    }

    public void fitnessPairInverTwoOp2(TSP_Instance instance)
    {
        //run EA with InverTwoOptFitnessFunction2 
    }

    public void fitnessPairInverAlg(TSP_Instance instance)
    {
        //run EA with InverAlgFitnessFunction 
    }

    public void fitnessPairInverAlg2(TSP_Instance instance)
    {
        //run EA with InverAlgFitnessFunction2 
    }

}