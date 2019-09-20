import java.util.ArrayList;

public class Performance
{

    //Evolutionary Algorithm
    private TSPEvolutionaryAlgorithm evolution;

    //fitness implementaitons
    private InverTwoOpFitnessFunction inver_two_op;
    private TwoOptAlgFitnessFunction two_opt_alg;
    private TwoOptAlgFitnessFunction2 two_opt_alg2;

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

        evolution = new TSPEvolutionaryAlgorithm(0.05, 4, 2); // 2 = 10000
        supa = new SuperNovaMutation(0, 10, 0, 10);
        crossover = new MeanTSPCrossover();   
        inver_two_op = new InverTwoOpFitnessFunction();
        two_opt_alg = new TwoOptAlgFitnessFunction();
        two_opt_alg2 = new TwoOptAlgFitnessFunction2();
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

    public void fitnessPairTwoOptAlg() {
        evolution.evolutionaryAlgorithm(pop, two_opt_alg, supa, crossover);
    }

    public void fitnessPairTwoOptAlg2() {
        evolution.evolutionaryAlgorithm(pop, two_opt_alg2, supa, crossover);
    }

}
