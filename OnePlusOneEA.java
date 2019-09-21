import java.util.ArrayList;
import java.util.Random;

// (1+1) EA class
public class OnePlusOneEA implements IEvolutionary
{
    public OnePlusOneEA()
    {

    }

    // Evolutionary Algorithm to produce TSP Instances maximising
    // while loops taken for Two Opt to reach local optimum.
    public void EvolutionaryAlgorithm(TSP_Instance tsp)
    {
        // Set the fitness function of TSP to Two Opt.
        TSP_Instance.setFitnessFunction(new TwoOptFitnessFunction());

        // Initialise parent and child TSPs.
        TSP_Instance parentTsp = new TSP_Instance(tsp.getCoordinates());
        TSP_Instance childTsp;

        // Initialise parent and child fitness.
        double parentFitness = parentTsp.getFitness();
        double childFitness;

        // Initialise FastNova mutation.
        FastNovaMutation fastnova = new FastNovaMutation(10);

        // Initialise variable to keep track of current generation.
        int generation = 0;

        // Loop for 10,000 generations.
        while (generation <= 10000)
        {
            // Increment generation each loop.
            generation++;
            //System.out.println(generation);

            // Mutate parent to create child.
            childTsp = fastnova.mutate(parentTsp);

            // Calculate child fitness.
            childFitness = childTsp.getFitness();

            // If child fitness is better than parent, child
            // is now the parent for future generations.
            if (childFitness > parentFitness)
            {
                System.out.println(childFitness);
                parentTsp = childTsp;
                parentFitness = childFitness;
            }
        }

        // Output final fitness.
        System.out.println(parentFitness);
    }
}
