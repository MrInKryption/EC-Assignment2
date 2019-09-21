import java.util.ArrayList;

public class Performance
{

    //Evolutionary Algorithm
    private TSPEvolutionaryAlgorithm evolution;

    //fitness implementaitons
    private InverTwoOpFitnessFunction inver_two_op;
    private InverTwoOpFitnessFunction2 inver_two_op_2;
    private TwoOptAlgFitnessFunction two_opt_alg;
    private TwoOptAlgFitnessFunction2 two_opt_alg2;
    private InverAlgFitnessFunction inver_alg;
    private InverAlgFitnessFunction2 inver_alg_2;

    //mutators
    private SuperNovaMutation supa;
    private FastNovaMutation fast;
    private OffsetMutation offset;

    //crossovers
    private MeanTSPCrossover crossover;
    private SchoolyardTeamsTSPCrossover school;

    //Populations for - comparison
    private ArrayList<TSP_Instance> pop;

    //sets up all populations with an initial selection of instances
    public Performance(int problem_size, double min, double max, int population_size, int numGenerations)
    {
        pop = new ArrayList<TSP_Instance>();
        for(int i = 0; i < population_size; i++)
        {
            TSP_Instance new_tsp = new TSP_Instance(problem_size, min, max);
            pop.add(new_tsp);
        }

        evolution = new TSPEvolutionaryAlgorithm(0.05, 4, numGenerations);
        supa = new SuperNovaMutation(0, 10, 0, 10);
        fast = new FastNovaMutation(5);
        offset = new OffsetMutation();
        
        crossover = new MeanTSPCrossover();
        school = new SchoolyardTeamsTSPCrossover();

        inver_two_op = new InverTwoOpFitnessFunction();
        inver_two_op_2 = new InverTwoOpFitnessFunction2();
        two_opt_alg = new TwoOptAlgFitnessFunction();
        two_opt_alg2 = new TwoOptAlgFitnessFunction2();
        inver_alg = new InverAlgFitnessFunction();
        inver_alg_2 = new InverAlgFitnessFunction2();
    }

    public void fitnessPairInverTwoOp(int mutate, int cross)
    {
        //run EA with InverTwoOptFitnessFunction
        switch(cross)
        {
            case 1:
                switch(mutate)
                {
                    case 1:
                        evolution.evolutionaryAlgorithm(pop, inver_two_op, supa, crossover);
                        break;
                    case 2:
                        evolution.evolutionaryAlgorithm(pop, inver_two_op, fast, crossover);
                        break;
                    case 3:
                        evolution.evolutionaryAlgorithm(pop, inver_two_op, offset, crossover);
                        break;
                }
                break;

            case 2:
            switch(mutate)
            {
                case 1:
                    evolution.evolutionaryAlgorithm(pop, inver_two_op, supa, school);
                    break;
                case 2:
                    evolution.evolutionaryAlgorithm(pop, inver_two_op, fast, school);
                    break;
                case 3:
                    evolution.evolutionaryAlgorithm(pop, inver_two_op, offset, school);
                    break;
            }
                break;
        }






        evolution.evolutionaryAlgorithm(pop, inver_two_op, supa, crossover);
    }

    public void fitnessPairInverTwoOp2()
    {
        //run EA with InverTwoOptFitnessFunction2 
        evolution.evolutionaryAlgorithm(pop, inver_two_op_2, supa, crossover);
    }

    public void fitnessPairInverAlg()
    {
        evolution.evolutionaryAlgorithm(pop, inver_alg, supa, crossover); 
    }

    public void fitnessPairInverAlg2()
    {
        evolution.evolutionaryAlgorithm(pop, inver_alg_2, supa, crossover); 
    }

    public void fitnessPairTwoOptAlg() {
        evolution.evolutionaryAlgorithm(pop, two_opt_alg, supa, crossover);
    }

    public void fitnessPairTwoOptAlg2() {
        evolution.evolutionaryAlgorithm(pop, two_opt_alg2, supa, crossover);
    }

}
