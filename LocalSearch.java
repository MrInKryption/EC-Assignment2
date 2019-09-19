import java.util.ArrayList;

// Generic class for local search.
public class LocalSearch
{
    // Function performs local traveling salesman problem,
    // using operator to define the neighbourhood.
    public int search(TSP_Instance tsp, ILocalSearchOperator operator)
    {
        // Create random permutation based on the TSP and calculate
        // its total trip distance.
        ArrayList<Integer> current = tsp.initPermutation();
        double currentDistance = tsp.getTotalDistance(current);

        // Declare list and number to store the child permutations and
        // total trip distance.
        ArrayList<Integer> next;
        double nextDistance;

        // Initialise the size of the permutations and variable
        // to count the number of while loop iterations.
        int size = current.size();
        int count = 0;

        finish:
        while (true)
        {
            // Increment count every while loop.
            count++;

            repeat:
            for (int i = 0; i < size - 1; i++)
            {
                for (int j = i + 1; j < size; j++)
                {
                    // Clone current best permutation.
                    next = new ArrayList<Integer>(current);

                    // Mutate clone to produce child.
                    operator.mutate(next, i, j);

                    // Calculate total trip distance of child.
                    nextDistance = tsp.getTotalDistance(next);

                    // Compare parent and child.
                    if (nextDistance < currentDistance)
                    {
                        // If child is more fit than parent, use child as new best
                        // and repeat the while loop.
                        current = new ArrayList<Integer>(next);
                        currentDistance = nextDistance;
                        break repeat;
                    }
                }
                // Optimal condition.
                if (i == size - 2)
                {
                    break finish;
                }
            }
        }

        // Return number of iterations required to meet local optimum.
        return count;
    }
}
