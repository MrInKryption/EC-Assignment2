import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/* 
InverOver function

Arguments: 
ArrayList<ArrayList<Point>> : which is your population
int while_cond              : sets the number of iterations over the population -> recommended 10
int prob                    : inner probability -> recommended 0.02


*/

public class InverOver
{
    //------------------helper functions---------------------//

    //gets a random int that is not the same as the input index
    //takes in size of a permutation and index of point chosen for inverOver
    public int getRandPoint(int index, int size)
    {
        int random;
        do
        {
            random = rand.nextInt(size);
        }
        while(random == index);

        return random;
    }

    //finds the index of a points location in another permutation/individual
    public int findIndex(Point point, ArrayList<Point> list2)
    {
        //get x and y of point
        int px = point.getX();
        int py = point.getY();
        int size = list2.size();

        //find point in list2
        for(int i = 0; i < size; i++)
        {
            int lx = list2.get(i).getX();
            int ly = list2.get(i).getY();

            if((Double.compare(px, lx) == 0) & (Double.compare(py, ly) == 0))
            {
                return i;
            }
        }
        //if not found return false (shouldn't ever happen)
        return -1;
    }

    //inverts section of arrayList<Point> from start (p) + 1 to end (p')
    //returns the new inverted arraylist
    public ArrayList<Point> invertor(ArrayList<Point> list, int start, int end)
    {
        int size = list.size();
        if(start < end) { end += size;}

        ArrayList<Point> temp = new ArrayList<Point>();

        for(int i = start + 1; i <= end; i++) //add parts for inversion
        {
            temp.add(list.get(i%size));
        }

        Collections.reverse(temp);

        for(int i = start + 1; i <= end; i++)
        {
            list.set(i, temp.get(i%size));
        }

        return list;
    }

    //get fitness of a permutation/path
    public int calcFitness(ArrayList<Point> list)
    {
        int size = list.size();
        int distance = 0;
        for(int i = 0; i < size-1; i++)
        {
            distance += list.get(i).distance(list.get(i+1));
        }

        return distance;
    }

    

    //------------------------------InverOver Algorithm--------------------------------//

    public ArrayList<ArrayList<Point>> InverOverAlg(ArrayList<ArrayList<Point>> pop, int while_cond, double prob)
    {
        int size = pop.size();
        ArrayList<Point> individual;
        ArrayList<Point> individual_copy;

        int loop_times = 0;
        while(loop_times < while_cond)
        {
            for(int i = 0; i < size; i++) //for every individual in the population
            {
                individual = pop.get(i);
                individual_copy = individual;

                //randomly select a point p from I'
                int new_point_index = rand.nextInt(individual_copy.size());
                Point new_point = individual_copy.get(new_point_index);

                while(true)
                {
                    Point p_dash; // p'
                    int p_dash_index;

                    if(rand.nextFloat() <= prob)
                    {
                        //get p' from remaining points in I'
                        int random = getRandPoint(new_point_index, individual_copy.size());
                        p_dash = individual_copy.get(random);
                        p_dash_index = random;
                    }
                    else
                    {
                        //get random individual from population
                        int random = rand.nextInt(size);
                        ArrayList<Point> other_individual = pop.get(random);

                        //find location of p in random individual
                        int found = findIndex(new_point, other_individual);

                        //get point next to it in random Individual
                        Point random_individual_point = other_individual.get(found + 1);

                        //find that point in the current individual
                        found = findIndex(random_individual_point, individual_copy);
                        p_dash = individual_copy.get(found);
                        p_dash_index = found;

                    }

                    //if p' is next to p (either side)
                    if(Math.abs(p_dash_index - new_point_index) == 1)
                    {
                        break;
                    }

                    //invert from p to p'
                    individual_copy = invertor(individual_copy, new_point_index, p_dash_index);
                    //p = p'
                    new_point_index = p_dash_index + 1;
                    new_point = individual_copy.get(new_point_index);

                }

                //if individual_copy has better fitness than individual
                //replace individual with individual copy
                if(calcFitness(individual_copy) < calcFitness(individual))
                {
                    pop.set(i, individual_copy);
                }

            }
        }

        //update population and return
        ArrayList<ArrayList<Point>> new_pop = new ArrayList<ArrayList<Point>>(pop);
        return new_pop;
        
    }

}
