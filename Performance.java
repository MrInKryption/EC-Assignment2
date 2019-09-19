import java.util.ArrayList;
import java.util.Collections;


public class Performance
{

    //Evolutionary Algorithm
    private TSPEvolutionaryAlgorithm evolution;

    //fitness implementaitons
    private InverTwoOpFitnessFunction inver_two_op;

    //mutators
    private SuperNovaMutation supa;

    //crossovers
    private MeanTSPCrossover crossover;

    //Populations for - comparison
    private ArrayList<TSP_Instance> pop;

    //sets up all populations with an initial selection of instances
    public Performance(int problem_size, double min, double max, int population_size)
    {
        pop = new ArrayList<TSP_Instance>();
        for(int i = 0; i < population_size; i++)
        {
            TSP_Instance new_tsp = new TSP_Instance(problem_size, min, max);
            pop.add(new_tsp);
        }

        evolution = new TSPEvolutionaryAlgorithm(0.05, 4, 1000);
        supa = new SuperNovaMutation(0, 10, 0, 10);
        crossover = new MeanTSPCrossover();   
        inver_two_op = new InverTwoOpFitnessFunction();
    }

    public void fitnessPairInverTwoOp()
    {
        //run EA with InverTwoOptFitnessFunction
        evolution.evolutionaryAlgorithm(pop, inver_two_op, supa, crossover);
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