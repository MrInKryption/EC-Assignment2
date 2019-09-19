public class InverTwoOpFitnessFunction2 implements ITSPFitnessFunction
{
    private InverOver inver; //inverOver algorithm
    //private TwoOpt two; use whatever name was used for the two opt algorithm
    private General create_pop;

    public double fitness(TSP_Instance instance)
    {
        ArrayList<ArrayList<Point>> pop = create_pop.create_population(instance, 10); //get a random population
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
                inver_best_result = fitness_result;
            }
        }

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
                two_opt_best_result = fitness_result;
            }
        }

        double result = Math.abs(inver_best_fitness/two_opt_best_fitness);


        return result;
    }
}