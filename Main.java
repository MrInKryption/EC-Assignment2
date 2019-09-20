import java.util.ArrayList;

// Main class
public class Main
{
    //private static TSP_Instance tsp;
    private static Performance perform;
    
    public static void main(String[] args) {
        // if(args.length == 0)
        // {
        //     System.out.println("You have provided no arguments.");
        //     return;
        // }
        // else
        // {
            /*tsp = new TSP_Instance(10, 0, 10);
            EvolutionaryAlgorithm1 ea = new EvolutionaryAlgorithm1();
            //ea.EvolutionaryAlgorithm(tsp);
            
            TSPEvolutionaryAlgorithm alg = new TSPEvolutionaryAlgorithm(0.05, 4, 1000);
            ArrayList<TSP_Instance> population = new ArrayList<TSP_Instance>();
            int population_size = 10;
            for (int i = 0; i < population_size; i++)
            {
                population.add(new TSP_Instance(50, 0, 10));
            }
            
            TwoOptFitnessFunction fitness = new TwoOptFitnessFunction();
            SuperNovaMutation mutator = new SuperNovaMutation(0, 10, 0, 10);
            MeanTSPCrossover crossover = new MeanTSPCrossover();
            
            alg.evolutionaryAlgorithm(population, fitness, mutator, crossover);*/

            perform = new Performance(100, 10, 0, 10);
            perform.fitnessPairInverAlg();
            return;
        //}
    }
}
