import java.util.ArrayList;

// Main class
public class Main
{
    private static Performance perform;

    public static void main(String[] args) {
        TSP_Instance tsp = new TSP_Instance(50, 0, 50);
        OnePlusOneEA onePlusOne = new OnePlusOneEA();
        onePlusOne.EvolutionaryAlgorithm(tsp);
        
        // TSPEvolutionaryAlgorithm alg = new TSPEvolutionaryAlgorithm(0.05, 4, 1000);
        // TSPEvolutionaryAlgorithm algB = new TSPEvolutionaryAlgorithm(0.05, 4, 1000);
        // ArrayList<TSP_Instance> population = new ArrayList<TSP_Instance>();
        // ArrayList<TSP_Instance> populationB = new ArrayList<TSP_Instance>();
        // int population_size = 10;
        // int instance_size = 100;
        // for (int i = 0; i < population_size; i++)
        // {
        //     population.add(new TSP_Instance(instance_size, 0, 100));
        //     populationB.add(new TSP_Instance(instance_size, 0, 100));
        // }
        
        // TwoOptFitnessFunction fitness = new TwoOptFitnessFunction();
        // FastNovaMutation mutator = new FastNovaMutation(3);
        // SuperNovaMutation superMutator = new SuperNovaMutation(0, 0, 100, 100);
        // MeanTSPCrossover crossover = new MeanTSPCrossover();
        
        // long startTimeA = System.nanoTime();
        // alg.evolutionaryAlgorithm(population, fitness, mutator, crossover);
        // long endTimeA = System.nanoTime();
        
        // System.out.println("FastNova run time: " + (endTimeA - startTimeA));
        
        // long startTimeB = System.nanoTime();
        // algB.evolutionaryAlgorithm(populationB, fitness, superMutator, crossover);
        // long endTimeB = System.nanoTime();
        
        // System.out.println("SuperNova run time: " + (endTimeB - startTimeB));
  
        // Exercise 3
        perform = new Performance(100, 10, 0, 10);
        // perform.fitnessPairInverTwoOp();
        // perform.fitnessPairInverTwoOp2();
        // perform.fitnessPairInverAlg();
        // perform.fitnessPairInverAlg2();
        // perform.fitnessPairTwoOptAlg();
        // perform.fitnessPairTwoOptAlg2();
        
        return;
    }
}
