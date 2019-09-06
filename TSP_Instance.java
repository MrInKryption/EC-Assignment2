import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TSP_Instance
{
    private int dimension; // Number of cities in the TSP Instance.
    private ArrayList<Point> coordinates; // Coordinates for each city in the TSP Instance.

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
    }

    public TSP_Instance(ArrayList<Point> coordinates)
    {
        dimension = coordinates.size();
        this.coordinates = coordinates;
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
