import java.util.ArrayList;

public class ElitismSelectionTSP implements ITSPSelection
{
    // Keeps the individuals with highest fitness scores
    // numElites variable is number of elites present in a population
    public ArrayList<TSP_Instance> select(ArrayList<TSP_Instance> individuals, int numElites)
    {
        // Initialise population array
        ArrayList<TSP_Instance> population = new ArrayList<TSP_Instance>(individuals);
        ArrayList<TSP_Instance> elites = new ArrayList<TSP_Instance>();

        // Find elites and seperate from population
        for (int i = 0; i < numElites; i++)
        {
            double best = population.get(0).getFitness();
            int indexBest = 0;
            for (int j = 1; j < population.size(); j++)
            {
                if (population.get(j).getFitness() < best)
                {
                    best = population.get(j).getFitness();
                    indexBest = j;  
                }
            }
            // Add elites to seperate list
            elites.add(population.get(indexBest));
            population.remove(indexBest);
        }
        
        return elites;
    }
}
