public class TwoOptFitnessFunction implements ITSPFitnessFunction
{
    static LocalSearch search;
    static TwoOptOperator operator;
    
    // Calculate fitness of a TSP Instance based on the average time
    // it takes to find local optima using Two Opt local search over
    // 5 iterations. Time is defined as the number of while loop
    // iterations required to reach local optima. More time required
    // equals higher fitness.
    public double fitness(TSP_Instance tsp)
    {
        if (search == null)
        {
            search = new LocalSearch();
        }
        
        if (operator == null)
        {
            operator = new TwoOptOperator();
        }
        
        double sum = 0;
        for (int i = 0; i < 5; i++)
        {
            sum += search.search(tsp, operator);
        }
        return sum / 5.0;
    }
}
