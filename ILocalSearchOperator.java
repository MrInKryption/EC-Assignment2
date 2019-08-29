import java.util.ArrayList;

public interface ILocalSearchOperator
{
    public void mutate(ArrayList<Integer> permutation, int first, int second);
    public String name();
}
