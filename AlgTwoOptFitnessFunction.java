import java.util.ArrayList;

public class InverAlgFitnessFunction implements ITSPFitnessFunction
{
    private LocalSearch ls;
    private TwoOptOperator twoOpt;
    private InverOver inver;
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
        inver = new InverOver();
        pop = new Population(instance, 20);

        double lsBestScore = 10000;
        double inverBestScore = 10000;
        for(int j = 0; j < 3; j++)
        {
            // 2-Opt Local Search: Finds best score across all instances
            double lsScore = ls.search(instance, twoOpt);
            if (lsScore < lsBestScore) {
                lsBestScore = lsScore;
            }

            // Genetic Algorithm: 10000 generations, tournament and elitism selection, pmx crossover, insert mutation
            pop = inver.InverOver(instance, pop, 10, 0.02);     //GeneticAlgorithmSearch(instance, pop, 20);
            double inverScore = alg3.stats(pop.getParents());
            if (inverScore < inverBestScore) {
                inverBestScore = inverScore;
            }
        }
        return Math.abs(lsBestScore - inverBestScore);
    }
}