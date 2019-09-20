import java.util.ArrayList;

public class TwoOptAlgFitnessFunction implements ITSPFitnessFunction
{
    private LocalSearch ls;
    private TwoOptOperator twoOpt;
    private GeneticAlgorithm alg3;
    private Population pop;

    public TwoOptAlgFitnessFunction()
    {
        // ls = new LocalSearch();
        // twoOpt = new TwoOptOperator();
        // alg3 = new GeneticAlgorithm();
        // create_pop = new General();
    }

    public double fitness(TSP_Instance instance)
    {
        ls = new LocalSearch();
        twoOpt = new TwoOptOperator();
        alg3 = new GeneticAlgorithm();
        pop = new Population(instance, 20);

        double lsBestScore = 10000;
        double alg3BestScore = 10000;
        for(int j = 0; j < 3; j++)
        {
            // Creating populations
            // ArrayList<ArrayList<Point>> lspop = create_pop.create_population(instance, 10);
            // ArrayList<TSP_Instance> algpop = new ArrayList<TSP_Instance>();
            // for (int i = 0; i < lspop.size(); i++) {
            //     algpop.add(new TSP_Instance(lspop.get(i)));
            // }

            // 2-Opt Local Search: Finds best score across all instances
            double lsScore = 10000;
            lsScore = ls.search(instance, twoOpt);
            if (lsScore < lsBestScore) {
                lsBestScore = lsScore;
            }

            pop = alg3.GeneticAlgorithmSearch(instance, pop, 20);
            alg3Score = alg3.stats(pop.getParents());
            if (alg3Score < alg3BestScore) {
                alg3BestScore = algScore;
            }
        }
        return Math.abs(lsBestScore - alg3BestScore);
    }
}