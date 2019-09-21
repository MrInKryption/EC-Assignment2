import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TSP_Instance
{
    // Determines how the fitness of this TSP_Instance is calculated. 
    private static ITSPFitnessFunction fitnessCalculator; 
    private int dimension; // Number of cities in the TSP Instance.
    private ArrayList<Point> coordinates; // Coordinates for each city in the TSP Instance.
    private double fitness; // The fitness of this TSP Instance, from fitness calculator. 

    public TSP_Instance(String file_path)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            String line = br.readLine();
            String[] tokens;
            boolean read_coords = false;
            coordinates = new ArrayList<Point>();
            while (line != null)
            {
                if (!read_coords)
                {
                    line = line.replaceAll(":", "");
                    tokens = line.split(" ", 2);
                    switch(tokens[0])
                    {
                        case "DIMENSION":
                            tokens[1] = tokens[1].replaceAll(" ", "");
                            dimension = Integer.parseInt(tokens[1]);
                            break;
                        case "NODE_COORD_SECTION":
                            read_coords = true;
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    tokens = line.split(" ", 3);
                    if (tokens[0].equals("EOF")) {}
                    else
                    {
                        coordinates.add(new Point(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));
                    }
                }
                line = br.readLine();
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        recalculateFitness();
    }

    public TSP_Instance(ArrayList<Point> coordinates)
    {
        dimension = coordinates.size();
        this.coordinates = coordinates; 
        recalculateFitness();
    }
    
    // Randomly generates a TSP_Instance of the specified size,
    // with the points sampled between min and max. 
    public TSP_Instance(int problem_size, double min, double max)
    {
        this.coordinates = new ArrayList<Point>();
        Random rand = RandomNumberGenerator.getRandom();
        for (int i = 0; i < problem_size; i++)
        {
            double x = min + ((max - min) * rand.nextDouble());
            double y = min + ((max - min) * rand.nextDouble());
            
            Point new_point = new Point(x, y);
            this.coordinates.add(new_point);
        }
        
        recalculateFitness();
    }

    //For if you just want to use the distance function
    public TSP_Instance()
    {
        ;
    }
    
    // All TSP_Instances created after a call to this function
    // will use function to calculate the fitness which they return
    // with getFitness(). 
    // This will not update the function of existing instances. 
    // If this is not called, all instances will have 0 fitness. 
    // To update a existing instance to the new fitness function,
    // call recalculateFitness(). 
    public static void setFitnessFunction(ITSPFitnessFunction function)
    {
        fitnessCalculator = function;
    }

    // Gets the dimension of the TSP Instance.
    public int getDimension()
    {
        return dimension;
    }

    // Gets the array list of all coordinates.
    public ArrayList<Point> getCoordinates()
    {
        return coordinates;
    }

    // Sets the coordinates array list to a new specified one.
    public void setCoordinates(ArrayList<Point> coordinates)
    {
        this.coordinates = coordinates;
    }

    // Gets the euclidean distance between cities i and j.
    public double getDistance(int i, int j)
    {
        double x_diff = coordinates.get(i).getX() - coordinates.get(j).getX();
        double y_diff = coordinates.get(i).getY() - coordinates.get(j).getY();
        double x_pow = Math.pow(Math.abs(x_diff), 2);
        double y_pow = Math.pow(Math.abs(y_diff), 2);
        double root = Math.sqrt(x_pow + y_pow);
        return root;    
    }

    // Gets the total euclidean distance for a specified solution.
    public double getTotalDistance(ArrayList<Integer> solution)
    {
        double total = 0;
        total += getDistance(0, solution.get(0));
        for (int i = 0; i < solution.size()-1; i++)
        {
            total += getDistance(solution.get(i), solution.get(i+1));
        }
        total += getDistance(solution.get(solution.size()-1), 0);
        return total;
    }
    
    // Recalculates the fitness of this instance using fitnessCalculator.
    // May give slightly different results even if fitnessCalculator is not used. 
    public double recalculateFitness()
    {
        // Calculate the fitness of this instance. 
        if (fitnessCalculator == null)
        {
            // If no fitness calculator,
            // fitness is zero. 
            fitness = 0;
        }
        else
        {
            fitness = fitnessCalculator.fitness(this);
        }
        return fitness;
    }
    
    // Returns the fitness of this TSP instance, as set by fitness calculator. 
    public double getFitness()
    {
        return fitness;
    }
    
    // Generates a random permutation based on the TSP Instance.
    public ArrayList<Integer> initPermutation()
    {
        ArrayList<Integer> permutation = new ArrayList<Integer>();
        Random rand = RandomNumberGenerator.getRandom();
        for (int i = 1; i < getDimension(); i++)
        {
            permutation.add(i);
        }
        Collections.shuffle(permutation, rand);
        return permutation;
    }
    
    // Prints the point out. 
    public void print()
    {
        for (Point point : coordinates)
        {
            System.out.print("(" + point.getX() + ", " + point.getY() + ")");
            System.out.print(",");        
        }
        System.out.println("\n--------------");
    }
}
