import java.util.ArrayList;
import java.util.Random;

// Evolutionary Algorithm class
public class EvolutionaryAlgorithm1 implements IEvolutionary
{
    private LocalSearch ls;
    private TwoOptOperator twoOpt;

    public EvolutionaryAlgorithm1()
    {
        ls = new LocalSearch();
        twoOpt = new TwoOptOperator();
    }

    // Evolutionary Algorithm to produce TSP Instances maximising
    // while loops taken for Two Opt to reach local optima.
    public void EvolutionaryAlgorithm(TSP_Instance tsp)
    {
        // Create copy of initial TSP Instance as well as create
        // a variable to store the child TSP Instance after mutation.
        TSP_Instance currentTsp = new TSP_Instance(tsp.getCoordinates());
        TSP_Instance childTsp;

        // Calculate fitness of the current TSP Instance as well as create
        // a variable to store the fitness of any children.
        double currentFitness = calculateFitness(currentTsp);
        double childFitness;

        // Determine base probability based on number of cities as well as
        // create a variable to store the random probability generated.
        double baseProbability = 1.0 / (double)currentTsp.getDimension();
        double randomProbability;

        // Create a variable to be used to determine the city to mutate in the
        // TSP Instance as well as create a variable to be used as the stop case.
        int count;
        int repeatCount = 0;

        // Initialise random object.
        Random rand = RandomNumberGenerator.getRandom();

        // Loop until stop condition is met (repeatCount == 10000).
        while (true)
        {
            repeatCount++;
            System.out.println(repeatCount);

            // Reset counter to prepare for finding next city to mutate.
            count = 0;

            // Generate new random probability.
            randomProbability = rand.nextDouble();

            // Loop by taking the base probability away from the random
            // probability until equal to or less than 0. Depending on
            // case, uses count to determine city to mutate.
            while (true)
            {
                if (randomProbability > 0)
                {
                    randomProbability -= baseProbability;
                    count++;
                }
                else if(randomProbability == 0)
                {
                    break;
                }
                else if(randomProbability < 0)
                {
                    count--;
                    break;
                }
            }

            // Copy current TSP Instance for mutation.
            childTsp = new TSP_Instance(currentTsp.getCoordinates());

            // Mutate child TSP Instance by swapping the specific cities
            // X and Y coordinates.
            childTsp.getCoordinates().get(count).swapXY();

            // Calculate the childs fitness and compare against the
            // current parents fitness. Choose the TSP Instance with
            // the best fitness.
            childFitness = calculateFitness(childTsp);
            if (childFitness > currentFitness)
            {
                currentTsp = childTsp;
                currentFitness = childFitness;
            }

            // Stop condition for while loop.
            if (repeatCount == 10000)
            {
                break;
            }
        }
        System.out.println(currentFitness);
    }

    // Calculate fitness of a TSP Instance based on the average time
    // it takes to find local optima using Two Opt local search over
    // 5 iterations. Time is defined as the number of while loop
    // iterations required to reach local optima. More time required
    // equals higher fitness.
    public double calculateFitness(TSP_Instance tsp)
    {
        int sum = 0;
        for (int i = 0; i < 5; i++)
        {
            ls.search(tsp, twoOpt);
            sum += ls.getCount();
        }
        return (double)sum / 5.0;
    }
}
