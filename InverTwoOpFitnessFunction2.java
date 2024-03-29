public class InverTwoOpFitnessFunction2 implements ITSPFitnessFunction
{
    private LocalSearch ls;
    private TwoOptOperator twoOpt;
    private InverOver inver;
    private Population pop;
    private GeneticAlgorithm alg3;

    public double fitness(TSP_Instance instance)
    {
        ls = new LocalSearch();
        twoOpt = new TwoOptOperator();
        inver = new InverOver();
        pop = new Population(instance, 20);
        alg3 = new GeneticAlgorithm();

        double lsBestScore = 10000;
        double inverBestScore = 10000;
        for(int j = 0; j < 3; j++)
        {
            // 2-Opt Local Search: Finds best score across all instances
            double lsScore = ls.search(instance, twoOpt);
            if (lsScore < lsBestScore) {
                lsBestScore = lsScore;
            }

            // InverOver Algorithm
            pop = inver.InverOver(instance, pop, 10, 0.02);
            double inverScore = alg3.stats(pop.getParents());
            if (inverScore < inverBestScore) {
                inverBestScore = inverScore;
            }
        }
        return Math.abs(lsBestScore/inverBestScore);
    }
}
