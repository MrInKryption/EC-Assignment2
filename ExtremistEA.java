import java.util.ArrayList;
import java.util.Random;

// Evolutionary Algorithm class
public class ExtremistEA implements IEvolutionary
{
    public ExtremistEA()
    {
    }

    // Evolutionary Algorithm to produce TSP Instances maximising
    public void EvolutionaryAlgorithm(TSP_Instance tsp)
    {
        TSP_Instance.setFitnessFunction(new TwoOptFitnessFunction());

        // Create copy of initial TSP Instance as well as create
        // a variable to store the child TSP Instance after mutation.
        TSP_Instance currentTsp = new TSP_Instance(tsp.getCoordinates());
        TSP_Instance childTsp;

        // Calculate fitness of the current TSP Instance as well as create
        // a variable to store the fitness of any children.
        double currentFitness = currentTsp.getFitness();
        double childFitness;

        // Variables we will need
        int choice;
        int option;
        int num_cities = tsp.getCoordinates().size();
        Point p;
        double city_dim;
        long ave;

        // The number of times we wish to run the program
        int runs = 10000;

        //DEBUG
        int debug1 = 0;
        int debug2 = 0;

        // Initialise random object.
        Random rand = RandomNumberGenerator.getRandom();

        // Loop until stop condition is met (runs == 10000).
        while (runs > 0)
        {
            runs--;

            // Reset vars incase somehow we don't find a suitable city
            choice = 0;
            ave = 0;

            // Choose which kind of city we want to find
            option = rand.nextInt(4);   //We only want 4 options, [0...3]

            //Set the initial check value
            if(option == 0 || option == 1){
                city_dim = currentTsp.getCoordinates().get(0).getX();
            }
            else{
                city_dim = currentTsp.getCoordinates().get(0).getY();
            }

            //Using the random option, find the city with the largest or smallest x/y
            for(int i = 0; i < num_cities; i++){
                p = currentTsp.getCoordinates().get(i);
                switch(option){
                    case 0: //Finding the individual with the lowest X
                        if(p.getX() < city_dim){
                            city_dim = p.getX();
                            choice = i;
                        }
                        ave += p.getX();
                        break;
                    case 1: //highest X
                        if(p.getX() > city_dim){
                            city_dim = p.getX();
                            choice = i;
                        }
                        ave += p.getX();
                        break;
                    case 2: //lowest Y
                        if(p.getY() < city_dim){
                            city_dim = p.getY();
                            choice = i;
                        }
                        ave += p.getY();
                        break;
                    case 3: // highest Y
                        if(p.getY() > city_dim){
                            city_dim = p.getY();
                            choice = i;
                        }
                        ave += p.getY();
                        break;
                    default:
                        System.out.println("How'd you get here? Value = " + option);
                }
            }
            ave /= num_cities;

            // Copy current TSP Instance for mutation.
            childTsp = new TSP_Instance(currentTsp.getCoordinates());

            //Mutate the child by sending it further into the extreme
            if(option == 0 || option == 2){
                childTsp.getCoordinates().get(choice).scalar_factor(0.999);
            }
            else{
                childTsp.getCoordinates().get(choice).scalar_factor(1.001);
            }


            // Calculate the childs fitness and compare against the current parents fitness.
            // Choose the TSP Instance with the best fitness.
            childFitness = childTsp.getFitness();
            if (childFitness > currentFitness)
            {
                currentTsp = childTsp;
                currentFitness = childFitness;
                debug1++;
            }
            else if (childFitness < currentFitness){
                debug2++;
            }

        }
        // System.out.println("DEBUG VALUES (Replaces VS Unchanged): " + debug1 + ":" + debug2);
        // System.out.println("Final fitness: " + currentFitness);
        System.out.println(currentFitness);
    }
}
