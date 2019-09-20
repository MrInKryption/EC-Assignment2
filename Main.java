import java.util.ArrayList;

// Main class
public class Main
{
    private static TSP_Instance tsp;
    
    public static void main(String[] args) {
        if(args.length == 0)
        {
            System.out.println("You have provided no arguments.");
            return;
        }
        else
        {
            tsp = new TSP_Instance(10, 0, 10);
            EvolutionaryAlgorithm1 ea = new EvolutionaryAlgorithm1();
            //ea.EvolutionaryAlgorithm(tsp);
            
            TSPEvolutionaryAlgorithm alg = new TSPEvolutionaryAlgorithm(0.05, 4, 1000);
            TSPEvolutionaryAlgorithm algB = new TSPEvolutionaryAlgorithm(0.05, 4, 1000);
            TSPEvolutionaryAlgorithm algC = new TSPEvolutionaryAlgorithm(0.05, 4, 1000);
            ArrayList<TSP_Instance> population = new ArrayList<TSP_Instance>();
            ArrayList<TSP_Instance> populationB = new ArrayList<TSP_Instance>();
            ArrayList<TSP_Instance> populationC = new ArrayList<TSP_Instance>();
            int population_size = 10;
            int instance_size = 100;
            for (int i = 0; i < population_size; i++)
            {
                population.add(new TSP_Instance(instance_size, 0, 100));
                populationB.add(new TSP_Instance(instance_size, 0, 100));
                populationC.add(new TSP_Instance(instance_size, 0, 100));
            }
            
            TwoOptFitnessFunction fitness = new TwoOptFitnessFunction();
            FastNovaMutation mutator = new FastNovaMutation(3);
            SuperNovaMutation superMutator = new SuperNovaMutation(0, 0, 100, 100);
            OffsetMutation offsetMutator = new OffsetMutation();
            MeanTSPCrossover meanCrossover = new MeanTSPCrossover();
            SchoolyardTeamsTSPCrossover schoolCrossover = new SchoolyardTeamsTSPCrossover();
            
            long startTimeA = System.nanoTime();
            alg.evolutionaryAlgorithm(population, fitness, mutator, meanCrossover);
            long endTimeA = System.nanoTime();
            
            System.out.println("FastNova run time: " + (endTimeA - startTimeA));
            
            long startTimeB = System.nanoTime();
            algB.evolutionaryAlgorithm(populationB, fitness, superMutator, meanCrossover);
            long endTimeB = System.nanoTime();
            
            System.out.println("SuperNova run time: " + (endTimeB - startTimeB));

            long startTimeC = System.nanoTime();
            algC.evolutionaryAlgorithm(populationC, fitness, offsetMutator, schoolCrossover);
            long endTimeC = System.nanoTime();
            
            System.out.println("OffsetMutation run time: " + (endTimeC - startTimeC));
            
            return;
        }
    }
}
