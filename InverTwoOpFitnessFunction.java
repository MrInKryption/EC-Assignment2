import java.util.ArrayList;

public class InverTwoOpFitnessFunction implements ITSPFitnessFunction
{
    private InverOver inver; //inverOver algorithm
    private LocalSearch ls;
    private TwoOptOperator twoOpt;
    private General create_pop; //population creator

    public InverTwoOpFitnessFunction()
    {
        inver = new InverOver();
        ls = new LocalSearch();
        twoOpt = new TwoOptOperator();
        create_pop = new General();
    }

    public double fitness(TSP_Instance instance)
    {
        double overall_inver_best = -1;
        double overall_two_opt_best = 10000;
        double fitnessLS;
        for(int j = 0; j < 3; j++)
        {
            ArrayList<ArrayList<Point>> pop = create_pop.create_population(instance, 20);
            ArrayList<ArrayList<Point>> inver_pop = inver.InverOverAlg(pop, 10, 0.02); //get invers result
            double lowestScore = 10000;
            // Finds the best score across all instances
            for (int i = 0; i < 20; i++)
            {
                fitnessLS = ls.search(new TSP_Instance(pop.get(i)), twoOpt);
                if (fitnessLS < lowestScore) {lowestScore = fitnessLS;}
            }
            if (lowestScore < overall_two_opt_best) {overall_two_opt_best = lowestScore;}
            
            int size = inver_pop.size();
            ArrayList<Point> inver_best = inver_pop.get(0);
            double inver_best_fitness = inver.calcFitness(inver_best);
            for(int i = 1; i < size; i++)
            {
                double fitness_result = inver.calcFitness(inver_pop.get(i));
                if(fitness_result < inver_best_fitness)
                {
                    inver_best = inver_pop.get(i);
                    inver_best_fitness = fitness_result;
                }
            }
            if(overall_inver_best < 0 || inver_best_fitness < overall_inver_best) {overall_inver_best = inver_best_fitness;}
        }

        return Math.abs(overall_inver_best - overall_two_opt_best);
    }
}