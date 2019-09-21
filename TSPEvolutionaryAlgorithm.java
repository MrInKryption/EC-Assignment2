import java.util.ArrayList;
import java.util.Random;

public class TSPEvolutionaryAlgorithm
{
    private TournamentSelectionTSP tournament;
    private ElitismSelectionTSP elitism;
    private double mutationRate;
    private int generations;
    private double generation_fitness_average;
    private double overall_fitness_total;
    private double overall_fitness_average;
    private double number_generations_completed;
    
    // Constructor for this family of genetic algorithms.
    // Mutation rate is the chance of mutation, between 0 and 1.0.
    // 0 means no mutation will occur. 
    // 1 means mutation will always occur. 
    // Tournament size is the size of tournaments used for tournament selection.
    // Number generations is the number of generations to run the algorithm for. 
    public TSPEvolutionaryAlgorithm(double newMutationRate, int tournamentSize, int numberGenerations)
    {
        tournament = new TournamentSelectionTSP(tournamentSize);
        elitism = new ElitismSelectionTSP();

        generation_fitness_average = 0;
        overall_fitness_total = 0;
        overall_fitness_average = 0;
        number_generations_completed = 0;
        
        // Ensure mutation rate is between 0 and 1.
        if (newMutationRate < 0)
        {
            mutationRate = 0;
        }
        else if (newMutationRate > 1)
        {
            mutationRate = 1;
        }
        else
        {
            mutationRate = newMutationRate;
        }
        
        // Ensure generations is non-zero. 
        if (numberGenerations < 1)
        {
            generations = 1;
        }
        else
        {
            generations = numberGenerations;
        }
    }

    public void fitnessGrabber(ArrayList<TSP_Instance> pop)
    {
        int size = pop.size();
        int sum = 0;
        generation_fitness_average = 0;
        for(int i = 0; i < size; i++)
        {
            double fit = pop.get(i).getFitness();
            sum += fit;
        }

        generation_fitness_average = sum/size;   
    }
    
    public void evolutionaryAlgorithm(ArrayList<TSP_Instance> population, ITSPFitnessFunction fitness, ITSPMutation mutator, ITSPCrossover crossover)
    {
        TSP_Instance.setFitnessFunction(fitness);
        
        // Size of the population. 
        int populationSize = population.size();
        
        // Child population is half the size of the parent population,
        // though it must be an even number.
        int childPopulationSize = populationSize / 2;
        if (childPopulationSize % 2 != 0)
        {
            childPopulationSize += 1;
        }
        
        // Holds the current population of TSP_Instances. 
        ArrayList<TSP_Instance> currentPopulation = new ArrayList<TSP_Instance>(population);
        
        // Random number generator used to determine if children should be mutated. 
        Random rand = RandomNumberGenerator.getRandom();
        
        // Run the loop for generations times. 
        for (int i = 0; i < generations; i++)
        {
            System.out.println("doing generation: " + i + "\n");
            // nextPopulations starts off as a copy of the current population. 
            ArrayList<TSP_Instance> nextPopulation = new ArrayList<TSP_Instance>(currentPopulation);
            
            // Use tournament selection to choose which parents will reproduce.
            ArrayList<TSP_Instance> parents = tournament.select(currentPopulation, childPopulationSize);
            
            // For each pair of children in parents, perform crossover and potentially
            // mutate the children. 
            for (int j = 0; j < parents.size(); j = j + 2)
            {
                ArrayList<TSP_Instance> children = crossover.crossover(parents.get(j), parents.get(j+1));
                
                // For each child returned, mutationRate% chance of mutating them. 
                for (int k = 0; k < children.size(); k = k + 1)
                {
                    double test = rand.nextDouble();
                    if (test < mutationRate)
                    {
                        children.set(k, mutator.mutate(children.get(k)));
                    }
                }
                
                // Add the children to the next population. 
                nextPopulation.addAll(children);
            }
            
            // Use elitism to select the populationSize best individuals to add to the next
            // generation.
            currentPopulation = elitism.select(nextPopulation, populationSize);

            //calculations
            number_generations_completed ++; //increment number of generations completed
            fitnessGrabber(currentPopulation); //run fitness grabber which updates generation_fitness_average and best_fitness
            overall_fitness_total += generation_fitness_average; //add to overall fitness total
            overall_fitness_average = overall_fitness_total/number_generations_completed; //calculate overall average

            System.out.println("Generations Run: " + number_generations_completed);
            System.out.println("Generation fitness average: " + generation_fitness_average);
            System.out.println("Overall fitness Average: " + overall_fitness_average + "\n");

        }
        
        // Print out the final fitness. 
        System.out.println("Final fitness: " + currentPopulation.get(0).getFitness());
    }
}
