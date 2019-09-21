import java.util.ArrayList;

// Main class
public class Main
{
    private static Performance perform;

    // Main function. First command line argument determines which exercises code to run
    public static void main(String[] args) 
    {
        if (args.length == 0)
        {
            System.out.println("Please provide arguments first argument is which exercise to run");
        }
        
        if (args[0].equals("1"))
        {
            // Exercise 1 requires a number of cities. 
            if (args.length != 2)
            {
                System.out.println("Improper usage for part 1. Arguments are:");
                System.out.println("1 <number of cities>");
                System.exit(1);
            }
            int numberCities = 0;
            try
            {
                numberCities = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid number of cities: " + args[1]);
                System.exit(1);
            }
            // If first argument is 1, runs exercise 1. 
            TSP_Instance tsp = new TSP_Instance(numberCities, 0, 50);
            OnePlusOneEA onePlusOne = new OnePlusOneEA();
            onePlusOne.EvolutionaryAlgorithm(tsp);
        }
        else if (args[0].equals("2"))
        {
            // If first argument is 2, run exercise 2. 
            
            // Exercise 2 requires 3 arguments total. If not enough, print an error.
            if (args.length != 3)
            {
                System.out.println("Improper usage for part 2. Arguments are: ");
                System.out.println("2 <algorithm id 1,2 or 3> <instance size>");
                System.exit(1);
            }
            
            // Run for 1000 generations. 
            int generations = 1000;
            
            // 4 individuals in each round of tournament selection. 
            int tournaments = 4;
            
            // 5% chance of mutating each child. 
            double mutation_rate = 0.05;
            
            // 10 instances in the population. 
            int population_size = 10;
            
            // Runs the evolutinary algorithm.
            TSPEvolutionaryAlgorithm alg = new TSPEvolutionaryAlgorithm(mutation_rate, tournaments, generations);
            
            // Determines which algorithm to use.
            int algorithm = 0;
            
            // Determines size of instance. 
            int instance_size = 0;
            
            // Fitness function used to calculate fitness.
            TwoOptFitnessFunction fitness = new TwoOptFitnessFunction();
            
            // Population of TSP_Instances to use. 
            ArrayList<TSP_Instance> population = new ArrayList<TSP_Instance>();
            
            // Get command line argument for which algorithm to run.
            try
            {
                algorithm = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid algorithm argument. Must be 1, 2 or 3.");
                System.exit(1);
            }
            
            // Ensure algorithm read is valid.
            if (algorithm < 1 || algorithm > 3)
            {
                System.out.println("Invalid algorithm argument: " + args[1] + ". Must be 1, 2 or 3");
                System.exit(1);
            }
            
            // Get command line argument for number of cities. 
            try
            {
                instance_size = Integer.parseInt(args[2]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid number of cities: " + args[2]);
                System.exit(1);
            }
            
            // Ensure number of cities makes sense. 
            if (instance_size < 1)
            {
                System.out.println("Number of cities must be positive");
                System.exit(1);
            }
            
            // Generate the population. 
            for (int i = 0; i < population_size; i++)
            {
                population.add(new TSP_Instance(instance_size, 0, 100));
            }
            
            // Run the algorithm the user wanted. 
            switch(algorithm)
            {
                case 1:
                    FastNovaMutation fastMutator = new FastNovaMutation(5);
                    MeanTSPCrossover crossover = new MeanTSPCrossover();
                    
                    alg.evolutionaryAlgorithm(population, fitness, fastMutator, crossover);
                    break;
                case 2:
                    SuperNovaMutation novaMutator = new SuperNovaMutation(0, 0, 100, 100);
                    MeanTSPCrossover meanCrossover = new MeanTSPCrossover();
                    
                    alg.evolutionaryAlgorithm(population, fitness, novaMutator, meanCrossover);
                    break;
                case 3:
                    // Code for running the third EA goes here.
                    break;
                default:
                    System.out.println("Error: Unreachable code reached!");
                    System.exit(1);
            }
        }
        else if (args[0].equals("3"))
        {
           // Exercise 3
            perform = new Performance(100, 10, 0, 10);
            // perform.fitnessPairInverTwoOp();
            // perform.fitnessPairInverTwoOp2();
            // perform.fitnessPairInverAlg();
            // perform.fitnessPairInverAlg2();
            // perform.fitnessPairTwoOptAlg();
            // perform.fitnessPairTwoOptAlg2(); 
        }
        else
        {
            System.out.println("Error: Invalid exercise number: " + args[0] + ". Must be 1, 2 or 3");
        }
        
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
  
        
        
        return;
    }
}
