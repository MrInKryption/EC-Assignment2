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

            TSP_Instance tsp2 = new TSP_Instance(numberCities, 0, 50);
            ExtremistEA extremist = new ExtremistEA();
            extremist.EvolutionaryAlgorithm(tsp2);
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
                    OffsetMutation offsetMutator = new OffsetMutation();
                    SchoolyardTeamsTSPCrossover schoolCrossover = new SchoolyardTeamsTSPCrossover();
                    
                    alg.evolutionaryAlgorithm(population, fitness, offsetMutator, schoolCrossover);
                    break;
                default:
                    System.out.println("Error: Unreachable code reached!");
                    System.exit(1);
            }
        }
        else if (args[0].equals("3"))
        {
            int numGenerations = Integer.parseInt(args[4]);
            // Exercise 3 requires 5 arguments total.
            // If not enough, print an error.
            if (args.length != 5) {
                System.out.println("Improper usage for part 3. Arguments are: ");
                System.out.println("3 <algorithm id 1,2,3,4,5 or 6> <crossover id 1 or 2> <mutation id 1,2 or 3> <number of generations>");
                System.exit(1);
            }
            if (args[1].equals("1"))
            {  
                perform = new Performance(100, 10, 0, 10, numGenerations);
                perform.fitnessPairInverTwoOp(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
            else if (args[1].equals("2"))
            {
                perform = new Performance(100, 10, 0, 10, numGenerations);
                perform.fitnessPairInverTwoOp2(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
            else if (args[1].equals("3")) 
            {
                perform = new Performance(100, 10, 0, 10, numGenerations);
                perform.fitnessPairInverAlg(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
            else if (args[1].equals("4")) 
            {
                perform = new Performance(100, 10, 0, 10, numGenerations);
                perform.fitnessPairInverAlg2(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
            else if (args[1].equals("5")) 
            {
                perform = new Performance(100, 10, 0, 10, numGenerations);
                perform.fitnessPairTwoOptAlg(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
            if (args[1].equals("6")) 
            {
                perform = new Performance(100, 10, 0, 10, numGenerations);
                perform.fitnessPairTwoOptAlg2(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
        }
        else if (args[0].equals("4"))
        {
            // Exercise 1 requires a number of cities. 
            if (args.length != 2)
            {
                System.out.println("Improper usage for the bonus part. Arguments are:");
                System.out.println("4 <number of cities>");
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

            //Output is very varied. Once I saw a TSP instance with fitness of around 1600 and other times 200
            TSP_Instance tsp2 = new TSP_Instance(numberCities, 0, 50);
            ExtremistEA extremist = new ExtremistEA();
            extremist.EvolutionaryAlgorithm(tsp2);
        }
        else
        {
            System.out.println("Error: Invalid exercise number: " + args[0] + ". Must be 1, 2 or 3");
        }
        
        return;
    }
}