import java.util.ArrayList;

public class GeneticAlgorithm
{
    public ArrayList<TSP_Instance> GeneticAlgorithmSearch(ArrayList<TSP_Instance> pop,int populationSize){
        
        TournamentSelectionTSP tournament = new TournamentSelectionTSP(4);
        ElitismSelectionTSP elite = new ElitismSelectionTSP();
        PmxCrossover pmx = new PmxCrossover();
        InsertMutation insert = new InsertMutation();
        ArrayList<TSP_Instance> matingPop = new ArrayList<TSP_Instance>();
        ArrayList<TSP_Instance> child = new ArrayList<TSP_Instance>();

        //Run for 10000 generations
        for(int x=1;x<=10000;x++){
            //Select population based on the population size and which problem
            matingPop = tournament.select(pop, populationSize);
            for(int i=0;i<populationSize;i++){
                //Add offspring to the original pool
                pop.add(matingPop.get(i));
                for(int j=i+1;j<populationSize;j++){
                    //Crossover genes and then mutate the new children
                    child = pmx.crossover(matingPop.get(i), matingPop.get(j));
                    pop.add(insert.mutate(child.get(0)));
                    pop.add(insert.mutate(child.get(1))); 
                }
            }
            //Select the fittest population for the next generation
           pop = elite.select(pop,populationSize);
        }
        return pop;
    }
}