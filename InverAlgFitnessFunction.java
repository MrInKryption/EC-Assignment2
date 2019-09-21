import java.util.ArrayList;

public class InverAlgFitnessFunction implements ITSPFitnessFunction
{
    private InverOver inver;
    private Population pop;
    private Population pop2;
    private GeneticAlgorithm alg3;

    public double fitness(TSP_Instance instance)
    {
        inver = new InverOver();
        pop = new Population(instance, 20);
        pop2 = new Population(instance, 20);
        alg3 = new GeneticAlgorithm();

        double alg3BestScore = 10000;
        double inverBestScore = 10000;
        for(int j = 0; j < 3; j++)
        {
            // Genetic Algorithm: 10000 generations, tournament and elitism selection, pmx crossover, insert mutation
            pop = alg3.GeneticAlgorithmSearch(instance, pop, 20);
            double alg3Score = alg3.stats(pop.getParents());
            if (alg3Score < alg3BestScore) {
                alg3BestScore = alg3Score;
            }

            // InverOver Algorithm
            pop2 = inver.InverOver(instance, pop2, 10, 0.02);
            double inverScore = inver.stats(pop2.getParents());
            if (inverScore < inverBestScore) {
                inverBestScore = inverScore;
            }
        }
        return Math.abs(alg3BestScore - inverBestScore);
    }
}
