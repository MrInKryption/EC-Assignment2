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
            tsp = new TSP_Instance(args[0]);
            EvolutionaryAlgorithm1 ea = new EvolutionaryAlgorithm1();
            ea.EvolutionaryAlgorithm(tsp);
            return;
        }
    }
}
