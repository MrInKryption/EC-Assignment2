import java.util.ArrayList;

public class GeneticAlgorithm
{
    public Population GeneticAlgorithmSearch(TSP_Instance tsp,Population pop,int populationSize){
        
        ElitismSelection elite = new ElitismSelection();
        TournamentSelection tournament = new TournamentSelection(4);
        ArrayList<Individual> res=new ArrayList<Individual>();
        InsertMutation insert = new InsertMutation();
        PmxCrossover pmx = new PmxCrossover();
        Population matingPop=new Population(tsp,populationSize);
        //Run for 10000 generations
        for(int x=1;x<=10000;x++){
            tournament.select(tsp, matingPop, populationSize);
            for(int i=0;i<populationSize;i++){
                //Add offspring to the original pool
                pop.addToParents(matingPop.getParents().get(i));
                for(int j=i+1;j<populationSize;j++){
                    //Crossover genes and then mutate the new children
                    res=pmx.crossover(matingPop.getParents().get(i), pop.getParents().get(j));
                    pop.addToParents(insert.mutate(res.get(0)));
                    pop.addToParents(insert.mutate(res.get(1)));
                }
            }
            //Select the fittest population for the next generation
           pop=elite.select(tsp,pop,populationSize);
        }
        return pop;
    }

    public double stats(ArrayList<Individual> population) {
        double sum = 0;
        double lowestScore = population.get(0).getScore();
        for (Individual solution : population) {
            sum += solution.getScore();
            if (solution.getScore() < lowestScore) {lowestScore=solution.getScore();}
        }
        //double mean = sum / (double) population.size();
        //System.out.println("Mean Fitness Score: " + mean);
        return lowestScore;
    }
}
