import java.time.LocalTime;
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

        // Mutation method for parent TSP Instances.
        SuperNovaMutation snm = new SuperNovaMutation(0, 0, 100, 100);

        // Calculate fitness of the current TSP Instance as well as create
        // a variable to store the fitness of any children.
        double currentFitness = calculateFitness(currentTsp);
        double childFitness;

        // Create a variable to be used as the stop case.
        int repeatCount = 0;

        // Loop until stop condition is met (repeatCount == 10000).
        while (true)
        {
            repeatCount++;
            System.out.println(LocalTime.now() + ": " + repeatCount);

            // Mutate child TSP Instance
            childTsp = snm.mutate(currentTsp);

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
