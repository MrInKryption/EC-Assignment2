import java.util.ArrayList;
import java.util.Random;

public class TournamentSelectionTSP implements ITSPSelection
{
    ArrayList<TSP_Instance> populationObject;
    private int tournamentSize;

    public TournamentSelectionTSP(int size)
    {
        tournamentSize=size;
    }
    
    // Keeps individuals with higher fitness scores if selected in tournaments
    // Tournaments variable is equal to number of individuals returned
    public ArrayList<TSP_Instance> select(ArrayList<TSP_Instance> individuals, int tournaments)
    {

        // Initialise population array
        ArrayList<TSP_Instance> population = new ArrayList<TSP_Instance>(individuals);
        ArrayList<TSP_Instance> survivors = new ArrayList<TSP_Instance> ();
        
        for (int j = 0; j < tournaments; j++)
        {
            // Select n individuals randomly from individuals pool
            Random rand = RandomNumberGenerator.getRandom();
            ArrayList<TSP_Instance> competitors = new ArrayList<TSP_Instance>();
            for (int i = 0; i < tournamentSize; i++)
            {
                int randomProb = rand.nextInt(population.size());
                competitors.add(population.get(randomProb));
            }

            // Keep the individual with best fitness score
            double best = competitors.get(0).getFitness();
            int indexBest = 0;
            for (int i = 1; i < competitors.size(); i++)
            {
                if (best > competitors.get(i).getFitness())
                {
                    best = competitors.get(i).getFitness();
                    indexBest = i;
                }
            }
            // Add individual to survivors
            survivors.add(competitors.get(indexBest));
        }
        
        return survivors;
    }
}
