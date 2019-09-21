public interface ISelection
{
    public Population select(TSP_Instance tsp, Population solutions, int numSurvivors);
}