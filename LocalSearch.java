import java.util.ArrayList;

// Generic class which implements local search. 
public class LocalSearch
{
    private int count;

    // Function performs local search on the traveling salesperson problem,
    // using operator to define the neighbourhood. 
    public Double search(TSP_Instance problem, ILocalSearchOperator operator)
    {
        // Generate an initial permutation.
        ArrayList<Integer> current = problem.initPermutation();
        
        // Create arrayLists to keep track of next and best solutions. 
        ArrayList<Integer> next;
        ArrayList<Integer> nextBest = new ArrayList<Integer>();

        // Create doubles to keep track of distance. 
        Double currentDistance = problem.getTotalDistance(current);
        Double nextDistance;
        Double tempDistance;

        // Create boolean to track when the local optima is found.
        boolean optimal = false;

        // Create integer to store the size of the permutations.
        int bounds = current.size();

        // Set count to 0 to prepare counting the number of times the while
        // loop is repeated.
        count = 0;
        
        // Loop until an optimal solution is found. 
        while (!optimal)
        {
            // Increment count.
            count++;

            // Set best distance for this permutation as the current best distance found.
            nextDistance = currentDistance;
            
            // For each dimension
            for (int i = 0; i < bounds; i++)
            {
                // For each dimension
                for (int j = i+1; j < bounds; j++)
                {
                    // The next permutation starts as a duplicate of the current. 
                    next = new ArrayList<Integer>(current);
                    
                    // Mutate the next permutation. 
                    operator.mutate(next, i, j);

                    // Calculate total euclidean distance of the mutated permutation.
                    tempDistance = problem.getTotalDistance(next);
                    
                    // If the mutated permutation gives a shorter length
                    // than the previous permutation, keep it as current best. 
                    if (nextDistance > tempDistance)
                    {
                        nextBest = new ArrayList<Integer>(next);
                        nextDistance = tempDistance;
                    }
                }
            }
            
            // This condition will be true if nothing in the neighbourhood 
            // has shorter distance than the current distance.
            // If so we have our optimal solution. 
            if (nextDistance == currentDistance)
            {
                optimal = true;
            }
            else
            {
                // Update the current solution to be the best solution found
                // in the neighbourhood. 
                current = new ArrayList<Integer>(nextBest);
                currentDistance = nextDistance;
            }
        }
        return currentDistance;
    }

    public int getCount()
    {
        return count;
    }
}
