// Interface for classes which calculate the fitness of TSP_Instances,
// allowing for them to be interchanged. 
public interface ITSPFitnessFunction
{
    public double fitness(TSP_Instance instance);
}
