import java.util.ArrayList;

public interface ITSPSelection
{
    public ArrayList<TSP_Instance> select(ArrayList<TSP_Instance> solutions, int numSurvivors);
}
