

public class InverTwoOpFitnessFunction implements ITSPFitnessFunction
{
    private InverOver inver; //inverOver algorithm
    //private TwoOpt two; use whatever name was used for the two opt algorithm
    private General create_pop; //population creator

    public double fitness(TSP_Instance instance)
    {
        double overall_inver_best = -1;
        double overall_two_opt_best = -1;
        for(int j = 0; j < 3; j++)
        {
            ArrayList<ArrayList<Point>> pop = create_pop.create_population(instance, 10);
            ArrayList<ArrayList<Point>> inver_pop = inver.InverOverAlg(pop, 10, 0.02); //get invers result
            ArrayList<ArrayList<Point>> two_opt_pop;  //this will get twoOpt result

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

            // two opt borrows invers fitness calculation (we can move that elsewhere if needed) vvvvvv
            size = two_opt_pop.size();
            ArrayList<Point> two_opt_best = two_opt_pop.get(0);
            double two_opt_best_fitness = inver.calcFitness(two_opt_best);
            for(int i = 1; i < size; i++)
            {
                double fitness_result = inver.calcFitness(two_opt_pop.get(i)); //it is used here
                if(fitness_result < two_opt_best_fitness)
                {
                    two_opt_best = two_opt_pop.get(i);
                    two_opt_best_fitness = fitness_result;
                }
            }

            if(overall_two_opt_best < 0 || two_opt_best_fitness < overall_two_opt_best) {overall_two_opt_best = two_opt_best_fitness;}

        }
        

        double result = Math.abs(overall_inver_best - overall_two_opt_best);


        return result;
    }
}