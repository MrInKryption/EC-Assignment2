import java.util.ArrayList;
import java.util.Collections;

// Class which implements the TwoOptOperator. 
public class TwoOptOperator implements ILocalSearchOperator
{
    // Implements the TwoOptOperator.
    public void mutate(ArrayList<Integer> permutation, int first, int second)
    {
        while (true)
        {
            if (first == second)
            {
                break;
            }
            else if (first+1 == second)
            {
                Collections.swap(permutation, first, second);
                break;
            }
            else
            {
                Collections.swap(permutation, first, second);
                first++;
                second--;
            }
        }
    }
    
    // Gets the name of this Local Search Operator.
    public String name()
    {
        return "Two opt";
    }
}
